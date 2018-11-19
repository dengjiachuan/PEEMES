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

public class EAFiveShebeiAdapter extends ArrayAdapter<EAFive> {
    private int resourceId;
    //向下一个活动传送相关的参数的图片ID
    private int [] pictureID = {R.mipmap.fenlizonghenenghao,R.mipmap.fenliyixinengxiao,R.mipmap.fenlishuangxinengxiao,
            R.mipmap.ranliaozhuanhualv,R.mipmap.yixihegelv, R.mipmap.c2c3fenli};
    //向下一个活动传送文字说明
    private String word12 = "裂解炉1能效";
    private String word13 = "裂解炉1超高压蒸汽能量回收率";
    private String word14 = "裂解炉1热效率";
    private String word15 = "裂解炉1综合能耗";
    private String word16 = "裂解炉1净综合能耗";
    private String word17 = "裂解炉1锅炉给水能量回收率";
    private String [] word = {word12,word13,word14,word15,word16,word17};
    //向下一个活动传送数学表达式
    private String math12 = "净设备能耗/原料加工量";
    private String math13 = "FI_111001*蒸汽折能系数/FI_111409*燃料折能系数";
    private String math14 = "1-损失能量/提供能量";
    private String math15 = "FI_111409*燃料折能系数+(FIC_111104+FIC_111204+FIC_111304+FIC_111404)*56";
    private String math16 = "综合能耗-FI_111001*蒸汽折能系数";
    private String math17 = "锅炉给水回收能量/提供能量";
    private String [] math = {math12,math13,math14,math15,math16,math17};
    public EAFiveShebeiAdapter(Context context, int textViewRescourced, List<EAFive> objects) {
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
