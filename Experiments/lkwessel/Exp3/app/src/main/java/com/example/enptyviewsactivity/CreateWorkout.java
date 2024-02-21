package com.example.enptyviewsactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
//import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
//import com.example.enptyviewsactivity.app.AppController;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.enptyviewsactivity.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateWorkout extends AppCompatActivity {

    private String TAG = CreateWorkout.class.getSimpleName();
    private Button btnJsonObj, btnJsonArray, profile, post;
    private TextView msgResponse, workoutResponse;
    private EditText userWorkout;
    private ProgressDialog pDialog;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        btnJsonObj = (Button) findViewById(R.id.btnJsonObj);
        btnJsonArray = (Button) findViewById(R.id.btnJsonArray);
        msgResponse = (TextView) findViewById(R.id.msgResponse);
        workoutResponse = (TextView) findViewById(R.id.workoutResponse);
        profile = (Button) findViewById(R.id.profile);
        post = (Button) findViewById(R.id.post);
        userWorkout = (EditText) findViewById(R.id.userWorkout);

        post.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
//                String url = "https://5ce085df-d5c0-42b3-9b0a-fdcdd90205b6.mock.pstmn.io";
                String url = "https://coms-309-067.class.las.iastate.edu:8080/persons";
                try {
                    JSONObject json = new JSONObject();
                    json.put("name", userWorkout.getText().toString());
//                    String jsonBody = json.toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                            workoutResponse.setText(response.toString());
                            Toast.makeText(CreateWorkout.this, userWorkout.toString(), Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                            workoutResponse.setText(error.getMessage().toString());
                            Toast.makeText(CreateWorkout.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });

                    RequestQueue requestQueue;
                    requestQueue = Volley.newRequestQueue(CreateWorkout.this);
                    requestQueue.add(stringRequest);
                    workoutResponse.setText("Sent: " + json.toString());
                }
                catch(JSONException e){
                    e.printStackTrace();
                }}
        });
        btnJsonObj.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
//                String url = "http://coms-309-067.class.las.iastate.edu:8080/persons/";
                String url = "https://5ce085df-d5c0-42b3-9b0a-fdcdd90205b6.mock.pstmn.io";
                msgResponse.setText("waiting");
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        msgResponse.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        msgResponse.setText(error.getMessage());
                    }
                });
                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(CreateWorkout.this);
                requestQueue.add(jsonObjectRequest);
            }
        });

        btnJsonArray.setOnClickListener(new View.OnClickListener(){
            String url = "http://coms-309-067.class.las.iastate.edu:8080/uesr/routine";
            //                String url = "https://5ce085df-d5c0-42b3-9b0a-fdcdd90205b6.mock.pstmn.io";
            public void onClick(View v) {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>(){
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            msgResponse.setText(response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d(TAG, "Error:" + error.getMessage());
                            msgResponse.setText(error.getMessage());
                        }
                    });

                    RequestQueue requestQueue;
                    requestQueue = Volley.newRequestQueue(CreateWorkout.this);
                    requestQueue.add(jsonArrayRequest);
            }
        });

//        pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.setCancelable(false);
         //https://5ce085df-d5c0-42b3-9b0a-fdcdd90205b6.mock.pstmn.io";

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<String>() {
//            public void onResponse(String response) {
//                Toast.makeText(CreateWorkout.this, userWorkout.toString(), Toast.LENGTH_LONG).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(CreateWorkout.this, error.getMessage().toString(), Toast.LENGTH_LONG).show());
////                msgResponse.setText(error.getMessage());
//            }
//        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateWorkout.this, profilePage.class));
            }
        });

    }




}