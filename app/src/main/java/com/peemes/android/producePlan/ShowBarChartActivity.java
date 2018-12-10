package com.peemes.android.producePlan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.peemes.android.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by cshao on 2018/12/9.
 */

public class ShowBarChartActivity extends AppCompatActivity {
    //X、Y轴的数据集
    private List<String> xData = new ArrayList<>();
    private List<Float> realdata = new ArrayList<>();
    private List<Float> plandata = new ArrayList<>();
    //柱状图的相关参数
    private BarChart barChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;
    private Legend legend;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        //对布局文件中的控件进行注册
        Button buttonBack = (Button)findViewById(R.id.bar_chart_back);
        TextView textViewTitle = (TextView)findViewById(R.id.bar_chart_title);
        barChart = (BarChart)findViewById(R.id.bar_chart);
        //隐藏原标题
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //接收从上一个活动传来的标题
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        textViewTitle.setText(title);
        //对柱状图进行数据注册

        //1、数据的初始化
        initData();
        //2、初始化图表
        initBarChart(barChart);
        //3、对图表进行展示------单条柱状图
        //showBarChart(barChart,plandata,"计划生产完成量",getResources().getColor(R.color.bg_blue));
        //4、对图表进行展示------多条柱状图
        //Map集合，String对应柱状图的名字，list<folat>对应为Y轴的数据集合
        LinkedHashMap<String,List<Float>> chartDataMap = new LinkedHashMap<>();
        chartDataMap.put("实际完成量",realdata);
        chartDataMap.put("计划完成量",plandata);
        List<Integer> colors = Arrays.asList(getResources().getColor(R.color.bg_blue),
                getResources().getColor(R.color.text_yellow));
        showBarChart(xData,chartDataMap,colors);
        //对柱状图的点击事件进行注册
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d("BarChart点击事件",e.getX()+" "+e.getY());
                BarMarkView barMarkView = new BarMarkView(ShowBarChartActivity.this,R.layout.chart_item,
                        xAxis.getValueFormatter());
                barMarkView.setChartView(barChart);
                barChart.setMarker(barMarkView);
            }

            @Override
            public void onNothingSelected() {

            }
        });
        //对返回按钮进行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ShowBarChartActivity.this,PlanListActivityBar.class);
                startActivity(intent1);
                finish();
            }
        });
    }
    //初始化BarChart图表
    private void initBarChart(BarChart barChart) {
        /***图表设置***/
        //背景颜色
        barChart.setBackgroundColor(Color.WHITE);
        //不显示图表网格
        barChart.setDrawGridBackground(false);
        //背景阴影
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);
        //显示边框
        barChart.setDrawBorders(true);
        //设置动画效果
        barChart.animateY(1000, Easing.EasingOption.Linear);
        barChart.animateX(1000, Easing.EasingOption.Linear);
        //不显示边框
        barChart.setDrawBorders(false);
        //不显示右下角描述的内容
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);

        /***XY轴的设置***/
        //X轴设置显示位置在底部
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(xData.size());
        //将X轴显示在中央
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f);

        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();
        //保证Y轴从0开始，不然会上移一点
        //leftAxis.setAxisMinimum(0f);
        //rightAxis.setAxisMinimum(0f);

        //不显示X、Y轴线条
        xAxis.setDrawAxisLine(false);
        leftAxis.setDrawAxisLine(false);
        rightAxis.setDrawAxisLine(false);
        //不显示x轴的网格线
        xAxis.setDrawGridLines(false);
        //不显示右侧Y轴
        rightAxis.setEnabled(false);
        //设置Y轴左侧网格线为虚线
        leftAxis.enableGridDashedLine(10f,10f,0f);

        /***折线图例 标签 设置***/
        legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
    }
    //柱状图初始化设置，有个BarDataSet代表一列柱状图
    //一个柱状图
    private void initBarDataSet(BarDataSet barDataSet, int color) {
        barDataSet.setColor(color);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);
        //不显示柱状图顶部值
        barDataSet.setDrawValues(false);
//        barDataSet.setValueTextSize(10f);
//        barDataSet.setValueTextColor(color);
    }
    //显示一个柱状图
    public void showBarChart(BarChart barChart, List<String> dateValueList, String name, int color) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < dateValueList.size(); i++) {
            BarEntry barEntry = new BarEntry(i, Float.parseFloat(dateValueList.get(i)));
            entries.add(barEntry);
        }
        //X轴的自定义值、Y轴不用自定义
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xData.get((int)value % xData.size());
            }
        });
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, name);
        initBarDataSet(barDataSet, color);

//        // 添加多个BarDataSet时
//        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//        dataSets.add(barDataSet);
//        BarData data = new BarData(dataSets);

        BarData data = new BarData(barDataSet);
        barChart.setData(data);
    }
    //对数据进行初始化
    private void initData(){
        String Xdata[] = {"石脑油","工业水","裂解气油","脱盐水","氢气","燃料油","超高压蒸汽",
                "燃料气","电","软化水"};
        double realData[] = {2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0};
        double planData [] = {2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8};
        for(int i = 0; i<Xdata.length;i++){
            xData.add(Xdata[i]);
        }
        for(int i = 0; i<realData.length;i++){
            realdata.add((float)realData[i]);
        }
        for(int i = 0;i<planData.length;i++){
            plandata.add((float)planData[i]);
        }
    }
    public void showBarChart(final List<String> xValues, LinkedHashMap<String, List<Float>> dataLists,
                             @SuppressLint("SupportAnnotationUsage") @ColorRes List<Integer> colors) {
        /**
         * @param xValues   X轴的值
         * @param dataLists LinkedHashMap<String, List<Float>>
         *                  key对应柱状图名字  List<Float> 对应每类柱状图的Y值
         * @param colors
         */

        List<IBarDataSet> dataSets = new ArrayList<>();
        int currentPosition = 0;//用于柱状图颜色集合的index

        for (LinkedHashMap.Entry<String, List<Float>> entry : dataLists.entrySet()) {
            String name = entry.getKey();
            List<Float> yValueList = entry.getValue();

            List<BarEntry> entries = new ArrayList<>();

            for (int i = 0; i < yValueList.size(); i++) {
                entries.add(new BarEntry(i, yValueList.get(i)));
            }
            // 每一个BarDataSet代表一类柱状图
            BarDataSet barDataSet = new BarDataSet(entries, name);
            initBarDataSet(barDataSet, colors.get(currentPosition));
            dataSets.add(barDataSet);

            currentPosition++;
        }

        //X轴自定义值
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xValues.get((int) Math.abs(value) % xValues.size());
            }
        });
        BarData data = new BarData(dataSets);
        //把堆积柱状图转化为并列柱状图
        int barAmount = dataLists.size();
        float groupSpace = 0.3f; //柱状图组之间的间距
        float barWidth = (1f - groupSpace) / barAmount;
        float barSpace = 0f;
        //设置柱状图宽度
        data.setBarWidth(barWidth);
        //设置起始点、柱状图组间距 柱状图之间的距离
        data.groupBars(0f,groupSpace,barSpace);
        barChart.setData(data);
    }
}
