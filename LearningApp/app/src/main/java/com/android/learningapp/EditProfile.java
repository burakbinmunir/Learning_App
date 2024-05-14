package com.android.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;

public class EditProfile extends AppCompatActivity {

    TextInputEditText etEditProfileUsername, etEditProfileBio, etEditProfileEmail, etEditProfileMobile;
    Button btnSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init() {
        etEditProfileUsername = findViewById(R.id.etEditProfileUsername);
        etEditProfileBio = findViewById(R.id.etEditProfileBio);
        etEditProfileEmail = findViewById(R.id.etEditProfileEmail);
        etEditProfileMobile = findViewById(R.id.etEditProfileMobile);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        btnSaveChanges.setOnClickListener(v -> {
            // only update those fields that have been changed if any edit text is empty then don't update that field

            // get the current user
            FirebaseUtils firebaseUtils = FirebaseUtils.getInstance(this);
            FirebaseUser currentUser =  firebaseUtils.getCurrentUser();

            String newUsername = "";
            String newBio = "";
            String newEmail = "";
            String newMobile = "";

            if (!etEditProfileUsername.getText().toString().isEmpty()) {
                newUsername = etEditProfileUsername.getText().toString();
            }else {
                newUsername = currentUser.getDisplayName();
            }

            if (!etEditProfileBio.getText().toString().isEmpty()) {
                newBio = etEditProfileBio.getText().toString();
            }else {
                newBio = "";
            }

            if (!etEditProfileEmail.getText().toString().isEmpty()) {
                newEmail = etEditProfileEmail.getText().toString();
            }else {
                newEmail = currentUser.getEmail();
            }

            if (!etEditProfileMobile.getText().toString().isEmpty()) {
                newMobile = etEditProfileMobile.getText().toString();
            }else {
                newMobile = currentUser.getPhoneNumber();
            }



            // update the user profile

            firebaseUtils.updateUserProfile(newUsername, newBio, newEmail, newMobile);
            Intent intent = new Intent(EditProfile.this, UserProfile.class);
            startActivity(intent);

        });
    }
}