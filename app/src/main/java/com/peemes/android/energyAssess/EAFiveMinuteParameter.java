package com.peemes.android.energyAssess;

/**
 * Created by cshao on 2018/11/13.
 */

public class EAFiveMinuteParameter {
    private String id;
    private String clock;
    private String val;

    public EAFiveMinuteParameter(String id, String clock, String val) {
        this.id = id;
        this.clock = clock;
        this.val = val;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
