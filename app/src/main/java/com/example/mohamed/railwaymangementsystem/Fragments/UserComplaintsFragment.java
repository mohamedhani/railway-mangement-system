package com.example.mohamed.railwaymangementsystem.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mohamed.railwaymangementsystem.APILayer.APIConnection;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.ComplaintData;
import com.example.mohamed.railwaymangementsystem.R;
import com.example.mohamed.railwaymangementsystem.controller.Adapters.SpinnerAdapter;
import com.example.mohamed.railwaymangementsystem.controller.DatabaseAccess;
import com.example.mohamed.railwaymangementsystem.utiltes.GLobalData;
import com.example.mohamed.railwaymangementsystem.utiltes.GlobalMethods;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserComplaintsFragment extends Fragment  implements AdapterView.OnItemSelectedListener , View.OnClickListener , Callback<String>{
    Spinner spinner ;
    DatabaseAccess databaseAccess ;
    TextView fromStationTextView, toStationTextView;
    Button  sendComplmaintsButton;
    EditText complaintMessageEditText , ticketNumberEditText ;
    ProgressBar progressBar;
    ArrayList<String> ids;
    APIConnection connection;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
     View mainView =    inflater.inflate(R.layout.user_complaints_fragment,container,false);
     connection=APIConnection.getInstance();
     databaseAccess = DatabaseAccess.getInstance(getActivity());

     databaseAccess.open();
      prepareViews(mainView);
        prepareSpinner();

        return  mainView;

    }
    private void prepareViews(View mainView)
    {
        spinner =(Spinner) mainView.findViewById(R.id.train_id_spinner);
        fromStationTextView =(TextView) mainView.findViewById(R.id.from_station);
        toStationTextView = (TextView)mainView.findViewById(R.id.to_station);
        sendComplmaintsButton =(Button)mainView.findViewById(R.id.complaint_send_btn);
        complaintMessageEditText= (EditText) mainView.findViewById(R.id.complaint_message_et);
        ticketNumberEditText= (EditText) mainView.findViewById(R.id.ticket_number_et);
        progressBar =  (ProgressBar) mainView.findViewById(R.id.user_complaint_progress_bar);
        sendComplmaintsButton.setOnClickListener(this);

    }
    private  void prepareSpinner()
    {
          ids= databaseAccess.getTrainsIds();
          ids.add(0,"select train number");
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(ids,getActivity());
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i!=0) {
            String startStation = databaseAccess.getStartStationByTrainId(ids.get(i));
            String endStation = databaseAccess.getEndStationByTrainId(ids.get(i));
            fromStationTextView.setText(startStation);
            toStationTextView.setText(endStation);
        }
        else
        {
            fromStationTextView.setText("");
            toStationTextView.setText("");

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        String complaintMessage = complaintMessageEditText.getText().toString();
        String ticketNumber  = ticketNumberEditText.getText().toString();
        String trainNumber =spinner.getSelectedItem().toString();
        if(TextUtils.isEmpty(complaintMessage) ||
           TextUtils.isEmpty(ticketNumber)||
            trainNumber.equals("select train number")    )

        {
            GlobalMethods.createToast(getActivity(),"fill all blanks");
        }
        else {

            String jwt = getActivity().getSharedPreferences(GLobalData.LOGIN_PREFRENCE,Context.MODE_PRIVATE)
                        .getString("jwt","");
           Call<String> call = connection.complaint(trainNumber,ticketNumber,complaintMessage,jwt);
           progressBar.setVisibility(View.VISIBLE);
           call.enqueue(this);

        }

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        progressBar.setVisibility(View.GONE);
        if(response.isSuccessful())
        {
            GlobalMethods.createAlertDialog(getActivity(),"تم وصول المشكله وسنعمل علي حلها في اقرب وقت");

        }
        else
        {
            GlobalMethods.createToast(getActivity(),"there is an connection error ");
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        progressBar.setVisibility(View.GONE);
        GlobalMethods.createToast(getActivity(),"there is an connection error ");
    }
}
