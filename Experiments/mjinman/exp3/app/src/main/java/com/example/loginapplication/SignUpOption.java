package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_option);

        Button createUserBtn = (Button) findViewById(R.id.userSignupBtn);
        Button createBusinessBtn = (Button) findViewById(R.id.signupBusinessBtn);
        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpOption.this, UserSignup.class);
                startActivity(intent);
                finish();
            }
        });

        createBusinessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpOption.this, BusinessSignup.class);
                startActivity(intent);
            }
        });


    }
}