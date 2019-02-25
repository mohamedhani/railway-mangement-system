package com.example.mohamed.railwaymangementsystem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.mohamed.railwaymangementsystem.R;

public class UserAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
       Bundle bundle =  getIntent().getExtras();
       String name = bundle.getString("name");
        Log.v("name",name);
    }

    @Override
    public void onBackPressed() {
        onDestroy();
    }
}
