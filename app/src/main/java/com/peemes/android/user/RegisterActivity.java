package com.peemes.android.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by cshao on 2018/11/17.
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
        //对所有的控件进行注册，以获得相应的实例
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
        //获取当前系统的，把这个事件放在服务器上写
        //注册登录按钮的点击事件，点击的情况下进行登录
        register_affirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (register_password_text.getText().toString().equals(affirm_register_password_text.getText().toString())
                        && register_user_id.getText().toString().equals(affire_register_user_id.getText().toString())
                        && register_partment_id.getText().toString().equals(affire_register_partment_id.getText().toString())
                        && register_prvi_grade.getText().toString().equals(affire_register_prvi_grade.getText().toString())) {
                    User user = new User();
                    OkHttpClient client = new OkHttpClient();
                    final String userID = register_user_id.getText().toString();
                    final String userName = register_account_text.getText().toString();
                    final String userWord = register_password_text.getText().toString();
                    final String partmentID = register_partment_id.getText().toString();
                    final String privGrade = register_prvi_grade.getText().toString();
                    user.setUserID(userID);
                    user.setUserName(userName);
                    user.setPassWord(userWord);
                    user.setPartmentID(partmentID);
                    user.setPrivGrade(privGrade);
                    //使用Gson将对象装换为json字符串
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    RequestBody requestBody = FormBody.create(
                            MediaType.parse("application/json; charset = utf-8"),json);
                    Request request = new Request.Builder()
                            .url("http://10.6.62.14:8080/PEEMES/RegistreServlet")
                            .post(requestBody)
                            .build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("registerActivity",call.toString());
                            Log.d("registerActivity","往服务器传送数据失败");
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Looper.prepare();
                            final String res = response.body().string();
                            Log.d("registerActivity",res);
                            if (res.equals("{\"success\":\"success\"}")) {
                                Toast.makeText(RegisterActivity.this,"注册成功,请返回登录界面进行登录",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(RegisterActivity.this,"注册失败。请重新注册",Toast.LENGTH_SHORT).show();
                            }
                            Looper.loop();
                        }
                    });

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
        register_cancel_button.setOnClickListener(new View.OnClickListener() {
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
