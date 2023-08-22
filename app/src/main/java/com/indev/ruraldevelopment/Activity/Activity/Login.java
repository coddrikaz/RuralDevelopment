package com.indev.ruraldevelopment.Activity.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.Committee_Table;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMonitoringPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMultipleImagesPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStructureModel;
import com.indev.ruraldevelopment.Activity.Model.LoginPojo;
import com.indev.ruraldevelopment.Activity.Model.MonitorModel;
import com.indev.ruraldevelopment.Activity.Model.MonitorMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMappingPojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.StakeHolderSurveyModel;
import com.indev.ruraldevelopment.Activity.Model.TrainingModel;
import com.indev.ruraldevelopment.Activity.Model.TrainingMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.VillagePojo;
import com.indev.ruraldevelopment.Activity.Model.VillageProfileModel;
import com.indev.ruraldevelopment.Activity.restApi.APIClient;
import com.indev.ruraldevelopment.Activity.restApi.RuralApi;
import com.indev.ruraldevelopment.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText etPassword, etEmailLog;
    Button btnLogIn;
    private ProgressDialog dialog;
    Activity context = this;
    SharedPrefHelper sharedPrefHelper;
    SqliteHelper sqliteHelper;
    String user_id="";
    String resource_mapping_id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        btnLogIn = findViewById(R.id.btnLogIn);
        etPassword = findViewById(R.id.etpassword);
        etEmailLog = findViewById(R.id.etEmailLog);
        sharedPrefHelper = new SharedPrefHelper(this);
        sqliteHelper = new SqliteHelper(Login.this);

        CheckBox checkPass = (CheckBox) findViewById(R.id.checkBox);

        checkPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    callLoginApi();

//
                }
            }
        });


    }

    @SuppressLint("StaticFieldLeak")
    private void ResourceDataDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                ResourceMappingPojo personpojo = new ResourceMappingPojo();
        //        String userID= sharedPrefHelper.getString("user_id", "");
                personpojo.setUser_id(Integer.parseInt(user_id));
                Gson gson = new Gson();
                String data = gson.toJson(personpojo);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.Resource_data_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("resource_mapping");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "resource_mapping");
                                }
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                dialog.dismiss();
//                                finish();
                                //call_MonitoringStatus();
                            } else {
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                finish();
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }

    private void VillageDataDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                VillagePojo villagePojo = new VillagePojo();
                //        String userID= sharedPrefHelper.getString("user_id", "");
                villagePojo.setUser_id(Integer.parseInt(user_id));
                Gson gson = new Gson();
                String data = gson.toJson(villagePojo);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.Village_data_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("village");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "village");
                                }
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                dialog.dismiss();
//                                finish();
                                //call_MonitoringStatus();
                            } else {
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                finish();
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }

    private void CommitteeDataDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                Committee_Table committee_table = new Committee_Table();
                //        String userID= sharedPrefHelper.getString("user_id", "");
                committee_table.setUser_id(Integer.parseInt(user_id));
                Gson gson = new Gson();
                String data = gson.toJson(committee_table);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.committee_data_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("committee_member");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "committee_member");
                                }
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                dialog.dismiss();
                                //call_MonitoringStatus();
                            } else {
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                finish();
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }


    private void TrainingDataDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                TrainingModel trainingModel = new TrainingModel();
                //        String userID= sharedPrefHelper.getString("user_id", "");
                trainingModel.setUser_id(Integer.parseInt(user_id));
                Gson gson = new Gson();
                String data = gson.toJson(trainingModel);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.training_data_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("training");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "training");
                                }
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                dialog.dismiss();
                                //call_MonitoringStatus();
                            } else {
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                finish();
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }

    private void SurveyDataDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                StakeHolderSurveyModel stakeHolderSurveyModel = new StakeHolderSurveyModel();
                //        String userID= sharedPrefHelper.getString("user_id", "");
                stakeHolderSurveyModel.setUser_id(Integer.parseInt(user_id));
                Gson gson = new Gson();
                String data = gson.toJson(stakeHolderSurveyModel);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.survey_data_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("stakeholder_survey");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "stakeholder_survey");
                                }
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                dialog.dismiss();
                                //call_MonitoringStatus();
                            } else {
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                finish();
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }


    private void InfraDataDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                InfraStructureModel infraStructureModel = new InfraStructureModel();
                //        String userID= sharedPrefHelper.getString("user_id", "");
                infraStructureModel.setUser_id(Integer.parseInt(user_id));
                Gson gson = new Gson();
                String data = gson.toJson(infraStructureModel);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.infra_data_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("infra_structure");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }

                                    sqliteHelper.saveMasterTable(contentValues, "infra_structure");

                                }
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                dialog.dismiss();
                                //call_MonitoringStatus();
                            } else {
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                finish();
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }

    private void MonitorDataDownload() {

        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                MonitorModel monitorModel = new MonitorModel();
                //        String userID= sharedPrefHelper.getString("user_id", "");

                monitorModel.setUser_id(Integer.parseInt(user_id));

                Gson gson = new Gson();
                String data = gson.toJson(monitorModel);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.monitor_data_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("monitor_resource");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "monitor_resource");
                                }
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                dialog.dismiss();
                                //call_MonitoringStatus();
                            } else {
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                finish();
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();

    }

    private void VillageProfileDataDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                VillageProfileModel villageProfileModel = new VillageProfileModel();
                //        String userID= sharedPrefHelper.getString("user_id", "");
                villageProfileModel.setUser_id(Integer.parseInt(user_id));
                Gson gson = new Gson();
                String data = gson.toJson(villageProfileModel);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.village_profile_data_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("village_profile");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "village_profile");
                                }
//                                dialog.dismiss();

