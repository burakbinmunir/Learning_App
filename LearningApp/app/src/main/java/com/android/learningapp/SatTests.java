package com.android.learningapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

public class SatTests extends AppCompatActivity {

    MaterialCardView arabicTestSatCard, MathsSatTestCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sat_tests);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }
    private void init() {
        arabicTestSatCard = findViewById(R.id.arabicTestSatCard);
        arabicTestSatCard.setOnClickListener(v -> {
            startActivity(new Intent(SatTests.this, ArabicTestSat.class));
        });

        MathsSatTestCard = findViewById(R.id.MathsSatTestCard);
        MathsSatTestCard.setOnClickListener(v -> {
            startActivity(new Intent(SatTests.this, MathsTestSat.class));
        });
    }
}