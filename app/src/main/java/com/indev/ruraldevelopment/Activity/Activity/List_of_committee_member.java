package com.indev.ruraldevelopment.Activity.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.indev.ruraldevelopment.Activity.Adapter.AdapterCommittee;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.Activity.Model.Committee_Table;
import com.indev.ruraldevelopment.R;

import java.util.ArrayList;

public class List_of_committee_member extends AppCompatActivity{
    RecyclerView rvCommittee;
    ArrayList<Committee_Table> committee_tableArrayList;
    FloatingActionButton addCommittee;
    Context context = this;
    EditText etSearchBar;
    SqliteHelper sqliteHelper;
    String name = "";
    String village_id="",villageProfileId;
    SharedPrefHelper sharedPrefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_committee_member);
        setTitle("Committee Member List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initiliaze();
        Bundle bundle =getIntent().getExtras();
        if (bundle !=null){

            villageProfileId = bundle.getString("villageProfileId","");
        }
        village_id = sharedPrefHelper.getString("village_id", "");


        committee_tableArrayList = sqliteHelper.getCommitteeData(village_id);
        AdapterCommittee adapter = new AdapterCommittee(this, committee_tableArrayList);
        rvCommittee.setHasFixedSize(true);
        rvCommittee.setLayoutManager(new LinearLayoutManager(this));
        rvCommittee.setAdapter(adapter);

        etSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search = etSearchBar.getText().toString();
                committee_tableArrayList = sqliteHelper.getCommitteeSearchData(search, village_id);
                AdapterCommittee registerAdapter = new AdapterCommittee(List_of_committee_member.this, committee_tableArrayList);
                int counter = committee_tableArrayList.size();
                //  FarmerCount.setText("Farmer 0"+counter);
                rvCommittee.setHasFixedSize(true);
                rvCommittee.setLayoutManager(new LinearLayoutManager(List_of_committee_member.this));
                rvCommittee.setAdapter(registerAdapter);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        addCommittee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_of_committee_member.this, Add_members.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("villageProfileId",villageProfileId);
                intent.putExtra("screen_type", "Committe");
                startActivity(intent);
            }
        });
        addCommittee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_of_committee_member.this, Add_members.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("screentype","Committe");
                startActivity(intent);
            }
        });


    }


    private void initiliaze() {
        committee_tableArrayList = new ArrayList<>();
        rvCommittee = findViewById(R.id.rvCommittee);
        addCommittee = findViewById(R.id.addCommittee);
        sqliteHelper = new SqliteHelper(this);
        sharedPrefHelper=new SharedPrefHelper(this);
        etSearchBar = findViewById(R.id.etSearchBar);
    }


    public void onBackPressed() {
        Intent intent = new Intent(List_of_committee_member.this,VillageDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // app icon in action bar clicked; go home
//                Intent intent = new Intent(this, HomeActivity.class);
//                intent.putExtra("user_id",user_id);
//                intent.putExtra("mobile",mobile);
//                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//                }
//    }


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