package com.peemes.android.producePlan;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.peemes.android.R;

import java.util.Date;
import java.util.List;

/**
 * Created by cshao on 2018/11/22.
 */

public class BarMarkView extends MarkerView {
    private TextView textViewXdata;
    private TextView textViewreal;
    private TextView textViewplan;
    //展示自定义的X轴的内容
    private IAxisValueFormatter xAxisValueFormatter;
    public BarMarkView(Context context, int layoutResource, IAxisValueFormatter xAxisValueFormatter){
        super(context,layoutResource);
        this.xAxisValueFormatter = xAxisValueFormatter;

        //对控件进行注册
        textViewXdata = (TextView)findViewById(R.id.chart_item_time);
        textViewreal = (TextView)findViewById(R.id.chart_item_yixi);
        textViewplan = (TextView)findViewById(R.id.chart_item_UOMyixi);
    }
    //每次重绘时，会条用该方法

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        Chart barChart = getChartView();
        if (barChart instanceof BarChart) {
            BarData barData = ((BarChart)barChart).getBarData();
            //获取柱状图中所有的曲线
            List<IBarDataSet> dataSets = barData.getDataSets();
            for(int i = 0 ;i<dataSets.size();i++){
                BarDataSet barDataSet = (BarDataSet)dataSets.get(i);
                //获取到曲线所有的Y轴的数据集合，根据当前X轴的值来获取Y轴的值
                float y = barDataSet.getValues().get((int)e.getX()).getY();
                if (i == 0) {
                    textViewreal.setText(concat(y,"实际完成量： "));
                }
                if (i == 1) {
                    textViewplan.setText(concat(y,"计划完成量： "));
                }
            }
        }else{
            Log.d("DetailMarkerView","显示Y轴不同的数据失败");
        }
            String realX = xAxisValueFormatter.getFormattedValue(e.getX(),null);
            textViewXdata.setText("项目  "+realX);
        super.refreshContent(e,highlight);
    }
    //布局的偏移量，即布局显示在圆点的那个位置
    // -(width / 2) 布局水平居中
    //-(height) 布局显示在圆点上方

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth()/2),-getHeight());
    }

    //字符串函数的拼接
    public String concat( float value,String title){
        return title + String.valueOf(value);
    }

}
