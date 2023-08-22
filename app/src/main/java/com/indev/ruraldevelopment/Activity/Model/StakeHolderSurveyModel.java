package com.indev.ruraldevelopment.Activity.Model;

public class StakeHolderSurveyModel {
    private int local_id;
    private int survey_id;
    private String date_of_survey;
    private String person_name;
    private String designation;
    private String stakeholder_category_id;
    private String heard_about_jubliant_life_science;
    private String employee_discussed_about_company_activity;
    private String equipped_to_manage_operation;
    private String mock_drill_related_to_emergency;
    private String discussion_conduct_for_concern;
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
    private String financial_year;
    private String app_version;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getFinancial_year() {
        return financial_year;
    }

    public void setFinancial_year(String financial_year) {
        this.financial_year = financial_year;
    }

    public int getLocal_id() {
        return local_id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public String getDate_of_survey() {
        return date_of_survey;
    }

    public void setDate_of_survey(String date_of_survey) {
        this.date_of_survey = date_of_survey;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getStakeholder_category_id() {
        return stakeholder_category_id;
    }

    public void setStakeholder_category_id(String stakeholder_category_id) {
        this.stakeholder_category_id = stakeholder_category_id;
    }

    public String getHeard_about_jubliant_life_science() {
        return heard_about_jubliant_life_science;
    }

    public void setHeard_about_jubliant_life_science(String heard_about_jubliant_life_science) {
        this.heard_about_jubliant_life_science = heard_about_jubliant_life_science;
    }

    public String getEmployee_discussed_about_company_activity() {
        return employee_discussed_about_company_activity;
    }

    public void setEmployee_discussed_about_company_activity(String employee_discussed_about_company_activity) {
        this.employee_discussed_about_company_activity = employee_discussed_about_company_activity;
    }

    public String getEquipped_to_manage_operation() {
        return equipped_to_manage_operation;
    }

    public void setEquipped_to_manage_operation(String equipped_to_manage_operation) {
        this.equipped_to_manage_operation = equipped_to_manage_operation;
    }

    public String getMock_drill_related_to_emergency() {
        return mock_drill_related_to_emergency;
    }

    public void setMock_drill_related_to_emergency(String mock_drill_related_to_emergency) {
        this.mock_drill_related_to_emergency = mock_drill_related_to_emergency;
    }

    public String getDiscussion_conduct_for_concern() {
        return discussion_conduct_for_concern;
    }

    public void setDiscussion_conduct_for_concern(String discussion_conduct_for_concern) {
        this.discussion_conduct_for_concern = discussion_conduct_for_concern;
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

    public static final String TABLE_NAME = "stakeholder_survey";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_SURVEY_ID = "survey_id";
    public static final String COLUMN_DATE_OF_SURVEY = "date_of_survey";
    public static final String COLUMN_PERSON = "person_name";
    public static final String COLUMN_FINANCIAL_YEAR= "financial_year";
    public static final String COLUMN_DESIGNATION = "designation";
    public static final String COLUMN_STAKE_HOLDER_CATEGORY_ID = "stakeholder_category_id";
    public static final String COLUMN_HEARD_ABOUT_JUBLIANT_LIFE_SCIENCE = "heard_about_jubliant_life_science";
    public static final String COLUMN_EMPLOYEE_DISCUSSED_ABOUT_COMPANY_ACTIVITY = "employee_discussed_about_company_activity";
    public static final String COLUMN_EQUIPPED_TO_MANAGE_OPERATION = "equipped_to_manage_operation ";
    public static final String COLUMN_MOCK_DRILL_RELATED_TO_EMERGENCY = "mock_drill_related_to_emergency";
    public static final String COLUMN_DISCUSSION_CONDUCT_FOR_CONCERN = "discussion_conduct_for_concern";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_RESOURCE_ID = "resource_id";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_STATE_ID = "state_id";
    private static final String COLUMN_DISTRICT_ID = "district_id";
    private static final String COLUMN_BLOCK_ID = "block_id";
    private static final String COLUMN_VILLAGE_ID = "village_id";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";
    private static final String COLUMN_CREATED_AT = "created_at";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_FLAG = "flag";
    private static final String COLUMN_REMARK = "remark";
    private static final String COLUMN_APP_VERSION = "app_version";



    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + COLUMN_SURVEY_ID + " TEXT ,"
                    + COLUMN_DATE_OF_SURVEY + " TEXT ,"
                    + COLUMN_PERSON + " TEXT ,"
                    + COLUMN_DESIGNATION + " TEXT ,"
                    + COLUMN_STAKE_HOLDER_CATEGORY_ID + " TEXT ,"
                    + COLUMN_HEARD_ABOUT_JUBLIANT_LIFE_SCIENCE + " TEXT ,"
                    + COLUMN_EMPLOYEE_DISCUSSED_ABOUT_COMPANY_ACTIVITY + " TEXT ,"
                    + COLUMN_EQUIPPED_TO_MANAGE_OPERATION + " TEXT ,"
                    + COLUMN_MOCK_DRILL_RELATED_TO_EMERGENCY + " TEXT ,"
                    + COLUMN_DISCUSSION_CONDUCT_FOR_CONCERN + " TEXT ,"
                    + COLUMN_IMAGE + " TEXT ,"
                    + COLUMN_RESOURCE_ID + " TEXT ,"
                    + COLUMN_USER_ID + " TEXT ,"
                    + COLUMN_STATE_ID + " TEXT ,"
                    + COLUMN_DISTRICT_ID + " TEXT ,"
                    + COLUMN_BLOCK_ID + " TEXT ,"
                    + COLUMN_FINANCIAL_YEAR + " TEXT ,"
                    + COLUMN_VILLAGE_ID + " TEXT ,"
                    + COLUMN_LATITUDE + " TEXT ,"
                    + COLUMN_LONGITUDE + " TEXT ,"
                    + COLUMN_CREATED_AT + " TEXT ,"
                    + COLUMN_STATUS + " TEXT ,"
                    + COLUMN_REMARK + " TEXT ,"
                    + COLUMN_APP_VERSION + " TEXT ,"
                    + COLUMN_FLAG + " TEXT "
                    + ")";
}
