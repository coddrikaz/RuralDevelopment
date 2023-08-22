package com.indev.ruraldevelopment.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.StakeHolderSurveyModel;
import com.indev.ruraldevelopment.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;

public class StakeHolderSurvey extends AppCompatActivity {
 //   private static final int REQUEST_IMAGE = 1888;
    private static final int CAMERA_REQUEST = 1888;
    int mYear,mMonth,mDay,year,month,day;
    DatePickerDialog datePickerDialog;

    Spinner spnResource;
    TextView tv_village;
    ArrayList<String> resourceArrayList;
    HashMap<String, Integer> resourceNameHM;
    int resource_id = 0;
    double appVersion = 1.01;
    String DateOfSurvey="";


    Spinner spn_stackholder;
    //Text View
    TextView servey_holder_date;
    //Edit text
    EditText servey_holder_person_name,servey_holder_designation,remark;
    //Radio Button
    RadioButton servey_strongly_agree1, servey_agree1, servey_disagree1, servey_strongly_disagree1;
    RadioButton servey_strongly_agree2, servey_agree2,servey_disagree2, servey_strongly_disagree2;
    RadioButton servey_strongly_agree3, servey_agree3,servey_disagree3, servey_strongly_disagree3;
    RadioButton servey_strongly_agree4, servey_agree4,servey_disagree4, servey_strongly_disagree4;
    RadioButton servey_strongly_agree5, servey_agree5,servey_disagree5, servey_strongly_disagree5;

 //   String[] statackHolderCategory = {"Select Category Stakholder", "Local Community", "Local Administration", "Industry","Media","NGOs","Panchayati Raj Instritution(PRIs)","Opinion Leader","Employee","Other"};
    String base64,mCategory="",mRadioButton1="", mRadioButton2="",mRadioButton3="", mRadioButton4="", mRadioButton5="" ;
    SqliteHelper sqliteHelper;
    StakeHolderSurvey stakeHolderSurvey;
    SharedPrefHelper sharedPrefHelper;
    Spinner spnFinancialYear;
 //   ArrayList<StakeHolderSurvey> stakeHolderSurveyArrayList;
    ImageView iv_camera1;
    Button btnSubmit;
    ArrayList<String> categoryArrayList ;
    HashMap<String, Integer> categoryNameHM;
    int category_id = 0;
    String sImage="";
    String[] financialYear={"2022-2023","2023-2024","2024-2025"};

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stake_holder_survey);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Stakeholder Survey Form");
        spnFinancialYear=findViewById(R.id.spnFinancialYear);
        //Variable initialization method calling....


        ArrayAdapter arrayStatus = new ArrayAdapter(StakeHolderSurvey.this, android.R.layout.simple_spinner_item, financialYear);
        arrayStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFinancialYear.setAdapter(arrayStatus);
//
//        Bundle bundle = getIntent().getExtras();
//        if(bundle!=null){
//            village =bundle.getString("village_name", "");
//
////
//        }



        intialization();
        sqliteHelper=new SqliteHelper(getApplicationContext());
        getCategorySpinner();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdfsss = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(calendar.getTime());
        String currentDateddd = sdfsss.format(calendar.getTime());
        sharedPrefHelper.setString("date",currentDate);
        servey_holder_date.setText(currentDate);

//        tvStartDate.setText(currentDate);
//        Date date = Calendar.getInstance().getTime();
//
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
//        String formattedDate = df.format(date);
//        sharedPrefHelper.setString("date",formattedDate);
//        servey_holder_date.setText(formattedDate);
        //Set Current Date
        DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String inputDateStr=currentDate;
        Date date2 = null;
        try {
            date2 = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date2);
        DateOfSurvey=outputDateStr;


        spnResource = findViewById(R.id.spnResource);
        resourceArrayList = new ArrayList<>();
        resourceNameHM = new HashMap<>();

 //       getResourceSpinner();

        //database call
        stakeHolderSurvey = new StakeHolderSurvey();
        tv_village.setText(sharedPrefHelper.getString("village_id", ""));
        String village=sqliteHelper.getColumnName("village_name","village"," where village_id='"+sharedPrefHelper.getString("village_id", "")+"'");
        tv_village.setText(village);




        //getRadioButton();
        //Date picker calling method........
