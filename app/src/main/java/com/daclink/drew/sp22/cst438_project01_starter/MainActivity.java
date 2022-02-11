package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fitnessapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TextView stringURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);

        /**
         * this means that whatever we are doing to the stringURL variable,
         * will be displayed/sent to the textViewImageURL in activity_main.xml
         */
        stringURL = binding.textViewImageURL;

        /**
         * Here we are now appending the base and the relative url together
         */
        ExerciseImageAPI exerciseImageApi_ = RetroFit.getRetrofitInstance().create(ExerciseImageAPI.class);

        /**
         * Network Request.
         * This will return a list of our Exercise images
         * Our list is called "call"
         */

        Call<ExerciseImage> call = exerciseImageApi_.getImage();

        call.enqueue(new Callback<ExerciseImage>() {
            @Override
            public void onResponse(Call<ExerciseImage> call, Response<ExerciseImage> response) {
                /**
                 * If response is not successful, we will print
                 * the error code to our screen and return nothing
                 */
                if(!response.isSuccessful()) {
                    stringURL.setText("Code: " + response.code());
                    return;
                }

                ArrayList<ExerciseImage.results> urls = response.body().getResults();

                for(ExerciseImage.results i : urls) {
                    /**
                     * We need to turn these urls into actual images.
                     * As of now, only the string url is being displayed
                     * on the screen.
                     */
                    String content = "";
                    content += i.getImage() + "\n";

                    stringURL.append(content);
                }
            }

            @Override
            public void onFailure(Call<ExerciseImage> call, Throwable t) {
                stringURL.setText(t.getMessage());
            }
        });
    }
}