package com.peemes.android.liuchengjiankong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.peemes.android.MainActivity;
import com.peemes.android.R;

/**
 * Created by cshao on 2018/11/20.
 */

public class LcjkActivity extends AppCompatActivity {
    private String []data = {"裂解过程","急冷过程","压缩过程","分离过程","设备监控-重质裂解炉",
            "设备监控-轻质裂解炉","设备监控-气体裂解炉"};
    //往下一个活动传送的图片的ID
    private int [] pictureID = {R.mipmap.liejie,R.mipmap.jileng,R.mipmap.yasuo,R.mipmap.fenli,R.mipmap.pyrosis1,
            R.mipmap.pyrosis6,R.mipmap.pyrosis8};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        //对控件尽心注册以获取实例
        Button buttonBack = (Button)findViewById(R.id.button_title_back);
        TextView title = (TextView)findViewById(R.id.textView_title_text);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        //对原来的题目进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //对题目进行设定
        title.setText("过程监控");
        //对ListView的适配器进行处理
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LcjkActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        //对子项的点击事件进行处理
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //往下一个活动传题目，传送具体图片的ID
                String title = data[position];
                int pictureid = pictureID[position];
                Intent intent = new Intent(LcjkActivity.this,LcjkDetailActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("pictureID",pictureid);
                startActivity(intent);
                finish();
            }
        });
        //对返回按钮进行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LcjkActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
