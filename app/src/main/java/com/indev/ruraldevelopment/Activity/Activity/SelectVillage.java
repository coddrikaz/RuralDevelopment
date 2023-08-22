package com.indev.ruraldevelopment.Activity.Activity;

import static com.yalantis.ucrop.UCropFragment.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.VillagePojo;
import com.indev.ruraldevelopment.Activity.Model.VillageProfileModel;
import com.indev.ruraldevelopment.Activity.restApi.APIClient;
import com.indev.ruraldevelopment.Activity.restApi.RuralApi;
import com.indev.ruraldevelopment.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectVillage extends AppCompatActivity {
    Spinner spnVillage;
    Button btnSubmit,btnrefresh;
    String mVillage="";
    ArrayList<String> VillageArrayList ;
    HashMap<String, Integer> VillageNameHM;
    String village_id ="";
    String vill_id="";
    String local_id="";
    SqliteHelper sqliteHelper;
    VillagePojo villagePojo;
    SharedPrefHelper sharedPrefHelper;
    ArrayList<VillageProfileModel> villageProfileModelArrayListData;
    int user_id=0;
    ProgressDialog dialog;
    Activity context = this;
    String latitude="";
    String longitude="";
    public static Dialog submit_alert;
    LocationManager locationManager;
    GPSLocation mGPS;
    private LocationRequest locationRequest;
    private static final int REQUEST_CHECK_SETTINGS = 10001;
    FusedLocationProviderClient mFusedLocationClient;
    int PERMISSION_ID = 44;
    Button btnSunb;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_village);
        getSupportActionBar().setTitle("Village List");
        spnVillage = findViewById(R.id.spnVillage);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnrefresh = findViewById(R.id.btnrefresh);
        VillageNameHM = new HashMap<>();
        VillageArrayList = new ArrayList<>();
        villageProfileModelArrayListData = new ArrayList<>();
        sqliteHelper = new SqliteHelper(this);
        villagePojo = new VillagePojo();
        sharedPrefHelper = new SharedPrefHelper(this);
        dialog = new ProgressDialog(this);
        mGPS = new GPSLocation(getApplicationContext());
        locationRequest = LocationRequest.create();


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        getCheck();

        getVillageSpinner();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation()) {
                    if (isLocationEnabled()) {
                        getCheck();


                        String village_idd = String.valueOf(village_id);
                        String hm = String.valueOf(VillageNameHM);

                        Intent intent = new Intent(SelectVillage.this, Main_Menu.class);
                        villageProfileModelArrayListData = sqliteHelper.getVillageProfileDataVillageType(village_id);
                        if (villageProfileModelArrayListData.size() != 0) {
                            vill_id = String.valueOf(villageProfileModelArrayListData.get(0).getVillage_id());
                            local_id = String.valueOf(villageProfileModelArrayListData.get(0).getLocal_id());


                        }
                        if (village_id.equals(vill_id)) {

                            sharedPrefHelper.setString("edit_type", "true");
                            sharedPrefHelper.setString("local_id", local_id);

                        } else {
                            sharedPrefHelper.setString("edit_type", "");

                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        getCheck();
                    }
                }
            }
        });

        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = ProgressDialog.show(context, "", "Please Wait...", true);
                VillageDataDownload();

            }
        });
    }


        private void getCheck (){
            GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API).build();
            googleApiClient.connect();
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval(10000 / 2);

            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            builder.setAlwaysShow(true);
            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            Log.i(TAG, "All location settings are satisfied.");
                            getLastLocation();
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");
                            Toast.makeText(SelectVillage.this, "GPS Required", Toast.LENGTH_SHORT).show();

                            try {
                                // Show the dialog by calling startResolutionForResult(), and check the result
                                // in onActivityResult().
                                status.startResolutionForResult(SelectVillage.this, REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException e) {
                                Log.i(TAG, "PendingIntent unable to execute request.");
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                            break;
                    }
                }
            });
        }

        @SuppressLint("MissingPermission")
        private void getLastLocation () {
            // check if permissions are given
            if (checkPermissions()) {

                // check if location is enabled
                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            latitude= String.valueOf(location.getLatitude());
                            longitude= String.valueOf(location.getLongitude());
                            sharedPrefHelper.setString("LAT", latitude);
                            sharedPrefHelper.setString("LONG", longitude);
                        }
                    }
                });
            } else {
                // if permissions aren't available,
                // request for permissions
                requestPermissions();
            }
        }

        @SuppressLint("MissingPermission")
        private void requestNewLocationData () {

            // Initializing LocationRequest
            // object with appropriate methods
            LocationRequest mLocationRequest = new LocationRequest();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setInterval(5);
            mLocationRequest.setFastestInterval(0);
            mLocationRequest.setNumUpdates(1);

            // setting LocationRequest
            // on FusedLocationClient
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        }


        private LocationCallback mLocationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location mLastLocation = locationResult.getLastLocation();
            }
        };

        private boolean checkPermissions () {
            return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

            // If we want background location
            // on Android 10.0 and higher,
            // use:
            // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
        }

        // method to request for permissions
        private void requestPermissions () {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
        }

        private boolean isLocationEnabled () {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }



    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }

