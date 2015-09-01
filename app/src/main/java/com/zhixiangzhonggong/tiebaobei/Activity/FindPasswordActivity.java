package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.app.AlertDialog;
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

import com.zhixiangzhonggong.tiebaobei.CustomizedClass.HideEditorKeyboard;
import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.database.UserAccountInFormationDB;
import com.zhixiangzhonggong.tiebaobei.model.UserAccountInformationModel;

public class FindPasswordActivity extends Activity {
    private HideEditorKeyboard mHideEditor;
    private ImageView mBackImage;
    private EditText userEmail,userPassword,userConfirmPassword;
    private Button accomplishButton;
    private UserAccountInFormationDB userAccountInFormationDB;
    private UserAccountInformationModel userAccountInformationModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        mHideEditor=new HideEditorKeyboard(this);
        mHideEditor.setupUI(findViewById(R.id.find_password_activity));
        initView();
        userAccountInFormationDB=new UserAccountInFormationDB(this);

        mBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        accomplishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAccountInformationModel=new UserAccountInformationModel();
                String email=userEmail.getText().toString();
                String password=userPassword.getText().toString();
                String confirmPassword=userConfirmPassword.getText().toString();
                userAccountInformationModel.setEmail(email);
                userAccountInformationModel.setPassword(password);

                if (password.equals(confirmPassword)){
                  int rowID=userAccountInFormationDB.updateUserAccountPasswordByEmail(userAccountInformationModel);
                   ShowDialog("密码以更改，请重新登录");
                }else {
                    ShowDialog("密码不相同，请再次输入");
                    userConfirmPassword.setText("");
                }
            }
        });
    }


    private void ShowDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FindPasswordActivity.this);
        builder.setMessage(message)
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initView() {
        mBackImage= (ImageView) findViewById(R.id.find_password_back_image);
        userEmail= (EditText) findViewById(R.id.find_password_emailField);
        userPassword= (EditText) findViewById(R.id.find_password_passwordText);
        userConfirmPassword= (EditText) findViewById(R.id.find_password_confirmPwText);
        accomplishButton= (Button) findViewById(R.id.find_password_activity_finish_Button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_password, menu);
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
