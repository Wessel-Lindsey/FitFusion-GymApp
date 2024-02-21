package com.example.javadoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

//import com.example.javadoc.databinding
//import com.example.javadoc.databinding.ActivityHomePageBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class HomePage extends AppCompatActivity {

//    private ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
//        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        Intent intent = getIntent();
        TextView helloUserTxt = findViewById(R.id.helloUserTxt);
        helloUserTxt.setText("Hello " + intent.getStringExtra(LoginPage.USERNAME));

//        BottomNavigationView navView = findViewById(R.id.nav_view);
//         // Passing each menu ID as a set of Ids because each
//         // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);

//        Toolbar toolbar = binding.toolbar;
//        setSupportActionBar(toolbar);
//        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
//        toolBarLayout.setTitle(getTitle());

//        FloatingActionButton fab = binding.UserProfileFAB;
//        fab.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view The view that was clicked.
             */
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        FloatingActionButton dmBtn = findViewById(R.id.messagesFAB);
        FloatingActionButton profile = findViewById(R.id.UserProfileFAB);
        FloatingActionButton workout = findViewById(R.id.workoutsFAB);
        FloatingActionButton search = findViewById(R.id.searchFAB);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(HomePage.this, SearchPage.class);
                intent2.putExtra("username", intent.getStringExtra(LoginPage.USERNAME));
                startActivity(intent2);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view The view that was clicked.
             */
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(HomePage.this, ProfilePage.class);
                intent2.putExtra("username", intent.getStringExtra(LoginPage.USERNAME));
                startActivity(intent2);
                finish();
            }
        });

        workout.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view The view that was clicked.
             */
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(HomePage.this, PostWorkout.class);
                intent2.putExtra("username", intent.getStringExtra(LoginPage.USERNAME));
                startActivity(intent2);
                finish();
            }
        });

        dmBtn.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view The view that was clicked.
             */
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(HomePage.this, openUserDM.class);
                intent2.putExtra("username", intent.getStringExtra(LoginPage.USERNAME));
                startActivity(intent2);
                finish();
            }
        });

        FloatingActionButton myGymBtn = findViewById(R.id.myGymFAB);
        myGymBtn.setOnClickListener(new View.OnClickListener() {

            /**
             *
             *
             * @param v The view that was clicked.
             */

            @Override
            public void onClick(View v) {
                //open a group chat of the users gym
                //need a call to get a users gym

                Intent i = new Intent(HomePage.this, MyGymChat.class);
                i.putExtra("username", getIntent().getStringExtra("username").toString());
                i.putExtra("myGym", "sonoco");
                startActivity(i);
                finish();
            }
        });

        FloatingActionButton challengeBtn = findViewById(R.id.challenge);
        challengeBtn.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                //open a group chat of the users gym
                //need a call to get a users gym

                Intent i = new Intent(HomePage.this, challengesPage.class);
                i.putExtra("username", getIntent().getStringExtra("username").toString());
                startActivity(i);
                finish();
            }
        });

    //        WebSocketManager.getInstance().connectWebSocket(serverUrl);
//        WebSocketManager.getInstance().setWebSocketListener(challengesPage.this);
//
//


//        createChallenge.setOnClickListener(v -> {
//            try {
//                // send challenge
//                WebSocketManager.getInstance().sendMessage(newChallenge.getText().toString());
//            } catch (Exception e) {
//                Log.d("ExceptionSendMessage:", e.getMessage().toString());
//            }
//        });

    }
//    You have been challanged by " + *username* + " to " *challenge*);
//    @Override
//    public void onWebSocketMessage(String message){
//        runOnUiThread(() -> {
//            String s = showChallenges.getText().toString();
//            showChallenges.setText(s + "\n" + message);
//                });
//    }
//
//    @Override
//    public void onWebSocketClose(int code, String reason, boolean remote) {
//        String closedBy = remote ? "server" : "local";
//        runOnUiThread(() -> {
//            String s = showChallenges.getText().toString();
//            showChallenges.setText(s + "---\nconnection closed by " + closedBy + "\nreason: " + reason);
//        });
//    }

//    @Override
//    public void onWebSocketOpen(ServerHandshake handshakedata) {}
//
//    @Override
//    public void onWebSocketError(Exception ex) {}
}
