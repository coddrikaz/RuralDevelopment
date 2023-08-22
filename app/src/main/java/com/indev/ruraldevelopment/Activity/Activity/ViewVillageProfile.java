package com.indev.ruraldevelopment.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.indev.ruraldevelopment.Activity.Adapter.AdapterMemberViewVillageResource;
import com.indev.ruraldevelopment.Activity.Adapter.AdapterResourceViewVillageResource;
import com.indev.ruraldevelopment.Activity.Adapter.SliderAdapter;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.Committee_Table;
import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMappingPojo;
import com.indev.ruraldevelopment.Activity.Model.VillageProfileModel;
import com.indev.ruraldevelopment.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class ViewVillageProfile extends AppCompatActivity {

    TextView tvPopulation, tvPradhan_name, tvContactNumber;
    Button btnEdit;
    ArrayList<VillageProfileModel> villageProfileModelArrayList;
    ArrayList<MultipleImagePojo> multipleImagePojoArrayList;
    SqliteHelper sqliteHelper;
    VillageProfileModel villageProfileModel;
    String id = "";
    String ll = "";
    SharedPrefHelper sharedPrefHelper;

    RecyclerView rvViewVillageMember, rvViewVillageResource;
    ArrayList<Committee_Table> committee_tableArrayList;
    ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList;

    String population, pradhan_name, contact_number, img;
    String unique_id= "";
    String village_id_id= "";
    int villageProfileId=0;
    int villageProfileIdssss=0;
    SliderView sliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_village_profile);

        getSupportActionBar().setTitle("View Village Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialize();

        sliderView = findViewById(R.id.image_slider);
        sqliteHelper = new SqliteHelper(this);

        sqliteHelper = new SqliteHelper(getApplicationContext());
        sharedPrefHelper = new SharedPrefHelper(this);
        village_id_id = sharedPrefHelper.getString("village_id", "");

        villageProfileId= sqliteHelper.getCloumnName("village_profile_id","village_profile"," where village_id='"+village_id_id+"'");

        villageProfileModel = new VillageProfileModel();
        villageProfileModelArrayList = new ArrayList<>();

        villageProfileModelArrayList = sqliteHelper.getVillageProfileData(villageProfileId);

        if (villageProfileModelArrayList.size() != 0) {

            ll = String.valueOf(villageProfileModelArrayList.get(0).getLocal_id());
        }
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            population = bundle.getString("population", "");
//            pradhan_name = bundle.getString("pradhanName", "");
//            contact_number = bundle.getString("contactNumber", "");
//            img = bundle.getString("villageImage", "");
//        }


        villageProfileModel = new VillageProfileModel();
        villageProfileModelArrayList = sqliteHelper.getVillageProfileDataView(Integer.parseInt(village_id_id),villageProfileId);
        if(villageProfileModelArrayList.size()!=0) {
            tvPopulation.setText(" : "+villageProfileModelArrayList.get(0).getPopulation());
            tvPradhan_name.setText(" : "+villageProfileModelArrayList.get(0).getPradhan_name());
            tvContactNumber.setText(" : "+villageProfileModelArrayList.get(0).getMobile_no());
            unique_id = String.valueOf(villageProfileModelArrayList.get(0).getVillage_profile_id());
        }

        viewVillage();


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ViewVillageProfile.this, AddVillageProfile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("type","edit");
                intent.putExtra("vi",villageProfileId);
                startActivity(intent);
            }
        });
        committee_tableArrayList.clear();

        committee_tableArrayList = sqliteHelper.getCommitteeDataInVillage(village_id_id, "");

        if (committee_tableArrayList.size() > 0) {
            committee_tableArrayList = sqliteHelper.getCommitteeData(village_id_id);
            AdapterMemberViewVillageResource adapter = new AdapterMemberViewVillageResource(this, committee_tableArrayList);
            rvViewVillageMember.setHasFixedSize(true);
            rvViewVillageMember.setLayoutManager(new LinearLayoutManager(this));
            rvViewVillageMember.setAdapter(adapter);
        }

        resourceMappingPojoArrayList = sqliteHelper.getResourceMappingDataVillage(village_id_id);
        if (resourceMappingPojoArrayList.size() > 0) {
            AdapterResourceViewVillageResource adapterResource = new AdapterResourceViewVillageResource(this, resourceMappingPojoArrayList);
            rvViewVillageResource.setHasFixedSize(true);
            rvViewVillageResource.setLayoutManager(new LinearLayoutManager(this));
            rvViewVillageResource.setAdapter(adapterResource);
        }

    }

    private void initialize() {
        rvViewVillageMember = findViewById(R.id.rvViewVillageMember);
        rvViewVillageResource = findViewById(R.id.rvViewVillageResource);
        btnEdit = findViewById(R.id.btnEdit);
        tvPopulation = findViewById(R.id.tvPopulation);
        tvPradhan_name = findViewById(R.id.tvPradhan_name);
        tvContactNumber = findViewById(R.id.tvContactNumber);
        committee_tableArrayList=new ArrayList<>();
    }


    private void viewVillage() {

        multipleImagePojoArrayList = sqliteHelper.getVillageImage(String.valueOf(unique_id));
        SliderAdapter sliderAdapter = new SliderAdapter(multipleImagePojoArrayList);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }

    @Override
    protected void onPostResume() {
        viewVillage();
        super.onPostResume();
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
