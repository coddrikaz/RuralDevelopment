package com.indev.ruraldevelopment.Activity.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.R;

public class Main_Menu extends App_drawer {
    CardView cv_jansanchetna;
    CardView cv_help;
    CardView cv_syn;
    CardView cv_infra;
    CardView cv_resource;
    CardView cv_stake;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    SharedPrefHelper sharedPrefHelper;
    SqliteHelper sqliteHelper;
    String resoureScrn="";
    String jansanchetnaScrn="";
//    private NavigationView navigationView;
//    private Menu mainmenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //setTitle("Main Menu");
        getSupportActionBar().hide();

        cv_jansanchetna = findViewById(R.id.cv_jansanchetna);
        cv_help = findViewById(R.id.cv_help);
        cv_infra = findViewById(R.id.cv_infra);
        cv_resource = findViewById(R.id.cv_resource);
        cv_stake = findViewById(R.id.cv_stake);
        cv_syn = findViewById(R.id.cv_syn);
        sharedPrefHelper=new SharedPrefHelper(this);
        sqliteHelper=new SqliteHelper(this);
//        navigationView.inflateMenu(R.menu.example_menu);
//        mainmenu = navigationView.getMenu();

//        drawerLayout = findViewById(R.id.drawable);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        NavigationView navigationView=findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if (item.getItemId()==R.id.logout)
//                {
//                    sharedPrefHelper.setString("isLogin","");
//                    Intent intent=new Intent(Main_Menu.this,Login.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    finishAffinity();
//                }
//                DrawerLayout drawerLayout=findViewById(R.id.drawable);
//                drawerLayout.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });

        cv_syn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Menu.this,Synchronize.class);
                startActivity(intent);
            }
        });

        cv_help.setOnClickListener(view ->
        {
            Intent intent= new Intent(Main_Menu.this,Help.class);
            startActivity(intent);
        });


        cv_stake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Menu.this, List_of_stakeholder_survey.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        cv_infra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Menu.this, List_of_InfraStructure.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        cv_resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefHelper.setString("resource_screen_type","resource");
                Intent intent = new Intent(Main_Menu.this, List_of_resources.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        cv_jansanchetna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPrefHelper.setString("resource_screen_type","jansanchetna");
                Intent intent = new Intent(Main_Menu.this, VillageDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        // builder.setIcon(R.drawable.ic_dialog_alert);
        builder.setTitle("Alert!");
        builder.setIcon(R.drawable.alert);
        builder.setMessage(R.string.are_you_sure_to_want_to_exitapplication);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
              finishAffinity();
            }
        });


        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }


//            public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
////                onBackPressed();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//                }
//    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            switch (item.getItemId()) {
//
//                case R.id.home:
//                    Toast.makeText(this, "My Account selected", Toast.LENGTH_SHORT).show();
//                    Intent intent1 = new Intent(this, Login.class);
//                    startActivity(intent1);
//                    return true;
                case R.id.logout:
                    Toast.makeText(this, "Logout selected", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(this, Login.class);
                    startActivity(intent2);
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }


        }

        return false;
    }



}



