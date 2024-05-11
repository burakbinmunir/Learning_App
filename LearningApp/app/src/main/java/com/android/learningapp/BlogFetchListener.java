package com.android.learningapp;

import java.util.ArrayList;

public interface BlogFetchListener {
    void onBlogsFetched(ArrayList<Blog> blogs);
    void onFetchFailed(String errorMessage);
}
