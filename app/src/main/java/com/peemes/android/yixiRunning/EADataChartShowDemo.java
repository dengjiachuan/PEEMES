package com.peemes.android.yixiRunning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.peemes.android.MainActivity;
import com.peemes.android.R;
import com.peemes.android.energyAssess.EAFiveMinuteParameter;
import com.peemes.android.util.GetSomething;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cshao on 2018/11/14.
 */

public class EADataChartShowDemo extends AppCompatActivity {
    private List<EAFiveMinuteParameter> firstList = new ArrayList<>();
    private List<EAFiveMinuteParameter> secondList = new ArrayList<>();
    private List<String> xList = new ArrayList<>();
    //查询周期为每天
    private List<EAFiveMinuteParameter> firstListDay = new ArrayList<>();
    private List<EAFiveMinuteParameter> secondListDay = new ArrayList<>();
    private List<String> xListDay = new ArrayList<>();
    //查询周期为每周
    private List<EAFiveMinuteParameter> firstListWeek = new ArrayList<>();
    private List<EAFiveMinuteParameter> secondListWeek = new ArrayList<>();
    private List<String> xListWeek = new ArrayList<>();
    //查询周期为每月
    private List<EAFiveMinuteParameter> firstListMonth = new ArrayList<>();
    private List<EAFiveMinuteParameter> secondListMonth = new ArrayList<>();
    private List<String> xListMonth = new ArrayList<>();

