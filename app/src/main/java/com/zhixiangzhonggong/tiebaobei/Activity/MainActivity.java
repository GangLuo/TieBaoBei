package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.CustomizedView.SlidingMenu;
import com.zhixiangzhonggong.tiebaobei.R;

public class MainActivity extends Activity {
    private ImageView settingButton;
    private SlidingMenu mSlidingMenu;
    private TextView mLogin;
    private TextView mSignUp;
    private TextView mSetting;
    private TextView mFeedBack;
    private TextView mAboutUs;
    private ImageView mBySmallPeopleImageLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       // supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //delete actionbar
        setContentView(R.layout.activity_main);
        initView();

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


    }

    private void initView() {
        mSlidingMenu= (SlidingMenu) findViewById(R.id.slidingMenu);
        settingButton= (ImageView) findViewById(R.id.setting_btn);
        mLogin= (TextView) findViewById(R.id.login_left_menu_id);
        mSignUp= (TextView) findViewById(R.id.signUp_left_menu_id);
        mSetting= (TextView) findViewById(R.id.setting_left_menu_id);
        mFeedBack=(TextView) findViewById(R.id.feedBack_left_menu_id);
        mAboutUs=(TextView) findViewById(R.id.aboutUs_left_menu_id);

        mBySmallPeopleImageLogin= (ImageView) findViewById(R.id.login_small_people_image);

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
