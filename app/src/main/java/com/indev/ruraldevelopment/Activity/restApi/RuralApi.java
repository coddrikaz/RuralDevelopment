package com.indev.ruraldevelopment.Activity.restApi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RuralApi {
    @POST("login.php")
    Call<JsonObject> LoginApi(@Body RequestBody body);

    @POST("download_general.php")
    Call<JsonArray> download_general(@Body RequestBody body);

    @POST("resource_mapping_list.php")
    Call<JsonArray> Resource_data_download(@Body RequestBody body);

    @POST("download_village.php")
    Call<JsonArray> Village_data_download(@Body RequestBody body);

    //DOWNLOAD RESOURCES IMAGES
    @POST("multiple_resource_img.php")
    Call<JsonArray> Resource_images_download(@Body RequestBody body);

    @POST("multiple_training_image.php")
    Call<JsonArray> Training_images_download(@Body RequestBody body);

    @POST("download_infra_monitor.php")
    Call<JsonArray> Infra_Monitor_download(@Body RequestBody body);
    @POST("multiple_infra_img.php")
    Call<JsonArray> Infra_Monitor_images_download(@Body RequestBody body);

    @POST("multiple_moniter_image.php")
    Call<JsonArray>Monitor_Resource_images_download(@Body RequestBody body);

    //DOWNLOAD VILLAGE IMAGES
    @POST("multiple_village_image.php")
    Call<JsonArray> Village_images_download(@Body RequestBody body);

    @POST("download_committee_member.php")
    Call<JsonArray> committee_data_download(@Body RequestBody body);

    @POST("download_tranning.php")
    Call<JsonArray> training_data_download(@Body RequestBody body);

    @POST("download_stakeholder_survey.php")
    Call<JsonArray> survey_data_download(@Body RequestBody body);

    @POST("download_infrastructure.php")
    Call<JsonArray> infra_data_download(@Body RequestBody body);

    @POST("download_resource_monitor.php")
    Call<JsonArray> monitor_data_download(@Body RequestBody body);

    @POST("download_village_profile.php")
    Call<JsonArray> village_profile_data_download(@Body RequestBody body);

    //SYNC

    @POST("add_infrastructure.php")
    Call<JsonObject> Infra_data_sync(@Body RequestBody body);


    @POST("add_stakeholder_survey.php")
    Call<JsonObject> Survey_data_sync(@Body RequestBody body);

    @POST("update_commiteemember_status.php")
    Call<JsonObject> UpdateActiveCommite(@Body RequestBody body);


    @POST("add_resource_mapping.php")
    Call<JsonObject> Resource_Mapping_data_sync(@Body RequestBody body);

    @POST("add_training.php")
    Call<JsonObject> Training_data_sync(@Body RequestBody body);

    @POST("add_monotring_resource.php")
    Call<JsonObject> Resource_Monitoring_data_sync(@Body RequestBody body);

    @POST("add_multiimage_monitoring.php")
    Call<JsonObject> Resource_Monitoring_MultiImage_data_sync(@Body RequestBody body);

    @POST("add_villageprofile.php")
    Call<JsonObject> Village_Profile_data_sync(@Body RequestBody body);

    @POST("add_committee_member.php")
    Call<JsonObject> Member_data_sync(@Body RequestBody body);

    @POST("add_infra_monitoring.php")
    Call<JsonObject> InfraMonitoringSync(@Body RequestBody body);
}
