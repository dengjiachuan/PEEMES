package com.peemes.android.ZheNengCoefficient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.peemes.android.MainActivity;
import com.peemes.android.R;
import com.peemes.android.util.GetIP;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cshao on 2018/10/25.
 */

public class ZheNengCoefficientActivity extends AppCompatActivity {
    private List<ZheNengParameter> znpList = new ArrayList<>();
    private Button button_back;
    private TextView textView_title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhenengparameter);
        //因为在该活动对应的页面布局文件中设计了题目栏，所以需要屏蔽原来的题目栏；
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //先注册控件，获取控件的实例化
        button_back = (Button)findViewById(R.id.button_item_back);
        textView_title = (TextView)findViewById(R.id.textView_item_title);
        textView_title.setText("折能系数");
        //对折能参数数据进行初始化。
        if (znpList.size()==0) {
            initZheNengCoefficient();
        }
        //获取Recycler的实例
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.zheneng_parameter_recyclerView);
        //创建LinearLayoutManager对象，用于设置到RecyclerView中,用于指定RecyclerView的布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ParameterAdapter adapter = new ParameterAdapter(znpList);
        recyclerView.setAdapter(adapter);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZheNengCoefficientActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void initZheNengCoefficient(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.6.102.10:8080/PEEMES/ZheNengCanShuServlet")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    //从服务器端获取了数据，对JSON格式的数据进行解析
                    parseJSONWithGson(responseData);
                }catch (Exception e){
                    Log.d("折能参数","连接折能参数获取折能参数异常");
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithGson(String jsonData){
        Gson gson = new Gson();
        List<ZheNengParameter> list = gson.fromJson(jsonData,
                new TypeToken<List<ZheNengParameter>>(){}.getType());
        for(ZheNengParameter znp :list){
            /*Log.d("ZheNengCoefficientAct",znp.getId());
            Log.d("ZheNengCoefficientAct",znp.getName());
            Log.d("ZheNengCoefficientAct",znp.getVal());
            Log.d("ZheNengCoefficientAct",znp.getUom());*/
            //先把数据装载在适配器的数组中
            ZheNengParameter myznp = new ZheNengParameter(znp.getId(),znp.getMeaning(),znp.getVal(),znp.getUom());
            znpList.add(myznp);
        }

    }
}
