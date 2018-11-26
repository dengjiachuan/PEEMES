package com.peemes.android.energyAssess;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.peemes.android.MainActivity;
import com.peemes.android.R;
import com.peemes.android.demo.CharUtil;

import java.util.ArrayList;
import java.util.List;

import static com.peemes.android.energyAssess.GetEAFiveMinute.eaFiveMinutesListParameter;

/**
 * Created by cshao on 2018/11/14.
 */

public class DynamicChartMinuteActivity extends AppCompatActivity {
    //private LineChartManager chartManager;
    //数据集合
    private List<String> dataList = new ArrayList<>();
    //折线名字集合
    private List<String> lineName = new ArrayList<>();
    //折线的颜色集合
    private List<Integer> lineColur = new ArrayList<>();
    //标记视图，点击xy轴焦点时弹出弹出展示的信息
    List<EAFiveMinuteParameter> firstList = new ArrayList<>();
    List<EAFiveMinuteParameter> secondList = new ArrayList<>();
    private TextView textViewTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);
        LineChart mChart = (LineChart)findViewById(R.id.dynamic_chart);
        Button buttonBack = (Button)findViewById(R.id.chart_back);
        textViewTitle = (TextView)findViewById(R.id.chart_title);
        //先把原来的标题隐藏掉
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //设置标题，目前先自己定义，后面为每一个能效评估指标做图时，在重新修改
        textViewTitle.setText("乙烯车间运行情况");
        //对返回按钮做点击事件
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DynamicChartMinuteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        LineChartManager.initChart(mChart);
        //死循环添加数据
        initData();
        LineChartManager.showLineChart(firstList,"乙烯收率",Color.CYAN);

    }
    //设置X轴的数据
    public void initData(){
        List<Entry> entries = new ArrayList<>();
        //先从数据库中获取数据
        GetEAFiveMinute.initEaFiveMinutesList();
        //然后把乙烯收率和单位乙烯综合能耗的小官数据提取出来

        for(EAFiveMinuteParameter eaFiveMinuteParameter : eaFiveMinutesListParameter) {
            if (Integer.parseInt(eaFiveMinuteParameter.getId()) == 17) {
                secondList.add(eaFiveMinuteParameter);
            }
            if (Integer.parseInt(eaFiveMinuteParameter.getId()) == 16) {
                firstList.add(eaFiveMinuteParameter);
            }
        }


    }
}
