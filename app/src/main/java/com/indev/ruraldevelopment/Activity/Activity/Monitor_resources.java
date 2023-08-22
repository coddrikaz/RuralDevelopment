package com.indev.ruraldevelopment.Activity.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.MonitorModel;
import com.indev.ruraldevelopment.Activity.Model.MonitorMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Utils.CommonClass;
import com.indev.ruraldevelopment.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class Monitor_resources extends AppCompatActivity {
    private static final int REQUEST_IMAGE = 1888;
    Button btnSubmit;
    String resource_type;
    int mYear,mMonth,mDay,year,month,day;
    DatePickerDialog datePickerDialog;
    EditText  etResource, etDescription,etDate;
    TextView tvResourcee,re_name;
    TextView tv_village;
    String base64="";
    int count = 1;
    String img1 = "", img2 = "", img3 = "",sImage="";
    String[] monitoringImage;
    ImageView image,iv_camera;
    SqliteHelper sqliteHelper;
    MonitorModel monitorModel;
    MonitorMultipleImagePojo monitorMultipleImagePojo;
    String resource_name="";
    RadioGroup rg_near_structure,rg_village,rg_damaged,rg_near_the_structure;
    RadioButton rv_yes,rv_No,rv_village,rv_Village,rv_damaged,rv_damaged_i,rv_near_the_structure,rv_near_structure;
    String str_dmg,str_near,str_structure,resource_mapping_name;
    String str_vill;
    String reourceType="";
    ImageView iv_camera1, iv_camera2, iv_camera3;
    double appVersion = 1.01;



    SharedPrefHelper sharedPrefHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_resources);
        setTitle("Monitor Resouce");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getid();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            resource_type =bundle.getString("resorce_type", "");
            resource_mapping_name =bundle.getString("resource_mapping_name", "");

//
        }



        sqliteHelper = new SqliteHelper(this);
        sharedPrefHelper=new SharedPrefHelper(this);
        resource_name= sharedPrefHelper.getString("name","");
        resource_type=sharedPrefHelper.getString("type_id","");
        tv_village.setText(sharedPrefHelper.getString("village_id", ""));
        String village=sqliteHelper.getColumnName("village_name","village"," where village_id='"+sharedPrefHelper.getString("village_id", "")+"'");
        tv_village.setText(village);

        String resourceType=sqliteHelper.getColumnName("resource_name","resource"," where resource_id='"+resource_type+"'");

//        reourceType= sharedPrefHelper.getString("resourceType","");
        tvResourcee.setText(resourceType);
        re_name.setText(resource_name);

        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onProfileImageClick();
