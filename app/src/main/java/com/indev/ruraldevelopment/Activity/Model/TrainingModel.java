package com.indev.ruraldevelopment.Activity.Model;

import java.util.ArrayList;

public class TrainingModel {
    private  int local_id;
    private  int training_id;
    private  String training_name;
    private  String start_date;
    private  String end_date;
    private  String total_attendance;
//    private String training_image_name;
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
    private String app_version;
    private String objective;
    private String trainer_name;
    private String male;
    private String female;

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

//    public String getTraining_image_name() {
//        return training_image_name;
//    }
//
//    public void setTraining_image_name(String training_image_name) {
//        this.training_image_name = training_image_name;
//    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public int getLocal_id() {
        return local_id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public int getTraining_id() {
        return training_id;
    }

    public void setTraining_id(int training_id) {
        this.training_id = training_id;
    }

    public String getTraining_name() {
        return training_name;
    }

    public void setTraining_name(String training_name) {
        this.training_name = training_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getTotal_attendance() {
        return total_attendance;
    }

    public void setTotal_attendance(String total_attendance) {
        this.total_attendance = total_attendance;
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

    ArrayList<TrainingMultipleImagePojo> training_image_name=new ArrayList<>();

    public ArrayList<TrainingMultipleImagePojo> getTraining_image_name() {
        return training_image_name;
    }

    public void setTraining_image_name(ArrayList<TrainingMultipleImagePojo> training_image_name) {
        this.training_image_name = training_image_name;
    }

    public static final String TABLE_NAME = "training";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_TRAINING_ID = "training_id";
    public static final String COLUMN_TRAINING_NAME = "training_name";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    private static final String COLUMN_TOTAL_ATTENDANCE = "total_attendance";
    public static final String COLUMN_IMAGE_NAME = "training_image_name";
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

    private static final String COLUMN_OBJECTIVE = "objective";
    private static final String COLUMN_TRAINER = "trainer_name";
    private static final String COLUMN_MALE = "male";
    private static final String COLUMN_FEMALE = "female";
    private static final String COLUMN_APP_VERSION = "app_version";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TRAINING_ID + " TEXT ,"
                    + COLUMN_TRAINING_NAME + " TEXT ,"
                    + COLUMN_START_DATE + " TEXT ,"
                    + COLUMN_END_DATE + " TEXT ,"
                    + COLUMN_TOTAL_ATTENDANCE + " TEXT ,"
                    + COLUMN_IMAGE_NAME + " TEXT ,"
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

                    + COLUMN_OBJECTIVE + " TEXT ,"
                    + COLUMN_MALE + " TEXT ,"
                    + COLUMN_FEMALE + " TEXT ,"
                    + COLUMN_TRAINER + " TEXT ,"
                    + COLUMN_APP_VERSION + " TEXT ,"

                    + COLUMN_FLAG + " TEXT "
                    + ")";


}
