package com.indev.ruraldevelopment.Activity.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStructureModel;
import com.indev.ruraldevelopment.Activity.Utils.CommonClass;
import com.indev.ruraldevelopment.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AddInfraStructure extends AppCompatActivity {

    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    EditText etNameoftheInfra,etCaretakerName,etAddress,etMobileNo,etAmount, etDescription, etExpected_beneficial;
    TextView tvStartDate;
    Spinner etSiteName;

    Button btnSubmit;
    ImageView iv_camera;
    TextView tv_village;
    String villageId="",sImage="";

    Spinner spnResource;
    ArrayList<String> resourceArrayList;
    HashMap<String, Integer> resourceNameHM;
    int resource_id = 0;
    ArrayList<String> SubresourceArrayList;
    LinkedHashMap<String, Integer> SubresourceNameHM;
    int Subresource_id=0;
    double appVersion = 1.01;
    SqliteHelper sqliteHelper;
    InfraStructureModel infraStructureModel;
    String base64 = "";


    public static final int REQUEST_IMAGE = 100;
    int mYear,mMonth,mDay,year,month,day;

    DatePickerDialog datePickerDialog;
    SharedPrefHelper sharedPrefHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_infra_structure);
        getSupportActionBar().setTitle("Add Infra Structure");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etSiteName = findViewById(R.id.etSiteName);
        etNameoftheInfra = findViewById(R.id.etNameoftheInfra);
        etCaretakerName = findViewById(R.id.etCaretakerName);
        tvStartDate = findViewById(R.id.tvStartDate);
        tv_village = findViewById(R.id.tv_village);
        etMobileNo = findViewById(R.id.etMobileNo);
        etAmount = findViewById(R.id.etAmount);
        etExpected_beneficial = findViewById(R.id.etExpected_beneficial);
        etDescription = findViewById(R.id.etDescription);
        etAddress = findViewById(R.id.etAddress);
        btnSubmit = findViewById(R.id.btnSubmit);
        iv_camera = findViewById(R.id.iv_camera);
        spnResource = findViewById(R.id.spnResource);
        sqliteHelper = new SqliteHelper(this);
        resourceArrayList = new ArrayList<>();
        SubresourceArrayList = new ArrayList<>();
        SubresourceNameHM = new LinkedHashMap<>();
        resourceNameHM = new HashMap<>();
        getResourceSpinner();



        infraStructureModel = new InfraStructureModel();
        sharedPrefHelper = new SharedPrefHelper(this);
        tv_village.setText(sharedPrefHelper.getString("village_id", ""));
         String village=sqliteHelper.getColumnName("village_name","village"," where village_id='"+sharedPrefHelper.getString("village_id", "")+"'");
        tv_village.setText(village);

//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
//        String formattedDate = df.format(c.getTime());
//        tvStartDate.setText(formattedDate);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdfsss = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(calendar.getTime());
        String currentDateddd = sdfsss.format(calendar.getTime());
        tvStartDate.setText(currentDate);

//        tvStartDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvStartDate.setError(null);
//                tvStartDate.clearFocus();
//                mYear = year;
//                mMonth = month;
//                mDay = day;
//
//                final Calendar c = Calendar.getInstance();
//                c.add(Calendar.DATE, -1);  //do not set past date
//                mYear = c.get(Calendar.YEAR); // current year
//                mMonth = c.get(Calendar.MONTH); // current month
//                mDay = c.get(Calendar.DAY_OF_MONTH); // current day
//                datePickerDialog = new DatePickerDialog(AddInfraStructure.this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                String nd = "" + dayOfMonth;
//                                String nm = "" + monthOfYear ;
//                                if ( dayOfMonth < 10 ){
//                                    nd = "0"+ dayOfMonth;
//                                }
//
//                                if ( (monthOfYear + 1) < 10){
//                                    nm = "0"+ ( monthOfYear +1 ) ;
//                                }else {
//                                    nm = ""+ ( monthOfYear + 1) ;
//                                }
//                                Log.d("testValue" , String.valueOf( monthOfYear ));
//
//                                // for button text
//                                tvStartDate.setText( ( year + "-" +  nm + "-" + nd));
//                            }
//                        }, mYear, mMonth, mDay);
//                datePickerDialog.show();
//                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
//                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
//                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ff173e6d"));
//            }
//        });

        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onProfileImageClick();