    private LineChart lineChart;
    protected XAxis xAxis;
    protected YAxis leftYAxis;
    protected YAxis rightYaxis;
    protected Legend legend;
    //标记视图，即自定义点击效果图效果图
    private DetailMarkerViewDemo detailMarkerView;
    //实现手动刷新功能
    private SwipeRefreshLayout swipeRefreshLayout;
    //设置定时器自动更新界面
    final Handler handler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    update();
                    break;
            }
            super.handleMessage(message);
        }
        void update(){
            initData();
        }
    };
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };
    //设置单选框，用来实现选择不同的查看周期来查看乙烯和单位乙烯综合能耗
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);
        //对原来的标题进行隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //对图表页的控件进行注册
        final Button buttonBack = (Button)findViewById(R.id.chart_back);
        TextView textViewTitle = (TextView)findViewById(R.id.chart_title);
        Button buttonMoreDetail = (Button)findViewById(R.id.chart_more_detail);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);

        //对下拉刷新的进度条进行设置
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        //对刷新操作进行监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        //对题目进行注册
        textViewTitle.setText("乙烯车间运行情况");
        lineChart = (LineChart)findViewById(R.id.dynamic_chart);
        //数据对象的初始化
        initData();
        //对返回按钮进行注册
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EADataChartShowDemo.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //对获取更多参数详情的按钮进行注册
        buttonMoreDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EADataChartShowDemo.this,ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //启动定时器
        timer.schedule(task,40*1000,5*60*1000);
        //对单选框进行注册获取相应的实例
        radioGroup = (RadioGroup)findViewById(R.id.chart_radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.chart_rg_tour:
                        //if (xList.size() ==0 && firstList.size() == 0 && secondList.size() == 0) {
                            initData();
                      //  }
                        break;
                    case R.id.chart_rg_day:
                        //if (xListDay.size() == 0 && firstListDay.size() == 0 && secondListDay.size() == 0) {
                            initDataDay();
                       // }
                        break;
                    case R.id.chart_rg_week:
                        //if (xListWeek.size() == 0 && firstListWeek.size() == 0 && secondListWeek.size() == 0) {
                            initWeekData();
                       // }
                        //Toast.makeText(EADataChartShow.this,"您选中的查看周期为每周",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chart_rg_month:
                        //if (xListMonth.size() == 0 && firstListMonth.size() == 0 && secondListMonth.size() == 0) {
                            initMonthData();
                       // }
                        //Toast.makeText(EADataChartShow.this,"您选中的查看周期为每月",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chart_rg_quarter:
                        Toast.makeText(EADataChartShowDemo.this,"您选中的查看周期为每季度",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chart_rg_year:
                        Toast.makeText(EADataChartShowDemo.this,"您选中的查看周期为每年",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(EADataChartShowDemo.this,"传入的数据有错误",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
    //1、数据对象的获取-----查询周期为每小时
    public  void  initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://"+ GetSomething.IP+":8080/PEEMES/EAFiveMinuteServlet")
                            .build();
                    Response response = client.newCall(request).execute();
                    Looper.prepare();
                    String reponseData = response.body().string();
                    parseJSONWithGson(reponseData);
                    Looper.loop();
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("GetEAFiveMinute","连接服务器获取没五分钟更新的数据异常，请检查IP地址是否对");
                }
            }
        }).start();
    }
    //2、对数据进行解析-----查询周期为小时
    public  void parseJSONWithGson(String responseData){
        Gson gson = new Gson();
        //对json格式的数据进行解析，然后存在list集合中
        List<EAFiveMinuteParameter> fiveMinuteList = gson.fromJson(responseData,
                new TypeToken<List<EAFiveMinuteParameter>>(){}.getType());
        //遍历list结合，把解析的数据传入到eaFiveMinutesList
        if (firstList.size() > 0) {
            firstList.clear();
        }
        if (secondList.size() > 0) {
            secondList.clear();
        }
        for(EAFiveMinuteParameter eaf: fiveMinuteList){
            EAFiveMinuteParameter myEaf = new EAFiveMinuteParameter(eaf.getId(),eaf.getClock(),eaf.getVal());
            //对不同的参数进行去值，放到相应的数组中
            if (Integer.parseInt(myEaf.getId()) == 16) {
                firstList.add(myEaf);
            }
            //获得单位乙烯综合能耗的数据集
            if (Integer.parseInt(myEaf.getId()) == 17) {
                secondList.add(myEaf);
            }
        }
        //对数据进行打印
        print(firstList,secondList,"小时");
        //图表的初始化设置
        initChart(lineChart);
        //曲线的展示
        showLineChart(firstList,secondList);
    }
    //3、对图表进行初始化---查询周期为每小时
    private  void initChart(final LineChart lineChart) {
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
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);

        //对X轴显现自己定义效果
        if (xList.size() > 0) {
            xList.clear();
        }
        for(int i = 0; i<firstList.size();i++){
            String temp = stringToDate(firstList.get(i).getClock());
            xList.add(temp);
        }
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String data = xList.get((int)value%xList.size());
                return data;
            }
        });
        /*xAxis.setValueFormatter(new IndexAxisValueFormatter(xList));
        CustomValueFormatter formatter = new CustomValueFormatter(xList);
        xAxis.setValueFormatter(formatter);*/
        //设置X轴的分割数
        xAxis.setLabelCount(5,false);

        //对Y轴进行相关的设计
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();

        //设置YZ轴的最值
        leftYAxis.setAxisMinimum(20f);
        leftYAxis.setAxisMaximum(40f);
        rightYaxis.setAxisMinimum(40f);
        rightYaxis.setAxisMaximum(1200f);
        //把XY轴自己的网格线禁掉
        rightYaxis.setDrawGridLines(false);
        leftYAxis.setDrawGridLines(false);
        //把网格背景线设置成为虚线
        leftYAxis.enableGridDashedLine(10f,10f,0f);
        rightYaxis.enableGridDashedLine(10f,10f,0f);
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
        legend.setFormToTextSpace(15f);
        //对图例中的标签设置是否换行
        legend.setWordWrapEnabled(true);
        //隐藏X轴描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        //点击图表坐标监听
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (lineChart.isDrawMarkersEnabled()) {
                    createMakerView();
                    lineChart.highlightValue(h);
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
        createMakerView();
    }
    //4、对曲线进行初始化
    private  void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
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
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(mode);
        } else {
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        }
    }
    //5、对曲线进行展示
    public  void showLineChart(List<EAFiveMinuteParameter> first,List<EAFiveMinuteParameter> second) {
        //乙烯收率的数据集
        List<Entry> entriesYixi = new ArrayList<>();
        for (int i = 0; i < first.size(); i++) {
            Entry entry = new Entry(i, Float.parseFloat(first.get(i).getVal()));
            entriesYixi.add(entry);
        }
        //单位乙烯综合能耗的数据集
        List<Entry> entriesUOMYixi = new ArrayList<>();
        for(int i = 0 ;i < second.size() ; i++ ){
            Entry entry = new Entry(i,Float.parseFloat(second.get(i).getVal()));
            entriesUOMYixi.add(entry);
        }
        // 每一个LineDataSet代表一条线
        LineDataSet set1,set2;
       // if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
           // set1 = (LineDataSet)lineChart.getData().getDataSetByIndex(0);
           // set2 = (LineDataSet)lineChart.getData().getDataSetByIndex(1);
      //  }else{
            set1 = new LineDataSet(entriesYixi,"乙烯收率");
            initLineDataSet(set1,Color.CYAN, LineDataSet.Mode.LINEAR);

            set2 = new LineDataSet(entriesUOMYixi,"单位乙烯综合能耗");
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            initLineDataSet(set2,Color.BLUE,LineDataSet.Mode.CUBIC_BEZIER);
      //  }
        LineData lineData = new LineData(set1,set2);
        //是否绘制线条上的文字
        lineData.setValueTextSize(9f);
        lineData.setValueTextColor(Color.WHITE);
        lineData.setDrawValues(false);
        lineChart.setData(lineData);
    }
    //6、创建覆盖物
    public  void createMakerView(){
        detailMarkerView = new DetailMarkerViewDemo(this,R.layout.chart_item,xAxis.getValueFormatter());
        detailMarkerView.setChartView(lineChart);
        //detailMarkerView.setChartView(lineChart);
        lineChart.setMarker(detailMarkerView);
    }

    //7、数据对象的获取-----查询周期为每天
    public  void  initDataDay(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://"+ GetSomething.IP+":8080/PEEMES/EADayServlet")
                            .build();
                    Response response = client.newCall(request).execute();
                    Looper.prepare();
                    String reponseData = response.body().string();
                    parseJSONWithGsonDay(reponseData);
                    Looper.loop();
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("GetEAFiveMinute","连接服务器获取每天更新的数据异常，请检查IP地址是否对");
                }
            }
        }).start();
    }
    //8、对数据进行解析-----查询周期为每天
    public  void parseJSONWithGsonDay(String responseData){
        Gson gson = new Gson();
        //对json格式的数据进行解析，然后存在list集合中
        List<EAFiveMinuteParameter> fiveMinuteList = gson.fromJson(responseData,
                new TypeToken<List<EAFiveMinuteParameter>>(){}.getType());
        //遍历list结合，把解析的数据传入到eaFiveMinutesList
        if (firstListDay.size() > 0) {
            firstListDay.clear();
        }
        if (secondListDay.size() > 0) {
            secondListDay.clear();
        }
        for(EAFiveMinuteParameter eaf: fiveMinuteList){
            EAFiveMinuteParameter myEaf = new EAFiveMinuteParameter(eaf.getId(),eaf.getClock(),eaf.getVal());
            //对不同的参数进行去值，放到相应的数组中
            if (Integer.parseInt(myEaf.getId()) == 16) {
                firstListDay.add(myEaf);
            }
            //获得单位乙烯综合能耗的数据集
            if (Integer.parseInt(myEaf.getId()) == 17) {
                secondListDay.add(myEaf);
            }
        }
        //对数据进行打印
        print(firstListDay,secondListDay,"天");
        //图表的初始化设置
        initChartDay(lineChart);
        //曲线的展示
        showLineChart(firstListDay,secondListDay);
    }
    //9、对图表进行初始化---查询周期为每每天
    private  void initChartDay(final LineChart lineChart) {
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
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);

        //对X轴显现自己定义效果
        if (xListDay.size() > 0) {
            xListDay.clear();
        }
        for(int i = 0; i<firstListDay.size();i++){
            String temp = stringToDataYear(firstListDay.get(i).getClock());
            xListDay.add(temp);
        }
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String data = xListDay.get((int)value%xListDay.size());
                return data;
            }
        });
        //设置X轴的分割数
        xAxis.setLabelCount(5,false);

        //对Y轴进行相关的设计
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();

        //设置YZ轴的最值
        leftYAxis.setAxisMinimum(20f);
        leftYAxis.setAxisMaximum(40f);
        rightYaxis.setAxisMinimum(40f);
        rightYaxis.setAxisMaximum(1200f);
        //把XY轴自己的网格线禁掉
        rightYaxis.setDrawGridLines(false);
        leftYAxis.setDrawGridLines(false);
        //把网格背景线设置成为虚线
        leftYAxis.enableGridDashedLine(10f,10f,0f);
        rightYaxis.enableGridDashedLine(10f,10f,0f);
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
        legend.setFormToTextSpace(15f);
        //对图例中的标签设置是否换行
        legend.setWordWrapEnabled(true);
        //隐藏X轴描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        //点击图表坐标监听
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (lineChart.isDrawMarkersEnabled()) {
                    createMakerView();
                    lineChart.highlightValue(h);
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
        createMakerView();
    }


    //10、数据对象的获取----查询周期为每周
    private void initWeekData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://"+ GetSomething.IP+":8080/PEEMES/EAWeekServlet")
                            .build();
                    Response response = client.newCall(request).execute();
                    Looper.prepare();
                    String reponseData = response.body().string();
                    parseJSONWithGsonWeek(reponseData);
                    Looper.loop();
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("GetEAFiveMinute","连接服务器获取每周更新的数据异常，请检查IP地址是否对");
                }
            }
        }).start();
    }
    //11、对图表进行初始化---查询周期为每每周
    private  void initChartWeek(final LineChart lineChart) {
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
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);

        //对X轴显现自己定义效果
        if (xListWeek.size() > 0) {
            xListWeek.clear();
        }
        for(int i = 0; i<firstListWeek.size();i++){
            String temp = stringToDataYear(firstListWeek.get(i).getClock());
            xListWeek.add(temp);
        }
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String data = xListWeek.get((int)value%xListWeek.size());
                return data;
            }
        });
        //设置X轴的分割数
        xAxis.setLabelCount(5,false);

        //对Y轴进行相关的设计
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();

        //设置YZ轴的最值
        //leftYAxis.setAxisMinimum(20f);
        //leftYAxis.setAxisMaximum(40f);
        //rightYaxis.setAxisMinimum(40f);
        //rightYaxis.setAxisMaximum(1200f);
        //leftYAxis.setAxisMinimum(0f);
        //rightYaxis.setAxisMinimum(0f);
        //把XY轴自己的网格线禁掉
        rightYaxis.setDrawGridLines(false);
        leftYAxis.setDrawGridLines(false);
        //把网格背景线设置成为虚线
        leftYAxis.enableGridDashedLine(10f,10f,0f);
        rightYaxis.enableGridDashedLine(10f,10f,0f);
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
        legend.setFormToTextSpace(15f);
        //对图例中的标签设置是否换行
        legend.setWordWrapEnabled(true);
        //隐藏X轴描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        //点击图表坐标监听
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (lineChart.isDrawMarkersEnabled()) {
                    createMakerView();
                    lineChart.highlightValue(h);
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
        createMakerView();
    }
    //12、对数据进行解析----查询周期为每周
    public  void parseJSONWithGsonWeek(String responseData){
        Gson gson = new Gson();
        //对json格式的数据进行解析，然后存在list集合中
        List<EAFiveMinuteParameter> fiveMinuteList = gson.fromJson(responseData,
                new TypeToken<List<EAFiveMinuteParameter>>(){}.getType());
        //遍历list结合，把解析的数据传入到eaFiveMinutesList
        if (firstListWeek.size() > 0) {
            firstListWeek.clear();
        }
        if (secondListWeek.size() > 0) {
            secondListWeek.clear();
        }
        for(EAFiveMinuteParameter eaf: fiveMinuteList){
            EAFiveMinuteParameter myEaf = new EAFiveMinuteParameter(eaf.getId(),eaf.getClock(),eaf.getVal());
            //对不同的参数进行去值，放到相应的数组中
            if (Integer.parseInt(myEaf.getId()) == 16) {
                firstListWeek.add(myEaf);
            }
            //获得单位乙烯综合能耗的数据集
            if (Integer.parseInt(myEaf.getId()) == 17) {
                secondListWeek.add(myEaf);
            }
        }
        //对数据进行打印
        print(firstListWeek,secondListWeek,"周");
        //图表的初始化设置
        initChartWeek(lineChart);
        //曲线的展示
        showLineChart(firstListWeek,secondListWeek);
    }


    //13、对查询周期为每月的数据进行初始化
    private void initMonthData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://"+ GetSomething.IP+":8080/PEEMES/EAMonthServlet")
                            .build();
                    Response response = client.newCall(request).execute();
                    Looper.prepare();
                    String reponseData = response.body().string();
                    parseJSONWithGsonMonth(reponseData);
                    Looper.loop();
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("GetEAFiveMinute","连接服务器获取每周更新的数据异常，请检查IP地址是否对");
                }
            }
        }).start();
    }
    //14、对图表进行初始化---查询周期为每每月
    private  void initChartMonth(final LineChart lineChart) {
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
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);

        //对X轴显现自己定义效果
        if (xListMonth.size() > 0) {
            xListMonth.clear();
        }
        for(int i = 0; i<firstListMonth.size();i++){
            String temp = stringToDataYear(firstListMonth.get(i).getClock());
            xListMonth.add(temp);
        }
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String data = xListMonth.get((int)value%xListMonth.size());
                return data;
            }
        });
        //设置X轴的分割数
        xAxis.setLabelCount(5,false);
        //对Y轴进行相关的设计
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //设置YZ轴的最值
        //leftYAxis.setAxisMinimum(0f);
        //leftYAxis.setAxisMaximum(40f);
        //rightYaxis.setAxisMinimum(0f);
        //rightYaxis.setAxisMaximum(1200f);
        //把XY轴自己的网格线禁掉
        rightYaxis.setDrawGridLines(false);
        leftYAxis.setDrawGridLines(false);
        //把网格背景线设置成为虚线
        leftYAxis.enableGridDashedLine(10f,10f,0f);
        rightYaxis.enableGridDashedLine(10f,10f,0f);
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
        legend.setFormToTextSpace(15f);
        //对图例中的标签设置是否换行
        legend.setWordWrapEnabled(true);
        //隐藏X轴描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        //点击图表坐标监听
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (lineChart.isDrawMarkersEnabled()) {
                    createMakerView();
                    lineChart.highlightValue(h);
                }
            }
            @Override
            public void onNothingSelected() {
            }
        });
        createMakerView();
    }
    //15、对数据进行解析----查询周期为月
    public  void parseJSONWithGsonMonth(String responseData){
        Gson gson = new Gson();
        //对json格式的数据进行解析，然后存在list集合中
        List<EAFiveMinuteParameter> fiveMinuteList = gson.fromJson(responseData,
                new TypeToken<List<EAFiveMinuteParameter>>(){}.getType());
        //遍历list结合，把解析的数据传入到eaFiveMinutesList
        if (firstListMonth.size() > 0) {
            firstListMonth.clear();
        }
        if (secondListMonth.size() > 0) {
            secondListMonth.clear();
        }
        for(EAFiveMinuteParameter eaf: fiveMinuteList){
            EAFiveMinuteParameter myEaf = new EAFiveMinuteParameter(eaf.getId(),eaf.getClock(),eaf.getVal());
            //对不同的参数进行去值，放到相应的数组中
            if (Integer.parseInt(myEaf.getId()) == 16) {
                firstListMonth.add(myEaf);
                //xList.add(myEaf.getClock());
            }
            //获得单位乙烯综合能耗的数据集
            if (Integer.parseInt(myEaf.getId()) == 17) {
                secondListMonth.add(myEaf);
            }
        }
        //对数据进行打印
        print(firstListMonth,secondListMonth,"月");
        //图表的初始化设置
        initChartMonth(lineChart);
        //曲线的展示
        showLineChart(firstListMonth,secondListMonth);
    }

    //16、对firstList进行打印输出
    private  void print(List<EAFiveMinuteParameter> first,List<EAFiveMinuteParameter> second,String temp){
        for(int i = 0 ;i < first.size();i++){
            Log.d("print函数",first.get(i).getId()+"  "+first.get(i).getVal()+"  "+first.get(i).getClock());
        }
        Log.d("first"," "+first.size()+" second.size():"+ second.size()+"查询周期为："+temp);
        Log.d("first and second 的分割线","*******************************");
        for(int i = 0;i<second.size();i++){
            Log.d("print函数",second.get(i).getId()+"  "+second.get(i).getVal()+"  "+second.get(i).getClock());
        }
    }
    //17、字符串到日期的转换 "2010-05-04 12:34:23"得到12:34:23
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
    //18、字符串到日期的转换 "2010-05-04 12:34:23"得到2010-05-04
    private  String stringToDataYear(String str){
        String time = null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(str);
            time = String.format("%tF",date);
        }catch(Exception e){
            e.printStackTrace();
            Log.d("字符串转换到日期出错","第七个函数");
        }
        return time;
    }

    //19、手动刷新的具体逻辑
    private void refreshData(){
        initData();
        /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.chart_rg_tour:
                        initData();
                        break;
                    case R.id.chart_rg_day:
                        initDataDay();
                        break;
                    case R.id.chart_rg_week:
                        initWeekData();
                        //Toast.makeText(EADataChartShow.this,"您选中的查看周期为每周",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chart_rg_month:
                        initMonthData();
                        //Toast.makeText(EADataChartShow.this,"您选中的查看周期为每月",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chart_rg_quarter:
                        Toast.makeText(EADataChartShowDemo.this,"您选中的查看周期为每季度",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chart_rg_year:
                        Toast.makeText(EADataChartShowDemo.this,"您选中的查看周期为每年",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(EADataChartShowDemo.this,"传入的数据有错误",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });*/
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(this,"数据更新成功",Toast.LENGTH_SHORT).show();
    }
    //20、注销定时器
    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroy();
    }

}
