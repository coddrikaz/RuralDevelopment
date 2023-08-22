package com.indev.ruraldevelopment.Activity.Model;

public class Infra_ResourceModel {



    private  String local_id;
    private  String resource_id;
    private  String resource_name;
    private  String created_at;
    private  String status;
    private  String resource_images;


    public String getLocal_id() {
        return local_id;
    }

    public void setLocal_id(String local_id) {
        this.local_id = local_id;
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

    public String getResource_images() {
        return resource_images;
    }

    public void setResource_images(String resource_images) {
        this.resource_images = resource_images;
    }

    private static final String TABLE_NAME = "infra_resource";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_RESOURCE_ID = "resource_id";
    public static final String COLUMN_RESOURCE_NAME = "resource_name";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_RESOURCE_IMAGES = "resource_images";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_RESOURCE_ID + " TEXT ,"
                    + COLUMN_RESOURCE_NAME + " TEXT ,"
                    + COLUMN_CREATED_AT + " TEXT ,"
                    + COLUMN_RESOURCE_IMAGES + " TEXT ,"
                    + COLUMN_STATUS + " TEXT "
                    + ")";
}
