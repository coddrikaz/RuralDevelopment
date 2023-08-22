package com.indev.ruraldevelopment.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.Committee_Table;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMonitoringPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMultipleImagesPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStructureModel;
import com.indev.ruraldevelopment.Activity.Model.MonitorModel;
import com.indev.ruraldevelopment.Activity.Model.MonitorMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMappingPojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.StakeHolderSurveyModel;
import com.indev.ruraldevelopment.Activity.Model.TrainingModel;
import com.indev.ruraldevelopment.Activity.Model.TrainingMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.VillageProfileModel;
import com.indev.ruraldevelopment.Activity.Utils.CommonClass;
import com.indev.ruraldevelopment.Activity.restApi.APIClient;
import com.indev.ruraldevelopment.Activity.restApi.RuralApi;
import com.indev.ruraldevelopment.R;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Synchronize extends AppCompatActivity {
//    @BindView(R.id.tvResourcedata)
//    android.widget.TextView btnFarmerRegistration;

    @BindView(R.id.tvResourceCount)
    android.widget.TextView tvResourceCount;

    @BindView(R.id.llResources)
    LinearLayout llResources;

    @BindView(R.id.tvTrainingCount)
    android.widget.TextView tvTrainingCount;

    @BindView(R.id.llTraining)
    LinearLayout llTraining;

    @BindView(R.id.tvVillageProfileCount)
    android.widget.TextView tvVillageProfileCount;

    @BindView(R.id.tvSurveyCount)
    android.widget.TextView tvSurveyCount;

    @BindView(R.id.llsurvey)
    LinearLayout llsurvey;

    @BindView(R.id.tvInfraCount)
    TextView tvInfraCount;

    @BindView(R.id.llInfra)
    LinearLayout llInfra;

    @BindView(R.id.tvInfraMonitoring_data_count)  //Infra Monitoring Count Data
    TextView tvInfraMonitoring_data_count;

    @BindView(R.id.tvResourceMonitoringCount)
    TextView tvResourceMonitoringCount;

    @BindView(R.id.tvCommitteeMemberCount)
    TextView tvCommitteeMemberCount;

    private Context context = this;
    private String TAG="";
    SqliteHelper sqliteHelper;
    private SharedPrefHelper sharedPrefHelper;
    private ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList = new ArrayList<>();
    private ArrayList<InfraStructureModel> infraStructureModelArrayList = new ArrayList<InfraStructureModel>();
    private ArrayList<StakeHolderSurveyModel> stakeHolderSurveyModelArrayList = new ArrayList<>();
    private ArrayList<TrainingModel> trainingModelArrayList = new ArrayList<>();
    private ArrayList<Committee_Table> committee_tableArrayList = new ArrayList<>();
    private ArrayList<VillageProfileModel> villageProfileModelArrayList = new ArrayList<>();
    private ArrayList<MonitorModel> monitorModelArrayList = new ArrayList<>();
    private ArrayList<InfraStruMonitoringPojo> infraStruMonitoringPojoArrayList = new ArrayList<>();
    private int countResource = 0, countMonitoring = 0,countMember=0,countStakeholder=0,countInfra=0, countTraining=0,countVillageProfile=0 ,countInfraMonitoring=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronize);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Synchronize");
        ButterKnife.bind(this);

        initViews();
        setDataCount();

        tvVillageProfileCount.setOnClickListener(view -> {
            sendVillageProfileDataOnServer();
        });

