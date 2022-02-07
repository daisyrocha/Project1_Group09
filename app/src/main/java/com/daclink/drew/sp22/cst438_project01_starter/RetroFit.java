package com.example.fitnessapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Here we are defining our base URL
 * We are creating a class for retrofit instance
 * This Base URL is where we have more URLs with the details we want
 * We will append the relative URL to this base
 * RelativeURL is in ExerciseImageAPI.java
 */
public class RetroFit {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://wger.de/api/v2/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    // create model class according to required data from API
}
