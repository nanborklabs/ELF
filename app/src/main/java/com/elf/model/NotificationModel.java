package com.elf.model;

/**
 * Created by nandhu on 3/8/16.
 */
public class NotificationModel  {
//    status can be anything like started,completed,finished,report
    public int status;
    public String Notification_data;

    public NotificationModel(int status, String notification_data) {
        this.status = status;
        Notification_data = notification_data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNotification_data() {
        return Notification_data;
    }

    public void setNotification_data(String notification_data) {
        Notification_data = notification_data;
    }
}


