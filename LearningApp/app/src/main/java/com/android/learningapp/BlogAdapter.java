package com.android.learningapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.learningapp.Blog;
import com.android.learningapp.R;
import com.bumptech.glide.Glide;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder> {
    private Context context;
    private ArrayList<Blog> blogList;

    public BlogAdapter(Context context, ArrayList<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_blog_layout, parent, false);
        return new BlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        Blog blog = blogList.get(position);
        Glide.with(context)
                .load(blog.getImageUrl())
                .into(holder.blogImage);
        System.out.println("Image URL: " + blog.getImageUrl());
        holder.blogTitle.setText(blog.getTitle());
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class BlogViewHolder extends RecyclerView.ViewHolder {
        private TextView blogTitle, blogContent;
        private ImageView blogImage;

        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            blogTitle = itemView.findViewById(R.id.tvBlogTitle);
            blogImage = itemView.findViewById(R.id.ivBlogImage);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Blog blog = blogList.get(position);
                Intent intent = new Intent(context, ReadBlog.class);
                intent.putExtra("blogTitle", blog.getTitle());
                intent.putExtra("blogContent", blog.getContent());
                intent.putExtra("blogImageUrl", blog.getImageUrl());
                context.startActivity(intent);
            });

            // on long click open dialog box to edit or delete blog
            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                Blog blog = blogList.get(position);
                // open alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Update or Delete Blog");
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_update_delete_blog, null);
                builder.setView(view);

                // set up dialog box
                EditText dialogEtBlogTitle = view.findViewById(R.id.dialogEtBlogTitle);
                EditText dialogEtBlogContent = view.findViewById(R.id.dialogEtBlogContent);
                dialogEtBlogContent.setText(blog.getContent());
                System.out.printf("Blog content: %s\n", blog.getContent());
                dialogEtBlogTitle.setText(blog.getTitle());

                // update blog
                view.findViewById(R.id.dialogBtnUpdate).setOnClickListener(v1 -> {
                    String updatedTitle = dialogEtBlogTitle.getText().toString();
                    String updatedContent = dialogEtBlogContent.getText().toString();
                    blog.setTitle(updatedTitle);
                    blog.setContent(updatedContent);
//                    notifyDataSetChanged();
//                    // update blog in database
//                    FirebaseUtils firebaseUtils = new FirebaseUtils(context);
//                    firebaseUtils.updateBlog(blog);
                });

                // delete blog
                view.findViewById(R.id.dialogBtnDelete).setOnClickListener(v1 -> {
                    // delete blog from database
//                    FirebaseUtils firebaseUtils = new FirebaseUtils(context);
//                    firebaseUtils.deleteBlog(blog);
                });

                builder.create().show();

                return true;
            });
        }

        public void bind(Blog blog) {
            blogTitle.setText(blog.getTitle());
            String imgUrl = blog.getImageUrl();
            ImageUtils.loadImage(context, imgUrl, blogImage);
        }
    }
}
