package com.indev.ruraldevelopment.Activity.Activity;

import static com.indev.ruraldevelopment.Activity.restApi.APIClient.BASE_URL7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.indev.ruraldevelopment.Activity.Adapter.MonitorSliderAdapter;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.MonitorModel;
import com.indev.ruraldevelopment.Activity.Model.MonitorMultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class Moniter_View extends AppCompatActivity {
    TextView tvResource,tvresourcename,tvDate,tv_radio_structure,tv_structure_damage,tv_structure_function,tv_jubilant_brand,tv_Mark,tvDescription;
    String resource_type,resource_name_status,resource_name,date,structure,jubiliant_brand,damaged,the_damage_structure,re_mark,description,img,structure_funtional2;
    ImageView image;
    ArrayList<MultipleImagePojo> multipleImagePojoArrayList;
    ArrayList<MonitorMultipleImagePojo> monitorMultipleImagePojoArrayList;
    SqliteHelper sqliteHelper;
    MonitorModel monitorModel;
    String resourceType="";
    String imageView="";
    int unique_id;
    int monitorId;
    SliderView sliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moniter_view);
        getSupportActionBar().setTitle("Monitoring View");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getid();

        SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(this);

        monitorModel = new MonitorModel();
        sqliteHelper = new SqliteHelper(this);
        sharedPrefHelper.getString("resource","");
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            monitorId = bundle.getInt("monitor_resource_id",0);
            resource_name =bundle.getString("resource_name","");
            resource_type =bundle.getString("resource_type_id","");

           // damaged =bundle.getString("damaged_structure","");
            re_mark=bundle.getString("remark","");
            description=bundle.getString("description","");


            date=bundle.getString("date_of_monitor","");
            structure=bundle.getString("farming_near_structure","");

            structure_funtional2=bundle.getString("structure_functional","");
            jubiliant_brand=bundle.getString("jubliant_visibility_board_near_structure","");

            damaged=bundle.getString("damaged_structure","");

//
            img=bundle.getString("image","");

        }
        viewMonitor();

        imageView = sqliteHelper.getColumnName("image", "monitor_resource", " where monitor_resource_id='" + monitorId + "'");

        tvResource.setText(" : "+resource_type);
        tvresourcename.setText(" : "+resource_name);

        tvDate.setText(" : "+date);

        tvDescription.setText(" : "+description);

        tv_structure_damage.setText(damaged);
        tv_structure_function.setText(structure_funtional2);
        tv_jubilant_brand.setText(jubiliant_brand);

        if (img != null && img.length() > 200) {
            byte[] decodedString = Base64.decode(img, Base64.NO_WRAP);
            InputStream inputStream = new ByteArrayInputStream(decodedString);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            image.setImageBitmap(bitmap);
        } else {
            try {
                Picasso.get()
                        .load(BASE_URL7+ imageView)
                        .placeholder(R.drawable.infrastructure_development)
                        .into(image);
            } catch (Exception e) {
                Log.d("Exception", "" + e);
            }
        }



    }

    public void getid(){
        sliderView= findViewById(R.id.sliderview);
        monitorMultipleImagePojoArrayList=new ArrayList<>();
        tvResource = findViewById(R.id.tvResource);
        tvresourcename = findViewById(R.id.tvresourcename);
        tvDescription=findViewById(R.id.tvDescription);
        tv_structure_damage=findViewById(R.id.tv_structure_damage);
        tv_radio_structure=findViewById(R.id.tv_radio_structure);
        tv_jubilant_brand=findViewById(R.id.tv_jubilant_brand);
        tvDate=findViewById(R.id.tvDate);
        // tv_Mark =findViewById(R.id.tv_Mark);
        tv_structure_function=findViewById(R.id.tv_structure_function);
        image = findViewById(R.id.image);


    }
    private void viewMonitor() {

        monitorMultipleImagePojoArrayList = sqliteHelper.getMonitorImage(String.valueOf(monitorId));
        MonitorSliderAdapter monitorSliderAdapter = new MonitorSliderAdapter(monitorMultipleImagePojoArrayList);
        sliderView.setSliderAdapter(monitorSliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }

    @Override
    protected void onPostResume() {
        viewMonitor();
        super.onPostResume();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Moniter_View.this, List_of_monitor_resources.class);
        startActivity(intent);

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