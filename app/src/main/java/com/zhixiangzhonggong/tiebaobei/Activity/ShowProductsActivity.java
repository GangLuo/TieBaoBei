package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.CustomizedClass.ProductBrandFragment;
import com.zhixiangzhonggong.tiebaobei.CustomizedClass.ShowProductsContentsFragment;
import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.adapter.ShowProductsContentsAdapter;
import com.zhixiangzhonggong.tiebaobei.model.ProductsList;
import com.zhixiangzhonggong.tiebaobei.webrequest.SendMixerTruckRequest;

public class ShowProductsActivity extends Activity {
    private ProductsList productsList;
    private SendMixerTruckRequest sendMixerTruckRequest;
    private ListView productsListView;
    private ImageView mBackImage;
    private TextView mBrands;
    private ProductBrandFragment productBrandFragment;
    private ShowProductsContentsAdapter showProductsContentsAdapter;
    private boolean i=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);
        initView();
        productBrandFragment =new ProductBrandFragment();



        showProductsContentsAdapter=new ShowProductsContentsAdapter(ShowProductsActivity.this,productsList);
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


                FragmentManager fm = getFragmentManager();
                FragmentTransaction tx = fm.beginTransaction();
                tx.setCustomAnimations(android.R.animator.fade_in,
                        android.R.animator.fade_out);
                if(!i){
                    tx.replace(R.id.show_fragment_id, productBrandFragment, "Brand");
                    tx.show(productBrandFragment);
                    tx.commit();
                    i=true;
                }
                else {

                    tx.hide( productBrandFragment);
                    tx.commit();
                    i=false;
                }
               // tx.replace(R.id.show_fragment_id, productBrandFragment);
            }
        });
    }

    private void initView() {
        mBackImage= (ImageView) findViewById(R.id.feed_back_image);
        mBrands= (TextView) findViewById(R.id.brand_id);
        productsListView= (ListView) findViewById(R.id.products_list_id);
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
