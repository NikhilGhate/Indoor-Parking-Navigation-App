package com.example.WIFI_HANDLER.DB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.NEAREST_NEIGHBOUR.SearchXYToConsider;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DB {

	//private DBHelper DBHelper_Instance;
	public static final String DB_NAME = "1stDatabase.sqlite";
	public static final int DB_SCHEMA_VERSION = 1;
	
	public static final String SQL_CREATE_TABLE ="CREATE  TABLE  IF NOT EXISTS wifidata"+
			"(" +
			"\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ,"
			+ "x INTEGER NOT NULL ,"
			+ "y INTEGER NOT NULL ,"
			+ "\"2WIRE912\" INTEGER DEFAULT 0,"
			+ "\"ATT848\" INTEGER DEFAULT 0," ////
			+ "\"ATT384\" INTEGER DEFAULT 0,"
			+ "\"Westeros\" INTEGER DEFAULT 0,"  ////
			+ "\"ATT912\" INTEGER DEFAULT 0,"  ////
			+ "\"TullNet\" INTEGER DEFAULT 0,"
			+ "\"ATT424\" INTEGER DEFAULT 0,"
			+ "\"belkin.17b\" INTEGER DEFAULT 0," 
			+ "\"ATT704\" INTEGER DEFAULT 0," //
			+ "\"Sunshine\" INTEGER DEFAULT 0,"
			+ "\"Sameer\" INTEGER DEFAULT 0,"
			+ "\"HOME-D024\" INTEGER DEFAULT 0,"
			+ "\"ATT072\" INTEGER DEFAULT 0,"
			+ "\"HOME-6A72\" INTEGER DEFAULT 0,"
			+ "\"allen\" INTEGER DEFAULT 0,"
			+ "\"ATT528\" INTEGER DEFAULT 0," //
			+"\"HOME-0182\" INTEGER DEFAULT 0," 
			+"\"HOME-0972\" INTEGER DEFAULT 0," 
			+"\"HOME-DB88\" INTEGER DEFAULT 0," 
			+"\"ATT168\" INTEGER DEFAULT 0," 
			+ "\"HOME-1E92\" INTEGER DEFAULT 0,"
			+ "\"NETGEAR26\" INTEGER DEFAULT 0,"
			+ "\"Vitaaksi\" INTEGER DEFAULT 0,"
			+ "\"Biranavi\" INTEGER DEFAULT 0"
			+")";
	
	//public static final String Dont_allow_DB_CREATE = "SELECT EXISTS(SELECT COUNT(*) FROM Student)";
	public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS wifidata";
			

	private final class DBHelper extends SQLiteOpenHelper
	{

		public DBHelper(Context context) {
			super(context, DB_NAME, null, DB_SCHEMA_VERSION);
			Log.w("DB.java", "Inside DBHelper constructor BELOW Super");
			
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			//db.execSQL(SQL_DROP_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			//Log.w("DB.java", "Inside onUpgrade which drops and Calls oncreate method");
			//db.execSQL(SQL_DROP_TABLE);
			//onCreate(db);
		}
				
	}
	
	Context mContext;
	SQLiteDatabase mDB;
	private DBHelper DBHelper_Instance;
	
	public DB(Context context) {
		// TODO Auto-generated constructor stub
		Log.w("DB.java", "Inside DB constructor");
		mContext = context;
		Log.w("DB.java", "mContext created");
		Log.w("DB.java", "Exiting DB constructor");
	}
	
	/////////////////////////////////////////// open DB connection //////////////////////////////
	
	public void open()
	{
		Log.d("DB.java", "Inside DB's Open() and creating DBHelper");
		DBHelper_Instance = new DBHelper(mContext);
		Log.d("DB.java", "Inside DB's Open() and DBHelper is CREATED");
		String DB_path = "/storage/sdcard0/1stDatabase.sqlite";
		mDB = SQLiteDatabase.openDatabase(DB_path, null, SQLiteDatabase.OPEN_READWRITE);
		//Log.w("DB.java", "Explicitly calling onCreate to create DB");
		//DBHelper_Instance.onCreate(mDB);
		Log.d("DB.java",mContext.getDatabasePath(DB_NAME).toString());
		//Toast.makeText(mContext," DB opened ", Toast.LENGTH_LONG).show();
	}
	
	////////////////////////////////////////////// Database Creation ///////////////////////////////////
	public void create()
	{
		/*Log.d("DB.java", "check if DB exist already ");
		Cursor  cursor = mDB.rawQuery(Dont_allow_DB_CREATE,null);
		Log.d("DB.java", "Cursor run ");
		

		if (cursor != null) {
			Log.d("DB.java", "Inside create() - Cursor has something so cant create DB again");
			Toast.makeText(mContext,"DB Tables already exists DESTROY it first", Toast.LENGTH_LONG).show();
        }
		else*/
		{
			Log.d("DB.java", "Inside create() called by Create_DB button");
			Log.d("DB.java", "Original table will be destroyed");
			destroy();
		
		Log.d("DB.java",mContext.getDatabasePath(DB_NAME).toString());
		Toast.makeText(mContext," Creating DB i.e Table needed ", Toast.LENGTH_SHORT).show();
		mDB.execSQL(SQL_CREATE_TABLE);
		
		Log.d("DB.java","wifi data table created $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		}
		
	}
	
	
	///////////////////////////////////////////////  DROP Database /////////////////////////////////////////
	public void destroy()
	{
		Toast.makeText(mContext,"DB Tables will be destroyed", Toast.LENGTH_SHORT).show();
		Log.d("DB.java", "Inside destroy() called by Destroy_DB button");
		mDB.execSQL(SQL_DROP_TABLE);
		Log.d("DB.java", "DB Table Destroyed");
				
	}
	
	public void close() {
		mDB.close();
		
	}
	
	
	/////////////////////////////////////////INSERT DATA TO DB /////////////////////////////////////////////
	static int Global_insert_count = 0;
	
	public void insert(int a, int b, String[] available_wifi, int[] lvls)
	{
		
		String update;
		int x = a;
		int y = b;
		String[] available_wifi_names = available_wifi;
		int[] levels = lvls;

		
		
		String insert_stmt;
		insert_stmt = "insert into wifidata values(null,"+x+","+y+",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)";
		mDB.execSQL(insert_stmt);
		Log.d("DB.java", "insert stmt "+insert_stmt);
		Cursor max_id = mDB.rawQuery("Select max(id) from wifidata",null);
		max_id.moveToPosition(0);
		int highest_id = max_id.getInt(0);
		Log.d("DB.java", "highest row rank"+highest_id);
		
		for (int i=0; i<available_wifi_names.length;i++)
		{
			if(available_wifi_names[i].equals("2WIRE912"))
				{update = "UPDATE wifidata SET \"2WIRE912\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
				mDB.execSQL(update);Log.d("DB.java", "2WIRE912 inserted");
				}
		
			if(available_wifi_names[i].equals("ATT848"))
			{update = "UPDATE wifidata SET \"ATT848\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "ATT848 inserted");
			}
			
			if(available_wifi_names[i].equals("ATT384"))
			{update = "UPDATE wifidata SET \"ATT384\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "ATT384 inserted");
			}
			
			if(available_wifi_names[i].equals("Westeros"))
			{update = "UPDATE wifidata SET \"Westeros\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "Westeros inserted");
			}
			
			if(available_wifi_names[i].equals("ATT912"))
			{update = "UPDATE wifidata SET \"ATT912\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "ATT912 inserted");
			}
			
			if(available_wifi_names[i].equals("TullNet"))
			{update = "UPDATE wifidata SET \"TullNet\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "TullNet inserted");
			}
			
			if(available_wifi_names[i].equals("ATT424")) 
			{update = "UPDATE wifidata SET \"ATT424\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "ATT424 inserted");
			}
			
			if(available_wifi_names[i].equals("belkin.17b"))
			{update = "UPDATE wifidata SET \"belkin.17b\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "belkin.17b inserted");
			}
			
			
			if(available_wifi_names[i].equals("ATT704"))
			{update = "UPDATE wifidata SET \"ATT704\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "ATT704 inserted");
			}
			
			if(available_wifi_names[i].equals("Sunshine"))
			{update = "UPDATE wifidata SET \"Sunshine\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "Sunshine inserted");
			}
			
			if(available_wifi_names[i].equals("Sameer"))
			{update = "UPDATE wifidata SET \"Sameer\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "Sameer inserted");
			}
			
			if(available_wifi_names[i].equals("HOME-D024"))
			{update = "UPDATE wifidata SET \"HOME-D024\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "HOME-D024 inserted");
			}
			///////////////
			
			if(available_wifi_names[i].equals("ATT072"))
			{update = "UPDATE wifidata SET \"ATT072\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "ATT072 inserted");
			}
			
			
			if(available_wifi_names[i].equals("HOME-6A72"))
			{update = "UPDATE wifidata SET \"HOME-6A72\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "HOME-6A72 inserted");
			}
			
			if(available_wifi_names[i].equals("allen"))
			{update = "UPDATE wifidata SET \"allen\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "allen inserted");
			}
			
			
			if(available_wifi_names[i].equals("ATT528"))
			{update = "UPDATE wifidata SET \"ATT528\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "ATT528 inserted");
			}
			
			if(available_wifi_names[i].equals("HOME-0182"))
			{update = "UPDATE wifidata SET \"HOME-0182\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "HOME-0182 inserted");
			}
			
			
			if(available_wifi_names[i].equals("HOME-0972"))
			{update = "UPDATE wifidata SET \"Sunshine\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "HOME-0972");
			}
			
			
			if(available_wifi_names[i].equals("HOME-DB88"))
			{update = "UPDATE wifidata SET \"HOME-DB88\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "HOME-DB88 inserted");
			}
			
			
			if(available_wifi_names[i].equals("ATT168"))
			{update = "UPDATE wifidata SET \"ATT168\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "ATT168 inserted");
			}
			//
			if(available_wifi_names[i].equals("HOME-1E92"))
			{update = "UPDATE wifidata SET \"HOME-1E92\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "HOME-1E92 inserted");
			}
			
			if(available_wifi_names[i].equals("NETGEAR26"))
			{update = "UPDATE wifidata SET \"NETGEAR26\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "NETGEAR26 inserted");
			}
			
			if(available_wifi_names[i].equals("Vitaaksi"))
			{update = "UPDATE wifidata SET \"Vitaaksi\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "Vitaaksi");
			}
			
			
			if(available_wifi_names[i].equals("Biranavi"))
			{update = "UPDATE wifidata SET \"Biranavi\" = "+levels[i]+ " WHERE x="+x+" AND y="+y+" AND id="+highest_id;
			mDB.execSQL(update);Log.d("DB.java", "HOME-DB88 inserted");
			}
			
			
		}
		max_id.close();
		Global_insert_count = Global_insert_count+1;
	}
	
	
	////////////////////////////// DELETE A ROW FROM DB ///////////////////////////////
	public void Delete_row(int x, int y)
	{
		String delete_stmt = "DELETE FROM wifidata WHERE x="+x+" AND y="+y;
		mDB.execSQL(delete_stmt);
		Log.d("DB.java", "DELETE FROM wifidata WHERE x="+x+" AND y="+y);
	}
	
	///////////////////////////////////// CURRENT POSITION ////////////////////////
	String[] considered_wifi = {"2WIRE912","ATT848","ATT384","Westeros","ATT912","TullNet","ATT424","belkin.17b","ATT704","Sunshine","Sameer","HOME-D024","ATT072","HOME-6A72","allen","ATT528","HOME-0182","HOME-0972","NETGEAR94","HOME-DB88","ATT168","HOME-1E92","NETGEAR26","Vitaaksi","Biranavi"};

	ArrayList<Integer> available_considered_wifi_strength ;
	ArrayList<String> available_considered_wifi ;
	
	
	///// Strip Unnecessary wifi signals -- will get rid of those signals which are there in our scan but not there in underlying database
	public void strip_unnecessary_readings(String[] available_wifi, int[] lvls)
	{
		
		Log.d("DB.java", "inside strip_unnecessary_readings~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		//available_considered_wifi;
		//available_considered_wifi_strength;
		//int iterator = 0;
		available_considered_wifi_strength = new ArrayList<Integer>();
		available_considered_wifi = new ArrayList<String>();
		for(int i=0;i<available_wifi.length;i++)
		{//Log.d("DB.java", "available_wifi[i] "+ available_wifi[i]);
		// now iterate through CONSIDERED_WIFI Array to see if name is present in scanned wifi names array i.e. available_wifi
		
			for(int j=0;j<considered_wifi.length;j++)
				
			{
				//Log.d("DB.java", "considered_wifi[j] "+ considered_wifi[j]);
				
				if(available_wifi[i].equals(considered_wifi[j]))
				{
					//Log.d("DB.java", "match found");
					available_considered_wifi.add(considered_wifi[j]);
					//Log.d("DB.java", "available_considered_wifi[i] "+ available_considered_wifi[i]);
					available_considered_wifi_strength.add(lvls[i]);
					//Log.d("DB.java", "available_considered_wifi_strength[i] "+ available_considered_wifi_strength[i]);
					break;
				}
				
			}
			//Log.d("DB.java", "______-----------------------______");
		}
		Log.d("DB.java", "DONE strip_unnecessary_readings~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	///// Unnecessary wifi signals stripped NOW FIND CURRENT_POSITION
	int last_known_x = -1;  // last known x,y signals
	int last_known_y = -1;
	int last_known_result[]={last_known_x,last_known_y};
	int[][] previous_result = new int[2][2];                  ///////// this matrix stores last 2 cell positions 
	
	
	boolean first_time= true;
	int[][] x_y_count;		// 2D array - x,y,count - initialized to null - this array will store how many times a distinct 
											///cell was matched in the database
	int x_y_count_index; // x_y_count_index - row of 2D array to which the data of x,y,count will be added

	public int[] current_position(String[] available_wifi, int[] lvls, List<int[]> cellsOnRoute)  // this function is also considering NEAREST NEIGHBOUR
													// i.e. neighbouring cells
	{
		Log.d("DB.java", "INSIDE current_position~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

				// push first row entries on last row 
				previous_result[1][0]=previous_result[0][0];
				previous_result[1][1]=previous_result[0][1];
				// push last scan entries onto first row
				previous_result[0][0]=last_known_x;
				previous_result[0][1]=last_known_y;
				
			
		if(first_time)  // if opening this method first time then open database once
		{	System.out.println("Database opening for first time-- next time you wont see this");
			open();first_time=false;}
		
		Log.d("DB.java", "Calling strip_unnecessary_readings");
		strip_unnecessary_readings(available_wifi,lvls);
		
		Log.d("DB.java", "Calling x_y_to_consider");
		//SearchXYToConsider SC = new SearchXYToConsider();
		//String x_y_to_consider_condition = SC.x_y_to_consider(last_known_x,last_known_y);
		
		//string
		
		String qur = "AND (";
		for (int[] l:cellsOnRoute) {
			Log.d("#DB#l=",Arrays.toString(l));
			String temp = "(x="+l[0]+" AND y="+l[1]+")OR";
			qur+=temp;
		}
		qur+="(x=999 AND y=999))";
		Log.d(qur,"Qur="+qur);
		
		
		Log.d("DB.java", "String x_y_to_consider_condition "+qur);
		
		
		x_y_count = new int[50][3];   // 2D array - x,y,count - initialized to null  
		x_y_count_index = 0;  // x_y_count_index - row of 2D array to which the data of x,y,count will be added
		
				
		Cursor c1 = null;	// now we execute the query
		
		for(int index=0; index < available_considered_wifi.size();index++)  // we iterate over available_considered_wifi scan array one by one
		{
			Log.d("DB.java", "Going Inside cursor c1");
			c1 = mDB.rawQuery("SELECT x,y,count(*) FROM wifidata WHERE \""
			+available_considered_wifi.get(index)+"\" BETWEEN "+(available_considered_wifi_strength.get(index)-3)+" AND "+(available_considered_wifi_strength.get(index)+3)
			+qur+" AND (x!="+previous_result[1][0]+" AND y!="+previous_result[1][1]+") GROUP BY x,y ORDER BY count(*) DESC",null);
		
		
			Log.d("DB.java", "SELECT x,y,count(*) FROM wifidata WHERE \""+available_considered_wifi.get(index)+"\" BETWEEN "
			+(available_considered_wifi_strength.get(index)-3)+" AND "+(available_considered_wifi_strength.get(index)+3)+qur
			+" GROUP BY x,y ORDER BY count(*) DESC");
		
			Log.d("DB.java", "c1.getCount() rows in cursor "+c1.getCount());
			Log.d("DB.java", "******************************");
		
		
		
		if(c1.getCount()>=1)  // row count for cursor is greater than 1 hence we have to iterate within cursor
			{
			for(int i=0;i<c1.getCount();i++)		// this loop will run for all the rows in the cursor
				{
				Log.d("DB.java", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				c1.moveToPosition(i);
				Log.d("DB.java", "Considered row in cursor "+i+ " @@@@@");
				//int last_non_zero_row_index = -1;  // this will store which was the last non zero index so if you get new x y then you can add it to this row+1 index
					for(int j=0;j<50;j++)		// this loop will iterate over all the rows in x_y_count matrix to check if match is found
					{
					//Log.d("DB.java", "Considered "+j+" row in x_y_count and 0th element is "+x_y_count[j][0]+" @@@@");	
					
						if(x_y_count[j][0]!=0)		// perform check if starting cell of j th row is not empty
						{							// now row is not empty so u can iterate within the row to check if x and y matches
							//last_non_zero_row_index=j;
						// this is how you check within a row   if match found then update value
							if(c1.getInt(0)==x_y_count[j][0] && c1.getInt(1)==x_y_count[j][1])
								{  
									x_y_count[j][2]= x_y_count[j][2]+c1.getInt(2);
									//Log.d("DB.java", "Updated x_y_count[j][2] i.e. count at j to "+x_y_count[j][2]);
									break;
								} 
						}
						else  // if row is empty then u have gone through all the non empty cells (or this is first value to go in) 
										//and you haven't found match hence insert new row
						{
							Log.d("DB.java", "Since it was 0 i'm adding new row at "+j);
							x_y_count[j][0]=c1.getInt(0);
							x_y_count[j][1]=c1.getInt(1);
							x_y_count[j][2]=c1.getInt(2);
							//Log.d("DB.java", "inserted new row in x_y_count-- x "+x_y_count[j][0]+"--y "+x_y_count[j][1]+"--count "+x_y_count[j][2]);
							//last_non_zero_row_index=j;        //new row was inserted here i.e. last row of x_y_count matrix
							break;
						}
				
					}
					//Log.d("DB.java", "Last non zero index "+last_non_zero_row_index);
					Log.d("DB.java", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				}
			}
		
		}
		
		
		display_x_y_count();

		
		Log.d("DB.java", "now trying to Get x_y_count matrix's max counts index");
		
		int max_val_index = 0;  // find which row_index has max count in x_y_count as it is likely to be the current cell
		int max_count_value = -1;
		
		if(x_y_count[0][0] != 0)
		{
		for(int i=0; i<x_y_count.length;i++)
		{
			if(x_y_count[i][2]>max_count_value )
			{
				max_count_value = x_y_count[i][2];
				max_val_index=i;
				Log.d("DB.java", "max_count_value "+max_count_value);
				Log.d("DB.java", "max_val_index "+max_val_index);
				
				last_known_x=x_y_count[max_val_index][0];
				last_known_y=x_y_count[max_val_index][1];
				Log.d("DB.java", "last_known_x "+last_known_x);
				Log.d("DB.java", "last_known_y "+last_known_y);
			}
		}
		}

		
		last_known_result[0]=last_known_x;
		last_known_result[1]=last_known_y;
		
		
		Log.d("DB.java", "RETURNING current_position~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+Arrays.toString(last_known_result)+"~~~~~~~~~~~~~~");
		return last_known_result;
		
		
		
	} // end of current_position
	
	
	public int[] current_position(String[] available_wifi, int[] lvls) 
	{
		Log.d("DB.java", "INSIDE current_position~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

				// push first row entries on last row 
				previous_result[1][0]=previous_result[0][0];
				previous_result[1][1]=previous_result[0][1];
				// push last scan entries onto first row
				previous_result[0][0]=last_known_x;
				previous_result[0][1]=last_known_y;
				
			
		if(first_time)  // if opening this method first time then open database once
		{	System.out.println("Database opening for first time-- next time you wont see this");
			open();first_time=false;}
		
		Log.d("DB.java", "Calling strip_unnecessary_readings");
		strip_unnecessary_readings(available_wifi,lvls);
		
		Log.d("DB.java", "Calling x_y_to_consider");
		//SearchXYToConsider SC = new SearchXYToConsider();
		//String x_y_to_consider_condition = SC.x_y_to_consider(last_known_x,last_known_y);
		
		//string
		
		
		
		
		x_y_count = new int[50][3];   // 2D array - x,y,count - initialized to null  // REMEMBER THIS ???????????????????????????????????????????????????????????????????????????????????????????
		x_y_count_index = 0;  // x_y_count_index - row of 2D array to which the data of x,y,count will be added
		
		
		Log.d("DB.java", "SELECT x,y,count(*) FROM wifidata WHERE \""+"50"+"\" BETWEEN "
				+"0"+" AND "+"100"
				+" GROUP BY x,y ORDER BY count(*) DESC");
		
		Cursor c1 = null;	// now we execute the query
		
		for(int index=0; index < available_considered_wifi.size();index++)  // we iterate over available_considered_wifi scan array one by one
		{
			Log.d("DB.java", "Going Inside cursor c1");
			c1 = mDB.rawQuery("SELECT x,y,count(*) FROM wifidata WHERE \""
			+available_considered_wifi.get(index)+"\" BETWEEN "+(available_considered_wifi_strength.get(index)-3)+" AND "+(available_considered_wifi_strength.get(index)+3)
			+" AND (x!="+previous_result[1][0]+" AND y!="+previous_result[1][1]+") GROUP BY x,y ORDER BY count(*) DESC",null);
		
		
			Log.d("DB.java", "SELECT x,y,count(*) FROM wifidata WHERE \""+available_considered_wifi.get(index)+"\" BETWEEN "
			+(available_considered_wifi_strength.get(index)-3)+" AND "+(available_considered_wifi_strength.get(index)+3)
			+" GROUP BY x,y ORDER BY count(*) DESC");
		
			Log.d("DB.java", "c1.getCount() rows in cursor "+c1.getCount());
			Log.d("DB.java", "******************************");
		
		
		
		if(c1.getCount()>=1)  // row count for cursor is greater than 1 hence we have to iterate within cursor
			{
			for(int i=0;i<c1.getCount();i++)		// this loop will run for all the rows in the cursor
				{
				Log.d("DB.java", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				c1.moveToPosition(i);
				Log.d("DB.java", "Considered row in cursor "+i+ " @@@@@");
				//int last_non_zero_row_index = -1;  // this will store which was the last non zero index so if you get new x y then you can add it to this row+1 index
					for(int j=0;j<50;j++)		// this loop will iterate over all the rows in x_y_count matrix to check if match is found
					{
					//Log.d("DB.java", "Considered "+j+" row in x_y_count and 0th element is "+x_y_count[j][0]+" @@@@");	
					
						if(x_y_count[j][0]!=0)		// perform check if starting cell of j th row is not empty
						{							// now row is not empty so u can iterate within the row to check if x and y matches
							//last_non_zero_row_index=j;
						// this is how you check within a row   if match found then update value
							if(c1.getInt(0)==x_y_count[j][0] && c1.getInt(1)==x_y_count[j][1])
								{  
									x_y_count[j][2]= x_y_count[j][2]+c1.getInt(2);
									//Log.d("DB.java", "Updated x_y_count[j][2] i.e. count at j to "+x_y_count[j][2]);
									break;
								} 
						}
						else  // if row is empty then u have gone through all the non empty cells (or this is first value to go in) 
										//and you haven't found match hence insert new row
						{
							Log.d("DB.java", "Since it was 0 i'm adding new row at "+j);
							x_y_count[j][0]=c1.getInt(0);
							x_y_count[j][1]=c1.getInt(1);
							x_y_count[j][2]=c1.getInt(2);
							//Log.d("DB.java", "inserted new row in x_y_count-- x "+x_y_count[j][0]+"--y "+x_y_count[j][1]+"--count "+x_y_count[j][2]);
							//last_non_zero_row_index=j;        //new row was inserted here i.e. last row of x_y_count matrix
							break;
						}
				
					}
					//Log.d("DB.java", "Last non zero index "+last_non_zero_row_index);
					Log.d("DB.java", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				}
			}
		
		}
		
		
		display_x_y_count();

		
		Log.d("DB.java", "now trying to Get x_y_count matrix's max counts index");
		
		int max_val_index = 0;  // find which row_index has max count in x_y_count
		int max_count_value = -1;
		
		if(x_y_count[0][0] != 0)
		{
		for(int i=0; i<x_y_count.length;i++)
		{
			if(x_y_count[i][2]>max_count_value )
			{
				max_count_value = x_y_count[i][2];
				max_val_index=i;
				Log.d("DB.java", "max_count_value "+max_count_value);
				Log.d("DB.java", "max_val_index "+max_val_index);
				
				last_known_x=x_y_count[max_val_index][0];
				last_known_y=x_y_count[max_val_index][1];
				Log.d("DB.java", "last_known_x "+last_known_x);
				Log.d("DB.java", "last_known_y "+last_known_y);
			}
		}
		}

		
		last_known_result[0]=last_known_x;
		last_known_result[1]=last_known_y;
		
		
		Log.d("DB.java", "RETURNING current_position~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return last_known_result;
		
		
		
	} // end of current_position
	
	public void display_x_y_count()
	{
		Log.d("DB.java", "++++++++++++++++++++++++++++++++x_y_count+++++++++++++++++++");
		int z=0;
		while(x_y_count[z][0]>0)
		{
			Log.d("DB.java",x_y_count[z][0]+"-"+x_y_count[z][1]+"-"+x_y_count[z][2]);
			z++;
		}
		Log.d("DB.java", "++++++++++++++++++++++++++++++++x_y_count+++++++++++++++++++");
					
	}	
	
	
}
