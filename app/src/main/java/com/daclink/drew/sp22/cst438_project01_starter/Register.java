package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.databinding.ActivityRegisterBinding;

import java.util.List;
import java.util.Locale;

public class Register extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    public static final String TAG = "Register";
    private FitnessAppDatabase db;
    List<User> user_accounts;
    EditText n1, e1, u1, p1;    // name, email, username, password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /**
         * We begin by obtaining an instance of our database class, then
         * populating our database instance and finally assigning our database
         * data to our newly created User list named "user_accounts".
         * user_accounts holds a copy of the list of users in our database.
         * Next, we bind the information from our activity_register.
         */
        db = FitnessAppDatabase.getInstance(this);
        db.populateInitialData();
        user_accounts = db.user().getAll();

        n1 = binding.textName;
        e1 = binding.textEmail;
        u1 = binding.textUserName;
        p1 = binding.textPassword;

        /**
         * When button "Register" is clicked, we take the user input and convert it to a string
         * variable. We set the username and email to lowercase, allowing the password and name
         * to be case sensitive.
         */
        binding.regBtn.setOnClickListener(view -> {
            String firstName = n1.getText().toString();
            String email = e1.getText().toString().toLowerCase(Locale.ROOT);
            String usrName = u1.getText().toString().toLowerCase(Locale.ROOT);
            String pwd = p1.getText().toString();

            boolean email_exists = false;
            boolean username_exists = false;

            /**
             * We confirm that none of the fields are left blank, then proceed to check
             * if the username or email are not already in our database. If either the username or
             * email are in our database, user will be notified to choose another username or email
             */
            if(firstName.length() != 0) {
                if(email.length() != 0) {
                    if(usrName.length() != 0) {
                        if(pwd.length() != 0) {
                            for(User x : user_accounts) {
                                if(x.getEmail().equals(email)) {
                                    email_exists = true;
                                }

                                if(x.getUsername().equals(usrName)) {
                                    username_exists = true;
                                }
                            }

                            if(email_exists) {
                                Toast.makeText(this, "Email already in use", Toast.LENGTH_LONG).show();
                            } else {
                                if(username_exists) {
                                    Toast.makeText(this, "Username already in use", Toast.LENGTH_LONG).show();
                                } else {
                                    // add them to the user list
                                    db.user().addUser(new User(firstName, email, usrName, pwd));
                                    Toast.makeText(this, "Account created Successfully", Toast.LENGTH_LONG).show();
                                    // jump them back over to login page for them to log in
                                    Intent i = new Intent(this, MainActivity.class);
                                    startActivity(i);
                                }
                            }

                        } else {
                            Toast.makeText(this,"Empty field\nPlease enter Password", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Empty field\nPlease enter Username", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Empty field\nPlease enter Email address", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Empty field\nPlease enter Name", Toast.LENGTH_LONG).show();
            }
        });

        /**
         * If the user decied to cancel their registration, user will be directed back
         * to the home screen, which is the login page and cannot proceed.
         */
        binding.cancelBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

    }
}