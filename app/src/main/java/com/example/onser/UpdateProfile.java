package com.example.onser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateProfile extends AppCompatActivity{

    EditText name_et,address_et,phone_et;
    Button btn;

    FirebaseUser firebaseUser;
    String name,address,phone,uid;
    DatabaseReference reference;
    Getuser getuser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        name_et= findViewById(R.id.update_name);
        address_et= findViewById(R.id.update_address);
        phone_et= findViewById(R.id.update_number);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        uid= firebaseUser.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("Getuser");

        btn=findViewById(R.id.update_info_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = new ProgressDialog(UpdateProfile.this);
                dialog.setTitle("Updating Your Profile");
                dialog.setMessage("Please Wait...");
                dialog.show();
                name= name_et.getText().toString().trim();
                if(name.isEmpty()){
                    dialog.dismiss();
                    name_et.setError("Name can't be empty");
                    name_et.requestFocus();
                    return;
                }
                address= address_et.getText().toString().trim();
                if(address.isEmpty()){
                    dialog.dismiss();
                    address_et.setError("Email can't be empty");
                    address_et.requestFocus();
                    return;
                }
                phone= phone_et.getText().toString().trim();
                if(phone.isEmpty()){
                    dialog.dismiss();
                    phone_et.setError("Phone can't be empty");
                    phone_et.requestFocus();
                    return;
                }

                reference.child(uid).child("name").setValue(name);
                reference.child(uid).child("address").setValue(address);
                reference.child(uid).child("phonenumber").setValue(phone);
                Toast.makeText(UpdateProfile.this, "Data Updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateProfile.this,User_Information_show.class));
                dialog.dismiss();
                finish();
            }
        });

    }

//    private void update() {
//
//
//
//
//    }
//    private int is_change() {
//
//        int x=0;
//
//        name= name_et.getText().toString().trim();
//        if(!getuser.name.equals(name)){
//
//            x=1;
//        }
//        email= email_et.getText().toString().trim();
//        if(!getuser.email.equals(email)){
//
//            x=1;
//        }
//        phone= phone_et.getText().toString().trim();
//        if(!getuser.phonenumber.equals(phone)){
//
//            x=1;
//        }
//        if(x==1){return 1;}
//        else return 0;
//
//    }

}