package com.android.learningapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Signin extends AppCompatActivity {

    // This is the main activity for the Signin page
    ProgressBar pbSignin;

    TextInputLayout tiSigninEmail, tiSigninPassword;

    Button btnSignin, btnCreateAccount;

    SignInButton btnSigninPageGoogleSignIn;

    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        initFirebaseAuth();
        initGoogleSignInClient();
        setupEventListeners();

    }

    private void init() {
        pbSignin = findViewById(R.id.pbSignin);
        tiSigninEmail = findViewById(R.id.tiSigninEmail);
        tiSigninPassword = findViewById(R.id.tiSigninPassword);
        btnSignin = findViewById(R.id.btnSignin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnSigninPageGoogleSignIn = findViewById(R.id.btnSigninPageGoogleSignIn);

        // remove error when user starts typing
        Objects.requireNonNull(tiSigninEmail.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiSigninEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // remove error when user starts typing
        Objects.requireNonNull(tiSigninPassword.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiSigninPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Find the TextView that is inside of the SignInButton and set its text
        for (int i = 0; i < btnSigninPageGoogleSignIn.getChildCount(); i++) {
            View v = btnSigninPageGoogleSignIn.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setText("Signin with Google");
                break;
            }
        }
    }

    private void initGoogleSignInClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void initFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                startActivity(new Intent(Signin.this, Home.class));
                finish();
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }
        };
    }

    private void setupEventListeners() {
        btnSignin.setOnClickListener(v -> {
            String email = Objects.requireNonNull(tiSigninEmail.getEditText()).getText().toString();
            String password = Objects.requireNonNull(tiSigninPassword.getEditText()).getText().toString();
            if (email.isEmpty() || password.isEmpty()) {

                // set error message if email or password is empty
                if (email.isEmpty()) {
                    tiSigninEmail.setError("Email is required");
                } else {
                    tiSigninEmail.setError(null);
                }

                if (password.isEmpty()) {
                    tiSigninPassword.setError("Password is required");

                } else {
                    tiSigninPassword.setError(null);
                }

            } else {
                pbSignin.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signin.this, task -> {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                FirebaseUtils firebaseUtils =  FirebaseUtils.getInstance(Signin.this);
                                firebaseUtils.setCurrentUser(user);
                                startActivity(new Intent(Signin.this, Home.class));
                                finish();
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(Signin.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            pbSignin.setVisibility(View.GONE);
                        });
            }
        });
        btnCreateAccount.setOnClickListener(v -> {
            startActivity(new Intent(Signin.this, Signup.class));
            finish();
        });
        btnSigninPageGoogleSignIn.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed");
            }
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        FirebaseUtils firebaseUtils = FirebaseUtils.getInstance(Signin.this);
                        firebaseUtils.setCurrentUser(user);
                        addUserToDatabase(user.getUid(), user.getEmail(), "", user.getDisplayName(), "", user.getPhotoUrl().toString());
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(Signin.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addUserToDatabase(String userId, String email, String password, String displayName, String phoneNumber, String photoUrl) {
        User newUser = new User(userId, email, password, displayName, phoneNumber, photoUrl);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://learningapp-1302-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference usersRef = database.getReference("users"); // Initialize database reference here
        usersRef.child(userId).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Signin.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                    pbSignin.setVisibility(View.GONE);
                    Intent intent = new Intent(Signin.this, Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Signin.this, "Failed to add user: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}