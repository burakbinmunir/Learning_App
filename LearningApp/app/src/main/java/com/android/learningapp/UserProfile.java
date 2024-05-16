package com.android.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.Shapeable;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity implements ScoresCallback , EnglishScoreCallBack{

    BottomNavigationView bottomNavigationView;
    ShapeableImageView profileImage;
    TextView username, bio, email, username2, mobile, physicsScore, englishScore;
    ImageButton editProfile;
    ProgressBar physicsProgressBar;
    SeekBar physicsSeekBar;
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
        physicsScore = findViewById(R.id.physicsScore);
        englishScore = findViewById(R.id.englishScore);

        FirebaseUtils firebaseUtils = FirebaseUtils.getInstance(this);
        firebaseUtils.getUserProfile( profileImage, username, bio, email, username2, mobile);

        bottomNavigationView.getMenu().getItem(2).setChecked(true);

        editProfile = findViewById(R.id.editProfile);
        editProfile.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfile.this, EditProfile.class);

            intent.putExtra("username", username.getText().toString());
            intent.putExtra("bio", bio.getText().toString());
            intent.putExtra("email", email.getText().toString());
            intent.putExtra("mobile", mobile.getText().toString());

            startActivity(intent);
        });
        initializeAnalytics();
    }

    private void initializeAnalytics() {
        FirebaseUtils firebaseUtils = FirebaseUtils.getInstance(this);
        firebaseUtils.getTestScores("physics", this);

        firebaseUtils.getEnglishTestScores(this);
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
            else if(item.getItemId() == R.id.itemcourse){
                Intent courseSelectionIntent = new Intent(this, CourseSelectionActivity.class);
                startActivity(courseSelectionIntent);
                item.setChecked(true);
                return true;
            }
            else if(item.getItemId() == R.id.itemchatbot){
                Intent ChatbotIntent = new Intent(this, ChatbotActivity.class);
                startActivity(ChatbotIntent);
                item.setChecked(true);
                return true;
            }
            return false;
        });
    }

    private int computeAverage(ArrayList<Integer> scores) {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }

    @Override
    public void onCallback(ArrayList<Integer> scores) {
            physicsScore.setText(String.valueOf(computeAverage(scores)));
    }

    @Override
    public void onEnglishScoreLoaded(ArrayList<Integer> scores) {
//        englishScore.setText("87");
        englishScore.setText(String.valueOf(computeAverage(scores)));

    }
}