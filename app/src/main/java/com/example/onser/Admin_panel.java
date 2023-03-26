package com.example.onser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Admin_panel extends AppCompatActivity {

    private CardView back,userlist,addworkers,workerlist,booking_list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        back= (CardView) findViewById(R.id.admin_exit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_panel.this,Login_preference.class));
                finish();
            }
        });

        userlist= (CardView) findViewById(R.id.admin_user_information);
        userlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_panel.this,UserList.class));
            }
        });

        addworkers=(CardView) findViewById(R.id.admin_add_worker_details);
        addworkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_panel.this,Add_workers.class));
            }
        });
        workerlist=(CardView) findViewById(R.id.admin_worker_information);
        workerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_panel.this,WorkerList.class));
            }
        });

        booking_list=(CardView) findViewById(R.id.admin_service_booked);
        booking_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_panel.this,Admin_Booking_list.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert= new AlertDialog.Builder(Admin_panel.this);
        alert.setTitle("Exit Admin Panel");
        alert.setMessage("Exit or User Home?");
        alert.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        alert.setNegativeButton("USER HOME", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Admin_panel.this,Login_preference.class));
                finish();
            }
        });
        alert.show();
    }

}