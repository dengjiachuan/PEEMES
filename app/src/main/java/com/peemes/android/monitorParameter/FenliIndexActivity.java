package com.peemes.android.monitorParameter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.peemes.android.R;

/**
 * Created by cshao on 2018/10/23.
 */

public class FenliIndexActivity extends AppCompatActivity {
    private String [] data = {"分离区能量流","分离区物质流"};
    private TextView textView_title;
    private Button button_title_back;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        button_title_back = (Button)findViewById(R.id.button_title_back);
        textView_title = (TextView)findViewById(R.id.textView_title_text);
        //将系统自带的标题栏隐藏掉
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //从上一个页面中拿出数据
        Intent intent = getIntent();
        String temp = intent.getStringExtra("title");
        //为ListView制定适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FenliIndexActivity.this,
                android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);

        showTitle(temp);
        listView.setAdapter(adapter);
        //ListView的点击效应
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FenliIndexActivity.this,data[position],Toast.LENGTH_SHORT).show();
                Log.d("第二页","项数"+position);
            }
        });
        //为返回按钮注册点击事件
        button_title_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FenliIndexActivity.this, ParaweterMonitorActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showTitle(final String title){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView_title.setText(title);
            }
        });
    }
}
