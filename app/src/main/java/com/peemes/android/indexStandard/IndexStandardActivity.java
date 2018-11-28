package com.peemes.android.indexStandard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.peemes.android.MainActivity;
import com.peemes.android.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cshao on 2018/11/9.
 */

public class IndexStandardActivity extends AppCompatActivity {
    private List<Index> indexList = new ArrayList<>();
    //下拉刷新操作
    private SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_standard);
        //屏蔽原来的标题栏，然后自己设置标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //注册控件，获取控件的实例化
        Button buttonBack = (Button) findViewById(R.id.button_standard_back);
        TextView textViewTitle = (TextView) findViewById(R.id.textView_standard_title);
        //设置标题
        textViewTitle.setText("指标基准值");
        //对指标基准值进行初始化
        initIndexStandard();
        //对下拉刷新操作进行注册
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.index_swipe_refresh);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initIndexStandard();
                refreshLayout.setRefreshing(false);
            }
        });
        /*//从登录界面中获取登录的相关参数
        Intent intent = getIntent();
        String userid = intent.getStringExtra("userid");
        if (userid == null) {
            userid = "1";
        }
        int num = Integer.parseInt(userid);
        if (num>100) {
            Toast.makeText(IndexStandardActivity.this,"您的权限为一般用户，不能查看该页内容",Toast.LENGTH_LONG).show();
        }
        if (num>10 && num <=100) {
            Toast.makeText(IndexStandardActivity.this,"您的权限为普通管理员，不能查看该页内容",Toast.LENGTH_LONG).show();
        }
        if(num>=0 && num <10){*/
            //获取RecyclerView的实例
            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView_standard);
            //创建LinearLayoutManager对象，用于设置到RecyclerView中,用于指定RecyclerView的布局
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(new ItemDecoration(this));
            //创建一个适配器
            IndexStandardAdapter adapter = new IndexStandardAdapter(indexList);
            //完成适配器设置，建立recyclerView和数据之间的真正的联系。
            recyclerView.setAdapter(adapter);
        //}

        //设置返回按钮
        buttonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexStandardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void initIndexStandard(){
        //新开一个线程，从服务器端获取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.6.76.128:8080/PEEMES/IndexStandardValServlet")
                            .build();
                    Response response = client.newCall(request).execute();
                    //从服务端获取JSON格式的数据
                    String responseData = response.body().string();
                    //对获取到的数据进行解析
                    parseJSONWithGson(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("指标基准值","连接服务器获取指标基准值失败");
                }
            }
        }).start();
    }
    private void parseJSONWithGson(String jsonData){
        List<String> listVal = new ArrayList<>();
        List<String> listID = new ArrayList<>();
        String [] stringName = {"系统级综合能耗","乙烯收率","单位乙烯综合能耗","单位乙烯燃料消耗"
                ,"单位乙烯水耗","单位乙烯蒸汽消耗","单位乙烯气体消耗","单位乙烯耗电量","重燃料油汽提塔C-1230能效",
                "请燃料油汽提塔C-1240能效","稀释蒸汽发生器V-1270效率","急冷油塔急冷泵P1210效率",
                "急冷油塔盘油泵P1211效率","急冷水塔水油分离泵P1220效率","C-1260塔底泵效率","一段压缩比","二段压缩比",
                "一二段间压力降","一二段间压力降百分比","三段压缩比","二三段压力降","二三段压力降百分比","五段压缩比",
                "总压缩比","裂解区锅炉给水能量回收率","裂解炉1能效"};
        String []stringUOM = {"kgeo/h","%","kgeo/t","kgeo/t","kgeo/t","kgeo/t","kgeo/t","kgeo/t"
                ,"kgeo/t","kgeo/t","kgeo/t","kgeo/t","kgeo/t","kgeo/t","kgeo/t","%","%","Mpa","%","%","Mpa"
                ,"%","%","%","%","kgeo/t"};
        Gson gson = new Gson();
        List<Index> list = gson.fromJson(jsonData,new TypeToken<List<Index>>(){}.getType());
        //for(Index index : list){
            //Log.d("指标基准值",index.getVal()+"   "+index.getId());
            //Index myIndex = new Index(index.getName(),index.getVal(),index.getUom(),index.getId());
            //indexList.add(myIndex);
       // }
        for(int i= 0; i<list.size();i++){
            listVal.add(list.get(i).getVal());
            listID.add(list.get(i).getId());
            //Log.d("指标基准值",listID.get(i)+"        "+listVal.get(i));
        }
        listID.remove(39);
        listID.remove(38);
        listID.remove(37);
        listVal.remove(39);
        listVal.remove(38);
        listVal.remove(37);
        for (int i = 1; i<14;i++){
            listID.remove(1);
            listVal.remove(1);
        }
        if (indexList.size()>0) {
            indexList.clear();
        }
        for(int i= 0; i<listID.size();i++){
            Index index = new Index(stringName[i],listVal.get(i),stringUOM[i],listID.get(i));
            indexList.add(index);
        }
    }
}
