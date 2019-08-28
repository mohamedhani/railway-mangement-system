package com.example.mohamed.railwaymangementsystem.Fragments;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mohamed.railwaymangementsystem.R;
import com.example.mohamed.railwaymangementsystem.TrackingActivity;

public class TrainLocationFragement extends Fragment{
    Button button  ;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.train_location_fragement,container,false);
        button  = view.findViewById(R.id.train_tracking_search_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TrackingActivity.class);
                startActivity(intent);
            }
        });
        return  view;

    }
}
