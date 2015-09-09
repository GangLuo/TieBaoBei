package com.zhixiangzhonggong.tiebaobei.CustomizedView;


import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.zhixiangzhonggong.tiebaobei.CustomizedClass.AudioManger;
import com.zhixiangzhonggong.tiebaobei.CustomizedClass.DialogManager;
import com.zhixiangzhonggong.tiebaobei.R;


import java.util.logging.LogRecord;


public class AudioRecordButton extends Button implements AudioManger.AudioStateListener{
private static final int STATE_NORMAL=1;
    private static final int STATE_RECORDING=2;
    private static final int STATE_WANT_TO_CANCEL=3;
    private int mCurState=STATE_NORMAL;
    private  boolean isRecoding=false;
    private static final int DISTANCE_Y_CANCEL=50;
    private DialogManager mDialogManger;
    private AudioManger mAudioManger;
    private float mTime;
    //是否触发longclick
    private boolean mReady;

    public AudioRecordButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        //TODO should be display at end of prepared audio
        mDialogManger=new DialogManager(getContext());
        String dir= Environment.getExternalStorageDirectory()+"/recoder_audios";

        mAudioManger=AudioManger.getInstance(dir);

        mAudioManger.setOnAudioStateListener(this);
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mReady=true;
            mAudioManger.prepareAudio();
                return false;
            }
        });
    }


    public interface AudioFinishRecorderListener{
        void onFinish(float seconds,String filePath);
    }

    private AudioFinishRecorderListener mListener;

    public void setAudioFinishRecorderListerner(AudioFinishRecorderListener listerner){
        mListener=listerner;
    }

    //acquire volum
    private Runnable mGetVoiceLevelRunnable=new Runnable() {
        @Override
        public void run() {
        while (isRecoding){
            try {
                Thread.sleep(100);
                mTime+=0.1f;
                mHandler.sendEmptyMessage(MSG_VOICE_CHANGED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }
    };


    private static final int MSG_AUDIO_PREPARED=0X110;
    private static final int MSG_VOICE_CHANGED=0X111;
    private static final int MSG_DIALOG_DISMISS=0X112;
    //??????????????????
    private Handler mHandler=new Handler()
    {
     public void handleMessage(android.os.Message msg){
         switch (msg.what)
         {
             case MSG_AUDIO_PREPARED:
                 mDialogManger.showRecordingDialog();
                 isRecoding=true;
                 new Thread(mGetVoiceLevelRunnable).start();
                 break;
             case MSG_VOICE_CHANGED:
                 mDialogManger.updateVoiceLevel(mAudioManger.getVoiceLevel(7));
                 break;
             case MSG_DIALOG_DISMISS:
                 mDialogManger.dismissDialog();
                 break;
         }
     };
    };


    @Override
    public void wellPrepared() {
    mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);
    }


    public AudioRecordButton(Context context) {
        this(context, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action=event.getAction();
        int x= (int) event.getX();
        int y= (int) event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
               
                changeState(STATE_RECORDING);
                break;
            case MotionEvent.ACTION_MOVE:

                if (isRecoding) {
                    if (wantToCancel(x, y)) {
                        changeState(STATE_WANT_TO_CANCEL);
                    } else {
                        changeState(STATE_RECORDING);
                    }
                }
                //base x,y coordinate ,decised id need cancel

                break;
            case MotionEvent.ACTION_UP:
                if (!mReady){
                    reset();
                    return super.onTouchEvent(event);
                }
                if (!isRecoding||mTime<0.6f){
                    mDialogManger.tooShort();
                    mAudioManger.cancel();
                    mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DISMISS, 1300);
                }
                else if (mCurState==STATE_RECORDING){
                    //release
                    mDialogManger.dismissDialog();

                    if (mListener!=null){
                        mListener.onFinish(mTime,mAudioManger.getCurrentFilePath());
                    }
                    mAudioManger.release();


                    //callbacktoact
                }else if (mCurState==STATE_WANT_TO_CANCEL){
                    //cancel
                    mDialogManger.dismissDialog();
                    mAudioManger.cancel();
                }
                reset();
                break;
        }
        return super.onTouchEvent(event);
    }
/*

 */
    private void reset() {
        mReady=false;
        mTime=0;
        isRecoding=false;
        changeState(STATE_NORMAL);
    }

    private boolean wantToCancel(int x, int y) {
        if (x<0||x>getWidth()){
            return true;
        }
        if (y<-DISTANCE_Y_CANCEL||y>getHeight()+DISTANCE_Y_CANCEL){
            return true;

        }
        return false;
    }

    private void changeState(int state) {
        if (mCurState!=state){
            mCurState=state;
            switch (state){
                case STATE_NORMAL:
                    setBackgroundResource(R.drawable.btn_recorder_normal);
                    setText(R.string.recorder_nomal);
                    break;
                case STATE_RECORDING:
                    setBackgroundResource(R.drawable.btn_recordering);
                    setText(R.string.recorder_recording);
                    if (isRecoding){
                        //TODO tododailoge
                        mDialogManger.recording();
                    }
                    break;
                case STATE_WANT_TO_CANCEL:
                    setBackgroundResource(R.drawable.btn_recordering);
                    setText(R.string.recorder_want_cancel);
                    mDialogManger.wantToCancle();
                    break;
            }
        }

    }


}
