package com.peemes.android.energyAssess;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.peemes.android.R;

/**
 * Created by cshao on 2018/11/19.
 */

public class EASystemDetailActivity extends AppCompatActivity {
    private Button buttonBack;
    private TextView textViewTitle;
    private TextView textViewWord;
    private TextView textViewMath;
    private ImageView imageViewPicture;
    private ScrollView scrollViewDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ea_detail);
        //对所有的控件进行注册来获取相应的实例
        buttonBack = (Button) findViewById(R.id.ea_detail_back);
        textViewTitle = (TextView) findViewById(R.id.ea_detail_title);
        textViewWord = (TextView) findViewById(R.id.ea_detail_word);
        textViewMath = (TextView) findViewById(R.id.ea_detail_math);
        imageViewPicture = (ImageView) findViewById(R.id.ea_detail_picture);
        scrollViewDetail = (ScrollView) findViewById(R.id.scrollView_ea_detail);
        //对原来的题目进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //从上一个活动中接收数据，主要包括题目，图片ID、文字说明内容和数学表达式的内容。
        final Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int pictureID = intent.getIntExtra("pictureID", 0);
        String word = intent.getStringExtra("word");
        String math = intent.getStringExtra("math");
        //初始化题目
        textViewTitle.setText(title);
        //初始化图表显示
        imageViewPicture.setImageResource(pictureID);
        //初始化文字说明
        textViewWord.setText(word);
        //初始化数学表达式说明
        textViewMath.setText(math);
        //对返回按钮进行实践注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EASystemDetailActivity.this, EAFiveSystemActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}