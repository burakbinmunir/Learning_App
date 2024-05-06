package com.android.learningapp;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FirebaseUtils {
    private FirebaseDatabase database;
    Context context;

    public FirebaseUtils(Context context){
        database = FirebaseDatabase.getInstance("https://learningapp-1302-default-rtdb.asia-southeast1.firebasedatabase.app/");
        this.context = context;
    }

    public void uploadAptitudeTestMcq(MCQ mcq){
        DatabaseReference mcqRef = database.getReference("aptitudeTestMcqs");

        mcqRef.push().setValue(mcq).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "MCQ uploaded successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "MCQ uploaded failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getApptitudeTestMcqs(MCQDataCallback callback) {
        DatabaseReference mcqRef = database.getReference("aptitudeTestMcqs");

        mcqRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<MCQ> mcqs = new ArrayList<>();
                    DataSnapshot dataSnapshot = task.getResult();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        MCQ mcq = child.getValue(MCQ.class);
                        mcqs.add(mcq);
                    }
                    callback.onMCQDataLoaded(mcqs);
                } else {
                    callback.onDataLoadFailed("Failed to get MCQs: " + task.getException());
                }
            }
        });
    }


}