//        servey_holder_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                servey_holder_date.setError(null);
//                servey_holder_date.clearFocus();
//                mYear = year;
//                mMonth = month;
//                mDay = day;
//
//                final Calendar c = Calendar.getInstance();
//                c.add(Calendar.DATE, 0);  //do not set past date
//                mYear = c.get(Calendar.YEAR); // current year
//                mMonth = c.get(Calendar.MONTH); // current month
//                mDay = c.get(Calendar.DAY_OF_MONTH); // current day
//                 datePickerDialog = new DatePickerDialog(StakeHolderSurvey.this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                String nd = "" + dayOfMonth;
//                                String nm = "" + monthOfYear ;
//                                if ( dayOfMonth < 10 ){
//                                    nd = "0"+ dayOfMonth;
//                                }
//
//
//                                if ( (monthOfYear + 1) < 10){
//                                    nm = "0"+ ( monthOfYear +1 ) ;
//                                }else {
//                                    nm = ""+ ( monthOfYear + 1) ;
//                                }
//                                Log.d("testValue" , String.valueOf( monthOfYear ));
//
//                                // for button text
//                                DateOfSurvey=year + "-" +  nm + "-" + nd;
//                                servey_holder_date.setText( ( nd + "-" +  nm + "-" + year));
//                                sharedPrefHelper.setString("date",( nd + "-" +  nm + "-" + year) );
////                                servey_holder_date.setText( ( year + "-" +  nm + "-" + nd));
//                            }
//                        }, mYear, mMonth, mDay);
//                datePickerDialog.show();
//                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
//                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
//                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ff173e6d"));
//            }
//        });

//        servey_holder_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                servey_holder_date.setError(null);
//                servey_holder_date.clearFocus();
//                mYear=year;
//                mMonth=month;
//                mDay=day;
//
//                final Calendar c = Calendar.getInstance();
//                mYear = c.get(Calendar.YEAR); // current year
//                mMonth = c.get(Calendar.MONTH); // current month
//                mDay = c.get(Calendar.DAY_OF_MONTH); // current day
//                datePickerDialog = new DatePickerDialog(StakeHolderSurvey.this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                servey_holder_date.setText("" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//
//                            }
//                        }, mYear, mMonth, mDay);
//                datePickerDialog.show();
//                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
//                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ff173e6d"));
//            }
//        });

        //Camra button.....
        iv_camera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefHelper.setString("mRadio1",mRadioButton1);
                sharedPrefHelper.setString("mRadio2",mRadioButton2);
                sharedPrefHelper.setString("mRadio3",mRadioButton3);
                sharedPrefHelper.setString("mRadio4",mRadioButton4);
                sharedPrefHelper.setString("mRadio5",mRadioButton5);
                ImagePicker.with(StakeHolderSurvey.this)
                        .cameraOnly()
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080,1080)//User can only select image from Gallery
                        .start();

                //               onProfileImageClick();
            }
        });

        //Submit button call
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    StakeHolderSurveyModel stakeHolderSurveyModel = new StakeHolderSurveyModel();
 //                   stakeHolderSurveyModel.setResource_id(resource_id);
                    stakeHolderSurveyModel.setDate_of_survey(DateOfSurvey);
                    stakeHolderSurveyModel.setFinancial_year(spnFinancialYear.getSelectedItem().toString().trim());
                    stakeHolderSurveyModel.setPerson_name(servey_holder_person_name.getText().toString().trim());
                    stakeHolderSurveyModel.setDesignation(servey_holder_designation.getText().toString().trim());
                    stakeHolderSurveyModel.setRemark(remark.getText().toString().trim());
                   // stakeHolderSurveyModel.setStakeholder_category_id(mCategory.trim());
                    stakeHolderSurveyModel.setStakeholder_category_id(String.valueOf(category_id));
                    stakeHolderSurveyModel.setHeard_about_jubliant_life_science(mRadioButton1);
                    stakeHolderSurveyModel.setEmployee_discussed_about_company_activity(mRadioButton2);
                    stakeHolderSurveyModel.setEquipped_to_manage_operation(mRadioButton3);
                    stakeHolderSurveyModel.setMock_drill_related_to_emergency(mRadioButton4);
                    stakeHolderSurveyModel.setDiscussion_conduct_for_concern(mRadioButton5);

                    stakeHolderSurveyModel.setApp_version(String.valueOf(appVersion));

                    stakeHolderSurveyModel.setUser_id(Integer.parseInt(sharedPrefHelper.getString("user_id","")));
                    stakeHolderSurveyModel.setVillage_id(Integer.parseInt(sharedPrefHelper.getString("village_id", "")));
                    stakeHolderSurveyModel.setState_id(Integer.parseInt(sharedPrefHelper.getString("state_id","")));
                    stakeHolderSurveyModel.setDistrict_id(Integer.parseInt(sharedPrefHelper.getString("district_id","")));
                    stakeHolderSurveyModel.setBlock_id(Integer.parseInt(sharedPrefHelper.getString("block_id","")));
                    stakeHolderSurveyModel.setLongitude(sharedPrefHelper.getString("LONG", ""));
                    stakeHolderSurveyModel.setLatitude(sharedPrefHelper.getString("LAT", ""));

                    stakeHolderSurveyModel.setImage(base64);
