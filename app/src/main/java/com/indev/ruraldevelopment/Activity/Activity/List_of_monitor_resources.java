package com.indev.ruraldevelopment.Activity.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.indev.ruraldevelopment.Activity.Adapter.AdapterMonitor;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.MonitorModel;
import com.indev.ruraldevelopment.Activity.Model.ResourceMappingPojo;
import com.indev.ruraldevelopment.R;

import java.util.ArrayList;

public class List_of_monitor_resources extends AppCompatActivity {
    RecyclerView rvMonitorResource;
    ArrayList<MonitorModel> monitorModelArrayList;
    ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList;
    SqliteHelper sqliteHelper;
    Context context = this;
    Button btnView;
    String village_id="";
    String resource_mapping_id="";
    SharedPrefHelper sharedPrefHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_monitor_resources);

        getSupportActionBar().setTitle("Monitor Resouce List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sqliteHelper = new SqliteHelper(this);
        rvMonitorResource = findViewById(R.id.rvMonitorResource);
        btnView=findViewById(R.id.btnView);
        sharedPrefHelper=new SharedPrefHelper(this);
        village_id = sharedPrefHelper.getString("village_id", "");
        resource_mapping_id=sharedPrefHelper.getString("resource_mapping_id","");

        resourceMappingPojoArrayList = sqliteHelper.getResourceMappingData(village_id);
        monitorModelArrayList = sqliteHelper.getMonitorData(village_id,resource_mapping_id);

        AdapterMonitor adapterMonitor = new AdapterMonitor(context, monitorModelArrayList, resourceMappingPojoArrayList);
        rvMonitorResource.setHasFixedSize(true);
        rvMonitorResource.setLayoutManager(new LinearLayoutManager(this));
        rvMonitorResource.setAdapter(adapterMonitor);


        FloatingActionButton addMonitorResource;
        addMonitorResource = findViewById(R.id.addMonitorResource);


        addMonitorResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_of_monitor_resources.this, Monitor_resources.class);intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }


    public void onBackPressed() {
        Intent intent=new Intent(List_of_monitor_resources.this, List_of_resources.class);
        startActivity(intent);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                // app icon in action bar clicked; go home
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