// __________________SEND MONITORING DATA SERVER ------------

        tvInfraMonitoring_data_count.setOnClickListener(view -> {
            sendMonitoringDataOnServer();
        });

        tvInfraCount.setOnClickListener(view -> {
            sendInfraDataOnServer();
        });

        tvSurveyCount.setOnClickListener(view -> {
            sendSurveyDataOnServer();
        });

        tvResourceCount.setOnClickListener(view -> {
            sendResourceMappingDataOnServer();
        });

        tvTrainingCount.setOnClickListener(view -> {
            sendTrainingDataOnServer();
        });

        tvCommitteeMemberCount.setOnClickListener(view -> {

            sendCommitteeMemberDataOnServer();
        });

        tvResourceMonitoringCount.setOnClickListener(view -> {
            sendMonitorResourceDataOnServer();
        });
    }
    private void setDataCount () {
        infraStructureModelArrayList = sqliteHelper.getInfraForSyn();
        countInfra = infraStructureModelArrayList.size();
        if (countInfra > 0) {
            Toast.makeText(context, ""+ countInfra, Toast.LENGTH_SHORT).show();
            tvInfraCount.setText(countInfra+"");
        }
        //-----------------------------------set count data ---     ------------

        infraStruMonitoringPojoArrayList = sqliteHelper.getInfraStructureMonitoring();
        countInfraMonitoring = infraStruMonitoringPojoArrayList.size();
        if (countInfraMonitoring > 0 ){
            Toast.makeText(context,""+ countInfraMonitoring,Toast.LENGTH_SHORT).show();
            tvInfraMonitoring_data_count.setText(countInfraMonitoring+"");
        }




        villageProfileModelArrayList = sqliteHelper.getVillageForSyn();
        countVillageProfile = villageProfileModelArrayList.size();
        if (countVillageProfile > 0) {
            Toast.makeText(context, ""+ countVillageProfile, Toast.LENGTH_SHORT).show();
            tvVillageProfileCount.setText(countVillageProfile+"");
        }

        stakeHolderSurveyModelArrayList = sqliteHelper.getSurveyForSyn();
        countStakeholder = stakeHolderSurveyModelArrayList.size();
        if (countStakeholder > 0) {
            Toast.makeText(context, ""+ countStakeholder, Toast.LENGTH_SHORT).show();
            tvSurveyCount.setText(countStakeholder+"");
        }

        resourceMappingPojoArrayList = sqliteHelper.getResourceForSyn(0);
        countResource = resourceMappingPojoArrayList.size();
        if (countResource > 0) {
            Toast.makeText(context, ""+ countResource, Toast.LENGTH_SHORT).show();
            tvResourceCount.setText(countResource+"");
        }

        trainingModelArrayList = sqliteHelper.getTrainingForSyn();
        countTraining = trainingModelArrayList.size();
        if (countTraining > 0) {
            Toast.makeText(context, ""+ countTraining, Toast.LENGTH_SHORT).show();
            tvTrainingCount.setText(countTraining+"");
        }

        committee_tableArrayList = sqliteHelper.getCommitteeMemberForSyn(0);
        countMember = committee_tableArrayList.size();
        if (countMember > 0) {
            Toast.makeText(context, ""+ countMember, Toast.LENGTH_SHORT).show();
            tvCommitteeMemberCount.setText(countMember+"");
        }

        monitorModelArrayList = sqliteHelper.getMonitorResourceForSyn();
        countMonitoring = monitorModelArrayList.size();
        if (countMonitoring > 0) {
            Toast.makeText(context, ""+ countMonitoring, Toast.LENGTH_SHORT).show();

            tvResourceMonitoringCount.setText(countMonitoring+"");
        }
    }

    private void initViews () {
        sqliteHelper = new SqliteHelper(this);
        sharedPrefHelper = new SharedPrefHelper(this);
    }



