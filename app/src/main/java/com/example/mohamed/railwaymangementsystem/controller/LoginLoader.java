package com.example.mohamed.railwaymangementsystem.controller;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
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
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by mohamed on 2/19/2019.
 */

public class LoginLoader extends AsyncTaskLoader<String> {
    private  String API ;
    private  Context context;
    public LoginLoader(Context context , String API)
    {   super(context);
        this.API=API;
        this.context=context;
    }
    @Override
    public String loadInBackground() {
        String output =null;
        try {
            URL url = new URL(API);

          output=  createHttpConnection(url);

        } catch (MalformedURLException e) {
            Log.v("URL","error in URl");
        } catch (IOException e) {
            Log.v("io","error in io");
        } catch (JSONException e) {
            Log.v("json","error with json");
        }
        return output;
    }

    private  String createHttpConnection (URL url) throws JSONException, IOException {
        StringBuilder JSONString =new StringBuilder();
        HttpURLConnection httpURLConnection =null;

        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
     //   httpURLConnection.setReadTimeout(2000);

        //OutputStreamWriter outputStreamWriter = new OutputStreamWriter( httpURLConnection.getOutputStream(),Charset.forName("UTF-8"));

        Log.v("login","connect");


            Log.v("login","done");
            // connect to server
        httpURLConnection.connect();

        JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_name","mohamed") ;
            jsonObject.put("password","45454");
            Log.v("json",jsonObject.toString());
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            Log.v("data",jsonObject.toString());
            bufferedWriter.write(jsonObject.toString());
            bufferedWriter.flush();
            outputStream.close();

            /////////////
           InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line=null;

            while ((line = bufferedReader.readLine()) != null)
                JSONString.append(line);
         //  JSONObject jsonObject =new JSONObject(JSONString.toString());*/
        return   JSONString.toString();


    }
}
