package com.indev.ruraldevelopment.Activity.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.DataDownloadInput;
import com.indev.ruraldevelopment.Activity.restApi.APIClient;
import com.indev.ruraldevelopment.Activity.restApi.RuralApi;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataDownload {
//
    private String[] tables = {"state","district","member_role","resource","block","stakeholder_category","infra_resource","infrasub_resource_type"};
    SharedPrefHelper sharedPrefHelper;

    ExecutorService executor = Executors.newSingleThreadExecutor();
    @SuppressLint("StaticFieldLeak")
    public void getMasterTables(final Activity context) {
        final SqliteHelper sqliteHelper = new SqliteHelper(context);
        //  dialog=new ProgressDialog(context);
        //  dialog = ProgressDialog.show(context, "", "Please Wait...", true);

        sharedPrefHelper = new SharedPrefHelper(context);

        sqliteHelper.openDataBase();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < tables.length; j++) {
                    DataDownloadInput dataDownloadInput = new DataDownloadInput();
                    dataDownloadInput.setTable_name(tables[j]);
                    Gson mGson = new Gson();
                    String data = mGson.toJson(dataDownloadInput);
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    RequestBody body = RequestBody.create(JSON, data);

                    final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
                    Call<JsonArray> call = apiService.download_general(body);
                    final int finalJ = j;
                    call.enqueue(new Callback<JsonArray>() {

                        @Override
                        public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                            try {
                                JsonArray data = response.body();
                                sqliteHelper.dropTable(tables[finalJ]);

                                for (int i = 0; i < data.size(); i++) {
                                    JSONObject singledata = new JSONObject(data.get(i).toString());
                                    Iterator keys = singledata.keys();
                                    ContentValues contentValues = new ContentValues();
                                    while (keys.hasNext()) {
                                        String currentDynamicKey = (String) keys.next();
                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                                    }

                                    sqliteHelper.saveMasterTable(contentValues, tables[finalJ]);

                                }

                                if (tables[finalJ].equals("infrasub_resource_type")) {
                                    sharedPrefHelper.setString("isSplashLoaded", "Yes");
                                    //  Change_Language(context);
                                    Intent intent = new Intent(context, Login.class);
                                    context.startActivity(intent);
                                    context.finish();
                                }
                            } catch (Exception s) {
                                s.printStackTrace();
                            }

                        }
                        @Override
                        public void onFailure(Call<JsonArray> call, Throwable t) {
                            Log.d("", "");
                        }
                    });

                }
            }
        });

    }

}
