package com.example.mohamed.railwaymangementsystem.APILayer;

import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.ComplaintData;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.ComplaintOutput;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.LoginData;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.RegisterData;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.Json;
import com.example.mohamed.railwaymangementsystem.modules.APIModules.LoginResponse;
import com.example.mohamed.railwaymangementsystem.utiltes.GLobalData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by mohamed on 7/3/2019.
 */

public class APIConnection {


    static APIConnection instance;
    private Retrofit retrofit;
    RailwayAPI loginApi;

    private APIConnection() {
        retrofit = new Retrofit.Builder()
                .baseUrl(GLobalData.API_URI)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loginApi = retrofit.create(RailwayAPI.class);

    }

    public static APIConnection getInstance() {
        if (instance == null) {

            instance = new APIConnection();

        }
        return instance;
    }

    public Call<LoginResponse> login(String email, String password) {
        LoginData loginInput = new LoginData(email, password);
        Call<LoginResponse> call = loginApi.login(loginInput);
        return call;

    }
    public Call<String> register (String email , String password , String phoneNumber)
    {
      RegisterData input = new RegisterData(email,phoneNumber,password);
      Call<String> call =loginApi.register(input);
      return call;


    }
    public  Call<String> complaint(String trainId , String ticketNumber , String complaintMessage  ,String jwt)
    {
        ComplaintData input = new ComplaintData(complaintMessage,trainId,ticketNumber, jwt);
        Call<String> call = loginApi.complaint(input);
        return call;


    }
    public Call<ArrayList<ComplaintOutput>> getAllComplaint(Json jwt)
    {
        Call<ArrayList<ComplaintOutput>> call = loginApi.getAllComplaints(jwt);
        return call;

    }

}