//        ArrayAdapter<String> gender_aArrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,village);
//        gender_aArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnVillage.setAdapter(gender_aArrayAdapter);
//        spnVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                if(position!=0)
//                {
//                    mVillage=spnVillage.getSelectedItem().toString().trim();
//
//
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });




    private void getLiveLocation() {
        if(mGPS.getLocation()!=null) {
            mGPS.getLocation();
              longitude = "" + mGPS.getLongitude();
            sharedPrefHelper.setString("LAT", latitude);
            sharedPrefHelper.setString("LONG", longitude);
            Log.e("TAG", "run: " + "Lat" + latitude + "Lon" + longitude);
        }else{
            mGPS.getLocation();
        }
    }
//    private void getVillageSpinner() {
//
//        VillageArrayList.clear();
//
//        VillageNameHM = sqliteHelper.getAllVillage();
//
//        HashMap<Integer,String > sortedMapAsc = sortByComparator(VillageNameHM);
//        for (int i = 0; i < sortedMapAsc.size(); i++) {
//            VillageArrayList.add(sortedMapAsc.values().toArray()[i].toString().trim());
//        }
//        Collections.sort(VillageArrayList, String.CASE_INSENSITIVE_ORDER);
//
//        VillageArrayList.add(0, "Select Your Name");
//
//        ArrayAdapter book_adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, VillageArrayList);
//        book_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnVillage.setAdapter(book_adapter);
////        if (screen_type.equals("edit_profile")) {
////            st_state = sqliteDatabase.getPSStateSp(editpregnantWomenRegisterTable.getState_id());
////            int pos = state_adapter.getPosition(st_state);
////            sp_state.setSelection(pos);
////        }
//
//
//        spnVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (!spnVillage.getSelectedItem().toString().trim().equalsIgnoreCase("Select Your Name")) {
//                    if (spnVillage.getSelectedItem().toString().trim() != null) {
//                        String subscriber_name = spnVillage.getSelectedItem().toString().trim();
//                        for (int j = 0; j < sortedMapAsc.size(); j++) {
//                            if(sortedMapAsc.values().toArray()[j].toString().trim().equals(subscriber_name)){
//                                village_id=sortedMapAsc.keySet().toArray()[j].toString().trim();
//                                sharedPrefHelper.setString("village_idd",village_id);
//
//                            }
//                        }
//                    }
//                }
//
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
//    private HashMap<Integer, String> sortByComparator(HashMap<Integer, String> subscriberNameHM) {
//        List<Map.Entry<Integer, String>> list = new LinkedList<Map.Entry<Integer, String>>(subscriberNameHM.entrySet());
//
//        // Sorting the list based on values
//        Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
//            public int compare(Map.Entry<Integer, String> o1,
//                               Map.Entry<Integer, String> o2) {
//
//                return o1.getValue().compareTo(o2.getValue());
//
//            }
//        });
//
//        // Maintaining insertion order with the help of LinkedList
//        HashMap<Integer, String> sortedMap = new LinkedHashMap<Integer, String>();
//        for (Map.Entry<Integer, String> entry : list) {
//            sortedMap.put(entry.getKey(), entry.getValue());
//        }
//
//        return sortedMap;
//    }

    private void VillageDataDownload() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {

                String userID= sharedPrefHelper.getString("user_id", "");

                VillagePojo villagePojo = new VillagePojo();
                villagePojo.setUser_id(Integer.parseInt(userID));

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
//                                Toast.makeText(context, "Village Updated", Toast.LENGTH_SHORT).show();
                                ShowSubmitDialog(context, getString(R.string.Village_Update),
                                        getString(R.string.village_update_successfully));

//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
                                dialog.dismiss();
                                getVillageSpinner();

//                                finish();
                                //call_MonitoringStatus();
                            } else {
//                                Intent intent = new Intent(Login.this, SelectVillage.class);
//                                startActivity(intent);
//                                finish();
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "Something is wrong" +e, Toast.LENGTH_LONG).show();
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

    public static void ShowSubmitDialog(Context context, String infoTitle, String message) {
        submit_alert = new Dialog(context);

        submit_alert.setContentView(R.layout.submit_alert);
        submit_alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        WindowManager.LayoutParams params = submit_alert.getWindow().getAttributes();
        params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;

//        TextView tvInfoTitle = (TextView) submit_alert.findViewById(R.id.tv_info_title);
//        TextView tvDescription = (TextView) submit_alert.findViewById(R.id.tv_description);
        Button btnOk = (Button) submit_alert.findViewById(R.id.btnOk);

//        tvInfoTitle.setText(infoTitle);
//        tvDescription.setText(Html.fromHtml("<font color=\"#000000\">" +message+ "</font>"));
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO DO
                submit_alert.dismiss();
//                Intent intent1 = new Intent(context, SelectVillage.class);
////                intent1.putExtra("intAnganId",intAnganId);
//                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                context.startActivity(intent1);
            }
        });

        submit_alert.show();
        submit_alert.setCanceledOnTouchOutside(false);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//            getLiveLocation();
