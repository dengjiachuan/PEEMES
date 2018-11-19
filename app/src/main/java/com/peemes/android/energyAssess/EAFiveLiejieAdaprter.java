package com.peemes.android.energyAssess;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.peemes.android.R;

import java.util.List;

/**
 * Created by cshao on 2018/11/19.
 */
//每五分钟更新的裂解过程级分析的适配器类
public class EAFiveLiejieAdaprter extends ArrayAdapter<EAFive> {
    private int resourceId;
    //向下一个活动传送相关的参数的图片ID
    public int [] pictureID = {R.mipmap.lj_efficy,R.mipmap.lj_steam,R.mipmap.lj_hot,R.mipmap.lj_consume,
            R.mipmap.lj_energy,R.mipmap.lj_water};
    //向下一个活动传送文字说明
    String word4 = "[燃料气AQ+燃料气LPG+天然气NG+燃料气FG+稀释蒸汽流量-(1.0Mpa蒸汽AS+0.4Mpa蒸汽AT+1.0Mpa" +
            "蒸汽AH+3.5Mpa蒸汽AG+0.4Mpa蒸汽AI)]/(乙烯生产总量)";
    String word5 = "(1.0Mpa蒸汽AS+0.4Mpa蒸汽AT+1.0Mpa蒸汽AH+3.5Mpa蒸汽AG+0.4Mpa蒸汽AI)/(燃料气AQ+燃料气LPG+天然气NG+燃料气FG)";
    String word6 = "(1号裂解炉至8号裂解炉利用能量值累加和)/(1号裂解炉至8号裂解炉供给能量值累加和)";
    String word7 = "(燃料气AQ+燃料气LPG+天然气NG+燃料气FG)+稀释蒸汽流量";
    String word8 = "(燃料气AQ+燃料气LPG+天然气NG+燃料气FG)+稀释蒸汽流量-(1.0Mpa蒸汽AS+0.4Mpa蒸汽AT+1.0Mpa蒸汽" +
            "AH+3.5Mpa蒸汽AG+0.4Mpa蒸汽AI)";
    String word9 = "比热容系数*(1至8号锅炉给水质量流量*温度差的和)/(燃料气AQ+燃料气LPG+天然气NG+燃料气FG)";
    public String [] word = {word4,word5,word6,word7,word8,word9};
    //向下一个活动传送数学表达式
    String math4 = "((FIQ19002+FIQ19007+FIQ19006+FIQ19001)*ZN_燃料+F_DS*ZN_DS-(FIQ19103+FIQ19105+FIQ19120+FIQ19102+FIQ19122)" +
            "*ZN_蒸汽 ))/(FIQ16060+FIQ16064)";
    String math5 = "(((FIQ19103+FIQ19105+FIQ19120+FIQ19102+FIQ19122)*ZN_蒸汽/(FIQ19002+FIQ19007+FIQ19006+FIQ19001)*ZN_燃料";
    String math6 = "(p1*FI111409+...+p8*FI118409)/(FI111409+...+FI118409)";
    String math7 = "(FIQ19002+FIQ19007+FIQ19006+FIQ19001)*ZN_蒸汽+FDS*ZN_DS";
    String math8 = "(FIQ19002+FIQ19007+FIQ19006+FIQ19001)*ZN_蒸汽+F_DS*ZN_DS-" +
            "(FIQ19103+FIQ19105+FIQ19120+FIQ19102+FIQ19122)*ZN_蒸汽";
    String math9 = "(1.82545*((FIC_111004+FIC_111005)*(TI_111030-TI_111009)+...+(FIC_118004+FIC_118005)*(TI_118030-" +
            "TI_118009))/((FIQ19002+FIQ19007+FIQ19006+FIQ19001)*ZN_燃料)";
    public String [] math = {math4,math5,math6,math7,math8,math9};
    public EAFiveLiejieAdaprter(Context context, int textViewRescourced, List<EAFive> objects) {
        super(context, textViewRescourced, objects);
        resourceId = textViewRescourced;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final EAFive eaFive = getItem(position);
        final View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else
        {
            view = convertView;
        }
        //对子项的五个控件进行注册，以获取实例
        TextView textViewName = (TextView)view.findViewById(R.id.eaItem_name);
        TextView textViewTime = (TextView)view.findViewById(R.id.eaItem_time);
        TextView textViewVal = (TextView)view.findViewById(R.id.eaItem_val);
        TextView textViewUom = (TextView)view.findViewById(R.id.eaItem_uom);
        Button buttonDetail = (Button) view.findViewById(R.id.eaItem_button);
        textViewName.setText(eaFive.getName());
        textViewTime.setText(eaFive.getTime());
        textViewVal.setText(eaFive.getVal());
        textViewUom.setText(eaFive.getUom());
        buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //往下一个活动传送相关数据
                Intent intent = new Intent(view.getContext(),EASystemDetailActivity.class);
                //传递题目
                intent.putExtra("title",eaFive.getName());
                //传递图片ID
                intent.putExtra("pictureID",pictureID[position]);
                //传递文字说明
                intent.putExtra("word",word[position]);
                //传递数学表达式
                intent.putExtra("math",math[position]);
                view.getContext().startActivity(intent);
            }
        });
        return view;
    }
}
