package com.peemes.android.liuchengjiankong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.peemes.android.R;

/**
 * Created by cshao on 2018/11/20.
 */

public class LcjkDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        //对控件进行注册，以获取相应的实例
        Button buttonBack = (Button)findViewById(R.id.picture_button_back);
        TextView textViewTitle = (TextView)findViewById(R.id.picture_title);
        ImageView imageView = (ImageView)findViewById(R.id.picture_imagView);
        //对原来的标题进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //接收从上一个果冻中接收的数据
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int pictureID = intent.getIntExtra("pictureID",0);
        //对题目和相应的图片进行展示
        textViewTitle.setText(title);
        imageView.setImageResource(pictureID);
        //对返回按钮进行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LcjkDetailActivity.this,LcjkActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
