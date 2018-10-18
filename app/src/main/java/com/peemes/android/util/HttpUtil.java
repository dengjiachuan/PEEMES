package com.peemes.android.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by cshao on 2018/10/14.
 */
/*
* 在安卓移动端所需要用的数据全是来自于服务器，因此和服务器的交互的很多
* 把与服务器相交互的过程封装成一个Util类
* 采用OkHttp通讯协议*/
public class HttpUtil {
    public static void sendOkHttpRequst(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
