package com.indev.ruraldevelopment.Activity.Model;

import java.util.ArrayList;

public class ResourceMappingPojo {
    private int local_id;
    private int resource_mapping_id;
    private int village_profile_id;
    private String resource_mapping_name;
    private String resource_type_id;
    private String contact_mob_no;
    private String contact_person;
    private String resource_status;
    private String address;
    private String description;
    private String image;
    private int resource_id;
    private int user_id;
    private int state_id;
    private int district_id;
    private int block_id;
    private int village_id;
    private String latitude;
    private String longitude;
    private String created_at;
    private String status;
    private String flag;
    private String resource_mapping_date;
    private String app_version;


    public String getContact_person() {return contact_person;}

    public void setContact_person(String contact_person) {this.contact_person = contact_person;}

    public String getContact_mob_no() {return contact_mob_no;}

    public void setContact_mob_no(String contact_mob_no) {this.contact_mob_no = contact_mob_no;}

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public int getVillage_profile_id() {
        return village_profile_id;
    }

    public void setVillage_profile_id(int village_profile_id) {
        this.village_profile_id = village_profile_id;
    }

    public String getResource_mapping_date() {
        return resource_mapping_date;
    }

    public void setResource_mapping_date(String resource_mapping_date) {
        this.resource_mapping_date = resource_mapping_date;
    }

    public int getLocal_id() {
        return local_id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public int getResource_mapping_id() {
        return resource_mapping_id;
    }

    public void setResource_mapping_id(int resource_mapping_id) {
        this.resource_mapping_id = resource_mapping_id;
    }

    public String getResource_mapping_name() {
        return resource_mapping_name;
    }

    public void setResource_mapping_name(String resource_mapping_name) {
        this.resource_mapping_name = resource_mapping_name;
    }

    public String getResource_type_id() {
        return resource_type_id;
    }

    public void setResource_type_id(String resource_type_id) {
        this.resource_type_id = resource_type_id;
    }

    public String getResource_status() {
        return resource_status;
    }

    public void setResource_status(String resource_status) {
        this.resource_status = resource_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    ArrayList<ResourceMultipleImagePojo> resource_mapping_image=new ArrayList<>();

    public ArrayList<ResourceMultipleImagePojo> getResource_mapping_image() {
        return resource_mapping_image;
    }

    public void setResource_mapping_image(ArrayList<ResourceMultipleImagePojo> resource_mapping_image) {
        this.resource_mapping_image = resource_mapping_image;
    }

    private static final String TABLE_NAME = "resource_mapping";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_RESOURCE_MAPPING_ID = "resource_mapping_id";
    public static final String COLUMN_RESOURCE_MAPPING_NAME = "resource_mapping_name";
    public static final String COLUMN_RESOURCE_TYPE_ID= "resource_type_id";
    public static final String COLUMN_RESOURCE_STATUS = "resource_status";
    public static final String COLUMN_RESOURCE_MAPPING_DATA = "resource_mapping_date";
    public static final String COLUMN_ADDRESS= "address";
    private static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE = " image";
    public static final String COLUMN_RESOURCE_ID = "resource_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_STATE_ID = "state_id";
    public static final String COLUMN_DISTRICT_ID = "district_id";
    public static final String COLUMN_BLOCK_ID = "block_id";
    public static final String COLUMN_CONTACT_NUMBER = "contact_mob_no";
    public static final String COLUMN_CONTACT_PERSON = "contact_person";
    public static final String COLUMN_VILLAGE_ID = "village_id";
    public static final String COLUMN_VILLAGE_PROFILE_ID = "village_profile_id";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_CREATED_AT = " created_at";
    public static final String COLUMN_STATUS = " status";
    public static final String COLUMN_FLAG = " flag";
    public static final String COLUMN_APP_VERSION = "app_version";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_RESOURCE_MAPPING_ID + " TEXT ,"
                    + COLUMN_RESOURCE_MAPPING_NAME + " TEXT ,"
                    + COLUMN_RESOURCE_TYPE_ID + " TEXT ,"
                    + COLUMN_RESOURCE_STATUS + " TEXT ,"
                    + COLUMN_ADDRESS + " TEXT ,"
                    + COLUMN_DESCRIPTION + " TEXT ,"
                    + COLUMN_IMAGE + " TEXT ,"
                    + COLUMN_RESOURCE_MAPPING_DATA + " TEXT ,"
                    + COLUMN_USER_ID + " TEXT, "
                    + COLUMN_RESOURCE_ID + " TEXT ,"
                    + COLUMN_STATE_ID + " TEXT, "
                    + COLUMN_DISTRICT_ID + " TEXT, "
                    + COLUMN_BLOCK_ID + " TEXT, "
                    + COLUMN_VILLAGE_ID + " TEXT, "
                    + COLUMN_VILLAGE_PROFILE_ID + " TEXT, "
                    + COLUMN_LATITUDE + " TEXT, "
                    + COLUMN_LONGITUDE + " TEXT, "
                    + COLUMN_CREATED_AT + " TEXT ,"
                    + COLUMN_STATUS + " TEXT ,"
                    + COLUMN_APP_VERSION + " TEXT ,"
                    + COLUMN_CONTACT_NUMBER + " TEXT ,"
                    + COLUMN_CONTACT_PERSON + " TEXT ,"
                    + COLUMN_FLAG + " TEXT "
                    + ")";
}
