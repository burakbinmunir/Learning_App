package com.android.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.Shapeable;

public class UserProfile extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ShapeableImageView profileImage;
    TextView username, bio, email, username2, mobile;
    ImageButton editProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UserProfile.this, Home.class);
        startActivity(intent);
    }

    private void init(){
        initializeBottomNavigation();
        profileImage = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        bio = findViewById(R.id.bio);
        email = findViewById(R.id.email);
        username2 = findViewById(R.id.username2);
        mobile = findViewById(R.id.mobile);

        FirebaseUtils firebaseUtils = FirebaseUtils.getInstance(this);
        firebaseUtils.getUserProfile( profileImage, username, bio, email, username2, mobile);

        bottomNavigationView.getMenu().getItem(2).setChecked(true);

        editProfile = findViewById(R.id.editProfile);
        editProfile.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfile.this, EditProfile.class);
            startActivity(intent);
        });
    }

    private void initializeBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.itemhome) {
                Intent intent = new Intent(UserProfile.this, Home.class);
                startActivity(intent);
                item.setChecked(true);
                return true;
            } else if (item.getItemId() == R.id.itemblog) {
                Intent intent = new Intent(UserProfile.this, Blogs.class);
                startActivity(intent);
                item.setChecked(true);
                return true;
            }
            else if (item.getItemId() == R.id.itemprofile) {
                Intent intent = new Intent(UserProfile.this, UserProfile.class);
                startActivity(intent);
                item.setChecked(true);
                return true;
            }
            return false;
        });
    }
}