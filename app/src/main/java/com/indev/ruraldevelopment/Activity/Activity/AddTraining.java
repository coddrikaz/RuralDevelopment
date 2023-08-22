package com.indev.ruraldevelopment.Activity.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.TrainingModel;
import com.indev.ruraldevelopment.Activity.Model.TrainingMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Utils.CommonClass;
import com.indev.ruraldevelopment.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class AddTraining extends AppCompatActivity {
    private static final int REQUEST_IMAGE = 1888;
    EditText etNameofTraining, etTrainerName, etMale, etFemale, etObjective;
    TextView etTotalParticipants;
    Button btnSubmit;
    TextView etStartDate;
    TextView tv_village;
    ImageView iv_camera, iv_camera1, iv_camera2, iv_camera3;
    String img1 = "", img2 = "", img3 = "",sImage="";
    String[] image;
    double appVersion = 1.01;

    TrainingMultipleImagePojo trainingMultipleImagePojo;


    SqliteHelper sqliteHelper;
    TrainingModel trainingModel;
    ArrayList<TrainingModel> trainingModelArrayList;
    String base64 = "";
    String type = "";
    String id = "";
    String village_id = "";
    int mYear, mMonth, mDay, year, month, day;
    DatePickerDialog datePickerDialog;
    SharedPrefHelper sharedPrefHelper;

    //   Spinner spnResource;
    ArrayList<String> resourceArrayList;
    HashMap<String, Integer> resourceNameHM;
    int resource_id = 0;
    int count = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_training);

        getSupportActionBar().setTitle("Add Training");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        etNameofTraining = findViewById(R.id.etNameofTraining);
        etStartDate = findViewById(R.id.etStartDate);
