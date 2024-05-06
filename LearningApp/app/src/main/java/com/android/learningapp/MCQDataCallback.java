package com.android.learningapp;

import java.util.ArrayList;

public interface MCQDataCallback {
    void onMCQDataLoaded(ArrayList<MCQ> mcqs);
    void onDataLoadFailed(String errorMessage);
}
