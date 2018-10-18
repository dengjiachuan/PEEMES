package com.peemes.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by cshao on 2018/10/16.
 */

public class IndexStandardVal extends DataSupport {
    private String ID;
    private String val;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
