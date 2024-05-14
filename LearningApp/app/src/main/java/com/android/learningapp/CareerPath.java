package com.android.learningapp;

import android.os.Bundle;

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
    }

    private ArrayList<String> loadDataFromXml() {
        ArrayList<String> dummyData = new ArrayList<>();
        dummyData.add("Software Engineer");
        dummyData.add("Data Scientist");
        dummyData.add("Product Manager");
        return dummyData;
    }
}
