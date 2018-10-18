package com.peemes.android.service;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.peemes.android.MainActivity;
import com.peemes.android.R;
import com.peemes.android.util.OpratorUser;

/**
 * Created by cshao on 2018/10/15.
 */

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountText;
    private EditText passwordText;
    private Button loginButton;
    private Button cancelButton;
    private Button registerButton;
    private CheckBox rememberPassword;
    private Button forgetPassword;
    private OpratorUser opratorUser = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountText = (EditText)findViewById(R.id.account);
        passwordText = (EditText)findViewById(R.id.password);
        rememberPassword = (CheckBox)findViewById(R.id.rememeber_password);
        loginButton = (Button)findViewById(R.id.login_buttton);
        cancelButton = (Button)findViewById(R.id.cancel_button);
        registerButton = (Button)findViewById(R.id.register_button);
        boolean isRemember = pref.getBoolean("remember_password",false);
        if (isRemember) {
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountText.setText(account);
            passwordText.setText(password);
            rememberPassword.setChecked(true);
        }
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = accountText.getText().toString();
                String password = passwordText.getText().toString();
                opratorUser = new OpratorUser();
                if (opratorUser.queryData(name,password)) {
                    editor = pref.edit();
                    if (rememberPassword.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",name);
                        editor.putString("password",password);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"用户或密码无效，请重新输入",Toast.LENGTH_SHORT).show();
                }

            }
        });
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String account = accountText.getText().toString();
                String password = passwordText.getText().toString();
                if (opratorUser.queryData(account,password)) {
                    opratorUser.deleteDataFromDatabase(account,password);
                }else{
                    Toast.makeText(LoginActivity.this,"该用户还未注册",Toast.LENGTH_SHORT).show();
                }
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
    }
}
