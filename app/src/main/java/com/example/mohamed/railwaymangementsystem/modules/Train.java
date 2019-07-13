package com.example.mohamed.railwaymangementsystem.modules;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by mohamed on 4/14/2019.
 */

public class Train  {
    private  int trainNumber , speed  , numberOfStaion ;
    private  String type ;
    private ArrayList<Station> stations ;
    public Train(int trainNumber, int speed, int numberOfStaion, String type ) {
        this.trainNumber = trainNumber;
        this.speed = speed;
        this.numberOfStaion = numberOfStaion;
        this.type = type;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public int getSpeed() {
        return speed;
    }

    public int getNumberOfStaion() {
        return numberOfStaion;
    }

    public String getType() {
        return type;
    }

/*
    @Override
    public int describeContents() {
        return  hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(trainNumber);
        parcel.writeInt(speed);
        parcel.writeInt(numberOfStaion);
        parcel.writeString(type);
        parcel.writeArray(stations.toArray());
    }
    public  static final  Creator<Train> creator = new Creator<Train>() {
        @Override
        public Train createFromParcel(Parcel parcel) {
            return new Train(parcel);
        }

        @Override
        public Train[] newArray(int i) {
            return new Train[0];
        }
    };
    protected  Train(Parcel in)
    {
        trainNumber = in.readInt();
        speed = in.readInt();
        numberOfStaion=in.readInt();
        type=in.readString();
        stations=in.readA
    }*/
}
