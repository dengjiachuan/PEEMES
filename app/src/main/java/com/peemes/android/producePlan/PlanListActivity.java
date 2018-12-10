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
import com.peemes.android.demo.BingActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cshao on 2018/12/9.
 */

public class PlanListActivity extends AppCompatActivity {
    private String data[] = {"2018年1月","2018年2月","2018年3月","2018年4月","2018年5月","2018年6月",
            "2018年7月","2018年8月","2018年9月","2018年10月","2018年11月","2018年12月"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        //对布局中的控件进行注册
        Button buttonBack = (Button)findViewById(R.id.button_title_back);
        TextView textViewTitle = (TextView)findViewById(R.id.textView_title_text);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        //对原来的标题进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //对题目进行注册
        textViewTitle.setText("选择查看的月份");
        //对ListView适配器进行配置
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlanListActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        //注册点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp = data[position];
                        intent.putExtra("title",temp);
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        Intent intent1 = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp1 = data[position];
                        intent1.putExtra("title",temp1);
                        startActivity(intent1);
                        finish();
                        break;
                    case 2:
                        Intent intent2 = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp2 = data[position];
                        intent2.putExtra("title",temp2);
                        startActivity(intent2);
                        finish();
                        break;
                    case 33:
                        Intent intent3 = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp3 = data[position];
                        intent3.putExtra("title",temp3);
                        startActivity(intent3);
                        finish();
                        break;
                    case 4:
                        Intent intent4 = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp4 = data[position];
                        intent4.putExtra("title",temp4);
                        startActivity(intent4);
                        finish();
                        break;
                    case 5:
                        Intent intent5 = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp5 = data[position];
                        intent5.putExtra("title",temp5);
                        startActivity(intent5);
                        finish();
                        break;
                    case 6:
                        Intent intent6 = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp6 = data[position];
                        intent6.putExtra("title",temp6);
                        startActivity(intent6);
                        finish();
                        break;
                    case 7:
                        Intent intent7 = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp7 = data[position];
                        intent7.putExtra("title",temp7);
                        startActivity(intent7);
                        finish();
                        break;
                    case 8:
                        Intent intent8 = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp8 = data[position];
                        intent8.putExtra("title",temp8);
                        startActivity(intent8);
                        finish();
                        break;
                    case 9:
                        Intent intent9 = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp9 = data[position];
                        intent9.putExtra("title",temp9);
                        startActivity(intent9);
                        finish();
                        break;
                    case 10:
                        Intent intent10 = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp10 = data[position];
                        intent10.putExtra("title",temp10);
                        startActivity(intent10);
                        finish();
                        break;
                    case 11:
                        Intent intent11 = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp11 = data[position];
                        intent11.putExtra("title",temp11);
                        startActivity(intent11);
                        finish();
                        break;
                    case 12:
                        Intent intent12 = new Intent(PlanListActivity.this,PlanShowActivity.class);
                        String temp12 = data[position];
                        intent12.putExtra("title",temp12);
                        startActivity(intent12);
                        finish();
                        break;
                }
            }
        });

        //对返回按钮进行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanListActivity.this, PlanMainListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
