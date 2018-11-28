package com.peemes.android.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.peemes.android.R;

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
 * Created by cshao on 2018/11/20.
 */

public class ModifyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        //对控件进行注册
        Button buttonBack = (Button)findViewById(R.id.modify_back);
        TextView textViewTitle = (TextView)findViewById(R.id.modify_title);
        final EditText editTextName = (EditText)findViewById(R.id.modify_name);
        final EditText editTextID = (EditText)findViewById(R.id.modify_userID);
        final EditText editTextPassword = (EditText)findViewById(R.id.modify_new_password);
        final EditText editTextNewPass = (EditText)findViewById(R.id.modify_affirm_newPassword);
        Button buttonAffirm = (Button)findViewById(R.id.modify_affirm_button);
        //对原来的标题进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //对标题进行编写
        textViewTitle.setText("修改密码");
        //返回按钮进行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyActivity.this,ManagerUserActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //注册确认删除的事件
        buttonAffirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextNewPass.getText().toString().equals(editTextPassword.getText().toString())) {
                    User user = new User();
                    OkHttpClient client = new OkHttpClient();
                    String usetid = editTextID.getText().toString();
                    String userName = editTextName.getText().toString();
                    String userPassword = editTextPassword.getText().toString();
                    user.setUserID(usetid);
                    user.setUserName(userName);
                    user.setPassWord(userPassword);
                    //使用GSON将将对象封装成json字符串
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    RequestBody requestBody = FormBody.create(
                            MediaType.parse("application/json; charset = utf-8"),json);
                    Request request = new Request.Builder()
                            .url("http://10.6.76.128:8080/PEEMES/ModifyUserServlet")
                            .post(requestBody)
                            .build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("ModifyActivity",call.toString());
                            Log.d("ModifyActivity","往服务器传送数据失败");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Looper.prepare();
                            final String res = response.body().string();
                            Log.d("registerActivity",res);
                            if (res.equals("{\"success\":\"success\"}")) {
                                Toast.makeText(ModifyActivity.this,"修改密码成功",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(ModifyActivity.this,"修改密码失败",Toast.LENGTH_SHORT).show();
                            }
                            Looper.loop();
                        }
                    });
                }
            }
        });

    }
}
