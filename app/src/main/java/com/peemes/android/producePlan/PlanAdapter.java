package com.peemes.android.producePlan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.peemes.android.R;

import java.util.List;

/**
 * Created by cshao on 2018/12/9.
 */

public class PlanAdapter extends ArrayAdapter<PlanTable>{
    private int resourceId;
    public PlanAdapter(Context context, int textViewResource, List<PlanTable> objects){
        super(context,textViewResource,objects);
        resourceId = textViewResource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlanTable planTable = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else {
            view = convertView;
        }
        //对子项控件进行注册
        TextView textViewPriject = (TextView)view.findViewById(R.id.plan_project);
        TextView textViewUom = (TextView)view.findViewById(R.id.plan_uom);
        TextView textViewYeild = (TextView)view.findViewById(R.id.plan_get);
        TextView textViewNumber = (TextView)view.findViewById(R.id.plan_number);
        //对子项控件进行填充
        textViewPriject.setText(planTable.getProject());
        textViewUom.setText(planTable.getUom());
        textViewNumber.setText(planTable.getNumber());
        textViewYeild.setText(planTable.getYield());
        return view;
    }
}
