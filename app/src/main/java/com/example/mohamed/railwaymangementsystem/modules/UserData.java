package com.example.mohamed.railwaymangementsystem.modules;

import java.util.ArrayList;

/**
 * Created by mohamed on 2/20/2019.
 */

public class UserData {
    private  String email , password , imgURl ;
    private ArrayList<Train> savedTrains ;

    public UserData(String email, String password, String imgURl, ArrayList<Train> savedTrains) {
        this.email = email;
        this.password = password;
        this.imgURl = imgURl;
        this.savedTrains = savedTrains;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImgURl() {
        return imgURl;
    }

    public ArrayList<Train> getSavedTrains() {
        return savedTrains;
    }

    private class Train {
        private String from , to ,trainType ;
    }

}


