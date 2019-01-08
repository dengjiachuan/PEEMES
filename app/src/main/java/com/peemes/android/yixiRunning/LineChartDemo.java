package com.peemes.android.yixiRunning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.peemes.android.MainActivity;
import com.peemes.android.R;
import com.peemes.android.energyAssess.GetEAFiveMinute;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

import static com.peemes.android.energyAssess.GetEAFiveMinute.eaFiveMinutesListParameter;

/**
 * Created by cshao on 2018/11/11.
 */

public class LineChartDemo extends AppCompatActivity {
    private LineChartView lineChart;
    private Button buttonBack;
    private TextView textViewTital;
    String[] weeks = new String[10];//X轴的标注
    float [] weather = new float[10];//图表的数据


    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisValues = new ArrayList<AxisValue>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        lineChart = (LineChartView) findViewById(R.id.total_chart_line);
        buttonBack = (Button)findViewById(R.id.total_button_title_back);
        textViewTital = (TextView)findViewById(R.id.total_textView_title_text);
        showTitle();
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LineChartDemo.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //隐藏掉标题
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        initArray();
        printArray();
      //  getAxisLables();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化

    }
    private void showTitle(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewTital.setText("乙烯车间运行情况");
            }
        });
    }
    private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.WHITE).setCubic(false);  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setCubic(true);//曲线是否平滑
        line.setFilled(false);//是否填充曲线的面积
		line.setHasLabels(true);//曲线的数据坐标是否加上备注
        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(Color.WHITE);  //设置字体颜色
        axisX.setTextSize(14);
        axisX.setName("未来几天的天气");//表格名称
        axisX.setTextSize(7);//设置字体大小
        axisX.setMaxLabelChars(7);  //最多几个X轴坐标
        axisX.setValues(mAxisValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
//	    data.setAxisXTop(axisX);  //x 轴在顶部

        Axis axisY = new Axis();  //Y轴
        axisY.setMaxLabelChars(7); //默认是3，只能看最后三个数字
        axisY.setName("温度");//y轴标注
        axisY.setTextSize(7);//设置字体大小

        data.setAxisYLeft(axisY);  //Y轴设置在左边
//	    data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
    }
    private void getAxisLables(){
        for (int i = 0; i < weeks.length; i++) {
            mAxisValues.add(new AxisValue(i).setLabel(weeks[i]));
        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints(){
        for (int i = 0; i < weather.length; i++) {
            mPointValues.add(new PointValue(i, weather[i]));
        }
    }
    private void initArray(){
        //for( EAFiveMinuteParameter listParameter : GetEAFiveMinute.eaFiveMinutesListParameter){
        GetEAFiveMinute.initEaFiveMinutesList();
        GetEAFiveMinute.print();
        for(int i = 0; i< eaFiveMinutesListParameter.size(); i++){
            if (Integer.parseInt(eaFiveMinutesListParameter.get(i).getId()) == 17) {
                weeks[i] = eaFiveMinutesListParameter.get(i).getClock();
                weather[i] = Integer.parseInt(eaFiveMinutesListParameter.get(i).getVal());
            }
        }
    }
    private void printArray(){
        for(int i = 0; i<weeks.length;i++){
            Log.d("X轴的值",weeks[i]);
            Log.d("Y轴的值",String.valueOf(weather[i]));
        }
    }
}
/*
* List<PointValue> pointValues = new ArrayList<PointValue>();// 节点数据结合
        Axis axisY = new Axis().setHasLines(true);// Y轴属性
        Axis axisX = new Axis();// X轴属性
        axisY.setName("温度");//设置Y轴显示名称
        axisX.setName("时间");//设置X轴显示名称
        ArrayList<AxisValue> axisValuesX = new ArrayList<AxisValue>();//定义X轴刻度值的数据集合
        ArrayList<AxisValue> axisValuesY = new ArrayList<AxisValue>();//定义Y轴刻度值的数据集合
        axisX.setValues(axisValuesX);//为X轴显示的刻度值设置数据集合
        axisX.setLineColor(Color.BLACK);// 设置X轴轴线颜色
        axisY.setLineColor(Color.BLACK);// 设置Y轴轴线颜色
        axisX.setTextColor(Color.RED);// 设置X轴文字颜色
        axisY.setTextColor(Color.RED);// 设置Y轴文字颜色
        axisX.setTextSize(14);// 设置X轴文字大小
        axisX.setTypeface(Typeface.DEFAULT);// 设置文字样式，此处为默认
        axisX.setHasTiltedLabels(false);// 设置X轴文字向左旋转45度
        axisX.setHasLines(false);// 是否显示X轴网格线
        axisY.setHasLines(false);// 是否显示Y轴网格线
        axisX.setHasSeparationLine(true);// 设置是否有分割线
        axisX.setInside(false);// 设置X轴文字是否在X轴内部

        for (int j = 0; j < 30; j+=5) {//循环为节点、X、Y轴添加数据
            axisValuesY.add(new AxisValue(j).setValue(j));// 添加Y轴显示的刻度值
        }
        for (int i=0; i < timePoint.length; i++){
            axisValuesX.add(new AxisValue(i).setLabel(timePoint[i]+"H"));
        }

        for (int i=0;i < tempPoint.size(); i++){
            pointValues.add(new PointValue(i,Float.parseFloat(tempPoint.get(i))));
        }

        List<Line> lines = new ArrayList<Line>();//定义线的集合
        Line line = new Line(pointValues);//将值设置给折线
        line.setColor(Color.RED);// 设置折线颜色
        line.setStrokeWidth(1);// 设置折线宽度
        line.setFilled(false);// 设置折线覆盖区域是否填充
        line.setCubic(false);// 是否设置为立体的
        line.setPointColor(Color.RED);// 设置节点颜色
        line.setPointRadius(5);// 设置节点半径
        line.setHasLabels(true);// 是否显示节点数据
        line.setHasLines(true);// 是否显示折线
        line.setHasPoints(true);// 是否显示节点
        line.setShape(ValueShape.CIRCLE);// 节点图形样式 DIAMOND菱形、SQUARE方形、CIRCLE圆形
        line.setHasLabelsOnlyForSelected(false);// 隐藏数据，触摸可以显示
        lines.add(line);// 将数据集合添加线

        LineChartData chartData = new LineChartData(lines);//将线的集合设置为折线图的数据
        chartData.setAxisYLeft(axisY);// 将Y轴属性设置到左边
        chartData.setAxisXBottom(axisX);// 将X轴属性设置到底部
        chartData.setBaseValue(20);// 设置反向覆盖区域颜色
        chartData.setValueLabelBackgroundAuto(false);// 设置数据背景是否跟随节点颜色
        chartData.setValueLabelBackgroundColor(Color.BLUE);// 设置数据背景颜色
        chartData.setValueLabelBackgroundEnabled(false);// 设置是否有数据背景
        chartData.setValueLabelsTextColor(Color.BLACK);// 设置数据文字颜色
        chartData.setValueLabelTextSize(15);// 设置数据文字大小
        chartData.setValueLabelTypeface(Typeface.MONOSPACE);// 设置数据文字样式
        lineChartView.setLineChartData(chartData);

        lineChartView.setZoomEnabled(true);//设置是否支持缩放
        lineChartView.setInteractive(true);//设置图表是否可以与用户互动
        lineChartView.setZoomType(ZoomType.HORIZONTAL);

        Viewport v = new Viewport(lineChartView.getMaximumViewport());
        v.left = 0;
        v.right= 7;
        v.bottom= 1;
        v.top= 30;
        lineChartView.setCurrentViewport(v);
*/