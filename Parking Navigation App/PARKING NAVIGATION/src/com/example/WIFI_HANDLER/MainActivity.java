package com.example.WIFI_HANDLER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.GPS_HANDLER.DB.*;
import com.example.GUI.TheListener;
import com.example.GUI.VariableChangeListener;
import com.example.NAVIGATION.*;
import com.example.WIFI_HANDLER.DB.DB;
import com.example.db_test1.R;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements OnTouchListener, VariableChangeListener, LocationListener{
	private DB Database;
	private Wifi_data wifi;
	private Button current_position;
	private Button refresh;
	private TextToSpeech tts;
	private float x,y,sX, sY, fX, fY;
	private int click;
	private Paint mainRoutePaint, secondaryRoutePaint, titlePaint, currentBlockPaint, markersText, markersDot;
	private Navigate n, n2;
	private int highlightCellX;
	private int highlightCellY;
	int flagToReroute;
	private TheListener mTheListener;
	float[] destination;
    float[] newSource;
    public int[] current_x_y;
    public double[] current_lat_long;
    NavigationBlocks nb;
    float[] x_y_row_highlight_cell;
    int testTrigger;
    //Location Stuff
    LocationManager locationManager ;
	String provider;
    GPS gps;
    GPS_DB gps_db;
    //Location Stuff Ends
    public boolean startMonitoringLocationChange;
    float[] assocByCurPositionClick, connByCurPositionClick, assoc2ByCurPositionClick, conn2ByCurPositionClick;
    int changeLocFlag=0;
    
	Button DB;
	Button routeButton; 
	
	Intent intentDB;
	Intent intentNav;
	
	//COMPASS MODULE
	private SensorManager mSensorManager;
    private Sensor mSensor;
    private SampleView mView;
    private float[] mValues;
    private static final boolean TEST_PICTURE = false;
    private final SensorEventListener mListener = new SensorEventListener() {
        @SuppressWarnings("unused")
        public void onSensorChanged(SensorEvent event) {
            if (false) Log.d(TAG,
                    "sensorChanged (" + event.values[0] + ", " + event.values[1] + ", " + event.values[2] + ")");
            mValues = event.values;
            if (mView != null) {
                mView.invalidate();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
	//COMPASS MODULE
	
    public List<int[]> routeCellsXY;
    
	public boolean isMyPositionClicked;
	Drawable d;
	Canvas canvas;
	Paint paint;
	Bitmap bitmap;
	float downx = 0,downy = 0,upx = 0,upy = 0;
	ImageView imageView, compassView;
	Matrix matrix = new Matrix();
	   Matrix savedMatrix = new Matrix();
	   private static final String TAG = "Touch";
	   static final int NONE = 0;
	   static final int DRAG = 1;
	   static final int ZOOM = 2;
	   int mode = NONE; 
	   
	   // Remember some things for zooming
	   PointF start = new PointF();
	   PointF mid = new PointF();
	   float oldDist = 1f;
	   float x1, y1, x2, y2;
	   Bitmap bmp, bmpCompass, bmpCell;
	   int i = 1;
	   
	private VariableChangeListener variableChangeListener;
	private VariableChangeListener variableChangeX;
	private VariableChangeListener variableChangeY;
	
   public VariableChangeListener getVariableChangeListener() {
		return variableChangeListener;
	}

	public void setVariableChangeListener(
			VariableChangeListener variableChangeListener) {
		this.variableChangeListener = variableChangeListener;
	}

public void setTheListener(TheListener listen) {
        mTheListener = listen;
    }   
	
	public TextToSpeech getTts() {
		return tts;
	}

	public void setTts(TextToSpeech tts) {
		this.tts = tts;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getsX() {
		return sX;
	}

	public void setsX(float sX) {
		this.sX = sX;
	}

	public float getsY() {
		return sY;
	}

	public void setsY(float sY) {
		this.sY = sY;
	}

	public float getfX() {
		return fX;
	}

	public void setfX(float fX) {
		this.fX = fX;
	}

	public float getfY() {
		return fY;
	}

	public void setfY(float fY) {
		this.fY = fY;
	}
	
	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
	}
	
	public int getHighlightCellX() {
		return highlightCellX;
	}

	public void setHighlightCellX(int highlightCellX) {
		this.highlightCellX = highlightCellX;
	}
	public int getHighlightCellY() {
		return highlightCellY;
	}

	public void setHighlightCellY(int highlightCellY) {
		this.highlightCellY = highlightCellY;
	}
	
	@SuppressLint({ "WrongCall", "CutPasteId" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		startMonitoringLocationChange = false;
		isMyPositionClicked = false;
		assocByCurPositionClick = null;
		connByCurPositionClick = null;
		
		//LOCATION RELATED - STARTS
		gps = new GPS(this);
		gps_db = new GPS_DB();
		nb = new NavigationBlocks(0,0);
		
		// Getting LocationManager object
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);        
     
        // Creating an empty criteria object
        Criteria criteria = new Criteria();
        
        // Getting the name of the provider that meets the criteria
        provider = locationManager.GPS_PROVIDER;      
                
        if(provider!=null && !provider.equals("")){
  
        	// Get the location from the given provider 
            Location location = locationManager.getLastKnownLocation(provider);
                        
            locationManager.requestLocationUpdates(provider, 3000, 2, this);
                        
            if(location!=null)
            	onLocationChanged(location);
            else
            	Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();            
        }else{
        	Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }
        
        //LOCATION RELATED - ENDS        
           
		Intent intent = getIntent();  
		Bundle extras = intent.getExtras(); 

		if(extras !=null)
		{				
			destination=extras.getFloatArray("destination");	
			float[] destXY=extras.getFloatArray("destXYFromNav");	
			System.out.println("=========================>destination="+destination[0]+" "+destination[1]);
			if (destXY!=null)
			{
				setfX(destXY[0]);
				setfY(destXY[1]);
			}
				System.out.println("=========================>newsource (100,210)");
				//Compute new location here.
			setsX(100);
			setsY(210);		
		}
		
		testTrigger = 0;
		
		imageView = (ImageView)findViewById(R.id.lot);
		//bmp = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Config.ARGB_8888);
		
		mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

		markersText = new Paint();
		markersText.setColor(Color.BLACK);
		markersText.setStyle(Paint.Style.FILL);
		markersText.setTextSize(50);		
		
		markersDot = new Paint();
		markersDot.setColor(Color.BLACK);
		markersDot.setStyle(Paint.Style.FILL);
		markersDot.setStrokeWidth(25);		
		
		titlePaint = new Paint();
		titlePaint.setAntiAlias(true);
		titlePaint.setDither(true);
		titlePaint.setColor(Color.RED);
		titlePaint.setStyle(Paint.Style.FILL);
		titlePaint.setStrokeJoin(Paint.Join.ROUND);
		titlePaint.setTextSize(30);
		titlePaint.setStrokeWidth(2);
        
		mainRoutePaint = new Paint();
        mainRoutePaint.setAntiAlias(true);
        mainRoutePaint.setDither(true);
        mainRoutePaint.setColor(Color.rgb(61, 132, 203));
        mainRoutePaint.setStyle(Paint.Style.STROKE);
        mainRoutePaint.setStrokeJoin(Paint.Join.ROUND);
       // mainRoutePaint.setStrokeCap(Paint.Cap.ROUND);
        mainRoutePaint.setStrokeWidth(10);
		
        secondaryRoutePaint = new Paint();
        secondaryRoutePaint.setAntiAlias(true);
        secondaryRoutePaint.setDither(true);
        mainRoutePaint.setColor(Color.rgb(61, 132, 203));
        secondaryRoutePaint.setStyle(Paint.Style.FILL);
        secondaryRoutePaint.setStrokeJoin(Paint.Join.ROUND);
        secondaryRoutePaint.setPathEffect(new DashPathEffect(new float[] {10,10}, 0));
       // secondaryRoutePaint.setStrokeCap(Paint.Cap.ROUND);
        secondaryRoutePaint.setStrokeWidth(2);    
        
        
        currentBlockPaint = new Paint();
        currentBlockPaint.setAntiAlias(true);
        currentBlockPaint.setDither(true);
        currentBlockPaint.setColor(Color.BLUE);
        currentBlockPaint.setStyle(Paint.Style.STROKE);
        
        currentBlockPaint.setStrokeJoin(Paint.Join.ROUND);
        currentBlockPaint.setPathEffect(new DashPathEffect(new float[] {10,10}, 0));
       // currentBlockPaint.setStrokeCap(Paint.Cap.ROUND);
        currentBlockPaint.setStrokeWidth(10);        

      
		DB = (Button)findViewById(R.id.DatabaseButton);
		routeButton = (Button)findViewById(R.id.routeButton);
		refresh = (Button)findViewById(R.id.refresh);
		
		refresh.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick (View v){
			    Intent intent = getIntent();
			    finish();
			    startActivity(intent);
			}
		});
		
		routeButton.setEnabled(false);
							
		DB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				CountDownTimer countDownTimer = new CountDownTimer(1000000, 2*1000) {
						
					@Override
					public void onTick(long millisUntilFinished) {
						// TODO Auto-generated method stub
						Database.open();
						wifi.turn_on();
						String[] available_wifi_names2 = wifi.return_available_wifi_names();
						int[] levels2 = wifi.return_levels();
						int[] current_position_list = {-1,-1};
						
						try{
							Log.d("#4Params", Arrays.toString(assocByCurPositionClick)+" "+Arrays.toString(connByCurPositionClick)+" "
						+Arrays.toString(assoc2ByCurPositionClick)+" "+Arrays.toString(conn2ByCurPositionClick));
							
							current_position_list = Database.current_position(available_wifi_names2,levels2,
									routeCellsXY);
							Log.d("FragActivity.java", "current_position_list values returned");
							Log.d("FragActivity.java", "caught result values"+current_position_list[0]+"^^"+current_position_list[1]);
							//current_x.setText(current_position_list[0]+"");
							//current_y.setText(current_position_list[1]+"");
						}catch(NullPointerException e)
						{System.out.println("exception caught");e.printStackTrace();}
						//current_x.setText(current_position_list[0]+"");
						//current_y.setText(current_position_list[1]+"");}

						
						//setHighlightCellX(2);
						//setHighlightCellY(3);
						
						// THIS IS MY CURRENT LOCATION AS RECEIVED AND ESTIMATED BY GPS/WIFI
						//double[] loc = gps.getLocation();
						//int[] curXYbyLocation = gps.get_x_y();
						//float curXYbyLocationROW = nb.getBlockRowByXAndY(curXYbyLocation);
						
						i = 2;						
						int[] xy = {6,2};
						xy = current_position_list;
						
						Log.d("#6XYonGPS:",Arrays.toString(xy));
						if (xy[0]==-1&&xy[1]==-1)				
						{
							xy[0] = 6;
							xy[1] = 2;
						}
						Log.d("#6XYonGPS:","#6XYonGPS:"+Arrays.toString(xy));
						
						float[] curXYbyLocationROW = nb.getBlockRowByXAndY(xy);
						sX = curXYbyLocationROW[5];
						sY = curXYbyLocationROW[6];				
						Log.d("CurLocRow","= = | |= ="+Arrays.toString(curXYbyLocationROW));
						Log.d("sXSy="," sx sy "+sX+" "+sY);
						int blockNum = nb.getBlockNumberByXAndY(xy);
						float[] highlightedCellRow = nb.getBlockRowByBlockNumber(blockNum);
						
						nb.getBlockRowByXAndY(xy);
						
						//
						onDrawHighlightCell(highlightedCellRow);
						
								
								Database.close();
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						this.start();
					}
					
				};
				countDownTimer.start();				
				}
		});

		wifi = new Wifi_data(MainActivity.this);
		Database = new DB(MainActivity.this);
		current_position = (Button)findViewById(R.id.current_position);			
		
		
		
		current_position.setOnClickListener(new View.OnClickListener() 
		{
			@Override		
			public void onClick(View v)
			{					
				if (isMyPositionClicked == false) {		
					
					Database.open();
					wifi.turn_on();
					String[] available_wifi_names2 = wifi.return_available_wifi_names();
					int[] levels2 = wifi.return_levels();
					int[] current_position_list = {-1,-1};
					
					try{
						Log.d("#4Params", Arrays.toString(assocByCurPositionClick)+" "+Arrays.toString(connByCurPositionClick)+" "
					+Arrays.toString(assoc2ByCurPositionClick)+" "+Arrays.toString(conn2ByCurPositionClick));
						
						current_position_list = Database.current_position(available_wifi_names2,levels2
								,routeCellsXY);
						Log.d("FragActivity.java", "current_position_list values returned");
						Log.d("FragActivity.java", "caught result values"+current_position_list[0]+"^^"+current_position_list[1]);
						//current_x.setText(current_position_list[0]+"");
						//current_y.setText(current_position_list[1]+"");
					}catch(NullPointerException e)
					{System.out.println("exception caught");e.printStackTrace();}

					i = 2;						
					int[] xy = {6,2};
					xy = current_position_list;
					
					Log.d("#6XYonGPS:",Arrays.toString(xy));
					if (xy[0]==-1&&xy[1]==-1)				
					{
						xy[0] = 6;
						xy[1] = 2;
					}
					Log.d("#6XYonGPS:","#6XYonGPS:"+Arrays.toString(xy));
					
					float[] curXYbyLocationROW = nb.getBlockRowByXAndY(xy);
					sX = curXYbyLocationROW[5];
					sY = curXYbyLocationROW[6];				
					Log.d("CurLocRow","= = | |= ="+Arrays.toString(curXYbyLocationROW));
					Log.d("sXSy="," sx sy "+sX+" "+sY);
					int blockNum = nb.getBlockNumberByXAndY(xy);
					float[] highlightedCellRow = nb.getBlockRowByBlockNumber(blockNum);
					
					nb.getBlockRowByXAndY(xy);
					
					//
					onDrawHighlightCell(highlightedCellRow);
					Log.d("2.sXSy="," sx sy "+sX+" "+sY);
					//n = new Navigate(sX, sY, 0, 0);
					//n.navigate();
					//System.out.println("------------1111111111111111111-------------");
					n = new Navigate(sX, sY, 0, 0);
					n.navigate();										
							assocByCurPositionClick = null;
							connByCurPositionClick = null;
					
							assocByCurPositionClick = n.getResultantAssociatedBlockRow();
							connByCurPositionClick = n.getResultantConnectorBlockRow();
									
							Log.d("2CurLocRow","= = | |= ="+Arrays.toString(curXYbyLocationROW));
							
					n = new Navigate(fX,fY,0,0);
					n.navigate();
							assoc2ByCurPositionClick = null;
							conn2ByCurPositionClick = null;
					
							assoc2ByCurPositionClick = n.getResultantAssociatedBlockRow();
							conn2ByCurPositionClick = n.getResultantConnectorBlockRow();
									
							
							
					isMyPositionClicked = true;
				}
				current_position.setEnabled(false);
			}
			
		});
		
	    imageView.setOnTouchListener(this);	    
	    
	    d = getResources().getDrawable(R.drawable.lot);	    
	    
	    bmp = ((BitmapDrawable)d).getBitmap();
	    
	    imageView.setImageBitmap(bmp);
	    
	    intentNav = new Intent(MainActivity.this, Navigation_Activity.class);	
		routeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intentNav.putExtra("foo", 5.0f);
				intentNav.putExtra("bar", "baz");
				intentNav.putExtra("id",R.id.lot);
				startActivity(intentNav);     
				finish();
			}
	});		
	}
		
	@SuppressLint("WrongCall")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
	    // TODO Auto-generated method stub
	    switch (event.getAction() & MotionEvent.ACTION_MASK) {
	      case MotionEvent.ACTION_UP:
	         savedMatrix.set(matrix);
	         start.set(event.getX(), event.getY());
	         Log.d(TAG, "mode=DRAG");
	         mode = DRAG;
	         Log.i(TAG, "("+String.valueOf((int)event.getX())+","+String.valueOf((int)event.getY())+")");
	         if (i==1 && isMyPositionClicked==false){
	             x1 = event.getX();
	             y1 = event.getY();
	             this.sX = x1;
	             this.sY = y1;
	             i = 2;
	             Log.i(TAG, "coordinate x1 : "+String.valueOf(x1)+" y1 : "+String.valueOf(y1));
	             onDrawMarker1(x1,y1);
	         }
	         else if (i==2){
	             x2 = event.getX();
	             y2 = event.getY();
	             this.fX = x2;
	             this.fY = y2;
	             if (fX!=0) {			             			            				
		     			routeButton.setEnabled(true);		     			
		     	 }
	             i = 3;
	             Log.i(TAG, "coordinate x2 : "+String.valueOf(x2)+" y2 : "+String.valueOf(y2));
	             
	             startMonitoringLocationChange = true;
	             
	             onDraw();	             
	             current_position.setEnabled(true);
	             imageView.setOnTouchListener(null);
	         } 	        
	         break;
	      }
	    return true;
	}
	
	public void onDrawMarker1(float x1, float y1){
	    bmp = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Config.ARGB_8888);
	    Canvas c = new Canvas(bmp);
	    imageView.draw(c);
	    
	    //c.drawLine(x1, y1, x2, y2, mainRoutePaint);
	    imageView.setImageBitmap(bmp);
		c.drawText("S", x1+20, y1+20, markersText);
		c.drawCircle(x1, y1, 10, markersDot);		
	}
	
	
	public void onDrawHighlightCell(float[] cell){
		
		if (sX!=0&&sY!=0)
		{
			System.out.println(Arrays.toString(cell));		
			bmp = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Config.ARGB_8888);
			Canvas c = new Canvas(bmp);
			imageView.draw(c);	    
		    //c.drawLine(x1, y1, x2, y2, mainRoutePaint);
		    imageView.setImageBitmap(bmp);			
			c.drawRect(cell[1],cell[3],cell[2],cell[4], currentBlockPaint);
		
		}
	}
	
	
	
	public void onDraw(){
	    bmp = Bitmap.createBitmap(imageView.getWidth(),imageView.getHeight(), Config.ARGB_8888);
	    //System.out.println("==>imageviewwidthhegiht="+ imageView.getWidth() + " " + imageView.getHeight());
	    Canvas c = new Canvas(bmp);
	    float[] assoc = {0,0,0,0,0,0,0};
	    float[] conn = {0,0,0,0,0,0,0};
	    float[] assoc2 = {0,0,0,0,0,0,0}; 
	    float[] conn2 = {0,0,0,0,0,0,0};
	    
	    imageView.draw(c);
	    
	    //c.drawLine(x1, y1, x2, y2, mainRoutePaint);
	    imageView.setImageBitmap(bmp);
	    i = 1;	    	    
	    
	    if(fX != 0 && fY!= 0) {
			
			/* LOGIC ---- DRAW PROPER ROUTE */
			n = new Navigate(sX, sY, 0, 0);
			n.navigate();
			System.out.println("------------1111111111111111111-------------");
			
			
					assoc = n.getResultantAssociatedBlockRow();
					conn = n.getResultantConnectorBlockRow();
					
					if (assocByCurPositionClick!=null) {
						assoc = assocByCurPositionClick;
						conn = connByCurPositionClick;
						assoc2 = assoc2ByCurPositionClick;
						conn2 = conn2ByCurPositionClick;
					}
					
					c.drawRect(assoc[1],assoc[3],assoc[2],assoc[4],currentBlockPaint);				
			
					
			System.out.println("Spine of 1:" + conn[0] + " " + conn[1] + " " + conn[2]);
			//System.out.println("Assoc of 1:" + assoc[0] + " " + assoc[1] + " " + assoc[2]);
			System.out.println("------------1111111111111111111--------------");
			
			
			n2 = new Navigate(fX, fY, 0, 0);
			n2.navigate();
			System.out.println("-----------2222222222--------------");
			
				assoc2 = n2.getResultantAssociatedBlockRow();
			
				conn2 = n2.getResultantConnectorBlockRow();
				
			System.out.println("Spine of 2:" + conn2[0] +  " " + conn2[1] + " " + conn2[2]);
			System.out.println("-----------2222222222--------------");
			
			System.out.println("=========Summary1==========");		

			int curLocBlock = getCurrentLocationBlockRow(); 
			float left = assoc[1];
			float right = assoc[3];
								
			if (!(conn2[3]==conn[3] && conn2[4]==conn[4])) {
				
				//System.out.println(" " + " b:" + conn[0] + " " + conn[5] + " " + conn[6]);
				
				System.out.println("Line 1: b:" + assoc[0] + " " + conn[0]);				
				System.out.println("Line 2: b:" + conn[0] + " " + conn2[0] );
				System.out.println("Line 3: b:" + conn2[0] + " " + assoc2[0]);

				c.drawLine(assoc[5], assoc[6], conn[5], conn[6], mainRoutePaint);         				
	            c.drawLine(conn2[5], conn2[6], conn[5], conn[6], mainRoutePaint);
	            c.drawLine(assoc2[5], assoc2[6], conn2[5], conn2[6], mainRoutePaint);
	            
	            if (fX!=0 && fY!=0)
	            	c.drawLine(assoc2[5], assoc2[6], fX, fY, secondaryRoutePaint);
	            if (fX!=0 && fY!=0)
	            	c.drawLine(assoc[5], assoc[6], sX, sY, secondaryRoutePaint);	            
			}
			
			else if (conn2[3]==conn[3] || conn2[4]==conn[4]) {
				//System.out.println("Line 1: b:" + assoc[0] + " " + assoc[5] + " " + assoc[6] + " b:" + assoc2[0] + " " + assoc2[5] + " " + assoc2[6]);
				
				c.drawLine(assoc2[5], assoc2[6], assoc[5], assoc[6], mainRoutePaint);
				
				if (fX!=0 && fY!=0)
				c.drawLine(assoc2[5], assoc2[6], fX, fY, secondaryRoutePaint);
				if (fX!=0 && fY!=0)
	            c.drawLine(assoc[5], assoc[6], sX, sY, secondaryRoutePaint);
			}
			
			CellsOnRoute cor = new CellsOnRoute();
			List<Integer> list = cor.getListOfCellNumbersOnRoute(assoc,conn,conn2,assoc2);
			List<int[]> cellsOnRoute = cor.getCellArraysOnRoute();
			routeCellsXY = cellsOnRoute;
			String qur = "AND (";
			for (int[] l:cellsOnRoute) {
				Log.d("l=",Arrays.toString(l));
				
				String temp = "(x="+l[0]+" AND y="+l[1]+")OR";
				qur+=temp;
			}
			qur+="(x=999 AND y=999))";
			Log.d(qur,"Qur="+qur);
			
			
			c.drawText("S", sX+20, sY+20, markersText);
			c.drawCircle(sX, sY, 10, markersDot);
			
			c.drawText("D", fX+20, fY+20, markersText);
			c.drawCircle(fX, fY, 10, markersDot);
			
			float[] destXY = {fX, fY};
			
			Bundle b=new Bundle();				
			b.putFloatArray("assoc", assoc);
			b.putFloatArray("assoc2", assoc2);
			b.putFloatArray("conn", conn);
			b.putFloatArray("conn2", conn2);
			b.putFloatArray("destXY", destXY);
			intentNav.putExtras(b);		
			
			
			assocByCurPositionClick = assoc;
			assoc2ByCurPositionClick = assoc2;
			connByCurPositionClick = conn;
			conn2ByCurPositionClick = conn2;
			
			
			System.out.println("=========Summary1ENDS==========");
			/* LOGIC ---- DRAW PROPER ROUTE */
	            
		}
	    
		//VALIDATIONS
		//1. If a slot is selected, choose the neighboring navigation cell. Mapping of slot to neighboring cell to be done somewhere above.
		//2. If a point is select outside of the boundary then ignore it.
		//3. Display the corresponding grid numbers that fall on the path.

		/*if (getClick()==1) {
			//canvas.drawText("(x, y) = " + (int) sX + ", " + (int) sY, sX-150,  sY, titlePaint);
			
				if( getHighlightCellX() != 2 && this.variableChangeListener != null) {
					
					System.out.println("1 1 1 1 PPPPPPPPPPPPPPPPPPPPINSIDEEEEEEEE X = " + getHighlightCellX());
					//c.drawRect (assoc2[1],assoc2[3], assoc2[2], assoc2[4], currentBlockPaint);
						this.variableChangeListener.onVariableChanged(getHighlightCellX(), c, assoc);
				}			    				
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
		}
		else if (getClick()== 2) {
    	    //canvas.drawText("(x, y) = " + (int) sX + ", " + (int) sY, sX-150,  sY, titlePaint);
            //canvas.drawText("(x, y) = " + (int) fX + ", " + (int) fY, fX-150,  fY, titlePaint);
            System.out.println("22222                     22222");
    	    setHighlightCellX(5);
    	    if( getHighlightCellX() != 2 && this.variableChangeListener != null) {
    			
    			System.out.println("2 2 2 2PPPPPPPPPPPPPPPPPPPPINSIDEEEEEEEE X = " + getHighlightCellX());
    			//c.drawRect (assoc2[1],assoc2[3], assoc2[2], assoc2[4], currentBlockPaint);
    			this.variableChangeListener.onVariableChanged(getHighlightCellX(), c, assoc2);
    	    }
		}	*/ 
	}
	
	private int getCurrentLocationBlockRow() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onVariableChanged(int cellX, Canvas can, float[] assoc_arr) {
		// TODO Auto-generated method stub		
		can.drawRect (assoc_arr[1],assoc_arr[3], assoc_arr[2], assoc_arr[4], currentBlockPaint);		
	}
	
	//COMPASS MODULE
	@Override
    protected void onResume()
    {
        if (false) Log.d(TAG, "onResume");
        super.onResume();

        mSensorManager.registerListener(mListener, mSensor,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop()
    {
        if (false) Log.d(TAG, "onStop");
        mSensorManager.unregisterListener(mListener);
        super.onStop();
    }

    public VariableChangeListener getVariableChangeX() {
		return variableChangeX;
	}

	public void setVariableChangeX(VariableChangeListener variableChangeX) {
		this.variableChangeX = variableChangeX;
	}

	public VariableChangeListener getVariableChangeY() {
		return variableChangeY;
	}

	public void setVariableChangeY(VariableChangeListener variableChangeY) {
		this.variableChangeY = variableChangeY;
	}

	class SampleView extends View {
	    private Paint   mPaint = new Paint();
	    private Path    mPath = new Path();
	    private boolean mAnimate;

	    public SampleView(Context context) {
	        super(context);
	        init();
	    }
	    
	    public SampleView(Context context, AttributeSet attrs)
	    {
	        super(context, attrs);// Construct a wedge-shaped path
	        init();
	    }
	    
	    public void init() {
			// TODO Auto-generated method stub
	    	 mPath.moveTo(0, -50);
	         mPath.lineTo(-20, 60);
	         mPath.lineTo(0, 50);
	         mPath.lineTo(20, 60);
	         mPath.close();
		}

		public SampleView(Context context, AttributeSet attrs, int defStyle) {
	        super(context, attrs, defStyle);
	        init();
	    }

	    protected void onDraw() {
	        
	    	bmpCompass = Bitmap.createBitmap(compassView.getWidth(), compassView.getHeight(), Config.ARGB_8888);
		    Canvas canvas = new Canvas(bmpCompass);
		    compassView.draw(canvas);
		    
		    //c.drawLine(x1, y1, x2, y2, mainRoutePaint);
		    //imageView.setImageBitmap(bmp);
			//canvas.drawText("S", x1+20, y1+20, markersText);
	    	
	    	
	    	Paint paint = mPaint;
	        canvas.drawColor(Color.WHITE);

	        paint.setAntiAlias(true);
	        paint.setColor(Color.BLACK);
	        paint.setStyle(Paint.Style.FILL);

	        int w = canvas.getWidth();
	        int h = canvas.getHeight();
	        int cx = w / 2;
	        int cy = h / 2;

	        canvas.translate(cx, cy);
	        if (mValues != null) {
	            canvas.rotate(-mValues[0]);
	        }
	        canvas.drawPath(mPath, mPaint);
	    }

	    @Override
	    protected void onAttachedToWindow() {
	        mAnimate = true;
	        if (false) Log.d(TAG, "onAttachedToWindow. mAnimate=" + mAnimate);
	        super.onAttachedToWindow();
	    }

	    @Override
	    protected void onDetachedFromWindow() {
	        mAnimate = false;
	        if (false) Log.d(TAG, "onDetachedFromWindow. mAnimate=" + mAnimate);
	        super.onDetachedFromWindow();
	    }
	}

	@SuppressWarnings("null")
	@SuppressLint("WrongCall")
	@Override
	public void onLocationChanged(Location location) {
		
		if(startMonitoringLocationChange=true) {
			//We enter this only if we NEED to monitor location change
			
			// Getting reference to TextView tv_longitude
			TextView tvLongitude = (TextView)findViewById(R.id.tv_longitude);
			
			// Getting reference to TextView tv_latitude
			TextView tvLatitude = (TextView)findViewById(R.id.tv_latitude);
			
			// Setting Current Longitude
			tvLongitude.setText("Longitude:" + location.getLongitude());
			
			// Setting Current Latitude
			tvLatitude.setText("Latitude:" + location.getLatitude() );
			
			
			//GET THE CURRENT LOCATION FROM HERE TO DECIDE WHICH CELL TO HIGHLIGHT WHILE NAVIGATING
			//current_lat_long = gps.getLocation();
			//current_x_y = gps.get_x_y();
			//x_y_row_highlight_cell = nb.getBlockRowByXAndY(current_x_y);				
		}
				
				
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub		
	}

	
	

	
}
