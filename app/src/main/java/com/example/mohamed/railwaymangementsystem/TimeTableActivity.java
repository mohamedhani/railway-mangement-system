package com.example.mohamed.railwaymangementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.mohamed.railwaymangementsystem.controller.Adapters.TimeTableAdapter;
import com.example.mohamed.railwaymangementsystem.controller.DatabaseAccess;
import com.example.mohamed.railwaymangementsystem.modules.Train;

import java.util.ArrayList;

public class TimeTableActivity extends AppCompatActivity {
    ExpandableListView expandableListView ;
    TextView titleTextView ;
    DatabaseAccess databaseAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_time_table);
        Intent intent = getIntent();
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        String fromStation = intent.getStringExtra("fromStationTextView");
        String toStation = intent.getStringExtra("toStationTextView");
        Log.v("from",fromStation);
        Log.v("to",toStation);
        String type=intent.getStringExtra("type");
        ArrayList<Train> trains = databaseAccess.getTrains(fromStation,toStation,type.trim());
      preapreTitle(fromStation,toStation);
        prepareExpandableListView(trains);




    }
    private void preapreTitle( String fromStation, String toStation)
    {

        titleTextView =(TextView) findViewById(R.id.time_table_title);
        StringBuilder title = new StringBuilder();
        title.append("القطارات من ");
        title.append(fromStation);
        title.append(" إلي ");
        title.append(toStation);
        titleTextView.setText(title.toString());
    }
    private  void prepareExpandableListView ( ArrayList<Train> trains)
    { expandableListView= (ExpandableListView) findViewById(R.id.time_table_elv);
        TimeTableAdapter timeTableAdapter = new TimeTableAdapter( this,trains);
        expandableListView.setAdapter(timeTableAdapter);
    }
}
