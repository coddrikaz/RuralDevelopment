package com.indev.ruraldevelopment.Activity.Model;

public class MemberRolePojo {
    private  int role_id;
    private  String role_name;
    private  String status;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private static final String TABLE_NAME = "member_role";
    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_ROLE_NAME = "role_name";
    public static final String COLUMN_STATUS = "status";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ROLE_ID + " TEXT ,"
                    + COLUMN_ROLE_NAME + " TEXT ,"
                    + COLUMN_STATUS + " TEXT "
                    + ")";
}
