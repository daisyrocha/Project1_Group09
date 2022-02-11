package com.example.fitnessapp;

import java.util.ArrayList;

public class ExerciseImage {
    private int count;
    private String next;
    private String previous;
    ArrayList<results> results;



    /**
     * These are the Getters and Setters for the ExerciseImage class
     * The variables we are using are from the API
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

    public ArrayList<ExerciseImage.results> getResults() {
        return results;
    }

    public void setResults(ArrayList<ExerciseImage.results> results) {
        this.results = results;
    }

    /**
     * This is the results class, which holds the variables
     * that are inside of the results
     */
    public class results {
        int id;
        String uuid;
        int exercise_base;
        String image;
        Boolean is_main;
        String status;
        String style;

        /**
         * These are the Getters and Setters for the variables inside
         * of the results section in our API. With these functions,
         * we are able to set information
         */
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public int getExercise_base() {
            return exercise_base;
        }

        public void setExercise_base(int exercise_base) {
            this.exercise_base = exercise_base;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Boolean getIs_main() {
            return is_main;
        }

        public void setIs_main(Boolean is_main) {
            this.is_main = is_main;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }
    }
    /**
     * Getters and setters from the results class
     */

}