//                sqliteHelper.saveHouseholdR(stakeHolderSurveyModel);
                    if (sqliteHelper.saveHouseholdR(stakeHolderSurveyModel) > 0) {
                       // Toast.makeText(StakeHolderSurvey.this, "insert data into table", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(StakeHolderSurvey.this, List_of_stakeholder_survey.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                }

                }
        });
//
//        ArrayAdapter<String> gender_aArrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,statackHolderCategory);
//        gender_aArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spn_stackholder.setAdapter(gender_aArrayAdapter);
//
//        spn_stackholder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                if(position!=0)
//                {
//                    mCategory=spn_stackholder.getSelectedItem().toString().trim();
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });
        //Radio Button
        servey_strongly_agree1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton1="Strongly Agree";
            }
        });
        servey_agree1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton1="Agree";
            }
        });
        servey_disagree1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton1="Disagree";
            }
        });
        servey_strongly_disagree1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton1="Strongly Disagree";
            }
        });

        //Radio Button Second
        servey_strongly_agree2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton2="Strongly Agree";
            }
        });
        servey_agree2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton2="Agree";
            }
        });
        servey_disagree2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton2="Disagree";
            }
        });
        servey_strongly_disagree2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton2="Strongly Disagree";
            }
        });

        //Radio Button Third
        servey_strongly_agree3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton3="Strongly Agree";
            }
        });
        servey_agree3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton3="Agree";
            }
        });
        servey_disagree3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton3="Disagree";
            }
        });
        servey_strongly_disagree3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton3="Strongly Disagree";
            }
        });


        //Radio Button Four
        servey_strongly_agree4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton4="Strongly Agree";
            }
        });
        servey_agree4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton4="Agree";
            }
        });
        servey_disagree4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton4="Disagree";
            }
        });
        servey_strongly_disagree4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton4="Strongly Disagree";
            }
        });

        //Radio Button Five
        servey_strongly_agree5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton5="Strongly Agree";
            }
        });
        servey_agree5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton5="Agree";
            }
        });
        servey_disagree5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton5="Disagree";
            }
        });
        servey_strongly_disagree5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton5="Strongly Disagree";
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
//        ArrayAdapter adapterEducation = new ArrayAdapter(StakeHolderSurvey.this, android.R.layout.simple_spinner_item, resourceArrayList);
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

    private void intialization() {
        servey_holder_date=findViewById(R.id.servey_holder_date);
        servey_holder_person_name=findViewById(R.id.servey_holder_person_name);
        servey_holder_designation=findViewById(R.id.servey_holder_designation);
        spn_stackholder=findViewById(R.id.spn_stackholder);
        remark=findViewById(R.id.remark);
        tv_village= findViewById(R.id.tv_village);

        servey_strongly_agree1=findViewById(R.id.servey_strongly_agree);
        servey_agree1=findViewById(R.id.servey_agree);
        servey_disagree1=findViewById(R.id.servey_disagree);
        servey_strongly_disagree1=findViewById(R.id.servey_strongly_disagree);

        servey_strongly_agree2=findViewById(R.id.servey_strongly_agree1);
        servey_agree2=findViewById(R.id.servey_agree1);
        servey_disagree2=findViewById(R.id.servey_disagree1);
        servey_strongly_disagree2=findViewById(R.id.servey_strongly_disagree1);

        servey_strongly_agree3=findViewById(R.id.servey_strongly_agree2);
        servey_agree3=findViewById(R.id.servey_agree2);
        servey_disagree3=findViewById(R.id.servey_disagree2);
        servey_strongly_disagree3=findViewById(R.id.servey_strongly_disagree2);

        servey_strongly_agree4=findViewById(R.id.servey_strongly_agree3);
        servey_agree4=findViewById(R.id.servey_agree3);
        servey_disagree4=findViewById(R.id.servey_disagree3);
        servey_strongly_disagree4=findViewById(R.id.servey_strongly_disagree3);

        servey_strongly_agree5=findViewById(R.id.servey_strongly_agree4);
        servey_agree5=findViewById(R.id.servey_agree4);
        servey_disagree5=findViewById(R.id.servey_disagree4);
        servey_strongly_disagree5=findViewById(R.id.servey_strongly_disagree4);

        iv_camera1=findViewById(R.id.iv_camera1);


        btnSubmit=findViewById(R.id.btnSubmit);
        categoryNameHM = new HashMap<>();
        categoryArrayList = new ArrayList<>();
        sqliteHelper = new SqliteHelper(this);
        sharedPrefHelper= new SharedPrefHelper(this);


    }




    //camra
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            photo.compress(Bitmap.CompressFormat.JPEG, 40, stream);
//            byte[] bytes = stream.toByteArray();
//            base64 = encodeTobase64(photo);
//            iv_camera1.setImageBitmap(photo);
//        }
//
//    }
//    //camra
//
//    private String encodeTobase64(Bitmap image) {
//        ByteArrayOutputStream byteArrayOS = null;
//        try {
//            System.gc();
//            byteArrayOS = new ByteArrayOutputStream();
//            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOS);
//        } catch (OutOfMemoryError e) {
//            e.printStackTrace();
//            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOS);
//        }
//        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.NO_WRAP);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    iv_camera1.setImageBitmap(bitmapp);
                    base64 = encodeTobase64(bitmapp);
                    servey_holder_date.setText(sharedPrefHelper.getString("date", ""));
                    mRadioButton1=sharedPrefHelper.getString("mRadio1","");
                    mRadioButton2=sharedPrefHelper.getString("mRadio2","");
                    mRadioButton3=sharedPrefHelper.getString("mRadio3","");
                    mRadioButton4=sharedPrefHelper.getString("mRadio4","");
                    mRadioButton5=sharedPrefHelper.getString("mRadio5","");

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


    private void getCategorySpinner() {

        categoryArrayList.clear();
        categoryNameHM = sqliteHelper.getCategory();
        for (int i = 0; i < categoryNameHM.size(); i++) {
            categoryArrayList.add(categoryNameHM.keySet().toArray()[i].toString().trim());
        }
        Collections.sort(categoryArrayList, String.CASE_INSENSITIVE_ORDER);
//        Collections.sort(blockArrayList);
        categoryArrayList.add(0, "Select Stakeholder Category");
        //state spinner choose
        ArrayAdapter adapter = new ArrayAdapter(StakeHolderSurvey.this, android.R.layout.simple_spinner_item, categoryArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_stackholder.setAdapter(adapter);
//        Bundle bundle = getIntent().getExtras();

//        if (screen_type.equals("edit_profile")) {
//            st_state = sqliteHelper.getPSState(CandidatePojo.getState_id());
//            int pos = state_adapter.getPosition(st_state);
//            spn_State.setSelection(pos);
//        }

        spn_stackholder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!spn_stackholder.getSelectedItem().toString().trim().equalsIgnoreCase("Select Stakeholder Category")) {
                    if (spn_stackholder.getSelectedItem().toString().trim() != null) {
                        category_id = categoryNameHM.get(spn_stackholder.getSelectedItem().toString().trim());

                    }
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private boolean validation() {

//        if (spnResource.getSelectedItemPosition() > 0) {
//            String itemvalue = String.valueOf(spnResource.getSelectedItem());
//        } else {
//            TextView errorTextview = (TextView) spnResource.getSelectedView();
//            errorTextview.setError("Error");
//            errorTextview.requestFocus();
//            return false;
//        }

        if (servey_holder_date.getText().toString().trim().length() == 0) {
            servey_holder_date.setError("Please enter the starting date");
            servey_holder_date.requestFocus();
            return false;
        }

        if (servey_holder_person_name.getText().toString().trim().length() == 0) {
            servey_holder_person_name.setError("Please enter person name!");
            servey_holder_person_name.requestFocus();
            return false;
        }

//        if (spn_stackholder.getText().toString().trim().length() == 0) {
//            spn_stackholder.setError("Please enter designation!");
//            spn_stackholder.requestFocus();
//            return false;
//        }

        if (servey_holder_designation.getText().toString().trim().length() == 0) {
            servey_holder_designation.setError("Please enter occupation!");
            servey_holder_designation.requestFocus();
            return false;
        }

        if (spn_stackholder.getSelectedItemPosition() > 0) {
            String itemvalue = String.valueOf(spn_stackholder.getSelectedItem());
        } else {
            TextView errorTextview = (TextView) spn_stackholder.getSelectedView();
            errorTextview.setError("Error");
            errorTextview.requestFocus();
            return false;
        }


        if(servey_strongly_agree1.isChecked() || servey_agree1.isChecked() || servey_disagree1.isChecked() || servey_strongly_disagree1.isChecked())
        {
        }else{
            Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(servey_strongly_agree2.isChecked() || servey_agree2.isChecked() || servey_disagree2.isChecked() || servey_strongly_disagree2.isChecked())
        {

        }else{
            Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(servey_strongly_agree3.isChecked() || servey_agree3.isChecked() || servey_disagree3.isChecked() || servey_strongly_disagree3.isChecked())
        { }else{
            Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
            return false;

        }

        if(servey_strongly_agree4.isChecked() || servey_agree4.isChecked() || servey_disagree4.isChecked() || servey_strongly_disagree4.isChecked())
        {

        }else{
            Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
            return false;

        }

        if(servey_strongly_agree5.isChecked() || servey_agree5.isChecked() || servey_disagree5.isChecked() || servey_strongly_disagree5.isChecked())
        {

        }else{
            Toast.makeText(getApplicationContext(), "Please choose one option. ", Toast.LENGTH_SHORT).show();
            return false;

        }

        if (base64.equals("")) {
            Toast.makeText(this, "Please click some Pictures also.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
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

//    void onProfileImageClick() {
//        Dexter.withActivity(this)
//                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        if (report.areAllPermissionsGranted()) {
//                            showImagePickerOptions();
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
//    }

//    private void showImagePickerOptions() {
//        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
//            @Override
//            public void onTakeCameraSelected() {
//                launchCameraIntent();
//            }
//
////            @Override
////            public void onChooseGallerySelected() {
////                launchGalleryIntent();
////            }
//        });
//    }

//    private void launchCameraIntent() {
//        Intent intent = new Intent(StakeHolderSurvey.this, ImagePickerActivity.class);
//        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);
//
//        // setting aspect ratio
//        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
//        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
//        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
//
//        // setting maximum bitmap width and height
//        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
//        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
//        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);
//
//        startActivityForResult(intent, REQUEST_IMAGE);
//    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(StakeHolderSurvey.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
  //      startActivityForResult(intent, REQUEST_IMAGE);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE) {
//            if (resultCode == Activity.RESULT_OK) {
//                Uri uri = data.getParcelableExtra("path");
//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    // compress Bitmap
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
//                    // Initialize byte array
//                    byte[] bytes = stream.toByteArray();
//                    // get base64 encoded string
//                    base64 = Base64.encodeToString(bytes, Base64.DEFAULT);
//                    byte[] bytess=Base64.decode(base64,Base64.DEFAULT);
//                    // Initialize bitmap
//                    Bitmap bitmapp= BitmapFactory.decodeByteArray(bytess,0,bytes.length);
//                    // set bitmap on imageView
//                    base64=encodeTobase64(bitmapp);
//                    iv_camera1.setImageBitmap(bitmapp);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
//    private void showSettingsDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(StakeHolderSurvey.this);
//        builder.setTitle(getString(R.string.dialog_permission_title));
//        builder.setMessage(getString(R.string.dialog_permission_message));
//        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
//            dialog.cancel();
//            openSettings();
//        });
//        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
//        builder.show();
//
//    }

    // navigating user to app settings
//    private void openSettings() {
//        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        Uri uri = Uri.fromParts("package", getPackageName(), null);
//        intent.setData(uri);
//        startActivityForResult(intent, 101);
//    }

}



