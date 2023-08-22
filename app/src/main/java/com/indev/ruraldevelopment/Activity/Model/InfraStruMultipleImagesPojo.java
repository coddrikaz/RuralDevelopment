package com.indev.ruraldevelopment.Activity.Model;

public class InfraStruMultipleImagesPojo {

    private String monitor_image_id;
    private String local_id;
    private String infra_monitoring_id;
    private String status;
    private String user_id;
    private String infra_monitoring_img;
    private String flag;
    private String image;

    public String getLocal_id() {
        return local_id;
    }

    public void setLocal_id(String local_id) {
        this.local_id = local_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMonitor_image_id() {
        return monitor_image_id;
    }

    public void setMonitor_image_id(String monitor_image_id) {
        this.monitor_image_id = monitor_image_id;
    }

    public String getInfra_monitoring_id() {
        return infra_monitoring_id;
    }

    public void setInfra_monitoring_id(String infra_monitoring_id) {
        this.infra_monitoring_id = infra_monitoring_id;
    }

    public String getInfra_monitoring_img() {
        return infra_monitoring_img;
    }

    public void setInfra_monitoring_img(String infra_monitoring_img) {
        this.infra_monitoring_img = infra_monitoring_img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }



    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }



    private static final String TABLE_NAME = "monitor_infra_image";
    private static final String COLUMN_MONITOR_IMAGE_ID = "infra_monitoring_id";
    private static final String COLUMN_LOCAL_ID = "local_id";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_INFRA_MONITORING_IMG ="infra_monitoring_img";
    private static final String COLUMN_FLAG ="flag";
    private static final String COLUMN_STATUS ="status";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_MONITOR_IMAGE_ID + " TEXT, "
                    + COLUMN_USER_ID + " TEXT, "
                    + COLUMN_INFRA_MONITORING_IMG + " TEXT, "
                    + COLUMN_FLAG + " TEXT, "
                    + COLUMN_STATUS + " TEXT "
                    +")";
}
