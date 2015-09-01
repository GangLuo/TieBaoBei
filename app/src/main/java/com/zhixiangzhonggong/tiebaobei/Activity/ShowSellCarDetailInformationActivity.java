package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.adapter.SellCarPictureGalleryAdapter;
import com.zhixiangzhonggong.tiebaobei.database.CarInformationDB;
import com.zhixiangzhonggong.tiebaobei.database.CarPictureUrlDB;
import com.zhixiangzhonggong.tiebaobei.model.CarInformation;
import com.zhixiangzhonggong.tiebaobei.model.UserLoadPictureUrl;
import com.zhixiangzhonggong.tiebaobei.util.Bimp;
import com.zhixiangzhonggong.tiebaobei.util.PublicWay;
import com.zhixiangzhonggong.tiebaobei.util.Res;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ShowSellCarDetailInformationActivity extends Activity {
    private TextView mCarBrandAndModelName,mCarId,mCarSellPrice,mCarSellerName,mCarAdminName,
        mCarUsedHours,mCarPublishDate,mCarCurrentSite,mCarOriginalPurpose,mCarProducedSite,
        mCarVerifyPaper,mCarIfAlteration,mCarProducedDate,mCarBoughtDate,mCarCurrentState,
        mCarSequenceNumber,mCarIfBigRepair,mCarState,mCarDetailDescriber,mCarUserPhone,mBackText;
    private Button callSellerPhone;
    private ImageView mBackImage;
    private long carId;
    private LinearLayout mShareLinearLayout;
    private CarInformationDB carInformationDB;
    private CarInformation carInformation;
    private Gallery mGallery;
    private CarPictureUrlDB carPictureUrlDB;
    private UserLoadPictureUrl userLoadPictureUrl;
    private SellCarPictureGalleryAdapter sellCarPictureGalleryAdapter;
    private ArrayList<String> mUrlList;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Res.init(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_show_sell_car_detail_information);
        PublicWay.activityList.add(this);
        initView();
        carPictureUrlDB=new CarPictureUrlDB(this);
        userLoadPictureUrl=new UserLoadPictureUrl();


        Intent intent = getIntent();
        carId=intent.getIntExtra("carId", 0);
      //  carId=intent.getLongExtra("carId",0L);
        getImagesUrlFromDB();

        sellCarPictureGalleryAdapter=new SellCarPictureGalleryAdapter(this,mUrlList);
        mGallery.setAdapter(sellCarPictureGalleryAdapter);
        mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Bimp.tempSelectBitmap.size()) {
                    Log.i("ddddddd", "----------");
                    // ll_popup.startAnimation(AnimationUtils.loadAnimation(ShowSellCarDetailInformationActivity.this, R.anim.activity_translate_in));
                    //pop.showAtLocation(this, Gravity.BOTTOM, 0, 0);
                } else {
                    Intent intent = new Intent(ShowSellCarDetailInformationActivity.this,
                            GalleryActivity.class);
                    intent.putExtra("position", "3");
                    intent.putExtra("ID", arg2);
                    intent.putExtra("isFromShowSellCarDetalActivity", true);
                    startActivity(intent);
                }
            }
        });

        setAllTextValues();


        // add PhoneStateListener
        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bimp.tempSelectBitmap.clear();
                finish();
            }
        });

        callSellerPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber="tel:"+mCarUserPhone.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(phoneNumber));
                startActivity(callIntent);
            }
        });

        ShareSDK.initSDK(this);
        context=this;
        mShareLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ShareSDK.initSDK(this);
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle(getString(R.string.share));
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");

                // 启动分享GUI
                oks.show(context);
            }
        });
    }

    private void getImagesUrlFromDB() {
        mUrlList=new ArrayList<>();

        userLoadPictureUrl=carPictureUrlDB.getUserLoadPictureUrlByCarId(carId);

        String url1=userLoadPictureUrl.getPictureUrl1();
        String url2=userLoadPictureUrl.getPictureUrl2();
        String url3=userLoadPictureUrl.getPictureUrl3();
        String url4=userLoadPictureUrl.getPictureUrl4();
        String url5=userLoadPictureUrl.getPictureUrl5();
        String url6=userLoadPictureUrl.getPictureUrl6();
        String url7=userLoadPictureUrl.getPictureUrl7();
        String url8=userLoadPictureUrl.getPictureUrl8();
        String url9=userLoadPictureUrl.getPictureUrl9();
        if(url1!=null){
            mUrlList.add(url1);
        }
        if (url2!=null){
            mUrlList.add(url2);
        }
         if (url3!=null){
            mUrlList.add(url3);
        }
         if (url4!=null){
            mUrlList.add(url4);
        }
         if (url5!=null){
            mUrlList.add(url5);
        }
        if (url6!=null){
            mUrlList.add(url6);
        }
         if (url7!=null){
            mUrlList.add(url7);
        }
         if (url8!=null){
            mUrlList.add(url8);
        }
         if (url9!=null){
            mUrlList.add(url9);
        }

    }

    private void setAllTextValues() {

       // SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

        carInformationDB=new CarInformationDB(this);
        carInformation=new CarInformation();
        carInformation=carInformationDB.getCarInformationByCarId(carId);
        mCarBrandAndModelName.setText(carInformation.getCarBrand()+carInformation.getCarModel());
        mCarId.setText(String.valueOf(carId));
        mCarSellPrice.setText(this.getString(R.string.yun_character)+String.valueOf(carInformation.getCarPrice())+"万");

        mCarSellerName.setText(carInformation.getCarUserName());
        mCarUsedHours.setText(String.valueOf(carInformation.getCarUsedHours()));
        mCarPublishDate.setText(carInformation.getCarPublishDate());
        mCarCurrentSite.setText(carInformation.getCarSite());
        mCarOriginalPurpose.setText(carInformation.getCarUsedPurpose());
        //mCarProducedSite.setText(carInformation.);
        mCarProducedDate.setText(carInformation.getCarProducedYear());
        mCarCurrentState.setText(carInformation.getCarUsedState());
        mCarDetailDescriber.setText(carInformation.getCarUserDescriber());
        mCarUserPhone.setText(carInformation.getCarUserPhone());


    }


    private void initView() {

        mCarBrandAndModelName= (TextView) findViewById(R.id.show_sell_car_brand_name_id);
        mCarId= (TextView) findViewById(R.id.show_sell_car_id);
        mCarSellPrice=(TextView) findViewById(R.id.show_sell_car_price_id);
        mCarSellerName=(TextView) findViewById(R.id.show_sell_car_user_name_id);
        mCarAdminName=(TextView) findViewById(R.id.show_sell_car_admin_name_id);
        mCarUsedHours=(TextView) findViewById(R.id.show_sell_car_used_hours_id);
        mCarPublishDate=(TextView) findViewById(R.id.show_sell_car_publish_date_id);
        mCarCurrentSite=(TextView) findViewById(R.id.show_sell_car_site_id);
        mCarOriginalPurpose=(TextView) findViewById(R.id.show_sell_car_purpose_id);
        mCarProducedSite=(TextView) findViewById(R.id.show_sell_car_produced_site_id);
        mCarVerifyPaper=(TextView) findViewById(R.id.show_sell_car_verify_id);
        mCarIfAlteration=(TextView) findViewById(R.id.show_sell_car_if_alteration_id);
        mCarProducedDate=(TextView) findViewById(R.id.show_sell_car_produced_date_id);
        mCarBoughtDate=(TextView) findViewById(R.id.show_sell_car_bought_date_id);
        mCarCurrentState=(TextView) findViewById(R.id.show_sell_car_current_state_id);
        mCarSequenceNumber=(TextView) findViewById(R.id.show_sell_car_sequence_number_id);
        mCarIfBigRepair=(TextView) findViewById(R.id.show_sell_car_if_big_repair_id);
        mCarState=(TextView) findViewById(R.id.show_sell_car_state_id);
        mCarDetailDescriber=(TextView) findViewById(R.id.show_sell_car_detail_describer_id);
        mCarUserPhone=(TextView) findViewById(R.id.show_sell_car_user_name_phone_id);
        mBackText=(TextView) findViewById(R.id.show_sell_car_back_text_id);
        callSellerPhone= (Button) findViewById(R.id.dial_phone_Button);
        mBackImage= (ImageView) findViewById(R.id.show_sell_car_back_image_id);
        mShareLinearLayout= (LinearLayout) findViewById(R.id.show_sell_car_if_share_id);
        mGallery= (Gallery) findViewById(R.id.sell_car_picture_gallery);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Bimp.tempSelectBitmap.clear();
            finish();

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_sell_car_detail_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //monitor phone call activities
    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    Intent i = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(
                                    getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                    isPhoneCalling = false;
                }

            }
        }
    }


}
