package com.example.javadoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchPage extends AppCompatActivity {
    AlertDialog dialog;
    Button search;
    LinearLayout layout;
    TextView baselineText;
    EditText searchName;
    private String TAG = MainActivity.class.getSimpleName();
    String URL = "http://coms-309-067.class.las.iastate.edu:8080/people";
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        Intent intent = getIntent();
        userName = intent.getStringExtra("username");

        search = findViewById(R.id.searchBTN);
        searchName = findViewById(R.id.searchName);
        layout = findViewById(R.id.container);
        baselineText = findViewById(R.id.baselineText);
        FloatingActionButton home = findViewById(R.id.homePage);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(SearchPage.this, HomePage.class);
                intent2.putExtra("username", intent.getStringExtra(LoginPage.USERNAME));
                startActivity(intent2);
                finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                layout.removeAllViews();
                String serverUrl = URL + "/@/" + userName + "/search/" + (searchName.getText().toString()) ;
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(serverUrl, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if (response.length() == 0) {
                                addCard("No People");
                            } else {
                                for (int i = response.length() - 1; i >= 0; i--) {

                                    JSONObject object = response.getJSONObject(i);
                                    String name = object.getString("username");
                                    addCard(name);
                                }
                            }
                        } catch (JSONException e) {
                            VolleyLog.d(TAG, "Error:" + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error:" + error.getMessage());
                    }
                });
                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(SearchPage.this);
                requestQueue.add(jsonArrayRequest);
            }

        });
    }

//    private void buildDialog(String user){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
////        View view = getLayoutInflater().inflate(R.layout.dialog, null);
//
////        EditText name = view.findViewById(R.id.nameEdit);
////        builder.setView(view);
////        builder.setTitle("Enter name");
//        addCard(user);
////                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////                        addCard(user);//name.getText().toString());
////                    }
////                })
////                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////
////                    }
////                });
//        dialog = builder.create();
//    }

    private void addCard(String name){
        View view = getLayoutInflater().inflate(R.layout.simple_box, null);
        TextView nameView = view.findViewById(R.id.textView);
        Button follow = view.findViewById(R.id.followBTN);

        nameView.setText(name);

        follow.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               try {
                   String followerURL = URL +"/@/"+ userName + "/friend/" + name;
//                   Toast.makeText(SearchPage.this, name, Toast.LENGTH_LONG).show();
                   StringRequest stringRequest = new StringRequest(Request.Method.POST, followerURL, new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {
                           Toast.makeText(SearchPage.this, "Sent: " + response.toString(), Toast.LENGTH_LONG).show();
//                           nameView.setText(response.toString());
                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           Toast.makeText(SearchPage.this, "Error: "+ error.toString(), Toast.LENGTH_LONG).show();
//                           nameView.setText(error.toString());
                       }
                   }
                   );

                   RequestQueue requestQueue;
                   requestQueue = Volley.newRequestQueue(SearchPage.this);
                   requestQueue.add(stringRequest);
               } catch (Exception e){
                   e.printStackTrace();
               }

           }
        });

        layout.addView(view);
    }
}