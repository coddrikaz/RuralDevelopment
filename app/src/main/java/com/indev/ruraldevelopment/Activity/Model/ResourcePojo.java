package com.indev.ruraldevelopment.Activity.Model;

public class ResourcePojo {

    private  String resource_id;
    private  String resource_name;
    private  String created_at;
    private  String image;
    private  String status;
    private  String resource_images;

    public String getResource_images() {
        return resource_images;
    }

    public void setResource_images(String resource_images) {
        this.resource_images = resource_images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private static final String TABLE_NAME = "resource";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_STATE_ID = "resource_id";
    public static final String COLUMN_STATE_NAME = "resource_name";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_RESOURCE_IMAGES = "resource_images";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_STATE_ID + " TEXT ,"
                    + COLUMN_STATE_NAME + " TEXT ,"
                    + COLUMN_CREATED_AT + " TEXT ,"
                    + COLUMN_IMAGE + " TEXT ,"
                    + COLUMN_RESOURCE_IMAGES + " TEXT ,"
                    + COLUMN_STATUS + " TEXT "
                    + ")";

}
