package com.example.onser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private TextView banner,exit;
    private EditText fullname,email,number,pass,address;
    private Button register;

    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    //@SuppressLint("MissingInflatedId")
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth= FirebaseAuth.getInstance();


        register= (Button) findViewById(R.id.register2);
        register.setOnClickListener(this);

        fullname= findViewById(R.id.fullname);
        email= findViewById(R.id.regemail);
        number= findViewById(R.id.number);
        pass = findViewById(R.id.regpassword);
        address= findViewById(R.id.address);

        exit= findViewById(R.id.backspace_reg);
        exit.setOnClickListener(this);


        //progressBar=(ProgressBar) findViewById(R.id.progressbarreg);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert= new AlertDialog.Builder(Register.this);
        alert.setTitle("Exit OnSer");
        alert.setMessage("Do you want to exit?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){



            case R.id.register2:
                registeruser();
                break;
            case R.id.backspace_reg:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    private void registeruser(){

        ProgressDialog dialog=new ProgressDialog(Register.this);
        dialog.setTitle("Registering Your Profile");
        dialog.setMessage("Please Wait...");
        dialog.show();

        String fn= fullname.getText().toString().trim();
        String em= email.getText().toString().trim();
        String num= number.getText().toString().trim();
        String password= pass.getText().toString().trim();
        String add= address.getText().toString().trim();


        //Getuser getuser= new Getuser(fn,em,num,password);
        if(fn.isEmpty()){
            dialog.dismiss();
            fullname.setError("Name can't be empty");
            fullname.requestFocus();
            return;
        }
        if(em.isEmpty())
        {
            dialog.dismiss();
            email.setError("Email can't be empty");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
            dialog.dismiss();
            email.setError("Enter valid email");
            email.requestFocus();
            return;
        }
        if(num.isEmpty()){
            dialog.dismiss();
            number.setError("Phone number can't be empty");
            number.requestFocus();
            return;
        }
        if (password.isEmpty()){
            dialog.dismiss();
            pass.setError("Password can't be empty");
            pass.requestFocus();
            return;
        }
        if(password.length()<6){
            dialog.dismiss();
            pass.setError("Password length should be greater than 6 char");
            pass.requestFocus();
            return;
        }
        if(add.isEmpty()){
            dialog.dismiss();
            address.setError("Address must be given");
            address.requestFocus();
            return;
        }


        //progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(em,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Getuser getuser=new Getuser(fn,em,num,add);
                            FirebaseDatabase.getInstance().getReference("Getuser")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(getuser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                dialog.dismiss();
                                                Toast.makeText(Register.this, "Register is successful...", Toast.LENGTH_SHORT).show();
                                                //login preference
                                                Intent intent=new Intent(Register.this,MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                                //progressBar.setVisibility(View.GONE);
                                            }else {
                                                dialog.dismiss();
                                                Toast.makeText(Register.this, "Failed to register! Try again...", Toast.LENGTH_SHORT).show();
                                                //progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }else {
                            dialog.dismiss();
                            Toast.makeText(Register.this, "Failed to register! Try again...", Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}