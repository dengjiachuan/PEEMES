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
//用于显示急冷区物质流的相关参数

public class ShowJilengMatterActivity extends AppCompatActivity {
    private List<ParameterMontor> parameterMontorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        //注册标题栏的相关控件
        Button backButton = (Button)findViewById(R.id.button_title_back);
        TextView textViewTitle = (TextView)findViewById(R.id.textView_title_text);

        //初始化参数数据，读取的是固定值，如何模拟值在一定范围内变化。
        initParameter();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ParameterMontorAdapter adapter = new ParameterMontorAdapter(ShowJilengMatterActivity.this,
                R.layout.item,parameterMontorList);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        listView.setAdapter(adapter);
        textViewTitle.setText("急冷区物质流");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowJilengMatterActivity.this,JilengIndexActivity.class);
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
        ParameterMontor parameterMontor1 = new ParameterMontor("急冷油塔汽油回流","FIC_12002","/","t/h",time);
        parameterMontorList.add(parameterMontor1);
        ParameterMontor parameterMontor2 = new ParameterMontor("急冷油塔汽油","FIC_12029","/","t/h",time);
        parameterMontorList.add(parameterMontor2);
        ParameterMontor parameterMontor3 = new ParameterMontor("盘油回油量","FI_12004","206.809","t/h",time);
        parameterMontorList.add(parameterMontor3);
        ParameterMontor parameterMontor4 = new ParameterMontor("急冷油塔塔底急冷油回流量","FIC_12005","/","t/h",time);
        parameterMontorList.add(parameterMontor4);
        ParameterMontor parameterMontor5 = new ParameterMontor("急冷水塔塔底油水分离：油","DG_JLQ_002","0.5265","t/h",time);
        parameterMontorList.add(parameterMontor5);
        ParameterMontor parameterMontor6 = new ParameterMontor("汽提塔C-1250塔底裂解汽油回流","DG_JLQ_003","4.5113","t/h",time);
        parameterMontorList.add(parameterMontor6);
    }
}
