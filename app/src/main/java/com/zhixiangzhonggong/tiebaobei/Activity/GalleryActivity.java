package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.util.Bimp;
import com.zhixiangzhonggong.tiebaobei.util.PublicWay;
import com.zhixiangzhonggong.tiebaobei.util.Res;
import com.zhixiangzhonggong.tiebaobei.zoom.PhotoView;
import com.zhixiangzhonggong.tiebaobei.zoom.ViewPagerFixed;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个是用于进行图片浏览时的界面
 */
public class GalleryActivity extends Activity {
    private Intent intent;
    // 返回按钮
    private Button back_bt;
    // 发送按钮
    private Button send_bt;
    //删除按钮
    private Button del_bt;
    //顶部显示预览图片位置的textview
    private TextView positionTextView;
    //获取前一个activity传过来的position
    private int position;
    //当前的位置
    private int location = 0;
    //is come from ShowSellCarDetailInfromation activity
    private boolean isFromShowSellCarDetalActivity;
    private ArrayList<View> listViews = null;
    private ViewPagerFixed pager;
    private MyPageAdapter adapter;

    public List<Bitmap> bmp = new ArrayList<Bitmap>();
    public List<String> drr = new ArrayList<String>();
    public List<String> del = new ArrayList<String>();

    private Context mContext;

    RelativeLayout photo_relativeLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(Res.getLayoutID("activity_gallery"));// 切屏到主界面
        setContentView(R.layout.activity_gallery);
        PublicWay.activityList.add(this);
        mContext = this;

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = Integer.parseInt(intent.getStringExtra("position"));
        isFromShowSellCarDetalActivity=getIntent().getExtras().getBoolean("isFromShowSellCarDetalActivity");

        back_bt = (Button) findViewById(R.id.gallery_back);
        //back_bt = (Button) findViewById(Res.getWidgetID("gallery_back"));
        //send_bt = (Button) findViewById(Res.getWidgetID("send_button"));
        send_bt = (Button) findViewById(R.id.send_button);
        //del_bt = (Button)findViewById(Res.getWidgetID("gallery_del"));
        del_bt = (Button)findViewById(R.id.gallery_del);
        if(isFromShowSellCarDetalActivity){
            back_bt.setText("返回");
            del_bt.setVisibility(View.GONE);
            send_bt.setVisibility(View.GONE);
        }
        back_bt.setOnClickListener(new BackListener());
        send_bt.setOnClickListener(new GallerySendListener());
        del_bt.setOnClickListener(new DelListener());



        isShowOkBt();
        // 为发送按钮设置文字
        //pager = (ViewPagerFixed) findViewById(Res.getWidgetID("gallery01"));
        pager = (ViewPagerFixed) findViewById(R.id.gallery01);
        pager.setOnPageChangeListener(pageChangeListener);
        for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
            initListViews( Bimp.tempSelectBitmap.get(i).getBitmap() );
        }

        adapter = new MyPageAdapter(listViews);
        pager.setAdapter(adapter);
        //pager.setPageMargin((int) getResources().getDimensionPixelOffset(Res.getDimenID("ui_10_dip")));
        pager.setPageMargin((int)getResources().getDimensionPixelOffset(R.dimen.ui_10_dip));
        int id = intent.getIntExtra("ID", 0);
        pager.setCurrentItem(id);
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        public void onPageSelected(int arg0) {
            location = arg0;
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void initListViews(Bitmap bm) {
        if (listViews == null)
            listViews = new ArrayList<View>();
        PhotoView img = new PhotoView(this);
        img.setBackgroundColor(0xff000000);
        img.setImageBitmap(bm);
        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        listViews.add(img);
    }

    // 返回按钮添加的监听器
    private class BackListener implements View.OnClickListener {

        public void onClick(View v) {
            if(isFromShowSellCarDetalActivity){
                back_bt.setText("返回");
                finish();
                //intent.setClass(GalleryActivity.this, ShowSellCarDetailInformationActivity.class);
                //startActivity(intent);
            }
            else {
                intent.setClass(GalleryActivity.this, ImageFile.class);
                startActivity(intent);
            }

        }
    }

    // 删除按钮添加的监听器
    private class DelListener implements View.OnClickListener {

        public void onClick(View v) {
            if (listViews.size() == 1) {
                Bimp.tempSelectBitmap.clear();
                Bimp.max = 0;
               // send_bt.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
                send_bt.setText(getResources().getString(R.string.finish1)+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
                Intent intent = new Intent("data.broadcast.action");
                sendBroadcast(intent);
                finish();
            } else {
                Bimp.tempSelectBitmap.remove(location);
                Bimp.max--;
                pager.removeAllViews();
                listViews.remove(location);
                adapter.setListViews(listViews);
                //send_bt.setText(Res.getString("finish") + "(" + Bimp.tempSelectBitmap.size() + "/" + PublicWay.num + ")");
                send_bt.setText(getResources().getString(R.string.finish1)+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
                adapter.notifyDataSetChanged();
            }
        }
    }

    // 完成按钮的监听
    private class GallerySendListener implements View.OnClickListener {
        public void onClick(View v) {
            finish();
            intent.setClass(mContext,SellCarInformationActivity.class);
            startActivity(intent);
        }

    }

    public void isShowOkBt() {
        if (Bimp.tempSelectBitmap.size() > 0) {
            //send_bt.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
            send_bt.setText(getResources().getString(R.string.finish1)+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
            send_bt.setPressed(true);
            send_bt.setClickable(true);
            send_bt.setTextColor(Color.WHITE);
        } else {
            send_bt.setPressed(false);
            send_bt.setClickable(false);
            send_bt.setTextColor(Color.parseColor("#E1E0DE"));
        }
    }

    /**
     * 监听返回按钮
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(position==1){
                this.finish();
                intent.setClass(GalleryActivity.this, AlbumActivity.class);
                startActivity(intent);
            }else if(position==2){
                this.finish();
                intent.setClass(GalleryActivity.this, ShowAllPhoto.class);
                startActivity(intent);
            }else if (position==3){
                this.finish();
            }
        }
        return true;
    }


    class MyPageAdapter extends PagerAdapter {

        private ArrayList<View> listViews;

        private int size;
        public MyPageAdapter(ArrayList<View> listViews) {
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        public void setListViews(ArrayList<View> listViews) {
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        public int getCount() {
            return size;
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPagerFixed) arg0).removeView(listViews.get(arg1 % size));
        }

        public void finishUpdate(View arg0) {
        }

        public Object instantiateItem(View arg0, int arg1) {
            try {
                ((ViewPagerFixed) arg0).addView(listViews.get(arg1 % size), 0);

            } catch (Exception e) {
            }
            return listViews.get(arg1 % size);
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
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
