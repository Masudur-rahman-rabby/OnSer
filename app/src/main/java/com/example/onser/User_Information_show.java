package com.example.onser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_Information_show extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private  String UserID;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information_show);

        ProgressDialog dialog=new ProgressDialog(User_Information_show.this);
        dialog.setTitle("Loading User Information");
        dialog.setMessage("Please Wait...");
        dialog.show();

        user= FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Getuser");
        UserID =user.getUid();

        final TextView fullname_textview= (TextView) findViewById(R.id.show_user_fullname);
        final TextView email_textview= (TextView) findViewById(R.id.show_user_email);
        final TextView phone_number_textview= (TextView) findViewById(R.id.show_user_number);
        final TextView address_textview = (TextView) findViewById(R.id.show_user_address);

        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Getuser getuser= snapshot.getValue(Getuser.class);

                if(getuser != null){
                    String fullname= getuser.name;
                    String email= getuser.email;
                    String address= getuser.address;
                    String number= getuser.phonenumber;


                    fullname_textview.setText(fullname);
                    email_textview.setText(email);
                    address_textview.setText(address);
                    phone_number_textview.setText(number);

                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
                Toast.makeText(User_Information_show.this, "Something wrong happened...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}