package com.android.learningapp;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseUtils {
    private static FirebaseUtils instance;
    private FirebaseDatabase database;
    Context context;
    FirebaseUser currentUser;

    private FirebaseUtils(Context context){
        database = FirebaseDatabase.getInstance("https://learningapp-1302-default-rtdb.asia-southeast1.firebasedatabase.app/");
        this.context = context;
    }

    public static FirebaseUtils getInstance(Context context) {
        if (instance == null) {
            instance = new FirebaseUtils(context);
        }
        return instance;
    }
    public void setCurrentUser(FirebaseUser user) {

        this.currentUser = user;
    }

    public void getUserProfile(ShapeableImageView profileImage, TextView username, TextView bio, TextView email, TextView username2, TextView mobile){

        DatabaseReference userRef = database.getReference("users").child(currentUser.getUid());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    if (user.getUserPhotoUrl() == null) {
                        profileImage.setImageResource(R.drawable.account_circle);
                    } else {
                        ImageUtils.loadImage(context, user.getUserPhotoUrl(), profileImage);
                    }

                    if (user.getUserDisplayName() == null) {
                        username.setText("---");
                    } else {
                        username.setText(user.getUserDisplayName());
                    }

                    if (user.getBio() == null) {
                        bio.setText("---");
                    } else {
                        bio.setText(user.getBio());
                    }

                    if (user.getUserEmail() == null) {
                        email.setText("---");
                    } else {
                        email.setText(user.getUserEmail());
                    }

                    if (user.getUserPhoneNumber() == null) {
                        mobile.setText("---");
                    } else {
                        mobile.setText(user.getUserPhoneNumber());
                    }

                    if (user.getUserDisplayName() == null) {
                        username2.setText("---");
                    } else {
                        username2.setText(user.getUserDisplayName());
                    }

                    if (user.getUserEmail() == null){
                        email.setText("---");
                    } else {
                        email.setText(user.getUserEmail());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Failed to get user profile: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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

    public void uploadEcatTestMcq(MCQ mcq, String subjectName){
        DatabaseReference mcqRef = database.getReference("ecat"+subjectName+"TestMcqs");

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

    public void getEcatTestMcqs(String subjectName, MCQDataCallback callback) {
        DatabaseReference mcqRef = database.getReference("ecat"+subjectName+"TestMcqs");

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

    public void uploadBlog(Blog blog){
        DatabaseReference blogRef = database.getReference("blogs");

        blogRef.push().setValue(blog).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Blog uploaded successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Blog uploaded failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public ArrayList<Blog> getBlogs(BlogFetchListener listener) {
        DatabaseReference blogRef = database.getReference("blogs");
        ArrayList<Blog> blogs = new ArrayList<>();
        blogRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Blog blog = child.getValue(Blog.class);
                    assert blog != null;
                    blog.setBlogKey(child.getKey());
                    blogs.add(blog);
                }
                listener.onBlogsFetched(blogs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFetchFailed(databaseError.getMessage());
            }
        });
        return blogs;
    }

    public void deleteBlog(Blog blog) {
        DatabaseReference blogRef = database.getReference("blogs");
        blogRef.child(blog.getBlogKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Blog deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to delete blog: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateBlog(Blog blog) {
        DatabaseReference blogRef = database.getReference("blogs");
        blogRef.child(blog.getBlogKey()).setValue(blog).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Blog updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to update blog: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateUserProfile(String string, String string1, String string2, String string3) {
        DatabaseReference userRef = database.getReference("users").child(currentUser.getUid());
        userRef.child("userDisplayName").setValue(string);
        userRef.child("bio").setValue(string1);
        userRef.child("userEmail").setValue(string2);
        userRef.child("userPhoneNumber").setValue(string3).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to update profile: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }
}
