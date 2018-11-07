package com.peemes.android.monitorParameter;

/**
 * Created by cshao on 2018/11/5.
 */

public class ParameterMontor {
    private String name;
    private String location;
    private String val;
    private String uom;
    private String time;

    public ParameterMontor(String name, String location, String val, String uom, String time) {
        this.name = name;
        this.location = location;
        this.val = val;
        this.uom = uom;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
