package com.peemes.android;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.peemes.android.ZheNengCoefficient.ZheNengCoefficientActivity;
import com.peemes.android.demo.BingActivity;
import com.peemes.android.energyAssess.EnergyAssessActivity;
import com.peemes.android.energyAssess.EADataChartShow;
import com.peemes.android.gongyiquanmao.GyqmActivity;
import com.peemes.android.indexStandard.IndexStandardActivity;
import com.peemes.android.liuchengjiankong.LcjkActivity;
import com.peemes.android.monitorParameter.ParaweterMonitorActivity;
import com.peemes.android.user.ManagerUserActivity;

public class MainActivity extends AppCompatActivity {
    //用来传递数据
    private String userid;
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
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        //对各个按钮进行注册事件
        buttonPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BingActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManagerUserActivity.class);
                startActivity(intent);
                finish();
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
                Intent intent = new Intent(MainActivity.this, EADataChartShow.class);
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
                intent.putExtra("userid",userid);
                startActivity(intent);
                //finish();
            }
        });
        buttonIndexStandard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IndexStandardActivity.class);
                intent.putExtra("userid",userid);
                startActivity(intent);
                //finish();
            }
        });
    }
}
