package com.elf.model;

import android.util.Log;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by nandhu on 2/8/16.
 */
public class Testinfo implements ParentListItem {
    public String Testno;
    public String Status;
    public String percent;
    public String Topic;

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    private List<Testdetail> deatils;

    public Testinfo(String testno, String status, String percent, String Top) {
        Testno = testno;
        Status = status;
        this.percent = percent;
        this.Topic=Top;
    }

    public String getTestno() {
        return Testno;
    }

    public void setTestno(String testno) {
        Testno = testno;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    @Override
    public List<Testdetail> getChildItemList() {
        if (deatils==null){
            Log.d("null", "getChildItemList:=0 ");
        }
        return deatils;
    }

    public void setDeatils(List<Testdetail> deatils) {
        this.deatils = deatils;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
