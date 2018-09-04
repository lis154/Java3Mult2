package com.company;

/**
 * Created by i.lapshinov on 04.09.2018.
 */
public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}
