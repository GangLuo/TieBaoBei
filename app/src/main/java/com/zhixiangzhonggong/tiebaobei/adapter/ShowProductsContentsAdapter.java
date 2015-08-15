package com.zhixiangzhonggong.tiebaobei.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.zhixiangzhonggong.tiebaobei.Activity.ShowProductsActivity;
import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.model.ProductsList;
import com.zhixiangzhonggong.tiebaobei.webrequest.AppController;

/**
 * Created by luogang on 15-08-15.
 */
public class ShowProductsContentsAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private Activity activity;
    private ProductsList productsList;
   // private ImageLoader imageLoader= AppController.getInstance().getImageLoader();
    private NetworkImageView mMachinePicture;
    private TextView mMchineName,mMachinePrice,mMchineUsedTime,mMachinePublishDate,mMchineSite;
    public ShowProductsContentsAdapter(Activity activity, ProductsList productsList) {
        this.activity = activity;
        this.productsList = productsList;
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

        return convertView;
    }
}
