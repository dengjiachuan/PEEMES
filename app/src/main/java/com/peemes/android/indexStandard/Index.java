package com.peemes.android.indexStandard;

/**
 * Created by cshao on 2018/11/7.
 */

public class Index {
    private String name;
    private String val;
    private String uom;
    private String ID;

    public Index(String name, String val, String uom, String id) {
        this.name = name;
        this.val = val;
        this.uom = uom;
        this.ID = id;
    }


    public String getId() {
        return ID;
    }

    public void setId(String id) {
        this.ID = id;
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
}