//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                finish();
                                //call_MonitoringStatus();
                            } else {
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                finish();
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }


    private void ResourceImagesDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                ResourceMultipleImagePojo resourceMultipleImagePojo = new ResourceMultipleImagePojo();
                //        String userID= sharedPrefHelper.getString("user_id", "");
                resourceMultipleImagePojo.setUser_id(Integer.parseInt(user_id));
                Gson gson = new Gson();
                String data = gson.toJson(resourceMultipleImagePojo);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.Resource_images_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("resuorce_mapping_image");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "resuorce_mapping_image");
                                }
//                                dialog.dismiss();
                                //call_MonitoringStatus();
                            } else {
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }

    private void TrainingImagesDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                TrainingMultipleImagePojo trainingMultipleImagePojo = new TrainingMultipleImagePojo();
                //        String userID= sharedPrefHelper.getString("user_id", "");
                trainingMultipleImagePojo.setUser_id(Integer.parseInt(user_id));
                Gson gson = new Gson();
                String data = gson.toJson(trainingMultipleImagePojo);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.Training_images_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("training_image");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "training_image");
                                }
//                                dialog.dismiss();
                                //call_MonitoringStatus();
                            } else {
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }

    private void InfraMonitorDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                InfraStruMonitoringPojo infraStruMonitoringPojo = new InfraStruMonitoringPojo();
                //        String userID= sharedPrefHelper.getString("user_id", "");
                infraStruMonitoringPojo.setUser_id(user_id);
                Gson gson = new Gson();
                String data = gson.toJson(infraStruMonitoringPojo);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.Infra_Monitor_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("infra_monitoring");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "infra_monitoring");
                                }
//                                dialog.dismiss();
                                //call_MonitoringStatus();
                            } else {
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }

    private void InfraMonitorImageDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                InfraStruMultipleImagesPojo infraStruMultipleImagesPojo = new InfraStruMultipleImagesPojo();
                //        String userID= sharedPrefHelper.getString("user_id", "");
                infraStruMultipleImagesPojo.setUser_id(user_id);
                Gson gson = new Gson();
                String data = gson.toJson(infraStruMultipleImagesPojo);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.Infra_Monitor_images_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("monitor_infra_image");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "monitor_infra_image");
                                }
//                                dialog.dismiss();
                                //call_MonitoringStatus();
                            } else {
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }


    private void MonitorResourceImagesDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                MonitorMultipleImagePojo monitorMultipleImagePojo = new MonitorMultipleImagePojo();
                //        String userID= sharedPrefHelper.getString("user_id", "");
                monitorMultipleImagePojo.setUser_id(Integer.parseInt(user_id));
                Gson gson = new Gson();
                String data = gson.toJson(monitorMultipleImagePojo);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.Monitor_Resource_images_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("moniter_resource_image");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "moniter_resource_image");
                                }
//                                dialog.dismiss();
                                //call_MonitoringStatus();
                            } else {
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }



    private void VillageImagesDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                MultipleImagePojo multipleImagePojo = new MultipleImagePojo();
                //        String userID= sharedPrefHelper.getString("user_id", "");
                multipleImagePojo.setUser_id(Integer.parseInt(user_id));
                Gson gson = new Gson();
                String data = gson.toJson(multipleImagePojo);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, data);

                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                Call<JsonArray> call = apiService.Village_images_download(body);
//                    final int finalJ = j;
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        try {
                            JsonArray data = response.body();
                            sqliteHelper.dropTable("village_profile_image");

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }
                                    sqliteHelper.saveMasterTable(contentValues, "village_profile_image");
                                }
                                Intent intent = new Intent(Login.this, SelectVillage.class);
                                startActivity(intent);
                                dialog.dismiss();
                                //call_MonitoringStatus();
                            } else {
                                Intent intent = new Intent(Login.this, SelectVillage.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("Failure", "" + t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute();
    }


    private void callLoginApi() {
        dialog = ProgressDialog.show(context, "", getString(R.string.please_wait), true);
        LoginPojo loginn = new LoginPojo();

        loginn.setUser_name(etEmailLog.getText().toString().trim());
        loginn.setPassword(etPassword.getText().toString().trim());
        sharedPrefHelper.setString("setPassword", etPassword.getText().toString().trim());

        Gson gson = new Gson();
        String data = gson.toJson(loginn);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, data);
        APIClient.getRuralClient().create(RuralApi.class).LoginApi(body).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString("message");
                    String user_name = jsonObject.getString("user_name");
                    user_id = jsonObject.getString("user_id");
                    sharedPrefHelper.setString("user_id",user_id);
                    sharedPrefHelper.setString("user_name",user_name);
                    Log.e("TAG", "onResponse: " + jsonObject.toString());

                    if (status.equals("1")) {
                        sharedPrefHelper.setString("isLogin", "yes");
                        ResourceDataDownload();
                        VillageDataDownload();
                        CommitteeDataDownload();
                        TrainingDataDownload();
                        SurveyDataDownload();
                        InfraDataDownload();
                        MonitorDataDownload();
                        VillageProfileDataDownload();
                        ResourceImagesDownload();
                        MonitorResourceImagesDownload();
                        InfraMonitorDownload();
                        InfraMonitorImageDownload();
                        TrainingImagesDownload();
                        VillageImagesDownload();

                    } else {
                        Toast.makeText(Login.this, "Invalid id", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }

    private boolean checkValidation() {

        if (etEmailLog.getText().toString().trim().length() == 0) {
            etEmailLog.setError("Please enter username!");
            etEmailLog.requestFocus();
            return false;
        }


        if (etPassword.getText().toString().trim().length() == 0) {
            etPassword.setError("Please enter password!");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }

}

