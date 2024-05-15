package com.android.learningapp;
// Roadmaps.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Roadmaps {
    private static Map<String, List<Object>> roadmapData = new HashMap<>();

    static {
        // Sample data for career roadmaps
        List<Object> softwareEngineerData = new ArrayList<>();
        softwareEngineerData.add(R.drawable.account_circle); // Image resource id
        softwareEngineerData.add(new String[]{"What is the career path for this profession?",
                "How can I advance in this career?"});
        softwareEngineerData.add(new String[]{"this is the career path for this profession?",
                "I can advance in this career?"});// FAQs
        roadmapData.put("Software Engineer", softwareEngineerData);


        List<Object> DsEngineerData = new ArrayList<>();
        DsEngineerData.add(R.drawable.account_circle); // Image resource id
        DsEngineerData.add(new String[]{"What is the career path for this Data Science?",
                "How can I advance in this career?"});
        DsEngineerData.add(new String[]{"this is the career path for this profession?",
                "I can advance in this career?"});// FAQs
        roadmapData.put("Data Scientist", DsEngineerData);
    }

    public static List<Object> getRoadmapData(String career) {
        return roadmapData.get(career);
    }
}
