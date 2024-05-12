package com.android.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Signup extends AppCompatActivity {
    private TextInputLayout tiEmail;
    private TextInputLayout tiPassword;
    private Button btnSignup;
    private SignInButton btnGoogleSignIn;
    private ProgressBar progressBar;

    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Signup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initFirebaseAuth();
        initUIComponents();
        setupEventListeners();
    }

    private void initFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void initUIComponents() {
        tiEmail = findViewById(R.id.tiEmail);
        tiPassword = findViewById(R.id.tiPassword);
        btnSignup = findViewById(R.id.btnSignup);
        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn);
        progressBar = findViewById(R.id.pbSignup);
    }

    private void setupEventListeners() {
        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupWithEmailAndPassword();
            }
        });
    }

    private void googleSignIn() {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    private void signupWithEmailAndPassword() {
        progressBar.setVisibility(View.VISIBLE);
        if (tiEmail.getEditText() != null && tiPassword.getEditText() != null) {
            String email = tiEmail.getEditText().getText().toString().trim();
            String password = tiPassword.getEditText().getText().toString().trim();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                addUserToDatabase(user.getUid(), email, password);
                            } else {
                                Toast.makeText(Signup.this, "Authentication failed: " + Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(Signup.this, "Email or password field is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void addUserToDatabase(String userId, String email, String password) {
        User newUser = new User(userId, email, password);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://learningapp-1302-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference usersRef = database.getReference("users"); // Initialize database reference here
        usersRef.child(userId).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Signup.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(Signup.this, Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Signup.this, "Failed to add user: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this, "Error coming: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    private void firebaseAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            addUserToDatabase(user.getUid(), user.getEmail(), user.getUid());
                        } else {
                            Toast.makeText(Signup.this, "Something went wrong with firebase authentication", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}