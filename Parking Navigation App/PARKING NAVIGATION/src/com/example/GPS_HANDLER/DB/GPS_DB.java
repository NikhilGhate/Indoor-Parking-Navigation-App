package com.example.GPS_HANDLER.DB;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GPS_DB {
	
	SQLiteDatabase gpsdata_database;
	String db_path;
	String create_table = "CREATE TABLE \"GPS Readings\" (\"ID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"X\" INTEGER,+" +
			" \"Y\" INTEGER, \"Lat Top\" NUMERIC, \"Lat Bottom\" NUMERIC, \"Long Left\" NUMERIC, \"Long Right\" NUMERIC)";
	// assumption lat1 is smaller than lat2 and so is lon1<lon2
	
	String drop_table = "DROP TABLE IF EXISTS \"GPS Readings\"";
	
	
	
	public void connect_db_file()
	{
		db_path = "/storage/sdcard0/1stDatabase.sqlite";
		gpsdata_database = SQLiteDatabase.openDatabase(db_path, null, SQLiteDatabase.OPEN_READWRITE);
	}
	
	public void create_db_table()
	{
		gpsdata_database.execSQL(create_table);
	}

	
	public int[] get_x_y(double[] received_lat_lon)
	{
		connect_db_file();
		Log.d("GPS_DB.java","Opened GPS_Database--");
		int[] return_x_y = {-1,-1};
		try{
		double[] current_lat_lon = received_lat_lon;
		String query_to_get_x_y = "Select x,y FROM \"GPS Readings\" WHERE (\"Lat Top\"<="+current_lat_lon[0]+
				"  AND \"Lat Bottom\">="+current_lat_lon[0]+") AND (\"Long Left\"=<"+current_lat_lon[1]+
				"  AND \"Long Right\">="+current_lat_lon[1]+")";
		
		Cursor c=gpsdata_database.rawQuery(query_to_get_x_y, null);
		int return_x = c.getInt(0);
		int return_y = c.getInt(1);
		return_x_y[0]= return_x;
		return_x_y[1]= return_y;
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		catch(ArrayIndexOutOfBoundsException a)
		{
			a.printStackTrace();
		}
		return return_x_y;
		
	}
	
	
	
}
