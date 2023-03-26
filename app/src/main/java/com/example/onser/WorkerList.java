package com.example.onser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WorkerList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<workers> w;
    worker_adapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list);

        ProgressDialog dialog=new ProgressDialog(WorkerList.this);
        dialog.setTitle("Loading List");
        dialog.setMessage("Please Wait...");
        dialog.show();

        recyclerView=findViewById(R.id.worker_details);
        databaseReference= FirebaseDatabase.getInstance().getReference("workers");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        w= new  ArrayList<>();
        adapter1 =new worker_adapter(this,w);
        recyclerView.setAdapter(adapter1);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    workers ww= dataSnapshot.getValue(workers.class);
                    ww.setKey(dataSnapshot.getKey());
                    w.add(ww);
                }
                adapter1.notifyDataSetChanged();
                dialog.dismiss();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(WorkerList.this,Admin_panel.class));
        finish();
    }
}