package com.peemes.android.demo;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.peemes.android.R;

import java.util.Date;
import java.util.List;

/**
 * Created by cshao on 2018/12/4.
 */

public class ChartMarkView extends MarkerView {
    private TextView textViewDate;
    private TextView textViewValue;
    private IAxisValueFormatter iAxisValueFormatter;
    public ChartMarkView(Context context,IAxisValueFormatter iAxisValueFormatter){
        super(context, R.layout.detail_markview);
        this.iAxisValueFormatter = iAxisValueFormatter;

        textViewDate = (TextView)findViewById(R.id.markview_data);
        textViewValue = (TextView)findViewById(R.id.markview_value);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String realX = iAxisValueFormatter.getFormattedValue(e.getX(),null);
        Date date = new Date();
        String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-" +
                "(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|(((" +
                "[0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        String time1 = String.format("%tF",date);//年份
        String time2 = String.format("%tT",date);//具体的时间
        if (realX.matches(regex)) {
            textViewDate.setText("Date： "+realX +"  " +time2);
        }else{
            textViewDate.setText("Date:  "+time1+" "+realX);
        }
        Chart chart = getChartView();
        if (chart instanceof LineChart) {
            LineData lineData = ((LineChart) chart).getLineData();
            List<ILineDataSet> dataSetList = lineData.getDataSets();
            for(int i = 0; i<dataSetList.size();i++){
                LineDataSet dataSet = (LineDataSet)dataSetList.get(i);
                float y = dataSet.getValues().get((int)e.getX()).getY();
                textViewValue.setText(dataSet.getLabel()+":  "+y);
            }
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth()/2),-getHeight());
    }
}
