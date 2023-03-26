package com.example.onser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_Booking_list extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<BookingClass> b;
    admin_booking_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking_list);

        ProgressDialog dialog=new ProgressDialog(Admin_Booking_list.this);
        dialog.setTitle("Loading List");
        dialog.setMessage("Please Wait...");
        dialog.show();

        recyclerView=findViewById(R.id.ad_booking_details);
        databaseReference= FirebaseDatabase.getInstance().getReference("admin_booking_details");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        b= new  ArrayList<>();
        adapter =new admin_booking_adapter(this,b);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    BookingClass ww= dataSnapshot.getValue(BookingClass.class);
                    ww.setKey(dataSnapshot.getKey());
                    b.add(ww);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Admin_Booking_list.this,Admin_panel.class));
        finish();
    }
}