//                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cInt, CAMERA_REQUEST);


            }
        });

        rg_village.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rv_village:
                        str_vill = "Yes";
                        break;
                    case R.id.rv_noVillage:
                        str_vill = "No";
                        break;


                }
            }
        });
        rg_near_the_structure .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rv_yes:
                        str_near = "Yes";
                        break;
                    case R.id.rv_No:
                        str_near = "No";
                        break;


                }
            }
        });

        rg_damaged.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rv_damaged:
                        str_dmg = "Yes";
                        break;
                    case R.id.rv_damaged_i:
                        str_dmg = "No";
                        break;


                }
            }
        });
        rg_near_structure.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rv_near_structure:
                        str_structure = "Yes";
                        break;
                    case R.id.rv_near_the_structure:
                        str_structure = "No";
                        break;


                }
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDate.setError(null);
                etDate.clearFocus();
                mYear = year;
                mMonth = month;
                mDay = day;

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR); // current year
                mMonth = c.get(Calendar.MONTH); // current month
                mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(Monitor_resources.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                String nd = "" + dayOfMonth;
                                String nm = "" + monthOfYear ;
                                if ( dayOfMonth < 10 ){
                                    nd = "0"+ dayOfMonth;
                                }


                                if ( (monthOfYear + 1) < 10){
                                    nm = "0"+ ( monthOfYear +1 ) ;
                                }else {
                                    nm = ""+ ( monthOfYear + 1) ;
                                }
                                Log.d("testValue" , String.valueOf( monthOfYear ));

                                // for button text
                                etDate.setText( (year + "-" +  nm + "-" + nd));

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ff173e6d"));
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validation()) {

                    monitoringImage = new String[]{
                            img1, img2, img3};
                    monitorModel.setMonitor_resource_id(Integer.parseInt(CommonClass.getUUID()));
                    monitorModel.setDate_of_monitor(etDate.getText().toString().trim());
//                    monitorModel.setMonitor_resource_id(Integer.parseInt(re_name.getText().toString().trim()));
                    monitorModel.setDescription(etDescription.getText().toString().trim());
                    monitorModel.setFarming_near_structure(str_vill);
//                    monitorModel.setStructure_funtional(str_dmg);
//                    monitorModel.setDamaged_structure(str_structure);
                    monitorModel.setResource_id(Integer.parseInt(resource_type));
//                    monitorModel.setJubliant_visibility_board_near_structure(str_near);
                    monitorModel.setApp_version(String.valueOf(appVersion));
                    monitorModel.setResource_mapping_id(Integer.parseInt(sharedPrefHelper.getString("resource_mapping_id","")));
                    monitorModel.setUser_id(Integer.parseInt(sharedPrefHelper.getString("user_id","")));
                    monitorModel.setVillage_id(Integer.parseInt(sharedPrefHelper.getString("village_id", "")));
                    monitorModel.setState_id(Integer.parseInt(sharedPrefHelper.getString("state_id","")));
                    monitorModel.setDistrict_id(Integer.parseInt(sharedPrefHelper.getString("district_id","")));
                    monitorModel.setBlock_id(Integer.parseInt(sharedPrefHelper.getString("block_id","")));
                    monitorModel.setLongitude(sharedPrefHelper.getString("LONG", ""));
                    monitorModel.setLatitude(sharedPrefHelper.getString("LAT", ""));
                    monitorModel.setImage(base64);
                    monitorModel.setStructure_functional(str_near);
                    monitorModel.setDamaged_structure(str_dmg);
                    monitorModel.setJubliant_visibility_board_near_structure(str_structure);
                    sqliteHelper.saveHousehold(monitorModel);
                    for (int i = 0; i < 3; i++) {
                        if (!monitoringImage[i].equals("")) {
                            monitorMultipleImagePojo.setMonitor_resource_id(monitorModel.getMonitor_resource_id());
                            monitorMultipleImagePojo.setMonitor_resource_image(monitoringImage[i]);
                            monitorMultipleImagePojo.setUser_id(Integer.parseInt(sharedPrefHelper.getString("user_id","")));
                            sqliteHelper.saveHousehold(monitorMultipleImagePojo);
                        }
                    }

                    Intent intent = new Intent(Monitor_resources.this, List_of_monitor_resources.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

            }
        });

    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//            byte[] bytes = stream.toByteArray();
//            base64 = encodeTobase64(photo);
//            if (count == 1) {
//                iv_camera1.setImageBitmap(photo);
//                img1 = encodeTobase64(photo);
//            }
//            if (count == 2) {
//                iv_camera2.setImageBitmap(photo);
//                img2 = encodeTobase64(photo);
//            }
//            if (count == 3) {
//                iv_camera3.setImageBitmap(photo);
//                img3 = encodeTobase64(photo);
//            }
//            count++;
//        }
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private String encodeTobase64(Bitmap image) {
        ByteArrayOutputStream byteArrayOS = null;
        try {
            System.gc();
            byteArrayOS = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOS);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            image.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOS);
        }
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.NO_WRAP);
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                // app icon in action bar clicked; go home
                return true;
