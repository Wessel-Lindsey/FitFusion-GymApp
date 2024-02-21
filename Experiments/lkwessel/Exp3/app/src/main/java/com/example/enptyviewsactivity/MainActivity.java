package com.example.enptyviewsactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.enptyviewsactivity.utils.Const;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    private Button button, profile, workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        profile = (Button) findViewById(R.id.profile);
        workout = (Button) findViewById(R.id.workout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openActivity2();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){openProfile();}
        });
        workout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){openWorkout();}
        });



    }

    public void openActivity2(){
        startActivity(new Intent(MainActivity.this, Activity2.class));
    }
    public void openProfile(){
        Intent intent = new Intent(this, profilePage.class);
        startActivity(intent);
    }
    public void openWorkout(){
        Intent intent = new Intent(this, CreateWorkout.class);
        startActivity(intent);
    }
}