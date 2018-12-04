package com.peemes.android;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.peemes.android.ZheNengCoefficient.ZheNengCoefficientActivity;
import com.peemes.android.demo.BingActivity;
import com.peemes.android.demo.EADataChartShowDemo;
import com.peemes.android.energyAssess.EnergyAssessActivity;
import com.peemes.android.energyAssess.EADataChartShow;
import com.peemes.android.gongyiquanmao.GyqmActivity;
import com.peemes.android.indexStandard.IndexStandardActivity;
import com.peemes.android.liuchengjiankong.LcjkActivity;
import com.peemes.android.monitorParameter.ParaweterMonitorActivity;
import com.peemes.android.user.ManagerUserActivity;

public class MainActivity extends AppCompatActivity {
    //用来传递数据
    private static String userid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.main_button_parameter_monitor);
        Button button_EnergyAssess = (Button)findViewById(R.id.main_button_assess_energy);
        Button button_ZheNengCofficient = (Button)findViewById(R.id.main_button_zheNeng_parameter);
        Button buttonIndexStandard = (Button)findViewById(R.id.main_button_indexStandard);
        Button buttonTotal = (Button)findViewById(R.id.main_button_total);
        Button buttonGyqm = (Button)findViewById(R.id.main_button_technology_panorama);
        Button buttonLcjk = (Button)findViewById(R.id.main_button_monitor_technological_process);
        Button buttonSystem = (Button)findViewById(R.id.main_button_system_management);
        Button buttonPlan = (Button)findViewById(R.id.main_button_production_plan);
        //接收从登录界面传过来的用户号，然后传给折能参数和指标基准值
        if (userid == null) {
            Intent intent = getIntent();
            userid = intent.getStringExtra("userid");
            // Log.d("userid","  123123****"+userid);
            if (userid == null) {
                userid = "1";
            }
        }
        //对各个按钮进行注册事件
        buttonPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(userid);
                if (id>100) {
                    Toast.makeText(MainActivity.this,"您为普通用户，没有权限浏览该页面",Toast.LENGTH_LONG).show();
                }
                if (id<=100 && id>10) {
                    Toast.makeText(MainActivity.this,"您的身份为普通管理员，没有权限浏览该页面",Toast.LENGTH_LONG).show();
                }
                if (id<=10 && id>0) {
                    Intent intent = new Intent(MainActivity.this, BingActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        buttonSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(userid);
                if (id>100) {
                    Toast.makeText(MainActivity.this,"您为普通用户，没有权限浏览该页面",Toast.LENGTH_LONG).show();
                }
                if (id<=100 && id>10) {
                    Toast.makeText(MainActivity.this,"您的身份为普通管理员，没有权限浏览该页面",Toast.LENGTH_LONG).show();
                }
                if (id<=10 && id>0) {
                    Intent intent = new Intent(MainActivity.this, ManagerUserActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        buttonLcjk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LcjkActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonGyqm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GyqmActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(MainActivity.this, EADataChartShow.class);
                Intent intent = new Intent(MainActivity.this, EADataChartShowDemo.class);
                startActivity(intent);
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParaweterMonitorActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button_EnergyAssess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EnergyAssessActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button_ZheNengCofficient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZheNengCoefficientActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonIndexStandard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IndexStandardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
