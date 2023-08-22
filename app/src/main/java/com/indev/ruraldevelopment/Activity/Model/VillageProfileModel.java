package com.indev.ruraldevelopment.Activity.Model;

import java.util.ArrayList;

public class VillageProfileModel {
    private static final String TABLE_NAME = "village_profile";
    private static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_VILLAGE_PROFILE_ID = "village_profile_id";
    public static final String COLUMN_POPULATION = "population";
    public static final String COLUMN_PRADHAN_NAME = "pradhan_name";
    private static final String COLUMN_MOBILE_NUMBER = "mobile_no";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_RESOURCE_ID = "resource_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_STATE_ID = "state_id";
    private static final String COLUMN_DISTRICT_ID = "district_id";
    private static final String COLUMN_BLOCK_ID = "block_id";
    private static final String COLUMN_VILLAGE_ID = "village_id";
    private static final String COLUMN_LATITUDE= "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";
    private static final String COLUMN_CREATED_AT = "created_at";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_FLAG = "flag";
    private static final String COLUMN_APP_VERSION = "app_version";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_VILLAGE_PROFILE_ID + " TEXT ,"
                    + COLUMN_POPULATION + " TEXT ,"
                    + COLUMN_PRADHAN_NAME + " TEXT ,"
                    + COLUMN_MOBILE_NUMBER + " TEXT ,"
                    + COLUMN_IMAGE + " TEXT ,"
                    + COLUMN_RESOURCE_ID + " TEXT ,"
                    + COLUMN_USER_ID + " TEXT ,"
                    + COLUMN_STATE_ID + " TEXT ,"
                    + COLUMN_DISTRICT_ID + " TEXT ,"
                    + COLUMN_BLOCK_ID + " TEXT ,"
                    + COLUMN_VILLAGE_ID + " TEXT ,"
                    + COLUMN_LATITUDE + " TEXT ,"
                    + COLUMN_LONGITUDE + " TEXT ,"
                    + COLUMN_CREATED_AT + " TEXT ,"
                    + COLUMN_STATUS + " TEXT ,"
                    + COLUMN_APP_VERSION + " TEXT ,"
                    + COLUMN_FLAG + " TEXT "
                    + ")";

    private int local_id;
    private int village_profile_id;
    private String population;
    private String longitude;
    private String created_at;
    private String status;
    private String pradhan_name;
    private String mobile_no;
    private String image;
    private int resource_id;
    private int user_id;
    private int state_id;
    private int district_id;
    private int block_id;
    private int village_id;
    private String latitude;
    private String flag;
    private String app_version;

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public int getLocal_id() {
        return local_id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public int getVillage_profile_id() {
        return village_profile_id;
    }

    public void setVillage_profile_id(int village_profile_id) {
        this.village_profile_id = village_profile_id;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getPradhan_name() {
        return pradhan_name;
    }

    public void setPradhan_name(String pradhan_name) {
        this.pradhan_name = pradhan_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
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


// VILLAGE IMAGES
    ArrayList<MultipleImagePojo> village_profile_img=new ArrayList<>();

    public ArrayList<MultipleImagePojo> getVillage_profile_img() {
        return village_profile_img;
    }

    public void setVillage_profile_img(ArrayList<MultipleImagePojo> village_profile_img) {
        this.village_profile_img = village_profile_img;
    }


// COMMITTEE MEMBERS
    ArrayList<Committee_Table> committee_member=new ArrayList<>();

    public ArrayList<Committee_Table> getCommittee_member() {
        return committee_member;
    }

    public void setCommittee_member(ArrayList<Committee_Table> committee_member) {
        this.committee_member = committee_member;
    }

    // RESOURCES MAPPING
    ArrayList<ResourceMappingPojo> resource_mapping=new ArrayList<>();

    public ArrayList<ResourceMappingPojo> getResource_mapping() {
        return resource_mapping;
    }

    public void setResource_mapping(ArrayList<ResourceMappingPojo> resource_mapping) {
        this.resource_mapping = resource_mapping;
    }
}
