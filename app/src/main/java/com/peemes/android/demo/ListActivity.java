package com.peemes.android.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.peemes.android.R;
import com.peemes.android.energyAssess.EAFiveMinuteParameter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cshao on 2018/12/3.
 */
//主要为所有参数的的列表集合，点击会给下一个活动传送题目、显示图表的具体数据
public class ListActivity extends AppCompatActivity {
   // private List<String> nameList = new ArrayList<>();
    private String[] nameList = {"MS消耗量","急冷水塔塔底油水分离：油","蒸馏汽提塔C-1250塔底裂解汽油回流","产生的稀释蒸汽（送至该系统外）",
           "F1120用的稀释蒸汽","F1130用的稀释蒸汽","F1140用的稀释蒸汽","F1150用的稀释蒸汽","F1160用的稀释蒸汽",
           "F1170用的稀释蒸汽","F1180用的稀释蒸汽","系统级燃料消耗量","系统级原料总量","系统级乙烯总量","系统级综合能耗",
           "乙烯收率","单位乙烯综合能耗","单位乙烯燃料消耗","单位乙烯水耗","单位乙烯蒸汽消耗","单位乙烯气体消耗",
           "单位乙烯耗电量","重燃料油汽提塔C-1230能效","轻燃料油汽提塔C-1240能效","稀释蒸汽发生器V-1270效率",
           "急冷油塔急冷泵P1210效率","急冷油塔盘油泵P1211效率","急冷水塔水油分离泵P1220效率","C-1260塔底泵效率","急冷区综合能耗",
           "急冷区能效","一段压缩比","二段压缩比","一二段间压力降","一二段间压力降百分比","三段压缩比","二三段压力降",
           "二三段间压力降百分比","五段压缩比","总压缩比","BFW（锅炉给水）流量（炉1）","稀释蒸汽（炉1)","FDS","裂解区能效",
           "裂解区超高压蒸汽能量回收率"," 裂解区热效率","裂解区综合能耗","裂解区净综合能耗","裂解区锅炉给水能量回收率",
           "裂解炉1能效","裂解炉1超高压蒸汽能量回收率","裂解炉1热效率"," 裂解炉1综合能耗","裂解炉1净综合能耗",
           "裂解炉1锅炉给水能量回收率","分离区综合能耗","分离过程乙烯能效","分离区双烯能效","燃料气转化率","乙烯合格率","C2、C3分离度"};
    private List<EAFiveMinuteParameter> subList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        //对布局中的控件进行注册
        Button buttonBack = (Button)findViewById(R.id.button_title_back);
        TextView textViewTitle = (TextView)findViewById(R.id.textView_title_text);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        //对原来的题目进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //对题目进行注册
        textViewTitle.setText("更多参数趋势图");
        //对返回按钮就行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this,EADataChartShowDemo.class);
                startActivity(intent);
                finish();
            }
        });
        //对数据进行初始化
        initData();
        //对ListView的适配器进行配置
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListActivity.this,
                android.R.layout.simple_list_item_1,nameList);
        listView.setAdapter(adapter);
        //对ListView的子项注册点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this,ParameterChartActivity.class);
                //往下一个活动传送的题目
                String title = nameList[position];
                //往下一个活动传送的图表数据
                List<EAFiveMinuteParameter> list = getSubList(position);
                ArrayList<String> val = new ArrayList<String>();
                ArrayList<String> time = new ArrayList<String>();
                for(int i = 0 ;i < list.size();i++){
                    val.add(list.get(i).getVal());
                    time.add(list.get(i).getClock());
                }
                //往下一个活动中传数据
                intent.putExtra("title",title);
                intent.putStringArrayListExtra("val",val);
                intent.putStringArrayListExtra("time",time);
                startActivity(intent);
                finish();
            }
        });
    }
    private void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.6.76.128:8080/PEEMES/EAFiveMinuteServlet")
                            .build();
                    Response response = client.newCall(request).execute();
                    Looper.prepare();
                    String reponseData = response.body().string();
                    parseJSONWithGson(reponseData);
                    Looper.loop();
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("GetEAFiveMinute","连接服务器获取没五分钟更新的数据异常，请检查IP地址是否对");
                }
            }
        }).start();
    }
    //对数据进行解析
    public  void parseJSONWithGson(String responseData){
        Gson gson = new Gson();
        //对json格式的数据进行解析，然后存在list集合中
        List<EAFiveMinuteParameter> fiveMinuteList = gson.fromJson(responseData,
                new TypeToken<List<EAFiveMinuteParameter>>(){}.getType());
        //遍历list结合，把解析的数据传入到eaFiveMinutesList
        for(int i = 0 ; i < fiveMinuteList.size(); i++){
            EAFiveMinuteParameter myEaf = new EAFiveMinuteParameter(fiveMinuteList.get(i).getId(),
                    fiveMinuteList.get(i).getClock(),fiveMinuteList.get(i).getVal());
            subList.add(myEaf);
        }
        //对数据进行打印
        //print(subList,nameList);
    }
    //对数组进行打印，看看数据有没有读上来
    private void print(List<EAFiveMinuteParameter> subList,String[] nameList){
        if (subList.size() == 0 || nameList.length == 0) {
            Log.d("ListActivity","subList.size()=" +subList.size()+"nameList.size()="+nameList.length);
        }
        Log.d("分割线1","***********************");
        for(int i = 0; i<subList.size();i++){
            Log.d("subList",subList.get(i).getId()+"   "+subList.get(i).getVal()+"   "+subList.get(i).getClock());
        }
    }
    //根据传入的编号，返回相应的数据
    private List<EAFiveMinuteParameter> getSubList(int position){
        List<EAFiveMinuteParameter> dataList = new ArrayList<>();
        for(int i = 0; i<subList.size(); i++){
            if (Integer.parseInt(subList.get(i).getId()) == position) {
                dataList.add(subList.get(i));
            }
        }
        for(int i = 0; i<dataList.size(); i++){
            Log.d("datalist",dataList.get(i).getId()+"  "+dataList.get(i).getVal()+"  "+dataList.get(i).getClock());
            Log.d("分割线","***************");
        }
        return dataList;
    }
}
