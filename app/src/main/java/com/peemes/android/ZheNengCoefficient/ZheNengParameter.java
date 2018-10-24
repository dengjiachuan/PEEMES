package com.peemes.android.ZheNengCoefficient;

/**
 * Created by cshao on 2018/10/24.
 */

public class ZheNengParameter {
    private int id;
    private String name;
    private double val;
    private String uom;

    public ZheNengParameter(int id, String name, double val, String uom) {
        this.id = id;
        this.name = name;
        this.val = val;
        this.uom = uom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
}
