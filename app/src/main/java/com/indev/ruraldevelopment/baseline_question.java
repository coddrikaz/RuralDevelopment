package com.indev.ruraldevelopment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.indev.ruraldevelopment.Activity.Activity.ImagePickerActivity;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMonitoringPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMultipleImagesPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStructureModel;
import com.indev.ruraldevelopment.Activity.Utils.CommonClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class baseline_question extends AppCompatActivity {
    TextView tv_village,etStartDate,tv_Resource_Name,tv_infraName,tv_SubResource_Name;
    String resource_name="";
    RadioGroup rg_near_structure,rg_group6,rg_village,rg_damaged,rg_near_the_structure,rg_group_average_shortfall,rg_group_teaching_through,rg_TLM,rg_group_education,rg_group_government,rg_group_teaching,rg_group_infrastructure,rg_group_digital,rg_group_e_muskan,rg_group_e_muskan_being,rg_group2,rg_group4,rg_group5;
    RadioButton rv_yes,rv_No,rv_village,rv_Village,rv_damaged,rv_damaged_i,rv_near_the_structure,rv_near_structure;
    RadioButton rg_button_average_shortfall_other,rg_button_average_shortfall_no,rg_very_effective,rg_effective,rg_no_effective,rg_button_average_shortfall_yes,rg_tlm_yes,rg_tlm_no,rg_group6_yes,rg_group6_no,rg_group5_yes,rg_group5_no,rg_school_undertaken_no,rg_school_undertaken_yes;
    Button btnSubmit;
    String str_dmg,str_near,str_structureQi3,str_structureEffectiv,str_structureYes,str_structureshortfall_yes,str_structureEmuskanQ4,str_structureEmuskanQ5,str_structureEmuskanQ6,str_structureEmuskanQ7,str_structureEmuskanQ8,str_structureEmuskanQ9,str_structureEmuskanQ10,str_structureEmuskanQ11,str_structureEmuskanQ12;

    String str_structureEmuskanQ13,str_structureEmuskanQ14;
    LinearLayout ll_quesion,ll_quesionEmuskan,ll_quesion_new;


    ImageView iv_camera;
    EditText etDescription;
    String[] image;
    String base64="",sImage="";
    int count = 1;
    double appVersion = 1.01;

    private static final int REQUEST_IMAGE = 1888;
    String img1 = "", img2 = "", img3 = "";
    String[] monitoringImage;
    ImageView iv_camera1, iv_camera2, iv_camera3;
    int mYear,mMonth,mDay,year,month,day;
    DatePickerDialog datePickerDialog;
    SharedPrefHelper sharedPrefHelper;
    com.indev.ruraldevelopment.Activity.Model.InfraStruMonitoringPojo InfraStruMonitoringPojo;
    SqliteHelper sqliteHelper;
    ArrayList<String> resourceArrayList;
    HashMap<String, Integer> resourceNameHM;
    InfraStruMultipleImagesPojo infraStruMultipleImagesPojo;
    String resource_id = "",SubResource_id="";
    int district_level=0;
    String infra_id="",infra_name="",resource_iddd="",Subresource_Type_name="";

    ArrayList<InfraStructureModel> infraStructureModelArrayList;

    String infra_iddd,Subresource_name,SubResource_iddd,getRg_value5,structure_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseline_question);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Baseline");
        initila();

