package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.adapter.ChooseMachineModelAdapter;
import com.zhixiangzhonggong.tiebaobei.util.Bimp;

import java.util.ArrayList;

public class ChooseMachineModelActivity extends Activity {
    private ImageView mBackImage;
    private TextView mMixerTruck;
    private ListView machineModelNameList;
    private ChooseMachineModelAdapter chooseMachineModelAdapter;
    private ArrayList<String> machineModelNames;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_machine_model);
        initView();
        machineModelNames=new ArrayList<>();
        String[] modelNames={"搅拌车","砂浆泵","泵车","小型挖掘机","大型挖掘机","装载机","推土机","配件","其他"};
        for (String names : modelNames){
            machineModelNames.add(names);
        }
        chooseMachineModelAdapter=new ChooseMachineModelAdapter(this,machineModelNames);

        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseMachineModelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        machineModelNameList.setAdapter(chooseMachineModelAdapter);
        machineModelNameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name= (String) chooseMachineModelAdapter.getItem(position);
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
                intent.putExtra("chooseMachineModel",name);
                startActivity(intent);
            }

        });
    }

    private void initView() {
        mBackImage= (ImageView) findViewById(R.id.machine_back_image);
        machineModelNameList= (ListView) findViewById(R.id.chooseMachineListViewId);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


           Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        return true;
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
