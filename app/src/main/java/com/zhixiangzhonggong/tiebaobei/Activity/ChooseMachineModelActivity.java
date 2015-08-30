package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.R;

public class ChooseMachineModelActivity extends Activity {
    private ImageView mBackImage;
    private TextView mMixerTruck;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_machine_model);
        initView();

        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChooseMachineModelActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mMixerTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getApplicationContext().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);

                editor = pref.edit();
                int selectedOrder = pref.getInt("selectedOrder", 0);
                Intent intent= new Intent();
                if(selectedOrder==3){
                    intent = new Intent(getApplicationContext(), ShowProductsActivity.class);
                }
                else if(selectedOrder==5){
                    intent = new Intent(getApplicationContext(), SellCarInformationActivity.class);
                }


                startActivity(intent);
            }
        });
    }

    private void initView() {
        mBackImage= (ImageView) findViewById(R.id.machine_back_image);
        mMixerTruck= (TextView) findViewById(R.id.machine_mixer_truck_id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_machine_model, menu);
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
