package com.example.WIFI_HANDLER;

import java.util.Currency;
import android.widget.Toast;

import com.example.WIFI_HANDLER.MainActivity;
import com.example.WIFI_HANDLER.DB.DB;
import com.example.db_test1.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Fragment_Activity extends Activity {
	private DB Database;
	private Wifi_data wifi;
	
	private Button Create_DB;
	private Button Destroy_DB;
	private Button Connect_DB;
	private Button turn_on_wifi;
	private Button Add_to_DB;
	private Button Delete_row;
	private Button current_position;
	private TextToSpeech tts;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
	    
	    setContentView(R.layout.fragment_main);
	    
	    Log.d("FragActivity.java", "OnCreate Method");
	    
	    
		Log.d("FragActivity.java", "calling DB Constructor ");
		Database = new DB(Fragment_Activity.this);
		
	
	

		Connect_DB = (Button)findViewById(R.id.Connect_DB);
		Log.d("FragActivity.java", "Connect_DB button component found");
		System.out.println(Connect_DB);
		
		Create_DB = (Button)findViewById(R.id.Create_DB);
		Log.d("FragActivity.java", "Create_DB button component found");
		System.out.println(Create_DB);
		
		Destroy_DB = (Button)findViewById(R.id.Destroy_DB);
		Log.d("FragActivity.java", "Destroy_DB button component found");
		System.out.println(Destroy_DB);
		
	
		
		
		Connect_DB.setOnClickListener(new View.OnClickListener() 
		{
		@Override
		
		public void onClick(View v)
			{
			Log.d("FragActivity.java", "inside Connect_DB OnClickListener()");
			Log.d("FragActivity.java", "inside Connect_DB OnClick()");
			// TODO Auto-generated method stub
			Database.open();
			}
		});
		
		
		Create_DB.setOnClickListener(new View.OnClickListener() 
		{
		@Override
		
		public void onClick(View v)
			{
			Log.d("FragActivity.java", "inside Create_DB OnClickListener()");
			Log.d("FragActivity.java", "inside Create_DB OnClick()");
			// TODO Auto-generated method stub
			Database.create();
			}
		});
		
		
		Destroy_DB.setOnClickListener(new View.OnClickListener() 
		{
		@Override
		
		public void onClick(View v)
			{
			Log.d("FragActivity.java", "inside Destroy_DB OnClickListener()");
			Log.d("FragActivity.java", "inside Destroy_DB OnClick()");
			// TODO Auto-generated method stub
			Database.destroy();
			}
		});
		
		
	/////////////////////////////////////////////////////////// wifi part
		
		Log.d("FragActivity.java", "instantiating wifi_data class");
		wifi = new Wifi_data(Fragment_Activity.this);
		
		
		turn_on_wifi = (Button)findViewById(R.id.MapView);
		Log.d("FragActivity.java", "turn_on_wifi button component found");
		System.out.println(turn_on_wifi);
		
		final TextView scan_result_textbox = (TextView)findViewById(R.id.direction_results);
		scan_result_textbox.setSingleLine(false);
		
		turn_on_wifi.setOnClickListener(new View.OnClickListener() 
		{
		@Override
		
		public void onClick(View v)
			{
			Log.d("FragActivity.java", "inside turn_on_wifi OnClickListener()");
			Log.d("FragActivity.java", "inside turn_on_wifi OnClick()");
			// TODO Auto-generated method stub
			wifi.turn_on();
			Log.d("FragActivity.java", "turn_on_wifi OnClick() done +++");
			//wifi.wifi_receiver.result();
			
			String[] available_wifi_names1 = wifi.return_available_wifi_names();
			int[] levels1 = wifi.return_levels();
			
			String temp_scan_result_holder="";
			for(int i=0;i<=available_wifi_names1.length-1;i++)
			{
				temp_scan_result_holder = temp_scan_result_holder+"| SSID "+available_wifi_names1[i] + "~~ Level "+levels1[i]+" |====== \n";
			}
			
			scan_result_textbox.setText(temp_scan_result_holder);
			
			}
		});
		///////////////////////////////////////////////////////////////////
		
		String[] available_wifi_names;
		int[] levels;
		
		
		
		Add_to_DB = (Button)findViewById(R.id.Add_to_DB);
		Log.d("FragActivity.java", "Add_to_DB button component found");
		System.out.println(Add_to_DB);
		
		final EditText x_et = (EditText)findViewById(R.id.x_coord_data);
		Log.d("FragActivity.java", "x_coord edittext found");
		
		final EditText y_et = (EditText)findViewById(R.id.y_coord_data);
		Log.d("FragActivity.java", "y_coord edittext found");
		
		Add_to_DB.setOnClickListener(new View.OnClickListener() 
		{
		@Override
		
		public void onClick(View v)
			{
			Log.d("FragActivity.java", "inside Add_to_DB OnClickListener()");
			Log.d("FragActivity.java", "inside Add_to_DB OnClick()");
			// TODO Auto-generated method stub
			int x = Integer.parseInt(x_et.getText().toString());
			System.out.println("value of x coord "+x);
			
			int y = Integer.parseInt(y_et.getText().toString());
			System.out.println("value of y coord "+y);
			
			String[] available_wifi_names = wifi.return_available_wifi_names();
			int[] levels = wifi.return_levels();
			
			
			Database.insert(x,y,available_wifi_names,levels);
			//wifi.wifi_receiver.result();
			
			}
		});
		
		
		
		
		////////////////////////// Delete unwanted row ///////////////////////////////
		
		Delete_row = (Button)findViewById(R.id.Delete_row);
		Log.d("FragActivity.java", "Delete_row button component found");
		System.out.println(Delete_row);
		
		final EditText Delete_x = (EditText)findViewById(R.id.Delete_x);
		Log.d("FragActivity.java", "Delete_x edittext found");
		
		final EditText Delete_y = (EditText)findViewById(R.id.Delete_y);
		Log.d("FragActivity.java", "Delete_y edittext found");
		
		Delete_row.setOnClickListener(new View.OnClickListener() 
		{
		@Override
		
		public void onClick(View v)
		{
			int x = Integer.parseInt(Delete_x.getText().toString());
			int y = Integer.parseInt(Delete_y.getText().toString());
			Database.Delete_row(x,y);
						
		}
		});
		
		////////////////////////////////////// My Current Location //////////////////////////
		
		current_position = (Button)findViewById(R.id.current_position);
		Log.d("FragActivity.java", "current_position button component found");
		System.out.println(current_position);
		
		final TextView current_x = (TextView)findViewById(R.id.current_x);
		Log.d("FragActivity.java", "current_x edittext found");
		
		final TextView current_y = (TextView)findViewById(R.id.current_y);
		Log.d("FragActivity.java", "current_y edittext found");
		
		current_position.setOnClickListener(new View.OnClickListener() 
		{
		@Override
		
		public void onClick(View v)
		{			Database.open();
		wifi.turn_on();
		String[] available_wifi_names2 = wifi.return_available_wifi_names();
		int[] levels2 = wifi.return_levels();
		int[] current_position_list = {-1,-1};
		
			/*try{

			current_position_list = Database.current_position(available_wifi_names2,levels2);
			Log.d("FragActivity.java", "current_position_list values returned");
			Log.d("FragActivity.java", "caught result values"+current_position_list[0]+"^^"+current_position_list[1]);
			current_x.setText(current_position_list[0]+"");
			current_y.setText(current_position_list[1]+"");
		}catch(NullPointerException e)
		{System.out.println("exception caught");e.printStackTrace();
		current_x.setText(current_position_list[0]+"");
		current_y.setText(current_position_list[1]+"");}
*/
		}
		});
		
	}
	
	

}