//        etEndDate = findViewById(R.id.etEndDate);
        btnSubmit = findViewById(R.id.btnSubmit);
        iv_camera = findViewById(R.id.iv_camera);
        iv_camera1 = findViewById(R.id.iv_camera1);
        iv_camera2 = findViewById(R.id.iv_camera2);
        iv_camera3 = findViewById(R.id.iv_camera3);
        etTrainerName = findViewById(R.id.etTrainerName);
        etMale = findViewById(R.id.etMale);
        etFemale = findViewById(R.id.etFemale);
        tv_village = findViewById(R.id.tv_village);
        etTotalParticipants = findViewById(R.id.etTotalParticipants);
        etObjective = findViewById(R.id.etObjective);
        //spnResource = findViewById(R.id.spnResource);

        trainingMultipleImagePojo = new TrainingMultipleImagePojo();

        sqliteHelper = new SqliteHelper(this);
        resourceArrayList = new ArrayList<>();
        resourceNameHM = new HashMap<>();


        //getResourceSpinner();

        trainingModel = new TrainingModel();
        sharedPrefHelper = new SharedPrefHelper(this);

        tv_village.setText(sharedPrefHelper.getString("village_id", ""));
        String village = sqliteHelper.getColumnName("village_name", "village", " where village_id='" + sharedPrefHelper.getString("village_id", "") + "'");
        tv_village.setText(village);

        TextWatcher autoAddTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!etMale.getText().toString().equals("") && !etFemale.getText().toString().equals("")) {
                    int value1 = Integer.parseInt(etMale.getText().toString());
                    int value2 = Integer.parseInt(etFemale.getText().toString());
                    etTotalParticipants.setText(String.valueOf(value1 + value2));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        etMale.addTextChangedListener(autoAddTextWatcher);
        etFemale.addTextChangedListener(autoAddTextWatcher);



    //Date_Picker StartDate
        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etStartDate.setError(null);
                etStartDate.clearFocus();
                mYear = year;
                mMonth = month;
                mDay = day;

                final Calendar c = Calendar.getInstance();
                c.add(Calendar.DATE, 1);  //do not set past date
                mYear = c.get(Calendar.YEAR);
//                mYear = c.get(Calendar.YEAR ,Calendar.YEAR +1); // current year
                mMonth = c.get(Calendar.MONTH);   // current month
                mDay = c.get(Calendar.DAY_OF_MONTH);   // current day
                datePickerDialog = new DatePickerDialog(AddTraining.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                String nd = "" + dayOfMonth;
                                String nm = "" + monthOfYear;


                                if (dayOfMonth < 10) {
                                    nd = "0" + dayOfMonth;
                                }


                                if ((monthOfYear + 1) > 10) {
                                    nm = "0" + (monthOfYear + 1);
                                } else {
                                    nm = "" + (monthOfYear + 1);
                                }

                                Log.d("testValue", String.valueOf(monthOfYear));


                                // for button text
                                etStartDate.setText((year + "-" + nm + "-" + nd));

                            }
                        }, mYear, mMonth, mDay);



                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ff173e6d"));
            }
        });



        //Update Bundale Type
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id", "0");
            type = bundle.getString("type", "");
        }

        if (type.equals("edit")) {
            getSupportActionBar().setTitle("Update Training Profile");
            village_id = sharedPrefHelper.getString("village_id", "");
            trainingModel = new TrainingModel();
            trainingModelArrayList = sqliteHelper.getTrainingData(village_id);
            etNameofTraining.setText(trainingModelArrayList.get(0).getTraining_name());
            etStartDate.setText(trainingModelArrayList.get(0).getStart_date());
            etTotalParticipants.setText(trainingModelArrayList.get(0).getTotal_attendance());

        } else {
            getSupportActionBar().setTitle("Add Training");
        }

        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < 4) {
                    onProfileImageClick();
                }

            }
        });

        

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (checkValidation()) {
                image = new String[]{
                        img1, img2, img3};

                trainingModel.setTraining_id(Integer.parseInt(CommonClass.getUUID()));
//                  trainingModel.setResource_id(resource_id);
                trainingModel.setTraining_name(etNameofTraining.getText().toString().trim());
                trainingModel.setStart_date(etStartDate.getText().toString().trim());
                trainingModel.setTrainer_name(etTrainerName.getText().toString().trim());
                trainingModel.setMale(etMale.getText().toString().trim());
                trainingModel.setFemale(etFemale.getText().toString().trim());
                trainingModel.setObjective(etObjective.getText().toString().trim());
                trainingModel.setTotal_attendance(etTotalParticipants.getText().toString().trim());
                trainingModel.setApp_version(String.valueOf(appVersion));
                trainingModel.setUser_id(Integer.parseInt(sharedPrefHelper.getString("user_id", "")));
                trainingModel.setVillage_id(Integer.parseInt(sharedPrefHelper.getString("village_id", "")));
                trainingModel.setState_id(Integer.parseInt(sharedPrefHelper.getString("state_id", "")));
                trainingModel.setDistrict_id(Integer.parseInt(sharedPrefHelper.getString("district_id", "")));
                trainingModel.setBlock_id(Integer.parseInt(sharedPrefHelper.getString("block_id", "")));
                trainingModel.setLongitude(sharedPrefHelper.getString("LONG", ""));
                trainingModel.setLatitude(sharedPrefHelper.getString("LAT", ""));
                trainingModel.setUser_id(Integer.parseInt(sharedPrefHelper.getString("user_id", "")));
                trainingModel.setVillage_id(Integer.parseInt(sharedPrefHelper.getString("village_id", "")));
                trainingModel.setState_id(Integer.parseInt(sharedPrefHelper.getString("state_id", "")));
                trainingModel.setDistrict_id(Integer.parseInt(sharedPrefHelper.getString("district_id", "")));
                trainingModel.setBlock_id(Integer.parseInt(sharedPrefHelper.getString("block_id", "")));
                trainingModel.setLongitude(sharedPrefHelper.getString("LONG", ""));
                trainingModel.setLatitude(sharedPrefHelper.getString("LAT", ""));
//                    trainingModel.setTraining_image_name(base64);

                for (int i = 0; i < 3; i++) {
                    if (!image[i].equals("")) {
                        trainingMultipleImagePojo.setTraining_id(trainingModel.getTraining_id());
                        trainingMultipleImagePojo.setTraining_image_name(image[i]);
                        trainingMultipleImagePojo.setUser_id(Integer.parseInt(sharedPrefHelper.getString("user_id", "")));
                        sqliteHelper.saveHousehold(trainingMultipleImagePojo);
                    }
                }

                if (type.equals("edit")) {
                    sqliteHelper.updatTraingeHousehold(trainingModel, id);
                    Intent intent = new Intent(AddTraining.this, List_of_Training.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    sqliteHelper.saveHousehold(trainingModel);
                    Intent intent = new Intent(AddTraining.this, List_of_Training.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                Intent intent = new Intent(AddTraining.this, List_of_Training.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra("type", "edit");
//                intent.putExtra("id",trainingModelArrayList.get(0).getLocal_id());
                startActivity(intent);
//                }
            }
        });
    }


//    private void getResourceSpinner() {
//        resourceArrayList.clear();
//        resourceNameHM = sqliteHelper.getResource();
//        for (int i = 0; i < resourceNameHM.size(); i++) {
//            resourceArrayList.add(resourceNameHM.keySet().toArray()[i].toString().trim());
//        }
//        Collections.sort(resourceArrayList, String.CASE_INSENSITIVE_ORDER);
////        Collections.sort(blockArrayList);
//        resourceArrayList.add(0, "Select Resource Type");
//        //state spinner choose
//        ArrayAdapter adapterEducation = new ArrayAdapter(AddTraining.this, android.R.layout.simple_spinner_item, resourceArrayList);
//        adapterEducation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnResource.setAdapter(adapterEducation);
////        Bundle bundle = getIntent().getExtras();
//
////        if (screen_type.equals("edit_profile")) {
////            st_state = sqliteHelper.getPSState(CandidatePojo.getState_id());
////            int pos = state_adapter.getPosition(st_state);
////            spn_State.setSelection(pos);
////        }
//
//        spnResource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (!spnResource.getSelectedItem().toString().trim().equalsIgnoreCase("Select Resource Type")) {
//                    if (spnResource.getSelectedItem().toString().trim() != null) {
//                        resource_id = resourceNameHM.get(spnResource.getSelectedItem().toString().trim());
//
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


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE) {
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
//        } else {
//            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
//        }
//
//    }


    private String encodeTobase64(Bitmap image) {
        ByteArrayOutputStream byteArrayOS = null;
        try {
            System.gc();
            byteArrayOS = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOS);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOS);
        }
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.NO_WRAP);
    }


    public void onBackPressed() {
        super.onBackPressed();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
//            case R.id.home_icon:
//                Intent intent1 = new Intent(this, Login.class);
//                startActivity(intent1);
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    void onProfileImageClick() {
//        Dexter.withActivity(this)
//                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        if (report.areAllPermissionsGranted()) {
                            ImagePicker.with(AddTraining.this)
                                    .cameraOnly()
                                    .crop()
                                    .compress(1024)
                                    .maxResultSize(1080,1080)//User can only select image from Gallery
                                    .start();
//                            showImagePickerOptions();
                        }
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
//    }

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
        Intent intent = new Intent(AddTraining.this, ImagePickerActivity.class);
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
        Intent intent = new Intent(AddTraining.this, ImagePickerActivity.class);
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
                        Toast.makeText(AddTraining.this, "You Can Capture Only Three Image", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AddTraining.this);
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

//    private boolean checkValidation() {
//
//        if (spnResource.getSelectedItemPosition() > 0) {
//            String itemvalue = String.valueOf(spnResource.getSelectedItem());
//        } else {
//            TextView errorTextview = (TextView) spnResource.getSelectedView();
//            errorTextview.setError("Error");
//            errorTextview.requestFocus();
//            return false;
//        }
//
//        if (etNameofTraining.getText().toString().trim().length() == 0) {
//            etNameofTraining.setError("Please enter the name of training");
//            etNameofTraining.requestFocus();
//            return false;
//        }
//        if (etStartDate.getText().toString().trim().length() == 0) {
//            etStartDate.setError("Please enter the starting date");
//            etStartDate.requestFocus();
//            return false;
//        }
//        if (etEndDate.getText().toString().trim().length() == 0) {
//            etEndDate.setError("Please enter the end date");
//            etEndDate.requestFocus();
//            return false;
//        }
//
//        if (etTotalAttendance.getText().toString().trim().length() == 0) {
//            etTotalAttendance.setError("Please enter the end date");
//            etTotalAttendance.requestFocus();
//            return false;
//        }
//
//        if (base64.equals("")) {
//            Toast.makeText(this, "Please click some Pictures also.", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
//    }


}