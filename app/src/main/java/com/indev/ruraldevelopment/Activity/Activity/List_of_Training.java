package com.indev.ruraldevelopment.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.indev.ruraldevelopment.Activity.Adapter.AdapterTraining;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.TrainingModel;
import com.indev.ruraldevelopment.R;

import java.util.ArrayList;

public class List_of_Training extends AppCompatActivity {
    RecyclerView rvTraining;
    ArrayList<TrainingModel> trainingModelArrayList;
    SqliteHelper sqliteHelper;
    Context context = this;
    String village_id="";
    SharedPrefHelper sharedPrefHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_training);


        getSupportActionBar().setTitle("Training List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sqliteHelper = new SqliteHelper(this);
        sharedPrefHelper = new SharedPrefHelper(this);
        rvTraining = findViewById(R.id.rvTraining);

        village_id=sharedPrefHelper.getString("village_id","");
        trainingModelArrayList = sqliteHelper.getTrainingData(village_id);
        AdapterTraining adapterTraining = new AdapterTraining(context, trainingModelArrayList);
        rvTraining.setHasFixedSize(true);
        rvTraining.setLayoutManager(new LinearLayoutManager(this));
        rvTraining.setAdapter(adapterTraining);


        FloatingActionButton addTraining;
        addTraining = findViewById(R.id.addTraining);
        addTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_of_Training.this,AddTraining.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }

    public void onBackPressed() {
       super.onBackPressed();
    }




    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               onBackPressed();
                return true;
//            case R.id.home_icon:
//                Intent intent1 = new Intent(this, Login.class);
//                startActivity(intent1);
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}