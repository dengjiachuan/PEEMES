package com.peemes.android.monitorParameter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.peemes.android.R;
import com.peemes.android.ZheNengCoefficient.ParameterAdapter;

import java.util.List;

/**
 * Created by cshao on 2018/11/5.
 */

public class ParameterMontorAdapter extends ArrayAdapter<ParameterMontor> {
    private int resourceID;
    public ParameterMontorAdapter(Context context, int textViewResourceId, List<ParameterMontor> objects){
        super(context,textViewResourceId,objects);
        resourceID = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ParameterMontor parameterMontor = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        }else {
            view = convertView;
        }
        //注册五个TextView;
        TextView textView_name = (TextView)view.findViewById(R.id.item_textView_name);
        TextView textView_location = (TextView)view.findViewById(R.id.item_textView_location);
        TextView textView_val = (TextView)view.findViewById(R.id.item_textView_val);
        TextView textView_uom = (TextView)view.findViewById(R.id.item_textView_uom);
        TextView textView_time = (TextView)view.findViewById(R.id.item_textView_time);
        textView_name.setText(parameterMontor.getName());
        textView_location.setText(parameterMontor.getLocation());
        textView_val.setText(parameterMontor.getVal());
        textView_uom.setText(parameterMontor.getUom());
        textView_time.setText(parameterMontor.getTime());
        return view;
    }
}
