package com.peemes.android.producePlan;

import android.graphics.Color;
import android.graphics.Matrix;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import static com.peemes.android.energyAssess.LineChartManager.legend;

/**
 * Created by cshao on 2018/12/9.
 */

public class BarChartManager {
    private BarChart mBarChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;
    //构造函数
    public BarChartManager(BarChart barChart){
        this.mBarChart = barChart;
        this.leftAxis = mBarChart.getAxisLeft();
        this.rightAxis = mBarChart.getAxisRight();
        this.xAxis = mBarChart.getXAxis();
    }
    //初始化
    private void initLineChart() {
        //背景颜色
       mBarChart.setBackgroundColor(Color.WHITE);
        //网格
        mBarChart.setDrawGridBackground(false);
        //背景阴影
        mBarChart.setDrawBarShadow(false);
        mBarChart.setHighlightFullBarEnabled(false);
        //显示边界
        mBarChart.setDrawBorders(true);
        //设置动画效果
        mBarChart.animateY(1000, Easing.EasingOption.Linear);
        mBarChart.animateX(1000, Easing.EasingOption.Linear);
        //图例设置  (图表说明文字)
        Legend legend = mBarChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);  //图例窗体的形状  目前是一条线 ，还有 圆圈 方块等形状
        legend.setTextSize(20f);  //图例文字的大小
        legend.setTextColor(Color.BLUE); //设置图例文字颜色
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); //图例说明文字在图表的上方
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER); //图例左居中
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//图例的方向为水平
        legend.setDrawInside(false);//绘制在chart的外侧
        //XY轴的设置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X轴设置显示位置在底部
        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签
        xAxis.setTextColor(Color.BLUE);//设置X轴字体颜色
        xAxis.setTextSize(10f);  //设置X轴字体大小
        xAxis.setDrawGridLines(false); //不绘制X轴网格，默认为绘制。
        //y轴设置
        leftAxis.setAxisMinimum(0f); //保证Y轴从0开始，不然会上移一点
        rightAxis.setAxisMinimum(0f);
        //将条目放大 可滑动
        mBarChart.invalidate();
        Matrix mMatrix = new Matrix();
        mMatrix.postScale(2f, 1f);  //X轴宽度放大2倍  竖直方向不变
        mBarChart.getViewPortHandler().refresh(mMatrix, mBarChart, false);
        mBarChart.animateY(800);
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

        /***XY轴的设置***/
        //X轴设置显示位置在底部
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        rightAxis.setAxisMinimum(0f);

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
    public void showBarChart(BarChart barChart,List<String> dateValueList, String name, int color) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < dateValueList.size(); i++) {
            BarEntry barEntry = new BarEntry(i, Float.parseFloat(dateValueList.get(i)));
            entries.add(barEntry);
        }
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
}
