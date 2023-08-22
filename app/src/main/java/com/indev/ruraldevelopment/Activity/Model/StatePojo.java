package com.indev.ruraldevelopment.Activity.Model;

public class StatePojo {
    private  String state_id;
    private  String state_name;
    private  String country_id;
    private  String date_of_creation;
    private  String status;
    private  String ref_id;

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
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

    private static final String TABLE_NAME = "state";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_STATE_ID = "state_id";
    public static final String COLUMN_STATE_NAME = "state_name";
    public static final String COLUMN_COUNTRY_ID = "country_id";
    public static final String COLUMN_DATE_OF_CREATION = "date_of_creation";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_REF_ID = "ref_id";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_STATE_ID + " TEXT ,"
                    + COLUMN_STATE_NAME + " TEXT ,"
                    + COLUMN_COUNTRY_ID + " TEXT ,"
                    + COLUMN_DATE_OF_CREATION + " TEXT ,"
                    + COLUMN_STATUS + " TEXT ,"
                    + COLUMN_REF_ID + " TEXT "
                    + ")";

}
