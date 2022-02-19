package com.example.fitnessapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fitnessapp.databinding.ActivityButtonRecyclerBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ButtonRecycler extends AppCompatActivity {

    private ActivityButtonRecyclerBinding binding;
    private FitnessAppDatabase db;

    RecyclerView recyclerView;  //activity
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
        recyclerView = binding.recycView;
        progressBar = binding.progressBar;
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        fetchData();

    }

    /**
     * This function will fetch the data we are requesting
     * from the API. We have a progress bar that appears as our app pulls the data
     */
    private void fetchData() {
        // we call our retrofit here
        progressBar.setVisibility(View.VISIBLE);
        RetroFit.getRetrofitInstance().getCategory().enqueue(new Callback<ExerciseInfoJson<MuscleCategory>>() {
            @Override
            public void onResponse(Call<ExerciseInfoJson<MuscleCategory>> call, Response<ExerciseInfoJson<MuscleCategory>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    optionsList = (response.body());
                    //inserted
                    adapter = new ButtonAdapter(optionsList);   // this needs to be inside of this class in order to fetch the data we need
                    recyclerView.setAdapter(adapter);           // this one also (BOTH need to be here, or else it won't work)
                    Log.d("GettingData", optionsList.toString());
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            /**
             * If our attempt to fetch data fails, we will display an error message in a toast
             */
            @Override
            public void onFailure(Call<ExerciseInfoJson<MuscleCategory>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ButtonRecycler.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
