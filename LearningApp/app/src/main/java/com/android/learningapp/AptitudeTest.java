package com.android.learningapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import java.util.ArrayList;
import java.util.List;

public class AptitudeTest extends AppCompatActivity implements MCQDataCallback{
    FragmentManager manager;
    Fragment fragment_mcq;
    List<MCQ> mcqList;
    int currentQuestionIndex;

    Button btnNext, btnFinish;
    TextView tvMcqQuestion, tvMcqQuestionNumber;
    RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    ProgressBar pbProgressBar;
    ImageView ivQuestionImage;

    int score;

    private void initializeViews() {
        manager = getSupportFragmentManager();
        fragment_mcq = manager.findFragmentById(R.id.fragment_container);
        assert fragment_mcq != null;
        View rootView = fragment_mcq.requireView();

        tvMcqQuestionNumber = rootView.findViewById(R.id.tvMcqQuestionNumber);
        tvMcqQuestion = rootView.findViewById(R.id.tvMcqQuestion);
        ivQuestionImage = rootView.findViewById(R.id.ivQuestionImage);
        rbOption1 = rootView.findViewById(R.id.rbOption1);
        rbOption2 = rootView.findViewById(R.id.rbOption2);
        rbOption3 = rootView.findViewById(R.id.rbOption3);
        rbOption4 = rootView.findViewById(R.id.rbOption4);
        btnNext = rootView.findViewById(R.id.btnNext);
        btnFinish = rootView.findViewById(R.id.btnFinish);
        btnFinish.setVisibility(View.GONE);

        pbProgressBar = rootView.findViewById(R.id.pbProgressBar);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex++;
                displayQuestion();
            }
        });

    btnFinish.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AptitudeTest.this, ApptitudeTestScore.class);
            intent.putExtra("score", score);
            startActivity(intent);
        }
    });
    }



    @Override
    public void onMCQDataLoaded(ArrayList<MCQ> mcqs) {
        mcqList = mcqs;
        Toast.makeText(this, "MCQs loaded: " + mcqList.size(), Toast.LENGTH_SHORT).show();
        initializeViews();
        displayQuestion();
        Toast.makeText(this, "MCQs loaded: " + mcqList.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataLoadFailed(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void displayQuestion() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < mcqList.size()) {
            MCQ currentMCQ = mcqList.get(currentQuestionIndex);
            tvMcqQuestionNumber.setText("Question: " + String.valueOf(currentQuestionIndex + 1));
            tvMcqQuestion.setText(currentMCQ.getQuestion());
            ivQuestionImage.setImageURI(Uri.parse(currentMCQ.getImgURl()));
            rbOption1.setText(currentMCQ.getOptions().get(0));
            rbOption2.setText(currentMCQ.getOptions().get(1));
            rbOption3.setText(currentMCQ.getOptions().get(2));
            rbOption4.setText(currentMCQ.getOptions().get(3));

            int percentage = ((currentQuestionIndex + 1 )* 10) + (100 - (mcqList.size() * 10)) ;
            pbProgressBar.setProgress(percentage,true);

            rbOption1.setOnClickListener(view -> handleOptionSelected(0));
            rbOption2.setOnClickListener(view -> handleOptionSelected(1));
            rbOption3.setOnClickListener(view -> handleOptionSelected(2));
            rbOption4.setOnClickListener(view -> handleOptionSelected(3));

            rbOption1.setChecked(false);
            rbOption2.setChecked(false);
            rbOption3.setChecked(false);
            rbOption4.setChecked(false);

        }
        else {
            btnFinish.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.GONE);
        }
    }

    private void handleOptionSelected(int optionIndex) {
        // Check if the selected option is correct
        MCQ currentMCQ = mcqList.get(currentQuestionIndex);
        if (optionIndex == currentMCQ.getCorrectAnswerIndex()) {
            score += 10;
        } else {
            score += 0;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude_test);
        FirebaseUtils firebaseUtils = new FirebaseUtils(this);
        firebaseUtils.getApptitudeTestMcqs(this);
    }
}