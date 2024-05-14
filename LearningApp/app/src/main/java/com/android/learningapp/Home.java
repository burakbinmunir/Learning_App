package com.android.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class Home extends AppCompatActivity {

    private Toolbar topAppBar;
    private MaterialButton btnPracticeMockTests;
    BottomNavigationView  bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

    }

    private void initializeBottomAppBar () {
        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setBottom(2);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.itemblog){
                    Intent intent = new Intent(Home.this, Blogs.class);
                    startActivity(intent);
                    return true;
                } else if (menuItem.getItemId() == R.id.itemprofile) {
                    Intent intent = new Intent(Home.this, UserProfile.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    private void initializeTopAppBar (){
        topAppBar = findViewById(R.id.topAppBar);

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation icon press
                // You can navigate to another activity or perform any other action here
                // For example, you can open a drawer or close the current activity
            }
        });

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.action_logout) {

                    FirebaseAuth.getInstance().signOut();

                    Toast.makeText(Home.this, "Signing out...", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Home.this, Signin.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    private void initializeTakeApptitudeTestButton(){
        btnPracticeMockTests = findViewById(R.id.btnPracticeMockTests);

        btnPracticeMockTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Taking Aptitude Test", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Home.this, MockTests.class);
                startActivity(intent);
            }
        });
    }

    private void init () {
     initializeTopAppBar();
     initializeBottomAppBar();
     initializeTakeApptitudeTestButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_menu, menu);
        return true;
    }
}
