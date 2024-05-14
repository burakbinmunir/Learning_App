package com.android.learningapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CareerPathRoadmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_path_roadmap);

        // Get selected career path from Intent extra
        String careerPath = getIntent().getStringExtra("careerPath");

        // Find TextView to display career path
        TextView textView = findViewById(R.id.textViewCareerPath);
        textView.setText("Roadmap for " + careerPath);

        // Dynamically render roadmap based on selected career path
        assert careerPath != null;
        renderRoadmap(careerPath);
    }

    private void renderRoadmap(String careerPath) {
        // Depending on the selected career path, dynamically render the roadmap
        TextView textViewRoadmap = findViewById(R.id.textViewRoadmap);
        switch (careerPath) {
            case "Software Engineer":
                //textViewRoadmap.setText(getString(R.string.software_engineer_roadmap));
                break;
            case "Data Scientist":
                //textViewRoadmap.setText(getString(R.string.data_scientist_roadmap));
                break;
            // Add cases for other career paths as needed
            default:
                // Handle default case
                break;
        }
    }
}