//                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cInt, CAMERA_REQUEST);

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation()) {
                    infraStructureModel.setInfra_id(Integer.parseInt(CommonClass.getUUID()));
                    infraStructureModel.setResource_id(resource_id);
                    infraStructureModel.setSite_name(String.valueOf(Subresource_id));
                    infraStructureModel.setSubresource_id(String.valueOf(Subresource_id));
                    infraStructureModel.setInfra_structure_name(etNameoftheInfra.getText().toString().trim());
                    infraStructureModel.setCaretaker(etCaretakerName.getText().toString().trim());
                    infraStructureModel.setAddress(etAddress.getText().toString().trim());
                    infraStructureModel.setAmount(etAmount.getText().toString().trim());
                    infraStructureModel.setMobile_no(etMobileNo.getText().toString().trim());
                    infraStructureModel.setStart_date(currentDateddd);
                    infraStructureModel.setExpected_beneficial(etExpected_beneficial.getText().toString().trim());
                    infraStructureModel.setDescription(etDescription.getText().toString().trim());
                    infraStructureModel.setApp_version(String.valueOf(appVersion));
                    infraStructureModel.setUser_id(Integer.parseInt(sharedPrefHelper.getString("user_id","")));
                    infraStructureModel.setVillage_id(Integer.parseInt(sharedPrefHelper.getString("village_id", "")));
                    infraStructureModel.setState_id(Integer.parseInt(sharedPrefHelper.getString("state_id","")));
                    infraStructureModel.setDistrict_id(Integer.parseInt(sharedPrefHelper.getString("district_id","")));
                    infraStructureModel.setBlock_id(Integer.parseInt(sharedPrefHelper.getString("block_id","")));
                    infraStructureModel.setLongitude(sharedPrefHelper.getString("LONG", ""));
                    infraStructureModel.setLatitude(sharedPrefHelper.getString("LAT", ""));
                    infraStructureModel.setImage(base64);
                    sqliteHelper.saveHousehold(infraStructureModel);

                    Intent intent = new Intent(AddInfraStructure.this, List_of_InfraStructure.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
            }
        });

        ImagePickerActivity.clearCache(this);
    }

