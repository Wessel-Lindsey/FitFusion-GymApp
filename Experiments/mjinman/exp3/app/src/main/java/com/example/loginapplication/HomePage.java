package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.example.MainActivity.androidvolley.app.AppController;
//import com.example.MainActivity.androidvolley.utils.Const;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomePage extends Activity {

    //private String TAG = HomePage.class.getSimpleName();
    //private Button btnStringReq;
    //private TextView msgResponse;
    //private ProgressDialog pDialog;

    // This tag will be used to cancel the request
    //private String tag_string_req = "string_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button loginPageBtn = (Button) findViewById(R.id.loginpagebtn);
        Button jsonRequestBtn = (Button) findViewById(R.id.jsonreqbtn);
        Button getAllUsersBtn = findViewById(R.id.getAllUsersBtn);
        TextView welcome = (TextView) findViewById(R.id.welcome);
        TextView backend = (TextView) findViewById(R.id.backendResponse);
        TextView helloUser = (TextView) findViewById(R.id.helloUser);
        loginPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(HomePage.this, MainActivity.class);
                startActivity(in);
            }

        });
        //final String url = "https://dbfca566-da98-43bd-8c06-e71b77d0240d.mock.pstmn.io/jsonrequest";
        final String url = "http://coms-309-067.class.las.iastate.edu:8080/people/@/billy";
        //final String getAllUsersURL = "http://coms-309-067.class.las.iastate.edu:8080/people";
        final String getAllUsersURL = "http://coms-309-067.class.las.iastate.edu:8080/people";
        jsonRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backend.setText("waiting");
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                backend.setText("Recieved from backend: " + response.toString());
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                backend.setText("Error: " + error.getMessage());
                            }
                        });
                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(HomePage.this);
                requestQueue.add(jsonObjectRequest);
            }
        });

        getAllUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getAllUsersURL, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // Handle the JSON response here
                                helloUser.setText(response.toString());
//                                try {
//                                    // Iterate through the JSON array and process the data
//                                    for (int i = 0; i < response.length(); i++) {
//                                        // Access individual JSON objects within the array
//                                        // For example, you can get a field named "name" like this:
//                                        String name = response.getJSONObject(i).getString("name");
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle errors here
                                Log.e("Volley Error", error.toString());
                            }
                        });
                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(HomePage.this);
                requestQueue.add(jsonArrayRequest);
            }
        });
        //btnStringReq = (Button) findViewById(R.id.btnStringReq);
        //msgResponse = (TextView) findViewById(R.id.backendResponse);


        //pDialog = new ProgressDialog(this);
        //pDialog.setMessage("Loading...");
        //pDialog.setCancelable(false);


        //btnStringReq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                makeStringReq();
//            }
//        });
    }


}
//    private void showProgressDialog() {
//        if (!pDialog.isShowing())
//            pDialog.show();
//    }
//
//
//    private void hideProgressDialog() {
//        if (pDialog.isShowing())
//            pDialog.hide();
//    }
//
//
//    //public static final String URL_STRING_REQ = "https://jsonplaceholder.typicode.com/users/1";
////   public static final String URL_STRING_REQ = "https://2aa87adf-ff7c-45c8-89bc-f3fbfaa16d15.mock.pstmn.io/users/1";
////   public static final String URL_STRING_REQ = "http://10.0.2.2:8080/users/1";
//    public static final String URL_STRING_REQ = "https://13b2db10-f75d-40eb-9320-8226532e179c.mock.pstmn.io/get";
//    /**
//     * Making json object request
//     **/
//    private void makeStringReq() {
//        showProgressDialog();
//
//
//        StringRequest strReq = new StringRequest(Method.GET, URL_STRING_REQ, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, response.toString());
//                msgResponse.setText(response.toString());
//                hideProgressDialog();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                hideProgressDialog();
//            }
//        });
//
//
//        // Adding request to request queue
//        //ppController.getInstance().addToRequestQueue(strReq, tag_string_req);
//
//
//    }
//}