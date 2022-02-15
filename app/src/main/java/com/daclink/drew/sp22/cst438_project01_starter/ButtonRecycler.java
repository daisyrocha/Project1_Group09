package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fitnessapp.databinding.ActivityButtonRecyclerBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ButtonRecycler extends AppCompatActivity {

    private ActivityButtonRecyclerBinding binding;
    private FitnessAppDatabase db;

    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    ButtonAdapter adapter;
    ExerciseInfoJson<MuscleCategory> optionsList = new ExerciseInfoJson();   //idk about this one


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityButtonRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /**
         * We fetch the recyclerview
         * We set the layout manager, it implements how we will show the recycler view
         * We create an instance of recycler view and pass the list we created
         * We then create this same constructor in the button adapter
         */
        recyclerView = binding.reclerview;
        progressBar = binding.progressBar;
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ButtonAdapter(optionsList);
        recyclerView.setAdapter(adapter);

        fetchData();

    }

    private void fetchData() {
        // we call our retrofit here
        progressBar.setVisibility(View.VISIBLE);
        RetroFit.getRetrofitInstance().getCategory().enqueue(new Callback<ExerciseInfoJson<MuscleCategory>>() {
            @Override
            public void onResponse(Call<ExerciseInfoJson<MuscleCategory>> call, Response<ExerciseInfoJson<MuscleCategory>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    optionsList = (response.body());
                    Log.d("GettingData", optionsList.toString());
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ExerciseInfoJson<MuscleCategory>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ButtonRecycler.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}