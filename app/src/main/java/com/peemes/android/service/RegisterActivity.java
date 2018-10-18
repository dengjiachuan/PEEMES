package com.peemes.android.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.peemes.android.R;
import com.peemes.android.db.User;

/**
 * Created by cshao on 2018/10/15.
 */

public class RegisterActivity extends AppCompatActivity {
    private EditText register_account_text;
    private EditText register_password_text;
    private EditText affirm_register_password_text;
    private EditText register_user_id;
    private EditText affire_register_user_id;
    private EditText register_partment_id;
    private EditText affire_register_partment_id;
    private EditText register_prvi_grade;
    private EditText affire_register_prvi_grade;
    private Button register_affirm_button;
    private Button register_cancel_button;
    private Button back_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_account_text = (EditText)findViewById(R.id.register_account_text);
        register_password_text = (EditText)findViewById(R.id.register_password_text);
        affirm_register_password_text = (EditText)findViewById(R.id.affirm_register_password_text);
        register_user_id = (EditText)findViewById(R.id.register_user_id);
        affire_register_user_id = (EditText)findViewById(R.id.affirm_register_user_id);
        register_partment_id = (EditText)findViewById(R.id.register_partment_id);
        affire_register_partment_id = (EditText)findViewById(R.id.affrim_register_partment_id);
        register_prvi_grade = (EditText)findViewById(R.id.register_prvi_grade);
        affire_register_prvi_grade = (EditText)findViewById(R.id.affirm_register_prvi_grade);
        register_affirm_button = (Button)findViewById(R.id.register_affirm_button);
        register_cancel_button = (Button)findViewById(R.id.register_cancel_button);
        back_login = (Button)findViewById(R.id.back_login);
        register_affirm_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (register_password_text.getText().toString().equals(affirm_register_password_text.getText().toString())
                        && register_user_id.getText().toString().equals(affire_register_user_id.getText().toString())
                        && register_partment_id.getText().toString().equals(affire_register_partment_id.getText().toString())
                        && register_prvi_grade.getText().toString().equals(affire_register_prvi_grade.getText().toString())) {
                    User user = new User();
                    user.setUserID(register_user_id.getText().toString());
                    user.setUserName(register_account_text.getText().toString());
                    user.setPassWord(register_password_text.getText().toString());
                    user.setPartmentID(register_partment_id.getText().toString());
                    user.setPrivGrade(register_prvi_grade.getText().toString());
                    //SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//API版本问题，能运行出正常结果。
                    //String date = sDateFormat.format(new java.util.Date());
                    user.setLastLogin(null);
                    boolean result = user.save();
                    if (result) {
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this,"注册失败，可能是没有添加上次登录时间失败",Toast.LENGTH_SHORT).show();
                    }
                }
                if (!(register_password_text.getText().toString()
                        .equals(affirm_register_password_text.getText().toString()))) {
                    Toast.makeText(RegisterActivity.this,"两次输入的密码不一样,请重新注册",Toast.LENGTH_SHORT).show();
                    register_password_text.setText(null);
                    affirm_register_password_text.setText(null);
                }
                if (!(register_user_id.getText().toString().equals(affire_register_user_id.getText().toString()))) {
                    Toast.makeText(RegisterActivity.this,"两次输入的用户号码不一样，请重新输入",Toast.LENGTH_SHORT).show();
                    register_user_id.setText(null);
                    affire_register_user_id.setText(null);
                }
                if (!(register_partment_id.getText().toString().equals(affire_register_partment_id.getText().toString()))) {
                    Toast.makeText(RegisterActivity.this,"两次输入的部门号不一样，请重新输入",Toast.LENGTH_SHORT).show();
                    register_partment_id.setText(null);
                    affire_register_partment_id.setText(null);
                }
                if (!(register_prvi_grade.getText().toString().equals(affire_register_prvi_grade.getText().toString()))) {
                    Toast.makeText(RegisterActivity.this,"两次输入的优先等级不一样，请重新输入",Toast.LENGTH_SHORT).show();
                    register_prvi_grade.setText(null);
                    affire_register_prvi_grade.setText(null);
                }
            }
        });
        register_cancel_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                register_account_text.setText(null);
                register_prvi_grade.setText(null);
                affire_register_prvi_grade.setText(null);
                register_partment_id.setText(null);
                affire_register_partment_id.setText(null);
                register_user_id.setText(null);
                affire_register_user_id.setText(null);
                register_password_text.setText(null);
                affirm_register_password_text.setText(null);
            }
        });
        back_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
