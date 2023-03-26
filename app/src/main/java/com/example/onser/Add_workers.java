package com.example.onser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Add_workers extends AppCompatActivity {

    EditText name, number;
    String[] ser;
    Button add;
    DatabaseReference databaseReference;
    workers w;
    Spinner service;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workers);

        name = (EditText) findViewById(R.id.worker_fullname);
        number = (EditText) findViewById(R.id.number);
        service = (Spinner) findViewById(R.id.worker_service);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("workers");

        add = (Button) findViewById(R.id.add_worker);
        add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                String wname = name.getText().toString().trim();
                String wnum = number.getText().toString().trim();
                String wservice = service.getSelectedItem().toString().trim();

                if (wname.isEmpty()) {
                    name.setError("Please Enter Full name of Worker");
                    name.requestFocus();
                    return;
                }
                if (wnum.isEmpty()) {
                    number.setError("Please Enter Number of Worker");
                    number.requestFocus();
                    return;
                }
                w = new workers(wname, wnum, wservice);

                    databaseReference.push().setValue(w);
                    Toast.makeText(Add_workers.this, "Worker Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Add_workers.this, workeraddingconfirmation.class);
                    intent.putExtra("worker_name", wname);
                    intent.putExtra("worker_number", wnum);
                    intent.putExtra("worker_service", wservice);
                    startActivity(intent);
                    finish();
            }

        });
    }
}