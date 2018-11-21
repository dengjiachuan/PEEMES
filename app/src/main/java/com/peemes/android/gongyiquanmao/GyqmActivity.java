package com.peemes.android.gongyiquanmao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.peemes.android.MainActivity;
import com.peemes.android.R;

/**
 * Created by cshao on 2018/11/20.
 */

public class GyqmActivity extends AppCompatActivity {
    private Button buttonBack;
    private TextView textViewTitle;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        //对控件进行注册获取相应的实例
        buttonBack = (Button)findViewById(R.id.picture_button_back);
        textViewTitle = (TextView)findViewById(R.id.picture_title);
        imageView = (ImageView)findViewById(R.id.picture_imagView);
        //对原来的题目进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //对题目进行设置
        textViewTitle.setText("乙烯生产全过程");
        //对全过程的图片进行显示
        imageView.setImageResource(R.mipmap.yixi);
        //对返回按钮进行事件的注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GyqmActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
