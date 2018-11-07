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
//用于显示压缩区能量流的相关参数

public class ShowYasuoEnergyActivity extends AppCompatActivity {
    private List<ParameterMontor> parameterMontorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        //注册标题栏的相关控件
        Button backButton = (Button)findViewById(R.id.button_title_back);
        TextView textViewTitle = (TextView)findViewById(R.id.textView_title_text);
        textViewTitle.setText("压缩区能量流");
        //初始化参数数据，读取的是固定值，如何模拟值在一定范围内变化。
        initParameter();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ParameterMontorAdapter adapter = new ParameterMontorAdapter(ShowYasuoEnergyActivity.this,
                R.layout.item,parameterMontorList);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        listView.setAdapter(adapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowYasuoEnergyActivity.this,YasuoIndexActivity.class);
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
        ParameterMontor parameterMontor1 = new ParameterMontor("一段入口压力","PI_13007","36.0075","KPa",time);
        parameterMontorList.add(parameterMontor1);
        ParameterMontor parameterMontor2 = new ParameterMontor("一段出口压力","PI_13010","179.025","KPa",time);
        parameterMontorList.add(parameterMontor2);
        ParameterMontor parameterMontor3 = new ParameterMontor("一段入口温度","TI_13001","37.6875","°C",time);
        parameterMontorList.add(parameterMontor3);
        ParameterMontor parameterMontor4 = new ParameterMontor("一段出口温度","TI_13002","86.4375","°C",time);
        parameterMontorList.add(parameterMontor4);
        ParameterMontor parameterMontor5 = new ParameterMontor("二段入口压力","PI_13004","155.625","KPa",time);
        parameterMontorList.add(parameterMontor5);
        ParameterMontor parameterMontor6 = new ParameterMontor("二段出口压力","PI_13017","0.4329","MPa",time);
        parameterMontorList.add(parameterMontor6);
        ParameterMontor parameterMontor7 = new ParameterMontor("二段入口温度","TI_13009","35.0625","°C",time);
        parameterMontorList.add(parameterMontor7);
        ParameterMontor parameterMontor8 = new ParameterMontor("二段出口温度","TI_13307","/","°C",time);
        parameterMontorList.add(parameterMontor8);
        ParameterMontor parameterMontor9 = new ParameterMontor("三段入口压力","PI_13020","0.3709","MPa",time);
        parameterMontorList.add(parameterMontor9);
        ParameterMontor parameterMontor10 = new ParameterMontor("三段出口压力","PI_13023","0.8434","Mpa",time);
        parameterMontorList.add(parameterMontor10);
        ParameterMontor parameterMontor11 = new ParameterMontor("三段入口温度","TI_13017","35.35","°C",time);
        parameterMontorList.add(parameterMontor11);
        ParameterMontor parameterMontor12 = new ParameterMontor("三段出口温度","TI_13018","86.925","°C",time);
        parameterMontorList.add(parameterMontor12);
        ParameterMontor parameterMontor13 = new ParameterMontor("四段入口压力","PI_13013","/","Mpa",time);
        parameterMontorList.add(parameterMontor13);
        ParameterMontor parameterMontor14 = new ParameterMontor("四段出口压力","PI_13015","/","Mpa",time);
        parameterMontorList.add(parameterMontor14);
        ParameterMontor parameterMontor23 = new ParameterMontor("四段入口温度","TI_13022","/","°C",time);
        parameterMontorList.add(parameterMontor23);
        ParameterMontor parameterMontor15 = new ParameterMontor("四段出口温度","TI_13024","85.6875","°C",time);
        parameterMontorList.add(parameterMontor15);
        ParameterMontor parameterMontor16 = new ParameterMontor("五段入口压力","PI_13027","1.4197","Mpa",time);
        parameterMontorList.add(parameterMontor16);
        ParameterMontor parameterMontor17 = new ParameterMontor("五段出口压力","PI_13030","3.8543","Mpa",time);
        parameterMontorList.add(parameterMontor17);
        ParameterMontor parameterMontor18 = new ParameterMontor("五段入口温度","TI_13064","7.525","°C",time);
        parameterMontorList.add(parameterMontor18);
        ParameterMontor parameterMontor19 = new ParameterMontor("五段出口温度","TI_13069","77.0063","°C",time);
        parameterMontorList.add(parameterMontor19);
        ParameterMontor parameterMontor20 = new ParameterMontor("压缩机透平转速","SE_13805","/","r/s",time);
        parameterMontorList.add(parameterMontor20);
    }
}
