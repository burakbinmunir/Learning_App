package com.android.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ApptitudeTestScore extends AppCompatActivity {

    TextView tvAptTestScore, tvAptTestScoreMessage;
    ProgressBar pbProgressBarAptTestScore;

    Button btnBackToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_apptitude_test_score);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
        tvAptTestScore = findViewById(R.id.tvAptTestScore);
        tvAptTestScoreMessage = findViewById(R.id.tvAptTestScoreMessage);
        pbProgressBarAptTestScore = findViewById(R.id.pbProgressBarAptTestScore);
        btnBackToHome = findViewById(R.id.btnBackToHome);

        Intent i = getIntent();
        int score = i.getIntExtra("score", 0);
        tvAptTestScore.setText("Your Score: " + score + "%");

        if (score < 50) {
            tvAptTestScoreMessage.setText("You need to practice more to improve your score.");
        } else if (score < 80) {
            tvAptTestScoreMessage.setText("Good job! You are on the right track.");
        } else {
            tvAptTestScoreMessage.setText("Excellent! You have a good understanding of the concepts.");
        }

        pbProgressBarAptTestScore.setProgress(score, true);

        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(ApptitudeTestScore.this, MockTests.class);
            startActivity(intent);
        });
    }
}