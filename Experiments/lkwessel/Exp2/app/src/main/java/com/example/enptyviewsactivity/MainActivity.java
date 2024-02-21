package com.example.enptyviewsactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        input = findViewById(R.id.userInput);

        button.setOnClickListener(v -> {
            String str = input.getText().toString();
            Intent intent = new Intent(getApplicationContext(), Activity2.class);

            intent.putExtra("message_key", str);
            startActivity(intent);
        });


    }
}