//    }

    private void getVillageSpinner() {

        VillageArrayList.clear();
        VillageNameHM = sqliteHelper.getVillage();
        for (int i = 0; i < VillageNameHM.size(); i++) {
            VillageArrayList.add(VillageNameHM.keySet().toArray()[i].toString().trim());
        }
        Collections.sort(VillageArrayList, String.CASE_INSENSITIVE_ORDER);
        VillageArrayList.add(0, "Select Village");
        //state spinner choose
        ArrayAdapter adapter = new ArrayAdapter(SelectVillage.this, android.R.layout.simple_spinner_item, VillageArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnVillage.setAdapter(adapter);


        spnVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!spnVillage.getSelectedItem().toString().trim().equalsIgnoreCase("Select Village")) {
                    if (spnVillage.getSelectedItem().toString().trim() != null) {
                        village_id = String.valueOf(VillageNameHM.get(spnVillage.getSelectedItem().toString().trim()));
                        sharedPrefHelper.setString("village_id",village_id);


                        int state_id=sqliteHelper.getCloumnName("state_id","village"," where village_id='"+village_id+"'");
                        int district_id=sqliteHelper.getCloumnName("district_id","village"," where village_id='"+village_id+"'");
                        int block_id=sqliteHelper.getCloumnName("block_id","village"," where village_id='"+village_id+"'");
                        sharedPrefHelper.setString("state_id", String.valueOf(state_id));
                        sharedPrefHelper.setString("district_id", String.valueOf(district_id));
                        sharedPrefHelper.setString("block_id", String.valueOf(block_id));


//                        String village_id= String.valueOf(VillageNameHM.get(spnVillage.getSelectedItem().toString().trim()));
//                        Toast.makeText(SelectVillage.this, ""+village_id, Toast.LENGTH_SHORT).show();
                    }
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean checkValidation() {
        if (spnVillage.getSelectedItemPosition() > 0) {
            String itemvalue = String.valueOf(spnVillage.getSelectedItem());
        } else {
            TextView errorTextview = (TextView) spnVillage.getSelectedView();
            errorTextview.setError("Error");
            errorTextview.requestFocus();
            Toast.makeText(this, "Please Select Village", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        // builder.setIcon(R.drawable.ic_dialog_alert);
        builder.setTitle("Alert!");
        builder.setMessage(R.string.are_you_sure_to_want_to_exitapplication);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finishAffinity();
            }
        });


        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
private void OnGPS() {
    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    });
    final AlertDialog alertDialog = builder.create();
    alertDialog.show();
}


}