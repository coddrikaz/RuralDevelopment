package com.indev.ruraldevelopment.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.indev.ruraldevelopment.Activity.Activity.AddInfraStructure;
import com.indev.ruraldevelopment.Activity.Activity.List_of_InfraStructure;
import com.indev.ruraldevelopment.Activity.Adapter.InfraMonitorSliderAdapter;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMultipleImagesPojo;
import com.indev.ruraldevelopment.BaselineListing;
import com.indev.ruraldevelopment.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class BaselineViewDetails extends AppCompatActivity {
    SliderView sliderView;
    TextView tv_village_aa,tv_Monitoring_date,tvStructureName,ll_Averagedaily_basis,ll_effective_basis,tv_TLM_method,tv_think_digital,tv_think_digital_system;
    TextView tv_use_think_digital,tvSupport_from,tv_Does_digital,tv_Installation_from,tv_Muskan_Being,tv_Library,tv_School_Undertaken,tv_kitchen_garden,tv_Science_lab;
    ArrayList<InfraStruMultipleImagesPojo> infraStruMultipleImagesPojoArrayList=new ArrayList<>();
    SqliteHelper sqliteHelper;
    String infraId="",resource_id="",resource_name="",Monitoring_date="",village_aa="",StructureName="",infra_monitoring_id="",infra_idss="",Subresource_id="",Subresource_Type_name="";
    String question1="",question2="",question3="",question4="",question5="",question6="",question7="",question8="",question9="",question10="",question11="",question12="",question13="",question14="";
    SharedPrefHelper sharedPrefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseline_view_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Infra Monitoring Detail");
        sliderView = findViewById(R.id.sliderview1);
        tv_village_aa = findViewById(R.id.tv_village_aa);
        tv_Monitoring_date = findViewById(R.id.tv_Monitoring_date);
        tvStructureName = findViewById(R.id.tvStructureName);
        sharedPrefHelper = new SharedPrefHelper(this);
        sqliteHelper = new SqliteHelper(this);
        inislaision();
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            infraId = String.valueOf(bundle.getInt("local_id", 0));
            Monitoring_date = bundle.getString("date_of_monitoring", "");
            village_aa = bundle.getString("village_id", "");
            resource_id = bundle.getString("resource_id", "");
            infra_monitoring_id = bundle.getString("infra_monitoring_id", "");
            Subresource_id = bundle.getString("Subresource_id", "");
            Subresource_Type_name = bundle.getString("Subresource_Type_name", "");
            infra_idss = bundle.getString("infra_idss", "");
            question1 = bundle.getString("question1", "");
            question2 = bundle.getString("question2", "");
            question3 = bundle.getString("question3", "");
            question4 = bundle.getString("question4", "");
            question5 = bundle.getString("question5", "");
            question6 = bundle.getString("question6", "");
            question7 = bundle.getString("question7", "");
            question8 = bundle.getString("question8", "");
            question9 = bundle.getString("question9", "");
            question10 = bundle.getString("question10", "");
            question11 = bundle.getString("question11", "");
            question12 = bundle.getString("question12", "");
            question13 = bundle.getString("question13", "");
            question14 = bundle.getString("question14", "");

        }
        viewMonitor();
        String village=sqliteHelper.getColumnName("village_name","village"," where village_id='"+village_aa+"'");
        resource_name = sharedPrefHelper.getString("resource_name", "");

        tv_village_aa.setText("  " + village);
        tv_Monitoring_date.setText(" " + Monitoring_date);
        tvStructureName.setText("  " + resource_name);
        ll_Averagedaily_basis.setText(" " + question1);
        ll_effective_basis.setText("  " + question2);
        tv_TLM_method.setText("  " + question3);
        tv_think_digital.setText("  " + question4);
        tv_think_digital_system.setText("  " + question5);
        tv_use_think_digital.setText("  " + question6);
        tvSupport_from.setText("  " + question7);
        tv_Does_digital.setText("  " + question8);
        tv_Installation_from.setText(": " + question9);
        tv_Muskan_Being.setText("  " + question10);
        tv_Library.setText("  " + question11);
        tv_School_Undertaken.setText("  " + question12);
        tv_kitchen_garden.setText("  " + question13);
        tv_Science_lab.setText("  " + question14);
    }
    private void viewMonitor() {
        infraStruMultipleImagesPojoArrayList=sqliteHelper.inframlistingimages(infra_monitoring_id);
        InfraMonitorSliderAdapter monitorSliderAdapter = new InfraMonitorSliderAdapter(infraStruMultipleImagesPojoArrayList);
        sliderView.setSliderAdapter(monitorSliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }
    private void inislaision() {
        ll_Averagedaily_basis = findViewById(R.id.ll_Averagedaily_basis);
        ll_effective_basis = findViewById(R.id.ll_effective_basis);
        tv_TLM_method = findViewById(R.id.tv_TLM_method);
        tv_think_digital = findViewById(R.id.tv_think_digital);
        tv_think_digital_system = findViewById(R.id.tv_think_digital_system);
        tv_use_think_digital = findViewById(R.id.tv_use_think_digital);
        tvSupport_from = findViewById(R.id.tvSupport_from);
        tv_Does_digital = findViewById(R.id.tv_Does_digital);
        tv_Installation_from = findViewById(R.id.tv_Installation_from);
        tv_Muskan_Being = findViewById(R.id.tv_Muskan_Being);
        tv_Library = findViewById(R.id.tv_Library);
        tv_School_Undertaken = findViewById(R.id.tv_School_Undertaken);
        tv_kitchen_garden = findViewById(R.id.tv_kitchen_garden);
        tv_Science_lab = findViewById(R.id.tv_Science_lab);

    }
    @Override
    protected void onPostResume() {
        viewMonitor();
        super.onPostResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(BaselineViewDetails.this, BaselineListing.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}