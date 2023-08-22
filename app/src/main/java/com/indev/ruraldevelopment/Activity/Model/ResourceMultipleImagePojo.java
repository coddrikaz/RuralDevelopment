package com.indev.ruraldevelopment.Activity.Model;

public class ResourceMultipleImagePojo {

    private String resource_mapping_image;
    private String resource_mapping_id;
    private String status;
    private int user_id;
    private int id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResource_mapping_image() {
        return resource_mapping_image;
    }

    public void setResource_mapping_image(String resource_mapping_image) {
        this.resource_mapping_image = resource_mapping_image;
    }

    public String getResource_mapping_id() {
        return resource_mapping_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setResource_mapping_id(String resource_mapping_id) {



        this.resource_mapping_id = resource_mapping_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private static final String TABLE_NAME = "resuorce_mapping_image";
    private static final String COLUMN_IMAGE = "resource_mapping_image";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_UNIQUE_ID ="resource_mapping_id";
    private static final String COLUMN_STATUS ="status";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_UNIQUE_ID + " TEXT, "
                    + COLUMN_USER_ID + " TEXT, "
                    + COLUMN_ID + " TEXT, "
                    + COLUMN_IMAGE + " TEXT, "
                    + COLUMN_STATUS + " TEXT "
                    +")";
}
