package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.zhixiangzhonggong.tiebaobei.CustomizedView.SlidingMenu;
import com.zhixiangzhonggong.tiebaobei.R;

public class MainActivity extends Activity {
    private ImageButton settingButton;
    private SlidingMenu mSlidingMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       // supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //delete actionbar
        setContentView(R.layout.activity_main);
        initView();
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.toggle();

            }
        });
    }

    private void initView() {
        mSlidingMenu= (SlidingMenu) findViewById(R.id.slidingMenu);
        settingButton= (ImageButton) findViewById(R.id.setting_btn);
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
