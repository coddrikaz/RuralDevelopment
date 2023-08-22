package com.indev.ruraldevelopment.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.indev.ruraldevelopment.Activity.Adapter.AdapterStakeHolderSurvey;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.StakeHolderSurveyModel;
import com.indev.ruraldevelopment.R;

import java.util.ArrayList;

public class List_of_stakeholder_survey extends AppCompatActivity {
    com.google.android.material.floatingactionbutton.FloatingActionButton addStakeholderSurvey;
    RecyclerView rvStakeholderSurvey;
    ArrayList<StakeHolderSurveyModel> stakeHolderSurveyModelArrayList;
    SqliteHelper sqliteHelper;
    SharedPrefHelper sharedPrefHelper;
    EditText etSearchBar;
    Context context = this;
    String village_id="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_stakeholder_survey);

        getSupportActionBar().setTitle("Stakeholder Survey List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addStakeholderSurvey=findViewById(R.id.addStakeholderSurvey);
        sqliteHelper = new SqliteHelper(this);
        sharedPrefHelper = new SharedPrefHelper(this);
        rvStakeholderSurvey = findViewById(R.id.rvStakeholderSurvey);
        etSearchBar = findViewById(R.id.list_search);


        stakeHolderSurveyModelArrayList = new ArrayList<>();
        village_id = sharedPrefHelper.getString("village_id" , "");
        stakeHolderSurveyModelArrayList = sqliteHelper.getStackHolderSurvey(village_id);
        AdapterStakeHolderSurvey adapterStakeholder = new AdapterStakeHolderSurvey(context, stakeHolderSurveyModelArrayList);
        rvStakeholderSurvey.setHasFixedSize(true);
        rvStakeholderSurvey.setLayoutManager(new LinearLayoutManager(this));
        rvStakeholderSurvey.setAdapter(adapterStakeholder);

        etSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search = etSearchBar.getText().toString();
                stakeHolderSurveyModelArrayList = sqliteHelper.getSurveySearch(search,village_id);
                AdapterStakeHolderSurvey registerAdapter = new AdapterStakeHolderSurvey(List_of_stakeholder_survey.this, stakeHolderSurveyModelArrayList);
                int counter = stakeHolderSurveyModelArrayList.size();
                //  FarmerCount.setText("Farmer 0"+counter);
                rvStakeholderSurvey.setHasFixedSize(true);
                rvStakeholderSurvey.setLayoutManager(new LinearLayoutManager(List_of_stakeholder_survey.this));
                rvStakeholderSurvey.setAdapter(registerAdapter);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        addStakeholderSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,StakeHolderSurvey.class);
                context.startActivity(intent);
            }
        });
   }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;
//            case R.id.home_icon:
//              Intent intent1 = new Intent(this, Login.class);
//                startActivity(intent1);
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}