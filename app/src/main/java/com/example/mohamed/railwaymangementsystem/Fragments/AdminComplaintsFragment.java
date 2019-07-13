package com.example.mohamed.railwaymangementsystem.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mohamed.railwaymangementsystem.APILayer.APIConnection;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.ComplaintData;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.ComplaintOutput;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.Json;
import com.example.mohamed.railwaymangementsystem.R;
import com.example.mohamed.railwaymangementsystem.controller.Adapters.ItemCLickListener;
import com.example.mohamed.railwaymangementsystem.controller.Adapters.RecycleViewAdapter;
import com.example.mohamed.railwaymangementsystem.utiltes.GlobalMethods;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminComplaintsFragment extends Fragment  implements Callback<ArrayList<ComplaintOutput>> , ItemCLickListener
{
    private RecyclerView recyclerView ;
    private RecycleViewAdapter recycleViewAdapter ;
    private ArrayList<ComplaintOutput> complaintOutputs;
    private LinearLayout emptyBoxLayout;
    private  APIConnection apiConnection ;
    private String jwt;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

                View mainView = inflater.inflate(R.layout.admin_complaints_fragment,container,false);
                prepareViews(mainView);
                apiConnection = APIConnection.getInstance();
                jwt= getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE).getString("jwt","");

                Call<ArrayList<ComplaintOutput>> call = apiConnection.getAllComplaint(new Json(jwt));
                call.enqueue(this);
                return  mainView ;
    }
      private void prepareViews(View mainView)
      {

       recyclerView = (RecyclerView) mainView.findViewById(R.id.admin_complaints_rv);
       emptyBoxLayout=(LinearLayout)mainView.findViewById(R.id.empty_complaint_layout);
       progressBar= mainView.findViewById(R.id.admin_complaint_progress_bar);
        progressBar.setVisibility(View.VISIBLE);

      }

    @Override
    public void onResponse(Call<ArrayList<ComplaintOutput>> call, Response<ArrayList<ComplaintOutput>> response) {
        progressBar.setVisibility(View.GONE);
        if(response.isSuccessful())
        {
            complaintOutputs=response.body();
            if(complaintOutputs.size()>0)
            {
                emptyBoxLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

               recycleViewAdapter = new RecycleViewAdapter(getActivity(), complaintOutputs  ,this);
                recyclerView.setAdapter(recycleViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


            }
            else {
                emptyBoxLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }


        }
        else
        {
            GlobalMethods.createToast(getActivity()," error");
        }
    }

    @Override
    public void onFailure(Call<ArrayList<ComplaintOutput>> call, Throwable t) {
        GlobalMethods.createToast(getActivity(),"connection error");
        progressBar.setVisibility(View.GONE);



    }


    @Override
    public void onItemClick(View view, final int position) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               GlobalMethods.createComplaintDialog(getActivity(),complaintOutputs.get(position));

            }
        });
    }


    }


