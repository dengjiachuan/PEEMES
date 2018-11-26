package com.peemes.android.energyAssess;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * Created by cshao on 2018/11/25.
 */

public class CustomValueFormatter implements IAxisValueFormatter {
    private List<String> mList;
    public CustomValueFormatter(List<String> mList){
        this.mList = mList;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mList.get((int)value);
    }
}
