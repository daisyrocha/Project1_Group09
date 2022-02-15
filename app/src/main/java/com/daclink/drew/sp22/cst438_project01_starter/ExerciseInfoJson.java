package com.example.fitnessapp;

import java.util.List;

/**
 * This class holds the variables that every API path holds
 */

public class ExerciseInfoJson<T> {
    private int count;
    private String next;
    private String previous;
    private List<T> results;


    /**
     * Here he ware creating the getters and setters that
     * will help us obtain and set variable values from the
     * Json file we are viewing
     */

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ExerciseInfoJson{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
}


