package com.indev.ruraldevelopment.Activity.Model;

public class MultipleImagePojo {

    private String village_profile_img;
    private String village_profile_id;
    private String status;
    private int user_id;
    private int id;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getVillage_profile_img() {
        return village_profile_img;
    }

    public void setVillage_profile_img(String village_profile_img) {
        this.village_profile_img = village_profile_img;
    }

    public String getVillage_profile_id() {
        return village_profile_id;
    }

    public void setVillage_profile_id(String village_profile_id) {
        this.village_profile_id = village_profile_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private static final String TABLE_NAME = "village_profile_image";
    private static final String COLUMN_IMAGE = "village_profile_img";
    private static final String COLUMN_UNIQUE_ID ="village_profile_id";
    private static final String COLUMN_USER_ID ="user_id";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_STATUS ="status";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_UNIQUE_ID + " TEXT, "
                    + COLUMN_IMAGE + " TEXT, "
                    + COLUMN_USER_ID + " TEXT, "
                    + COLUMN_ID + " TEXT, "
                    + COLUMN_STATUS + " TEXT "
                    +")";
}
