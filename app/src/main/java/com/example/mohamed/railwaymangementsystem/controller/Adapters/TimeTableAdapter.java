package com.example.mohamed.railwaymangementsystem.controller.Adapters;

import android.content.Context;
import android.util.Log;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.mohamed.railwaymangementsystem.R;
import com.example.mohamed.railwaymangementsystem.TimeTableActivity;
import com.example.mohamed.railwaymangementsystem.modules.Station;
import com.example.mohamed.railwaymangementsystem.modules.Train;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

/**
 * Created by mohamed on 4/30/2019.
 */

public class TimeTableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<Train> trains;
    public TimeTableAdapter(Context context , ArrayList<Train> trains)
    {
        this.context=context;
        this.trains=trains;
    }
    @Override
    public int getGroupCount() {
        return trains.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return trains.get(i).getStations().size()-2;
    }

    @Override
    public Object getGroup(int groupId) {
        return trains.get(groupId);
    }

    @Override
    public Object getChild(int groupId, int childId) {
        return trains.get(groupId).getStations().get(childId);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        View myView = LayoutInflater.from(context).inflate( R.layout.time_table_group_layout,viewGroup,false);
        Train train = trains.get(i);
        String duration="";
        TextView trainTextView= (TextView)myView.findViewById(R.id.train_tv);
        TextView leaveTextView = (TextView )myView.findViewById(R.id.leave_tv);
        TextView arriveTextView =(TextView) myView.findViewById(R.id.arrive_tv);
        TextView durationTextView =(TextView) myView.findViewById(R.id.duration_tv);
        TextView speedTextView  =(TextView) myView.findViewById(R.id.speed_tv);
        TextView degreeTextView=(TextView) myView.findViewById(R.id.train_degree_tv);
        TextView stationNumberTextView  =(TextView) myView.findViewById(R.id.station_no_tV);
        trainTextView.setText(train.getTrainNumber()+"");
         String leave = train.getStations().get(0).getDateOfArrival();          //time of leave
        String arrive = train.getStations().get( train.getStations().size()- 1).getDateOfArrival(); // time of arrive
        SimpleDateFormat dateFormat =new SimpleDateFormat("hh:mm");
        try {
            long leaveTime = dateFormat.parse(leave).getTime();
            long arriveTime  =dateFormat.parse(arrive).getTime();
            long diffrenceMinutes  = TimeUnit.MILLISECONDS.toMinutes(arriveTime-leaveTime); ;
            duration=diffrenceMinutes/60+":"+diffrenceMinutes%60;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        leaveTextView.setText(leave);
        arriveTextView.setText(arrive);
       durationTextView.setText(duration+"");
        speedTextView.setText(train.getSpeed()+"");
        degreeTextView.setText(train.getType());
        stationNumberTextView.setText(train.getNumberOfStaion()+"");
        myView.setPadding(0,20,0,0);
        return myView;
    }

    @Override
    public View getChildView(int groupId, int childId, boolean b, View view, ViewGroup viewGroup) {
        View myView = LayoutInflater.from(context).inflate(R.layout.time_table_child_layout,viewGroup,false);
        TextView stationTextView = (TextView) myView.findViewById(R.id.station_tv);
        TextView arrivalTextView =(TextView) myView.findViewById(R.id.arrival_station_tv);
         Station station = trains.get(groupId).getStations().get(childId +1);
         stationTextView.setText(station.getName());
         arrivalTextView.setText(station.getDateOfArrival());

        return myView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
