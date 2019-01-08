package com.peemes.android.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.peemes.android.MainActivity;
import com.peemes.android.R;
import com.peemes.android.util.GetSomething;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cshao on 2018/11/20.
 */

public class ManagerUserActivity extends AppCompatActivity{
    //实现下拉刷新操作
    private SwipeRefreshLayout refreshLayout;
    private List<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_user);
        //对控件进行注册，以获取实例
        Button buttonBack = (Button)findViewById(R.id.user_button);
        TextView textViewTitle = (TextView)findViewById(R.id.user_title);
        final ListView listView = (ListView)findViewById(R.id.user_listview);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.user_swipe_refresh);
        //对原来的题目进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //设置标题
        textViewTitle.setText("用户信息管理");
        //初始化用户数据
        initUser();
        //配置适配器
        UserAdapter adapter = new UserAdapter(ManagerUserActivity.this,R.layout.user_item,userList);
        listView.setAdapter(adapter);
        //对返回按钮进行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerUserActivity.this, SystemListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //下拉刷新操作
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initUser();
                //配置适配器
                UserAdapter adapter = new UserAdapter(ManagerUserActivity.this,R.layout.user_item,userList);
                listView.setAdapter(adapter);
                refreshLayout.setRefreshing(false);
            }
        });
    }
    private void initUser(){
        //新开一个线程，从服务器获取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://"+ GetSomething.IP+":8080/PEEMES/UserServlet")
                            .build();
                    Response response = client.newCall(request).execute();
                    //从服务器端接收到的json格式的数据
                    String responseData = response.body().string();
                    //对获取到的数据进行解析
                    parseJSONWithGson(responseData);
                }catch(Exception e){
                    e.printStackTrace();
                    Log.d("获取用户信息","连接服务器获取获取用户信息失败");
                }
            }
        }).start();
    }
    private void parseJSONWithGson(String jsonData){
        Gson gson = new Gson();
        //用一个集合接收从服务端传过来的数据
        List<User> list = gson.fromJson(jsonData,new TypeToken<List<User>>(){}.getType());
        if (userList.size()>0) {
            userList.clear();
        }
        for(User user:list){
            User myUser = new User(user.getUserID(),user.getUserName(),user.getPassWord(),user.getPartmentID(),
                    user.getPrivGrade(),user.getLastLogin());
            userList.add(myUser);
        }
    }
}
