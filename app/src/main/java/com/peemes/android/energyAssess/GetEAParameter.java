package com.peemes.android.energyAssess;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cshao on 2018/11/12.
 */
/*
* 主要是接收从服务端传过来的能效评估的指标的相应值的所有信息*/
public class GetEAParameter {
    public static List<EnergyAssessParameter> assessActivityList = new ArrayList<>();
    //对传过来的JSON格式的数据进行GSON解析
    public static void parseJSONWithGson(String jsonData){
        Gson gson = new Gson();
        List<EnergyAssessParameter> list = gson.fromJson(jsonData,
                new TypeToken<List<EnergyAssessParameter>>(){}.getType());
        if (assessActivityList.size()>0) {
            assessActivityList.clear();
        }
        for(EnergyAssessParameter eap : list){
            EnergyAssessParameter myZap = new EnergyAssessParameter(eap.getId(),eap.getName(),
                    eap.getCal_meth(),eap.getMeaning(),eap.getUom());
            assessActivityList.add(myZap);
        }
    }
    //对assessActivityList进行初始化；以供后续操作
    public static void initAssessActivityList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.6.76.128:8080/PEEMES/EnergyAssessServlet")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGson(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("EnergyAssessParameter","连接服务器获取能效评估指标异常");
                }
            }
        }).start();
    }
    //验证是否从服务器获取数据成功，对List集合打印输出。
    public static void print(){
        for(EnergyAssessParameter eap : assessActivityList){
            Log.d("EnergyAssessParameter",eap.getId()+" "+eap.getName()+" "+eap.getCal_meth()
            +" "+eap.getMeaning()+" "+eap.getUom());
        }
    }
}
