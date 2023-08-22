package com.indev.ruraldevelopment.Activity.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.ResourceMappingPojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Utils.CommonClass;
import com.indev.ruraldevelopment.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Add_resources extends AppCompatActivity {
    private static final int REQUEST_IMAGE = 1888;
    Spinner spinnerResource, etStatus;
    HashMap<String, Integer> resourceNameHM;
    ArrayList<String> resourceArrayList;
    int resource_id = 0;
    double appVersion = 1.01;
    String sImage="";


    int mYear, mMonth, mDay, year, month, day;
    DatePickerDialog datePickerDialog;
    //    String[] resource = {"Select Resource Type", "Hospital", "School", "Temple", "Water Supply", "Others"};
    String[] status = {"Select Resource Status", "Functional", "Non-Functional"};
    Button btnsubmit;
    String[] image;
    EditText etAddress, etDescription, etDate, etResourcename, etMobileNo,etContact,etPerson;
    TextView tv_village;
    String base64 = "";
    String img1 = "", img2 = "", img3 = "";
    ImageView iv_camera, iv_camera1, iv_camera2, iv_camera3;
    SqliteHelper sqliteHelper;
    int count = 1;
    String name = "";
    String village_id = "";
    private static final int REQUEST = 112;
    SharedPrefHelper sharedPrefHelper;
    //    MultipleImagePojo multipleImagePojo;
    ResourceMultipleImagePojo resourceMultipleImagePojo;
    ResourceMappingPojo resourceMappingPojo;
    String screentype = "";
    int v_id = 0;
    private final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resources);
        setTitle("Add Resource ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getid();
        resourceMappingPojo = new ResourceMappingPojo();
//      multipleImagePojo = new MultipleImagePojo();
        resourceMultipleImagePojo = new ResourceMultipleImagePojo();
        sqliteHelper = new SqliteHelper(this);
        sharedPrefHelper = new SharedPrefHelper(this);
        resourceNameHM = new HashMap<>();
        resourceArrayList = new ArrayList<>();

        getResourceSpinner();
        tv_village.setText(sharedPrefHelper.getString("village_id", ""));
        String village=sqliteHelper.getColumnName("village_name","village"," where village_id='"+sharedPrefHelper.getString("village_id", "")+"'");
        tv_village.setText(village);


        //All Spinner Value
        ArrayAdapter arrayStatus = new ArrayAdapter(Add_resources.this, android.R.layout.simple_spinner_item, status);
        arrayStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etStatus.setAdapter(arrayStatus);

//        ArrayAdapter adapterEducation = new ArrayAdapter(Add_resources.this, android.R.layout.simple_spinner_item, resource);
//        adapterEducation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnResource.setAdapter(adapterEducation);
        Bundle bundle = getIntent().getExtras();
//
        if (bundle != null) {
            screentype = bundle.getString("screentype", "");
            v_id = bundle.getInt("v_id", 0);
        }

        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < 4) {
                    onProfileImageClick();
//
                }

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (validation()) {
                    image = new String[]{
                            img1, img2, img3};

//                if(v_id==0){
//                    v_id=0;
//                }
//                int villageProfileId=0;
//                if(sharedPrefHelper.getString("resource_screen_type","").equals("jansanchetna")){
//                    villageProfileId= sqliteHelper.getCloumnName("village_profile_id","village_profile"," where village_id='"+village_id+"'");
//                }

                    name = etResourcename.getText().toString().trim();
                    resourceMappingPojo.setResource_id(Integer.parseInt(String.valueOf(resource_id)));
                    resourceMappingPojo.setResource_mapping_name(etResourcename.getText().toString().trim());
                    resourceMappingPojo.setResource_status(etStatus.getSelectedItem().toString().trim());
                    resourceMappingPojo.setResource_mapping_date(CommonClass.currentDate());
                    resourceMappingPojo.setAddress(etAddress.getText().toString().trim());
                    resourceMappingPojo.setContact_person(etPerson.getText().toString().trim());
                    resourceMappingPojo.setContact_mob_no(etContact.getText().toString().trim());
                    resourceMappingPojo.setVillage_profile_id(v_id);
                    resourceMappingPojo.setApp_version(String.valueOf(appVersion));

                    resourceMappingPojo.setUser_id(Integer.parseInt(sharedPrefHelper.getString("user_id", "")));
                    resourceMappingPojo.setVillage_id(Integer.parseInt(sharedPrefHelper.getString("village_id", "")));
                    resourceMappingPojo.setState_id(Integer.parseInt(sharedPrefHelper.getString("state_id", "")));
                    resourceMappingPojo.setDistrict_id(Integer.parseInt(sharedPrefHelper.getString("district_id", "")));
                    resourceMappingPojo.setBlock_id(Integer.parseInt(sharedPrefHelper.getString("block_id", "")));
                    resourceMappingPojo.setLongitude(sharedPrefHelper.getString("LONG", ""));
                    resourceMappingPojo.setLatitude(sharedPrefHelper.getString("LAT", ""));

                    resourceMappingPojo.setDescription(etDescription.getText().toString().trim());
                    String uuid = CommonClass.getUUID();
                    resourceMappingPojo.setResource_mapping_id(Integer.parseInt(uuid));
                    sqliteHelper.saveHousehold(resourceMappingPojo);
                    for (int i = 0; i < 3; i++) {
                        if (!image[i].equals("")) {
                            resourceMultipleImagePojo.setResource_mapping_id(String.valueOf(resourceMappingPojo.getResource_mapping_id()));
                            resourceMultipleImagePojo.setResource_mapping_image(image[i]);
                            resourceMultipleImagePojo.setUser_id(Integer.parseInt(sharedPrefHelper.getString("user_id", "")));
                            sqliteHelper.saveHousehold(resourceMultipleImagePojo);
                        }
                    }

                    Intent intent = null;
                    if (screentype.equals("Resource Mapping")) {
                        intent = new Intent(Add_resources.this, List_of_resources.class);
                        startActivity(intent);
                    } else {
                        intent = new Intent(Add_resources.this, AddVillageProfile.class);
                        intent.putExtra("type", "edit");
                        intent.putExtra("vi", v_id);
                        startActivity(intent);
//                        onBackPressed();
                    }
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);

