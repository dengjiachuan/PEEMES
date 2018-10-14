package com.peemes.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by cshao on 2018/10/14.
 */

public class ZheNengXiShu extends DataSupport {
    private int id ;
    private String name;
    private double val;
    private String meaning;
    private String uom;

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
