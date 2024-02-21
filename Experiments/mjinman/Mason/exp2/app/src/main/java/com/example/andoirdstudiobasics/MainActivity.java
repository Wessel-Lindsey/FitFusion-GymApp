package com.example.andoirdstudiobasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TEXTBOX = "com.example.androidstudiobasics.TEXTBOX";
    Button button;
    Button button2;

    EditText textBox;

    //TextView temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // storing ID of the button
        // in a variable
        button = (Button)findViewById(R.id.button);
        //temp.setText(textBox.getText());
        // when user tap on the button
        if (button != null) {
            button.setOnClickListener((View.OnClickListener)(v -> {

                // displaying a toast message
                Toast.makeText((Context)MainActivity.this, R.string.message, Toast.LENGTH_LONG).show();
            }));
        }
        button2 = (Button) findViewById(R.id.button2);

        if (button2 != null) {
            button2.setOnClickListener(view -> {
                textBox = (EditText) findViewById(R.id.textBox);
                Intent intent = new Intent(MainActivity.this, HelloWorldActivity.class);
                String text = textBox.getText().toString();

                intent.putExtra(TEXTBOX, text);
                startActivity(intent);
            });
        }
    }
}