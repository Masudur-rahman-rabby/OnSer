package com.example.onser;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOhistory {

    private DatabaseReference databaseReference;
    DAOhistory(){

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("BookingInformation").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    }

    public Task<Void> remove(String key) {
        return databaseReference.child(key).removeValue();
    }
}
