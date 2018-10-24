package com.peemes.android.util;

import org.litepal.LitePal;

/**
 * Created by cshao on 2018/10/20.
 */
//对指标基准值表格的相关操作
public class OperatorIndexStandardVal {
    //升级和创建一个数据库表
    public void updataAndCreate(){
        LitePal.getDatabase();
    }
    //网数据库中加入数据
    /*
    * 正常情况下应该是通过网络通信，把 数据库中的数据直接读取出来，存入数据库中
    * 此处直接把数据库中的相关数据取出来，手动加在安卓端的数据库中*/
    public void addDataToIndexStandardVal(){
        //因为只有两行数据，用Map结合来做。
    }
}
