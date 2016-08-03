package com.elf.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

/**
 * Created by nandhu on 3/8/16.
 */
public class TopicDetail{
   public String Lesson;
    public int question_asked;

    public TopicDetail(String lesson, int question_asked) {
        Lesson = lesson;
        this.question_asked = question_asked;
    }

    public String getLesson() {
        return Lesson;
    }

    public void setLesson(String lesson) {
        Lesson = lesson;
    }

    public int getQuestion_asked() {
        return question_asked;
    }

    public void setQuestion_asked(int question_asked) {
        this.question_asked = question_asked;
    }
}
