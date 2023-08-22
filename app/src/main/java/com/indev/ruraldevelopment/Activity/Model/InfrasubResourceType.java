package com.indev.ruraldevelopment.Activity.Model;

public class InfrasubResourceType {
    private String local_id;
    private String infrasub_resource_id;
    private String infrasub_resource_name;
    private String infraresource_id;
    private String status;


    public String getLocal_id() {
        return local_id;
    }

    public void setLocal_id(String local_id) {
        this.local_id = local_id;
    }

    public String getInfrasub_resource_id() {
        return infrasub_resource_id;
    }

    public void setInfrasub_resource_id(String infrasub_resource_id) {
        this.infrasub_resource_id = infrasub_resource_id;
    }

    public String getInfrasub_resource_name() {
        return infrasub_resource_name;
    }

    public void setInfrasub_resource_name(String infrasub_resource_name) {
        this.infrasub_resource_name = infrasub_resource_name;
    }

    public String getInfraresource_id() {
        return infraresource_id;
    }

    public void setInfraresource_id(String infraresource_id) {
        this.infraresource_id = infraresource_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private static final String TABLE_NAME = "infrasub_resource_type";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_INFARASUB_RESOURCE_ID = "infrasub_resource_id";
    public static final String COLUMN_INFRASUB_RESOURCE_NAME = "infrasub_resource_name";
    public static final String COLUMN_INFRARESOURCE_ID = "infraresource_id";
    public static final String COLUMN_STATUS = "status";



    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_INFARASUB_RESOURCE_ID + " TEXT ,"
                    + COLUMN_INFRASUB_RESOURCE_NAME + " TEXT ,"
                    + COLUMN_INFRARESOURCE_ID + " TEXT ,"
                    + COLUMN_STATUS + " TEXT "
                    + ")";
}
