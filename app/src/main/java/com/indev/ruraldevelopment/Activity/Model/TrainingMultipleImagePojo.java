package com.indev.ruraldevelopment.Activity.Model;

public class TrainingMultipleImagePojo {

    private String training_image_name;
    private int training_id;
    private  int training_image_id;
    private int user_id;
    private String status;


    public int getTraining_id() {
        return training_id;
    }

    public void setTraining_id(int training_id) {
        this.training_id = training_id;
    }

    public int getTraining_image_id() {
        return training_image_id;
    }

    public void setTraining_image_id(int training_image_id) {
        this.training_image_id = training_image_id;
    }

    public String getTraining_image_name() {
        return training_image_name;
    }

    public void setTraining_image_name(String training_image_name) {
        this.training_image_name = training_image_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private static final String TABLE_NAME = "training_image";
    private static final String COLUMN_TRAINING_ID = "training_id";
    private static final String COLUMN_TRAINING_IMAGE_ID = "training_image_id";
    private static final String COLUMN_USER_ID ="user_id";
    private static final String COLUMN_TRAINING_IMAGE = "training_image_name";
    private static final String COLUMN_STATUS ="status";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_TRAINING_ID + " INTEGER ,"
                    + COLUMN_TRAINING_IMAGE_ID + " TEXT, "
                    + COLUMN_USER_ID + " TEXT, "
                    + COLUMN_TRAINING_IMAGE + " TEXT, "
                    + COLUMN_STATUS + " TEXT "
                    +")";


}
