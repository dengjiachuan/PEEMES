package com.peemes.android.demo;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.peemes.android.R;
import com.peemes.android.energyAssess.CustomValueFormatter;

import java.util.Date;
import java.util.List;

/**
 * Created by cshao on 2018/11/22.
 */

public class DetailMarkerViewDemo extends MarkerView {
    private TextView textViewTime;
    private TextView textViewYixi;
    private TextView textViewUOMYixi;
    //展示自定义的X轴的内容
    private IAxisValueFormatter xAxisValueFormatter;
    public DetailMarkerViewDemo(Context context, int layoutResource, IAxisValueFormatter xAxisValueFormatter){
        super(context,layoutResource);
        this.xAxisValueFormatter = xAxisValueFormatter;

        //对控件进行注册
        textViewTime = (TextView)findViewById(R.id.chart_item_time);
        textViewYixi = (TextView)findViewById(R.id.chart_item_yixi);
        textViewUOMYixi = (TextView)findViewById(R.id.chart_item_UOMyixi);
    }
    //每次重绘时，会条用该方法

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        try{
            //当Y轴的值为零时，显示暂无数据
            if (e.getY() == 0) {
               textViewYixi.setText("暂无数据");
            }else {
                Chart chart = getChartView();
                if (chart instanceof LineChart) {
                    LineData lineData = ((LineChart)chart).getLineData();
                    //获取图中所有的曲线
                    List<ILineDataSet> dataSetList = lineData.getDataSets();
                    for(int i = 0 ; i<dataSetList.size(); i++){
                        LineDataSet dataSet = (LineDataSet)dataSetList.get(i);
                        //获取到曲线所有的Y轴的数据集合，根据当前X轴的值来获取Y轴的值
                        float y = dataSet.getValues().get((int)e.getX()).getY();
                        if (i == 0) {
                            textViewYixi.setText(concat(y,"乙烯收率： "));
                        }
                        if (i == 1) {
                            textViewUOMYixi.setText(concat(y,"单位乙烯综合能耗： "));
                        }
                    }
                }else{
                    Log.d("DetailMarkerView","显示Y轴不同的数据失败");
                }
            }
            String realX = xAxisValueFormatter.getFormattedValue(e.getX(),null);
            Date date = new Date();
            String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-" +
                    "(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|(((" +
                    "[0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
            String time1 = String.format("%tF",date);//年份
            String time2 = String.format("%tT",date);//具体的时间
            if (realX.matches(regex)) {
                textViewTime.setText("Date："+realX +"  " +time2);
            }else{
                textViewTime.setText("Date: "+time1+" "+realX);
            }
        }catch (Exception e1){
            e1.printStackTrace();
        }
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
