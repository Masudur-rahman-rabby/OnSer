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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView forgetpassword,reg;
    private EditText editemail,editpass;
    private Button login;


    //private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //progressBar=(ProgressBar)findViewById(R.id.progressbarLogin);

        mAuth=FirebaseAuth.getInstance();
        editemail=(EditText)findViewById(R.id.email);
        editpass=(EditText)findViewById(R.id.loginpass);

        reg =(TextView) findViewById(R.id.register);
        reg.setOnClickListener(this);

        login=(Button) findViewById(R.id.login);
        login.setOnClickListener(this);

        forgetpassword=(TextView) findViewById(R.id.forgetpass);
        forgetpassword.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
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
            case R.id.register:
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
                finish();
                break;

            case R.id.login:
                userlogin();
                break;

            case R.id.forgetpass:
                startActivity(new Intent(MainActivity.this,ForgetPassword.class));
                finish();
                break;

        }

    }

    private void userlogin() {
        ProgressDialog dialog=new ProgressDialog(MainActivity.this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please Wait...");
        dialog.show();
        String loginemail= editemail.getText().toString().trim();
        String loginpass= editpass.getText().toString().trim();

        if(loginemail.isEmpty())
        {
            dialog.dismiss();
            editemail.setError("Email can't be empty");
            editemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(loginemail).matches()){
            dialog.dismiss();
            editemail.setError("Enter valid email");
            editemail.requestFocus();
            return;
        }
        if (loginpass.isEmpty()){
            dialog.dismiss();
            editpass.setError("Password can't be empty");
            editpass.requestFocus();
            return;
        }
        if(loginpass.length()<6){
            dialog.dismiss();
            editpass.setError("Password length should be greater than 6 char");
            editpass.requestFocus();
            return;
        }
        //progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(loginemail,loginpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

                            if(firebaseUser.isEmailVerified()){
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, "LogIn Successful...", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,Login_preference.class));
                                finish();
                                //progressBar.setVisibility(View.GONE);
                            }else{
                                dialog.dismiss();
                                firebaseUser.sendEmailVerification();
                                Toast.makeText(MainActivity.this, "Verify your email!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, "LogIn Failed! Try again...", Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}