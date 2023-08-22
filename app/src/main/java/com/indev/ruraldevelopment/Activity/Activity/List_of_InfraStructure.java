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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.indev.ruraldevelopment.Activity.Adapter.AdapterInfraStructure;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStructureModel;
import com.indev.ruraldevelopment.R;

import java.util.ArrayList;

public class List_of_InfraStructure extends AppCompatActivity {
    RecyclerView rvInfrStructure;
    ArrayList<InfraStructureModel> infraStructureModelArrayList;
    Context context = this;
    SqliteHelper sqliteHelper;
    EditText etSearchBar;
    String village_id="";
    SharedPrefHelper sharedPrefHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_infra_structure);

        getSupportActionBar().setTitle("Infra Structure List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        allenislaision();


    }

    private void allenislaision() {


        sqliteHelper = new SqliteHelper(this);
        rvInfrStructure = findViewById(R.id.rvInfrStructure);
        etSearchBar = findViewById(R.id.etSearchBar);
        sharedPrefHelper=new SharedPrefHelper(this);
        village_id = sharedPrefHelper.getString("village_id", "");

        infraStructureModelArrayList = sqliteHelper.getInfraStructureData(village_id);
        AdapterInfraStructure adapterInfraStructure = new AdapterInfraStructure(context, infraStructureModelArrayList);
        rvInfrStructure.setHasFixedSize(true);
        rvInfrStructure.setLayoutManager(new LinearLayoutManager(this));
        rvInfrStructure.setAdapter(adapterInfraStructure);


        etSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search = etSearchBar.getText().toString();
                infraStructureModelArrayList = sqliteHelper.getInfraSearch(search,village_id);
                AdapterInfraStructure registerAdapter = new AdapterInfraStructure(List_of_InfraStructure.this, infraStructureModelArrayList);
                int counter = infraStructureModelArrayList.size();
                //  FarmerCount.setText("Farmer 0"+counter);
                rvInfrStructure.setHasFixedSize(true);
                rvInfrStructure.setLayoutManager(new LinearLayoutManager(List_of_InfraStructure.this));
                rvInfrStructure.setAdapter(registerAdapter);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        FloatingActionButton addInfraStructure;
        addInfraStructure = findViewById(R.id.addInfraStructure);
        addInfraStructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_of_InfraStructure.this, AddInfraStructure.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }

    public void onBackPressed() {
       super.onBackPressed();
    }

    @Override
    protected void onResume() {
        allenislaision();
        super.onResume();
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