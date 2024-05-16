package com.android.learningapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {
    private String mTitle;
    private String mDescription;
    private float mRatings;
    private int mImageResourceId;
    private String mCourseContent;
    private String mExercise;
    private String mSolution;

    public Course(String title, String description, float ratings, int imageResourceId, String courseContent, String exercise, String solution) {
        mTitle = title;
        mDescription = description;
        mRatings = ratings;
        mImageResourceId = imageResourceId;
        mCourseContent = courseContent;
        mExercise = exercise;
        mSolution = solution;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public float getRatings() {
        return mRatings;
    }

    public void setRatings(float ratings) {
        mRatings = ratings;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        mImageResourceId = imageResourceId;
    }

    public String getCourseContent() {
        return mCourseContent;
    }

    public void setCourseContent(String courseContent) {
        mCourseContent = courseContent;
    }

    public String getExercise() {
        return mExercise;
    }

    public void setExercise(String exercise) {
        mExercise = exercise;
    }

    public String getSolution() {
        return mSolution;
    }

    public void setSolution(String solution) {
        mSolution = solution;
    }

    // Parcelable implementation
    protected Course(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mRatings = in.readFloat();
        mImageResourceId = in.readInt();
        mCourseContent = in.readString();
        mExercise = in.readString();
        mSolution = in.readString();
    }

    public static final Parcelable.Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeFloat(mRatings);
        dest.writeInt(mImageResourceId);
        dest.writeString(mCourseContent);
        dest.writeString(mExercise);
        dest.writeString(mSolution);
    }
}