package com.peemes.android.monitorParameter;

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
 * Created by cshao on 2018/10/23.
 */

public class ParaweterMonitorActivity extends AppCompatActivity {
    private String[] data = {"系统级一次指标","裂解过程一次指标","急冷过程一次指标",
            "压缩过程一次指标","分离过程一次指标"};
    private Button button_title_back;
    private TextView textView_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        button_title_back = (Button)findViewById(R.id.button_title_back);
        textView_title = (TextView)findViewById(R.id.textView_title_text);
        //将系统自带的标题栏隐藏掉
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //为ListView制定适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ParaweterMonitorActivity.this,
                android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView)findViewById(R.id.listView_title_listview);
        showTitle("参数监测");
        listView.setAdapter(adapter);
        //ListView的点击效应
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Toast.makeText(ParaweterMonitorActivity.this,data[position],Toast.LENGTH_SHORT).show();
                        Log.d("第几行：","1");
                        Intent intent1 = new Intent(ParaweterMonitorActivity.this,LiejieIndexActivity.class);
                        intent1.putExtra("title",data[position]);
                        intent1.putExtra("number",position);
                        startActivity(intent1);
                        break;
                    case 2:
                        Toast.makeText(ParaweterMonitorActivity.this,data[position],Toast.LENGTH_SHORT).show();
                        Log.d("第几行：","2");
                        Intent intent2 = new Intent(ParaweterMonitorActivity.this,JilengIndexActivity.class);
                        intent2.putExtra("title",data[position]);
                        intent2.putExtra("number",position);
                        startActivity(intent2);
                        break;
                    case 3:
                        Toast.makeText(ParaweterMonitorActivity.this,data[position],Toast.LENGTH_SHORT).show();
                        Log.d("第几行：","3");
                        Intent intent3 = new Intent(ParaweterMonitorActivity.this,YasuoIndexActivity.class);
                        intent3.putExtra("title",data[position]);
                        intent3.putExtra("number",position);
                        startActivity(intent3);
                        break;
                    case 4:
                        Toast.makeText(ParaweterMonitorActivity.this,data[position],Toast.LENGTH_SHORT).show();
                        Log.d("第几行：","4");
                        Intent intent4 = new Intent(ParaweterMonitorActivity.this,FenliIndexActivity.class);
                        intent4.putExtra("title",data[position]);
                        intent4.putExtra("number",position);
                        startActivity(intent4);
                        break;
                    case 0:
                        Toast.makeText(ParaweterMonitorActivity.this,data[position],Toast.LENGTH_SHORT).show();
                        Log.d("第几行：","0");
                        Intent intent0 = new Intent(ParaweterMonitorActivity.this,SystemIndexActivity.class);
                        intent0.putExtra("title",data[position]);
                        intent0.putExtra("number",position);
                        startActivity(intent0);
                        break;
                }
            }
        });
        //为返回按钮注册点击事件
        button_title_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParaweterMonitorActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //往标题栏里写标题
    private void showTitle(final String title){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView_title.setText(title);
            }
        });
    }
}
