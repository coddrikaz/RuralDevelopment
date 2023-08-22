package com.indev.ruraldevelopment.Activity.Activity;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.indev.ruraldevelopment.Activity.Database.SharedPrefHelper;
import com.indev.ruraldevelopment.Activity.Database.SqliteHelper;
import com.indev.ruraldevelopment.R;

public class App_drawer extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private View view;
    private Menu menu;
    private FrameLayout frame;
    private DrawerLayout drawerLayout;
    public Toolbar toolbar;
    SharedPrefHelper sharedPrefHelper;
    SqliteHelper sqliteHelper;
    private Context context = this;
    TextView tv_nav;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        view = getLayoutInflater().inflate(R.layout.activity_app_drawer, null);
        frame = view.findViewById(R.id.frame);
        getLayoutInflater().inflate(layoutResID, frame, true);

        setContentView(view);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.inflateMenu(R.menu.mainmenu);
        menu = navigationView.getMenu();
        toolbar = findViewById(R.id.toolbar);
        tv_nav = navigationView.findViewById(R.id.tv_nav);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        sharedPrefHelper= new SharedPrefHelper(this);
        sqliteHelper= new SqliteHelper(this);

        View header = navigationView.getHeaderView(0);
        tv_nav = (TextView) header.findViewById(R.id.tv_nav);
        String gdghd= sharedPrefHelper.getString("user_name","");
        tv_nav.setText(gdghd);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                supportInvalidateOptionsMenu();
            }
        };


        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);//when using our custom drawer icon
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });



        menu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                int id = item.getItemId();
                switch (id) {

                    case R.id.village:
                        Intent aboutUs = new Intent(App_drawer.this, SelectVillage.class);
                        startActivity(aboutUs);
                        break;
                    case R.id.logout:
                        logoutDialog();
                        break;
                }
                return true;
            }
        });
    }

    private void logoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure that you want to logout?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPrefHelper.setString("isLogin","");

                        Intent intent = new Intent(context, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(R.drawable.alert)
                .show();
    }

}