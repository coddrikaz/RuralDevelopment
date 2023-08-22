package com.indev.ruraldevelopment.Activity.Model;

public class BlockPojo {

    private  String block_id;
    private  String block_name;
    private  String state_id;
    private  String district_id;
    private  String status;
    private  String ref_id;


    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

    public String getBlock_name() {
        return block_name;
    }

    public void setBlock_name(String block_name) {
        this.block_name = block_name;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
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

    private static final String TABLE_NAME = "block";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_BLOCK_ID = "block_id";
    public static final String COLUMN_BLOCK_NAME = "block_name";
    public static final String COLUMN_STATE_ID = "state_id";
    public static final String COLUMN_DISTRICT_ID = "district_id";
    public static final String COLUMN_REF_ID = "ref_id";
    public static final String COLUMN_STATUS = "status";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_BLOCK_ID + " TEXT ,"
                    + COLUMN_BLOCK_NAME + " TEXT ,"
                    + COLUMN_STATE_ID + " TEXT ,"
                    + COLUMN_DISTRICT_ID + " TEXT ,"
                    + COLUMN_REF_ID + " TEXT ,"
                    + COLUMN_STATUS + " TEXT "
                    + ")";

}
