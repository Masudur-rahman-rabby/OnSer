package com.example.onser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<BookingClass> booking_list;
    history_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ProgressDialog dialog=new ProgressDialog(History.this);
        dialog.setTitle("Loading History");
        dialog.setMessage("Please Wait...");
        dialog.show();

        recyclerView=findViewById(R.id.history);
        databaseReference= FirebaseDatabase.getInstance().getReference("BookingInformation");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        booking_list= new  ArrayList<>();
        adapter =new history_adapter(this,booking_list);
        recyclerView.setAdapter(adapter);

        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    BookingClass bookingClass= dataSnapshot.getValue(BookingClass.class);
                    bookingClass.setKey(dataSnapshot.getKey());
                    booking_list.add(bookingClass);

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
        startActivity(new Intent(History.this,Login_preference.class));
        finish();
    }
}