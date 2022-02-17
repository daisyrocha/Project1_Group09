package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    /**
     * Here we have an object of type FitnessAppDatabase
     * We also have userName and userPassword, that are used to
     * obtain input from the user. We also create a List of User
     * so that we can compare the user's input with our userTable entity
     */
    private FitnessAppDatabase db;
    EditText userName, userPassword;
    List<User> user_accounts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /**
         * We are assigning or instance of type FitnessApp Database to db
         * In other words, here, we are assigning our current database to db
         * We then populate our database with the info we gave it, in this case
         * the first user account, which is that of the Administrator.
         * Username: "Admin" and Password: "111". We set these values in the FitnessAppDatabase class.
         * @user_accounts gets a list of all of our accounts database, we are doing this to
         * easily be able to compare username and password entered by the user with that
         * in our database. We want to make sure input matches or doesn't already exist.
         */
        db = FitnessAppDatabase.getInstance(this);
        db.populateInitialData();
        user_accounts = db.user().getAll();

        userName = binding.textUserName;
        userPassword = binding.textPassword;


        binding.loginBtn.setOnClickListener(view -> {
            String usrName = userName.getText().toString();
            String usrPwd = userPassword.getText().toString();
            boolean match = false;
            String firstName = "";

            /**
             * This if-else checks if the username is left empty,
             * if not left empty, we will continue, otherwise we will have
             * a Toast asking user to enter their username before clicking on
             * the Login button. The second if-else statement does the same, but instead,
             * checks if password field is left empty. If empty, user will be asked
             * to enter password. If both conditions are met, where the username field and
             * the password field are not empty, we use a for loop to iterate through our
             * user_accounts and see if the username and password match those in our database
             */
            if(usrName.length() != 0) {
                if(usrPwd.length() != 0) {
                    for(User x : user_accounts) {
                        if(x.getUsername().equals(usrName)) {
                            if(x.getPassword().equals(usrPwd)) {
                                match = true;
                                firstName = x.getName();
                            }
                        }
                    }

                    /**
                     * If the username entered matches a username in our database and the password
                     * entered is part of the username tuple, then we move to the MuscleGroup place
                     * If either input doesn't match, we alert the user that the text entered is not a
                     * match.
                     */
                    if(match) {
                        Toast.makeText(this, "Welcome " + firstName + "!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(this, ButtonRecycler.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(this, "Invalid Username/Password", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(this, MainActivity.class);
                        startActivity(i);
                    }

                } else {
                    Toast.makeText(this,"Empty field\nPlease enter password", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this,"Empty field\nPlease enter username", Toast.LENGTH_LONG).show();
            }



        });

        binding.registerBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, Register.class);
            startActivity(i);
        });
    }

    private void logoutUser(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage(R.string.Logout);

        alertBuilder.setPositiveButton(getString(R.string.yesLogout),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
        alertBuilder.setNegativeButton(getString(R.string.noLogout),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alertBuilder.create().show();
    }


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
                Toast.makeText(this, "You have logged out!", Toast.LENGTH_SHORT);
                logoutUser();
                return true;
            case R.id.update_profile:
                Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}