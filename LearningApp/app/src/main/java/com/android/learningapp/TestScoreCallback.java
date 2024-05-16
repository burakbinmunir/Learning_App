package com.android.learningapp;

public interface TestScoreCallback {
    void onTestScoreLoaded(int score);

    void onDataLoadFailed(String error);
}
