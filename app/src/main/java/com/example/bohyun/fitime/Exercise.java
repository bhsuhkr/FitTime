package com.example.bohyun.fitime;
/**
 * Created by zschr on 11/28/2017.
 */

public class Exercise {

    String name, description, type;

    public Exercise(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}