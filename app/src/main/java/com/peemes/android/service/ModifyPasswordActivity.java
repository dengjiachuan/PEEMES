package com.peemes.android.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.peemes.android.R;
import com.peemes.android.util.OpratorUser;

/**
 * Created by cshao on 2018/10/15.
 */

public class ModifyPasswordActivity extends AppCompatActivity {
    private EditText edit_name;
    private EditText edit_userID;
    private EditText edit_newPassword;
    private EditText edit_affirm_newPassword;
    private Button button_commit;
    private Button button_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_userID = (EditText)findViewById(R.id.edit_userID);
        edit_newPassword = (EditText)findViewById(R.id.edit_new_password);
        edit_affirm_newPassword = (EditText)findViewById(R.id.edit_affirm_newPassword);
        button_commit = (Button)findViewById(R.id.affirm_button);
        button_back = (Button)findViewById(R.id.button_back);
        button_commit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpratorUser opratorUser = new OpratorUser();
                String name = edit_name.getText().toString();
                String userID = edit_userID.getText().toString();
                String newPassword = edit_newPassword.getText().toString();
                String affirmNewPassword = edit_affirm_newPassword.getText().toString();
                if ((opratorUser.queryUserIDAndUserName(name,userID)) && (newPassword.equals(affirmNewPassword))){
                    boolean result = opratorUser.updataData(userID,"passWord",newPassword);
                    if (result) {
                        Toast.makeText(ModifyPasswordActivity.this,"修改密码成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ModifyPasswordActivity.this,"修改密码失败",Toast.LENGTH_SHORT).show();
                        Log.d("ModifyPasswordActivity","新旧密码相同，用户名也在数据库中，更新数据库出错");
                    }
                }
                if (opratorUser.queryUserIDAndUserName(name,userID)) {
                    Toast.makeText(ModifyPasswordActivity.this,"用户名和用户号不一致，请重新输入",Toast.LENGTH_SHORT).show();
                    edit_name.setText(null);
                    edit_userID.setText(null);
                }
                if (newPassword.equals(affirmNewPassword)) {
                    Toast.makeText(ModifyPasswordActivity.this,"两次输入的密码币一样，请重新输入",Toast.LENGTH_SHORT).show();
                    edit_newPassword.setText(null);
                    edit_affirm_newPassword.setText(null);
                }
            }
        });
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
