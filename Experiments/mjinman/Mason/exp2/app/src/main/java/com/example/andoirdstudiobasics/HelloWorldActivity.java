package com.example.andoirdstudiobasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HelloWorldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        Intent intent = getIntent();
        String msg = intent.getStringExtra(MainActivity.TEXTBOX);
        TextView t = (TextView) findViewById(R.id.t);
        t.setText(msg);
    }
}