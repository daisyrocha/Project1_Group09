package com.example.fitnessapp;

/**
 * This data is what is inside of our "result" from this specific API path
 * Each Path has different data inside of its "result" field, so we are creating
 * a new class for each different path we try to obtain.
 */
public class MuscleCategory {
    private int id;
    private String name;

    /**
     * We create getters and setters that will help us obtain and modify
     * information from our API
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MuscleCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
