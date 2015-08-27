package com.zhixiangzhonggong.tiebaobei.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.toolbox.NetworkImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.database.CarInformationDB;
import com.zhixiangzhonggong.tiebaobei.model.CarInformation;
import com.zhixiangzhonggong.tiebaobei.model.CarInformationList;
import com.zhixiangzhonggong.tiebaobei.webrequest.AppController;


public class ShowProductsContentsAdapter extends BaseAdapter{
    private static AppController mAppController= AppController.getInstance();
    private LayoutInflater inflater;
    private Activity activity;
    private CarInformationList carInformationList;
    private CarInformation carInformation;
   // private ImageLoader imageLoader= AppController.getInstance().getImageLoader();
    private ImageView mMachinePicture;
    private TextView mMchineName,mMachinePrice,mMchineUsedTime,mMachinePublishDate,mMchineSite;

    static ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mAppController)
            .memoryCacheSize(41943040)
            .diskCacheSize(104857600)
            .threadPoolSize(10)
            .build();
    static DisplayImageOptions imgDisplayOptions = new DisplayImageOptions.Builder()
            .cacheInMemory()
            .cacheOnDisk(true)
            .build();
    static ImageLoader imageLoader= ImageLoader.getInstance();
    public ShowProductsContentsAdapter(Activity activity, CarInformationList carInformationList) {
        this.activity = activity;
        imageLoader.init(config);
        this.carInformationList = carInformationList;
    }

    @Override
    public int getCount() {
        return carInformationList.getCarInformationArrayList().size();
    }

    @Override
    public Object getItem(int position) {
        return carInformationList.getCarInformationArrayList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.show_products_items, null);

        mMachinePicture= (ImageView) convertView.findViewById(R.id.machine_image_id);
        mMchineName= (TextView) convertView.findViewById(R.id.machine_name_id);
        mMachinePrice=(TextView) convertView.findViewById(R.id.machine_price_id);
        mMchineUsedTime=(TextView) convertView.findViewById(R.id.machine_used_time_id);
        mMachinePublishDate=(TextView) convertView.findViewById(R.id.machine_publish_date_id);
        mMchineSite=(TextView) convertView.findViewById(R.id.machine_site_id);

        carInformation=new CarInformation();

        carInformation=carInformationList.getCarInformationArrayList().get(position);

        mMchineName.setText(carInformation.getCarBrand()+carInformation.getCarModel());
        mMachinePrice.setText(String.valueOf(carInformation.getCarPrice()));
        mMchineUsedTime.setText(String.valueOf(carInformation.getCarUsedHours()));
        mMachinePublishDate.setText((CharSequence) carInformation.getCarPublishDate());
        mMchineSite.setText(carInformation.getCarSite());

        //pictureLocalUrl "/data/data/com.zhixiangzhonggong.tiebaobei/app_imageDir"
       String filePath = new String("file:///" + "/data/data/com.zhixiangzhonggong.tiebaobei/app_imageDir" + "/" + carInformation.getCarPictureLocalName());
       Log.d("",filePath);
        imageLoader.displayImage(filePath, mMachinePicture, imgDisplayOptions);
        return convertView;
    }
}
