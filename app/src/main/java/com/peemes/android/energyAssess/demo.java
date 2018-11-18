package com.peemes.android.energyAssess;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.peemes.android.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.peemes.android.energyAssess.GetEAFiveMinute.eaFiveMinutesListParameter;

/**
 * Created by cshao on 2018/11/14.
 */

public class demo extends AppCompatActivity {
    private List<EAFiveMinuteParameter> firstList = new ArrayList<>();
    private LineChart lineChart;
    private XAxis xAxis;
    private YAxis leftYAxis;
    private YAxis rightYaxis;
    private Legend legend;
    private LimitLine limitLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);
        lineChart = (LineChart)findViewById(R.id.dynamic_chart2);
        initData();
        initChart(lineChart);
        showLineChart(firstList,"乙烯收率",Color.CYAN);
    }

    //1、数据对象的获取
    public void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.6.102.10:8080/PEEMES/EAFiveMinuteServlet")
                            .build();
                    Response response = client.newCall(request).execute();
                    String reponseData = response.body().string();
                    parseJSONWithGson(reponseData);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("GetEAFiveMinute","连接服务器获取没五分钟更新的数据异常，请检查IP地址是否对");
                }
            }
        }).start();
        for(EAFiveMinuteParameter eaFiveMinuteParameter : eaFiveMinutesListParameter) {
            if (Integer.parseInt(eaFiveMinuteParameter.getId()) == 16) {
                firstList.add(eaFiveMinuteParameter);
            }
        }
    }
    //2、对图表进行初始化
    private void initChart(LineChart lineChart) {
        /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(true);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);

        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);

        /***折线图例 标签 设置***/
        legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
    }
    //3、对曲线进行初始化
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }
    //4、对曲线进行展示
    public void showLineChart(List<EAFiveMinuteParameter> dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            Entry entry = new Entry(i, (float) Integer.parseInt(dataList.get(i).getVal()));
            entries.add(entry);
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }
    public static void parseJSONWithGson(String responseData){
        Gson gson = new Gson();
        //对json格式的数据进行解析，然后存在list集合中
        List<EAFiveMinuteParameter> fiveMinuteList = gson.fromJson(responseData,
                new TypeToken<List<EAFiveMinuteParameter>>(){}.getType());
        //遍历list结合，把解析的数据传入到eaFiveMinutesList
        for(EAFiveMinuteParameter eaf: fiveMinuteList){
            EAFiveMinuteParameter myEaf = new EAFiveMinuteParameter(eaf.getId(),eaf.getClock(),eaf.getVal());
            eaFiveMinutesListParameter.add(myEaf);
        }
    }
}
