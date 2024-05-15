package com.android.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CourseSelectionActivity extends AppCompatActivity {

    private RecyclerView mCourseRecyclerView;
    private ArrayList<Course> mCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);

        mCourses = new ArrayList<>();
        mCourses.add(new Course("Meta Android Developer", "Learn Android development using Kotlin and Jetpack", 4.6f, R.drawable.android_course, "This is the course content...", "1 + 1 = ?", "2"));
        mCourses.add(new Course("Meta React Native", "Learn React Native development for mobile apps", 4.7f, R.drawable.reactnative_course, "This is the course content for React Native...", "What is the purpose of JSX?", "To allow JavaScript and HTML to be used together"));
        mCourses.add(new Course("Meta iOS Developer", "Learn iOS development using Swift and SwiftUI", 4.6f, R.drawable.ios_course, "This is the course content for iOS development...", "What is the name of the iOS development framework?", "SwiftUI"));
        mCourses.add(new Course("Meta React Native", "Learn React Native development for mobile apps", 4.7f, R.drawable.reactnative_course, "This is the course content for React Native...", "What is the purpose of React Hooks?", "To manage state and side effects in functional components"));

        mCourseRecyclerView = findViewById(R.id.course_recycler_view);
        mCourseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCourseRecyclerView.setAdapter(new CourseAdapter(mCourses));
    }
}