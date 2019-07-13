package com.example.mohamed.railwaymangementsystem.controller;

import android.content.Context;

import com.example.mohamed.railwaymangementsystem.modules.Station;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by mohamed on 4/9/2019.
 */

public class DatabaseOpenHelper  extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "rail.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
  public static    class  TrainTable
    {
        static  final   String  TABLE_NAME =  "train";
        static  final   String TRAIN_ID = "train_id";
        static  final   String TRAIN_NUMBER ="train_number";
        static  final   String TRAIN_TYPE = "train_type";
        static  final   String TRAIN_SPEED ="train_speed";
    }
   public static class  StationTable
   { static  final   String  TABLE_NAME = "station";
       static  final   String STATION_ID = "station_id";
       static  final  String  STATION_NAME = "stationName";
       static  final   String LONGITUDE = "longitude";
       static  final   String LATITUDE = "latitude";

   }
   public static class RouteTable
   {    static  final   String  TABLE_NAME = "route";
       static  final   String STOP_NO = "stop_no";
       static  final   String TRAIN_ID = "train_id";
       static final String ARRIVAL_TIME = "arrival_time";
   }
   public  static class ConsistOfTable {
       static  final   String  TABLE_NAME = "consist_of";
       static  final   String TRAIN_ID ="train_id";
       static  final   String STATION_ID = "station_id";
       static  final   String STOP_NO = "stop_no";
   }
}
