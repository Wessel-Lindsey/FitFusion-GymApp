package com.example.javadoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPage extends AppCompatActivity {



    //public static final String URL_STRING_REQ = "https://dbfca566-da98-43bd-8c06-e71b77d0240d.mock.pstmn.io/username";
    //public static final String URL_STRING_REQ = "http://coms-309-067.class.las.iastate.edu:8080/people"; //kyle endpoint
    public static final String URL = "http://coms-309-067.class.las.iastate.edu:8080/people/@/test2"; //kyle endpoint
    public final String GETPERSONURL = "http://coms-309-067.class.las.iastate.edu:8080/people/@/"; //kyle endpoint
    //public static final String URL = "https://dbfca566-da98-43bd-8c06-e71b77d0240d.mock.pstmn.io/jsonrequest"; //postman test
    //returns { "username" : mjinman, "password" : "password!" }
    public static final String USERNAME = "username";

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        TextView username = findViewById(R.id.username);
        TextView password = findViewById(R.id.passwrd);
        TextView forgotPassword = findViewById(R.id.forgotpassword);
        Button loginBtn = (Button) findViewById(R.id.loginbtn);
        Button homeBtn = (Button) findViewById(R.id.homebtn);
        Button goToSignupBtn = (Button) findViewById(R.id.signupbtn);
        Button goToBusinessLoginBtn = findViewById(R.id.businessLoginBtn);
        String URL = GETPERSONURL;
        //admin and admin
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedBtn = v.getId();
                forgotPassword.setText( "" + v.toString());



                        String URL = GETPERSONURL + username.getText().toString();
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        forgotPassword.setText("Recieved from backend: " + response.toString());

                                            try {
                                                if (username.getText().toString().equals(response.getString("username")) &&
                                                        password.getText().toString().equals(response.getString("password"))) {
                                                    Intent intent = new Intent(LoginPage.this, HomePage.class);
                                                    intent.putExtra(USERNAME, response.getString("username"));
                                                    startActivity(intent);
                                                    finish();
                                                }
                                                else {
                                                    forgotPassword.setText("Username or Password incorrect");
                                                }
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }

                                    }
                                }, new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        forgotPassword.setText("Error: " + error.getMessage());
                                    }
                                });


                        RequestQueue requestQueue;
                        requestQueue = Volley.newRequestQueue(LoginPage.this);
                        requestQueue.add(jsonObjectRequest);


                    if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                        //correct login
                        Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT);
                        //String url = "http://localhost/inserttask.php";
                    }
                    else {
                        //login failed
                        Toast.makeText(LoginPage.this, "Login Failed", Toast.LENGTH_SHORT);
                    }
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LoginPage.this, HomePage.class);
                in.putExtra("username", "test");
                startActivity(in);
                finish();
            }
        });
        goToSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LoginPage.this, SignupOptions.class);
                startActivity(in);
                finish();
            }
        });
        goToBusinessLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LoginPage.this, BusinessLogin.class);
                startActivity(in);
                finish();
            }
        });




    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}