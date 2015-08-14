package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.R;

public class LoginActivity extends Activity {
    private TextView forgetPassWorldText;
    private ImageButton checkButton;
    private ImageView backIamge;
    private TextView mLogin;
    private boolean colorChanged=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        backIamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!colorChanged ) {
                    checkButton.setImageResource(R.drawable.check);
                    colorChanged = true;
                } else  {
                    checkButton.setImageResource(R.drawable.uncheck);
                    colorChanged = false;

                }


            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);

                startActivity(intent);
            }
        });
    }

    private void initView() {
        forgetPassWorldText= (TextView) findViewById(R.id.forgotPasswordText);
        forgetPassWorldText .getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        checkButton=(ImageButton)findViewById(R.id.checkImageButton);
        checkButton.setImageResource(R.drawable.uncheck);
        backIamge= (ImageView) findViewById(R.id.back_image);
        mLogin= (TextView) findViewById(R.id.sign_up_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
