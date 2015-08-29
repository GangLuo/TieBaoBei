package com.zhixiangzhonggong.tiebaobei.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ShowSellCarDetailInformationActivity extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sell_car_detail_information);

        initView();
        carPictureUrlDB=new CarPictureUrlDB(this);
        userLoadPictureUrl=new UserLoadPictureUrl();


        Intent intent = getIntent();
        carId=intent.getIntExtra("carId", 0);
      //  carId=intent.getLongExtra("carId",0L);
        getImagesUrlFromDB();
        sellCarPictureGalleryAdapter=new SellCarPictureGalleryAdapter(this,mUrlList);
        mGallery.setAdapter(sellCarPictureGalleryAdapter);
        setAllTextValues();



        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        mCarSellPrice.setText(String.valueOf(carInformation.getCarPrice()));
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


}
