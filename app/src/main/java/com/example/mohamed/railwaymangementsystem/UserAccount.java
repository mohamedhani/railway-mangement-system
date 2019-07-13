package com.example.mohamed.railwaymangementsystem;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.mohamed.railwaymangementsystem.controller.Adapters.FragementsAdapters;
import com.example.mohamed.railwaymangementsystem.utiltes.GLobalData;

public class UserAccount extends AppCompatActivity {
    private ViewPager viewPager ;
    private TabLayout tabLayout ;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    int userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        String email =getSharedPreferences(GLobalData.LOGIN_PREFRENCE,MODE_PRIVATE).getString("email","");
        Log.v("email",email);
        prepareViwes(email);
    }
    private void  prepareViwes(String email)
    {
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        tabLayout=(TabLayout) findViewById(R.id.tab_layout);

        FragementsAdapters fragemntsAdapters  =new FragementsAdapters(getSupportFragmentManager(),email);
        viewPager.setAdapter(fragemntsAdapters);
        tabLayout.addTab(tabLayout.newTab().setText("Train scheduling"));
        tabLayout.addTab(tabLayout.newTab().setText("Train Location"));
        tabLayout.addTab(tabLayout.newTab().setText("Complaints"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#6495ED"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#006400"), Color.parseColor("#191970"));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
