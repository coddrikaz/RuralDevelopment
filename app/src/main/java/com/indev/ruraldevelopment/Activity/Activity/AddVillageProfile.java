
package com.indev.ruraldevelopment.Activity.Activity;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL6;

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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.indev.ruraldevelopment.Activity.Adapter.AdapterMemberViewVillageResource;
import com.indev.ruraldevelopment.Activity.Adapter.AdapterResourceViewVillageResource;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.Committee_Table;
import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMappingPojo;
import com.indev.ruraldevelopment.Activity.Model.VillageProfileModel;
import com.indev.ruraldevelopment.Activity.Utils.CommonClass;
import com.indev.ruraldevelopment.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddVillageProfile extends AppCompatActivity {
    private static final int REQUEST_IMAGE = 1888;
    private static final int ADD_MEMBER = 9999;

    RecyclerView rvMembers, rvResources;
    ArrayList<Committee_Table> committee_tableArrayList;
    ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList;
    EditText etPopulation;
    TextView tv_village;
    EditText etPradhan_name;
    EditText etContactnumber;
    int uuid=0;
    Button btnSubmit;
    SharedPrefHelper sheredPrefHelpers;
    String base64 = "";
    SqliteHelper sqliteHelper;
    String village_id="",sImage="";
    String village_idd = "";
    VillageProfileModel villageProfileModel;
    VillageProfileModel villageProfileModelData;
    ArrayList<VillageProfileModel> villageProfileModelArrayList;
    ArrayList<VillageProfileModel> villageProfileModelArrayListData;
    ArrayList<MultipleImagePojo> multipleImagePojoArrayList;

    String type = "";
    String id = "";
    String st_population = "";
    String st_pradhan_name = "";
    String st_contact_no = "";
    String edit_type = "";
    String local_id = "";
    String st_village_profile_id = "";
    int v_id=0;
    double appVersion = 1.01;


    SharedPrefHelper sharedPrefHelper;
    String population, pradhan_name, contact_number;

    String st_image = "", st_image1 = "", st_image2 = "";
    ImageView iv_camera, iv_camera1, iv_camera2, iv_camera3;
    CardView addResourses, addMembers;
    String[] image;
    int count = 1;
    MultipleImagePojo multipleImagePojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_village_profile);
        tv_village=findViewById(R.id.tv_village);

        getSupportActionBar().setTitle("Add Village Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        define();
        sqliteHelper = new SqliteHelper(this);
        sharedPrefHelper = new SharedPrefHelper(this);
//        tv_village.setText(sharedPrefHelper.getString("village_id", ""));
        String village=sqliteHelper.getColumnName("village_name","village"," where village_id='"+sharedPrefHelper.getString("village_id", "")+"'");
        tv_village.setText(village);




        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id", "");
            type = bundle.getString("type", "");
            v_id = bundle.getInt("vi", 0);
        }
        village_id=sharedPrefHelper.getString("village_id","");
        local_id = sharedPrefHelper.getString("local_id", "");
        id = local_id;

        edit_type = sharedPrefHelper.getString("edit_type", "");

        villageProfileModel = new VillageProfileModel();


        multipleImagePojo = new MultipleImagePojo();

        addMembers = findViewById(R.id.addMembers);
        addResourses = findViewById(R.id.addResource);

        //Find Data Village_base




