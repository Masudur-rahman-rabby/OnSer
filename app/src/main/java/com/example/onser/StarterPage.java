package com.example.onser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StarterPage extends AppCompatActivity {

    private ProgressBar progressBar;
    int progress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter_page);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);

        mAuth = FirebaseAuth.getInstance();
        //getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar = (ProgressBar) findViewById(R.id.starterProgress);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                dowork();
                startApp();


            }
        });
        thread.start();
    }

    public void dowork() {
        for (progress = 10; progress <= 100; progress = progress + 50) {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void startApp() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            Intent intent = new Intent(StarterPage.this, Login_preference.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(StarterPage.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}