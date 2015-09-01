package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhixiangzhonggong.tiebaobei.CustomizedClass.HideEditorKeyboard;
import com.zhixiangzhonggong.tiebaobei.CustomizedClass.SaveImageToMemory;
import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.database.UserAccountInFormationDB;
import com.zhixiangzhonggong.tiebaobei.model.UserAccountInformationModel;

public class SignUpActivity extends Activity {
    private HideEditorKeyboard mHideEditor;
    private ImageView mBackImage;
    private TextView mLoginText;
    private EditText mUserName,mEmail,mPassword,mConfirmPassword;
    private Button mSignUpButton;
    private int i ;
    private UserAccountInFormationDB userAccountInFormationDB;
    private UserAccountInformationModel userAccountInformationModel;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mHideEditor=new HideEditorKeyboard(this);
        mHideEditor.setupUI(findViewById(R.id.sign_up_input_field_id));
        initView();
        userAccountInFormationDB=new UserAccountInFormationDB(this);
        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
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
                        i=0;
                        showAlertDialog("密码不相同，请再次输入");
                    }
                }
                else {
                    userAccountInformationModel=new UserAccountInformationModel();
                    userAccountInformationModel.setUserAliasName(userName);
                    userAccountInformationModel.setEmail(email);
                    userAccountInformationModel.setPassword(password);

                   long rowID= userAccountInFormationDB.insertUserAccountInformation(userAccountInformationModel);

                    Toast.makeText(SignUpActivity.this,rowID+
                            "注册成功,请登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void initView() {

        mLoginText= (TextView) findViewById(R.id.sign_up_login_text);
        mBackImage= (ImageView) findViewById(R.id.sign_up_back_image);
        mUserName= (EditText) findViewById(R.id.usernameField);
        mEmail= (EditText) findViewById(R.id.emailField);
        mPassword=(EditText) findViewById(R.id.passwordText);
        mConfirmPassword=(EditText) findViewById(R.id.confirmPwText);
        mSignUpButton= (Button) findViewById(R.id.signupButton);
    }


    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        if(i==0){
            builder.setMessage(message)
                    .setPositiveButton(android.R.string.ok, null);
        }
        else {
            builder.setMessage("请输入"+message+"信息")
                    .setPositiveButton(android.R.string.ok, null);
        }

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
