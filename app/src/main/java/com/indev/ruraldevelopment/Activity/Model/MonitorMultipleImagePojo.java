package com.indev.ruraldevelopment.Activity.Model;

import java.util.ArrayList;

public class MonitorMultipleImagePojo {
    private String monitor_resource_image;
    private int monitor_id;
    private int user_id;
    private int monitor_resource_id;
    private String status;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMonitor_resource_image() {
        return monitor_resource_image;
    }

    public void setMonitor_resource_image(String monitor_resource_image) {
        this.monitor_resource_image = monitor_resource_image;
    }

    public int getMonitor_id() {
        return monitor_id;
    }

    public void setMonitor_id(int monitor_id) {
        this.monitor_id = monitor_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMonitor_resource_id() {
        return monitor_resource_id;
    }

    public void setMonitor_resource_id(int monitor_resource_id) {
        this.monitor_resource_id = monitor_resource_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    private static final String TABLE_NAME = "moniter_resource_image";
    private static final String COLUMN_MONITOR_ID = "monitor_id";
    private static final String COLUMN_MONITOR_RESOURCE_ID ="monitor_resource_id";
    private static final String COLUMN_USER_ID ="user_id";
    private static final String COLUMN_MONITOR_IMAGE = "monitor_resource_image";
    private static final String COLUMN_STATUS ="status";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_MONITOR_ID + " INTEGER ,"
                    + COLUMN_MONITOR_RESOURCE_ID + " TEXT, "
                    + COLUMN_USER_ID + " TEXT, "
                    + COLUMN_MONITOR_IMAGE + " TEXT, "
                    + COLUMN_STATUS + " TEXT "
                    +")";
}
