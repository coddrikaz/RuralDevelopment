package com.indev.ruraldevelopment.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.VillageProfileModel;
import com.indev.ruraldevelopment.R;

import java.util.ArrayList;

public class VillageDetails extends AppCompatActivity {
    CardView cv_viewVillageProfile;
    CardView cv_CreateVillageProfile;
    TextView tvText;
    CardView cv_committeeMember;
    CardView cv_Trainig;
    ArrayList<VillageProfileModel> villageProfileModelArrayList;
    SqliteHelper sqliteHelper;
    SharedPrefHelper sharedPrefHelper;
    String village_id="";
    int villageProfileId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_details);
        setTitle("Village Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cv_viewVillageProfile = findViewById(R.id.cv_viewVillageProfile);
        cv_CreateVillageProfile = findViewById(R.id.cv_CreateVillageProfile);
        tvText = findViewById(R.id.tvText);
        cv_committeeMember = findViewById(R.id.cv_committeeMember);
        cv_Trainig = findViewById(R.id.cv_Trainig);
        sqliteHelper = new SqliteHelper(this);
        sharedPrefHelper = new SharedPrefHelper(this);
        villageProfileModelArrayList = new ArrayList<>();
        village_id = sharedPrefHelper.getString("village_id", "");

        villageProfileId= sqliteHelper.getCloumnName("village_profile_id","village_profile"," where village_id='"+village_id+"'");
        if(villageProfileId!=0){
            tvText.setText("Edit");
        }

        cv_viewVillageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(villageProfileId!=0){
                    Intent intent = new Intent(VillageDetails.this, ViewVillageProfile.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else {
                    Toast.makeText(VillageDetails.this, "Please Create first village profile.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        cv_Trainig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VillageDetails.this, List_of_Training.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        cv_committeeMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VillageDetails.this, List_of_committee_member.class);
                intent.putExtra("villageProfileId",villageProfileId);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        cv_CreateVillageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VillageDetails.this, AddVillageProfile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if(villageProfileId!=0){
                    intent.putExtra("type","edit");
                    sharedPrefHelper.setString("edit_type","edit");
                }
                intent.putExtra("vi",villageProfileId);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VillageDetails.this,Main_Menu.class);
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