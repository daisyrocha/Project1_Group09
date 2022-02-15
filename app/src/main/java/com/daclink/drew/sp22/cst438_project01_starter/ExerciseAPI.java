package com.example.fitnessapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExerciseAPI {
    /**
     * This is a GET request which will get data from the server
     * We have the base URL in our RetroFit.java
     * "exerciseimage/?format=json" is our relative URL
     * What we have after the '/' is just a direct connection to the json file
     */

    @GET("exerciseimage/?format=json")
    Call<ExerciseInfoJson> getImageUrl();

    @GET("exerciseinfo/?format=json&limit=300")
    Call<List<ExerciseInfoJson>> getAllInfo();

    @GET("exercisecategory/?format=json")
    Call<ExerciseInfoJson<MuscleCategory>> getCategory();
}