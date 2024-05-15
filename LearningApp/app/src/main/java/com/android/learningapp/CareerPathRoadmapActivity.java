package com.android.learningapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class CareerPathRoadmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_path_roadmap);

        // Get career name from intent extra
        String career = getIntent().getStringExtra("career_roadmap");
        // Set the image for the career roadmap
        ImageView careerRoadmapImage = findViewById(R.id.image_career_roadmap);
        // Get roadmap data for the career
        List<Object> roadmapData = Roadmaps.getRoadmapData(career);
        if (roadmapData != null && !roadmapData.isEmpty()) {
            int imageResource = (int) roadmapData.get(0);
            careerRoadmapImage.setImageResource(imageResource);
        }

        populateFAQCards(career);
    }

    private void populateFAQCards(String career) {
        // Get roadmap data for the career
        List<Object> roadmapData = Roadmaps.getRoadmapData(career);

        if (roadmapData != null && roadmapData.size() > 1) {

            String[] faqTitles = (String[]) roadmapData.get(1);

            String[] faqAnswers = (String[]) roadmapData.get(2); // Get FAQ answers

            // Show Toast messages for FAQ titles
            for (String title : faqTitles) {
                Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
            }

            FAQListAdapter adapter = new FAQListAdapter(this, faqTitles, faqAnswers);

            // Get the ListView within the CardView
            ListView listViewFAQ = findViewById(R.id.listview_faq);

            // Set the adapter to the ListView
            listViewFAQ.setAdapter(adapter);
        }
    }


}
