package com.example.GPS_HANDLER.DB;

import com.example.WIFI_HANDLER.MainActivity;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class GPS {
	
	Context gps_context ;
	GPS_DB gpsdb;
	
	public GPS(MainActivity context) {
		// TODO Auto-generated constructor stub
		gps_context = context;
		gpsdb = new GPS_DB();
	}
	
	public double[] getLocation()
    {
	double[] return_location = new double[2];
     // Get the location manager
     LocationManager locationManager = (LocationManager)gps_context.getSystemService(gps_context.LOCATION_SERVICE);
     Criteria criteria = new Criteria();
     String bestProvider = locationManager.getBestProvider(criteria, false);
     Location location = locationManager.getLastKnownLocation(bestProvider);
     Double lat,lon;
     try {
       lat = location.getLatitude();
       lon = location.getLongitude();
       return_location[0]=lat;
       return_location[1]=lon;
       return return_location;
     	}
     catch (NullPointerException e){
         e.printStackTrace();
       return null;
     	}
    }
	
	public int[] get_x_y()
	{
		double current_loc[]= getLocation();
		int[] current_x_y = gpsdb.get_x_y(current_loc);
		return current_x_y;
	}

}
