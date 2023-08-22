package com.indev.ruraldevelopment;

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
import com.indev.ruraldevelopment.Activity.Adapter.AdapterBaseline;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMonitoringPojo;
import com.indev.ruraldevelopment.Activity.Model.InfraStruMultipleImagesPojo;

import java.util.ArrayList;

public class BaselineListing extends AppCompatActivity {
    RecyclerView recycle_list;
    FloatingActionButton add_infra_monitoring;
    SqliteHelper sqliteHelper;
    ArrayList<InfraStruMonitoringPojo> infraStruMonitoringPojoArrayList;
    ArrayList<InfraStruMultipleImagesPojo> infraStruMultipleImagesPojoArrayList;
    EditText etSearchBar;
    Context context=this;
    String infra_id="",village_id="",resource_namedd="",baseline="",resource_name="",resource_id="",infra_name="",Subresource_name="",baseliness="",Subresource_id="";
    SharedPrefHelper sharedPrefHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseline_listing2);
        setTitle("Baseliness Monitoring List");
        add_infra_monitoring=findViewById(R.id.add_infra_monitoringss);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recycle_list=findViewById(R.id.recyclerViewss);
        sqliteHelper = new SqliteHelper(this);
        infraStruMultipleImagesPojoArrayList = new ArrayList<>();
        sharedPrefHelper=new SharedPrefHelper(this);
        village_id=sharedPrefHelper.getString("village_id","");
        sharedPrefHelper.setString("village_id",village_id);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            infra_id = bundle.getString("infra_idddd", "");
            resource_id = bundle.getString("resource_id", "");
            resource_name = bundle.getString("resource_name", "");
            infra_name = bundle.getString("infra_structure_name", "");
            Subresource_name = bundle.getString("Subresource_Type_name", "");
            Subresource_id = bundle.getString("Subresource_id", "");
            baseliness = bundle.getString("baseline", "");
            sharedPrefHelper.setString("infra_name",infra_name);
//            sharedPrefHelper.setString("infra_idd",infra_id);
            sharedPrefHelper.setString("resource_id",resource_id);
        }


        resource_namedd = sqliteHelper.getTypeName(sharedPrefHelper.getString("resource_id",resource_id));
        sharedPrefHelper.setString("resource_name",resource_name);
//        baseline= sqliteHelper.GetValueCommon("baseline", "select baseline from infra_monitoring");
        baseline= sqliteHelper.GetValueCommonaaaa("baseline", "select baseline from infra_monitoring");

        infraStruMonitoringPojoArrayList = sqliteHelper.getInfraStructureMonitoringListbaseline(infra_id);

        AdapterBaseline adapterBaseline=new AdapterBaseline(context,infraStruMonitoringPojoArrayList);
        recycle_list.setHasFixedSize(true);
        recycle_list.setLayoutManager(new LinearLayoutManager(this));
        recycle_list.setAdapter(adapterBaseline);

        if (infraStruMonitoringPojoArrayList.size()>0){
            add_infra_monitoring.setVisibility(View.GONE);
        }else {
            add_infra_monitoring.setVisibility(View.VISIBLE);
        }
        add_infra_monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(BaselineListing.this, baseline_question.class);
                intent.putExtra("infra_id",infra_id);
                intent.putExtra("resource_id",resource_id);
                intent.putExtra("infra_name",infra_name);
                intent.putExtra("resource_name",resource_name);
                intent.putExtra("Subresource_Type_name",Subresource_name);
                intent.putExtra("Subresource_id",Subresource_id);
                sharedPrefHelper.setString("Subresource_Typ",Subresource_name);
                sharedPrefHelper.setString("infra_idd",infra_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //     --------------------- Back Button Set------------------------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}