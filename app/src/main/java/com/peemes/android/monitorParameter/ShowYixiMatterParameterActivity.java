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
//用于显示乙烯装置物质流的相关参数

public class ShowYixiMatterParameterActivity extends AppCompatActivity {
    private List<ParameterMontor> parameterMontorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_detal);
        //注册标题栏的相关控件
        Button backButton = (Button)findViewById(R.id.button_title_back);
        TextView textViewTitle = (TextView)findViewById(R.id.textView_title_text);
        textViewTitle.setText("乙烯装置物质流");
        //初始化参数数据，读取的是固定值，如何模拟值在一定范围内变化。
        initParameter();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ParameterMontorAdapter adapter = new ParameterMontorAdapter(ShowYixiMatterParameterActivity.this,
                R.layout.item,parameterMontorList);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        listView.setAdapter(adapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowYixiMatterParameterActivity.this,SystemIndexActivity.class);
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
        //ParameterMontor parameterMontor = new ParameterMontor("物质名称","位号","实时值","单位","时间");
        //parameterMontorList.add(parameterMontor);
        ParameterMontor parameterMontor1 = new ParameterMontor("加氢裂化尾油","FIQ_19113","75.375","t/h",time);
        parameterMontorList.add(parameterMontor1);
        ParameterMontor parameterMontor2 = new ParameterMontor("减一减顶油","FI_19107","50.625","t/h",time);
        parameterMontorList.add(parameterMontor2);
        ParameterMontor parameterMontor3 = new ParameterMontor("石脑油","FI_19114","163.875","t/h",time);
        parameterMontorList.add(parameterMontor3);
        ParameterMontor parameterMontor4 = new ParameterMontor("轻烃进料","FI_19115","/","t/h",time);
        parameterMontorList.add(parameterMontor4);
        ParameterMontor parameterMontor5 = new ParameterMontor("加氢碳五","FI_19102","/","t/h",time);
        parameterMontorList.add(parameterMontor5);
        ParameterMontor parameterMontor6 = new ParameterMontor("LPG进料","FI_19110","19.8754","t/h",time);
        parameterMontorList.add(parameterMontor6);
        ParameterMontor parameterMontor7 = new ParameterMontor("液相乙烯T","FI_19109","/","t/h",time);
        parameterMontorList.add(parameterMontor7);
        ParameterMontor parameterMontor8 = new ParameterMontor("气相乙烯AW","FI_19108","75","t/h",time);
        parameterMontorList.add(parameterMontor8);
        ParameterMontor parameterMontor9 = new ParameterMontor("丙烯","FIQ_19116","/","t/h",time);
        parameterMontorList.add(parameterMontor9);
        ParameterMontor parameterMontor10 = new ParameterMontor("原料总量","FIQ_19007","309.75","t/h",time);
        parameterMontorList.add(parameterMontor10);
        ParameterMontor parameterMontor11 = new ParameterMontor("乙烯总量","FI_19006","75","t/h",time);
        parameterMontorList.add(parameterMontor11);
    }
}
