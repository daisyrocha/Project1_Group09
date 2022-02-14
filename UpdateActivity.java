package com.daclink.drew.sp22.cst438_project01_starter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    EditText userId,  name;
    Button update;
    TextView userProfileText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        userId = findViewById(R.id.userIdUpdate);
        name = findViewById(R.id.nameUpdate);
        update = findViewById(R.id.userUpdateBtn);
        userProfileText =findViewById(R.id.userProfileText);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateNameData = name.getText().toString();
                String updateUserIdData = userId.getText().toString();
                userProfileText.setText("Name: \t" + updateNameData + "\nUserid: \t" + updateUserIdData);

            }
        });

    }

}
