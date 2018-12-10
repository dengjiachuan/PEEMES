package com.peemes.android.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.peemes.android.R;
import com.peemes.android.util.OperatorUser;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cshao on 2018/12/8.
 */

public class OperatorActivity extends AppCompatActivity {
    private List<OperatorUser> operatorUserList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_operator);
        //对布局中的控件进行注册
        Button buttonBack = (Button)findViewById(R.id.operator_activity_back);
        TextView textViewTitle = (TextView)findViewById(R.id.operator_activity_title);
        ListView listView = (ListView)findViewById(R.id.operator_activity_liseview);
        //对原来的标题进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //对题目进行注册
        textViewTitle.setText("操作日志");
        //接收广播
        //对数据进行初始化
        initOperatorUser();
        print();
        //注册适配器
        OperatorUserAdapter adapter = new OperatorUserAdapter
                (OperatorActivity.this,R.layout.user_operator_item,operatorUserList);
        listView.setAdapter(adapter);
        //对返回按钮进行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OperatorActivity.this,SystemListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //对数据进行初始化
    private void initOperatorUser(){
        //从数据库中拿出数据
        List<OperatorUser> operatorList = DataSupport.findAll(OperatorUser.class);
        Log.d( "operator.size() =","   "+operatorList.size());
        for(int i = 0; i<operatorList.size(); i++){
            OperatorUser operatorUser = new OperatorUser();
            operatorUser.setId(operatorList.get(i).getId());
            operatorUser.setUserid(operatorList.get(i).getUserid());
            operatorUser.setUsername(operatorList.get(i).getUsername());
            operatorUser.setOperator(operatorList.get(i).getOperator());
            operatorUser.setLoginTime(operatorList.get(i).getLoginTime());
            operatorUserList.add(operatorUser);
        }
    }
    private void print(){
        for(int i = 0; i<operatorUserList.size(); i++){
            Log.d("UserList.size()=","  "+operatorUserList.size());
            Log.d("operatorUserList",operatorUserList.get(i).getOperator()+" "+operatorUserList.get(i).getLoginTime());
        }
    }
}
