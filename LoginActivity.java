package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText userPassword;
    private TextView registerUser;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText)findViewById(R.id.textUserName);
        userPassword = (EditText)findViewById(R.id.textPassword);
        registerUser = (TextView)findViewById(R.id.registerTextView);
        loginButton = (Button)findViewById(R.id.loginBtn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo change later to except all users
                adminPassword(userName.getText().toString(), userPassword.getText().toString());
            }
        });

    }

    private void adminPassword(String userName, String userPassword){
        if((userName == "Admin") && (userPassword == "111")){
            Intent intent = new Intent(LoginActivity.this, SecondFragment.class);
            startActivity(intent);
        }else{
            //todo add somthing when the password is wrong.
        }
    }

//    private boolean validatePassword(){
//        return userName.equals(userPassword);
//    }

}
