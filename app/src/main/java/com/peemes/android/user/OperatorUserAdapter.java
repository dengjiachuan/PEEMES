package com.peemes.android.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.peemes.android.R;
import com.peemes.android.util.OperatorUser;

import java.util.List;

/**
 * Created by cshao on 2018/12/8.
 */

public class OperatorUserAdapter extends ArrayAdapter<OperatorUser> {
    private int resourceId;
    public OperatorUserAdapter(Context context, int textViewResourceId, List<OperatorUser> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OperatorUser operatorUser = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else {
            view = convertView;
        }
        //对子项布局中的控件进行注册
        TextView textViewId = (TextView)view.findViewById(R.id.operator_id);
        TextView textViewUserid = (TextView)view.findViewById(R.id.operator_userid);
        TextView textViewUserName = (TextView)view.findViewById(R.id.operator_username);
        TextView textViewTime = (TextView)view.findViewById(R.id.operator_time);
        TextView textViewOperator = (TextView)view.findViewById(R.id.operator_useroperator);
        textViewId.setText(String.valueOf(operatorUser.getId()));
        textViewUserid.setText(operatorUser.getUserid());
        textViewUserName.setText(operatorUser.getUsername());
        textViewTime.setText(operatorUser.getLoginTime());
        textViewOperator.setText(operatorUser.getOperator());
        return view;
    }
}
