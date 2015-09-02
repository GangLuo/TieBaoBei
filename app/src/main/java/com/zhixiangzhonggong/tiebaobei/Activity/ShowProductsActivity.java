package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.CustomizedClass.ProductBrandFragment;
import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.adapter.ShowProductsContentsAdapter;
import com.zhixiangzhonggong.tiebaobei.database.CarInformationDB;
import com.zhixiangzhonggong.tiebaobei.model.CarInformationList;
import com.zhixiangzhonggong.tiebaobei.webrequest.SendMixerTruckRequest;

public class ShowProductsActivity extends Activity {
    private CarInformationList carInformationList;
    private SendMixerTruckRequest sendMixerTruckRequest;
    private ListView productsListView;
    private ImageView mBackImage;
    private TextView mBrands,mPrice,mProvice,mMore,mOrder;
    private FrameLayout mFragmentLayout;
    private int buttonNumber;
    private ProductBrandFragment productBrandFragment;
    private ShowProductsContentsAdapter showProductsContentsAdapter;
    private CarInformationDB carInformationDB=new CarInformationDB(this);
    FragmentManager fm ;
    FragmentTransaction tx ;
    private boolean i=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);
        initView();

        productBrandFragment =new ProductBrandFragment();

        carInformationList=new CarInformationList();
        carInformationList.setCarInformationArrayList(carInformationDB.getAllInformatons());
        showProductsContentsAdapter=new ShowProductsContentsAdapter(ShowProductsActivity.this, carInformationList);
        productsListView.setAdapter(showProductsContentsAdapter);

        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBrands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            buttonNumber=1;
              String[]  values = new String[] { "不限", "三一重工", "卡特彼勒",
                        "小松", "日立", "斗山", "现代", "神钢",
                        "住友", "柳工","临工", "加藤", "凯斯", "阿特拉斯", "福田雷沃",
                        "玉柴", "中联重科","徐工", "沃得重工", "山东犀牛", "夏工", "合肥振宇"};

                    productBrandFragment.setAdapterValue(values);
                    setFragmentManager();
                    productBrandFragment.updateValues();

               // productBrandFragment.getListView().invalidateViews();
               // productBrandFragment.updateValues();
               // tx.replace(R.id.show_fragment_id, productBrandFragment);
            }
        });

        mPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNumber=2;
                String[]  values = new String[] { "不限", "20万以下", "20-50万",
                        "50-110万", "110万以上"};
                productBrandFragment.setAdapterValue(values);
                setFragmentManager();
                productBrandFragment.updateValues();
            }
        });

        mProvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNumber=3;
                String[]  values = new String[] { "不限", "山东", "江苏",
                        "广东", "四川", "河南", "河北", "辽宁","陕西", "湖南", "湖北", "北京", "山西",
                        "天津", "内蒙古","吉林", "黑龙江", "上海", "浙江", "安徽", "福建", "江西", "广西",
                        "海南","重庆", "贵州", "云南", "西藏", "甘肃","青海", "宁夏", "新疆"};
                productBrandFragment.setAdapterValue(values);
                setFragmentManager();
                productBrandFragment.updateValues();

            }
        });

        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonNumber=4;

                String[]  values = new String[] { "米数", "地盘", "年份",
                        "小时数", "自重", "类型"};
                productBrandFragment.setAdapterValue(values);
                setFragmentManager();
                productBrandFragment.updateValues();
            }
        });

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    buttonNumber=5;

                    String[]  values = new String[] { "默认", "价格", "小时数",
                            "完整度", "发布时间"};

                    setFragmentManager();
                    productBrandFragment.setAdapterValue(values);
                    productBrandFragment.updateValues();

            }
        });


        productsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ShowProductsActivity.this,ShowSellCarDetailInformationActivity.class);

                intent.putExtra("carId",position);
                startActivity(intent);
            }
        });
    }

    private void setFragmentManager() {
         fm = getFragmentManager();
        tx = fm.beginTransaction();
        tx.setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out);
        int olderNumber;
        if(!i){

            tx.replace(R.id.show_fragment_id, productBrandFragment, "Brand");
            tx.addToBackStack(null);
            tx.show(productBrandFragment);
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,300, getResources().getDisplayMetrics());
            mFragmentLayout.setVisibility(View.VISIBLE);
            mFragmentLayout.getLayoutParams().height=height;

           tx.commit();
            i=true;
        }
        else {
            mFragmentLayout.setVisibility(View.GONE);
            tx.hide( productBrandFragment);
            tx.commit();
            i=false;
        }
    }

    private void initView() {
        mBackImage= (ImageView) findViewById(R.id.feed_back_image);
        mBrands= (TextView) findViewById(R.id.brand_id);
        productsListView= (ListView) findViewById(R.id.products_list_id);
        mPrice= (TextView) findViewById(R.id.price_id);
        mProvice= (TextView) findViewById(R.id.province_id);
        mMore= (TextView) findViewById(R.id.more_id);
        mOrder= (TextView) findViewById(R.id.order_id);
        mFragmentLayout= (FrameLayout) findViewById(R.id.show_fragment_id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_products, menu);
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
