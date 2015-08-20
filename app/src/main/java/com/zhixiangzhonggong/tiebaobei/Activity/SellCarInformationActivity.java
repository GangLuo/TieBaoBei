package com.zhixiangzhonggong.tiebaobei.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhixiangzhonggong.tiebaobei.CustomizedClass.HideEditorKeyboard;
import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.util.Bimp;
import com.zhixiangzhonggong.tiebaobei.util.FileUtils;
import com.zhixiangzhonggong.tiebaobei.util.ImageItem;
import com.zhixiangzhonggong.tiebaobei.util.PublicWay;
import com.zhixiangzhonggong.tiebaobei.util.Res;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

public class SellCarInformationActivity extends BaseActivity implements  OnWheelChangedListener {
    private PopupWindow pop = null;
    private PopupWindow pop1=null;
    private LinearLayout ll_popup;
    private LinearLayout mSiteLayout,mCarBrandModelLayout;
    private GridAdapter adapter;
    private View parentView,view;
    private TextView mSiteText,mBrandText,mModelText;
    private ImageView mbackbutton,mLoginImage;
    public static Bitmap bimap ;
    private Gallery mGallery;
    int width,height;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button mBtnConfirm;
    private HideEditorKeyboard mHideEditor;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Res.init(this);
        setContentView(R.layout.activity_sell_car_information);
        bimap = BitmapFactory.decodeResource(
                getResources(),
                R.drawable.icon_addpic_unfocused);
        PublicWay.activityList.add(this);
        parentView = getLayoutInflater().inflate(R.layout.activity_sell_car_information, null);
        setContentView(parentView);
        //hide editext key board when click other place
        mHideEditor=new HideEditorKeyboard(this);
        mHideEditor.setupUI(findViewById(R.id.sellCarInfromationId));

        Init();


