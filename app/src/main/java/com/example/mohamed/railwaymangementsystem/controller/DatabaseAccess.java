package com.example.mohamed.railwaymangementsystem.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.mohamed.railwaymangementsystem.modules.Station;
import com.example.mohamed.railwaymangementsystem.modules.Train;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 4/9/2019.
 */

public class DatabaseAccess {
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance ;

    private  DatabaseAccess(Context context) {
        this.sqLiteOpenHelper= new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = sqLiteOpenHelper.getWritableDatabase();
    }
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     *
     * @param stationName
     * @return stationID
     *  this function return the id of station
     */
    public  int getStationId ( String stationName)
    {

        String query = "select station_id from station where stationName =  \""+stationName + "\"";

        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex("station_id"));
        cursor.close();
        return  id;
    }

    /**
     *
     * @param fromStation
     * @param toStation
     * get the train from station to another station
     * @return
     */
  public   ArrayList<Train> getTrains (String fromStation , String toStation  , String type)
    {
        int from = getStationId(fromStation);
        int to =getStationId(toStation);

        ArrayList <Integer > trainsId = getTrainsId(from,to);
        ArrayList<Train> trains = new ArrayList<>();

      for (int i=0;i<trainsId.size();i++)
        {
            Train t = oneTrainQuery(trainsId.get(i),from,to,type);
            if(t!=null)
            trains.add(t);

        }
        return  trains;

    }

    /**
     *
     * @param trainId
     * @param from
     * @param to
     * @param type
     * @return stations , type , speed of each train
     */
        private  Train oneTrainQuery(int trainId , int from , int to , String type ) {
            Log.v(" from ", from+"");
            Log.v(" to ", to+"");


            // query of getting train from one station to another one
            String query = "select train_number,train_type, train_speed  ,stationName , arrival_time " +
                    "from train as t  , station as s  , route as r , consist_of as c " +
                    " where s.station_id = c.station_id and t.train_id = c.train_id and r.stop_no = c.stop_no and r.train_id = c.train_id  " +
                    "and t.train_id = "+trainId  +
                  " and r.stop_no >= (SELECT stop_no from consist_of where train_id = "+trainId+" and station_id = "+from +" ) " +
                   "and c.stop_no <= (SELECT stop_no from consist_of where train_id = "+trainId+
                    " and station_id ="+to+" )  ";


           if (type != "all")
            {
                query+=" and t.train_type = \""+type+ "\"";  // setting type of train if exist
            }
            query+= " group by c.stop_no";  // setting to order by of query

            Cursor cursor = database.rawQuery(query,null);// query


            if(cursor.getCount()==0)
            {
                cursor.close();
                return null;
            }
            cursor.moveToFirst();
            String trainType = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.TrainTable.TRAIN_TYPE)); // get train type
            int trainSpeed = cursor.getInt(cursor.getColumnIndex(DatabaseOpenHelper.TrainTable.TRAIN_SPEED));  // get train speed
            int numberOfStations = cursor.getCount();  //get  number of station for each train
            Train train = new Train(trainId,trainSpeed,numberOfStations,trainType);
            ArrayList<Station> stations = new ArrayList<>();
             // loop for getting the stations for each train
            while(! cursor.isAfterLast())
            {
                String stationName  = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.StationTable.STATION_NAME));
                String arrivalTime = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.RouteTable.ARRIVAL_TIME));
                stations.add(new Station(stationName,arrivalTime));
                cursor.moveToNext();
            }
            cursor.close();
            train.setStations(stations);

            return  train;

        }

    /**
     *
     * @param from
     * @param to
     * @return ArrayList<Integer>
     *     get the ids of trains that go from station to another station
     */
    public ArrayList<Integer> getTrainsId(int from, int to)
    {
        String query = "select e.train_id " +
                "from consist_of as e, consist_of as d " +
                "where e.station_id = "+ from  +
                " and d.station_id="+to+
                " and d.train_id=e.train_id" ;
        Cursor cursor= database.rawQuery(query,null);
        cursor.moveToNext();
        ArrayList<Integer> trainIds= new ArrayList<>();
        while (!cursor.isAfterLast())
        {
            trainIds.add(cursor.getInt(cursor.getColumnIndex("train_id")));
            cursor.moveToNext();
        }
        cursor.close();
        return trainIds;
    }

    /**
     *
     * @return Station
     * this function get the all station names in the data base
     */
    public  ArrayList<String> getStations ()
    {
        Cursor cursor = database.query(DatabaseOpenHelper.StationTable.TABLE_NAME,
                new String[]{"stationName"},
                null
                ,null
                ,null
                ,null
                ,null);
        ArrayList<String> stations = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            stations.add(cursor.getString(cursor.getColumnIndex("stationName")));
            cursor.moveToNext();
        }
        cursor.close();
            return stations;
    }
    public ArrayList<Train> queryTrain(String [] queryColumns ,String whereColumns, String [] values)
    {
         Cursor cursor =database.query(DatabaseOpenHelper.TrainTable.TABLE_NAME,queryColumns,whereColumns,values,null,null,null);
         ArrayList <Train> trains = new ArrayList<>();
        cursor.moveToFirst();
     /*   while (cursor.moveToNext())
        { Train train = new Train(cursor.getInt(cursor.getColumnIndex("train_id")) ,
                                   cursor.getInt(cursor.getColumnIndex("train_speed")),
                                    cursor.);
            trains.add()

        }*/
return  null;
    }
    public  ArrayList<String> getTrainsIds()
    {  ArrayList<String> output  = new ArrayList<>();
        Cursor cursor = database.rawQuery("select train_id  from train",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            output.add(String.valueOf(cursor.getInt(cursor.getColumnIndex("train_id"))));
            cursor.moveToNext();
        }
        return output;

    }
    public  String getStartStationByTrainId(String trainId)
    {
        String query ="select station.stationName   , min( consist_of.stop_no)\n" +
                "from station  , consist_of \n" +
                "where consist_of.station_id = station.station_id  \n" +
                "and consist_of.train_id= "+trainId;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        String station =cursor.getString( cursor.getColumnIndex("stationName" ));
        return station;
    }
    public  String getEndStationByTrainId(String trainId)
    {
        String query ="select station.stationName   , max( consist_of.stop_no)\n" +
                "from station  , consist_of \n" +
                "where consist_of.station_id = station.station_id  \n" +
                "and consist_of.train_id= "+trainId;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        String station =cursor.getString( cursor.getColumnIndex("stationName" ));
        return station;
    }
    public  ArrayList<Station> getStationInfo()
    {
        String query = "select * from station";
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        ArrayList<Station> arrayList = new ArrayList<>();
        while(!cursor.isAfterLast())
        {   String stationName = cursor.getString(cursor.getColumnIndex("stationName"));

            double longitude =  Double.parseDouble(cursor.getString(cursor.getColumnIndex("longitude") ));
            double latitude  = Double.parseDouble(cursor.getString(cursor.getColumnIndex("latitude")));

            arrayList.add(new Station(stationName,longitude,latitude));
            cursor.moveToNext();
        }
        return  arrayList;

    }

}


