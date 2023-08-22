package com.indev.ruraldevelopment.Activity.Model;

public class Stakeholder_CategoryPojo {
    private String stakeholder_category_id;
    private String stakeholder_category_name;
    private String created_at;
    private String status;


    public String getStakeholder_category_id() {
        return stakeholder_category_id;
    }

    public void setStakeholder_category_id(String stakeholder_category_id) {
        this.stakeholder_category_id = stakeholder_category_id;
    }

    public String getStakeholder_category_name() {
        return stakeholder_category_name;
    }

    public void setStakeholder_category_name(String stakeholder_category_name) {
        this.stakeholder_category_name = stakeholder_category_name;
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

    private static final String TABLE_NAME = "stakeholder_category";
    public static final String COLUMN_LOCAL_ID = "local_id";
    public static final String COLUMN_STAKEHOLDER_CATEGORY_ID = "stakeholder_category_id";
    public static final String COLUMN_STAKEHOLDER_CATEGORY_NAME = "stakeholder_category_name";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_STATUS = "status";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_STAKEHOLDER_CATEGORY_ID + " TEXT ,"
                    + COLUMN_STAKEHOLDER_CATEGORY_NAME + " TEXT ,"
                    + COLUMN_CREATED_AT + " TEXT ,"
                    + COLUMN_STATUS + " TEXT "
                    + ")";

}
