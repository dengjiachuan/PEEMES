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

public class EAFiveJilengAdapter extends ArrayAdapter<EAFive> {
    private int resourceId;
    //向下一个活动传送相关的参数的图片ID
    public int [] pictureID = {R.mipmap.uni_elec,R.mipmap.uni_elec,R.mipmap.uni_elec,R.mipmap.uni_elec,R.mipmap.uni_elec,
            R.mipmap.uni_elec,R.mipmap.uni_elec,R.mipmap.uni_elec,R.mipmap.uni_elec};
    //向下一个活动传送文字说明
    String word12 = "重燃料油汽提塔C-1230蒸汽减黏消耗高压蒸汽/乙烯总量";
    String word13 = "轻燃料油汽提塔C-1240消耗稀释蒸汽/乙烯总量";
    String word14 = "稀释蒸汽发生中压蒸气使用量/乙烯总量";
    String word15 = "C1210急冷油泵消耗HS(单位：t/h) /乙烯总量";
    String word16 = "C1210盘油泵消耗HS(单位：t/h)/乙烯总量";
    String word17 = "急冷水塔水油分离泵P1220分离出来的汽油/乙烯总量";
    String word18 = "P1206泵消耗MS/乙烯总量";
    String word19 = "急冷区消耗能源";
    String word20 = "急冷区综合能耗/乙烯总量";
    public String [] word = {word12,word13,word14,word15,word16,word17,word18,word19,word20};
    //向下一个活动传送数学表达式
    String math12 = "FIC_12016*1.0Mpa蒸汽折能系数/(FIQ16060*折能系数+FIQ1606*折能系数)";
    String math13 = "FIC_12012*1.0Mpa蒸汽折能系数/(FIQ16060*折能系数+FIQ1606*折能系数)";
    String math14 = "(FI_12036+FI_12037)*1.0Mpa蒸汽折能系数/(FIQ16060*折能系数+FIQ1606*折能系数)";
    String math15 = "FI_12042*(3.5Mpa-1.0Mpa)蒸汽折能系数/(FIQ16060*折能系数+FIQ1606*折能系数)";
    String math16 = "FI_12043*(3.5Mpa-1.0Mpa)蒸汽折能系数/(FIQ16060*折能系数+FIQ1606*折能系数)";
    String math17 = "FIC_12025*(3.5Mpa-1.0Mpa)蒸汽折能系数/(FIQ16060*折能系数+FIQ1606*折能系数)";
    String math18 = "FI_12045*(1.0Mpa-0.4Mpa)蒸汽折能系数/(FIQ16060*折能系数+FIQ1606*折能系数)";
    String math19 = "(FI_12042+FI_12043+FI_12044)*(ZN_生活水AF-ZN_污水AR)+FIC_12016*ZN_生活水AF+" +
            "FI_12045*(ZN_1.0Mpa蒸汽-ZN_0.4Mpa蒸汽)+(FI_12036+FI_12037)*ZN_污水AR";
    String math20 = "(FI_12042+FI_12043+FI_12044)*(ZN_生活水AF-ZN_污水AR)+FIC_12016*ZN_生活水AF+FI_" +
            "12045*(ZN_1.0Mpa蒸汽-ZN_0.4Mpa蒸汽)+(FI_12036+FI_12037)*ZN_污水AR/(FIQ16060*折能系数+FIQ1606*折能系数)";
    public String [] math = {math12,math13,math14,math15,math16,math17,math18,math19,math20};
    public EAFiveJilengAdapter(Context context, int textViewRescourced, List<EAFive> objects) {
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
