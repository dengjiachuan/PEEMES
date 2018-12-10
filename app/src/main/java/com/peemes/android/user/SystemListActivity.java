package com.peemes.android.user;

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
import android.widget.Toast;

import com.peemes.android.MainActivity;
import com.peemes.android.R;

/**
 * Created by cshao on 2018/12/8.
 */

public class SystemListActivity extends AppCompatActivity {
    private String data [] = {"用户信息管理","操作日志"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        //对控件进行注册
        Button buttonBack = (Button)findViewById(R.id.button_title_back);
        TextView textViewTitle = (TextView)findViewById(R.id.textView_title_text);
        //对原来的标题进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //对标题进行注册
        textViewTitle.setText("系统管理");
        //对ListView进行配置适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (SystemListActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        listView.setAdapter(adapter);
        //对返回安妮进行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SystemListActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //对ListView的子项点击事件进行注册
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(SystemListActivity.this,ManagerUserActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        Intent intent1 = new Intent(SystemListActivity.this,OperatorActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    default:
                        Toast.makeText(SystemListActivity.this,"系统管理列表出错",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }
}
