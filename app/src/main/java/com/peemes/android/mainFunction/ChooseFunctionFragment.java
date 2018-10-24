package com.peemes.android.mainFunction;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.peemes.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cshao on 2018/10/22.
 */

public class ChooseFunctionFragment extends Fragment {
    public static final int LEVEL_MAINFUNCTION = 0;
    public static final int LEVEL_SUNMAINFUNCTION = 1;
    public static final int LEVEL_DETAILEDFUNCTION = 2;
    private ProgressDialog progressDialog;
    private TextView textTitle;
    private Button buttonBack;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();
    private String [] mainFunctionList = {"参数监测","能效评估","指标基准值","折能参数","工艺全貌",
    "流程监控","系统管理","生产计划","退出系统"};//主要功能列表
    private String [] parameterMonitorList = {""};   // 参数前侧功能列表
    private List<String> systemOneIndexList;     //系统级一次指标列表
    //选中的主要功能
    private String selectMainFunction;
    //选中的主要功能下面的某个子功能
    private String selectSubMainFunction;
    //选中的三级功能
    private String selectDetailedFunction;
    //选中的级别
    private int currentLevel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_real,container,false);
        textTitle = (TextView)view.findViewById(R.id.main_text_title);
        buttonBack = (Button)view.findViewById(R.id.main_button_back);
        listView = (ListView)view.findViewById(R.id.main_listView);
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //往主要功能的列表中加入具体的功能选项

                if (currentLevel == LEVEL_MAINFUNCTION) {
                  // selectMainFunction = mainFunctionList.get(position);
                    //查找选中的功能的主要的子功能

                }
            }
        });
    }
    //往主要功能列表中加入具体有哪些功能
    private void quaryMainFunction(){
       /// mainFunctionList.add("参数监测");
        //mainFunctionList.add("能效评估");
        //mainFunctionList.add("指标基准值");
       // mainFunctionList.add()
    }
    //往第二级功能中加入具体的功能
    private void quarySubMainFunction(){

    }
}
