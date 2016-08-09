package com.elf.model;

/**
 * Created by user on 09-08-2016.
 */
public class SubjectHomeModel {

    public String Subject_name;
    public String percentage;

    public SubjectHomeModel(String subject_name, String percentage) {
        Subject_name = subject_name;
        this.percentage = percentage;
    }


    public String getSubject_name() {
        return Subject_name;
    }

    public void setSubject_name(String subject_name) {
        Subject_name = subject_name;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