//                    Toast.makeText(Add_resources.this, "Resources has been added", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null) {
//            if (requestCode == REQUEST_IMAGE) {
//                Bitmap photo = (Bitmap) data.getExtras().get("data");
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] bytes = stream.toByteArray();
//
//                base64 = encodeTobase64(photo);
//                if (count == 1) {
//                    iv_camera1.setImageBitmap(photo);
//                    img1 = encodeTobase64(photo);
//                }
//                if (count == 2) {
//                    iv_camera2.setImageBitmap(photo);
//                    img2 = encodeTobase64(photo);
//
//                }
//                if (count == 3) {
//                    iv_camera3.setImageBitmap(photo);
//                    img3 = encodeTobase64(photo);
//                }
//                count++;
//            } else {
//                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
//            }
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

    @Override
    public void onBackPressed() {
        if (!screentype.equals("Resource Mapping")) {
            Intent intent = new Intent(Add_resources.this, AddVillageProfile.class);
            intent.putExtra("type", "edit");
            intent.putExtra("vi", v_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            super.onBackPressed();
        }

    }


//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


    public void getid() {

        etStatus = findViewById(R.id.etStatus);
        iv_camera = findViewById(R.id.iv_camera);
        iv_camera1 = findViewById(R.id.iv_camera1);
        iv_camera2 = findViewById(R.id.iv_camera2);
        iv_camera3 = findViewById(R.id.iv_camera3);
        etResourcename = findViewById(R.id.etResourcename);
        etDescription = findViewById(R.id.etDescription);
        etAddress = findViewById(R.id.etAddress);
        spinnerResource = findViewById(R.id.spinnerResource);
        tv_village = findViewById(R.id.tv_village);
        btnsubmit = findViewById(R.id.btnsubmit);
        //       etDate = findViewById(R.id.etDate);
        etMobileNo = findViewById(R.id.etMobileNo);
        etContact = findViewById(R.id.etContact);
        etPerson = findViewById(R.id.etPerson);
    }

    private void getResourceSpinner() {
        resourceArrayList.clear();
        resourceNameHM = sqliteHelper.getResourcess();
        for (int i = 0; i < resourceNameHM.size(); i++) {
            resourceArrayList.add(resourceNameHM.keySet().toArray()[i].toString().trim());
        }
        Collections.sort(resourceArrayList, String.CASE_INSENSITIVE_ORDER);
//        Collections.sort(blockArrayList);
        resourceArrayList.add(0, "Select Resource Type");
        //state spinner choose
        ArrayAdapter adapterEducation = new ArrayAdapter(Add_resources.this, android.R.layout.simple_spinner_item, resourceArrayList);
        adapterEducation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerResource.setAdapter(adapterEducation);
//        Bundle bundle = getIntent().getExtras();

//        if (screen_type.equals("edit_profile")) {
//            st_state = sqliteHelper.getPSState(CandidatePojo.getState_id());
//            int pos = state_adapter.getPosition(st_state);
//            spn_State.setSelection(pos);
//        }

        spinnerResource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!spinnerResource.getSelectedItem().toString().trim().equalsIgnoreCase("Select Resource Type")) {
                    if (spinnerResource.getSelectedItem().toString().trim() != null) {
                        resource_id = resourceNameHM.get(spinnerResource.getSelectedItem().toString().trim());

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean validation() {

        if (spinnerResource.getSelectedItemPosition() > 0) {
            String itemvalue = String.valueOf(spinnerResource.getSelectedItem());
        } else {
            TextView errorTextview = (TextView) spinnerResource.getSelectedView();
            errorTextview.setError("Error");
            errorTextview.requestFocus();
            return false;
        }
        if (etResourcename.getText().toString().trim().length() == 0) {
            etResourcename.setError("Please enter person name!");
            etResourcename.requestFocus();
            return false;
        }

        if (etStatus.getSelectedItem().toString().equals("Select Resource Type")) {
            Toast.makeText(getApplicationContext(), "Please select category", Toast.LENGTH_LONG).show();
            return false;
        }

        if (etAddress.getText().toString().trim().length() == 0) {
            etAddress.setError("Please enter designation!");
            etAddress.requestFocus();
            return false;
        }

        if (etDescription.getText().toString().trim().length() == 0) {
            etDescription.setError("Please enter designation!");
            etDescription.requestFocus();
            return false;
        }

        if (base64.equals("")) {
            Toast.makeText(this, "Please click some pictures also.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    void onProfileImageClick() {
//        Dexter.withActivity(this)
//                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        if (report.areAllPermissionsGranted()) {
                            ImagePicker.with(Add_resources.this)
                                    .cameraOnly()
                                    .crop()
                                    .compress(1024)
                                    .maxResultSize(1080,1080)//User can only select image from Gallery
                                    .start();
//                            showImagePickerOptions();
//                        }

//                        if (report.isAnyPermissionPermanentlyDenied()) {
//                            showSettingsDialog();
//                        }
//                    }

//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                }).check();
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
        Intent intent = new Intent(Add_resources.this, ImagePickerActivity.class);
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
        Intent intent = new Intent(Add_resources.this, ImagePickerActivity.class);
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
                        Toast.makeText(Add_resources.this, "You Can Capture Only Three Image", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(Add_resources.this);
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