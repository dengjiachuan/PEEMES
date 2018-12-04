package com.peemes.android.demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.peemes.android.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cshao on 2018/12/3.
 */

public class ParameterChartActivity extends AppCompatActivity {
    private LineChart lineChart;
    protected XAxis xAxis;
    protected YAxis leftYAxis;
    //protected YAxis rightYAxis;
    protected Legend legend;
    //X轴的数据集合
    List<String> xDataList = new ArrayList<>();
    //准备两个数据集，当从前面活动中接收到的数据为空时，避免死机
    private String[] realVal = {"32.4508","24.9595","24.8013","24.7564","31.3973","24.7608","32.201","24.6398",
            "24.472","32.4255","32.5694"};
    List<String> realTime = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);
        //对原文中的控件进行注册
        Button buttonBack = (Button)findViewById(R.id.chart_detail_back);
        TextView textViewTitle = (TextView)findViewById(R.id.chart_detail_title);
        TextView textViewDetail = (TextView)findViewById(R.id.chart_detail_text);
        lineChart = (LineChart)findViewById(R.id.chart_detail);
        //对原来的标题进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //对返回按钮进行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParameterChartActivity.this,ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //对有上一个活动中传来的数据进行接收
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        ArrayList<String> val = intent.getStringArrayListExtra("val");
        ArrayList<String> time = intent.getStringArrayListExtra("time");
        if (val.size() == 0) {
            for(int i = 0; i<realVal.length; i++){
                val.add(realVal[i]);
            }
        }
        if (time.size() == 0) {
            initTime();
            for(int i = 0; i<realTime.size();i++){
                time.add(realTime.get(i));
            }
        }
        //检验数据是否接收成功
       // print(title,val,time);
        //对标题进行注册
        textViewTitle.setText("参数趋势图");
        //对详情页进行简介
        textViewDetail.setText(title+"的趋势图");
        //对图表进行初始化
        initChart(time);
        //对曲线进行初始化并显示
        showLineChart(val,title);
    }
    //对接收的数据进行打印输出
    private void print(String title,ArrayList<String> val,ArrayList<String> time){
        for(int i = 0; i <val.size();i++){
            Log.d("val",val.get(i));
        }
        for(int i = 0;i<time.size();i++){
            Log.d("time",time.get(i));
        }
        Log.d("接收到的题目是：",title);
    }
    //对曲线进行显示
    public  void showLineChart(ArrayList<String> val,String name) {
        //Y轴的数据集
        List<Entry> yDataList = new ArrayList<>();
        for (int i = 0; i < val.size(); i++) {
            Entry entry = new Entry(i, Float.parseFloat(val.get(i)));
            yDataList.add(entry);
        }
        // 每一个LineDataSet代表一条线
        LineDataSet set1 = new LineDataSet(yDataList,name);
        initLineDataSet(set1, Color.BLUE);
        //set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
        LineData lineData = new LineData(set1);
        //是否绘制线条上的文字
        lineData.setValueTextSize(9f);
        lineData.setValueTextColor(Color.WHITE);
        lineData.setDrawValues(false);
        lineChart.setData(lineData);
    }
    //对曲线进行初始化
    private void initLineDataSet(LineDataSet lineDataSet,int color){
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(false);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

    }
    //对图表进行初始化，主要有图表的基本设置，图表的XY轴设置，图表的监听事件，显示弹出的小框以及图例的相关设计
    private void initChart(ArrayList<String> time){
        //图表设置
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(true);
        lineChart.setDragEnabled(false);
        lineChart.setTouchEnabled(true);
        lineChart.animateY(2500);
        lineChart.animateX(1500);

        //X轴的设计
        xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        //对X轴显示自定义效果
        if (xDataList.size()>0) {
            xDataList.clear();
        }
        for(int i = 0; i<time.size(); i++){
            String temp = stringToDate(time.get(i));
            xDataList.add(temp);
        }
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String data = xDataList.get((int)value%xDataList.size());
                return data;
            }
        });
        xAxis.setLabelCount(5,false);
        //对Y轴的数据进行相关设计
        leftYAxis = lineChart.getAxisLeft();
        //rightYAxis = lineChart.getAxisRight();
        leftYAxis.enableGridDashedLine(10f,10f,0f);
        //rightYAxis.enableGridDashedLine(10f,10f,0f);
        //rightYAxis.setEnabled(false);

        //图例的相关设计
        legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setFormToTextSpace(15f);
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (lineChart.isDrawMarkersEnabled()) {
                    setMarkView();
                    lineChart.highlightValue(h);
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
        setMarkView();
    }
    //字符串到日期的转换 "2010-05-04 12:34:23"得到12:34:23
    private  String stringToDate(String str){
        String time = null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(str);
            time = String.format("%tT",date);
        }catch(Exception e){
            e.printStackTrace();
            Log.d("字符串转换到日期出错","第七个函数");
        }
        return time;
    }
    //初始化时间数组
    private void initTime(){
        //获取当前时间
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //设置时间的格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i = 11; i>0; i--){
            long temp = timestamp.getTime() - (long)i*5*60*1000;
            Timestamp timestamp1 = new Timestamp(temp);
            String startTime = simpleDateFormat.format(timestamp1);
            //String timeVal = stringToDate(startTime);
            realTime.add(startTime);
        }
    }
    //对markview进行展示
    private void setMarkView(){
        ChartMarkView chartMarkView = new ChartMarkView(ParameterChartActivity.this,xAxis.getValueFormatter());
        chartMarkView.setChartView(lineChart);
        lineChart.setMarker(chartMarkView);
        //lineChart.invalidate();
    }
}
