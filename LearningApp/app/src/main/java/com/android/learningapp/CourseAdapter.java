package com.android.learningapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.widget.ImageView;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> mCourses;
    private CourseSelectionActivity mActivity;

    public CourseAdapter(List<Course> courses, CourseSelectionActivity activity) {
        mCourses = courses;
        mActivity = activity;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = mCourses.get(position);
        holder.mTitleTextView.setText(course.getTitle());
        holder.mDescriptionTextView.setText(course.getDescription());
        holder.mRatingsTextView.setText(String.valueOf(course.getRatings()));
        holder.mCourseImageView.setImageResource(course.getImageResourceId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.launchCourseDetailActivity(course);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView;
        public TextView mDescriptionTextView;
        public TextView mRatingsTextView;
        public ImageView mCourseImageView;

        public CourseViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.title_text_view);
            mDescriptionTextView = itemView.findViewById(R.id.description_text_view);
            mRatingsTextView = itemView.findViewById(R.id.ratings_text_view);
            mCourseImageView = itemView.findViewById(R.id.course_image_view);
        }
    }
}
