package com.android.learningapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MathsTestSat extends AppCompatActivity {

    EditText answer1, answer2, answer3, answer4, answer5;
    TextView timer;

    Button submitAnswers;
    int score;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_maths_test_sat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
    }

    private boolean checkFields() {
        String ans1 = answer1.getText().toString();
        String ans2 = answer2.getText().toString();
        String ans3 = answer3.getText().toString();
        String ans4 = answer4.getText().toString();
        String ans5 = answer5.getText().toString();

        // check all answers are filled
        if (ans1.isEmpty()) {
            answer1.setError("Please fill this field");
            return false;
        }
        if (ans2.isEmpty()) {
            answer2.setError("Please fill this field");
            return false;
        }
        if (ans3.isEmpty()) {
            answer3.setError("Please fill this field");
            return false;
        }
        if (ans4.isEmpty()) {
            answer4.setError("Please fill this field");
            return false;
        }
        if (ans5.isEmpty()) {
            answer5.setError("Please fill this field");
            return false;
        }
        return true;
    }

    private void init() {
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        answer5 = findViewById(R.id.answer5);

        submitAnswers = findViewById(R.id.submitAnswers);
        timer = findViewById(R.id.timer);
        score = 0;

        countDownTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("Time remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                Intent intent = new Intent(MathsTestSat.this, ApptitudeTestScore.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        }.start();

        submitAnswers.setOnClickListener(v -> {
            String ans1 = answer1.getText().toString();
            String ans2 = answer2.getText().toString();
            String ans3 = answer3.getText().toString();
            String ans4 = answer4.getText().toString();
            String ans5 = answer5.getText().toString();

            // check all answers are filled
            if (checkFields() == true ){
                // check answers
                if (ans1.equals("16")) {
                    score+=20;
                }
                if (ans2.equals("8")) {
                    score+=20;
                }
                if (ans3.equals("9")) {
                    score+=20;
                }
                if (ans4.equals("25")) {
                    score+=20;
                }
                if (ans5.equals("3")) {
                    score+=20;
                }

                Intent intent = new Intent(MathsTestSat.this, ApptitudeTestScore.class);
                intent.putExtra("score", score);
                startActivity(intent);

                countDownTimer.cancel();
            }
        });

    }

    @Override
    public void onBackPressed() {
        // show dialog to confirm exit

        AlertDialog.Builder builder = new AlertDialog.Builder(MathsTestSat.this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            // close activity
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            // close dialog
            dialog.dismiss();
        });
        builder.show();


        countDownTimer.cancel();
    }
}