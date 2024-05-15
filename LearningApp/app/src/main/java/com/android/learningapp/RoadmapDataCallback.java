package com.android.learningapp;

import java.util.List;

public interface RoadmapDataCallback {
    
    void onRoadmapDataLoaded(List<Object> roadmapData);
    void onDataLoadFailed(String errorMessage);
}
