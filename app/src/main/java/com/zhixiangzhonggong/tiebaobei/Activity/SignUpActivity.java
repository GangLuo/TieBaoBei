package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhixiangzhonggong.tiebaobei.R;

public class SignUpActivity extends Activity {
    private ImageView mBackImage;
    private EditText mUserName,mEmail,mPassword,mConfirmPassword;
    private Button mSignUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();

        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=mUserName.getText().toString();
                String email=mEmail.getText().toString();
                String password=mPassword.getText().toString();
                String confirmPassword=mConfirmPassword.getText().toString();
                if (userName.isEmpty()||email.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()
                        ||!password.equals(confirmPassword)){
                    if (userName.isEmpty()){
                        showAlertDialog("用户名");
                    }
                    else if (email.isEmpty()){
                        showAlertDialog("邮箱");
                    }
                    else if (password.isEmpty()){
                        showAlertDialog("密码");
                    }
                    else if (confirmPassword.isEmpty()){
                        showAlertDialog("确认密码");
                    }
                    else if (!password.equals(confirmPassword)){
                        showAlertDialog("确认密码");
                    }
                }
                else {

                }
            }
        });
    }

    private void initView() {
        mBackImage= (ImageView) findViewById(R.id.sign_up_back_image);
        mUserName= (EditText) findViewById(R.id.usernameField);
        mEmail= (EditText) findViewById(R.id.emailField);
        mPassword=(EditText) findViewById(R.id.passwordField);
        mConfirmPassword=(EditText) findViewById(R.id.confirmPwText);
        mSignUpButton= (Button) findViewById(R.id.signupButton);
    }


    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setMessage("请输入"+message+"信息")
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
