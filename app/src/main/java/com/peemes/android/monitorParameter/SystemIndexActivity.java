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

public class SystemIndexActivity extends AppCompatActivity {
    private String [] data0 = {"乙烯装置能量流","乙烯装置物质流"};
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
        int num = intent.getIntExtra("number",0);
        String temp = intent.getStringExtra("title");
        //为ListView制定适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SystemIndexActivity.this,
                android.R.layout.simple_list_item_1,data0);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);

        showTitle("系统级一次指标");
        listView.setAdapter(adapter);
        //ListView的点击效应
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SystemIndexActivity.this,data0[position],Toast.LENGTH_SHORT).show();
                Log.d("第二页","项数"+position);
                switch (position){
                    case 0:
                        Intent intent1 = new Intent(SystemIndexActivity.this,
                                ShowYixiEnergyParameterActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case 1:
                        Intent intent2 = new Intent(SystemIndexActivity.this,
                                ShowYixiMatterParameterActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });
        //为返回按钮注册点击事件
        button_title_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SystemIndexActivity.this, ParaweterMonitorActivity.class);
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
