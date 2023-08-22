package com.indev.ruraldevelopment.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.indev.ruraldevelopment.Activity.Adapter.ResourceSliderAdapter;
import com.indev.ruraldevelopment.Activity.Adapter.SliderAdapter;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMultipleImagePojo;
import com.indev.ruraldevelopment.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class Resorces_view extends AppCompatActivity {
    TextView tvResourcetype,tvresourcename,tvAddress,tvDate,tvStatus,tvDescription,tvlongitude,tvlatitude;
    SqliteHelper sqliteHelper;
    String  resource_type,status,address,description,img,resource_name,latitude,longitude;
    int unique_id;
//    ArrayList<MultipleImagePojo>multipleImagePojoArrayList;
    ArrayList<ResourceMultipleImagePojo> resourceMultipleImagePojoArrayList;
    SliderView sliderView;
    String resourceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resorces_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Resource Details");

        sqliteHelper = new SqliteHelper(this);

        getid();

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            resource_type =bundle.getString("resource_type_id", "");
            unique_id=bundle.getInt("resource_mapping_id",0);
            status =bundle.getString("resource_status","");
            resource_name =bundle.getString("resource_mapping_name","");
            address=bundle.getString("address","");
            description=bundle.getString("description","");
            img=bundle.getString("image","");
            longitude=bundle.getString("longitude","");
            latitude=bundle.getString("latitude","");


        }

      //  String resource_type= sqliteHelper.getCloumnName("resource_name","resource","where =resource_id'"+resourceType+"'");
        resourceMultipleImagePojoArrayList = sqliteHelper.getimage(String.valueOf(unique_id));
        ResourceSliderAdapter resourceSliderAdapter = new ResourceSliderAdapter(resourceMultipleImagePojoArrayList);

        sliderView.setSliderAdapter(resourceSliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        tvResourcetype.setText(" : "+resource_type);
        tvresourcename.setText(" : "+resource_name);
        tvStatus.setText(" : "+status);
        tvAddress.setText(" : "+address);
        tvlongitude.setText(" : "+latitude);
        tvlatitude.setText(" : "+longitude);



//        tvDate.setText(date);
        tvDescription.setText(" : "+description);



    }


    public void getid(){


        tvresourcename=findViewById(R.id.tvresourcename);

        tvResourcetype=findViewById(R.id.tvResource);
        tvAddress=findViewById(R.id.tvAddress);
        tvDate=findViewById(R.id.tvDate);
        sliderView=findViewById(R.id.image_slider);
        tvStatus=findViewById(R.id.tvStatus);
        tvDescription=findViewById(R.id.tvDescription);
        tvlongitude=findViewById(R.id.tvlongitude);
        tvlatitude=findViewById(R.id.tvlatitude);





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