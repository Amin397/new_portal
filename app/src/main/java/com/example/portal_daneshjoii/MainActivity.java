package com.example.portal_daneshjoii;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portal_daneshjoii.classes.Functions;
import com.example.portal_daneshjoii.fragments.Darkhast_Daneshjoii;
import com.example.portal_daneshjoii.fragments.Entekhab_Vahed;
import com.example.portal_daneshjoii.fragments.Omor_Amozeshi;
import com.example.portal_daneshjoii.fragments.Omor_Mali;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView Bottom_navigation;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView txt_Name , txt_Code;
    private ImageView img_Profile;

    private String std_name;
    private String std_nationalCode;
    private String std_code;

    Functions f1 = new Functions(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        initViews();

        //sharedData();

        //Navigation View
        setSupportActionBar(toolbar);
        toolbar.setTitle("تهران شمال");
        ActionBar actionBar = getSupportActionBar();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this , drawerLayout , toolbar , 0 , 0);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(mNavigationDrawerItemSelectedListener);

        Bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Bottom_navigation.setSelectedItemId(R.id.omor_amozeshi_bottom_id);

    }

    private void sharedData() {
        SharedPreferences saver = this.getSharedPreferences("login" , Context.MODE_PRIVATE);
        std_name = saver.getString("FullName" , null);
        txt_Name.setText(std_name);
        std_code = saver.getString("StudentCode" , null);
        txt_Code.setText(std_code);
        std_nationalCode = saver.getString("NationalCode" , null);
        String image = saver.getString("ProfilePic" , null);
        Picasso.get().load(image).into(img_Profile);
    }

    private NavigationView.OnNavigationItemSelectedListener mNavigationDrawerItemSelectedListener
             = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.person_info_id:{
                    Toast.makeText(MainActivity.this, "person info", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    break;
                }
                case R.id.mail_id:{
                    Toast.makeText(MainActivity.this, "mails", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    break;
                }
                case R.id.parvande_id:{
                    Toast.makeText(MainActivity.this, "parvande", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    break;
                }
                case R.id.kholase_vaze_tahsili_id:{
                    Toast.makeText(MainActivity.this, "vaziat_tahsili", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    break;
                }
                case R.id.map_id:{
                    Toast.makeText(MainActivity.this, "map", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    break;
                }
                case R.id.phone_id:{
                    f1.phoneChange(MainActivity.this , std_nationalCode, getLayoutInflater());
                    drawerLayout.closeDrawers();
                    break;
                }
                case R.id.exit_id:{
                    Toast.makeText(MainActivity.this, "exit", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    break;
                }
                case R.id.change_pass_id:{
                    f1.passwordChange(MainActivity.this , getLayoutInflater() , std_code);
                    drawerLayout.closeDrawers();
                    break;
                }
            }
            return false;
        }
    };

    private void initViews() {
        Bottom_navigation = (BottomNavigationView) findViewById(R.id.navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main_id);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_id);
        navigationView = (NavigationView) findViewById(R.id.navigation_view_id);

    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "برای خروج دوباره بزنید !", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        } , 2000);



        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.omor_mali_bottom_id:{
                    fragment = new Omor_Mali();
                    loadFragment(fragment);
                    return true;
                }
                case R.id.omor_amozeshi_bottom_id:{
                    fragment = new Omor_Amozeshi();
                    loadFragment(fragment);
                    return true;
                }
                case R.id.darkhast_daneshjoii_bottom_id:{
                    fragment = new Darkhast_Daneshjoii();
                    loadFragment(fragment);
                    return true;
                }
                case R.id.entekhab_vahed_bottom_id:{
                    fragment = new Entekhab_Vahed();
                    loadFragment(fragment);
                    return true;
                }
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_tab_id , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
