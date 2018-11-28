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
//用于显示裂解区物质流的相关参数

public class ShowLiejieMatterActivity extends AppCompatActivity {
    private List<ParameterMontor> parameterMontorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_detal);
        //注册标题栏的相关控件
        Button backButton = (Button)findViewById(R.id.button_title_back);
        TextView textViewTitle = (TextView)findViewById(R.id.textView_title_text);
        textViewTitle.setText("裂解区物质流");
        //初始化参数数据，读取的是固定值，如何模拟值在一定范围内变化。
        initParameter();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ParameterMontorAdapter adapter = new ParameterMontorAdapter(ShowLiejieMatterActivity.this,
                R.layout.item,parameterMontorList);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        listView.setAdapter(adapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowLiejieMatterActivity.this,LiejieIndexActivity.class);
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
        ParameterMontor parameterMontor1 = new ParameterMontor("加氢裂化尾油","TI_111009","75.1872","t/h",time);
        parameterMontorList.add(parameterMontor1);
        ParameterMontor parameterMontor2 = new ParameterMontor("轻烃进料","TI_111030","/","t/h",time);
        parameterMontorList.add(parameterMontor2);
        ParameterMontor parameterMontor3 = new ParameterMontor("石脑油","FI_19002","164.25","t/h",time);
        parameterMontorList.add(parameterMontor3);
        ParameterMontor parameterMontor4 = new ParameterMontor("驰放丙烯","FIQ_19007","/","t/h",time);
        parameterMontorList.add(parameterMontor4);
        ParameterMontor parameterMontor5 = new ParameterMontor("加氢碳五（拔头油）","FI_19006","/","t/h",time);
        parameterMontorList.add(parameterMontor5);
        ParameterMontor parameterMontor6 = new ParameterMontor("减一减顶油","FIQ_19001","50.625","t/h",time);
        parameterMontorList.add(parameterMontor6);
        ParameterMontor parameterMontor7 = new ParameterMontor("DPG","FIQ_19103","/","t/h",time);
        parameterMontorList.add(parameterMontor7);
    }
}
