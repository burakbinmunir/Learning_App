package com.android.learningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class AptitudeTest extends AppCompatActivity {
    FragmentManager manager;
    Fragment fragment_mcq;
    List<MCQ> mcqList;
    int currentQuestionIndex;

    Button btnNext;
    TextView tvMcqQuestion;
    RadioButton rbOption1, rbOption2, rbOption3, rbOption4;

    private void initializeViews() {
        manager = getSupportFragmentManager();
        fragment_mcq = manager.findFragmentById(R.id.fragment_container);
        assert fragment_mcq != null;
        View rootView = fragment_mcq.requireView();
        tvMcqQuestion = rootView.findViewById(R.id.tvMcqQuestion);
        rbOption1 = rootView.findViewById(R.id.rbOption1);
        rbOption2 = rootView.findViewById(R.id.rbOption2);
        rbOption3 = rootView.findViewById(R.id.rbOption3);
        rbOption4 = rootView.findViewById(R.id.rbOption4);
        btnNext = rootView.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex++;
                displayQuestion();
            }
        });
    }



    private void loadMCQs() {
        mcqList = new ArrayList<MCQ>();

        mcqList.add(new MCQ("What is the capital of France?", new String[]{"Paris", "London", "Pakistan", "Istanbul"}, 0));
        mcqList.add(new MCQ("What is the largest planet in our solar system?", new String[]{"Jupiter", "Mars", "Earth", "Mercury"}, 0));
        mcqList.add(new MCQ("Who wrote 'To Kill a Mockingbird'?", new String[]{"Harper Lee", "J.K. Rowling", "J.K. Rowling", "J.K. Rowling"}, 0));

    }

    private void displayQuestion() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < mcqList.size()) {
            MCQ currentMCQ = mcqList.get(currentQuestionIndex);
            tvMcqQuestion.setText(currentMCQ.getQuestion());
            rbOption1.setText(currentMCQ.getOptions()[0]);
            rbOption2.setText(currentMCQ.getOptions()[1]);
            rbOption3.setText(currentMCQ.getOptions()[2]);
            rbOption4.setText(currentMCQ.getOptions()[3]);
            // Set other UI elements with MCQ data

            // Set click listeners for radio buttons
            rbOption1.setOnClickListener(view -> handleOptionSelected(0));
            rbOption2.setOnClickListener(view -> handleOptionSelected(1));
            rbOption3.setOnClickListener(view -> handleOptionSelected(2));
            rbOption4.setOnClickListener(view -> handleOptionSelected(3));
        }
    }

    private void handleOptionSelected(int optionIndex) {
        // Check if the selected option is correct
        MCQ currentMCQ = mcqList.get(currentQuestionIndex);
        if (optionIndex == currentMCQ.getCorrectAnswerIndex()) {
            // Handle correct answer
        } else {
            // Handle incorrect answer
        }

        // Optionally, disable radio buttons after selection to prevent multiple selections
//        rbOption1.setEnabled(false);
//        rbOption2.setEnabled(false);
//        rbOption3.setEnabled(false);
//        rbOption4.setEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude_test);
        initializeViews();
        loadMCQs();
        displayQuestion();
    }
}