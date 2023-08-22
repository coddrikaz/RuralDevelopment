package com.indev.ruraldevelopment.Activity.Model;

public class DistrictPojo {
    private  String district_id;
    private  String state_id;
    private  String district_name;
    private  String date_of_creation;
    private  String status;
    private  String ref_id;

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(String date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRef_id() {
        return ref_id;
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    private static final String TABLE_NAME = "district";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_DISTRICT_ID = "district_id";
    public static final String COLUMN_STATE_ID = "state_id";
    public static final String COLUMN_DISTRICT_NAME = "district_name";
    public static final String COLUMN_DATE_OF_CREATION = "date_of_creation";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_REF_ID = "ref_id";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_STATE_ID + " TEXT ,"
                    + COLUMN_DISTRICT_ID + " TEXT ,"
                    + COLUMN_DISTRICT_NAME + " TEXT ,"
                    + COLUMN_DATE_OF_CREATION + " TEXT ,"
                    + COLUMN_STATUS + " TEXT ,"
                    + COLUMN_REF_ID + " TEXT "
                    + ")";

}
