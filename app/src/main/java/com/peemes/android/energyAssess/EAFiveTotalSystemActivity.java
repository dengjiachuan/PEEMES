package com.peemes.android.energyAssess;

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

import com.peemes.android.R;

/**
 * Created by cshao on 2018/11/19.
 */

public class EAFiveTotalSystemActivity extends AppCompatActivity{
    private String [] data = {"裂解过程分析","急冷过程分析","压缩过程分析","分离过程分析"};
    private Button buttonBack;
    private TextView textViewTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        //对所有的控件进行注册
        buttonBack = (Button)findViewById(R.id.button_title_back);
        textViewTitle = (TextView)findViewById(R.id.textView_title_text);
        //对原来的标题进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //设置标题
        textViewTitle.setText("过程级分析");
        //编写列表
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EAFiveTotalSystemActivity.this,
                android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        listView.setAdapter(adapter);
        //对ListView的子项进行注册点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(EAFiveTotalSystemActivity.this,EAFiveLiejieActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        Intent intent1 = new Intent(EAFiveTotalSystemActivity.this,EAFiveJilengActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case 2:
                        Intent intent2 = new Intent(EAFiveTotalSystemActivity.this,EAFiveYasuoActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case 3:
                        Intent intent3 = new Intent(EAFiveTotalSystemActivity.this,EAFiveFenliActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                }
            }
        });
        //对返回按钮注册点击事件
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EAFiveTotalSystemActivity.this,EnergyAssessActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
