package com.peemes.android.energyAssess;

/**
 * Created by cshao on 2018/11/18.
 */
//每五分钟就更新一次的实体类，用来作为ListView的适配器类型，用EAFiveMinuteParameter和EnergyAssessParameter来初始化
    //取EAFiveMinuteParameter中的变量值，其余的字段名名用EnergyAssessParameter的相关参数来初始化
public class EAFive {
    private String name;
    private String time;
    private String val;
    private String uom;

    public EAFive(String name, String time, String val, String uom) {
        this.name = name;
        this.time = time;
        this.val = val;
        this.uom = uom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
}
