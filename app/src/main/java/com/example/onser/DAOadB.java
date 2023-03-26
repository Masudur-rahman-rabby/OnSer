package com.example.onser;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOadB {

    private DatabaseReference databaseReference;
    DAOadB(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("admin_booking_details");
    }

    public Task<Void> remove(String key) {
        return databaseReference.child(key).removeValue();
    }
}
