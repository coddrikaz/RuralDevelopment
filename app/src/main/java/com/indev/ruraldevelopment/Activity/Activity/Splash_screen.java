package com.indev.ruraldevelopment.Activity.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.R;

public class Splash_screen extends AppCompatActivity {
    SqliteHelper sqliteHelper = null;
    private static final int Spash_pre_length=2000;
    SharedPrefHelper sharedPrefHelper;
    String latitude,longitude;
    private static final int REQUEST=112;
    boolean ret=false;
    String splashLoaded = "No";
    private ProgressDialog dialog;
    Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        sqliteHelper=new SqliteHelper(this);
        sharedPrefHelper=new SharedPrefHelper(Splash_screen.this);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.black));

        //Permission
        String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA
        };
        //Permission
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST);

        }else{
            callNextActivity();
        }
    }


    private boolean hasPermissions(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null){
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void callNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                splashLoaded = sharedPrefHelper.getString("isSplashLoaded", "No");
                String islogin =sharedPrefHelper.getString("isLogin", "");

                if (splashLoaded.equals("No")) {
                    //    if (CommonClass.isInternetOn(context)) {

                    // download data in Database
                    DataDownload dataDownload = new DataDownload();
                    dataDownload.getMasterTables(Splash_screen.this);
  //                  ResourceDataDownload();
//
//                    sqliteHelper.dropTable("state");
//                    sqliteHelper.dropTable("district");
//                    sqliteHelper.dropTable("resource");
//                    sqliteHelper.dropTable("village");
//                    sqliteHelper.dropTable("block");
//                    sqliteHelper.dropTable("stakeholder_category");

                } else if(islogin.equals("yes") && splashLoaded.equals("Yes")) {
                    Intent intent = new Intent(Splash_screen.this, SelectVillage.class);
                    startActivity(intent);
                    finishAffinity();
                } else {
                    Intent intent = new Intent(Splash_screen.this, Login.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, Spash_pre_length);
    }

    //GPS Location Method is used
    private boolean getGeoLocation() {
        GPSLocation mGPS = new GPSLocation(getApplicationContext());
        if(mGPS.canGetLocation){
            mGPS.getLocation();
            latitude= ""+mGPS.getLatitude();
            longitude= ""+mGPS.getLongitude();
            sharedPrefHelper.setString("LAT",latitude);
            sharedPrefHelper.setString("LONG",longitude);
            Log.e("TAG", "run: "+ "Lat"+latitude+"Lon"+longitude);
            ret=true;
        }else{
            ret=false;
        }
        return ret;
    }
    //Permissions Request
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("TAG", "@@@ PERMISSIONS grant");
                    callNextActivity();
                } else {
                    Log.d("TAG", "@@@ PERMISSIONS Denied");
                    Toast.makeText(this, "PERMISSIONS Denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

//    private void ResourceDataDownload() {
//        new AsyncTask<Void, Void, Void>() {
//            @SuppressLint("StaticFieldLeak")
//            @Override
//            protected Void doInBackground(Void... voids) {
//                ResourceMappingPojo personpojo = new ResourceMappingPojo();
//                personpojo.setUser_id(Integer.parseInt(sharedPrefHelper.getString("user_id", "")));
//                Gson gson = new Gson();
//                String data = gson.toJson(personpojo);
//                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//                RequestBody body = RequestBody.create(JSON, data);
//
//                final RuralApi apiService = APIClient.getRuralClient().create(RuralApi.class);
//                Call<JsonArray> call = apiService.Resource_data_download(body);
////                    final int finalJ = j;
//                call.enqueue(new Callback<JsonArray>() {
//                    @Override
//                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
//                        try {
//                            JsonArray data = response.body();
//                            sqliteHelper.dropTable("resource_mapping");
//
//                            if (data.size() > 0) {
//                                for (int i = 0; i < data.size(); i++) {
//                                    JSONObject singledata = new JSONObject(data.get(i).toString());
//                                    Iterator keys = singledata.keys();
//                                    ContentValues contentValues = new ContentValues();
//                                    while (keys.hasNext()) {
//                                        String currentDynamicKey = (String) keys.next();
//                                        contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
//                                    }
//                                    sqliteHelper.saveMasterTable(contentValues, "resource_mapping");
//                                }
//                                //call_MonitoringStatus();
//                            } else {
//                                Intent intent = new Intent(Splash_screen.this, Login.class);
//                                startActivity(intent);
//                                finish();
//                                dialog.dismiss();
//                            }
//
//                        } catch (Exception e) {
//                            Toast.makeText(context, "Something is wrong", Toast.LENGTH_LONG).show();
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<JsonArray> call, Throwable t) {
//                        Log.d("Failure", "" + t.getMessage());
//                    }
//                });
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void unused) {
//                super.onPostExecute(unused);
//            }
//        }.execute();
//    }


//    @Override
//    public void onBackPressed(){
//        super.onBackPressed();
//        Intent i=new Intent(Intent.ACTION_MAIN);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        finish();
//    }
}