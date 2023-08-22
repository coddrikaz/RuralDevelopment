package com.indev.ruraldevelopment.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.indev.ruraldevelopment.Activity.Activity.AddInfraStructureMonitoring;
import com.indev.ruraldevelopment.Activity.Activity.InfraStraMonitoringList;
import com.indev.ruraldevelopment.Activity.Adapter.AdapterBaseline;
import com.indev.ruraldevelopment.Activity.Adapter.InfraStruMonitoringAdapter;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMonitoringPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMultipleImagesPojo;
import com.indev.ruraldevelopment.R;
import com.indev.ruraldevelopment.baseline_question;

import java.util.ArrayList;

public class baseline_Listing extends AppCompatActivity {
    RecyclerView recycle_list;
    FloatingActionButton add_infra_monitoring;
    SqliteHelper sqliteHelper;
    ArrayList<InfraStruMonitoringPojo> infraStruMonitoringPojoArrayList;
    ArrayList<InfraStruMultipleImagesPojo> infraStruMultipleImagesPojoArrayList;
    EditText etSearchBar;
    Context context=this;
    String infra_id="",resource_name="",resource_id="",infra_name="",Subresource_name="";
    SharedPrefHelper sharedPrefHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Baseline Monitoring List");
        setContentView(R.layout.activity_baseline_listing);
        add_infra_monitoring=findViewById(R.id.add_infra_monitoringss);
        recycle_list=findViewById(R.id.recyclerViewss);
        sqliteHelper = new SqliteHelper(this);
        infraStruMonitoringPojoArrayList = new ArrayList<>();
        infraStruMultipleImagesPojoArrayList = new ArrayList<>();
        sharedPrefHelper=new SharedPrefHelper(this);


//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            infra_id = bundle.getString("infra_idddd", "");
//            resource_id = bundle.getString("resource_id", "");
//            infra_name = bundle.getString("infra_structure_name", "");
//            Subresource_name = bundle.getString("Subresource_name", "");
//            sharedPrefHelper.setString("infra_name",infra_name);
//            sharedPrefHelper.setString("infra_idd",infra_id);
//            sharedPrefHelper.setString("resource_id",resource_id);
//        }
//
//
//        resource_name = sqliteHelper.getTypeName(sharedPrefHelper.getString("resource_id",resource_id));
//        sharedPrefHelper.setString("resource_name",resource_name);
//
//        infraStruMonitoringPojoArrayList = sqliteHelper.getInfraStructureMonitoringList(sharedPrefHelper.getString("infra_idd",infra_id));
//
//
//        AdapterBaseline adapterBaseline=new AdapterBaseline(context,infraStruMonitoringPojoArrayList);
//        recycle_list.setHasFixedSize(true);
//        recycle_list.setLayoutManager(new LinearLayoutManager(this));
//        recycle_list.setAdapter(adapterBaseline);
//
//
//        add_infra_monitoring.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(baseline_Listing.this, baseline_question.class);
//                sharedPrefHelper.setString("Subresource_Typ",Subresource_name);
//                sharedPrefHelper.setString("infra_idd",infra_id);
//                startActivity(intent);
//            }
//        });
    }
//     --------------------- Back Button Set------------------------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}