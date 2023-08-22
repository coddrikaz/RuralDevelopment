package com.indev.ruraldevelopment.Activity.Activity;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL2;
import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL3;
import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL7;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.indev.ruraldevelopment.Activity.Adapter.MonitorSliderAdapter;
import com.indev.ruraldevelopment.Activity.Adapter.ResourceSliderAdapter;
import com.indev.ruraldevelopment.Activity.Adapter.TrainingSliderAdapter;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStructureModel;
import com.indev.ruraldevelopment.Activity.Model.MonitorMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.TrainingModel;
import com.indev.ruraldevelopment.Activity.Model.TrainingMultipleImagePojo;
import com.indev.ruraldevelopment.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class TrainingView extends AppCompatActivity {
    TextView  tv_training_name, tv_trainer_name, tv_Start_Date, tv_attendance, tv_male, tv_feMale,tv_objective;
    String trainingname,resource_type,startdate,male,female,trainerName,totalAttendance,objective,img;
    int resource_id=0;
    TrainingModel trainingModel;
    SqliteHelper sqliteHelper;
    SharedPrefHelper sharedPreferences;
    ArrayList<TrainingMultipleImagePojo> trainingMultipleImagePojoArrayList;
    int trainingId;
    SliderView sliderView;
    String imageView="";
    int unique_id;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Training Details");

        sliderView = findViewById(R.id.training_image_slider);
 //       tvResource = findViewById(R.id.tvResource);
        tv_training_name = findViewById(R.id.tv_training_name);
        tv_Start_Date = findViewById(R.id.tv_Start_Date);
        tv_trainer_name = findViewById(R.id.tv_trainer_name);
        tv_male = findViewById(R.id.tv_male);
        tv_feMale = findViewById(R.id.tv_feMale);
        tv_attendance = findViewById(R.id.tv_attendance);

        tv_objective = findViewById(R.id.tv_objective);
        trainingModel = new TrainingModel();
        sqliteHelper = new SqliteHelper(this);
        sharedPreferences = new SharedPrefHelper(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            unique_id = bundle.getInt("training_id",0);
            trainingname = bundle.getString("training_name", "");
 //           resource_id = bundle.getInt("resource_id", 0);
            startdate = bundle.getString("start_date", "");
            trainerName = bundle.getString("trainer_name", "");
            male = bundle.getString("male", "");
            female = bundle.getString("female", "");
            totalAttendance = bundle.getString("total_attendance", "");
            objective = bundle.getString("objective", "");
            img = bundle.getString("image", "");
        }


        imageView = sqliteHelper.getColumnName("image", "training", " where training_id='" + unique_id + "'");

        trainingMultipleImagePojoArrayList = sqliteHelper.getTrainingImage(String.valueOf(unique_id));
        TrainingSliderAdapter trainingSliderAdapter = new TrainingSliderAdapter(trainingMultipleImagePojoArrayList);

        sliderView.setSliderAdapter(trainingSliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

//        tvResource.setText(" : " + resource_type);
        tv_training_name.setText(" : " + trainingname);
        tv_trainer_name.setText(" : " + trainerName);
        tv_Start_Date.setText(" : " + startdate);
        tv_male.setText(" : " + male);
        tv_feMale.setText(" : " + female);
        tv_attendance.setText(" : " + totalAttendance);
        tv_objective.setText(" : " + objective);


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