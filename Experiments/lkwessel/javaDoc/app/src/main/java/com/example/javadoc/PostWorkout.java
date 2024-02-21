package com.example.javadoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostWorkout extends AppCompatActivity {
    public static final String GETUSERURL = "http://coms-309-067.class.las.iastate.edu:8080/people/@/";
    public static final String BASE_POSTROUTINE_URL = "http://coms-309-067.class.las.iastate.edu:8080/user/"; ///user/{username}/routines

    private Button homeBtn, trackWorkoutBtn;
    private CheckBox squats, bench, deadlift, run, crunch;
    private EditText squatsPR, benchPR, deadliftPR, runPR, crunchPR,
                     squatsSets, benchSets, deadliftSets, runSets, crunchSets,
                      squatsReps, benchReps, deadliftReps, runReps, crunchReps,
                        customName;
    private TextView debug;

    //private String[] workoutNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_workout);

        //initWorkoutNames();
        //declarations
        homeBtn = findViewById(R.id.workoutsToHomeBtn);
        trackWorkoutBtn = findViewById(R.id.trackWorkoutBtn);
        squats = findViewById(R.id.squats);
        bench = findViewById(R.id.bench);
        deadlift = findViewById(R.id.deadlift);
        run = findViewById(R.id.run);
        crunch = findViewById(R.id.crunch);
        squatsSets = findViewById(R.id.squatsSets);
        benchSets = findViewById(R.id.benchSets);
        deadliftSets = findViewById(R.id.deadliftSets);
        runSets = findViewById(R.id.runSets);
        crunchSets = findViewById(R.id.crunchSets);
        squatsReps = findViewById(R.id.squatsReps);
        benchReps = findViewById(R.id.benchReps);
        deadliftReps = findViewById(R.id.deadliftReps);
        runReps = findViewById(R.id.runReps);
        crunchReps = findViewById(R.id.crunchReps);

        squatsPR = findViewById(R.id.squatsPR);
        benchPR = findViewById(R.id.benchPR);
        deadliftPR = findViewById(R.id.deadliftPR);
        runPR = findViewById(R.id.runPR);
        crunchPR = findViewById(R.id.crunchPR);


        debug = findViewById(R.id.debugTxt);
        customName = findViewById(R.id.customName);


        //load user data

        //get request for business
        String URL = GETUSERURL + getIntent().getStringExtra("username");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //populate profile page
                        /*try {
                            squatsPR.setHint(response.getString("squatPR"));
                            benchPR.setHint(response.getString("benchPR"));
                            deadliftPR.setHint(response.getString("deadliftPR"));
                            runPR.setHint(response.getString("runPR"));
                            crunchPR.setHint(response.getString("crunchPR"));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }*/
                        Toast.makeText(PostWorkout.this, response.toString(), Toast.LENGTH_LONG).show();


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PostWorkout.this, "Error getting User details", Toast.LENGTH_LONG).show();
                    }
                });


        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(PostWorkout.this);
        requestQueue.add(jsonObjectRequest);



        trackWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONArray jsonArr = new JSONArray();
                    //check for buttons that are checked, make a post request of that workout
                    if (squats.isChecked()) {
                        JSONObject json = new JSONObject();
                        json.put("name", "squats");
                        if (squatsPR.getText().toString().equals("")) {
                            //pull old users PR
                            json.put("record", "0");
                        }
                        else {
                            json.put("record", squatsPR.getText().toString());
                        }
                        if (squatsSets.getText().toString().equals("")) {
                            json.put("sets", "0");
                        }
                        else {
                            json.put("sets", squatsSets.getText().toString());
                        }
                        if (squatsReps.getText().toString().equals("")) {
                            json.put("reps", "0");
                        }
                        else {
                            json.put("reps", squatsReps.getText().toString());
                        }
                        jsonArr.put(json);
                    }
                    if (bench.isChecked()) {
                        JSONObject json = new JSONObject();
                        json.put("name", "bench");
                        if (benchPR.getText().equals(null)) {
                            //pull old users PR
                            json.put("record", "0");
                        }
                        else {
                            json.put("record", benchPR.getText().toString());
                        }
                        if (benchSets.getText().toString().equals("")) {
                            json.put("sets", "0");
                        }
                        else {
                            json.put("sets", benchSets.getText().toString());
                        }
                        if (benchReps.getText().toString().equals("")) {
                            json.put("reps", "0");
                        }
                        else {
                            json.put("reps", benchReps.getText().toString());
                        }
                        jsonArr.put(json);

                    }
                    if (deadlift.isChecked()) {
                        JSONObject json = new JSONObject();
                        json.put("name", "deadlift");
                        if (!deadliftPR.getText().equals("")) {
                            //pull old users PR
                        }
                        else {
                            json.put("record", deadliftPR.getText().toString());
                        }
                        if (deadliftSets.getText().toString().equals("")) {
                            json.put("sets", "0");
                        }
                        else {
                            json.put("sets", deadliftSets.getText().toString());
                        }
                        if (deadliftReps.getText().toString().equals("")) {
                            json.put("reps", "0");
                        }
                        else {
                            json.put("reps", deadliftReps.getText().toString());
                        }
                        jsonArr.put(json);

                    }
                    if (run.isChecked()) {
                        JSONObject json = new JSONObject();
                        json.put("name", "run");
                        if (!runPR.getText().equals("")) {
                            //pull old users PR
                        }
                        else {
                            json.put("record", runPR.getText().toString());
                        }
                        if (runSets.getText().toString().equals("")) {
                            json.put("sets", "0");
                        }
                        else {
                            json.put("sets", runSets.getText().toString());
                        }
                        if (runReps.getText().toString().equals("")) {
                            json.put("reps", "0");
                        }
                        else {
                            json.put("reps", runReps.getText().toString());
                        }
                        jsonArr.put(json);

                    }
                    if (crunch.isChecked()){
                        JSONObject json = new JSONObject();
                        json.put("name", "crunch");
                        if (!crunchPR.getText().equals("")) {
                            //pull old users PR
                        }
                        else {
                            json.put("record", crunchPR.getText().toString());
                        }
                        if (crunchSets.getText().toString().equals("")) {
                            json.put("sets", "0");
                        }
                        else {
                            json.put("sets", crunchSets.getText().toString());
                        }
                        if (crunchReps.getText().toString().equals("")) {
                            json.put("reps", "0");
                        }
                        else {
                            json.put("reps", crunchReps.getText().toString());
                        }
                        jsonArr.put(json);

                    }
                    //debug.setText(jsonArr.toString());

                    String postWORKOUT = BASE_POSTROUTINE_URL + getIntent().getStringExtra("username") + "/routines";
                    //name: "name", workouts: []

                    debug.setText(debug.getText().toString() + "\n" + postWORKOUT);

                    JSONObject routineJSON = new JSONObject();
                    if (!customName.getText().toString().equals(null)) {
                        routineJSON.put("name", customName.getText().toString());
                    }
                    else {
                        routineJSON.put("name", getIntent().getStringExtra("username") + "'s_workout");
                    }

                    routineJSON.put("workouts", new JSONArray());
                    //routineJSON.put("workouts", jsonArr);

                    debug.setText(debug.getText().toString() + "\n" + routineJSON.toString());

                    JsonObjectRequest postEmptyRoutineRequest = new JsonObjectRequest(Request.Method.POST, postWORKOUT, routineJSON, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Toast.makeText(PostWorkout.this, "Routine Made!", Toast.LENGTH_SHORT).show();
                                debug.setText(debug.getText().toString() + "\n" + response.toString());

                                add_workouts(jsonArr);
//                                Intent intent = new Intent(PostWorkout.this, ProfilePage.class);
//                                intent.putExtra("username", getIntent().getStringExtra("username"));
//                                startActivity(intent);
//                                finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(PostWorkout.this, "Error Posting your Event",
                                    Toast.LENGTH_LONG).show();
                            debug.setText(debug.getText().toString() + "\nError Posting your Event");
                        }
                    });

                    RequestQueue requestQueue;
                    requestQueue = Volley.newRequestQueue(PostWorkout.this);
                    requestQueue.add(postEmptyRoutineRequest);
                    //requestQueue.add(jsonObjectRequest);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PostWorkout.this, HomePage.class);
                i.putExtra("username", getIntent().getStringExtra("username"));
                startActivity(i);
                finish();
            }
        });
    }
    private void add_workouts(JSONArray workouts) {
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
                        JSONObject newRoutine;
                        int id;

                        try {
                            newRoutine = findMaxID(response);

                            debug.setText(debug.getText().toString() + "\n\n" + newRoutine.toString() + "\n\nAdding workouts... to" + newRoutine.getInt("id") + "\n");
                            id = newRoutine.getInt("id");
                            String addWorkoutURL = "http://coms-309-067.class.las.iastate.edu:8080/routine/" + id + "/workout";;
                            //post selected workouts to newRoutine
                            for (int i = 0; i < workouts.length(); i++) {

                                //JSONObject curWorkout = workouts.getJSONObject(i);
                                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                        (Request.Method.POST, addWorkoutURL, workouts.getJSONObject(i), new Response.Listener<JSONObject>() {

                                            @Override
                                            public void onResponse(JSONObject response) {
                                                //keep posting workouts
                                                debug.setText(debug.getText().toString() + "\nWorkout ");


                                            }
                                        }, new Response.ErrorListener() {

                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(PostWorkout.this, "Error Posting Workout details", Toast.LENGTH_LONG).show();
                                            }
                                        });


                                RequestQueue rq;
                                rq = Volley.newRequestQueue(PostWorkout.this);
                                rq.add(jsonObjectRequest);
                            }


                            Toast.makeText(PostWorkout.this, response.toString(), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }



                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PostWorkout.this, "Error getting User details", Toast.LENGTH_LONG).show();
                    }
                });


        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(PostWorkout.this);
        requestQueue.add(jsonArrayRequest);

    }
    private static JSONObject findMaxID(JSONArray arr) {
        int maxID, curID, index = 0;
        try {
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
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}