package com.peemes.android.energyAssess;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import com.peemes.android.MainActivity;
import com.peemes.android.R;


/**
 * Created by cshao on 2018/10/24.
 */

public class EnergyAssessActivity extends AppCompatActivity {
    private String[] data = {"系统级分析","过程级分析","设备级分析","问题及建议","专家诊断"};
    private Button button_title_back;
    private TextView textView_title_text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        button_title_back = (Button)findViewById(R.id.button_title_back);
        textView_title_text = (TextView)findViewById(R.id.textView_title_text);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EnergyAssessActivity.this,
                android.R.layout.simple_list_item_1,data);
        textView_title_text.setText("能效评估");
        //这两个方法是实验数据是否传送上来。
        //GetEAParameter.initAssessActivityList();
        //GetEAParameter.print();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Toast.makeText(EnergyAssessActivity.this,data[position],Toast.LENGTH_SHORT).show();
                        Log.d("第几行：","1");
                        break;
                    case 2:
                        Toast.makeText(EnergyAssessActivity.this,data[position],Toast.LENGTH_SHORT).show();
                        Log.d("第几行：","2");
                        break;
                    case 3:
                        Toast.makeText(EnergyAssessActivity.this,data[position],Toast.LENGTH_SHORT).show();
                        Log.d("第几行：","3");
                        break;
                    case 4:
                        Toast.makeText(EnergyAssessActivity.this,data[position],Toast.LENGTH_SHORT).show();
                        Log.d("第几行：","4");
                        break;
                    case 0:
                        Toast.makeText(EnergyAssessActivity.this,data[position],Toast.LENGTH_SHORT).show();
                        Log.d("第几行：","0");
                        break;
                }
            }
        });
        button_title_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnergyAssessActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
