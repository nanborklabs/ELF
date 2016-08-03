package com.elf.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by nandhu on 3/8/16.
 */
public class Lessoninfo implements ParentListItem{
    public  String lesson_no;
    public  int weightage;
    public String Status;
    public List<LessonDeatils> mDetails;

    public Lessoninfo(String lesson_no, int weightage, String status,List<LessonDeatils> mlist) {
        this.lesson_no = lesson_no;
        this.weightage = weightage;
        Status = status;
        this.mDetails=mlist;
    }

    public String getLesson_no() {
        return lesson_no;
    }

    public void setLesson_no(String lesson_no) {
        this.lesson_no = lesson_no;
    }

    public int getWeightage() {
        return weightage;
    }

    public void setWeightage(int weightage) {
        this.weightage = weightage;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setmDetails(List<LessonDeatils> mDetails) {
        this.mDetails = mDetails;
    }

    @Override
    public List<LessonDeatils> getChildItemList() {
        return mDetails;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
