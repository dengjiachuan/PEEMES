package com.peemes.android.ZheNengCoefficient;

/**
 * Created by cshao on 2018/10/24.
 */

public class ZheNengParameter {
    private String id;
    private String name;
    private String val;
    private String uom;
    private String meaning;

    public ZheNengParameter() {

    }

    public ZheNengParameter(String id, String name, String val, String uom) {
        this.id = id;
        this.name = name;
        this.val = val;
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

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