//        String ll=sharedPrefHelper.getString("SubResource","");
//        if (sharedPrefHelper.getString("SubResource","").equalsIgnoreCase("E-muskaan")){
//            ll_quesion.setVisibility(View.GONE);
//            ll_quesionEmuskan.setVisibility(View.VISIBLE);
//        }else {
//            ll_quesion.setVisibility(View.GONE);
//            ll_quesionEmuskan.setVisibility(View.VISIBLE);
//        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            infra_iddd = bundle.getString("infra_id", "");
            resource_id = bundle.getString("resource_id", "");
            infra_name = bundle.getString("infra_name", "");
            resource_name = bundle.getString("resource_name", "");
            Subresource_name = bundle.getString("Subresource_Type_name", "");
            SubResource_iddd = bundle.getString("Subresource_id", "");

        }
        resource_id=sharedPrefHelper.getString("resource_id","");
        SubResource_id=sharedPrefHelper.getString("SubResource_id","");

        tv_SubResource_Name.setText(Subresource_name);
        tv_Resource_Name.setText(resource_name);
        tv_infraName.setText(infra_name);
        AllRadioButton();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdfsss = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(calendar.getTime());
        String currentDateddd = sdfsss.format(calendar.getTime());
        etStartDate.setText(currentDate);
        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < 4) {
                    onProfileImageClick();
                }
            }
        });


        resource_name= sharedPrefHelper.getString("name","");
        tv_village.setText(sharedPrefHelper.getString("village_id", ""));
        String village=sqliteHelper.getColumnName("village_name","village"," where village_id='"+sharedPrefHelper.getString("village_id", "")+"'");
        tv_village.setText(village);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validation()) {

                    image = new String[]{
                            img1, img2, img3};
                    InfraStruMonitoringPojo.setSubresource_id(SubResource_iddd);
                    InfraStruMonitoringPojo.setVillage_id(tv_village.getText().toString().trim());
                    InfraStruMonitoringPojo.setDate_of_monitoring(currentDateddd);
                    InfraStruMonitoringPojo.setResource_id(resource_id);
//                    InfraStruMonitoringPojo.setDate_of_monitoring(etStartDate.getText().toString().trim());
                    InfraStruMonitoringPojo.setDescription(etDescription.getText().toString().trim());
                    InfraStruMonitoringPojo.setIs_structure_functional(str_near);
                    InfraStruMonitoringPojo.setIs_structure_damaged(str_dmg);
                    InfraStruMonitoringPojo.setVisibility_of_jubilant_brand(str_structureQi3);
                    InfraStruMonitoringPojo.setApp_version(String.valueOf(appVersion));
                    InfraStruMonitoringPojo.setUser_id(sharedPrefHelper.getString("user_id",""));
                    InfraStruMonitoringPojo.setVillage_id(sharedPrefHelper.getString("village_id", ""));
                    InfraStruMonitoringPojo.setState_id(sharedPrefHelper.getString("state_id",""));
                    InfraStruMonitoringPojo.setDistrict_id(sharedPrefHelper.getString("district_id",""));
                    InfraStruMonitoringPojo.setBlock_id(sharedPrefHelper.getString("block_id",""));
                    InfraStruMonitoringPojo.setLongitude(sharedPrefHelper.getString("LONG", ""));
                    InfraStruMonitoringPojo.setLatitude(sharedPrefHelper.getString("LAT", ""));
                    InfraStruMonitoringPojo.setInfra_id(infra_iddd);
                    InfraStruMonitoringPojo.setAvg_shortfall_attendence(str_structureshortfall_yes);
                    InfraStruMonitoringPojo.setEffective_teaching_through_chalk_board(str_structureEffectiv);
                    InfraStruMonitoringPojo.setTlm_method_teaching(str_structureYes);
                    InfraStruMonitoringPojo.setInfra_structure_name(sharedPrefHelper.getString("infra_name",""));
                    String uuid = CommonClass.getUUID();
                    InfraStruMonitoringPojo.setInfra_monitoring_id(uuid);
                    InfraStruMonitoringPojo.setIs_there_science_lab(str_structureEmuskanQ14);
                    InfraStruMonitoringPojo.setDigital_method_teaching_increase_attendance(str_structureEmuskanQ4);
                    InfraStruMonitoringPojo.setEdu_method_ingovt_need_pedagogy(str_structureEmuskanQ5);
                    InfraStruMonitoringPojo.setDigital_method_teaching(str_structureEmuskanQ6);
                    InfraStruMonitoringPojo.setSupport_from_govt_econtent_it_infrastruc(str_structureEmuskanQ7);
                    InfraStruMonitoringPojo.setDigital_add_help_stud(str_structureEmuskanQ8);
                    InfraStruMonitoringPojo.setInstallation_emuskaan(str_structureEmuskanQ9);
                    InfraStruMonitoringPojo.setIs_emuskan_being(str_structureEmuskanQ10);
                    InfraStruMonitoringPojo.setIs_there_installed_library(str_structureEmuskanQ11);
                    InfraStruMonitoringPojo.setIs_school_undertaken(str_structureEmuskanQ12);
                    InfraStruMonitoringPojo.setIs_there_a_kitchen_garden(str_structureEmuskanQ13);
                    InfraStruMonitoringPojo.setBaseline("baseline");
                    sqliteHelper.InfraStructureMonitoring(InfraStruMonitoringPojo);

                    for (int i = 0; i < 3; i++) {
                        if (!image[i].equals("")) {
                            infraStruMultipleImagesPojo.setMonitor_image_id(uuid);
                            infraStruMultipleImagesPojo.setInfra_monitoring_img(image[i]);
                            infraStruMultipleImagesPojo.setUser_id(String.valueOf(sharedPrefHelper.getString("user_id", "")));
                            sqliteHelper.infrasavemonitoringimages(infraStruMultipleImagesPojo);
                        }
                    }
                    Intent intent = new Intent(baseline_question.this, BaselineListing.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("infra_idddd",infra_iddd);
                    intent.putExtra("Subresource_id",SubResource_iddd);
                    intent.putExtra("Subresource_Type_name",Subresource_name);
                    intent.putExtra("resource_id",resource_id);
                    intent.putExtra("resource_name",resource_name);
                    sharedPrefHelper.setString("village_id",sharedPrefHelper.getString("village_id", ""));
                    startActivity(intent);
                }
            }
        });

    }




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

    void onProfileImageClick() {
//        Dexter.withActivity(this)
//                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        if (report.areAllPermissionsGranted()) {
//                            ImagePicker.with(AddInfraStructureMonitoring.this)
//                                    .cameraOnly()
//                                    .crop()
//                                    .compress(1024)
//                                    .maxResultSize(1080,1080)//User can only select image from Gallery
//                                    .start();
//
////                            showImagePickerOptions();
//                        }
//
//                        if (report.isAnyPermissionPermanentlyDenied()) {
//                            showSettingsDialog();
//                        }
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                }).check();

