package com.example.mohamed.railwaymangementsystem.modules;

import java.sql.Time;

/**
 * Created by mohamed on 4/14/2019.
 */

public class Station {
  private   String name , arrivalTime ;

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
}
