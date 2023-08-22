package com.indev.ruraldevelopment.Activity.Activity;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.StakeHolderSurveyModel;
import com.indev.ruraldevelopment.R;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServeyView extends AppCompatActivity {
    TextView tvResourcetype, person_name, degignation, servey_holder_dates, stakeholder_category, heard_about, employee_discussed, equipped_to_manage, mock_drill, discussion_conduct,remark;
    String resource_id, resourece_name, pName, pDesignation, serveyHolderDate, stackCategory, heard_About, employee_Discussed, equipped_to_Manage, mock_Drill, discussion_Conduct, str_remark;
    String img="";
    ZoomageView image;
    String imageView="";
    int surveyId;
    SqliteHelper sqliteHelper;
    SharedPrefHelper sharedPreferences;
    StakeHolderSurveyModel stakeHolderSurveyModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servey_view);
        setTitle("Stakeholder Survey Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

 //       tvResourcetype=findViewById(R.id.tvResourceType);
        person_name=findViewById(R.id.person_name);
        degignation = findViewById(R.id.degignation);
        servey_holder_dates = findViewById(R.id.servey_dates);
        stakeholder_category = findViewById(R.id.stakeholder_category);
        heard_about = findViewById(R.id.heard_about);
        employee_discussed = findViewById(R.id.employee_discussed);
        equipped_to_manage = findViewById(R.id.equipped_to_manage);
        mock_drill = findViewById(R.id.mock_drill);
        remark = findViewById(R.id.remark);
        discussion_conduct = findViewById(R.id.discussion_conduct);
        stakeHolderSurveyModel = new StakeHolderSurveyModel();

        image = findViewById(R.id.stakeHolderImage);

        sqliteHelper = new SqliteHelper(this);
        sharedPreferences = new SharedPrefHelper(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            surveyId = bundle.getInt("survey_id", 0);
            pName = bundle.getString("person_name", "");
            pDesignation = bundle.getString("designation", "");
            serveyHolderDate = bundle.getString("date_of_survey", "");
            str_remark =  bundle.getString("remark");
            stackCategory = bundle.getString("stakeholder_category_id", "");
            heard_About = bundle.getString("heard_about_jubliant_life_science");
            employee_Discussed = bundle.getString("employee_discussed_about_company_activity");
            equipped_to_Manage = bundle.getString("equipped_to_manage_operation");
            mock_Drill = bundle.getString("mock_drill_related_to_emergency");
            discussion_Conduct = bundle.getString("discussion_conduct_for_concern");

            heard_About =  bundle.getString("heard_about_jubliant_life_science");
            employee_Discussed =  bundle.getString("employee_discussed_about_company_activity");
            equipped_to_Manage =  bundle.getString("equipped_to_manage_operation");
            mock_Drill =  bundle.getString("mock_drill_related_to_emergency");
            discussion_Conduct =  bundle.getString("discussion_conduct_for_concern");
//            img = bundle.getString("image", "");
            img=sharedPreferences.getString("images","");

        }

//        resource_id=sharedPreferences.getString("resourceType_id", "");

  //      resourece_name = sqliteHelper.getColumnName("resource_name", "resource", " where resource_id ='" + resource_id + "'");
        stackCategory = sqliteHelper.getColumnName("stakeholder_category_name", "stakeholder_category", " where stakeholder_category_id ='" + stackCategory + "'");

//        tvResourcetype.setText(" : " +resourece_name);
        imageView = sqliteHelper.getColumnName("image", "stakeholder_survey", " where survey_id='" + surveyId + "'");
        person_name.setText(" : " +pName);
        degignation.setText(" : " +pDesignation);
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        String inputDateStr=serveyHolderDate;
        Date date2 = null;
        try {
            date2 = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date2);
        servey_holder_dates.setText(" : " +outputDateStr);

        stakeholder_category.setText(" : " +stackCategory);
        remark.setText("  "+str_remark);
        heard_about.setText(" " +heard_About);
        employee_discussed.setText("  " +employee_Discussed);
        equipped_to_manage.setText(" " +equipped_to_Manage);
        mock_drill.setText("  " +mock_Drill);
        discussion_conduct.setText("  " +discussion_Conduct);

        if (img != null && img.length() > 200) {
            byte[] decodedString = Base64.decode(img, Base64.NO_WRAP);
            InputStream inputStream = new ByteArrayInputStream(decodedString);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            image.setImageBitmap(bitmap);
        }else{
                try {
                    Picasso.get()
                            .load(BASE_URL5 + imageView)
                            .placeholder(R.drawable.infrastructure_development)
                            .into(image);
                } catch (Exception e) {
                    Log.d("Exception", "" + e);
                }
            }
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
}