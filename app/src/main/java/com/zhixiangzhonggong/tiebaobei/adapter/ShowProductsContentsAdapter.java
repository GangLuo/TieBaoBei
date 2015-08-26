package com.zhixiangzhonggong.tiebaobei.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.android.volley.toolbox.NetworkImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.model.CarInformationList;
import com.zhixiangzhonggong.tiebaobei.webrequest.AppController;


public class ShowProductsContentsAdapter extends BaseAdapter{
    private static AppController mAppController= AppController.getInstance();
    private LayoutInflater inflater;
    private Activity activity;
    private CarInformationList carInformationList;
   // private ImageLoader imageLoader= AppController.getInstance().getImageLoader();
    private NetworkImageView mMachinePicture;
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
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.show_products_items, null);
       /* if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView mMachinePicture= (NetworkImageView) convertView.findViewById(R.id.machine_image_id);*/
        mMchineName= (TextView) convertView.findViewById(R.id.machine_name_id);
        mMachinePrice=(TextView) convertView.findViewById(R.id.machine_price_id);
        mMchineUsedTime=(TextView) convertView.findViewById(R.id.machine_used_time_id);
        mMachinePublishDate=(TextView) convertView.findViewById(R.id.machine_publish_date_id);
        mMchineSite=(TextView) convertView.findViewById(R.id.machine_site_id);
       // String filePath = new String("file:///" + storeDish.getMediaLocalUrl() + "/" + storeDish.getMediaUrl());
       // Log.d(TAG, filePath);
       // imageLoader.displayImage(filePath, dbImage, imgDisplayOptions);
        return convertView;
    }
}
