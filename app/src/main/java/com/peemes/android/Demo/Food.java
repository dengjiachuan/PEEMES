package com.peemes.android.Demo;

/**
 * Created by cshao on 2018/10/22.
 */

public class Food {
    private String name;
    private int id;

    public Food(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