//    @OnClick({R.id.llInfra})
//    void onClick (View v){
//        switch (v.getId()) {
//            case R.id.llInfra:
//                sendInfraDataOnServer() ;
//                break;
//
//            case R.id.llsurvey:
//                sendSurveyDataOnServer();
//                break;
//
//            case R.id.llResources:
//                sendResourceMappingDataOnServer();
//                break;
//
//            case R.id.llTraining:
//                sendTrainingDataOnServer();
//                break;
//
//        }
//    }


    //- - - --- - - - - - - -- SEND MONITORING DATA  SEND TO SERVER----=-----------------

    private void sendMonitoringDataOnServer () {

        try {
            //in users table last inserted id is user_id
            //  int ids = sqliteHelper.getLastInsertedLocalID();

            if (CommonClass.isInternetOn(context)) {
                infraStruMonitoringPojoArrayList = sqliteHelper.getInfraStructureMonitoring();
                countInfraMonitoring = infraStruMonitoringPojoArrayList.size();
                if (countInfraMonitoring > 0) {
                    for (int i = 0; i < infraStruMonitoringPojoArrayList.size(); i++) {

                        ArrayList<InfraStruMultipleImagesPojo> infraStruMultipleImagesPojoArrayList=new ArrayList<>();
                        infraStruMultipleImagesPojoArrayList=sqliteHelper.inframlistingimages(String.valueOf(infraStruMonitoringPojoArrayList.get(i).getInfra_monitoring_id()));
                        infraStruMonitoringPojoArrayList.get(i).setInfra_monitoring_img(infraStruMultipleImagesPojoArrayList);


                        Gson gson = new Gson();
                        String data = gson.toJson(infraStruMonitoringPojoArrayList.get(i));
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, data);
//
                        if(countInfra==0) {
                            sendInfraDataSync(body, String.valueOf(infraStruMonitoringPojoArrayList.get(i).getLocal_id()), infraStruMonitoringPojoArrayList.get(i).getInfra_monitoring_id());
                        }else{
                            Toast.makeText(context, "Please Sync First InfraStructure Data ", Toast.LENGTH_LONG).show();

                        }
//                        }
                    }
                } else {
                    Toast.makeText(context, R.string.no_data_pending, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(Synchronize.this, getString(R.string.please_wait), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





    private void sendInfraDataOnServer () {
        try {
            //in users table last inserted id is user_id
          //  int ids = sqliteHelper.getLastInsertedLocalID();

            if (CommonClass.isInternetOn(context)) {
                infraStructureModelArrayList = sqliteHelper.getInfraForSyn();
                countInfra = infraStructureModelArrayList.size();
                if (countInfra > 0) {
                    for (int i = 0; i < infraStructureModelArrayList.size(); i++) {


                        Gson gson = new Gson();
                        String data = gson.toJson(infraStructureModelArrayList.get(i));
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, data);
//                        if (farmerRegistrationPojoArrayList.get(i).getOffline_sync() == 1 && farmerRegistrationPojoArrayList.get(i).getFlag().equals("1")) {
//                            sendEditFramerRegistrationData(body, userId);
//                        } else {
                        sendInfraData(body, String.valueOf(infraStructureModelArrayList.get(i).getInfra_id()));
//                        }
                    }
                } else {
                    Toast.makeText(context, R.string.no_data_pending, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(Synchronize.this, getString(R.string.please_wait), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void sendInfraData (RequestBody body, String infra_id){
        ProgressDialog dialog = ProgressDialog.show(Synchronize.this, "", getString(R.string.please_wait), true);
        APIClient.getRuralClient().create(RuralApi.class).Infra_data_sync(body).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString().trim());
                    dialog.dismiss();
                    Log.e("bchjc", "data " + jsonObject.toString());
                    String status = jsonObject.optString("status");
                    String message = jsonObject.optString("message");
                    String last_infra_id = jsonObject.optString("last_infra_id");

                    if (status.equalsIgnoreCase("1")) {
                        //update flag in tables

                        sqliteHelper.updateId("infra_structure", "infra_id='" + infra_id + "'", last_infra_id, "infra_id");
                        sqliteHelper.updateId("infra_monitoring", "infra_id='" + infra_id + "'", last_infra_id, "infra_id");

                        if (countInfra > 0) {
                            countInfra = countInfra - 1;
                            tvInfraCount.setText(countInfra + "");
                            dialog.dismiss();
                        }

                    } else {
                        Toast.makeText(Synchronize.this, "" + message, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
            }
        });
    }
    private void sendInfraDataSync(RequestBody body, String localId, String infra_monitoring_id){
        ProgressDialog dialog = ProgressDialog.show(Synchronize.this, "", getString(R.string.please_wait), true);
        APIClient.getRuralClient().create(RuralApi.class).InfraMonitoringSync(body).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString().trim());
                    dialog.dismiss();
                    Log.e("bchjc", "data " + jsonObject.toString());
                    String status = jsonObject.optString("status");
                    String message = jsonObject.optString("message");
                     String last_infra_id = jsonObject.optString("last_infra_monitor_id");

                    if (status.equalsIgnoreCase("1")) {
                        //update flag in tables
                        sqliteHelper.updateId("monitor_infra_image", "infra_monitoring_id='" + infra_monitoring_id + "'", last_infra_id, "infra_monitoring_id");
                        sqliteHelper.updateId("infra_monitoring", "infra_monitoring_id='" + infra_monitoring_id + "'", last_infra_id, "infra_monitoring_id");
//                        sqliteHelper.updateId("infra_monitoring", "infra_monitoring_id='" + infra_monitoring_id + "'", last_infra_id, "infra_id");
                        if (countInfraMonitoring > 0) {
                            countInfraMonitoring = countInfraMonitoring - 1;
                            tvInfraMonitoring_data_count.setText(countInfraMonitoring + "");
                            dialog.dismiss();
                        }

                    } else {
                        Toast.makeText(Synchronize.this, "" + message, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
            }
        });
    }



    //SEND STAKEHOLDER SURVEY DATA SEND TO SERVER

    private void sendSurveyDataOnServer () {
        try {
            //in users table last inserted id is user_id
            //  int ids = sqliteHelper.getLastInsertedLocalID();

            if (CommonClass.isInternetOn(context)) {
                stakeHolderSurveyModelArrayList = sqliteHelper.getSurveyForSyn();
                countStakeholder = stakeHolderSurveyModelArrayList.size();
                if (countStakeholder > 0) {
                    for (int i = 0; i < stakeHolderSurveyModelArrayList.size(); i++) {

                        Gson gson = new Gson();
                        String data = gson.toJson(stakeHolderSurveyModelArrayList.get(i));
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, data);
//                        if (farmerRegistrationPojoArrayList.get(i).getOffline_sync() == 1 && farmerRegistrationPojoArrayList.get(i).getFlag().equals("1")) {
//                            sendEditFramerRegistrationData(body, userId);
//                        } else {
                        sendSurveyData(body, String.valueOf(stakeHolderSurveyModelArrayList.get(i).getLocal_id()));
//                        }
                    }
                } else {
                    Toast.makeText(context, R.string.no_data_pending, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(Synchronize.this, getString(R.string.please_wait), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendSurveyData (RequestBody body, String localId){
        ProgressDialog dialog = ProgressDialog.show(Synchronize.this, "", getString(R.string.please_wait), true);
        APIClient.getRuralClient().create(RuralApi.class).Survey_data_sync(body).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString().trim());
                    dialog.dismiss();
                    Log.e("bchjc", "data " + jsonObject.toString());
                    String status = jsonObject.optString("status");
                    String message = jsonObject.optString("message");
                    String last_survey_id = jsonObject.optString("last_stake_id");

                    if (status.equalsIgnoreCase("1")) {
                        //update flag in tables

                        sqliteHelper.updateId("stakeholder_survey", "local_id='" + localId + "'", last_survey_id, "survey_id");
                        if (countStakeholder > 0) {
                            countStakeholder = countStakeholder - 1;
                            tvSurveyCount.setText(countStakeholder + "");
                            dialog.dismiss();
                        }


                    } else {
                        Toast.makeText(Synchronize.this, "" + message, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
            }
        });
    }


    //SEND RESOURECE DATA SEND TO SERVER

    private void sendResourceMappingDataOnServer () {
        try {
            //in users table last inserted id is user_id
            //  int ids = sqliteHelper.getLastInsertedLocalID();

            if (CommonClass.isInternetOn(context)) {
                resourceMappingPojoArrayList = sqliteHelper.getResourceForSyn(0);
                countResource = resourceMappingPojoArrayList.size();
                if (countResource > 0) {
                    for (int i = 0; i < resourceMappingPojoArrayList.size(); i++) {
                        ArrayList<ResourceMultipleImagePojo> resource_mapping_image=new ArrayList<>();
                        resource_mapping_image=sqliteHelper.getResourceimage(String.valueOf(resourceMappingPojoArrayList.get(i).getResource_mapping_id()));
                        resourceMappingPojoArrayList.get(i).setResource_mapping_image(resource_mapping_image);

                        Gson gson = new Gson();
                        String data = gson.toJson(resourceMappingPojoArrayList.get(i));
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, data);
                        Log.e(TAG, "sendResourceDataOnServer: "+data);

//                        if (farmerRegistrationPojoArrayList.get(i).getOffline_sync() == 1 && farmerRegistrationPojoArrayList.get(i).getFlag().equals("1")) {
//                            sendEditFramerRegistrationData(body, userId);
//                        } else {
                      
                        sendRescourceMappingData(body, String.valueOf(resourceMappingPojoArrayList.get(i).getLocal_id()),String.valueOf(resourceMappingPojoArrayList.get(i).getResource_mapping_id()));
//                        }
                    }

                } else {
                    Toast.makeText(context, R.string.no_data_pending, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(Synchronize.this, getString(R.string.please_wait), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendRescourceMappingData (RequestBody body, String localId, String id){
        ProgressDialog dialog = ProgressDialog.show(Synchronize.this, "", getString(R.string.please_wait), true);
        APIClient.getRuralClient().create(RuralApi.class).Resource_Mapping_data_sync(body).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString().trim());
                    dialog.dismiss();
                    Log.e("bchjc", "data " + jsonObject.toString());
                    String status = jsonObject.optString("status");
                    String message = jsonObject.optString("message");
                    String last_resource_mapping_id = jsonObject.optString("last_resource_mapping_id");



                    if (status.equalsIgnoreCase("1")) {
                        //update flag in tables
                        sqliteHelper.updateResourceImage("monitor_resource", "resource_mapping_id='" + id + "'", last_resource_mapping_id, "resource_mapping_id");
                        sqliteHelper.updateResourceImage("resuorce_mapping_image", "resource_mapping_id='" + id + "'", last_resource_mapping_id, "resource_mapping_id");
                        sqliteHelper.updateId("resource_mapping", "local_id='" + localId + "'", last_resource_mapping_id, "resource_mapping_id");

//                        tvResourceCount.setText("0 Pending Data");
//                        dialog.dismiss();

                        if (countResource > 0) {
                            countResource = countResource - 1;
                            tvResourceCount.setText(countResource + "");
                            dialog.dismiss();
                        }
                    } else {
                        Toast.makeText(Synchronize.this, "" + message, Toast.LENGTH_LONG).show();


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
            }
        });
    }


    //SEND TRAINING DATA SEND TO SERVER

    private void sendTrainingDataOnServer () {
        try {
            //in users table last inserted id is user_id
            //  int ids = sqliteHelper.getLastInsertedLocalID();

            if (CommonClass.isInternetOn(context)) {
                trainingModelArrayList = sqliteHelper.getTrainingForSyn();
                countTraining = trainingModelArrayList.size();
                if (countTraining > 0) {
                    for (int i = 0; i < trainingModelArrayList.size(); i++) {
                        ArrayList<TrainingMultipleImagePojo> training_image=new ArrayList<>();
                        training_image= sqliteHelper.getTrainingMultipleImage(trainingModelArrayList.get(i).getTraining_id());
                        trainingModelArrayList.get(i).setTraining_image_name(training_image);

                        Gson gson = new Gson();
                        String data = gson.toJson(trainingModelArrayList.get(i));
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, data);
//                        if (farmerRegistrationPojoArrayList.get(i).getOffline_sync() == 1 && farmerRegistrationPojoArrayList.get(i).getFlag().equals("1")) {
//                            sendEditFramerRegistrationData(body, userId);
//                        } else {
                        sendTrainingData(body, String.valueOf(trainingModelArrayList.get(i).getLocal_id()),trainingModelArrayList.get(i).getTraining_id());
//                        }
                    }
                } else {
                    Toast.makeText(context, R.string.no_data_pending, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(Synchronize.this, getString(R.string.please_wait), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendTrainingData (RequestBody body, String localId,int id){
        ProgressDialog dialog = ProgressDialog.show(Synchronize.this, "", getString(R.string.please_wait), true);
        APIClient.getRuralClient().create(RuralApi.class).Training_data_sync(body).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString().trim());
                    dialog.dismiss();
                    Log.e("bchjc", "data " + jsonObject.toString());
                    String status = jsonObject.optString("status");
                    String message = jsonObject.optString("message");
                    String last_Training_id = jsonObject.optString("last_training_id");

                    if (status.equalsIgnoreCase("1")) {
                        //update flag in tables

                        sqliteHelper.updateResourceImage("training_image", "training_id='" + id + "'", last_Training_id, "training_id");


                        sqliteHelper.updateId("training", "local_id='" + localId + "'", last_Training_id, "training_id");
                        if (countTraining > 0) {
                            countTraining = countTraining - 1;
                            tvTrainingCount.setText(countTraining + "");
                            dialog.dismiss();
                        }
                    } else {
                        Toast.makeText(Synchronize.this, "" + message, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
            }
        });
    }


    //SEND COMMITTEE MEMBER DATA SEND TO SERVER

    private void sendCommitteeMemberDataOnServer () {
        try {
            //in users table last inserted id is user_id
            //  int ids = sqliteHelper.getLastInsertedLocalID();

            if (CommonClass.isInternetOn(context)) {
                committee_tableArrayList = sqliteHelper.getCommitteeMemberForSyn(0);
                countMember = committee_tableArrayList.size();
                if (countMember > 0) {
                    for (int i = 0; i < committee_tableArrayList.size(); i++) {

                        Gson gson = new Gson();
                        String data = gson.toJson(committee_tableArrayList.get(i));
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, data);
//                        if (farmerRegistrationPojoArrayList.get(i).getOffline_sync() == 1 && farmerRegistrationPojoArrayList.get(i).getFlag().equals("1")) {
//                            sendEditFramerRegistrationData(body, userId);
//                        } else {
                        sendCommitteeMemberData(body, String.valueOf(committee_tableArrayList.get(i).getLocal_id()));
//                        }
                    }
                } else {
                    Toast.makeText(context, R.string.no_data_pending, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(Synchronize.this, getString(R.string.please_wait), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendCommitteeMemberData (RequestBody body, String localId){
        ProgressDialog dialog = ProgressDialog.show(Synchronize.this, "", getString(R.string.please_wait), true);
        APIClient.getRuralClient().create(RuralApi.class).Member_data_sync(body).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString().trim());
                    dialog.dismiss();
                    Log.e("bchjc", "data " + jsonObject.toString());
                    String status = jsonObject.optString("status");
                    String message = jsonObject.optString("message");


                    if (status.equalsIgnoreCase("1")) {
                        String last_member_id = jsonObject.optString("last_member_id");

                        sqliteHelper.updateId("committee_member", "local_id='" + localId + "'", last_member_id, "member_id");

                        if (countMember > 0) {

                            countMember = countMember - 1;
                            tvCommitteeMemberCount.setText(countMember + "");
                            dialog.dismiss();
                        }
                    } else {
                        Toast.makeText(Synchronize.this, "" + message, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
            }
        });
    }

//----------- - - - SEND MONITOR RESOURCE DATA SEND TO SERVER - - - - - - - --------------

    private void sendMonitorResourceDataOnServer () {
        try {

            if (CommonClass.isInternetOn(context)) {
                monitorModelArrayList = sqliteHelper.getMonitorResourceForSyn();
                countMonitoring = monitorModelArrayList.size();
                if (countMonitoring > 0) {
                    for (int i = 0; i < monitorModelArrayList.size(); i++) {
                        ArrayList<MonitorMultipleImagePojo> monitor_resource_image=new ArrayList<>();
                        monitor_resource_image= sqliteHelper.getMoniorMultipleImage(monitorModelArrayList.get(i).getMonitor_resource_id());
                        monitorModelArrayList.get(i).setMonitor_resource_image(monitor_resource_image);
                        Gson gson = new Gson();
                        String data = gson.toJson(monitorModelArrayList.get(i));
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, data);
//
                        if(countResource==0){
                            sendMonitoringResourceData(body, String.valueOf(monitorModelArrayList.get(i).getLocal_id()), monitorModelArrayList.get(i).getMonitor_resource_id());
                        }else {
                            Toast.makeText(context, "Please Sync Resource Data First", Toast.LENGTH_SHORT).show();
                        }

                        }

                } else {
                    Toast.makeText(context, R.string.no_data_pending, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(Synchronize.this, getString(R.string.please_wait), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMonitoringResourceData (RequestBody body, String localId, int id){

        ProgressDialog dialog = ProgressDialog.show(Synchronize.this, "", getString(R.string.please_wait), true);
        APIClient.getRuralClient().create(RuralApi.class).Resource_Monitoring_data_sync(body).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString().trim());
                    dialog.dismiss();
                    Log.e("bchjc", "data " + jsonObject.toString());
                    String status = jsonObject.optString("status");
                    String message = jsonObject.optString("message");
                    String last_resource_monitor_id = jsonObject.optString("last_resource_monitor_id");

                    if (status.equalsIgnoreCase("1")) {
                        //update flag in tables
                        sqliteHelper.updateResourceImage("moniter_resource_image", "monitor_resource_id='" + id + "'", last_resource_monitor_id, "monitor_resource_id");
                        sqliteHelper.updateId("monitor_resource", "local_id='" + localId + "'", last_resource_monitor_id, "monitor_resource_id");

                        if (countMonitoring > 0) {
                            countMonitoring = countMonitoring - 1;
                            tvResourceMonitoringCount.setText(countMonitoring + "");
                            dialog.dismiss();
                        }
//                        tvResourceMonitoringCount.setText("0 Pending Data");
//                        dialog.dismiss();
                    } else {
                        Toast.makeText(Synchronize.this, "" + message, Toast.LENGTH_LONG).show();
                        if(countResource==0){
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
            }
        });
    }


    //SEND VILLAGE PROFILE DATA SEND TO SERVER

    private void sendVillageProfileDataOnServer () {
        try {
            //in users table last inserted id is user_id
            //  int ids = sqliteHelper.getLastInsertedLocalID();

            if (CommonClass.isInternetOn(context)) {
                villageProfileModelArrayList = sqliteHelper.getVillageForSyn();
                countVillageProfile = villageProfileModelArrayList.size();
                if (countVillageProfile > 0) {
                    for (int i = 0; i < villageProfileModelArrayList.size(); i++) {
                        ArrayList<MultipleImagePojo> multipleImagePojos=new ArrayList<>();
                        multipleImagePojos=sqliteHelper.getVimage(String.valueOf(villageProfileModelArrayList.get(i).getVillage_profile_id()));
                        villageProfileModelArrayList.get(i).setVillage_profile_img(multipleImagePojos);

                        ArrayList<Committee_Table> committee_tableArrayList= new ArrayList<>();
                        committee_tableArrayList = sqliteHelper.getCommitteeMemberForSyn(villageProfileModelArrayList.get(i).getVillage_profile_id());
                        villageProfileModelArrayList.get(i).setCommittee_member(committee_tableArrayList);

                        ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList = new ArrayList<>();
                        resourceMappingPojoArrayList = sqliteHelper.getResourceForSyn(villageProfileModelArrayList.get(i).getVillage_profile_id());
                        ArrayList<ResourceMultipleImagePojo> resource_mapping_image=new ArrayList<>();
                        for (int j = 0; j < resourceMappingPojoArrayList.size(); j++) {
                            resource_mapping_image=sqliteHelper.getVillageResourceforSYnc(String.valueOf(resourceMappingPojoArrayList.get(j).getResource_mapping_id()));
                            resourceMappingPojoArrayList.get(j).setResource_mapping_image(resource_mapping_image);
                        }


                        villageProfileModelArrayList.get(i).setResource_mapping(resourceMappingPojoArrayList);

                        Gson gson = new Gson();
                        String data = gson.toJson(villageProfileModelArrayList.get(i));
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, data);

//                      if(countVillageProfile==0){
                             sendVillageProfileData(body, String.valueOf(villageProfileModelArrayList.get(i).getLocal_id()), String.valueOf(villageProfileModelArrayList.get(i).getVillage_profile_id()));
//                        }else {
//                            Toast.makeText(context, "Please Sync Resource Monitoring Data First", Toast.LENGTH_SHORT).show();
//                        }

//                        }

                        }

                } else {
                    Toast.makeText(context, R.string.no_data_pending, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(Synchronize.this, getString(R.string.please_wait), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendVillageProfileData (RequestBody body, String localId,String Id){
        ProgressDialog dialog = ProgressDialog.show(Synchronize.this, "", getString(R.string.please_wait), true);
        APIClient.getRuralClient().create(RuralApi.class).Village_Profile_data_sync(body).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString().trim());
                    dialog.dismiss();
                    Log.e("bchjc", "dataVillage " + jsonObject.toString());
                    String status = jsonObject.optString("status");
                    String message = jsonObject.optString("message");
                    String last_village_id = jsonObject.optString("last_village_id");

                    if (status.equalsIgnoreCase("1")) {
                        //update flag in tables

                        sqliteHelper.updateResourceImage("village_profile_image", "village_profile_id='" + Id + "'", last_village_id, "village_profile_id");
                        sqliteHelper.updateId("village_profile", "local_id='" + localId + "'", last_village_id, "village_profile_id");
                        sqliteHelper.updateId("committee_member", "village_profile_id='" + Id + "'", last_village_id, "village_profile_id");
                        sqliteHelper.updateId("resource_mapping", "village_profile_id='" + Id + "'", last_village_id, "village_profile_id");

                        if (countVillageProfile > 0) {
                            countVillageProfile = countVillageProfile - 1;
                            tvVillageProfileCount.setText(countVillageProfile + "");
                            dialog.dismiss();
                        }
                    } else {
                        Toast.makeText(Synchronize.this, "" + message, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
            }
        });
    }


}