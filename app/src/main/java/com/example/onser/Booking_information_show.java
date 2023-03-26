package com.example.onser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Booking_information_show extends AppCompatActivity {

    String service;
    String address;
    String date;
    String time,cost;

    private FirebaseUser user2;
    private DatabaseReference reference2;

    private  String UserID2;

    private Button home;
    private TextView username_textview,phone_no_textview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_information_show);

        ProgressDialog dialog=new ProgressDialog(Booking_information_show.this);
        dialog.setTitle("Loading Booking Information");
        dialog.setMessage("Please Wait...");
        dialog.show();

        user2 = FirebaseAuth.getInstance().getCurrentUser();
        reference2 = FirebaseDatabase.getInstance().getReference("Getuser");
        UserID2 = user2.getUid();
        //String key= reference2.push().getKey();


        username_textview = (TextView) findViewById(R.id.booking_username);
        phone_no_textview = (TextView) findViewById(R.id.booking_phone_number);
        final TextView service_name_textview = (TextView) findViewById(R.id.booked_service);
        final TextView address_textview = (TextView) findViewById(R.id.booked_address);
        final TextView date_textview = (TextView) findViewById(R.id.booked_date);
        final TextView time_textview = (TextView) findViewById(R.id.booked_time);
        final TextView cost_textview= (TextView) findViewById(R.id.booking_cost);

        reference2.child(UserID2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Getuser getuser= snapshot.getValue(Getuser.class);
                if(getuser!= null){
                    String fullname= getuser.name;
                    String number= getuser.phonenumber;
                    username_textview.setText(fullname);
                    phone_no_textview.setText(number);
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Booking_information_show.this, "Something wrong happened...", Toast.LENGTH_SHORT).show();

            }
        });

        service= getIntent().getStringExtra("service_name");
        address= getIntent().getStringExtra("booking_address");
        date= getIntent().getStringExtra("booking_date");
        time= getIntent().getStringExtra("booking_time");
        cost= getIntent().getStringExtra("cost");


        service_name_textview.setText(service);
        address_textview.setText(address);
        date_textview.setText(date);
        time_textview.setText(time);
        cost_textview.setText(cost);

        home=(Button) findViewById(R.id.goto_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Booking_information_show.this,Payment.class));
                finish();
            }
        });
    }
}