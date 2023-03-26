package com.example.onser;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOworkers {
    private DatabaseReference databaseReference;
    DAOworkers(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference(workers.class.getSimpleName());
    }

    public Task<Void> remove(String key) {
        return databaseReference.child(key).removeValue();
    }
}
