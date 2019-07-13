package com.example.mohamed.railwaymangementsystem.APILayer;

import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.ComplaintData;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.ComplaintOutput;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.Json;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.LoginData;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.RegisterData;
import com.example.mohamed.railwaymangementsystem.modules.APIModules.LoginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by mohamed on 7/4/2019.
 */

 interface RailwayAPI {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginData inputData);

    @POST("auth/signup")
    Call<String> register(@Body RegisterData inputData);
    @POST("complaint/addComplaint")
    Call<String> complaint(@Body ComplaintData inputData);
    @POST("complaint/getComplaints")
   Call<ArrayList<ComplaintOutput>> getAllComplaints(@Body Json jwt);
}
