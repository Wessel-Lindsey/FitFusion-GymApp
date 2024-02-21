package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class BusinessSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_signup);

        Button toLoginBtn = findViewById(R.id.businessSignupToLoginPageBtn);
        Button createBusiness = findViewById(R.id.createBusinessAccountBtn);

        EditText businessName = findViewById(R.id.businessNameETxt);
        EditText password = findViewById(R.id.passwordBusinessETxt);
        EditText city = findViewById(R.id.businessCityETxt);
        EditText state = findViewById(R.id.businessStateETxt);

        //final String URL = "https://dbfca566-da98-43bd-8c06-e71b77d0240d.mock.pstmn.io/people/create?username&password";
        //final String URL = "https://dbfca566-da98-43bd-8c06-e71b77d0240d.mock.pstmn.io/businesses";
        final String URL = "http://coms-309-067.class.las.iastate.edu:8080/businesses";
        createBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //post request
                    JSONObject json = new JSONObject();
                    json.put("businessName", businessName.getText().toString());
                    json.put("password", password.getText().toString());
                    json.put("city", city.getText().toString());
                    json.put("zipcode", state.getText().toString());
                    String jsonBody = json.toString();

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, json, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Create a JSONTokener to parse the JSON response
                            //JSONTokener tokener = new JSONTokener(response);

                            try {
                                // Create a JSONObject from the JSONTokener
                                //JSONObject jsonObject = new JSONObject(tokener);

                                // Access data in the JSONObject
                                String status = response.getString("statusCode");
                                //int age = jsonObject.getInt("age");
                                //String city = jsonObject.getString("city");
                                Toast.makeText(BusinessSignup.this, response.toString(),
                                        Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(BusinessSignup.this, error.getMessage().toString(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                    RequestQueue requestQueue;
                    requestQueue = Volley.newRequestQueue(BusinessSignup.this);
                    requestQueue.add(request);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
        toLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusinessSignup.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}