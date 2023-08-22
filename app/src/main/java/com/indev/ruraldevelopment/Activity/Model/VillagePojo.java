package com.indev.ruraldevelopment.Activity.Model;

public class VillagePojo {

    private  String village_id;
    private  String village_name;
    private  String state_id;
    private  String district_id;
    private  String block_id;
    private  String status;
    private  String description;
    private  String address;
    private  int user_id;

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
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

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private static final String TABLE_NAME = "village";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_VILLAGE_ID = "village_id";
    public static final String COLUMN_BLOCK_ID = "block_id";
    public static final String COLUMN_VILLAGE_NAME = "village_name";
    public static final String COLUMN_STATE_ID = "state_id";
    public static final String COLUMN_DISTRICT_ID = "district_id";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ADDRESS = "address";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_BLOCK_ID + " TEXT ,"
                    + COLUMN_VILLAGE_NAME + " TEXT ,"
                    + COLUMN_STATE_ID + " TEXT ,"
                    + COLUMN_DISTRICT_ID + " TEXT ,"
                    + COLUMN_VILLAGE_ID + " TEXT ,"
                    + COLUMN_USER_ID + " TEXT ,"
                    + COLUMN_DESCRIPTION + " TEXT ,"
                    + COLUMN_ADDRESS + " TEXT ,"
                    + COLUMN_STATUS + " TEXT "
                    + ")";

}
