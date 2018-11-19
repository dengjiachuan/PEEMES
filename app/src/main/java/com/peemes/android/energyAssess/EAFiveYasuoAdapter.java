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

public class EAFiveYasuoAdapter extends ArrayAdapter<EAFive> {
    private int resourceId;
    //向下一个活动传送相关的参数的图片ID
    public int [] pictureID = {R.mipmap.yiduanyasuobi,R.mipmap.erduanyasuobi,R.mipmap.yierduanjianyalijiang,
            R.mipmap.yierduanjianyalijiang,R.mipmap.sanduanyasuobi, R.mipmap.ersanduanjianyalijiang,
            R.mipmap.ersanduanjianyalijiang,R.mipmap.wuduanyasuobi,R.mipmap.zongyasuobi};
    //向下一个活动传送文字说明
    String word12 = "裂解气压缩机一段的压缩比，输出压力与输入压力的比值，反应一段压缩能力";
    String word13 = "裂解气压缩机二段的压缩比，输出压力与输入压力的比值，反应二段压缩能力";
    String word14 = "一二段间裂解气压力损失，反应能量损失";
    String word15 = "一二段间裂解气压力损失与一段出口压力的比值，反应压缩段气密性";
    String word16 = "裂解气压缩机三段的压缩比，输出压力与输入压力的比值，反应三段压缩能力";
    String word17 = "二三段间裂解气压力损失，反应能量损失";
    String word18 = "一二段间裂解气压力损失与一段出口压力的比值，反应压缩段气密性";
    String word19 = "裂解气压缩机五段的压缩比，输出压力与输入压力的比值，反应五段压缩能力";
    String word20 = "裂解气压缩机总压缩比，输出压力与输入压力的比值，反应压缩机压缩能力";
    public String [] word = {word12,word13,word14,word15,word16,word17,word18,word19,word20};
    //向下一个活动传送数学表达式
    String math12 = "一段压缩比=PI_13010/PI_13007";
    String math13 = "二段压缩比=PI_13017*1000/PI_13004";
    String math14 = "一二段间压力降=PI_13010-PI_13004";
    String math15 = "一二段间压力降=(PI_13010-PI_13004)/PI_13010*100%";
    String math16 = "三段压缩比=PI_13023/PI_13020";
    String math17 = "二三段间压力降=PI_13017-PI_13020";
    String math18 = "二三段间压力降=(PI_13017-PI_13020)/PI_13017*100%";
    String math19 = "一段压缩比=PI_13030/PI_13027";
    String math20 = "一段压缩比=PI_13030*1000/PI_13010";
    public String [] math = {math12,math13,math14,math15,math16,math17,math18,math19,math20};
    public EAFiveYasuoAdapter(Context context, int textViewRescourced, List<EAFive> objects) {
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

