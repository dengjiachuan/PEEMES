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

public class EAFiveFenliAdapter extends ArrayAdapter<EAFive> {
    private int resourceId;
    //向下一个活动传送相关的参数的图片ID
    private int [] pictureID = {R.mipmap.fenlizonghenenghao,R.mipmap.fenliyixinengxiao,R.mipmap.fenlishuangxinengxiao,
            R.mipmap.ranliaozhuanhualv,R.mipmap.yixihegelv, R.mipmap.c2c3fenli};
    //向下一个活动传送文字说明
    private String word12 = "分离区综合能耗";
    private String word13 = "分离过程乙烯能效";
    private String word14 = "分离区双烯能效";
    private String word15 = "燃料气转化率";
    private String word16 = "乙烯合格率";
    private String word17 = "C2、C3分离度";
    private String [] word = {word12,word13,word14,word15,word16,word17};
    //向下一个活动传送数学表达式
    private String math12 = "FFIC_15003/1000*氢气折能系数+(FI_15002+FIC_15008+FIC_15016+FIC_15020)*中压压蒸汽折能系数";
    private String math13 = "分离区综合能耗/乙烯产量";
    private String math14 = "分离区综合能耗/（乙烯产量+FIQ_15014）";
    private String math15 = "(FIC_14022+FIC_14030+FI_14081+FI_14065)/分离区综合能耗";
    private String math16 = "[1-FIC_13006/(乙烯产量+FIC_130060)]*100%";
    private String math17 = "[1-FIC_14037/(FIC_13048+FIC_13047)]*100%";
    private String [] math = {math12,math13,math14,math15,math16,math17};
    public EAFiveFenliAdapter(Context context, int textViewRescourced, List<EAFive> objects) {
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