        //pop up choose site popwindow
        mSiteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getApplicationContext().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);

                pref = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
                editor = pref.edit();
                editor.putString("mySelected", "site");
                editor.commit();
                InitSitePopUpWindow();
                pop1.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

            }
        });

        //pop up the car model and  brand popupwindow
        mCarBrandModelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getApplicationContext().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);

                pref = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
                editor = pref.edit();
                editor.putString("mySelected", "brand");
                editor.commit();
                InitBrandPopUpWindow();
                pop1.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    //init brand model pop up windows
    public void InitBrandPopUpWindow(){
        InitPopUpWindow();
        setExcavatorData();

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBrandText.setText(mCurentExcavatorName);
                mModelText.setText(mCurrentExcavatorModelName);
                mModelText.setTextSize(10);
                mModelText.setTextColor(Color.BLUE);
                mBrandText.setTextSize(10);
                mBrandText.setTextColor(Color.BLUE);
                pop1.dismiss();
            }
        }) ;
    }




    //init site pop up window
        public void InitSitePopUpWindow(){
            InitPopUpWindow();
            setUpData();
            mBtnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSiteText.setText(mCurrentProviceName + "," + mCurrentCityName + ","
                            + mCurrentDistrictName);
                    mSiteText.setTextSize(10);
                    mSiteText.setTextColor(Color.BLUE);
                    showSelectedResult();
                    pop1.dismiss();
                }
            }) ;
        }



    //init all popup window
    private void InitPopUpWindow() {
        pop1 = new PopupWindow(SellCarInformationActivity.this);
        view = getLayoutInflater().inflate(R.layout.activity_choose_car_site_popupwindow, null);
        pop1.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop1.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop1.setBackgroundDrawable(new BitmapDrawable());
        pop1.setFocusable(true);
        pop1.setOutsideTouchable(true);
        pop1.setContentView(view);

        mViewProvince = (WheelView) view.findViewById(R.id.id_province);
        mViewCity = (WheelView) view.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) view.findViewById(R.id.id_district);
        mBtnConfirm = (Button) view.findViewById(R.id.btn_confirm);

        mViewProvince.addChangingListener(this);

        mViewCity.addChangingListener( this);

        mViewDistrict.addChangingListener(this);
    }

        //init camera window
        public void Init() {
            mbackbutton= (ImageView) findViewById(R.id.car_informatin_back_image);
            mLoginImage= (ImageView) findViewById(R.id.car_informatin_login_small_people_image);
            mSiteText= (TextView) findViewById(R.id.car_site_text);
            mBrandText= (TextView) findViewById(R.id.carBrandTextId);
            mSiteLayout= (LinearLayout) parentView.findViewById(R.id.choose_site_id);
            mCarBrandModelLayout= (LinearLayout) parentView.findViewById(R.id.car_brand_model_layout_id);
            mModelText= (TextView) parentView.findViewById(R.id.carModelTextId);

            mbackbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            mLoginImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                    startActivity(intent);
                }
            });

        //init PopupWindows
        pop = new PopupWindow(SellCarInformationActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        final RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                photo();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SellCarInformationActivity.this,
                        AlbumActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });







        mGallery= (Gallery) findViewById(R.id.main_gallery);
       // mGallery.setSelector(new ColorDrawable(Color.TRANSPARENT));
        //noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
       // noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAdapter(this);
        adapter.update();
        mGallery.setAdapter(adapter);
        //noScrollgridview.setAdapter(adapter);
        mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Bimp.tempSelectBitmap.size()) {
                    Log.i("ddddddd", "----------");
                    ll_popup.startAnimation(AnimationUtils.loadAnimation(SellCarInformationActivity.this, R.anim.activity_translate_in));
                    pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
                } else {
                    Intent intent = new Intent(SellCarInformationActivity.this,
                            GalleryActivity.class);
                    intent.putExtra("position", "1");
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });

    }

    @SuppressLint("HandlerLeak")
    public class GridAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int selectedPosition = -1;
        private boolean shape;

        public boolean isShape() {
            return shape;
        }

        public void setShape(boolean shape) {
            this.shape = shape;
        }

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void update() {
            loading();
        }

        public int getCount() {
            if(Bimp.tempSelectBitmap.size() == 9){
                return 9;
            }
            return (Bimp.tempSelectBitmap.size() + 1);
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int arg0) {
            return 0;
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

            if (position ==Bimp.tempSelectBitmap.size()) {
                holder.image.setImageBitmap(BitmapFactory.decodeResource(
                        getResources(), R.drawable.icon_addpic_unfocused));
                if (position == 9) {
                    holder.image.setVisibility(View.GONE);
                }
            } else {
                holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
            }

            return convertView;
        }

        public class ViewHolder {
            public ImageView image;
        }

        Handler handler = new Handler() {
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
            }).start();
        }
    }

    public String getString(String s) {
        String path = null;
        if (s == null)
            return "";
        for (int i = s.length() - 1; i > 0; i++) {
            s.charAt(i);
        }
        return path;
    }

    protected void onRestart() {
        adapter.update();
        super.onRestart();
    }

    private static final int TAKE_PICTURE = 0x000001;

    public void photo() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {

                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    FileUtils.saveBitmap(bm, fileName);

                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setBitmap(bm);
                    Bimp.tempSelectBitmap.add(takePhoto);
                }
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            for(int i=0;i<PublicWay.activityList.size();i++){
                if (null != PublicWay.activityList.get(i)) {
                    PublicWay.activityList.get(i).finish();
                }
            }
            System.exit(0);
        }
        return true;
    }

    //wheel choose

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(SellCarInformationActivity.this, mProvinceDatas));

        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    private void setExcavatorData(){
        initExcavatorBrandDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(SellCarInformationActivity.this, mExcavatorDatas));
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        updateExcavatorBrand();
        updateExcavatorModel();

    }




    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        pref = this.getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);

        editor = pref.edit();
        String mySelected = pref.getString("mySelected",null);
        if(mySelected.equals("site")){
            if (wheel == mViewProvince) {

                updateCities();
            } else if (wheel == mViewCity) {
                updateAreas();
            } else if (wheel == mViewDistrict) {
                mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
                mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
            }
        }
        else if (mySelected.equals("brand")){
            if (wheel == mViewProvince) {
                updateExcavatorBrand();
                //updateExcavatorModel();
            }else if (wheel == mViewCity){
                updateExcavatorModel();
            }
        }

    }

    private void updateExcavatorModel() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentExcavatorModelName = mExcavatorModelDatasMap.get(mCurentExcavatorName)[pCurrent];

    }

    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[] { "" };
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);
    }


    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[] { "" };
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    private  void updateExcavatorBrand(){
        int pCurrent = mViewProvince.getCurrentItem();
        mCurentExcavatorName = mExcavatorDatas[pCurrent];
        String[] models = mExcavatorModelDatasMap.get(mCurentExcavatorName);
        if (models == null) {
            models = new String[] { "" };
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, models));
        mViewCity.setCurrentItem(0);
        updateExcavatorModel();
    }
    private void showSelectedResult() {
        Toast.makeText(SellCarInformationActivity.this, "地址:" + mCurrentProviceName + "," + mCurrentCityName + ","
                + mCurrentDistrictName + "," + mCurrentZipCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sell_car_information, menu);
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