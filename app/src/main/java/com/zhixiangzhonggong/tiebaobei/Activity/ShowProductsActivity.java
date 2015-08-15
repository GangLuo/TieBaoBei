package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.adapter.ShowProductsContentsAdapter;
import com.zhixiangzhonggong.tiebaobei.model.ProductsList;
import com.zhixiangzhonggong.tiebaobei.webrequest.SendMixerTruckRequest;

public class ShowProductsActivity extends Activity {
    private ProductsList productsList;
    private SendMixerTruckRequest sendMixerTruckRequest;
    private ListView productsListView;
    private ImageView mBackImage;
    private ShowProductsContentsAdapter showProductsContentsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);
        initView();

        showProductsContentsAdapter=new ShowProductsContentsAdapter(ShowProductsActivity.this,productsList);
        productsListView.setAdapter(showProductsContentsAdapter);

        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mBackImage= (ImageView) findViewById(R.id.feed_back_image);
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
