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
//用于显示急冷区能量流的相关参数

public class ShowJilengEnergyActivity extends AppCompatActivity {
    private List<ParameterMontor> parameterMontorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_detal);
        //注册标题栏的相关控件
        Button backButton = (Button)findViewById(R.id.button_title_back);
        TextView textViewTitle = (TextView)findViewById(R.id.textView_title_text);

        //初始化参数数据，读取的是固定值，如何模拟值在一定范围内变化。
        initParameter();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ParameterMontorAdapter adapter = new ParameterMontorAdapter(ShowJilengEnergyActivity.this,
                R.layout.item,parameterMontorList);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        listView.setAdapter(adapter);
        textViewTitle.setText("急冷区能量流");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowJilengEnergyActivity.this,JilengIndexActivity.class);
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
        //ParameterMontor parameterMontor = new ParameterMontor("能源名称","位号","实时值","单位","时间");
        //parameterMontorList.add(parameterMontor);
        ParameterMontor parameterMontor1 = new ParameterMontor("急冷油泵P-1210A消耗HS","FI_12042","/","Nm3/h",time);
        parameterMontorList.add(parameterMontor1);
        ParameterMontor parameterMontor2 = new ParameterMontor("塔盘油泵P-1211A消耗HS","FI_12043","/","Nm3/h",time);
        parameterMontorList.add(parameterMontor2);
        ParameterMontor parameterMontor3 = new ParameterMontor("重燃料油汽提塔蒸汽减黏消耗HS","FIC_12016","194.788","t/h",time);
        parameterMontorList.add(parameterMontor3);
        ParameterMontor parameterMontor4 = new ParameterMontor("轻燃料油汽提塔消耗DS","FIC_12012","1577.27","t/h",time);
        parameterMontorList.add(parameterMontor4);
        ParameterMontor parameterMontor5 = new ParameterMontor("水油分离泵P1220消耗HS","FI_12044","/","t/h",time);
        parameterMontorList.add(parameterMontor5);
        ParameterMontor parameterMontor6 = new ParameterMontor("塔底泵p-1260消耗MS","FI_12045","/","t/h",time);
        parameterMontorList.add(parameterMontor6);
        ParameterMontor parameterMontor7 = new ParameterMontor("裂解气温度","TIC_111006A","278.613","°C",time);
        parameterMontorList.add(parameterMontor7);
        ParameterMontor parameterMontor8 = new ParameterMontor("重燃料油汽提塔塔顶温度","TI_12018","213.871","°C",time);
        parameterMontorList.add(parameterMontor8);
        ParameterMontor parameterMontor9 = new ParameterMontor("进入C1206的工艺水","FIC_12032","283.529","t/h",time);
        parameterMontorList.add(parameterMontor9);
        ParameterMontor parameterMontor10 = new ParameterMontor("稀释蒸汽发生系统MS消耗量","DG_JLO_001","106.162","t/h",time);
        parameterMontorList.add(parameterMontor10);
        ParameterMontor parameterMontor11 = new ParameterMontor("DS（送至该系统外）","DG_JLO_004","3809.35","t/h",time);
        parameterMontorList.add(parameterMontor11);
        ParameterMontor parameterMontor12 = new ParameterMontor("F1120消耗DS","DG_JLO_005","7286.34","t/h",time);
        parameterMontorList.add(parameterMontor12);
        ParameterMontor parameterMontor13 = new ParameterMontor("F1130消耗DS","DG_JLO_006","11736","t/h",time);
        parameterMontorList.add(parameterMontor13);
        ParameterMontor parameterMontor14 = new ParameterMontor("F1140消耗DS","DG_JLO_007","907.587","t/h",time);
        parameterMontorList.add(parameterMontor14);
        ParameterMontor parameterMontor23 = new ParameterMontor("F1150消耗DS","DG_JLO_008","36579.2","t/h",time);
        parameterMontorList.add(parameterMontor23);
        ParameterMontor parameterMontor15 = new ParameterMontor("F1160消耗DS","DG_JLO_009","24120.3","t/h",time);
        parameterMontorList.add(parameterMontor15);
        ParameterMontor parameterMontor16 = new ParameterMontor("F1170消耗DS","DG_JLO_010","13869.1","t/h",time);
        parameterMontorList.add(parameterMontor16);
        ParameterMontor parameterMontor17 = new ParameterMontor("F1180消耗DS","DG_JLO_011","932.424","t/h",time);
        parameterMontorList.add(parameterMontor17);
    }
}
