package com.peemes.android.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peemes.android.MainActivity;
import com.peemes.android.R;
import com.peemes.android.util.*;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by cshao on 2018/11/27.
 */

public class BingActivity extends AppCompatActivity {
    private ImageView imageView;
    private SharedPreferences preferences ;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing_picture);
        //对控件进行注册
        Button buttonBack = (Button)findViewById(R.id.bing_back);
        TextView textViewTitle = (TextView)findViewById(R.id.bing_title);
        imageView = (ImageView)findViewById(R.id.bing_imageView);
        //对原来的标题进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //对标题进行设置
        textViewTitle.setText("必应每日一图");
        //对返回按钮进行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //把图片数据线缓存在SharedPerference中；
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String bingPic = preferences.getString("bing_pic",null);
        if (bingPic != null) {
            Glide.with(BingActivity.this).load(bingPic).into(imageView);
        }else{
            loadBingPicture();
        }
    }
    private void loadBingPicture(){
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        com.peemes.android.util.HttpUtil.sendOkHttpRequst(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingpic = response.body().string();
                editor = PreferenceManager.getDefaultSharedPreferences(BingActivity.this).edit();
                editor.putString("bing_pic",bingpic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(BingActivity.this).load(bingpic).into(imageView);
                    }
                });
            }
        });
    }
}
