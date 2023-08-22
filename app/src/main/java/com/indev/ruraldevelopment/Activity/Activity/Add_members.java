package com.indev.ruraldevelopment.Activity.Activity;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.Committee_Table;
import com.indev.ruraldevelopment.Activity.Utils.CommonClass;
import com.indev.ruraldevelopment.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Add_members extends AppCompatActivity {
    private static final int REQUEST_IMAGE = 1888;
    TextView etName, etPhone_no;
    Spinner etRole;
    // String[] select_role = {"Advisor", "Trainer", "Tailoring",};
    Button btnSubmit;
    ImageView iv_camera;
    SqliteHelper sqliteHelper;
    TextView tv_village;
    Committee_Table committee_Table;
    SharedPrefHelper sharedPrefHelper;
    String base64 = "";
    String screen_type = "",villageProfileId="";
    String type = "";
    String village_id = "",sImage="";
    String id = "";
    ArrayList<Committee_Table> committee_tableArrayList;
    LinkedHashMap<String, Integer> memberRoleHM;
    ArrayList<String> memberArrayList;
    int role_id = 0;
    int v_id = 0;
    double appVersion = 1.01;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Committee Member");


        etName = findViewById(R.id.etName);
        etPhone_no = findViewById(R.id.etPhone_no);
        btnSubmit = findViewById(R.id.btnSubmit);
        etRole = findViewById(R.id.etRole);
        tv_village = findViewById(R.id.tv_village);
        iv_camera = findViewById(R.id.iv_camera);
        sqliteHelper = new SqliteHelper(this);
        committee_Table = new Committee_Table();
        sharedPrefHelper = new SharedPrefHelper(this);
        memberRoleHM = new LinkedHashMap<>();
        memberArrayList = new ArrayList<>();

        getMemberRoleSpinner();
        village_id = sharedPrefHelper.getString("village_id","");
        tv_village.setText(sharedPrefHelper.getString("village_id", ""));
        String village=sqliteHelper.getColumnName("village_name","village"," where village_id='"+sharedPrefHelper.getString("village_id", "")+"'");
//        villageProfileId= String.valueOf(sqliteHelper.getCloumnName("village_profile_id","village_profile"," where village_id='"+village_id+"'"));

        tv_village.setText(village);



//        ArrayAdapter arrayStatus = new ArrayAdapter(Add_members.this, android.R.layout.simple_spinner_item, select_role);
//        arrayStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        etRole.setAdapter(arrayStatus);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            screen_type = bundle.getString("screentype", "");
            v_id = bundle.getInt("v_id", 0);

        }

        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onProfileImageClick();
            }
        });

//        if(bundle != null){
//            id = bundle.getString("id", "0");
//            type = bundle.getString("type", "");
//        }
//
//        if (type.equals("edit")) {
//            getSupportActionBar().setTitle("Update Committee Member");
//            committee_Table = new Committee_Table();
//
//            committee_tableArrayList= sqliteHelper.updateHouseholdMember(committee_Table, id);
//            etName.setText(committee_tableArrayList.get(0).getMember_name());
//            etPhone_no.setText(committee_tableArrayList.get(0).getMobile_no());
////            etRole.se(villageProfileModelArrayList.get(0).getMobile_no());
//
//
//        } else {
//            getSupportActionBar().setTitle("Add Village Profile");
//        }


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation()) {
//                int vId = getIntent().getIntExtra("v_id", -1);
                    // ''
//                if (vId == -1) {
//                    return;
//                }
                    village_id = sharedPrefHelper.getString("village_id", "");
//
//                int villageProfileId= sqliteHelper.getCloumnName("village_profile_id","village_profile"," where village_id='"+village_id+"'");
//
                    committee_Table.setMember_name(etName.getText().toString().trim());
                    committee_Table.setMobile_no(etPhone_no.getText().toString().trim());
                    committee_Table.setRole_id(String.valueOf(role_id));

                    committee_Table.setApp_version(String.valueOf(appVersion));


                    committee_Table.setUser_id(Integer.parseInt(sharedPrefHelper.getString("user_id", "")));
                    committee_Table.setState_id(Integer.parseInt(sharedPrefHelper.getString("state_id", "")));
                    committee_Table.setDistrict_id(Integer.parseInt(sharedPrefHelper.getString("district_id", "")));
                    committee_Table.setBlock_id(Integer.parseInt(sharedPrefHelper.getString("block_id", "")));
                    committee_Table.setLongitude(sharedPrefHelper.getString("LONG", ""));
                    committee_Table.setLatitude(sharedPrefHelper.getString("LAT", ""));
                    committee_Table.setVillage_id(Integer.parseInt(village_id));
                    committee_Table.setVillage_profile_id(v_id);
                    committee_Table.setMember_id(Integer.parseInt(CommonClass.getUUID()));
                    committee_Table.setImage(base64);

                    sqliteHelper.saveHousehold(committee_Table);


                    Intent intent = null;
                    if (screen_type.equals("Committe")) {
                        intent = new Intent(Add_members.this, List_of_committee_member.class);
                        intent.putExtra("vil_id", sharedPrefHelper.getString("village_id", ""));
                        startActivity(intent);
                    } else {
                        intent = new Intent(Add_members.this, AddVillageProfile.class);
                        intent.putExtra("type", "edit");
                        intent.putExtra("vi", v_id);
                        startActivity(intent);
                    }
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);

