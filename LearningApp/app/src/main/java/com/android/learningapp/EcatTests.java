package com.android.learningapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

public class EcatTests extends AppCompatActivity {

    MaterialCardView physicsEcatTestCard, chemistryEcatTestCard, mathsEcatTestCard, englishEcatTestCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ecat_tests);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init() {
        physicsEcatTestCard = findViewById(R.id.physicsEcatTestCard);
        chemistryEcatTestCard = findViewById(R.id.chemistryEcatTestCard);
        mathsEcatTestCard = findViewById(R.id.mathsEcatTestCard);
        englishEcatTestCard = findViewById(R.id.englishEcatTestCard);

        physicsEcatTestCard.setOnClickListener(v -> {
            startActivity(new Intent(EcatTests.this, PhysicsEcatTest.class));
        });

        chemistryEcatTestCard.setOnClickListener(v -> {
            startActivity(new Intent(EcatTests.this, ChemistryEcatTest.class));
        });

        mathsEcatTestCard.setOnClickListener(v -> {
            startActivity(new Intent(EcatTests.this, MathsEcatTest.class));
        });

        englishEcatTestCard.setOnClickListener(v -> {
            startActivity(new Intent(EcatTests.this, EnglishEcatTest.class));
        });
    }
}