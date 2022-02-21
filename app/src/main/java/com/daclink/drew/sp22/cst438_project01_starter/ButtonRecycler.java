package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ButtonRecycler extends AppCompatActivity {

    private ActivityButtonRecyclerBinding binding;
    private FitnessAppDatabase db;
    private int userPK;


    public int getUserPK() {
        return userPK;
    }

    public void setUserPK(int userPK) {
        this.userPK = userPK;
    }

    RecyclerView recyclerView;  //activity
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    ButtonAdapter adapter;
    ExerciseInfoJson<MuscleCategory> optionsList = new ExerciseInfoJson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityButtonRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // new code 02/20/22
        /**
         * This code is the user ID we receive from the Main Activity
         * It will tell us which user is the one logging in.
         * This value should follow the user to any page they decide to navigate to,
         * in case they want something to happen in their account, like view their user
         * profile, save a workout, etc.
         */
        Bundle bundle = getIntent().getExtras();
        setUserPK(Integer.parseInt(bundle.getString("userPrimaryKey")));  //storing user id
//        Log.d("User ID: " + userPK, Integer.toString(userPK));

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

    // bryan's code 2/20/22
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.Logout:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "You have logged out!", Toast.LENGTH_SHORT);
                return true;
            case R.id.update_profile:
                /**
                 * Here I am SENDING the userID to the next intent, which in this case is UserProfile
                 * We are choosing to send this to be able to easily access the rest of the user's
                 * information, like name, email, username, and password
                 */
                Intent i = new Intent(getApplicationContext(), UserProfile.class);
                Bundle bundle = new Bundle();
                bundle.putString("userPrimaryKey", String.valueOf(getUserPK()));
                i.putExtras(bundle);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
