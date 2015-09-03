package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.CustomizedClass.CustomAutoCompleteTextChangedListener;
import com.zhixiangzhonggong.tiebaobei.CustomizedClass.HideEditorKeyboard;
import com.zhixiangzhonggong.tiebaobei.CustomizedView.CustomAutoCompleteView;
import com.zhixiangzhonggong.tiebaobei.CustomizedView.SlidingMenu;
import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.database.CarInformationDB;
import com.zhixiangzhonggong.tiebaobei.database.CarPictureUrlDB;
import com.zhixiangzhonggong.tiebaobei.model.CarInformation;

import java.util.List;

public class MainActivity extends Activity {
    private HideEditorKeyboard mHideEditor;
    private ImageView settingButton;
    private SlidingMenu mSlidingMenu;
    private TextView mLogin;
    private TextView mSignUp;
    private TextView mSetting;
    private TextView mFeedBack;
    private TextView mAboutUs;
    private TextView mAllMachineModel;
    private TextView mSellCar;
    private ImageView mBySmallPeopleImageLogin;
    private Button searchButton;
    private int selectedOrder;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    public CustomAutoCompleteView myAutoCompleteView;
    public ArrayAdapter<String> myAdapter;
    private CarInformationDB carInformationDB;
    public String[] item = new String[] {"Please search..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       // supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //delete actionbar
        setContentView(R.layout.activity_main);
        initView();
        mHideEditor=new HideEditorKeyboard(this);
        mHideEditor.setupUI(findViewById(R.id.mainActivityId));
        carInformationDB=new CarInformationDB(this);

        mBySmallPeopleImageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                startActivity(intent);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                startActivity(intent);
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);

                startActivity(intent);
            }
        });

        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);

                startActivity(intent);
            }
        });

        mFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeedBackActivity.class);

                startActivity(intent);
            }
        });

        mAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IntroduceUsActivity.class);

                startActivity(intent);
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.toggle();

            }
        });

        mAllMachineModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOrder = 3;
                pref = getApplicationContext().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);

                pref = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
                editor = pref.edit();
                editor.putInt("selectedOrder", selectedOrder);

                editor.commit();
                Intent intent = new Intent(getApplicationContext(), ChooseMachineModelActivity.class);

                startActivity(intent);
            }
        });

        mSellCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOrder = 5;
                pref = getApplicationContext().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);

                pref = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
                editor = pref.edit();
                editor.putInt("selectedOrder", selectedOrder);

                editor.commit();
                Intent intent = new Intent(getApplicationContext(), ChooseMachineModelActivity.class);

                startActivity(intent);
            }
        });

    //below is auto fill textview method if you need
       myAutoCompleteView.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
        myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
        myAutoCompleteView.setAdapter(myAdapter);
        myAutoCompleteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             /*   Intent intent=new Intent(MainActivity.this,ShowSellCarDetailInformationActivity.class);
                intent.putExtra("carId",position);
                startActivity(intent);*/
            }
        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowSearchResultActivity.class);
                String input=myAutoCompleteView.getText().toString();
                intent.putExtra("input",input);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        mSlidingMenu= (SlidingMenu) findViewById(R.id.slidingMenu);
        settingButton= (ImageView) findViewById(R.id.setting_btn);
        mLogin= (TextView) findViewById(R.id.login_left_menu_id);
        mSignUp= (TextView) findViewById(R.id.signUp_left_menu_id);
        mSetting= (TextView) findViewById(R.id.setting_left_menu_id);
        mFeedBack=(TextView) findViewById(R.id.feedBack_left_menu_id);
        mAboutUs=(TextView) findViewById(R.id.aboutUs_left_menu_id);
        mSellCar= (TextView) findViewById(R.id.sell_car_id);
        mAllMachineModel=(TextView) findViewById(R.id.all_machine_model_id);
        mBySmallPeopleImageLogin= (ImageView) findViewById(R.id.login_small_people_image);
        myAutoCompleteView= (CustomAutoCompleteView) findViewById(R.id.main_search_field_id);
        searchButton= (Button) findViewById(R.id.main_search_button);

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return true;
    }


    // this function is used in CustomAutoCompleteTextChangedListener.java
    public String[] getItemsFromDb(String searchTerm){

        // add items on the array dynamically
        List<CarInformation> products = carInformationDB.readCarInformationFromDB(searchTerm);
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (CarInformation record : products) {

            item[x] = record.getCarModel();
            x++;
        }

        return item;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
