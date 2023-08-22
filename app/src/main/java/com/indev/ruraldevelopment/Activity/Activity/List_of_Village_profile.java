package com.indev.ruraldevelopment.Activity.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.indev.ruraldevelopment.Activity.Adapter.AdapterVillageProfile;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.VillageProfileModel;
import com.indev.ruraldevelopment.R;

import java.util.ArrayList;

public class List_of_Village_profile extends AppCompatActivity {
    RecyclerView rvVillageProfile;
    ArrayList<VillageProfileModel> villageProfileModelArrayList;
    ArrayList<MultipleImagePojo> multipleImagePojoArrayList;
    SqliteHelper sqliteHelper;
    Context context = this;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_village_profile);

        getSupportActionBar().setTitle("Village Profile List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sqliteHelper = new SqliteHelper(this);
        image = findViewById(R.id.image);
        rvVillageProfile = findViewById(R.id.rvVillageProfile);


        villageProfileModelArrayList = sqliteHelper.getVillageProfileData(0);

        AdapterVillageProfile adapterVillageProfile = new AdapterVillageProfile(context, villageProfileModelArrayList);
       // multipleImagePojoArrayList = sqliteHelper.getMultipleimage();

        rvVillageProfile.setHasFixedSize(true);
        rvVillageProfile.setLayoutManager(new LinearLayoutManager(this));
        rvVillageProfile.setAdapter(adapterVillageProfile);



//        FloatingActionButton addVillageProfile;
//        addVillageProfile = findViewById(R.id.addVillageProfile);
//        addVillageProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(List_of_Village_profile.this, AddVillageProfile.class);
//                startActivity(intent);
//
//            }
//        });
    }
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