package com.indev.ruraldevelopment.Activity.Model;

import java.util.ArrayList;

public class MonitorModel {

    private int local_id;
    private int monitor_resource_id;
    private int resource_id;
    private String date_of_monitor;
    private String farming_near_structure;
//    private String sewage_at_pond;
    private String damaged_structure;
    private String jubliant_visibility_board_near_structure;
    private String description;
    private String image;
    private int user_id;
    private int state_id;
    private int district_id;
    private int block_id;
    private int village_id;
    private String latitude;
    private String longitude;
    private String created_at;
    private String status;
    private String remarks;
    private String flag;
    private int resource_mapping_id;
    private String app_version;

    /////
    private String structure_functional;

    public String getStructure_functional() {
        return structure_functional;
    }

    public void setStructure_functional(String structure_functional) {
        this.structure_functional = structure_functional;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public int getResource_mapping_id() {
        return resource_mapping_id;
    }

    public void setResource_mapping_id(int resource_mapping_id) {
        this.resource_mapping_id = resource_mapping_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getLocal_id() {
        return local_id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public int getMonitor_resource_id() {




        return monitor_resource_id;
    }

    public void setMonitor_resource_id(int monitor_resource_id) {
        this.monitor_resource_id = monitor_resource_id;
    }

    public String getDate_of_monitor() {
        return date_of_monitor;
    }

    public void setDate_of_monitor(String date_of_monitor) {
        this.date_of_monitor = date_of_monitor;
    }

    public String getFarming_near_structure() {
        return farming_near_structure;
    }

    public void setFarming_near_structure(String farming_near_structure) {
        this.farming_near_structure = farming_near_structure;
    }
//
//    public String getSewage_at_pond() {
//        return sewage_at_pond;
//    }
//
//    public void setSewage_at_pond(String sewage_at_pond) {
//        this.sewage_at_pond = sewage_at_pond;
//    }

    public String getDamaged_structure() {
        return damaged_structure;
    }

    public void setDamaged_structure(String damaged_structure) {
        this.damaged_structure = damaged_structure;
    }

    public String getJubliant_visibility_board_near_structure() {
        return jubliant_visibility_board_near_structure;
    }

    public void setJubliant_visibility_board_near_structure(String jubliant_visibility_board_near_structure) {
        this.jubliant_visibility_board_near_structure = jubliant_visibility_board_near_structure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getResource_id() {
        return resource_id;
    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public int getBlock_id() {
        return block_id;
    }

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }

    public int getVillage_id() {
        return village_id;
    }

    public void setVillage_id(int village_id) {
        this.village_id = village_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    ArrayList<MonitorMultipleImagePojo> monitor_resource_image=new ArrayList<>();

    public ArrayList<MonitorMultipleImagePojo> getMonitor_resource_image() {
        return monitor_resource_image;
    }

    public void setMonitor_resource_image(ArrayList<MonitorMultipleImagePojo> monitor_resource_image) {
        this.monitor_resource_image = monitor_resource_image;
    }

    private static final String TABLE_NAME = "monitor_resource";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_MONITOR_RESOURCE_ID = "monitor_resource_id";
    public static final String COLUMN_DATE_OF_MONITOR = "date_of_monitor";
    public static final String COLUMN_RESOURCE_MAPPING_ID = "resource_mapping_id";
    public static final String COLUMN_FARMING_NEAR_STRUCTURE= "farming_near_structure";
//    public static final String COLUMN_SEWAGE_AT_POND = "sewage_at_pond";
    public static final String COLUMN_DAMAGED_STRUCTURE = "damaged_structure";
    public static final String COLUMN_JUBLIANT_VISIBILITY_BOARD_NEAR_STRUCTURE = "jubliant_visibility_board_near_structure";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE ="image";
    public static final String COLUMN_RESOURCE_ID ="resource_id";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_STATE_ID = "state_id";
    private static final String COLUMN_DISTRICT_ID = "district_id";
    private static final String COLUMN_BLOCK_ID = "block_id";
    private static final String COLUMN_VILLAGE_ID = "village_id";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE= "longitude";
    private static final String COLUMN_CREATED_AT = "created_at";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_FLAG = "flag";
    private static final String COLUMN_APP_VERSION = "app_version";
    private static final String COLUMN_STRUCTURE_FUNCTIONAL = "structure_functional";




    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_MONITOR_RESOURCE_ID + " TEXT ,"
                    + COLUMN_DATE_OF_MONITOR + " TEXT ,"
                    + COLUMN_FARMING_NEAR_STRUCTURE + " TEXT ,"
//                    + COLUMN_SEWAGE_AT_POND + " TEXT ,"
                    + COLUMN_DAMAGED_STRUCTURE + " TEXT ,"
                    + COLUMN_JUBLIANT_VISIBILITY_BOARD_NEAR_STRUCTURE + " TEXT ,"
                    + COLUMN_DESCRIPTION + " TEXT ,"
                    + COLUMN_IMAGE + " TEXT ,"
                    + COLUMN_RESOURCE_ID + " TEXT ,"
                    + COLUMN_USER_ID + " TEXT ,"
                    + COLUMN_STATE_ID + " TEXT ,"
                    + COLUMN_STRUCTURE_FUNCTIONAL + " TEXT ,"
                    + COLUMN_RESOURCE_MAPPING_ID + " TEXT ,"
                    + COLUMN_DISTRICT_ID + " TEXT ,"
                    + COLUMN_BLOCK_ID + " TEXT ,"
                    + COLUMN_VILLAGE_ID + " TEXT ,"
                    + COLUMN_LATITUDE + " TEXT ,"
                    + COLUMN_LONGITUDE + " TEXT ,"
                    + COLUMN_CREATED_AT + " TEXT ,"
                    + COLUMN_REMARKS + " TEXT ,"
                    + COLUMN_STATUS + " TEXT ,"
                    + COLUMN_APP_VERSION + " TEXT , "
                    + COLUMN_FLAG + " TEXT "
                    + ")";
}
