package com.peemes.android.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.peemes.android.util.OperatorUser;

/**
 * Created by cshao on 2018/12/8.
 */
//接收广播的类，主要是显示用户操作日志所用
public class LocalBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //接收数据，加到数据中
        String userid = intent.getStringExtra("userid");
        String username = intent.getStringExtra("username");
        String operator = intent.getStringExtra("operator");
        String time = intent.getStringExtra("time");
        //加载数据库
        OperatorUser operatorUser = new OperatorUser();
        operatorUser.setUserid(userid);
        operatorUser.setUsername(username);
        operatorUser.setOperator(operator);
        operatorUser.setLoginTime(time);
        operatorUser.save();
    }
}
