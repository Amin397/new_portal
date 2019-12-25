package com.example.portal_daneshjoii;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.portal_daneshjoii.fragments.Darkhast_Daneshjoii;
import com.example.portal_daneshjoii.fragments.Entekhab_Vahed;
import com.example.portal_daneshjoii.fragments.Omor_Amozeshi;
import com.example.portal_daneshjoii.fragments.Omor_Mali;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.omor_amozeshi_bottom_id);
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

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_tab_id , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
