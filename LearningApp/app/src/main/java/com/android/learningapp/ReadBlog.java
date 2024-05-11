package com.android.learningapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReadBlog extends AppCompatActivity {

    TextView textViewTitle, textViewContent;
    ImageView imageViewBlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_blog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init() {
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewContent = findViewById(R.id.textViewContent);
        imageViewBlog = findViewById(R.id.imageViewBlog);

        String title = getIntent().getStringExtra("blogTitle");
        String content = getIntent().getStringExtra("blogContent");
        String imageUrl = getIntent().getStringExtra("blogImageUrl");

        textViewTitle.setText(title);
        textViewContent.setText(content);
        ImageUtils.loadImage(this, imageUrl, imageViewBlog);

    }
}