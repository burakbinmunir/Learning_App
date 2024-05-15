package com.android.learningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CourseDetailActivity extends AppCompatActivity {
    private TextView mCourseTitleTextView;
    private TextView mCourseContentTextView;
    private TextView mExerciseTextView;
    private EditText mAnswerEditText;
    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        mCourseTitleTextView = findViewById(R.id.course_title_text_view);
        mCourseContentTextView = findViewById(R.id.course_content_text_view);
        mExerciseTextView = findViewById(R.id.exercise_text_view);
        mAnswerEditText = findViewById(R.id.answer_edit_text);
        mSubmitButton = findViewById(R.id.submit_button);

        Course course = getIntent().getParcelableExtra("course");
        mCourseTitleTextView.setText(course.getTitle());
        mCourseContentTextView.setText(course.getCourseContent());
        mExerciseTextView.setText(course.getExercise());

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = mAnswerEditText.getText().toString();
                if (answer.equals(course.getSolution())) {
                    // download certificate and congratulate
                    Toast.makeText(v.getContext(), "Congratulations! You have completed the course.", Toast.LENGTH_SHORT).show();
                    // download certificate logic here
                    finish();
                } else {
                    Toast.makeText(v.getContext(), "Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}