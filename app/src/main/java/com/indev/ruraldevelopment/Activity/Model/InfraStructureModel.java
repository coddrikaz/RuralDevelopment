package com.indev.ruraldevelopment.Activity.Model;

public class InfraStructureModel {
    private int local_id;
    private int infra_id;
    private String site_name;
    private String infra_structure_name;
    private String caretaker;
    private String mobile_no;
    private String start_date;
    private String end_date;
    private String address;
    private String amount;
    private String expected_beneficial;
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
    private String infra_status_id;
    private String app_version;
    private String subresource_id;

    public int getLocal_id() {
        return local_id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public int getInfra_id() {
        return infra_id;
    }

    public void setInfra_id(int infra_id) {
        this.infra_id = infra_id;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getInfra_structure_name() {
        return infra_structure_name;
    }

    public void setInfra_structure_name(String infra_structure_name) {
        this.infra_structure_name = infra_structure_name;
    }

    public String getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(String caretaker) {
        this.caretaker = caretaker;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExpected_beneficial() {
        return expected_beneficial;
    }

    public void setExpected_beneficial(String expected_beneficial) {
        this.expected_beneficial = expected_beneficial;
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

    public String getInfra_status_id() {
        return infra_status_id;
    }

    public void setInfra_status_id(String infra_status_id) {
        this.infra_status_id = infra_status_id;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getSubresource_id() {
        return subresource_id;
    }

    public void setSubresource_id(String subresource_id) {
        this.subresource_id = subresource_id;
    }

    public static final String TABLE_NAME = "infra_structure";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_INFRA_ID = "infra_id";
    public static final String COLUMN_SITE_NAME = "site_name";
    public static final String COLUMN_INFRA_STRUCTURE_NAME = "infra_structure_name";
    public static final String COLUMN_CARETAKER = "caretaker";
    public static final String COLUMN_MOBILE_NO = "mobile_no";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_EXPECTED_BENEFICIAL = "expected_beneficial";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_RESOURCE_ID = "resource_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_STATE_ID = "state_id";
    private static final String COLUMN_DISTRICT_ID = "district_id";
    private static final String COLUMN_BLOCK_ID = "block_id";
    private static final String COLUMN_VILLAGE_ID = "village_id";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";
    private static final String COLUMN_CREATED_AT = "created_at";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_INFRA_STATUS_ID = "infra_status_id";
    private static final String COLUMN_FLAG = "flag";
    private static final String COLUMN_APP_VERSION = "app_version";
    private static final String COLUMN_SUBRESOURCE_ID = "subresource_id";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + COLUMN_INFRA_ID + " TEXT ,"
                    + COLUMN_SITE_NAME + " TEXT ,"
                    + COLUMN_INFRA_STRUCTURE_NAME + " TEXT ,"
                    + COLUMN_CARETAKER + " TEXT ,"
                    + COLUMN_MOBILE_NO + " TEXT ,"
                    + COLUMN_START_DATE + " TEXT ,"
                    + COLUMN_END_DATE + " TEXT ,"
                    + COLUMN_ADDRESS + " TEXT ,"
                    + COLUMN_AMOUNT + " TEXT ,"
                    + COLUMN_EXPECTED_BENEFICIAL + " TEXT ,"
                    + COLUMN_DESCRIPTION + " TEXT ,"
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
                    + COLUMN_INFRA_STATUS_ID + " TEXT ,"
                    + COLUMN_APP_VERSION + " TEXT ,"
                    + COLUMN_SUBRESOURCE_ID + " TEXT ,"
                    + COLUMN_FLAG + " TEXT "
                    + ")";
}
