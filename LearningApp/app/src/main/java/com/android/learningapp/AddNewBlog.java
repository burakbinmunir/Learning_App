package com.android.learningapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddNewBlog extends AppCompatActivity {

    Button  btnAddBlog;
    ImageButton btnSelectImage;
    private static final int PICK_IMAGE_REQUEST = 1;

    ImageView selectedImageView;
    Cloudinary cloudinary;

    String imageUploadResult;
    Uri imageUri;

    TextInputEditText etTitle, etContent;

    AutoCompleteTextView actvBlogCategory;

    String selectedCategory = "General";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_blog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
    }


    private void init() {
        etContent = findViewById(R.id.etContent);
        etTitle = findViewById(R.id.etTitle);
        selectedImageView = findViewById(R.id.selectedImageView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        setBtnSelectImage();
        setBtnAddBlog();
        setActvBlogCategory();
    }

    private void setActvBlogCategory(){

        actvBlogCategory = findViewById(R.id.actvBlogCategory);
        String[] blogCategories = getResources().getStringArray(R.array.dropdown_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, blogCategories);
        actvBlogCategory.setAdapter(adapter);

        actvBlogCategory.setOnItemClickListener((parent, view, position, id) -> {
            selectedCategory = (String) parent.getItemAtPosition(position);
        });
    }

    private void setBtnAddBlog() {
        btnAddBlog = findViewById(R.id.btnAddBlog);
        btnAddBlog.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            new ImageUploadTask(AddNewBlog.this, new CloudinaryUploadListener() {
                @Override
                public void onCloudinaryUploadComplete(Map uploadResult) {
                    progressBar.setVisibility(View.GONE);
                    if (uploadResult != null) {
                        imageUploadResult = Objects.requireNonNull(uploadResult.get("secure_url")).toString();
                        FirebaseUtils firebaseUtils = FirebaseUtils.getInstance(AddNewBlog.this);
                        firebaseUtils.uploadBlog(new Blog(
                                Objects.requireNonNull(etTitle.getText()).toString(),
                                Objects.requireNonNull(etContent.getText()).toString(),
                                imageUploadResult,
                                selectedCategory
                        ));
                        Intent intent = new Intent(AddNewBlog.this, Blogs.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddNewBlog.this, "Error uploading image", Toast.LENGTH_SHORT).show();
                    }
                }
            }).execute(imageUri);
        });
    }

    private void setBtnSelectImage (){

        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                selectedImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ImageUploadTask extends AsyncTask<Uri, Void, Map> {
        private WeakReference<Context> contextRef;
        private CloudinaryUploadListener listener;

        public ImageUploadTask(Context context, CloudinaryUploadListener listener) {
            contextRef = new WeakReference<>(context);
            this.listener = listener;

        }

        @Override
        protected Map doInBackground(Uri... uris) {
            if (uris.length == 0 || uris[0] == null) {
                return null;
            }

            System.out.printf("Uploading image %s%n", uris[0].toString());
            try {
                String filePath = getRealPathFromUri(uris[0]); // Convert content URI to file path
                if (filePath == null) {
                    return null; // Failed to convert URI to file path
                }

                Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dlgfqfcqj",
                        "api_key", "678388316313752",
                        "api_secret", "23lZ8GNk8_T-KZ5zPujz5A_Zyck",
                        "secure", true));

                return cloudinary.uploader().upload(filePath, ObjectUtils.emptyMap());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        // Helper method to get the file path from a content URI
        private String getRealPathFromUri(Uri uri) {
            String filePath = null;
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                filePath = cursor.getString(columnIndex);
                cursor.close();
            }
            return filePath;
        }


        @Override
        protected void onPostExecute(Map uploadResult) {
            Context context = contextRef.get();
            if (context != null && listener != null) {
                if (uploadResult != null) {
                    listener.onCloudinaryUploadComplete(uploadResult);
                } else {
                    listener.onCloudinaryUploadComplete(null); // Notify listener about upload failure
                }
            }
        }
    }

}
