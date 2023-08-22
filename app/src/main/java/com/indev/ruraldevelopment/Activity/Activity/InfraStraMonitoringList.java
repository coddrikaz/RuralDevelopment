package com.indev.ruraldevelopment.Activity.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.indev.ruraldevelopment.Activity.Adapter.InfraStruMonitoringAdapter;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMonitoringPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMultipleImagesPojo;
import com.indev.ruraldevelopment.R;

import java.util.ArrayList;

public class InfraStraMonitoringList extends AppCompatActivity {
    RecyclerView recycle_list;
    FloatingActionButton add_infra_monitoring;
    SqliteHelper sqliteHelper;
    ArrayList<InfraStruMonitoringPojo>infraStruMonitoringPojoArrayList;
    ArrayList<InfraStruMultipleImagesPojo> infraStruMultipleImagesPojoArrayList;
    EditText etSearchBar;
    Context context=this;
    String infra_id="",village_id="",Subresource_Type_namedd="",monitoring="",resource_name="",resource_id="",infra_name="",Subresource_name="",Subresource_id="",Subresource_Type_name="";
    SharedPrefHelper sharedPrefHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_infra_stra_monitoring_list);
        setTitle("InfraStructure Monitoring List");
        add_infra_monitoring=findViewById(R.id.add_infra_monitoring);
        recycle_list=findViewById(R.id.recyclerView);
        recycle_list=findViewById(R.id.recyclerView);
        sqliteHelper = new SqliteHelper(this);
        infraStruMultipleImagesPojoArrayList = new ArrayList<>();
        sharedPrefHelper=new SharedPrefHelper(this);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
//            infra_id = bundle.getString("infra_idddd", "");
            resource_id = bundle.getString("resource_id", "");
            infra_name = bundle.getString("infra_structure_name", "");
            Subresource_name = bundle.getString("Subresource_Type_name", "");
            village_id = bundle.getString("village_id", "");
            resource_name = bundle.getString("resource_name", "");
            Subresource_id = bundle.getString("Subresource_id", "");
            sharedPrefHelper.setString("infra_name",infra_name);
            sharedPrefHelper.setString("infra_idd",infra_id);
           sharedPrefHelper.setString("resource_id",resource_id);
        }


        Subresource_Type_namedd = sqliteHelper.getResourceName(Subresource_id);


//        resource_name = sqliteHelper.getTypeName(sharedPrefHelper.getString("resource_id",resource_id));
            sharedPrefHelper.setString("resource_name",resource_name);

            sharedPrefHelper.setString("Subresource_id",Subresource_id);
//        Subresource_Type_name = sqliteHelper.getResourceName(String.valueOf(infraStruMonitoringPojoArrayList.get(0).getSubresource_id()));

        monitoring= sqliteHelper.GetValueCommon("monitoring", "select monitoring from infra_monitoring");
        infraStruMonitoringPojoArrayList = sqliteHelper.getInfraStructureMonitoringList(infra_id);

        InfraStruMonitoringAdapter infraStruMonitoringAdapter=new InfraStruMonitoringAdapter(context,infraStruMonitoringPojoArrayList);
        recycle_list.setHasFixedSize(true);
        recycle_list.setLayoutManager(new LinearLayoutManager(this));

        recycle_list.setAdapter(infraStruMonitoringAdapter);


        add_infra_monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(InfraStraMonitoringList.this, AddInfraStructureMonitoring.class);
                intent.putExtra("infra_id",infra_id);
                intent.putExtra("resource_id",resource_id);
                intent.putExtra("infra_name",infra_name);
                intent.putExtra("resource_name",resource_name);
                intent.putExtra("Subresource_Type_name",Subresource_name);
                intent.putExtra("Subresource_id",Subresource_id);
                sharedPrefHelper.setString("Subresource_Typ",Subresource_name);
                sharedPrefHelper.setString("infra_idd",infra_id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // --------------------- Back Button Set------------------------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}