//        if (type.equals("edit")) {
//            getSupportActionBar().setTitle("Update Village Profile");
////            villageProfileModel = new VillageProfileModel();
////            villageProfileModelArrayList = sqliteHelper.getVillageProfileData(v_id);
////            etPopulation.setText(villageProfileModelArrayList.get(0).getPopulation());
////            etPradhan_name.setText(villageProfileModelArrayList.get(0).getPradhan_name());
////            etContactnumber.setText(villageProfileModelArrayList.get(0).getMobile_no());
//
//
//        } else {
//            getSupportActionBar().setTitle("Add Village Profile");
//        }

        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < 4) {
                    onProfileImageClick();
                }
            }
        });

        addResourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                image = new String[]{
                        st_image, st_image1, st_image2};
                uuid = Integer.parseInt(CommonClass.getUUID());
                villageProfileModel.setPopulation(etPopulation.getText().toString().trim());
                villageProfileModel.setPradhan_name(etPradhan_name.getText().toString().trim());
                villageProfileModel.setMobile_no(etContactnumber.getText().toString().trim());
                villageProfileModel.setVillage_id(Integer.parseInt(village_id));
                villageProfileModel.setApp_version(String.valueOf(appVersion));
                villageProfileModel.setState_id(Integer.parseInt(sharedPrefHelper.getString("state_id","")));
                villageProfileModel.setDistrict_id(Integer.parseInt(sharedPrefHelper.getString("district_id","")));
                villageProfileModel.setBlock_id(Integer.parseInt(sharedPrefHelper.getString("block_id","")));
                villageProfileModel.setLongitude(sharedPrefHelper.getString("LONG", ""));
                villageProfileModel.setLatitude(sharedPrefHelper.getString("LAT", ""));
                villageProfileModel.setFlag("2");
                villageProfileModel.setVillage_profile_id(uuid);

                String user_id = sharedPrefHelper.getString("user_id", "");
                villageProfileModel.setUser_id(Integer.parseInt(user_id));

//                villageProfileModel.setVillage_profile_id(uuid);

                if (type.equals("edit") || edit_type.equals("true")) {
                    sqliteHelper.updateHousehold(villageProfileModel,v_id);
                    Intent intent = new Intent(AddVillageProfile.this, Add_resources.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("v_id", v_id);
                    startActivity(intent);
                } else {
                    long villageProfileId=sqliteHelper.saveHousehold(villageProfileModel);
                    for (int i = 0; i < 3; i++) {
                        if (!image[i].equals("")) {
                            multipleImagePojo.setVillage_profile_id(String.valueOf(uuid));
                            multipleImagePojo.setVillage_profile_img(image[i]);
                            sqliteHelper.saveVillageImage(multipleImagePojo);
                        }
                    }

                    if(villageProfileId>0) {
                        Intent intent = new Intent(AddVillageProfile.this, Add_resources.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("v_id", villageProfileModel.getVillage_profile_id());
                        startActivity(intent);
                    }
                }
            }
        });


        if (type.equals("edit") || edit_type.equals("true")) {
//            getSupportActionBar().setTitle("Update Village Profile");

            villageProfileModelArrayListData = sqliteHelper.getVillageProfileDataVillageType(String.valueOf(v_id));
            if (villageProfileModelArrayListData.size() != 0) {
                village_idd = String.valueOf(villageProfileModelArrayListData.get(0).getVillage_id());
                st_population = String.valueOf(villageProfileModelArrayListData.get(0).getPopulation());
                st_pradhan_name = String.valueOf(villageProfileModelArrayListData.get(0).getPradhan_name());
                st_contact_no = String.valueOf(villageProfileModelArrayListData.get(0).getMobile_no());
                st_village_profile_id = String.valueOf(villageProfileModelArrayListData.get(0).getVillage_profile_id());

                //setVillage view data
//                sharedPrefHelper.setString("population", st_population);
//                sharedPrefHelper.setString("pradhanName", st_pradhan_name);
//                sharedPrefHelper.setString("contactNumber", st_contact_no);
//                sharedPrefHelper.setString("villImage", st_village_profile_id);

                etPopulation.setText(st_population);
                etPradhan_name.setText(st_pradhan_name);
                etContactnumber.setText(st_contact_no);

                multipleImagePojoArrayList = sqliteHelper.getMultipleImage(v_id);
                if (multipleImagePojoArrayList.size() > 0 ) {
                    st_image = String.valueOf(multipleImagePojoArrayList.get(0).getVillage_profile_img());

                    if(multipleImagePojoArrayList.size()>1){
                        st_image1 = String.valueOf(multipleImagePojoArrayList.get(1).getVillage_profile_img());
                    }else if(multipleImagePojoArrayList.size()>2){
                        st_image2 = String.valueOf(multipleImagePojoArrayList.get(2).getVillage_profile_img());
                    }
                }
                sharedPrefHelper.setString("villageImage", st_image);
                if (st_image != null && st_image.length() > 200) {
                    byte[] decodedString = Base64.decode(st_image, Base64.NO_WRAP);
                    InputStream inputStream = new ByteArrayInputStream(decodedString);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    iv_camera1.setImageBitmap(bitmap);
                }else {
                    try {
                        Picasso.get()
                                .load(BASE_URL6 + st_image)
                                .placeholder(R.drawable.ic_baseline_broken_image_24)
                                .into(iv_camera1);

                    } catch (Exception e) {
                        Log.d("Exception", "" + e);
                    }
                }

                if (st_image1 != null && st_image1.length() > 200) {
                    byte[] decodedString = Base64.decode(st_image1, Base64.NO_WRAP);
                    InputStream inputStream = new ByteArrayInputStream(decodedString);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    iv_camera2.setImageBitmap(bitmap);
                } else {
                    try {
                        Picasso.get()
                                .load(BASE_URL6 + st_image1)
                                .placeholder(R.drawable.ic_baseline_broken_image_24)
                                .into(iv_camera2);
                    } catch (Exception e) {
                        Log.d("Exception", "" + e);
                    }
                }


                if (st_image2 != null && st_image2.length() > 200) {
                    byte[] decodedString = Base64.decode(st_image2, Base64.NO_WRAP);
                    InputStream inputStream = new ByteArrayInputStream(decodedString);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    iv_camera3.setImageBitmap(bitmap);
                } else {
                    try {
                        Picasso.get()
                                .load(BASE_URL6 + st_image2)
                                .placeholder(R.drawable.ic_baseline_broken_image_24)
                                .into(iv_camera3);

                    } catch (Exception e) {
                        Log.d("Exception", "" + e);
                    }
                }

            }
        }else{
            getSupportActionBar().setTitle("Add Village Profile");

        }

        addMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                image = new String[]{
                        st_image, st_image1, st_image2};
                uuid = Integer.parseInt(CommonClass.getUUID());
                villageProfileModel.setPopulation(etPopulation.getText().toString().trim());
                villageProfileModel.setPradhan_name(etPradhan_name.getText().toString().trim());
                villageProfileModel.setMobile_no(etContactnumber.getText().toString().trim());
                villageProfileModel.setVillage_id(Integer.parseInt(village_id));
                villageProfileModel.setApp_version(String.valueOf(appVersion));
                villageProfileModel.setState_id(Integer.parseInt(sharedPrefHelper.getString("state_id","")));
                villageProfileModel.setDistrict_id(Integer.parseInt(sharedPrefHelper.getString("district_id","")));
                villageProfileModel.setBlock_id(Integer.parseInt(sharedPrefHelper.getString("block_id","")));
                villageProfileModel.setLongitude(sharedPrefHelper.getString("LONG", ""));
                villageProfileModel.setLatitude(sharedPrefHelper.getString("LAT", ""));
                villageProfileModel.setFlag("2");
                villageProfileModel.setVillage_profile_id(uuid);

                String user_id = sharedPrefHelper.getString("user_id", "");
                villageProfileModel.setUser_id(Integer.parseInt(user_id));

