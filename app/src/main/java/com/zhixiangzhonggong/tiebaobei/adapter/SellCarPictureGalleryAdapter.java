package com.zhixiangzhonggong.tiebaobei.adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.database.CarPictureUrlDB;
import com.zhixiangzhonggong.tiebaobei.model.UserLoadPictureUrl;
import com.zhixiangzhonggong.tiebaobei.util.Bimp;
import com.zhixiangzhonggong.tiebaobei.util.ImageItem;
import com.zhixiangzhonggong.tiebaobei.webrequest.AppController;

import java.util.ArrayList;

public class SellCarPictureGalleryAdapter extends BaseAdapter{
    private Context activity;
    private LayoutInflater inflater;
    private UserLoadPictureUrl userLoadPictureUrl;
    private CarPictureUrlDB carPictureUrlDB;
    private ArrayList<String> mImageUrlList;
    private int selectedPosition = -1;
    private boolean shape;
    Bimp bimp;
    ImageItem imageItem;
    private static AppController mAppController= AppController.getInstance();
    private int carId;
    static ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mAppController)
            .memoryCacheSize(41943040)
            .diskCacheSize(104857600)
            .threadPoolSize(10)
            .build();
    static DisplayImageOptions imgDisplayOptions = new DisplayImageOptions.Builder()
            .cacheInMemory()
            .cacheOnDisk(true)
            .build();
    static ImageLoader imageLoader=ImageLoader.getInstance();

    public boolean isShape() {
        return shape;
    }

    public void setShape(boolean shape) {
        this.shape = shape;
    }

    public SellCarPictureGalleryAdapter(Context context,ArrayList<String> mImageUrlList) {
        imageLoader.init(config);
        this.activity=context;
       this.mImageUrlList=mImageUrlList;
        inflater = LayoutInflater.from(context);
    }

  /*  public void update() {
        loading();
    }*/
  @Override
    public int getCount() {

        return mImageUrlList.size();
    }
    @Override
    public Object getItem(int arg0) {
        return mImageUrlList.get(arg0);
    }
    @Override
    public long getItemId(int postion) {
        return postion;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_published_grida,
                    parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView
                    .findViewById(R.id.item_grida_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

    /*    if (position ==Bimp.tempSelectBitmap.size()) {
            holder.image.setImageBitmap(BitmapFactory.decodeResource(
                    getResources(), R.drawable.icon_addpic_unfocused));
            if (position == 9) {
                holder.image.setVisibility(View.GONE);
            }
        } else {
            holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
        }*/
        //+"/data/data/com.zhixiangzhonggong.tiebaobei/app_imageDir"+ "/"
        String filePath = new String("file:///"+mImageUrlList.get(position));
        imageLoader.displayImage(filePath, holder.image, imgDisplayOptions, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                bimp=new Bimp();
                imageItem=new ImageItem();
                imageItem.setBitmap(bitmap);
                bimp.tempSelectBitmap.clear();
                bimp.tempSelectBitmap.add(imageItem);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
        return convertView;
    }

    public class ViewHolder {
        public ImageView image;
    }

/*    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    adapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public void loading() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (Bimp.max == Bimp.tempSelectBitmap.size()) {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                        break;
                    } else {
                        Bimp.max += 1;
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                }
            }
        }).start();*/
   // }
}



