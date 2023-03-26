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
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    private Button resetpass;
    private TextView exit;
    private EditText recovery_email;
    //private ProgressBar progressBar;
    FirebaseAuth auth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        resetpass=(Button) findViewById(R.id.resetpass_button);
        recovery_email=(EditText) findViewById(R.id.recoveryemail_editText);
        auth= FirebaseAuth.getInstance();
        //progressBar= (ProgressBar) findViewById(R.id.progressbar_forgetpass);

        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

        exit= findViewById(R.id.backspace);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassword.this,MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert= new AlertDialog.Builder(ForgetPassword.this);
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

    private void resetPassword() {

        ProgressDialog dialog=new ProgressDialog(ForgetPassword.this);
        dialog.setTitle("Recovering your PASSWORD");
        dialog.setMessage("Please Wait...");
        dialog.show();

        String recoveryEmail= recovery_email.getText().toString().trim();

        if(recoveryEmail.isEmpty()){
            dialog.dismiss();
            recovery_email.setError("Email can't be void");
            recovery_email.requestFocus();

            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(recoveryEmail).matches()){
            dialog.dismiss();
            recovery_email.setError("Enter a Valid Email");
            recovery_email.requestFocus();
            return;
        }
        //progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(recoveryEmail).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override

            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    dialog.dismiss();
                    startActivity(new Intent(ForgetPassword.this,MainActivity.class));
                    finish();
                    Toast.makeText(ForgetPassword.this, "Check Email to reset your password! ", Toast.LENGTH_SHORT).show();
                    //progressBar.setVisibility(View.GONE);
                }else {
                    dialog.dismiss();
                    Toast.makeText(ForgetPassword.this, "Something went wrong! ", Toast.LENGTH_SHORT).show();
                    //progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}