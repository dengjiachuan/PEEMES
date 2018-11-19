package com.peemes.android.energyAssess;

/**
 * Created by cshao on 2018/11/12.
 */
//主要是把能效评估的所有参数的相关信息传入安卓端，然后可以针对这些数据进行相应的编程。
    //该类是为一个实体类，作为安卓端传输的数据的实体类；
public class EnergyAssessParameter {
    private String id;
    private String name;
    private String cal_meth;
    private String meaning;
    private String uom;

    public EnergyAssessParameter(String id, String name, String cal_meth, String meaning, String uom) {
        this.id = id;
        this.name = name;
        this.cal_meth = cal_meth;
        this.meaning = meaning;
        this.uom = uom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCal_meth() {
        return cal_meth;
    }

    public void setCal_meth(String cal_meth) {
        this.cal_meth = cal_meth;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
}
