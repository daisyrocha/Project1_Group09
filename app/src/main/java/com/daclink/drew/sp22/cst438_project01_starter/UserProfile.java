package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.databinding.ActivityUserProfileBinding;

import java.util.List;

public class UserProfile extends AppCompatActivity {

    private ActivityUserProfileBinding binding;
    private FitnessAppDatabase db;
    List<User> user_accounts;

    /**
     * userPK is used to obtain and set user's id number
     * We set it as private to not allow it to be easily modified
     * We have the setUserPK function to set the user's id number when we
     * have it passed to this activity from another activity.
     */
    private int userPK;

    public int getUserPK() {
        return userPK;
    }

    public void setUserPK(int userPK) {
        this.userPK = userPK;
    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /**
         * We are obtaining our user_accounts database information to be able to
         * obtain user information for the user profile page.
         */
        db = FitnessAppDatabase.getInstance(this);
        db.populateInitialData();
        user_accounts = db.user().getAll();

        /**
         * This code is the user ID we receive from the ButtonRecycler
         * It will tell us which user is the one wanting to see their profile.
         * This value should follow user to any page they decide to navigate to,
         * in case they want something to happen in their account, like view their user
         * profile, save a workout, etc.
         */
        Bundle bundle = getIntent().getExtras();
        setUserPK(Integer.parseInt(bundle.getString("userPrimaryKey")));  //storing user id

        // New code
        TextView name = binding.name;
        TextView email = binding.email;
        TextView username = binding.uName;
        for(User u : user_accounts) {
            if(u.getId() == userPK) {
                name.setText(u.getName());
                email.setText(u.getEmail());
                username.setText(u.getUsername());
            }
        }
    }



    // Recycled code from Bryan
    /**
     * We are implementing our options menu once again
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected: " +item.getTitle(), Toast.LENGTH_SHORT).show();
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

            case R.id.myWorkouts:
                // here we should send the user id to the SavedWorkouts activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}