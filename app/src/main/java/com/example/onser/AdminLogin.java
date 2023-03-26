package com.example.onser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    private EditText password;

    private Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        password= (EditText) findViewById(R.id.admin_loginpass);



        button=(Button) findViewById(R.id.admin_login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass= password.getText().toString().trim();
                String pass1="admin";
                if(pass.equals(pass1)){
                    startActivity(new Intent(AdminLogin.this,Admin_panel.class));
                    finish();
                    Toast.makeText(AdminLogin.this, "Admin Login Successful", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AdminLogin.this, "Password Doesn't Match! Try Again...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}