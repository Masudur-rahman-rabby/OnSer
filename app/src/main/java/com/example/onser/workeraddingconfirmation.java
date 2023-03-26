package com.example.onser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class workeraddingconfirmation extends AppCompatActivity {

    String name,number,service;

    Button adminhome;
    TextView tname,tnum,tser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workeraddingconfirmation);

        tname=findViewById(R.id.name_of_worker);
        tnum=findViewById(R.id.number_of_worker);
        tser=findViewById(R.id.service_of_worker);

        service= getIntent().getStringExtra("worker_service");
        number= getIntent().getStringExtra("worker_number");
        name= getIntent().getStringExtra("worker_name");


        tser.setText(service);
        tname.setText(name);
        tnum.setText(number);

        adminhome=findViewById(R.id.goto_admin_home);
        adminhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(workeraddingconfirmation.this,Admin_panel.class));
                finish();
            }
        });
    }
}