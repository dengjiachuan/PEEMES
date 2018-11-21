package com.peemes.android.energyAssess;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.peemes.android.R;

import java.util.List;

/**
 * Created by cshao on 2018/11/18.
 */
//每五分钟更新数据的适配器类
public class EAFiveSystemAdapter extends ArrayAdapter<EAFive>{
    private int resourceId;
    //传送给下一个活动的图片ID
    public int [] pictureID = {R.mipmap.c2c3fenli,R.mipmap.lj_energy,R.mipmap.uni_steam,R.mipmap.energy,R.mipmap.yield,
            R.mipmap.uni_energy,R.mipmap.uni_hot,R.mipmap.uni_water,R.mipmap.uni_steam,R.mipmap.uni_gas,R.mipmap.uni_elec};
    //传送给下一个的活动的文字说明
    String str1 = "乙烯生产过程整个系统的燃料消耗量";
    String str2 = "乙烯生产过程中整个系统投入的原料总量";
    String str3 = "整个系统级的乙烯生产总量";
    String str4 = "工业水AC+循环水AD+脱盐水AE+生活水AF+3.5Mpa蒸汽AG+仪表风AJ+工厂风AK+氮气AL+热水AP+" +
            "燃料气（LPG）AM+天然气（NG）AN+甲烷（C1410)+氢气(14065)AX+氢气（含转燃料）-污水AR-1.0Mpa蒸汽AS-" +
            "0.4Mpa蒸汽AT-蒸汽凝液AU-除氧水AV";
    String str5 = "(加氢裂化尾油+减一减顶油+石脑油+轻烃进料+加氢碳五（拔头油）+LPG进料)/(液相乙烯T+气相乙烯AW)";
    String str6 = "(综合能耗)/ (乙烯总量)";
    String str7 = "(燃料)/ (乙烯总量)";
    String str8 = "(工业水AC+循环水AD+脱盐水AE+生活水AF+污水AR+除氧水AV)/(乙烯总量)";
    String str9 = "(3.5Mpa蒸汽AG+1.0Mpa蒸汽AS+0.4Mpa蒸汽AT+蒸汽凝液AU)/(乙烯总量)";
    String str10 = "(仪表风AJ+工厂风AK+氮气AL)/(乙烯总量)";
    String str11 = "(电)/(乙烯总量)";
    public String [] word = {str1,str2,str3,str4,str5,str6,str7,str8,str9,str10,str11};
    //传送给下一个活动的表达式
    String math1 = "FIQ14030+FIQ14081+FIQ19006+FIQ14063-FIQ007-FIQ19002-FIQ14065";
    String math2 = "FIQ_110001+FIQ_110007+FIQ_110003+FIQ_110002+FIQ_110006+FIQ_19007";
    String math3 = "FIQ_16060+FIQ_16064";
    String math4 = "FIQ19113*折能系数+FIQ19107*折能系数+FIQ19114*折能系数+FIQ19115*折能系数+FIQ19102*折能系数+FIQ19110" +
            "*折能系数+FIQ19109*折能系数+FIQ19108*折能系数+FIQ19116*折能系数+（FIQ14030+FIQ14081+FIQ19006+FIQ19007+FIQ140" +
            "63）*折能系数-FIQ19106*折能系数-FIQ19030*折能系数-FIQ19105*折能系数-FIQ19103*折能系数-FIQ19045*折能系数";
    String math5 = "(FIQ110001*折能系数+FIQ110007*折能系数+FIQ110003*折能系数+FIQ110002*折能系数+FIQ110006*折能系数+" +
            "FIQ110007*折能系数)/ (FIQ16060*折能系数+FIQ16064*折能系数)";
    String math6 = "(FIQ19113*折能系数+FIQ19107*折能系数+FIQ19114*折能系数+FIQ19115*折能系数+FIQ19102*折能系数+FIQ19110*" +
            "折能系数+FIQ19109*折能系数+FIQ19108*折能系数+FIQ19116*折能系数+（FIQ14030+FIQ14081+FIQ19006+FIQ19007+FIQ14063）" +
            "*折能系数-FIQ19106*折能系数-FIQ19030*折能系数-FIQ19105*折能系数-FIQ19103*折能系数-FIQ19045*折能系数)/ " +
            "(FIQ16060*折能系数+FIQ16064*折能系数)";
    String math7 = "(FIQ14030*折能系数+FIQ1408*折能系数)/ (FIQ16060*折能系数+FIQ16064*折能系数)";
    String math8 = "(FIQ19113*折能系数+FIQ19107*折能系数+FIQ19114*折能系数+FIQ19115*折能系数+FIQ19045*" +
            "折能系数+FIQ19106*折能系数)/(FIQ16060*折能系数+FIQ1606*折能系数)";
    String math9 = "(FIQ19102*折能系数+FIQ19103*折能系数+FIQ19105*折能系数+FIQ19030*折能系数)/" +
            "(FIQ16060*折能系数+FIQ1606*折能系数)";
    String math10 = "(FIQ19110*折能系数+FIQ19108*折能系数+FIQ19108*折能系数)/(FIQ16060*折能系数+FIQ1606*折能系数)";
    String math11 = "(129500*折能系数)/(FIQ16060*折能系数+FIQ1606*折能系数)";
    public String [] math = {math1,math2,math3,math4,math5,math6,math7,math8,math9,math10,math11};
    public EAFiveSystemAdapter(Context context, int textViewRescourced, List<EAFive> objects){
        super(context,textViewRescourced,objects);
        resourceId = textViewRescourced;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final EAFive eaFive = getItem(position);
        final View view ;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else {
            view = convertView;
        }
        TextView textViewName = (TextView)view.findViewById(R.id.eaItem_name);
        TextView textViewTime = (TextView)view.findViewById(R.id.eaItem_time);
        TextView textViewVal = (TextView)view.findViewById(R.id.eaItem_val);
        TextView textViewUom = (TextView)view.findViewById(R.id.eaItem_uom);
        Button textViewButton = (Button) view.findViewById(R.id.eaItem_button);
        textViewName.setText(eaFive.getName());
        textViewTime.setText(eaFive.getTime());
        textViewVal.setText(eaFive.getVal());
        textViewUom.setText(eaFive.getUom());
        textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EAFiveSystemAdapter","点击单个按钮有相应的反应，可以完成页面跳转的功能");
                Intent intent = new Intent(view.getContext(),EASystemDetailActivity.class);
                Log.d("position is :"," "+position);
                //传递题目
                intent.putExtra("title",eaFive.getName());
                //传递图片ID
                intent.putExtra("pictureID",pictureID[position]);
                //传递文字说明
                intent.putExtra("word",word[position]);
                //传递数学表达式
                intent.putExtra("math",math[position]);
                view.getContext().startActivity(intent);
                if (EAFiveSystemActivity.eaFiveList.size()>0) {
                    EAFiveSystemActivity.eaFiveList.clear();
                }
            }
        });
        return view;
    }
}
