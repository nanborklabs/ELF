package com.elf.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by nandhu on 3/8/16.
 */
public class Lessoninfo {
    private String mLessonName;
    private String mPercentage;
    private String mQuestionsAksed;
    private String mCorrectQuestions;

    public Lessoninfo(String mLessonName, String mPercentage, String mQuestionsAksed, String mCorrectQuestions) {
        this.mLessonName = mLessonName;
        this.mPercentage = mPercentage;
        this.mQuestionsAksed = mQuestionsAksed;
        this.mCorrectQuestions = mCorrectQuestions;
    }

    public String getmLessonName() {
        return mLessonName;
    }

    public void setmLessonName(String mLessonName) {
        this.mLessonName = mLessonName;
    }

    public String getmPercentage() {
        return mPercentage;
    }

    public void setmPercentage(String mPercentage) {
        this.mPercentage = mPercentage;
    }

    public String getmQuestionsAksed() {
        return mQuestionsAksed;
    }

    public void setmQuestionsAksed(String mQuestionsAksed) {
        this.mQuestionsAksed = mQuestionsAksed;
    }

    public String getmCorrectQuestions() {
        return mCorrectQuestions;
    }

    public void setmCorrectQuestions(String mCorrectQuestions) {
        this.mCorrectQuestions = mCorrectQuestions;
    }
}