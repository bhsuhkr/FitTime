package com.example.bohyun.fitime;
/**
 * Created by zschr on 11/28/2017.
 */

public class Workout {

    String name, description, exercises;

    public Workout(String name, String description, String exercises) {
        this.name = name;
        this.description = description;
        this.exercises = exercises;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getWorkouts() {
            return exercises;
    }
}