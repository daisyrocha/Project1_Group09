package com.example.fitnessapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExerciseImageAPI {
    /**
     * This is a GET request which will get data from the server
     * We have the base URL in our RetroFit.java
     * "exerciseimage/?format=json" is our relative URL
     * What we have after the '/' is just a direct connection to the json file
     */

    @GET("exerciseimage/?format=json")
    Call<ExerciseImage> getImage();
}
