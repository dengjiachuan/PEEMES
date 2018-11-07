package com.peemes.android.monitorParameter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.peemes.android.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cshao on 2018/11/6.
 */
//用于显示乙烯装置能量流的相关参数

public class ShowYixiEnergyParameterActivity extends AppCompatActivity {
    private List<ParameterMontor> parameterMontorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        //注册标题栏的相关控件
        Button backButton = (Button)findViewById(R.id.button_title_back);
        TextView textViewTitle = (TextView)findViewById(R.id.textView_title_text);
        textViewTitle.setText("乙烯装置能量流");
        //初始化参数数据，读取的是固定值，如何模拟值在一定范围内变化。
        initParameter();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ParameterMontorAdapter adapter = new ParameterMontorAdapter(ShowYixiEnergyParameterActivity.this,
                R.layout.item,parameterMontorList);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        listView.setAdapter(adapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowYixiEnergyParameterActivity.this,SystemIndexActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void initParameter(){
        Date date = new Date();
        String time1 = String.format("%tF",date);
        String time2 = String.format("%tT",date);
        String time = time1+" "+time2;
        ParameterMontor parameterMontor = new ParameterMontor("能源名称","位号","实时值","单位","时间");
        parameterMontorList.add(parameterMontor);
        ParameterMontor parameterMontor1 = new ParameterMontor("工业水AD","FIQ_19113","0.3516","t/h",time);
        parameterMontorList.add(parameterMontor1);
        ParameterMontor parameterMontor2 = new ParameterMontor("循环水AD","FI_19107","44456.5","t/h",time);
        parameterMontorList.add(parameterMontor2);
        ParameterMontor parameterMontor3 = new ParameterMontor("脱盐水AE","FI_19114","183.912","t/h",time);
        parameterMontorList.add(parameterMontor3);
        ParameterMontor parameterMontor4 = new ParameterMontor("生活水AF","FI_19115","/","t/h",time);
        parameterMontorList.add(parameterMontor4);
        ParameterMontor parameterMontor5 = new ParameterMontor("3.5Mpa蒸汽AG","FI_19102","124.457","t/h",time);
        parameterMontorList.add(parameterMontor5);
        ParameterMontor parameterMontor6 = new ParameterMontor("仪表风AJ","FI_19110","1660.24","Nm3/h",time);
        parameterMontorList.add(parameterMontor6);
        ParameterMontor parameterMontor7 = new ParameterMontor("工厂风AK","FI_19109","1608.46","Nm3/h",time);
        parameterMontorList.add(parameterMontor7);
        ParameterMontor parameterMontor8 = new ParameterMontor("氮气AL","FI_19108","3260.39","Nm3/h",time);
        parameterMontorList.add(parameterMontor8);
        ParameterMontor parameterMontor9 = new ParameterMontor("热水AP","FIQ_19116","/","t/h",time);
        parameterMontorList.add(parameterMontor9);
        ParameterMontor parameterMontor10 = new ParameterMontor("燃料气（LPG)AM","FIQ_19007","/","t/h",time);
        parameterMontorList.add(parameterMontor10);
        ParameterMontor parameterMontor11 = new ParameterMontor("天然气(NG)AN","FI_19006","9.4743","t/h",time);
        parameterMontorList.add(parameterMontor11);
        ParameterMontor parameterMontor12 = new ParameterMontor("甲烷(C1410)","FI_14030","29.4301","t/h",time);
        parameterMontorList.add(parameterMontor12);
        ParameterMontor parameterMontor13 = new ParameterMontor("甲烷M(V1421)","FI_14081","10.1366","t/h",time);
        parameterMontorList.add(parameterMontor13);
        ParameterMontor parameterMontor14 = new ParameterMontor("氢气(14065)AX","FIQ_14065","2.115","t/h",time);
        parameterMontorList.add(parameterMontor14);
        ParameterMontor parameterMontor23 = new ParameterMontor("氢气(含转燃料)","FI_14063","2393.81","kg/h",time);
        parameterMontorList.add(parameterMontor23);
        ParameterMontor parameterMontor15 = new ParameterMontor("甲烷（小乙烯）","FI_007","/","t/h",time);
        parameterMontorList.add(parameterMontor15);
        ParameterMontor parameterMontor16 = new ParameterMontor("燃料气AQ","FI_19002","/","kg/h",time);
        parameterMontorList.add(parameterMontor16);
        ParameterMontor parameterMontor17 = new ParameterMontor("污水AR","FI_19045","67.3091","t/h",time);
        parameterMontorList.add(parameterMontor17);
        ParameterMontor parameterMontor18 = new ParameterMontor("1.0Mpa蒸汽AS","FIQ_19103","48.6564","t/h",time);
        parameterMontorList.add(parameterMontor18);
        ParameterMontor parameterMontor19 = new ParameterMontor("0.4Mpa蒸汽AT","FIC_19105","25.4638","t/h",time);
        parameterMontorList.add(parameterMontor19);
        ParameterMontor parameterMontor20 = new ParameterMontor("蒸汽凝液AU","FI_19030","183.96","t/h",time);
        parameterMontorList.add(parameterMontor20);
        ParameterMontor parameterMontor21 = new ParameterMontor("除氧水AV","FIQ_19106","/","t/h",time);
        parameterMontorList.add(parameterMontor21);
        ParameterMontor parameterMontor22 = new ParameterMontor("燃料","DG_XT_001","48.1435","t/h",time);
        parameterMontorList.add(parameterMontor22);

    }
}
