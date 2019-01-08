package com.peemes.android.yixiRunning;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by cshao on 2018/11/27.
 */

public class HttpUtil {
    public static void sendOKHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
