package com.android.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class CourseSelectionActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_COURSE_DETAIL = 1;
    private RecyclerView mCourseRecyclerView;
    private RecyclerView mCompletedCoursesRecyclerView;
    private CourseAdapter mCourseAdapter;
    private CourseAdapter mCompletedCoursesAdapter;
    private List<Course> mCourses;
    private List<Course> mFilteredCourses;
    private List<Course> mCompletedCourses;
    private SearchView mSearchView;
    private TextView mCompletedCoursesHeading;
    private TextView mAllCoursesHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);

        mCourses = new ArrayList<>();
        mCompletedCourses = new ArrayList<>();
        mCourses.add(new Course("Meta Android Developer",
                "Learn Android development using Kotlin and Jetpack",
                4.6f,
                R.drawable.android_course,
                "\n\nIn this course, you will embark on a comprehensive journey into Android app development using Kotlin and Jetpack. Android is the most popular mobile operating system globally, and mastering its development can open doors to exciting career opportunities. Throughout this course, you will delve into the intricacies of Android development, from understanding the fundamental concepts to building complex applications.\n\nKotlin, a modern and expressive programming language, has become the preferred choice for Android development due to its conciseness and safety features. You will learn how to harness the power of Kotlin to write clean and efficient code, making your apps more robust and maintainable.\n\nJetpack, a set of libraries, tools, and guidance provided by Google, simplifies the development process and enhances the quality of Android apps. You will explore various Jetpack components such as ViewModel, LiveData, Room database, and Navigation, equipping yourself with the skills needed to create dynamic and user-friendly applications.\n\nBy the end of this course, you will have a solid understanding of Android development principles and the confidence to build your own innovative apps to conquer the digital world.",
                "What layout do you use for a single row or single column?",
                "LinearLayout"));

        mCourses.add(new Course("Meta React Native",
                "Learn React Native development for mobile apps",
                4.7f,
                R.drawable.reactnative_course,
                "\n\nEmbark on an exciting journey into the world of mobile app development with React Native. React Native, developed by Facebook, enables you to build cross-platform mobile applications using JavaScript and React. Whether you are a beginner or an experienced developer, this course will equip you with the skills to create high-quality mobile apps for both iOS and Android platforms.\n\nReact Native leverages the power of JavaScript and React to deliver a seamless development experience. You will learn how to leverage React's component-based architecture to build reusable UI elements, allowing you to develop applications faster and more efficiently.\n\nOne of the key features of React Native is JSX (JavaScript XML), a syntax extension that allows you to write HTML-like code within JavaScript. You will discover how JSX facilitates the creation of dynamic and interactive user interfaces, enabling you to bring your app designs to life.\n\nThroughout this course, you will explore advanced topics such as navigation, state management, and integrating native modules, empowering you to build feature-rich and performant mobile apps. By the end of this course, you will be equipped with the knowledge and skills to embark on your journey as a React Native developer.",
                "What is the purpose of JSX?",
                "To allow JavaScript and HTML to be used together"));

        mCourses.add(new Course("Meta iOS Developer",
                "Learn iOS development using Swift and SwiftUI",
                4.6f,
                R.drawable.ios_course,
                "\n\nDive into the world of iOS app development with Swift and SwiftUI. iOS, Apple's mobile operating system, powers millions of devices worldwide, making it a lucrative platform for app developers. In this course, you will learn how to leverage the power of Swift and SwiftUI to create stunning and intuitive iOS apps that delight users.\n\nSwift, Apple's programming language for iOS, macOS, watchOS, and tvOS, combines the performance of compiled languages with the expressiveness of modern scripting languages. You will master the Swift language fundamentals, including variables, control flow, functions, and object-oriented programming concepts, laying the foundation for building iOS apps.\n\nSwiftUI, Apple's declarative framework for building user interfaces, revolutionizes the way iOS apps are developed. You will explore SwiftUI's intuitive syntax and powerful features, such as state management, layout design, and animation, enabling you to create visually stunning and responsive user interfaces.\n\nThroughout this course, you will embark on hands-on projects, from creating simple interfaces to building complex applications, honing your iOS development skills along the way. By the end of this course, you will have the expertise to turn your app ideas into reality and showcase your creations on the App Store.",
                "What is the name of the iOS development framework?",
                "SwiftUI"));
        mCourses.add(new Course("Flutter Mobile App Developer",
                "Master cross-platform mobile app development with Flutter",
                4.8f,
                R.drawable.flutter_course,
                "\n\nUnlock the potential of cross-platform mobile app development with Flutter. Flutter, Google's UI toolkit, empowers developers to create natively compiled applications for mobile, web, and desktop from a single codebase. In this course, you will learn how to harness the capabilities of Flutter to build beautiful and performant apps for both iOS and Android.\n\nFlutter uses Dart, an object-oriented programming language, which is designed to be easy to learn and productive to use. You will gain a deep understanding of Dart's core concepts, including variables, control structures, functions, and classes, laying a solid foundation for your Flutter development journey.\n\nWith Flutter's rich set of pre-designed widgets, you will create visually appealing and highly responsive user interfaces. You will delve into Flutter's widget tree architecture, exploring essential components like Container, Column, Row, and ListView. Additionally, you will learn about state management techniques, navigation, and integrating APIs to build dynamic and interactive applications.\n\nThroughout this course, you will work on hands-on projects, starting from simple user interfaces to complex, fully functional applications. By the end of this course, you will possess the skills to develop and deploy cross-platform apps that provide a native user experience on both iOS and Android, helping you stand out in the competitive app development market.",
                "What programming language does Flutter use?",
                "Dart"));



        mFilteredCourses = new ArrayList<>(mCourses);

        mCourseRecyclerView = findViewById(R.id.course_recycler_view);
        mCourseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCourseAdapter = new CourseAdapter(mFilteredCourses, this);
        mCourseRecyclerView.setAdapter(mCourseAdapter);

        mCompletedCoursesRecyclerView = findViewById(R.id.completed_courses_recycler_view);
        mCompletedCoursesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCompletedCoursesAdapter = new CourseAdapter(mCompletedCourses, this);
        mCompletedCoursesRecyclerView.setAdapter(mCompletedCoursesAdapter);

        mCompletedCoursesHeading = findViewById(R.id.completed_courses_heading);
        mAllCoursesHeading = findViewById(R.id.all_courses_heading);

        mSearchView = findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterCourses(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCourses(newText);
                return true;
            }
        });

        updateCompletedCoursesVisibility();
    }

    private void filterCourses(String query) {
        mFilteredCourses.clear();
        if (query.isEmpty()) {
            mFilteredCourses.addAll(mCourses);
        } else {
            for (Course course : mCourses) {
                if (course.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    mFilteredCourses.add(course);
                }
            }
        }
        mCourseAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_COURSE_DETAIL && resultCode == RESULT_OK && data != null) {
            Course completedCourse = data.getParcelableExtra("completed_course");
            if (completedCourse != null) {
                mCourses.remove(completedCourse);
                mFilteredCourses.remove(completedCourse);
                mCompletedCourses.add(completedCourse);
                mCourseAdapter.notifyDataSetChanged();
                mCompletedCoursesAdapter.notifyDataSetChanged();
                updateCompletedCoursesVisibility();
            }
        }
    }

    // Launch CourseDetailActivity and handle the result
    public void launchCourseDetailActivity(Course course) {
        Intent intent = new Intent(this, CourseDetailActivity.class);
        intent.putExtra("course", course);
        startActivityForResult(intent, REQUEST_CODE_COURSE_DETAIL);
    }

    // Update visibility of completed courses heading and RecyclerView based on completed courses list
    private void updateCompletedCoursesVisibility() {
        if (mCompletedCourses.isEmpty()) {
            mCompletedCoursesHeading.setVisibility(View.GONE);
            mCompletedCoursesRecyclerView.setVisibility(View.GONE);
        } else {
            mCompletedCoursesHeading.setVisibility(View.VISIBLE);
            mCompletedCoursesRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}

