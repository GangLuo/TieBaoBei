package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.database.UserAccountInFormationDB;
import com.zhixiangzhonggong.tiebaobei.model.UserAccountInformationModel;

import java.util.ArrayList;

public class LoginActivity extends Activity {
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    private TextView forgetPassWorldText;
    private ImageButton checkButton;
    private Button mLoginButton;
    private ImageView backIamge;
    private TextView mLoginText;
    private EditText mEmail,mPassword;
    private boolean colorChanged=false;
    private UserAccountInFormationDB userAccountInFormationDB;
    private UserAccountInformationModel userAccountInformation;
    private ArrayList<UserAccountInformationModel> userAccountInformationModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        userAccountInFormationDB=new UserAccountInFormationDB(this);
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

        forgetPassWorldText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindPasswordActivity.class);

                startActivity(intent);
            }
        });

        mLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);

                startActivity(intent);
            }
        });



        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= mEmail.getText().toString();
                String password= mPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("请输入用户名和密码")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    userAccountInformationModelArrayList=new ArrayList<UserAccountInformationModel>();
                    userAccountInformationModelArrayList=userAccountInFormationDB.getAllUserAccountInFormations();
                    for(int i=0;i<userAccountInformationModelArrayList.size();i++){
                        userAccountInformation=new UserAccountInformationModel();
                        userAccountInformation=userAccountInformationModelArrayList.get(i);
                        String emailFromDB=userAccountInformation.getEmail();
                        String passwordFromDB=userAccountInformation.getPassword();
                        if(emailFromDB.equals(email)){
                            if (passwordFromDB.equals(password)){
                                if (colorChanged){
                                    pref = getApplicationContext().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
                                    pref = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
                                    editor = pref.edit();
                                    editor.putString("userEmail", email);
                                    editor.putString("userPassword", password);
                                    editor.commit();
                                }
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                ShowDialog("密码不正确");
                            }
                        }
                        else if (!emailFromDB.equals(email)){
                            ShowDialog("用户名不存在");
                        }
                    }

                }
            }
        });
    }

    private void ShowDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage(message)
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initView() {
        forgetPassWorldText= (TextView) findViewById(R.id.forgotPasswordText);
        forgetPassWorldText .getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        checkButton=(ImageButton)findViewById(R.id.checkImageButton);
        checkButton.setImageResource(R.drawable.uncheck);
        backIamge= (ImageView) findViewById(R.id.back_image);
        mLoginText= (TextView) findViewById(R.id.sign_up_text);
        mLoginButton= (Button) findViewById(R.id.loginButton);
        mEmail= (EditText) findViewById(R.id.emailLoginField);
        mPassword= (EditText) findViewById(R.id.passwordField);
    }



    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("请输入"+message+"信息")
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
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
