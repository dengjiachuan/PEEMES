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
//用于显示分离区物质流的相关参数

public class ShowFenliMatterActivity extends AppCompatActivity {
    private List<ParameterMontor> parameterMontorList = new ArrayList<>();
    private TextView textViewTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        //注册标题栏的相关控件
        Button backButton = (Button)findViewById(R.id.button_title_back);
        textViewTitle = (TextView)findViewById(R.id.textView_title_text);
        setTextViewTitle();
        //初始化参数数据，读取的是固定值，如何模拟值在一定范围内变化。
        initParameter();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ParameterMontorAdapter adapter = new ParameterMontorAdapter(ShowFenliMatterActivity.this,
                R.layout.item,parameterMontorList);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        listView.setAdapter(adapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowFenliMatterActivity.this,FenliIndexActivity.class);
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
        ParameterMontor parameterMontor1 = new ParameterMontor("C3以下组分","FIC_13048","34.0505","t/h",time);
        parameterMontorList.add(parameterMontor1);
        ParameterMontor parameterMontor2 = new ParameterMontor("H2储罐","FI_14065","2185.96","t/h",time);
        parameterMontorList.add(parameterMontor2);
        ParameterMontor parameterMontor3 = new ParameterMontor("循环甲烷CH4","FIC_14022","185.832","t/h",time);
        parameterMontorList.add(parameterMontor3);
        ParameterMontor parameterMontor4 = new ParameterMontor("甲烷CH4","FIC_14030","/","t/h",time);
        parameterMontorList.add(parameterMontor4);
        ParameterMontor parameterMontor5 = new ParameterMontor("甲烷CH4","FI_14081","9.9062","t/h",time);
        parameterMontorList.add(parameterMontor5);
        ParameterMontor parameterMontor6 = new ParameterMontor("循环乙烷C2H6","FIC_14001","17.0916","t/h",time);
        parameterMontorList.add(parameterMontor6);
        ParameterMontor parameterMontor7 = new ParameterMontor("液相乙烯C2H4","FIC_16060","/","t/h",time);
        parameterMontorList.add(parameterMontor7);
        ParameterMontor parameterMontor8 = new ParameterMontor("乙烯返炼","FIC_13006","/","t/h",time);
        parameterMontorList.add(parameterMontor8);
        ParameterMontor parameterMontor9 = new ParameterMontor("脱乙烷塔底","FIC_140376","35.4925","t/h",time);
        parameterMontorList.add(parameterMontor9);
        ParameterMontor parameterMontor10 = new ParameterMontor("C3以上组分","FIC_13037","/","t/h",time);
        parameterMontorList.add(parameterMontor10);
        ParameterMontor parameterMontor11 = new ParameterMontor("丙烯储罐","FIC_15014","48.0766","t/h",time);
        parameterMontorList.add(parameterMontor11);
        ParameterMontor parameterMontor12 = new ParameterMontor("循环丙烷","FIC_15025","2295.81","t/h",time);
        parameterMontorList.add(parameterMontor12);
        ParameterMontor parameterMontor13 = new ParameterMontor("循环丙烷","FIC_15010","2412.71","t/h",time);
        parameterMontorList.add(parameterMontor13);
        ParameterMontor parameterMontor14 = new ParameterMontor("混合C4","FIC_15023","34.0505","t/h",time);
        parameterMontorList.add(parameterMontor14);
        ParameterMontor parameterMontor15 = new ParameterMontor("粗裂解汽油","FI_15022","66.9062","t/h",time);
        parameterMontorList.add(parameterMontor15);
    }
    private void setTextViewTitle(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewTitle.setText("分离区物质流");
            }
        });
    }
}
