package com.peemes.android.producePlan;

import org.litepal.crud.DataSupport;

/**
 * Created by cshao on 2018/12/9.
 */

public class PlanTable extends DataSupport{
    private int id;
    private String project;
    private String uom;
    private String yield;
    private String number;

    public PlanTable(String project, String uom, String yield, String number) {
        this.project = project;
        this.uom = uom;
        this.yield = yield;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
