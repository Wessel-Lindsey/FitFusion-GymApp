package com.example.javadoc;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.example.javadoc.databinding.ActivityProfilePageBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ProfilePage extends AppCompatActivity {
    public String GETUSERURL = "http://coms-309-067.class.las.iastate.edu:8080/people/@/";
    public String GETPOSTSURL = "http://coms-309-067.class.las.iastate.edu:8080/posts";
//    private ActivityProfilePageBinding binding;
    private String TAG = MainActivity.class.getSimpleName(), feedStr;
    private TextView workoutPosts, userName, followsTxt, userFeed, pointsTxt, userRoutines;

    private FloatingActionButton myGymChatFAB, followList;

    private Button followBtn, loadRoutinesBtn, postMyGym;
    private EditText myGymEtxt;
    private ScrollView profileScroll;
    private int routineChoice;
    private String userGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        Intent intent = getIntent();
        FloatingActionButton home = findViewById(R.id.homePage);

        userName = findViewById(R.id.userName);
        myGymChatFAB = findViewById(R.id.myGymChat_userProfilePage);
        followsTxt = findViewById(R.id.userProfileFollowersTxt);
        followBtn = findViewById(R.id.userFollowBtn);
        userFeed = findViewById(R.id.userFeed);
        pointsTxt = findViewById(R.id.userProfilePointsTxt);
        loadRoutinesBtn = findViewById(R.id.showAllRoutinesButton);
        userRoutines = findViewById(R.id.userRoutineList);
        profileScroll = findViewById(R.id.profile_scroll);
        followList = findViewById(R.id.followList);
        postMyGym = findViewById(R.id.postMyGymBtn);
        myGymEtxt = findViewById(R.id.postMyGymEtx);


        loadUserInfo();
        loadFeed();
        loadLatestRoutine();

        workoutPosts = findViewById(R.id.workoutPosts);

        userGym = getUserGym(); //call it initially otherwise null on first click

        postMyGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call post request
                    //post request //sets the mygym attribute in the URL to the users gym
                    String postMyWorkout = GETUSERURL + getIntent().getStringExtra("username") + "/setHome/" + myGymEtxt.getText().toString();
                StringRequest postMyWorkoutRequest = new StringRequest(Request.Method.POST, postMyWorkout, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        userGym = getUserGym();
                        Toast.makeText(ProfilePage.this, "myGym posted", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfilePage.this, "error posting myGym", Toast.LENGTH_LONG).show();
                    }
                });
                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(ProfilePage.this);
                requestQueue.add(postMyWorkoutRequest);
            }
        });



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ProfilePage.this, HomePage.class);
                intent2.putExtra("username", intent.getStringExtra(LoginPage.USERNAME));
                startActivity(intent2);
                finish();
            }
        });
        followList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ProfilePage.this, FollowingList.class);
                intent2.putExtra("username", intent.getStringExtra(LoginPage.USERNAME));
                startActivity(intent2);
                finish();
            }
        });

        loadRoutinesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (routineChoice) {
                    case 0:
                        routineChoice = 1;
                        loadAllRoutines();
                        break;
                    case 1:
                        routineChoice = 0;
                        loadLatestRoutine();
                        profileScroll.smoothScrollTo(0,0);
                        break;
                }

            }
        });

        myGymChatFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usersGym = getUserGym();
   //             Toast.makeText(ProfilePage.this, usersGym, Toast.LENGTH_LONG).show();
                Intent gcIntent = new Intent(ProfilePage.this, MyGymChat.class);
                gcIntent.putExtra("username", getIntent().getStringExtra("username"));
                gcIntent.putExtra("myGym", usersGym);
                startActivity(gcIntent);
                finish();
            }
        });

    }
    private void loadUserInfo() {
        String feedStr;
        String URL = GETUSERURL + getIntent().getStringExtra("username");

        Toast.makeText(ProfilePage.this, URL, Toast.LENGTH_LONG);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //populate profile page
                        try {
                            userName.setText(response.getString("username"));
                            pointsTxt.setText("Points: " +response.getInt("points"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfilePage.this, "Error getting user details", Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue rq = Volley.newRequestQueue(ProfilePage.this);
        rq.add(jsonObjectRequest);
    }
    private void loadFeed() {

        /**
         * TEMP IMPLEMENTATION of all posts
         */
        JsonArrayRequest postsGETRequest = new JsonArrayRequest(Request.Method.GET, GETPOSTSURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.length() == 0) {
                        userFeed.setText("No posts here yet");
                    }
                    else {
                        feedStr = "";
                        for (int i = response.length()-1; i >= 0; i--) {

                            JSONObject object = response.getJSONObject(i);
                            String title = object.getString("title");
                            String message = object.getString("message");
                            int likes = object.getInt("likes");
                            feedStr += "<h2><b> <span style=color:black>"+ title + ":</span> </b></h2>"
                                    + "<p>" + message + "<p><span style=color:#505050> " +
                                    "Likes: </span><b><span style=color:#F06060>" + likes + "</span></b><br><br>";

                        }
                        userFeed.setText(Html.fromHtml(feedStr));
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                userFeed.setText("Error loading feed");
                error.printStackTrace();

            }
        });


        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(ProfilePage.this);
        requestQueue.add(postsGETRequest);
    }
    private void loadLatestRoutine() {
        //get routines of the user
        //find routine with highest id
        //add workouts to that routine
        //debug.setText("in add workouts");

        String getAllRoutinesURL = "http://coms-309-067.class.las.iastate.edu:8080/user/" + getIntent().getStringExtra("username") + "/routines";


        //get request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, getAllRoutinesURL, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        //find highest id
                        JSONObject latestRoutine;
                        int id;
                        String msg;

                        try {
                            latestRoutine = findMaxID(response);
                            if (latestRoutine != null) {
                                JSONArray workouts = latestRoutine.getJSONArray("workouts");

                                id = latestRoutine.getInt("id");


                                msg = "<h2><b><span style=color:black>Latest Routine: </b></span><span style=color:#64000A>" + latestRoutine.getString("name") + "</span></h2>";

                                for (int i = 0; i < workouts.length(); i++) {
                                    JSONObject exercise = workouts.getJSONObject(i);
                                    msg += "<p><b><span style=color:black>Exercise " + (i+1) + ": </b></style><span style=color:#64000A>" + exercise.getString("name") +
                                            "</span>\t<b><span style=color:black>\tPR: </style></b><span style=color:#64000A>" + exercise.getString("record") + " </span><b><span style=color:black> reps: </style></b><span style=color:#64000A>" + exercise.getString("reps") + "</span></p>";

                                }
                            }
                            else {
                                //user has no routines
                                msg = "<h2><b><span style=color:black>No Routines yet...</span></b></h2>";
                            }

                            userRoutines.setText(Html.fromHtml(msg));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }



                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfilePage.this, "Error getting User details", Toast.LENGTH_LONG).show();
                    }
                });


        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(ProfilePage.this);
        requestQueue.add(jsonArrayRequest);

    }
    private void loadAllRoutines() {
        String getAllRoutinesURL = "http://coms-309-067.class.las.iastate.edu:8080/user/" + getIntent().getStringExtra("username") + "/routines";

        //get request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, getAllRoutinesURL, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        //find highest id
                        JSONObject cur;
                        int id;
                        String msg;

                        msg = "<h2><b><span style=color:black>Latest Routine: </b></h2>";


                        try {
                            //print out all routines
                            for (int j = 0; j < response.length(); j++) {
                                cur = response.getJSONObject(j);
                                JSONArray workouts = cur.getJSONArray("workouts");
                                msg += "<h2><span style=color:#64000A>" + cur.getString("name") + "</span></h2>";

                                //iterate through all the workouts
                                for (int i = 0; i < workouts.length(); i++) {
                                    JSONObject exercise = workouts.getJSONObject(i);
                                    msg += "<p><b><span style=color:black>Exercise " + (i+1) + ": </b></style><span style=color:#64000A>" + exercise.getString("name") +
                                            "</span>\t<b><span style=color:black>\tPR: </style></b><span style=color:#64000A>" + exercise.getString("record") + " </span><b><span style=color:black> reps: </style></b><span style=color:#64000A>" + exercise.getString("reps") + "</span></p>";
                                }

                                msg += "<p><span style=color:black>-------------------------------------------------</span></p>";

                            }

                            //update GUI
                            userRoutines.setText(Html.fromHtml(msg));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfilePage.this, "Error getting User details", Toast.LENGTH_LONG).show();
                    }
                });


        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(ProfilePage.this);
        requestQueue.add(jsonArrayRequest);

    }
    private static JSONObject findMaxID(JSONArray arr) {
        int maxID, curID, index = 0;
        try {
            if (arr.length() != 0) {
                JSONObject max = arr.getJSONObject(0);
                maxID = max.getInt("id");
                for (int i = 1; i < arr.length(); i++) {
                    curID = arr.getJSONObject(i).getInt("id");
                    if (curID > maxID) {
                        maxID = curID;
                        index = i;
                    }
                }
                return arr.getJSONObject(index);
            }
            return null;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
    private String getUserGym() {
        String URL = GETUSERURL + getIntent().getStringExtra("username");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Toast.makeText(ProfilePage.this, response.toString(), Toast.LENGTH_LONG).show();
                            userGym = response.getJSONObject("myGym").getString("businessName");
                        } catch (JSONException e) {
                                userGym = "none";
//                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfilePage.this, "Error getting user details", Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue rq = Volley.newRequestQueue(ProfilePage.this);
        rq.add(jsonObjectRequest);

        return userGym;
    }
}
