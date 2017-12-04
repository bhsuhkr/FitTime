package com.example.bohyun.fitime;

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

    public String getExercises() {
        return exercises;
    }
}