//            case R.id.home_icon:
//                Intent intent1 = new Intent(this, Login.class);
//                startActivity(intent1);
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getid() {
        monitorMultipleImagePojo= new MonitorMultipleImagePojo();
        iv_camera1 = findViewById(R.id.iv_camera1);
        iv_camera2 = findViewById(R.id.iv_camera2);
        iv_camera3 = findViewById(R.id.iv_camera3);
        iv_camera = findViewById(R.id.iv_camera);
        tv_village = findViewById(R.id.tv_village);
        tvResourcee = findViewById(R.id.tvResourcee);
        etDescription = findViewById(R.id.etDescription);
        btnSubmit = findViewById(R.id.btnSubmit);
        etDate = findViewById(R.id.etDate);
//      etResourceStatus = findViewById(R.id.etResourceStatus);
        monitorModel = new MonitorModel();
        rv_Village = findViewById(R.id.rv_noVillage);
        rv_village = findViewById(R.id.rv_village);
        rv_damaged = findViewById(R.id.rv_damaged);
        rv_damaged_i = findViewById(R.id.rv_damaged_i);
        rv_near_structure = findViewById(R.id.rv_near_structure);
        rv_near_the_structure = findViewById(R.id.rv_near_the_structure);
        rv_yes = findViewById(R.id.rv_yes);
        rv_No = findViewById(R.id.rv_No);
        rg_village = findViewById(R.id.rg_village);
        rg_near_structure = findViewById(R.id.rg_near_structure);
        rg_damaged = findViewById(R.id.rg_damaged);
        rg_near_the_structure = findViewById(R.id.rg_near_the_structure);
        re_name = findViewById(R.id.re_name);

        image = findViewById(R.id.image);
    }
    private boolean validation() {

        if (etDate.getText().toString().trim().length() == 0) {
            etDate.setError("Please enter date!");
            etDate.requestFocus();
            return false;
        }
        if (re_name.getText().toString().trim().length() == 0) {
            re_name.setError("Please enter Remark!");
            re_name.requestFocus();
            return false;
        }

//
        if(rv_yes.isChecked() || rv_No.isChecked())
        {
        }else{
            Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
            return false;
        }

//        if(rv_village.isChecked() || rv_noVillage.isChecked())
//        {
//        }else{
//            Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
//            return false;
//        }

        if(rv_damaged.isChecked() || rv_damaged_i.isChecked())
        {
        }else{
            Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(rv_near_the_structure.isChecked() || rv_near_structure.isChecked())
        {
        }else{
            Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etDescription.getText().toString().trim().length() == 0) {
            etDescription.setError("Please enter designation!");
            etDescription.requestFocus();
            return false;
        }


//        if (base64.equals("")) {
//            Toast.makeText(this, "Please click some pictures also.", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        if (etDescription.getText().toString().trim().length() == 0) {
            etDescription.setError("Please enter Remark!");
            etDescription.requestFocus();
            return false;
        }

        if (base64.equals("")) {
            Toast.makeText(this, "Please click some Pictures also.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    void onProfileImageClick() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            ImagePicker.with(Monitor_resources.this)
                                    .cameraOnly()
                                    .crop()
                                    .compress(1024)
                                    .maxResultSize(1080,1080)//User can only select image from Gallery
                                    .start();
//                            showImagePickerOptions();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

//            @Override
//            public void onChooseGallerySelected() {
//                launchGalleryIntent();
//            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(Monitor_resources.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(Monitor_resources.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                Uri uri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    // compress Bitmap
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    // Initialize byte array
                    byte[] bytes = stream.toByteArray();
                    // get base64 encoded string
                    sImage = Base64.encodeToString(bytes, Base64.DEFAULT);
                    byte[] bytess = Base64.decode(sImage, Base64.DEFAULT);
                    // Initialize bitmap
                    Bitmap bitmapp = BitmapFactory.decodeByteArray(bytess, 0, bytes.length);
                    // set bitmap on imageView
                    base64 = encodeTobase64(bitmapp);
                    if (count == 1) {
                        iv_camera1.setImageBitmap(bitmapp);
                        img1 = base64;
                    }
                    if (count == 2) {
                        iv_camera2.setImageBitmap(bitmapp);
                        img2 = base64;

                    }
                    if (count == 3) {
                        iv_camera3.setImageBitmap(bitmapp);
                        img3 = base64;
                    }
                    if (count == 3) {
//                        binding.ivCamera3.setClickable(false);
                        Toast.makeText(Monitor_resources.this, "You Can Capture Only Three Image", Toast.LENGTH_SHORT).show();
                    }
                    count++;

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }



    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Monitor_resources.this);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


}