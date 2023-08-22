package com.indev.ruraldevelopment.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.indev.ruraldevelopment.Activity.Adapter.InfraMonitorSliderAdapter;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMultipleImagesPojo;
import com.indev.ruraldevelopment.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Infra_Monitoring_view extends AppCompatActivity {

    String infraId,Monitoring_date,Installation,village_aa,StructureName,school_undertaken,kitchen_garden,emuskan,installed_library,science_lab;
    TextView tv_village_aa,tv_school_Installation,tv_Monitoring_date,tvStructureName,tv_school_undertaken,tvkitchen_garden,tv_emuskan,tv_installed_library,tvscience_lab;
    SqliteHelper sqliteHelper;
    SharedPrefHelper sharedPrefHelper;
    ArrayList<InfraStruMultipleImagesPojo>infraStruMultipleImagesPojoArrayList=new ArrayList<>();
    SliderView sliderView;
    String infra_monitoring_id="";
    String resource_id="";
    String fonction="",resource_name="",Subresource_Type_name="",Subresource_id="",infra_id="";
    LinearLayout ll_quesion_Emuskan;
    int columnValue=0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infra_monitoring_view);
        setTitle("Infra Monitoring Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_village_aa = findViewById(R.id.tv_village_aa);
        tv_Monitoring_date = findViewById(R.id.tv_Monitoring_date);
        tvStructureName = findViewById(R.id.tvStructureName);
        tv_school_undertaken = findViewById(R.id.tv_school_undertaken);
        tvkitchen_garden = findViewById(R.id.tvkitchen_garden);
        tvscience_lab = findViewById(R.id.tvscience_lab);
        sliderView = findViewById(R.id.sliderview1);
        ll_quesion_Emuskan = findViewById(R.id.ll_quesion_Emuskan);
        tv_school_Installation = findViewById(R.id.tv_school_Installation);
        sqliteHelper=new SqliteHelper(this);
        sharedPrefHelper = new SharedPrefHelper(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            infraId = String.valueOf(bundle.getInt("local_id", 0));
            Monitoring_date = bundle.getString("date_of_monitoring", "");
            village_aa = bundle.getString("village_id", "");
            Subresource_id = bundle.getString("Subresource_id", "");
            Subresource_Type_name = bundle.getString("Subresource_Type_name", "");
            infra_id = bundle.getString("infra_id", "");
            resource_id = bundle.getString("resource_id", "");
            school_undertaken = bundle.getString("is_the_structure_funtional", "");
            kitchen_garden = bundle.getString("is_the_structure_damaged", "");
            emuskan = bundle.getString("is_there_emuskan", "");
            Installation = bundle.getString("Installation", "");
            installed_library = bundle.getString("is_there_installed_library", "");
            science_lab = bundle.getString("is_there_science_lab", "");
            infra_monitoring_id = bundle.getString("infra_monitoring_id", "");

        }
        Subresource_Type_name = sqliteHelper.getResourceName(Subresource_id);

        viewMonitor();
//        if (sharedPrefHelper.getString("baseline","").equals("baseline")){
//            ll_quesion_Emuskan.setVisibility(View.GONE);
//        }else {
//            ll_quesion_Emuskan.setVisibility(View.VISIBLE);
//        }
        String village=sqliteHelper.getColumnName("village_name","village"," where village_id='"+village_aa+"'");


            fonction = sharedPrefHelper.getString("infra_idd", "");
            resource_name = sharedPrefHelper.getString("resource_name", "");
            tv_village_aa.setText(": " + village);
            tv_Monitoring_date.setText(": " + Monitoring_date);
        tv_school_Installation.setText(": " + Installation);
            tvStructureName.setText(": " + resource_name);
            tv_school_undertaken.setText(": " + fonction);
            tvkitchen_garden.setText(": " + kitchen_garden);
            tvscience_lab.setText(": " + science_lab);



    }

    public void bundless(){

    }

    private void viewMonitor() {
        infraStruMultipleImagesPojoArrayList=sqliteHelper.inframlistingimages(infra_monitoring_id);
        InfraMonitorSliderAdapter monitorSliderAdapter = new InfraMonitorSliderAdapter(infraStruMultipleImagesPojoArrayList);
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