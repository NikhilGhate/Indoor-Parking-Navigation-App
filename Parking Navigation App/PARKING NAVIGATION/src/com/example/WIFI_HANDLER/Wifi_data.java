package com.example.WIFI_HANDLER;

import java.util.ArrayList;
import java.util.List;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.Toast;
import android.net.wifi.ScanResult;
import android.os.Handler;

@SuppressWarnings("unused")
public class Wifi_data {
	
	Context wifi_context ;
	WifiManager wifiManager;
	List<ScanResult> wifiList;
	//WifiReceiver wifi_receiver;
	int size = 0;
	String scan_result = "" ;
	
	
	public Wifi_data(Fragment_Activity context) {
		// TODO Auto-generated constructor stub
		wifi_context = context;
	}
	
	public Wifi_data(Navigation_Activity context) {
		// TODO Auto-generated constructor stub
		wifi_context = context;
	}


	public Wifi_data(MainActivity context) {
		// TODO Auto-generated constructor stub
		wifi_context = context;
	}

	@SuppressWarnings("static-access")
	public void turn_on()
	{
		//Toast.makeText(wifi_context," Turning Wifi on ", Toast.LENGTH_LONG).show();

		final Toast toast = Toast.makeText(wifi_context, "Scanning", Toast.LENGTH_SHORT);
	    toast.show();

	    Handler handler = new Handler();
	        handler.postDelayed(new Runnable() {
	           @Override
	           public void run() {
	               toast.cancel(); 	          
	           }
	    }, 750);	
		
		
		wifiManager = (WifiManager) wifi_context.getSystemService(wifi_context.WIFI_SERVICE); 
		wifiManager.setWifiEnabled(true);
		Log.d("Wifi_data.java", "Inside Wifi_data - Turning Wifi on");
		scan_result();
	}
	
	public void scan_result()  // responsible for scanning current signals
	{
		
		Log.d("Wifi_data.java", "scan_result() nethod started");
		wifiManager.startScan();
		wifiList = wifiManager.getScanResults();
		Log.d("Wifi_data.java", "wifiManager.startScan() started");
		
		for(ScanResult SR : wifiList)
		{
		System.out.println("SSID "+SR.SSID + "~~ BSSID "+SR.BSSID+"~~ Level"+SR.level);
		
		scan_result = "SSID "+SR.SSID + "~~ BSSID "+SR.BSSID+"~~ Level "+SR.level+"======";
		}
		
		set_current_wifi_sign();
	}
	
	
	String[] available_wifi_names;
	int[] levels;
	
	public void set_current_wifi_sign()   // create arrays to store wifi names and their signal strengths
	{
		Log.d("Wifi_data.java", "set_current_wifi_sign() started");	
		available_wifi_names = new String[wifiList.size()];
		levels = new int[wifiList.size()];
		
		
		for(int i=0; i<wifiList.size(); i++)
		{
			available_wifi_names[i] = wifiList.get(i).SSID;
			levels[i] = wifiList.get(i).level;
		}

		Log.d("Wifi_data.java", "set_current_wifi_sign() Completed");
	}
	
	public String[] return_available_wifi_names()
	{
		return available_wifi_names;
	}
	
	public int[] return_levels()
	{
		return levels;
	}
    
        
	/*class WifiReceiver extends BroadcastReceiver
    {
		@Override
		public void onReceive(Context context, Intent intent)
		{
			// TODO Auto-generated method stub
            wifiList = wifiManager.getScanResults();
            Log.d("Wifi_data.java", "Inside WifiReceiver setting up WifiList to hold scan results");
            	
		}
		
            
            public void result()
            {
                for (ScanResult result : wifiList) {
                    Toast.makeText(wifi_context, result.SSID + " " + result.level,
                            Toast.LENGTH_SHORT).show();
            }
    }
	
    }*/
	
	
	
	
	
	
	
}
