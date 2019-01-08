package com.peemes.android.producePlan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.peemes.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cshao on 2018/12/9.
 */

public class PlanShowActivity extends AppCompatActivity {
    private List<PlanTable> planTableList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        //对布局中的控件进行注册
        Button buttonBack = (Button)findViewById(R.id.plan_activity_back);
        TextView textViewTitle = (TextView)findViewById(R.id.plan_activity_title);
        ListView listView = (ListView)findViewById(R.id.plan_activity_listview);
        //对原来的标题进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //对题目进行注册
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        textViewTitle.setText(title);
        //初始化数据
        initPlanTable();
        //对ListView适配器进行配置
        PlanAdapter adapter = new PlanAdapter(PlanShowActivity.this,R.layout.plan_item,planTableList);
        listView.setAdapter(adapter);
        //对返回按钮进行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanShowActivity.this, PlanListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void initPlanTable(){
        PlanTable planTable1 = new PlanTable("加工量","吨","/","2.345");
        planTableList.add(planTable1);
        PlanTable planTable2 = new PlanTable("大庆油","/","/","20369");
        planTableList.add(planTable2);
        PlanTable planTable3 = new PlanTable("其中俄罗斯原油","/","/","8.934");
        planTableList.add(planTable3);
        PlanTable planTable4 = new PlanTable("生产天数","天","/","4.036");
        planTableList.add(planTable4);
        PlanTable planTable5 = new PlanTable("日均加工量","吨/日","/","9.578");
        planTableList.add(planTable5);
        PlanTable planTable6 = new PlanTable("轻收","吨","/","1.235");
        planTableList.add(planTable6);
        PlanTable planTable7 = new PlanTable("产品","吨","/","11.23");
        planTableList.add(planTable7);
        PlanTable planTable8 = new PlanTable("气体","吨","/","8.916");
        planTableList.add(planTable8);
        PlanTable planTable9 = new PlanTable("蒸顶","吨","/","5.68");
        planTableList.add(planTable9);
        PlanTable planTable10 = new PlanTable("常顶","吨","/","3.694");
        planTableList.add(planTable10);
        PlanTable planTable11 = new PlanTable("煤油","吨","/","2.345");
        planTableList.add(planTable11);
        PlanTable planTable12 = new PlanTable("柴油","吨","/","2.345");
        planTableList.add(planTable12);
        PlanTable planTable13 = new PlanTable("常三","吨","/","2.345");
        planTableList.add(planTable13);
        PlanTable planTable14 = new PlanTable("减一","吨","/","2.345");
        planTableList.add(planTable14);
        PlanTable planTable15 = new PlanTable("减二","吨","/","2.345");
        planTableList.add(planTable15);
        PlanTable planTable16 = new PlanTable("减三","吨","/","2.345");
        planTableList.add(planTable16);
        PlanTable planTable17 = new PlanTable("减四","吨","/","2.345");
        planTableList.add(planTable17);
        PlanTable planTable18 = new PlanTable("减五","吨","/","2.345");
        planTableList.add(planTable18);
        PlanTable planTable19 = new PlanTable("减六","吨","/","2.345");
        planTableList.add(planTable19);
        PlanTable planTable111 = new PlanTable("减渣","吨","/","2.345");
        planTableList.add(planTable111);
        PlanTable planTable112 = new PlanTable("损失","吨","/","2.345");
        planTableList.add(planTable112);
        PlanTable planTable113 = new PlanTable("合计","吨","/","2.345");
        planTableList.add(planTable113);
    }
}
