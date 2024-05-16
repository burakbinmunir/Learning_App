package com.android.learningapp;


import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
public class CourseDetailActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private TextView mCourseTitleTextView;
    private TextView mCourseContentTextView;
    private TextView mExerciseTextView;
    private EditText mAnswerEditText;
    private Button mSubmitButton;
    private Course mCourse;

    private int imageResourceId;
    public ImageView mCourseImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        mCourseTitleTextView = findViewById(R.id.course_title_text_view);
        mCourseContentTextView = findViewById(R.id.course_content_text_view);
        mExerciseTextView = findViewById(R.id.exercise_text_view);
        mAnswerEditText = findViewById(R.id.answer_edit_text);
        mSubmitButton = findViewById(R.id.submit_button);
        mCourseImageView = findViewById(R.id.course_detail_image_view);


        mCourse = getIntent().getParcelableExtra("course");
        mCourseTitleTextView.setText(mCourse.getTitle());
        mCourseContentTextView.setText(mCourse.getCourseContent());
        mExerciseTextView.setText(mCourse.getExercise());
        imageResourceId = mCourse.getImageResourceId();
        mCourseImageView.setImageResource(mCourse.getImageResourceId());

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = mAnswerEditText.getText().toString();
                if (answer.equals(mCourse.getSolution())) {

                    Toast.makeText(v.getContext(), "Congratulations! You have completed the course.", Toast.LENGTH_SHORT).show();
                    if (checkPermission()) {
                        FirebaseUtils firebaseUtils = FirebaseUtils.getInstance(CourseDetailActivity.this);
                       createAndSaveCertificate(mCourse.getTitle(), "User Name");
                    } else {
                        requestPermission();
                    }
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("completed_course", mCourse);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(v.getContext(), "Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return true; // Permissions not needed for Tiramisu and above for writing files
        } else {
            int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                createAndSaveCertificate(mCourse.getTitle(), "User Name"); // Replace "User Name" with actual user name
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createAndSaveCertificate(String courseName, String userName) {
        int width = 1000;
        int height = 700;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);

        // Background color
        canvas.drawColor(Color.WHITE);

        // Draw border
        Paint borderPaint = new Paint();
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(10);
        canvas.drawRect(0, 0, width, height, borderPaint);

        // Title
        paint.setTextSize(60);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Certificate of Completion", width / 2, 150, paint);

        // Subtitle
        paint.setTextSize(40);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        canvas.drawText("This certifies that", width / 2, 250, paint);

        // User's Name
        paint.setTextSize(50);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText(userName, width / 2, 350, paint);

        // Course Completion Text
        paint.setTextSize(40);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        canvas.drawText("has completed the course", width / 2, 450, paint);

        // Course Name
        paint.setTextSize(50);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText(courseName, width / 2, 550, paint);

        // Signature Line
        paint.setTextSize(30);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        canvas.drawText("____________________", width / 2, 650, paint);
        canvas.drawText("Instructor", width / 2, 690, paint);

        saveImage(bitmap);
    }

    private void saveImage(Bitmap bitmap) {
        String fileName = "Certificate_" + System.currentTimeMillis() + ".png";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Use MediaStore for Android Q and above
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

            Uri uri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
            if (uri != null) {
                try (OutputStream out = getContentResolver().openOutputStream(uri)) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    Toast.makeText(this, "Certificate saved in Downloads", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to save certificate", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // Use legacy method for Android P and below
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, fileName);
            try (FileOutputStream out = new FileOutputStream(file)) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                Toast.makeText(this, "Certificate saved in Downloads", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to save certificate", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
