package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //public static final String URL_STRING_REQ = "https://dbfca566-da98-43bd-8c06-e71b77d0240d.mock.pstmn.io/username";
    //public static final String URL_STRING_REQ = "http://coms-309-067.class.las.iastate.edu:8080/people"; //kyle endpoint
    public static final String URL_STRING_REQ = "http://coms-309-067.class.las.iastate.edu:8080/people/@/test2"; //kyle endpoint
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = findViewById(R.id.username);
        TextView password = findViewById(R.id.passwrd);
        TextView forgotPassword = findViewById(R.id.forgotpassword);
        Button loginBtn = (Button) findViewById(R.id.loginbtn);
        Button homeBtn = (Button) findViewById(R.id.homebtn);
        Button goToSignupBtn = (Button) findViewById(R.id.signupbtn);

        //admin and admin
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedBtn = v.getId();
                //forgotPassword.setText( "" + clickedBtn);
                if (clickedBtn == R.id.loginbtn) {
                    if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                        //correct login
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT);
                        //String url = "http://localhost/inserttask.php";
                    }
                    else {
                        //login failed
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT);
                    }
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_STRING_REQ,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    forgotPassword.setText(response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    forgotPassword.setText(error.getMessage());
                                }
                            });
                        RequestQueue requestQueue;
                        requestQueue = Volley.newRequestQueue(MainActivity.this);
                        requestQueue.add(stringRequest);
                    }
                }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPassword.setText("homebutton");
                Intent in = new Intent(MainActivity.this, HomePage.class);
                startActivity(in);
                finish();
            }
        });
        goToSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPassword.setText("signup");
                Intent in = new Intent(MainActivity.this, SignUpOption.class);
                startActivity(in);
                finish();
            }
        });




    }
}