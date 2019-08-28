package com.example.mohamed.railwaymangementsystem;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mohamed.railwaymangementsystem.controller.DatabaseAccess;
import com.example.mohamed.railwaymangementsystem.modules.Station;

import java.util.ArrayList;
import java.util.Date;

public class TrackingActivity extends AppCompatActivity {
     DatabaseAccess databaseAccess ;
      FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
         frameLayout = findViewById(R.id.route_view);
        ArrayList <Station> arrayList = databaseAccess.getStationInfo();
        float totalDistance =0;
        ArrayList<Float> distance = new ArrayList<>();
        for(int i =0 ; i < arrayList.size()-1;i++) {
            float[] result = new float[1];
            Station startStation = arrayList.get(i);
            Station endStation = arrayList.get(i+1);
            Location.distanceBetween(startStation.getLattuide(), startStation.getLongitude()
                    , endStation.getLattuide(), endStation.getLongitude(), result);
           totalDistance+=result[0];
           distance.add(result[0]);
        }
        Log.v("total distance","d " +totalDistance);
        int marginLeft = (int) (90* this.getResources().getDisplayMetrics().density);
        int staticMargin =  (int) ( 60 *this.getResources().getDisplayMetrics().density);

        Log.v("margin ","d " +staticMargin);
        for(int i =0 ; i < distance.size();i++)
        {FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
         Log.v(arrayList.get(i).getName(),"distance"+distance.get(i));
          int  marginTop = staticMargin +  (int) (( (float) i/distance.size())* (400 *this.getResources().getDisplayMetrics().density));
            Log.v("margin ","d " +marginTop);
        lp.setMargins(marginLeft, marginTop, 0, 0);
            TextView textView = new TextView(this);
            textView.setText(arrayList.get(i).getName());
            frameLayout.addView(textView,lp);

        }


    }
}
