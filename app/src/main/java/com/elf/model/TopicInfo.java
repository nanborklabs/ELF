package com.elf.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by nandhu on 3/8/16.
 */
public class TopicInfo implements ParentListItem {
    public String topic_name;
    public String percent_achieved;
    public List<TopicDetail> mDetails;

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getPercent_achieved() {
        return percent_achieved;
    }

    public void setPercent_achieved(String percent_achieved) {
        this.percent_achieved = percent_achieved;
    }

    public List<TopicDetail> getmDetails() {
        return mDetails;
    }

    public void setmDetails(List<TopicDetail> mDetails) {
        this.mDetails = mDetails;
    }

    public TopicInfo(String topic_name, String percent_achieved, List<TopicDetail> mDetails) {
        this.topic_name = topic_name;
        this.percent_achieved = percent_achieved;
        this.mDetails = mDetails;
    }

    @Override
    public List<TopicDetail> getChildItemList() {
        return mDetails;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
