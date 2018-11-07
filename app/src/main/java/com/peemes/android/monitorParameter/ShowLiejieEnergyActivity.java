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
//用于显示裂解区能量流的相关参数

public class ShowLiejieEnergyActivity extends AppCompatActivity {
    private List<ParameterMontor> parameterMontorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        //注册标题栏的相关控件
        Button backButton = (Button)findViewById(R.id.button_title_back);
        TextView textViewTitle = (TextView)findViewById(R.id.textView_title_text);
        textViewTitle.setText("裂解区能量流");
        //初始化参数数据，读取的是固定值，如何模拟值在一定范围内变化。
        initParameter();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ParameterMontorAdapter adapter = new ParameterMontorAdapter(ShowLiejieEnergyActivity.this,
                R.layout.item,parameterMontorList);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        listView.setAdapter(adapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowLiejieEnergyActivity.this,LiejieIndexActivity.class);
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
        ParameterMontor parameterMontor = new ParameterMontor("物质名称","位号","实时值","单位","时间");
        parameterMontorList.add(parameterMontor);
        ParameterMontor parameterMontor1 = new ParameterMontor("BFW(锅炉给水)进口温度","TI_111009","115.226","C",time);
        parameterMontorList.add(parameterMontor1);
        ParameterMontor parameterMontor2 = new ParameterMontor("BFW（锅炉给水）进口温度","TI_111030","295.25","C",time);
        parameterMontorList.add(parameterMontor2);
        ParameterMontor parameterMontor3 = new ParameterMontor("燃料气AQ","FI_19002","/","t/h",time);
        parameterMontorList.add(parameterMontor3);
        ParameterMontor parameterMontor4 = new ParameterMontor("燃料气（LPG）AM","FIQ_19007","19.875","t/h",time);
        parameterMontorList.add(parameterMontor4);
        ParameterMontor parameterMontor5 = new ParameterMontor("天然气（NG）AN","FI_19006","9.4743","t/h",time);
        parameterMontorList.add(parameterMontor5);
        ParameterMontor parameterMontor6 = new ParameterMontor("燃料气（FG）AO","FIQ_19001","/","t/h",time);
        parameterMontorList.add(parameterMontor6);
        ParameterMontor parameterMontor7 = new ParameterMontor("1.0Mpa蒸汽AS","FIQ_19103","50.0622","t/h",time);
        parameterMontorList.add(parameterMontor7);
        ParameterMontor parameterMontor8 = new ParameterMontor("0.4Mpa蒸汽AT","FIC_19105","20.8249","t/h",time);
        parameterMontorList.add(parameterMontor8);
        ParameterMontor parameterMontor9 = new ParameterMontor("1.0Mpa蒸汽AH","FIQ_19120","/","t/h",time);
        parameterMontorList.add(parameterMontor9);
        ParameterMontor parameterMontor10 = new ParameterMontor("3.5Mpa蒸汽AG","FI_19102","124.457","t/h",time);
        parameterMontorList.add(parameterMontor10);
        ParameterMontor parameterMontor11 = new ParameterMontor("0.4Mpa蒸汽AL","FIQ_19122","/","t/h",time);
        parameterMontorList.add(parameterMontor11);
        ParameterMontor parameterMontor12 = new ParameterMontor("烟气排烟温度","TI_111034","154.8","C",time);
        parameterMontorList.add(parameterMontor12);
        ParameterMontor parameterMontor13 = new ParameterMontor("BFW（锅炉给水）流量","DG_LJQ_001","55077.8","t/h",time);
        parameterMontorList.add(parameterMontor13);
        ParameterMontor parameterMontor14 = new ParameterMontor("稀释蒸汽量","DG_LJQ_002","2526.18","kg/h",time);
        parameterMontorList.add(parameterMontor14);
    }
}
