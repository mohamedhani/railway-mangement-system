package com.example.mohamed.railwaymangementsystem.modules;

import java.sql.Time;

/**
 * Created by mohamed on 4/14/2019.
 */

public class Station {
  private   String name , arrivalTime  ;
  private  double longitude , lattuide ;

    public String getName() {
        return name;
    }

    public String getDateOfArrival() {
        return arrivalTime;
    }

    public Station(String name ,String arrivalTime) {
        this.name = name;
        this.arrivalTime=arrivalTime;
    }

    public double getLongitude() {
        return longitude;
    }

    public Station(String name, double longitude, double lattuide) {
        this.name = name;
        this.longitude = longitude;
        this.lattuide = lattuide;
    }

    public double getLattuide() {
        return lattuide;
    }


}
