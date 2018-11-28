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
 * Created by cshao on 2018/11/13.
 */
//主要接收来自服务端的能效评估的具体值，每五分钟更新一次，在此处取得数据可用于折线图的显示
public class GetEAFiveMinute {
    //从数据库中获取的每五分钟更新的所有数据
    public static List<EAFiveMinuteParameter> eaFiveMinutesListParameter = new ArrayList<>();
    //系统级分析的能效评估的指标
    public static List<EAFiveMinuteParameter> systemFiveMinuteList = new ArrayList<>();
    //过程级的裂解过程的指标
    public static List<EAFiveMinuteParameter> LiejieFiveMinuteList = new ArrayList<>();
    //过程级的急冷过程的指标
    public static List<EAFiveMinuteParameter> JilengFiveMinuteList = new ArrayList<>();
    //过程级的压缩过程的指标
    public static List<EAFiveMinuteParameter> YasuoFiveMinuteList = new ArrayList<>();
    //过程级的分级过程的指标
    public static List<EAFiveMinuteParameter> FenliFiveMinuteList = new ArrayList<>();
    //设备级分析的能效评估指标
    public static List<EAFiveMinuteParameter> EquipmentFiveMinuteList = new ArrayList<>();
    //对传过来的JSON格式的数据进行解析
    public static void parseJSONWithGson(String responseData){
        Gson gson = new Gson();
        //对json格式的数据进行解析，然后存在list集合中
        List<EAFiveMinuteParameter> fiveMinuteList = gson.fromJson(responseData,
                new TypeToken<List<EAFiveMinuteParameter>>(){}.getType());
        //遍历list结合，把解析的数据传入到eaFiveMinutesList
        if (eaFiveMinutesListParameter.size()>0) {
            eaFiveMinutesListParameter.clear();
        }
        for(EAFiveMinuteParameter eaf: fiveMinuteList){
            EAFiveMinuteParameter myEaf = new EAFiveMinuteParameter(eaf.getId(),eaf.getClock(),eaf.getVal());
            eaFiveMinutesListParameter.add(myEaf);
        }
    }
    //安卓客服端与服务端进行网络通信，真正的对eaFiveMinutesList进行初始化
    public static void initEaFiveMinutesList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.6.76.128:8080/PEEMES/EAFiveLastServlet")
                            .build();
                    Response response = client.newCall(request).execute();
                    String reponseData = response.body().string();
                    parseJSONWithGson(reponseData);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("GetEAFiveMinute","连接服务器获取没五分钟更新的数据异常，请检查IP地址是否对");
                }
            }
        }).start();
    }
    //将得到的数据进行输出到控制台，来确定有没有从服务端读取数据成功
    public static void print(){
        for(EAFiveMinuteParameter eap:eaFiveMinutesListParameter){
            Log.d("GetEAFiveMinute",eap.getId()+"  "+eap.getClock()+"  "+eap.getVal());
        }
    }
    //对得到的数据进行分组，符合和服务端的显示
    public static void initAllList(){
        if (systemFiveMinuteList.size()>0) {
            systemFiveMinuteList.clear();
        }
        if (LiejieFiveMinuteList.size()>0) {
            LiejieFiveMinuteList.clear();
        }
        if (JilengFiveMinuteList.size()>0) {
            JilengFiveMinuteList.clear();
        }
        if(YasuoFiveMinuteList.size()>0){
            YasuoFiveMinuteList.clear();
        }
        if (FenliFiveMinuteList.size()>0) {
            FenliFiveMinuteList.clear();
        }
        if (EquipmentFiveMinuteList.size()>0) {
            EquipmentFiveMinuteList.clear();
        }
        for(EAFiveMinuteParameter eaFiveMinuteParameter : eaFiveMinutesListParameter){
            if (Integer.parseInt(eaFiveMinuteParameter.getId()) > 11 && (Integer.parseInt(eaFiveMinuteParameter.getId())) < 23) {
                systemFiveMinuteList.add(eaFiveMinuteParameter);
            }
            if ((Integer.parseInt(eaFiveMinuteParameter.getId()) > 43) && (Integer.parseInt(eaFiveMinuteParameter.getId())) < 50) {
                LiejieFiveMinuteList.add(eaFiveMinuteParameter);
            }
            if ((Integer.parseInt(eaFiveMinuteParameter.getId()) > 22) && (Integer.parseInt(eaFiveMinuteParameter.getId())) < 32) {
                JilengFiveMinuteList.add(eaFiveMinuteParameter);
            }
            if ((Integer.parseInt(eaFiveMinuteParameter.getId()) > 31) && (Integer.parseInt(eaFiveMinuteParameter.getId())) < 41) {
                YasuoFiveMinuteList.add(eaFiveMinuteParameter);
            }
            if ((Integer.parseInt(eaFiveMinuteParameter.getId()) > 55) && (Integer.parseInt(eaFiveMinuteParameter.getId())) < 62) {
                FenliFiveMinuteList.add(eaFiveMinuteParameter);
            }
            if ((Integer.parseInt(eaFiveMinuteParameter.getId()) > 49) && (Integer.parseInt(eaFiveMinuteParameter.getId())) < 56) {
                EquipmentFiveMinuteList.add(eaFiveMinuteParameter);
            }
        }
    }
}
