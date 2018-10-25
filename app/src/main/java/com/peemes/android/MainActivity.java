package com.peemes.android;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.peemes.android.ZheNengCoefficient.ZheNengCoefficientActivity;
import com.peemes.android.energyAssess.EnergyAssessActivity;
import com.peemes.android.monitorParameter.ParaweterMonitorActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button_EnergyAssess;
    private Button button_ZheNengCofficient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.main_button_parameter_monitor);
        button_EnergyAssess = (Button)findViewById(R.id.main_button_assess_energy);
        button_ZheNengCofficient = (Button)findViewById(R.id.main_button_zheNeng_parameter);
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
    }
}
