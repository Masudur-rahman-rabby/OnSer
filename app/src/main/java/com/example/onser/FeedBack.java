package com.example.onser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedBack extends AppCompatActivity {

    EditText fb;
    DatabaseReference databaseReference;
    Button btn;
    fb fbc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        fb= findViewById(R.id.feedback_text);
        btn= findViewById(R.id.feedback_submit);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("feedback");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback = fb.getText().toString().trim();
                fbc=new fb(feedback);
                databaseReference.push().setValue(fbc);
                AlertDialog.Builder alert= new AlertDialog.Builder(FeedBack.this);
                alert.setTitle("Thanks For Your Feedback!");
                alert.setMessage("Your Feedback Recorded");
                alert.setIcon(R.drawable.confirmed_image);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(FeedBack.this,Login_preference.class));
                        finish();
                    }
                });
                AlertDialog alertDialog= alert.create();
                alertDialog.show();
                //Toast.makeText(FeedBack.this, "You Feedback is Recorded", Toast.LENGTH_SHORT).show();

            }
        });

    }
}