//        if (report.areAllPermissionsGranted()) {
        ImagePicker.with(baseline_question.this)
                .cameraOnly()
                .crop()
                .compress(1024)
                .maxResultSize(1080,1080)//User can only select image from Gallery
                .start();

//                            showImagePickerOptions();
//        }

    }


    private void launchCameraIntent() {
        Intent intent = new Intent(baseline_question.this, ImagePickerActivity.class);
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
        Intent intent = new Intent(baseline_question.this, ImagePickerActivity.class);
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
                        Toast.makeText(baseline_question.this, "You Can Capture Only Three Image", Toast.LENGTH_SHORT).show();
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
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(baseline_question.this);
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




    private void AllRadioButton() {



        rg_group_average_shortfall.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_button_average_shortfall_yes:
                        str_structureshortfall_yes = "5%";
                        break;
                    case R.id.rg_button_average_shortfall_no:
                        str_structureshortfall_yes = "5-15%";
                        break;
                    case R.id.rg_button_average_shortfall_other:
                        str_structureshortfall_yes = "15%";
                        break;
                }
            }
        });

        rg_group_teaching_through.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_very_effective:
                        str_structureEffectiv = "very Effective";
                        break;
                    case R.id.rg_effective:
                        str_structureEffectiv = "Effective";
                        break;
                    case R.id.rg_no_effective:
                        str_structureEffectiv = " Not Effective";
                        break;
                }
            }
        });

        rg_TLM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_tlm_yes:
                        str_structureYes = "Yes";
                        break;
                    case R.id.rg_tlm_no:
                        str_structureYes = "No";
                        break;

                }
            }
        });

        rg_group_education.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_education_online:
                        str_structureEmuskanQ4 = "online";
                        break;
                    case R.id.rg_education_mixed:
                        str_structureEmuskanQ4 = "offline mixed";
                        break;
                    case R.id.rg_offline:
                        str_structureEmuskanQ4 = "offline";
                        break;
                }
            }
        });



        rg_group_government.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_group_government_yes:
                        str_structureEmuskanQ5 = "Yes";
                        break;
                    case R.id.rg_group_government_no:
                        str_structureEmuskanQ5 = "No";
                        break;

                }
            }
        });
        rg_group_teaching.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_button_teaching_yes:
                        str_structureEmuskanQ6 = "Yes";
                        break;
                    case R.id.rg_button_teaching_no:
                        str_structureEmuskanQ6 = "No";
                        break;

                }
            }
        });
        rg_group_infrastructure.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_button_infrastructure_yes:
                        str_structureEmuskanQ7 = "Yes";
                        break;
                    case R.id.rg_button_infrastructure_no:
                        str_structureEmuskanQ7 = "No";
                        break;

                }
            }
        });
        rg_group_digital.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_button_digital_yes:
                        str_structureEmuskanQ8 = "Yes";
                        break;
                    case R.id.rg_button_digital_no:
                        str_structureEmuskanQ8 = "No";
                        break;

                }
            }
        });

        rg_group_e_muskan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_butto_e_muskan_yes:
                        str_structureEmuskanQ9 = "Yes";
                        break;
                    case R.id.rg_butto_e_muskan_no:
                        str_structureEmuskanQ9 = "No";
                        break;

                }
            }
        });
        rg_group_e_muskan_being.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_button_e_muskan_being_yes:
                        str_structureEmuskanQ10 = "Yes";
                        break;
                    case R.id.rg_button_e_muskan_being_no:
                        str_structureEmuskanQ10 = "No";
                        break;

                }
            }
        });
        rg_group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_installation_yes:
                        str_structureEmuskanQ11 = "Yes";
                        break;
                    case R.id.rg_installation_no:
                        str_structureEmuskanQ11 = "No";
                        break;

                }
            }
        });
        rg_group4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_school_undertaken_yes:
                        str_structureEmuskanQ12 = "Yes";
                        break;
                    case R.id.rg_school_undertaken_no:
                        str_structureEmuskanQ12 = "No";
                        break;

                }
            }
        });
        rg_group5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_group5_yes:
                        str_structureEmuskanQ13 = "Yes";
                        break;
                    case R.id.rg_group5_no:
                        str_structureEmuskanQ13 = "No";
                        break;

                }
            }
        });
        rg_group6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rg_group6_yes:
                        str_structureEmuskanQ14 = "Yes";
                        break;
                    case R.id.rg_group6_no:
                        str_structureEmuskanQ14 = "No";
                        break;

                }
            }
        });
    }

    @SuppressLint("WrongViewCast")
    private void initila() {
        rg_school_undertaken_no = findViewById(R.id.rg_school_undertaken_no);
        rg_school_undertaken_yes = findViewById(R.id.rg_school_undertaken_yes);
        rg_group5_no = findViewById(R.id.rg_group5_no);
        rg_group5_yes = findViewById(R.id.rg_group5_yes);
        rg_group6_no = findViewById(R.id.rg_group6_no);
        rg_group6_yes = findViewById(R.id.rg_group6_yes);
        rg_tlm_yes = findViewById(R.id.rg_tlm_yes);
        rg_tlm_no = findViewById(R.id.rg_tlm_no);
        rg_button_average_shortfall_yes = findViewById(R.id.rg_button_average_shortfall_yes);
        rg_no_effective = findViewById(R.id.rg_no_effective);
        rg_group_teaching_through = findViewById(R.id.rg_group_teaching_through);
        rg_effective = findViewById(R.id.rg_effective);
        rg_very_effective = findViewById(R.id.rg_very_effective);
        rg_button_average_shortfall_other = findViewById(R.id.rg_button_average_shortfall_other);
        rg_button_average_shortfall_no = findViewById(R.id.rg_button_average_shortfall_no);
        tv_SubResource_Name = findViewById(R.id.tv_SubResource_Name);
        ll_quesionEmuskan = findViewById(R.id.ll_quesionEmuskan);
        ll_quesion = findViewById(R.id.ll_quesion);
        rg_group5 = findViewById(R.id.rg_group5);
        rg_group4 = findViewById(R.id.rg_group4);
        rg_group2 = findViewById(R.id.rg_group2);
        rg_group_e_muskan_being = findViewById(R.id.rg_group_e_muskan_being);
        rg_group_e_muskan = findViewById(R.id.rg_group_e_muskan);
        rg_group_digital = findViewById(R.id.rg_group_digital);
        rg_group_infrastructure = findViewById(R.id.rg_group_infrastructure);
        rg_group_teaching = findViewById(R.id.rg_group_teaching);
        rg_group_government = findViewById(R.id.rg_group_government);
        rg_group_education = findViewById(R.id.rg_group_education);
        rg_TLM = findViewById(R.id.rg_TLM);
        tv_village = findViewById(R.id.tv_village);
        iv_camera1 = findViewById(R.id.iv_camera1);
        iv_camera2 = findViewById(R.id.iv_camera2);
        iv_camera3 = findViewById(R.id.iv_camera3);
        etStartDate = findViewById(R.id.etStartDate);
        etDescription = findViewById(R.id.etDescription);
        tv_Resource_Name = findViewById(R.id.tv_Resource_Name);
        tv_infraName = findViewById(R.id.tv_infraName);
        rg_near_the_structure = findViewById(R.id.rg_near_the_structure);
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
        rg_group6 = findViewById(R.id.rg_group6);
        rg_damaged = findViewById(R.id.rg_damaged);
        iv_camera = findViewById(R.id.iv_camera);
        btnSubmit = findViewById(R.id.btnSubmit);
        rg_group_average_shortfall = findViewById(R.id.rg_group_average_shortfall);
        InfraStruMonitoringPojo = new InfraStruMonitoringPojo();
        sqliteHelper = new SqliteHelper(this);
        sharedPrefHelper = new SharedPrefHelper(this);
        infraStruMultipleImagesPojo = new InfraStruMultipleImagesPojo();
        resourceArrayList = new ArrayList<>();
        infraStructureModelArrayList = new ArrayList<>();
        resourceNameHM = new HashMap<>();


    }
    private boolean validation() {
        if (etStartDate.getText().toString().trim().length() == 0) {
            etStartDate.setError("Please select date!");
            etStartDate.requestFocus();
            return false;
        }
//        if (sharedPrefHelper.getString("SubResource","").equalsIgnoreCase("E-muskaan")){
            if(rg_button_average_shortfall_yes.isChecked() || rg_button_average_shortfall_no.isChecked()||rg_button_average_shortfall_other.isChecked())
            {
            }else{
                Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(rg_very_effective.isChecked() || rg_effective.isChecked()||rg_no_effective.isChecked())
            {
            }else{
                Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(rg_tlm_yes.isChecked() || rg_tlm_no.isChecked())
            {
            }else{
                Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(rg_group6_yes.isChecked() || rg_group6_no.isChecked())
            {
            }else{
                Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(rg_school_undertaken_yes.isChecked() || rg_school_undertaken_no.isChecked())
            {
            }else{
                Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
                return false;
            }
//        }else {
//            if (rv_yes.isChecked() || rv_No.isChecked()) {
//            } else {
//                Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//            if (rv_damaged.isChecked() || rv_damaged_i.isChecked()) {
//            } else {
//                Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//            if (rv_near_the_structure.isChecked() || rv_near_structure.isChecked()) {
//            } else {
//                Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        }
        if (etDescription.getText().toString().trim().length() == 0) {
            etDescription.setError("Please enter description!");
            etDescription.requestFocus();
            return false;
        }

        if (base64.equals("")) {
            Toast.makeText(this, "Please click some pictures also.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    //    Back Button Set
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}