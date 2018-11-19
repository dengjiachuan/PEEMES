package com.peemes.android.energyAssess;

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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cshao on 2018/11/19.
 */

public class EAFiveFenliActivity extends AppCompatActivity {
    private List<EAFive> eaFiveList = new ArrayList<>();
    private Button buttonBack;
    private TextView textViewTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ea_list);
        //对按钮和文本框进行控件初始化
        buttonBack = (Button)findViewById(R.id.eaList_back);
        textViewTitle = (TextView)findViewById(R.id.eaList_title);
        //先对原来的题目进行覆盖
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //对标题进行设定，固定时可以直接在此处操作，若是动态更新时，需要重新开线程
        textViewTitle.setText("分离过程工艺流程分析");
        //初始化系统级相关的能效评估的指标
        initEAFiveList();
        EAFiveFenliAdapter adapter = new EAFiveFenliAdapter(EAFiveFenliActivity.this,R.layout.ea_item,eaFiveList);
        ListView listView = (ListView)findViewById(R.id.eaList_listView);
        listView.setAdapter(adapter);
        //对eaFiveList集合进行清除，用来装下一次的数据
        // eaFiveList.clear();
        //对返回按钮进行注册点击事件
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EAFiveFenliActivity.this,EnergyAssessActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //对系统级指标进行初始化
    private void initEAFiveList(){
        //先从服务端获取相应的数据
        GetEAParameter.initAssessActivityList();
        GetEAParameter.print();
        GetEAFiveMinute.initEaFiveMinutesList();
        GetEAFiveMinute.print();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = simpleDateFormat.format(timestamp);
        String name = null;
        String val = null;
        String uom = null;
        // String time = null;
        for(int i = 0; i< GetEAFiveMinute.eaFiveMinutesListParameter.size();i++){
            //得到GetEAFiveMinute的ID号
            int num2 = Integer.parseInt(GetEAFiveMinute.eaFiveMinutesListParameter.get(i).getId());
            if (num2>55 && num2 <62) {
                val = GetEAFiveMinute.eaFiveMinutesListParameter.get(i).getVal();
                // time = GetEAFiveMinute.eaFiveMinutesListParameter.get(i).getClock();
                for(int j = 0; j<GetEAParameter.assessActivityList.size();j++){
                    //得到GetEAParameter的ID号
                    int num1 = Integer.parseInt(GetEAParameter.assessActivityList.get(j).getId());
                    if (num1 == num2) {
                        name = GetEAParameter.assessActivityList.get(j).getMeaning();
                        uom = GetEAParameter.assessActivityList.get(j).getUom();
                        EAFive eaFive = new EAFive(name,time,val,uom);
                        eaFiveList.add(eaFive);
                    }
                }
            }
        }
    }
}
