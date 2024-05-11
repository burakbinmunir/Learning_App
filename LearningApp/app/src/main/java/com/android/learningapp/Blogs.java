package com.android.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Blogs extends AppCompatActivity implements BlogFetchListener{

    BottomNavigationView  bottomNavigationView;

    FloatingActionButton fabAddNewBlog;

    RecyclerView rvBlogs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_blogs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init() {
        initializeBottomNavigation();
        initializeFabAddNewBlog();
        initializeRecyclerView();
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
    }

    private void initializeBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.itemhome) {
                Intent intent = new Intent(Blogs.this, Home.class);
                startActivity(intent);
                item.setChecked(true);
            } else if (item.getItemId() == R.id.itemprofile) {
                Intent intent = new Intent(Blogs.this, UserProfile.class);
                startActivity(intent);
                item.setChecked(true);
            } else if (item.getItemId() == R.id.itemblog) {
                Intent intent = new Intent(Blogs.this, Blogs.class);
                startActivity(intent);
                item.setChecked(true);
            }

            return false;
        });

    }
    private void initializeRecyclerView() {
        rvBlogs = findViewById(R.id.rvBlogs);

        rvBlogs.setHasFixedSize(true);
        rvBlogs.setLayoutManager(new GridLayoutManager(this, 2));
        FirebaseUtils firebaseUtils = new FirebaseUtils(this);
        firebaseUtils.getBlogs(this);
    }

    @Override
    public void onBlogsFetched(ArrayList<Blog> blogs) {
        System.out.printf("Size of blogs: %d\n", blogs.size());
        rvBlogs.setAdapter(new BlogAdapter(this, blogs)); // Set adapter here after blogs are fetched
    }

    private void initializeFabAddNewBlog() {
        fabAddNewBlog = findViewById(R.id.fabAddNewBlog);
        fabAddNewBlog.setOnClickListener(v -> {
            Intent intent = new Intent(Blogs.this, AddNewBlog.class);
            startActivity(intent);
        });
    }



    @Override
    public void onBackPressed() {
            super.onBackPressed();
            Intent intent = new Intent(Blogs.this, Home.class);
            startActivity(intent);
    }

    @Override
    public void onFetchFailed(String errorMessage) {
        Toast.makeText(this, "Failed to get blogs: " + errorMessage, Toast.LENGTH_SHORT).show();

    }
}