//                villageProfileModel.setVillage_profile_id(uuid);

                if (type.equals("edit") || edit_type.equals("true")) {
                    sqliteHelper.updateHousehold(villageProfileModel, v_id);
                    Intent intent = new Intent(AddVillageProfile.this, Add_members.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("v_id", v_id);
                    startActivity(intent);
                } else {
                    long villageProfileId=sqliteHelper.saveHousehold(villageProfileModel);
                    for (int i = 0; i < 3; i++) {
                        if (!image[i].equals("")) {
                            multipleImagePojo.setVillage_profile_id(String.valueOf(uuid));
                            multipleImagePojo.setVillage_profile_img(image[i]);
                            sqliteHelper.saveVillageImage(multipleImagePojo);
                        }
                    }

                    if(villageProfileId>0) {
                        Intent intent = new Intent(AddVillageProfile.this, Add_members.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        int villageProId=villageProfileModel.getVillage_profile_id();
                        intent.putExtra("v_id", villageProId);
//                            startActivityForResult(intent, ADD_MEMBER);
                        startActivity(intent);
                    }
                }
            }
        });

        showMember();
        showResource();

        btnSubmit.setOnClickListener(view -> {
//            image = new String[]{
//                    st_image, st_image1, st_image2};
            if (checkValidation()) {


                String user_id = sharedPrefHelper.getString("user_id", "");
                villageProfileModel.setUser_id(Integer.parseInt(user_id));

                if (type.equals("edit") || edit_type.equals("true")) {
                    image = new String[]{
                            st_image, st_image1, st_image2};
                    villageProfileModel.setPopulation(etPopulation.getText().toString().trim());
                    villageProfileModel.setPradhan_name(etPradhan_name.getText().toString().trim());
                    villageProfileModel.setMobile_no(etContactnumber.getText().toString().trim());
                    villageProfileModel.setLongitude(sharedPrefHelper.getString("LONG", ""));
                    villageProfileModel.setLatitude(sharedPrefHelper.getString("LAT", ""));
                    villageProfileModel.setFlag("0");
                    sqliteHelper.updateHousehold(villageProfileModel, v_id);

                    Intent intent = new Intent(AddVillageProfile.this, Main_Menu.class);
                    startActivity(intent);
                } else {
                    image = new String[]{
                            st_image, st_image1, st_image2};
                    uuid = Integer.parseInt(CommonClass.getUUID());
                    villageProfileModel.setPopulation(etPopulation.getText().toString().trim());
                    villageProfileModel.setPradhan_name(etPradhan_name.getText().toString().trim());
                    villageProfileModel.setMobile_no(etContactnumber.getText().toString().trim());
                    villageProfileModel.setVillage_id(Integer.parseInt(village_id));
                    villageProfileModel.setApp_version(String.valueOf(appVersion));
                    villageProfileModel.setState_id(Integer.parseInt(sharedPrefHelper.getString("state_id","")));
                    villageProfileModel.setDistrict_id(Integer.parseInt(sharedPrefHelper.getString("district_id","")));
                    villageProfileModel.setBlock_id(Integer.parseInt(sharedPrefHelper.getString("block_id","")));
                    villageProfileModel.setLongitude(sharedPrefHelper.getString("LONG", ""));
                    villageProfileModel.setLatitude(sharedPrefHelper.getString("LAT", ""));
                    villageProfileModel.setFlag("0");
                    villageProfileModel.setVillage_profile_id(uuid);

                    long ids= sqliteHelper.saveHousehold(villageProfileModel);
                    for (int i = 0; i < 3; i++) {
                        if (!image[i].equals("")) {
                            multipleImagePojo.setVillage_profile_id(String.valueOf(uuid));
                            multipleImagePojo.setVillage_profile_img(image[i]);
                            sqliteHelper.saveVillageImage(multipleImagePojo);
                        }
                    }

                    if(ids>0) {
                        //Toast.makeText(this, "Village detail has been successfully added.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddVillageProfile.this, VillageDetails.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        int villageProId=villageProfileModel.getVillage_profile_id();
                        intent.putExtra("v_id", villageProId);
//                            startActivityForResult(intent, ADD_MEMBER);
                        startActivity(intent);
                    }

//                    if (committee_tableArrayList.size() != 0 && resourceMappingPojoArrayList.size() != 0) {

//                        Intent intent = new Intent(this, List_of_Village_profile.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);

//                    } else {
//                        Toast.makeText(this, "Please Add Members and Resource.", Toast.LENGTH_SHORT).show();
//
//
//                    }
                }
            }

//        }


        });
    }



    private void define() {
        multipleImagePojoArrayList = new ArrayList<>();
        villageProfileModelArrayListData = new ArrayList<>();
        committee_tableArrayList=new ArrayList<>();
        resourceMappingPojoArrayList=new ArrayList<>();
        rvMembers = findViewById(R.id.rvMembers);
        rvResources = findViewById(R.id.rvResources);
        etPopulation = findViewById(R.id.etPopulation);
        etPradhan_name = findViewById(R.id.etPradhan_name);
        etContactnumber = findViewById(R.id.etContactnumber);
        iv_camera = findViewById(R.id.iv_camera);
        iv_camera1 = findViewById(R.id.iv_camera1);
        iv_camera2 = findViewById(R.id.iv_camera2);
        iv_camera3 = findViewById(R.id.iv_camera3);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null) {
//            if (requestCode == CAMERA_REQUEST) {
//                Bitmap photo = (Bitmap) data.getExtras().get("data");
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] bytes = stream.toByteArray();
//
//                base64 = encodeTobase64(photo);
//                if (count == 1) {
//                    iv_camera1.setImageBitmap(photo);
//                    st_image = encodeTobase64(photo);
//                }
//                if (count == 2) {
//                    iv_camera2.setImageBitmap(photo);
//                    st_image1 = encodeTobase64(photo);
//                }
//                if (count == 3) {
//                    iv_camera3.setImageBitmap(photo);
//                    st_image2 = encodeTobase64(photo);
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

    public void onBackPressed() {
        String flag= sqliteHelper.getColumnName("flag","village_profile"," where village_profile_id='"+v_id+"'");
        if(flag.equals("2")) {

            sqliteHelper.deleteData("committee_member", "village_profile_id", String.valueOf(v_id));
            sqliteHelper.deleteData("resource_mapping", "village_profile_id", String.valueOf(v_id));
            sqliteHelper.deleteData("village_profile", "village_profile_id", String.valueOf(v_id));

            Intent intent=new Intent(AddVillageProfile.this, VillageDetails.class);
            startActivity(intent);
        }else{

            Intent intent=new Intent(AddVillageProfile.this, VillageDetails.class);
            startActivity(intent);
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                String flag= sqliteHelper.getColumnName("flag","village_profile"," where village_profile_id='"+v_id+"'");
                if(flag.equals("2")) {

                    sqliteHelper.deleteData("committee_member", "village_profile_id", String.valueOf(v_id));
                    sqliteHelper.deleteData("resource_mapping", "village_profile_id", String.valueOf(v_id));
                    sqliteHelper.deleteData("village_profile", "village_profile_id", String.valueOf(v_id));

                    Intent intent=new Intent(AddVillageProfile.this, VillageDetails.class);
                    startActivity(intent);
                }else{

                    Intent intent=new Intent(AddVillageProfile.this, VillageDetails.class);
                    startActivity(intent);
                }
                // app icon in action bar clicked; go home
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onPostResume() {
        showMember();
        super.onPostResume();
        showResource();
        super.onPostResume();
    }


    private boolean checkValidation() {

        if (etPopulation.getText().toString().trim().length() == 0) {
            etPopulation.setError("Please enter the total population.");
            etPopulation.requestFocus();
            return false;
        }

        if (etPradhan_name.getText().toString().trim().length() == 0) {
            etPradhan_name.setError("Please enter pradhan name");
            etPradhan_name.requestFocus();
            return false;
        }

        if (etContactnumber.getText().toString().trim().length() == 0) {
            etContactnumber.setError("Please enter the total population.");
            etContactnumber.requestFocus();
            return false;
        }

        if (st_image.equals("") && st_image1.equals("") && st_image2.equals("")) {
            Toast.makeText(this, "Please click some Pictures also.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }




    private void showMember(){
        committee_tableArrayList = sqliteHelper.getCommitteeDataInVillage(village_id, String.valueOf(v_id));
        AdapterMemberViewVillageResource adapter = new AdapterMemberViewVillageResource(this, committee_tableArrayList);
        rvMembers.setHasFixedSize(true);
        rvMembers.setLayoutManager(new LinearLayoutManager(this));
        rvMembers.setAdapter(adapter);
    }
    private void showResource(){
        resourceMappingPojoArrayList = sqliteHelper.getResourceMappingData(village_id);
        if (resourceMappingPojoArrayList.size() > 0) {
            AdapterResourceViewVillageResource adapter = new AdapterResourceViewVillageResource(this, resourceMappingPojoArrayList);
            rvResources.setHasFixedSize(true);
            rvResources.setLayoutManager(new LinearLayoutManager(this));
            rvResources.setAdapter(adapter);
        }
    }

    void onProfileImageClick() {
//        Dexter.withActivity(this)
//                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        if (report.areAllPermissionsGranted()) {
                            ImagePicker.with(AddVillageProfile.this)
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
        Intent intent = new Intent(AddVillageProfile.this, ImagePickerActivity.class);
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
        Intent intent = new Intent(AddVillageProfile.this, ImagePickerActivity.class);
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
                        st_image = base64;
                    }
                    if (count == 2) {
                        iv_camera2.setImageBitmap(bitmapp);
                        st_image1 = base64;

                    }
                    if (count == 3) {
                        iv_camera3.setImageBitmap(bitmapp);
                        st_image2 = base64;
                    }
                    if (count == 3) {
//                        binding.ivCamera3.setClickable(false);
                        Toast.makeText(AddVillageProfile.this, "You Can Capture Only Three Image", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AddVillageProfile.this);
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