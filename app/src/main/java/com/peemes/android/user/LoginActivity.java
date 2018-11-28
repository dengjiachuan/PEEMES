package com.peemes.android.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.peemes.android.MainActivity;
import com.peemes.android.R;
import com.peemes.android.ZheNengCoefficient.ZheNengCoefficientActivity;
import com.peemes.android.indexStandard.IndexStandardActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by cshao on 2018/11/17.
 * 用户登录界面
 */

public class LoginActivity extends AppCompatActivity {
    private EditText userID;
    private EditText userName;
    private EditText userPassword;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPassword;
    private boolean backState = false;//用来记住是否登录成功，接收来自服务器返回的值

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userID = (EditText)findViewById(R.id.userID);
        userName = (EditText)findViewById(R.id.account);
        userPassword = (EditText)findViewById(R.id.password);
        Button buttonLogin = (Button) findViewById(R.id.login_buttton);
        Button buttonRegister = (Button) findViewById(R.id.register_button);
        rememberPassword = (CheckBox)findViewById(R.id.rememeber_password);
        boolean isRemember = sharedPreferences.getBoolean("remember_password",false);
        //判断是否需要记住密码
        if (isRemember) {
            String account = sharedPreferences.getString("account","");
            String password = sharedPreferences.getString("password","");
            String id = sharedPreferences.getString("id","");
            userID.setText(id);
            userName.setText(account);
            userPassword.setText(password);
            rememberPassword.setChecked(true);
        }
        //注册登录按钮事件
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = userID.getText().toString();
                String account = userName.getText().toString();
                String password = userPassword.getText().toString();
                //将用户名，用户号和用户密码传入服务端的数据库中，判断在数据库中是否已经存在，返回查询结果
                User user = new User();
                OkHttpClient client = new OkHttpClient();
                user.setUserID(id);
                user.setUserName(account);
                user.setPassWord(password);
                //使用GSON将对象转化为json格式的数据
                Gson gson = new Gson();
                String json = gson.toJson(user);
                RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset = utf-8"),json);
                Request request = new Request.Builder()
                        .url("http://10.6.76.128:8080/PEEMES/LoginActivity")
                        .post(requestBody)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("registerActivity",call.toString());
                        Log.d("registerActivity","往服务器传送数据失败,所以登录失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        Looper.prepare();
                        Log.d("LoginActivity",res);
                        if (res.equals("{\"success\":\"success\"}")) {
                            backState = true;
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        } else if (res.equals("{\"success\":\"failed\"}")) {
                            Toast.makeText(LoginActivity.this,"用户不存在",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                        }
                        Looper.loop();
                    }
                });
                if (backState) {
                    editor = sharedPreferences.edit();
                    if (rememberPassword.isChecked()) {
                        editor.putBoolean("remember_password",true);
                        editor.putString("id",id);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    //把用户号传给折能参数的活动页，然后根据用户号来判断想用的等级
                    //在启动活动的同时把用户号传给MAinActivity
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("userid",id);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"用户名和密码无效，请重新输入",Toast.LENGTH_SHORT);
                }
            }
        });
        //注册注册按钮事件
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
