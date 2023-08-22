package com.indev.ruraldevelopment.Activity.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.indev.ruraldevelopment.Activity.Adapter.AdapterResource;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.MultipleImagePojo;
import com.indev.ruraldevelopment.Activity.Model.ResourceMappingPojo;
import com.indev.ruraldevelopment.R;

import java.util.ArrayList;

public class List_of_resources extends AppCompatActivity {
    RecyclerView rvResource;
    ImageView image;
    ArrayList<ResourceMappingPojo> resourceMappingPojoArrayList;
    ArrayList<MultipleImagePojo> multipleImagePojoArrayList;
    SqliteHelper sqliteHelper;
    Context context = this;
    String village_id="";
    SharedPrefHelper sharedPrefHelper;
    EditText list_search;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_resources);

        getSupportActionBar().setTitle("Resource List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sqliteHelper = new SqliteHelper(this);
        image = findViewById(R.id.image);
        rvResource = findViewById(R.id.rvResource);
        list_search = findViewById(R.id.list_search);
        sharedPrefHelper=new SharedPrefHelper(this);


        FloatingActionButton addResource;
        addResource = findViewById(R.id.addResource);

        list_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search = list_search.getText().toString();
                resourceMappingPojoArrayList = sqliteHelper.getResourceSearchData(search,village_id);
                AdapterResource registerAdapter = new AdapterResource(List_of_resources.this, resourceMappingPojoArrayList);
                int counter = resourceMappingPojoArrayList.size();
                //  FarmerCount.setText("Farmer 0"+counter);
                rvResource.setHasFixedSize(true);
                rvResource.setLayoutManager(new LinearLayoutManager(List_of_resources.this));
                rvResource.setAdapter(registerAdapter);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        addResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_of_resources.this, Add_resources.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("screentype","Resource Mapping");
                startActivity(intent);
            }
        });
        village_id = sharedPrefHelper.getString("village_id", "");

        viewResource();


    }


    public void onBackPressed() {
        Intent intent=new Intent(List_of_resources.this, Main_Menu.class);
        startActivity(intent);
    }


    private void viewResource(){
        resourceMappingPojoArrayList = sqliteHelper.getResourceMappingData(village_id);

        AdapterResource adapterResource = new AdapterResource(context, resourceMappingPojoArrayList);
        rvResource.setHasFixedSize(true);
        rvResource.setLayoutManager(new LinearLayoutManager(this));
        rvResource.setAdapter(adapterResource);
    }

    @Override
    protected void onPostResume() {
        viewResource();
        super.onPostResume();
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
