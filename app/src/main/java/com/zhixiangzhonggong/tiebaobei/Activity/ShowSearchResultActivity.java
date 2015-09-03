package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.adapter.ShowProductsContentsAdapter;
import com.zhixiangzhonggong.tiebaobei.database.CarInformationDB;
import com.zhixiangzhonggong.tiebaobei.model.CarInformation;
import com.zhixiangzhonggong.tiebaobei.model.CarInformationList;

import java.util.ArrayList;
import java.util.List;

public class ShowSearchResultActivity extends Activity {
    private ListView searchResultContents;
    private ImageView mBackImageView;
    private TextView searchTitleText;
    private ShowProductsContentsAdapter showProductsContentsAdapter;
    private CarInformationDB carInformationDB;
    private CarInformationList carInformationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_search_result);
        carInformationDB=new CarInformationDB(this);
        carInformationList=new CarInformationList();
        initView();
        Intent intent=getIntent();
        String input=intent.getStringExtra("input");
        ArrayList<CarInformation> products = carInformationDB.readAllCarInformationFromDB(input);
        carInformationList.setCarInformationArrayList(products);
        showProductsContentsAdapter=new ShowProductsContentsAdapter(ShowSearchResultActivity.this,carInformationList);
        searchResultContents.setAdapter(showProductsContentsAdapter);

        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ShowSearchResultActivity.this, MainActivity.class);
                startActivity(intent1);

            }
        });
        searchTitleText.setText(input);

    }

    private void initView() {
        searchResultContents= (ListView) findViewById(R.id.search_result_list_id);
        mBackImageView= (ImageView) findViewById(R.id.search_back_image);
        searchTitleText= (TextView) findViewById(R.id.search_title);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_search_result, menu);
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
