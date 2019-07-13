package com.example.mohamed.railwaymangementsystem.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mohamed.railwaymangementsystem.LoginPackage.LoginActivity;
import com.example.mohamed.railwaymangementsystem.R;
import com.example.mohamed.railwaymangementsystem.TimeTableActivity;
import com.example.mohamed.railwaymangementsystem.UserAccount;
import com.example.mohamed.railwaymangementsystem.controller.Adapters.AutoCompleteAdapter;
import com.example.mohamed.railwaymangementsystem.controller.DatabaseAccess;
import com.example.mohamed.railwaymangementsystem.modules.Train;
import com.example.mohamed.railwaymangementsystem.utiltes.GLobalData;
import com.example.mohamed.railwaymangementsystem.utiltes.GlobalMethods;

import java.util.ArrayList;

public class TrainSchudlingFragment extends Fragment  implements View.OnClickListener{
    private Spinner trainTypeSpinner;
    private Button searchButton;
    private Button logOutButton;

    DatabaseAccess databaseAccess ;
    private AutoCompleteTextView fromAutoCompleteEditText , toAutoCompleteEditText;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View mainView =  inflater.inflate(R.layout.train_schudling_fragment,container,false);
        databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();
        ArrayList<String> stations = databaseAccess.getStations();
        prepareViews(mainView);
        PrepareAutoCompleteEditText(stations);
        return mainView;

    }
    private void prepareViews( View view)
    {
        trainTypeSpinner = (Spinner) view.findViewById(R.id.train_type_Scheduling_spinner);
        fromAutoCompleteEditText = (AutoCompleteTextView) view.findViewById(R.id.from_train_scheduling_ed);
        toAutoCompleteEditText  =(AutoCompleteTextView) view.findViewById(R.id.to_train_scheduling_ed);
        searchButton = (Button) view.findViewById(R.id.search_train_scheduling_btn);
        logOutButton=view.findViewById(R.id.log_out_btn);
        logOutButton.setOnClickListener(this);
    }
    private void  PrepareAutoCompleteEditText(final ArrayList<String> stations)

    {

       AutoCompleteAdapter fromStationArrayAapter = new AutoCompleteAdapter(getActivity(),R.layout.auto_complete_item_layout,stations);
        AutoCompleteAdapter tpStationArrayAdapter = new AutoCompleteAdapter(getActivity(),R.layout.auto_complete_item_layout,stations);
        fromAutoCompleteEditText.setAdapter(fromStationArrayAapter);
        fromAutoCompleteEditText.setThreshold(1);
        toAutoCompleteEditText.setAdapter(tpStationArrayAdapter);
        toAutoCompleteEditText.setThreshold(1);
        databaseAccess.close();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromStation = fromAutoCompleteEditText.getText().toString();
                String toStation = toAutoCompleteEditText.getText().toString();
                String type = trainTypeSpinner.getSelectedItem().toString();

                if(TextUtils.isEmpty(fromStation))
                {
                  GlobalMethods.createAlertDialog(getActivity(),"enter from station");
                }
                else if(TextUtils.isEmpty(toStation))
                {     GlobalMethods.createAlertDialog(getActivity(),"enter to station");

                }
                else if (TextUtils.equals(fromStation,toStation))
                {     GlobalMethods.createAlertDialog(getActivity(),"stations are the same");
                }
                else if(!stations.contains(fromStation) || !stations.contains(toStation))
                {     GlobalMethods.createAlertDialog(getActivity(),"select station from the menu");

                }
                else
                {


                    Intent intent = new Intent(getActivity(), TimeTableActivity.class);
                     intent.putExtra("fromStationTextView",fromStation);
                     intent.putExtra("toStationTextView",toStation);
                     intent.putExtra("type","express");
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        SharedPreferences.Editor preEditor = getActivity().getSharedPreferences(GLobalData.LOGIN_PREFRENCE, Context.MODE_PRIVATE).edit();
       SharedPreferences preferences = getActivity().getSharedPreferences(GLobalData.LOGIN_PREFRENCE,Context.MODE_PRIVATE);
        preEditor.putString("jwt","");
        preEditor.putString("email","");
        preEditor.apply();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      //  getActivity().finish();
    }
}
/*
        for(int i = 0 ; i<trains.size();i++)
        {
            Log.v("train id", trains.get(i).getTrainNumber()+"");
            Log.v("train type", trains.get(i).getType());
            Log.v("no of stations" , trains.get(i).getNumberOfStaion()+"");
            Log.v("speed",trains.get(i).getSpeed()+"");
            for(int j =0 ;j< trains.get(i).getStations().size();j++)
            {
                Log.v("station "+ j+1, trains.get(i).getStations().get(j).getName());
                Log.v("station "+ j+1, trains.get(i).getStations().get(j).getDateOfArrival());


            }
        }
 */