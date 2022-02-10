package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText userId;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userIdText = userId.getText().toString();
                String passwordText = password.getText().toString();
                if (userIdText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill in all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDoa userDoa = userDatabase.userDoa();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDoa.login(userIdText, passwordText);
                            if(userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                String name = userEntity.name;
                                // todo set this to the home screen
//                                startActivity(new Intent(LoginActivity.this, Home.class)
//                                .putExtra("name", name));
                            }
                        }
                    }).start();
                }
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
