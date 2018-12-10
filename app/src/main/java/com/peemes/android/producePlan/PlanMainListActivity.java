package com.peemes.android.producePlan;

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
 * Created by cshao on 2018/12/9.
 */

public class PlanMainListActivity extends AppCompatActivity {
    private String data[] = {"计划制定","完成情况"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        //对控件进行注册
        Button buttonBack = (Button)findViewById(R.id.button_title_back);
        TextView textViewTitle = (TextView)findViewById(R.id.textView_title_text);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        //对原来的辩题进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //设置标题
        textViewTitle.setText("生产计划");
        //配置是配置
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlanMainListActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        //注册点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(PlanMainListActivity.this,PlanListActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        Intent intent1 = new Intent(PlanMainListActivity.this,PlanListActivityBar.class);
                        startActivity(intent1);
                        finish();
                }
            }
        });
        //注册返回按钮事件
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanMainListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
