package com.example.mohamed.railwaymangementsystem.modules;

import java.util.ArrayList;

/**
 * Created by mohamed on 2/20/2019.
 */

public class User {
    private  String email , password , imgURl , firstName,lastName , gender;
    private ArrayList<Train> savedTrains ;

    public User(String email, String password, String imgURl, String firstName, String lastName, String gender, ArrayList<Train> savedTrains) {
        this.email = email;
        this.password = password;
        this.imgURl = imgURl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.savedTrains = savedTrains;
    }

    public User(String email, String password, String firstName, String lastName, String gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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


