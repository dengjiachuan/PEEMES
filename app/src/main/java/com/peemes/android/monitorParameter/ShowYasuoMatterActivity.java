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
//用于显示压缩区物质流的相关参数

public class ShowYasuoMatterActivity extends AppCompatActivity {
    private List<ParameterMontor> parameterMontorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        //注册标题栏的相关控件
        Button backButton = (Button)findViewById(R.id.button_title_back);
        TextView textViewTitle = (TextView)findViewById(R.id.textView_title_text);
        textViewTitle.setText("压缩区物质流");
        //初始化参数数据，读取的是固定值，如何模拟值在一定范围内变化。
        initParameter();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ParameterMontorAdapter adapter = new ParameterMontorAdapter(ShowYasuoMatterActivity.this,
                R.layout.item,parameterMontorList);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        listView.setAdapter(adapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowYasuoMatterActivity.this,YasuoIndexActivity.class);
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
        ParameterMontor parameterMontor1 = new ParameterMontor("一段裂解气流量","FI_13001","349.313","t/h",time);
        parameterMontorList.add(parameterMontor1);
        ParameterMontor parameterMontor2 = new ParameterMontor("二段裂解气流量","FI_13003","/","t/h",time);
        parameterMontorList.add(parameterMontor2);
        ParameterMontor parameterMontor3 = new ParameterMontor("三段裂解气流量","FI_13004","328.313","t/h",time);
        parameterMontorList.add(parameterMontor3);
        ParameterMontor parameterMontor4 = new ParameterMontor("四段裂解气流量","FI_13008","323.813","t/h",time);
        parameterMontorList.add(parameterMontor4);
        ParameterMontor parameterMontor5 = new ParameterMontor("五段裂解气流量","FI_13039","282.625","t/h",time);
        parameterMontorList.add(parameterMontor5);
        ParameterMontor parameterMontor6 = new ParameterMontor("三段回流量","LIY_13001A","/","t/h",time);
        parameterMontorList.add(parameterMontor6);
        ParameterMontor parameterMontor7 = new ParameterMontor("不合格乙烯","FIC_19003","/","t/h",time);
        parameterMontorList.add(parameterMontor7);
        ParameterMontor parameterMontor8 = new ParameterMontor("V1310/20/30/35/40水去c1220","FI_13002","16.8704","t/h",time);
        parameterMontorList.add(parameterMontor8);
        ParameterMontor parameterMontor9 = new ParameterMontor("不合格丙烯","FIQ_13030","/","t/h",time);
        parameterMontorList.add(parameterMontor9);
    }
}