//                    Toast.makeText(Add_members.this, "Member has been added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getMemberRoleSpinner() {

        memberArrayList.clear();
        memberRoleHM = sqliteHelper.getMemberRole();
        for (int i = 0; i < memberRoleHM.size(); i++) {
            memberArrayList.add(memberRoleHM.keySet().toArray()[i].toString().trim());
        }
//        Collections.sort(memberArrayList, String.CASE_INSENSITIVE_ORDER);
//        Collections.sort(blockArrayList);
        
        memberArrayList.add(0, "Select Member Role");
        //state spinner choose
        ArrayAdapter arrayStatus = new ArrayAdapter(Add_members.this, android.R.layout.simple_spinner_item, memberArrayList);
        arrayStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etRole.setAdapter(arrayStatus);
//

        etRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!etRole.getSelectedItem().toString().trim().equalsIgnoreCase("Select Member Role")) {
                    if (etRole.getSelectedItem().toString().trim() != null) {
                        role_id = memberRoleHM.get(etRole.getSelectedItem().toString().trim());

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null) {
//            if (requestCode == CAMERA_REQUEST) {
//                Bitmap photo = (Bitmap) data.getExtras().get("data");
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] bytes = stream.toByteArray();
//                base64 = encodeTobase64(photo);
//                iv_camera.setImageBitmap(photo);
//            }
//        }
//    }

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

    public void onBackPressed() {
        if (!screen_type.equals("Committe")) {
            Intent intent = new Intent(Add_members.this, AddVillageProfile.class);
            intent.putExtra("type", "edit");
            intent.putExtra("vi", v_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Intent intent = new Intent(Add_members.this, List_of_committee_member.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean checkValidation() {


        if (etName.getText().toString().trim().length() == 0) {
            etName.setError("Please enter member name");
            etName.requestFocus();
            return false;
        }
        if (etPhone_no.getText().toString().trim().length() < 10) {
            TextView flagEditfield = etPhone_no;
            String msg = getString(R.string.Please_Enter_Mobile_Number);
            etPhone_no.setError(msg);
            etPhone_no.requestFocus();
            return false;
        }

        if (etRole.getSelectedItemPosition() > 0) {
            String itemvalue = String.valueOf(etRole.getSelectedItem());
        } else {
            TextView errorTextview = (TextView) etRole.getSelectedView();
            errorTextview.setError("Error");
            errorTextview.requestFocus();
            return false;
        }

        if (base64.equals("")) {
            Toast.makeText(this, "Please click some Pictures also.", Toast.LENGTH_SHORT).show();
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
                            ImagePicker.with(Add_members.this)
                                    .cameraOnly()
                                    .crop()
                                    .compress(1024)
                                    .maxResultSize(1080,1080)//User can only select image from Gallery
                                    .start();
//                            showImagePickerOptions();
                        }

//                        if (report.isAnyPermissionPermanentlyDenied()) {
//                            showSettingsDialog();
//                        }
//                    }

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
        Intent intent = new Intent(Add_members.this, ImagePickerActivity.class);
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
        Intent intent = new Intent(Add_members.this, ImagePickerActivity.class);
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
                    iv_camera.setImageBitmap(bitmapp);
                    base64 = encodeTobase64(bitmapp);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(Add_members.this);
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