//    private void SetCurrentDate() {
//        Current Date Set
//        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        date = dateFormat.format(calendar.getTime());
//        tvStartDate.setText(date);
//    }
//






    private void getResourceSpinner() {
        resourceArrayList.clear();
        resourceNameHM= sqliteHelper.Downloadinfra_resourceData();
        for (int i = 0; i < resourceNameHM.size(); i++) {
            resourceArrayList.add(resourceNameHM.keySet().toArray()[i].toString().trim());
        }
        Collections.sort(resourceArrayList, String.CASE_INSENSITIVE_ORDER);
        resourceArrayList.add(0, "Select Resource Type");
        //state spinner choose
        ArrayAdapter adapterEducation = new ArrayAdapter(AddInfraStructure.this, android.R.layout.simple_spinner_item, resourceArrayList);
        adapterEducation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnResource.setAdapter(adapterEducation);
//        Bundle bundle = getIntent().getExtras();

//        if (screen_type.equals("edit_profile")) {
//            st_state = sqliteHelper.getPSState(CandidatePojo.getState_id());
//            int pos = state_adapter.getPosition(st_state);
//            spn_State.setSelection(pos);
//        }

        spnResource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!spnResource.getSelectedItem().toString().trim().equalsIgnoreCase("Select Resource Type")) {
                    if (spnResource.getSelectedItem().toString().trim() != null) {
                        resource_id = resourceNameHM.get(spnResource.getSelectedItem().toString().trim());
                        sharedPrefHelper.getString("resource",spnResource.getSelectedItem().toString().trim());
                        getSppinar();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void getSppinar() {

        SubresourceArrayList.clear();

        SubresourceNameHM = sqliteHelper.getDataResource(String.valueOf(resource_id));
        for (int i = 0; i < SubresourceNameHM.size(); i++) {
            SubresourceArrayList.add(SubresourceNameHM.keySet().toArray()[i].toString().trim());
        }
//        Collections.sort(SubresourceArrayList, String.CASE_INSENSITIVE_ORDER);

        SubresourceArrayList.add(0, "Select Sub Resource Type");

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SubresourceArrayList);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etSiteName.setAdapter(arrayAdapter2);
//        ID = 0;
        etSiteName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!etSiteName.getSelectedItem().toString().trim().equalsIgnoreCase("Select Sub Resource Type")) {
                        Subresource_id =SubresourceNameHM.get(etSiteName.getSelectedItem().toString().trim());
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
//        if (requestCode == CAMERA_REQUEST) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//            byte[] bytes = stream.toByteArray();
//            base64 = encodeTobase64(photo);
//            iv_camera.setImageBitmap(photo);
//        }
//
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




    private boolean checkValidation() {

        if (spnResource.getSelectedItem().toString().equals("Select Resource Type")) {
            Toast.makeText(getApplicationContext(), "Please select Resource Type", Toast.LENGTH_LONG).show();
            return false;
        }
        if (etSiteName.getSelectedItem().toString().equals("Select Sub Resource Type")) {
            Toast.makeText(getApplicationContext(), "Please select Sub Resource Type", Toast.LENGTH_LONG).show();
            return false;
        }

//        if (etSiteName.getSelectedItemId() == 0) {
//            etSiteName.setError("Please enter site name");
//            etSiteName.requestFocus();
//            return false;
//        }


        if (etNameoftheInfra.getText().toString().trim().length() == 0) {
            etNameoftheInfra.setError("Please enter Infra name");
            etNameoftheInfra.requestFocus();
            return false;
        }

        if (etCaretakerName.getText().toString().trim().length() == 0) {
            etCaretakerName.setError("Please fill the caretaker name");
            etCaretakerName.requestFocus();
            return false;
        }


        if (etMobileNo.getText().toString().trim().length() < 10) {
            EditText flagEditfield = etMobileNo;
            String msg = getString(R.string.Please_Enter_Mobile_Number);
            etMobileNo.setError(msg);
            etMobileNo.requestFocus();
            return false;
        }

        if (tvStartDate.getText().toString().trim().length() == 0) {
            tvStartDate.setError("Please enter the starting date");
            tvStartDate.requestFocus();
            return false;
        }
//        if (tvEndDate.getText().toString().trim().length() == 0) {
//            tvEndDate.setError("Please enter the ending date");
//            tvEndDate.requestFocus();
//            return false;
//        }

        if (etAddress.getText().toString().trim().length() == 0) {
            etAddress.setError("Please fill the address");
            etAddress.requestFocus();
            return false;
        }

        if (etAmount.getText().toString().trim().length() == 0) {
            etAmount.setError("Please enter amount");
            etAmount.requestFocus();
            return false;
        }


//        if (etExpected_beneficial.getText().toString().trim().length() == 0) {
//            etExpected_beneficial.setError("Please enter expected beneficial");
//            etExpected_beneficial.requestFocus();
//            return false;
//        }
        if (etDescription.getText().toString().trim().length() == 0) {
            etDescription.setError("Please enter the description");
            etDescription.requestFocus();
            return false;
        }

        if (base64.equals("")) {
            Toast.makeText(this, "Please click some Pictures also.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

//        if (etName.getText().toString().trim().length() == 0) {
//            etName.setError("Please enter 10 digit phone number");
//            etName.requestFocus();
//            return false;
//        }
//        if (etRole.getSelectedItem().toString().equals("Select Resource Type")) {
//            Toast.makeText(getApplicationContext(), "Please select category", Toast.LENGTH_LONG).show();
//            return false;
//        }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
//
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//


    void onProfileImageClick() {
//        Dexter.withActivity(this)
//                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        if (report.areAllPermissionsGranted()) {
                            ImagePicker.with(AddInfraStructure.this)
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
        Intent intent = new Intent(AddInfraStructure.this, ImagePickerActivity.class);
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
        Intent intent = new Intent(AddInfraStructure.this, ImagePickerActivity.class);
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

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddInfraStructure.this);
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