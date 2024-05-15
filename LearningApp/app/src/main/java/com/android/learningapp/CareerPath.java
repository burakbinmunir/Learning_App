package com.android.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CareerPath extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_path);

        listView = findViewById(R.id.listView);

        ArrayList<String> careerRoadmaps = loadDataFromXml();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_career_roadmap, careerRoadmaps);
        listView.setAdapter(adapter);

        // Set click listener for ListView items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected career roadmap
                String selectedCareerRoadmap = (String) parent.getItemAtPosition(position);

                // Start CareerPathRoadmap activity
                Intent intent = new Intent(CareerPath.this, CareerPathRoadmapActivity.class);
                // Pass selected career roadmap as extra data
                intent.putExtra("career_roadmap", selectedCareerRoadmap);
                startActivity(intent);
            }
        });
    }

    private ArrayList<String> loadDataFromXml() {
        ArrayList<String> dummyData = new ArrayList<>();
        dummyData.add("Software Engineer");
        dummyData.add("Data Scientist");
        dummyData.add("Product Manager");
        return dummyData;
    }
}
