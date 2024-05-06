package com.android.learningapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MCQ {
    private String question;
    private ArrayList<String> options;
    private int correctAnswerIndex;

    private String imgURl;

    public MCQ(){

    }

    public String getImgURl() {
        return imgURl;
    }

    public void setImgURl(String imgURl) {
        this.imgURl = imgURl;
    }

    public MCQ(String question, ArrayList<String> options, int correctAnswerIndex, String imgURl) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.imgURl = imgURl;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
