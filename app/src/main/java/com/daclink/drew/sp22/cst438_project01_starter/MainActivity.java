package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Locale;

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


        /**
         * When clicking on the login button, user's input will be compared to
         * that of the database. If information they enter matches our database data,
         * then user will be able to proceed to the following page, otherwise,
         * user will stay in the login page.
         */
        binding.loginBtn.setOnClickListener(view -> {
            String usrName = userName.getText().toString().toLowerCase(Locale.ROOT);
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

        /**
         * When clicking on the "Register" button, user will be taken
         * to a new intent where they are asked to enter their name,
         * email, can choose a username and a password.
         */
        binding.registerBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, Register.class);
            startActivity(i);
        });
    }
}