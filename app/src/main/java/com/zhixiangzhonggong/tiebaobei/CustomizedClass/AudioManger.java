package com.zhixiangzhonggong.tiebaobei.CustomizedClass;


import android.media.MediaRecorder;

import com.zhixiangzhonggong.tiebaobei.Activity.FindPasswordActivity;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AudioManger {
    private MediaRecorder mMediaRecorder;
    private String mDir;
    private  String mCurrentFilePath;
    private static AudioManger mInstance;
    public AudioStateListener mListener;
    private boolean isPrepared;

    private AudioManger(String dir){
        mDir=dir;
    }

    public String getCurrentFilePath() {
        return mCurrentFilePath;
    }


    public interface AudioStateListener{
        void wellPrepared();
    }



    public static AudioManger getInstance(String dir){
        if (mInstance==null){
            //??????????????
            synchronized (AudioManger.class)
            {
                if (mInstance==null)
                    mInstance=new AudioManger(dir);
            }
        }
        return mInstance;
    }



    public void setOnAudioStateListener(AudioStateListener listener){

        mListener=listener;
    }



    public void prepareAudio(){
            try {
                isPrepared=false;
                File dir=new File(mDir);
                if (!dir.exists()){
                    dir.mkdir();
                }
                String fileName=generateFileName();
                File file=new File(dir,fileName);

                mCurrentFilePath=file.getAbsolutePath();
                mMediaRecorder=new MediaRecorder();
                //set out put file
                mMediaRecorder.setOutputFile(file.getAbsolutePath());
                //set MediaRecorder's voice is mic
                mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                //set the voice format
                mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);

                mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                mMediaRecorder.prepare();

                mMediaRecorder.start();

                isPrepared=true;
                if (mListener!=null){
                    mListener.wellPrepared();
                }
            }catch (IllegalStateException | IOException e){
            e.printStackTrace();
            }


    }


    /*
    random create file name
     */
    private String generateFileName() {
       return UUID.randomUUID().toString()+".amr";

    }



    public int getVoiceLevel(int maxLevel){

        if (isPrepared){
            try {
                //mMediaRecorder.getMaxAmplitude() 1-32767
                return maxLevel*mMediaRecorder.getMaxAmplitude()/32768+1;
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return 1;
    }


    public void release(){
        mMediaRecorder.stop();
        mMediaRecorder.release();
        mMediaRecorder=null;
    }

    public void cancel(){

        release();
        if (mCurrentFilePath!=null){
            File file=new File(mCurrentFilePath);
            file.delete();
            mCurrentFilePath=null;
        }

    }
}
