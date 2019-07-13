package com.example.mohamed.railwaymangementsystem.controller;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by mohamed on 4/9/2019.
 */

public class DataValidationLoader extends AsyncTaskLoader<String> {
    private  String email , password,phoneNumber;
    private JSONObject jsonData;
    private  String API ; // registation api link
    public DataValidationLoader(Context context  , JSONObject jsonData,String API)
    {   super(context);
        this.jsonData=jsonData;
        this.API =API;
    }
    @Override
    public String loadInBackground() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String output =null;
        try {
            Log.v("sss","start");
            URL url = new URL(API);

            output=  createHttpConnection(url);

        } catch (MalformedURLException e) {
            Log.v("URL","error in URl");
        } catch (IOException e) {
            Log.v("io","error in io");
        } catch (JSONException e) {
            Log.v("Json","error with Json");
        }
        return output;
    }
    private  String createHttpConnection (URL url) throws JSONException, IOException {
        String output = null;
        HttpURLConnection httpURLConnection =null;

        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/Json");

        //   httpURLConnection.setReadTimeout(2000);

        //OutputStreamWriter outputStreamWriter = new OutputStreamWriter( httpURLConnection.getOutputStream(),Charset.forName("UTF-8"));


        // connect to server
        byte[] outputBytes = jsonData.toString().getBytes();

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(outputBytes);

        httpURLConnection.connect();

        InputStream inputStream = httpURLConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line="";
        while ((line = bufferedReader.readLine()) != null)
            
        output =   new  JSONObject(line).getString("message");


        Log.v("output",output);
        return  output;


    }
}
