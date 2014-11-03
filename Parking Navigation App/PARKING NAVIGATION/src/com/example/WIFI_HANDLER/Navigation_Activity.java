package com.example.WIFI_HANDLER;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

//import org.example.tts.R;

import com.example.NAVIGATION.DirectionsSingleStep;
import com.example.NAVIGATION.GetDirectionsList;
import com.example.NAVIGATION.GetDirectionsStepByStep;
import com.example.WIFI_HANDLER.DB.DB;
import com.example.db_test1.R;
import com.example.db_test1.R.id;
import com.example.db_test1.R.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.TextureView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class Navigation_Activity extends Activity implements OnInitListener, LocationListener {
	private Button turn_on_wifi;
	private Button rerouteButton;
	private Button current_position;
	private Button textToSpeechButton;
	private DB Database;
	private Wifi_data wifi;
	private TextToSpeech tts;
	private String temp_scan_result_holder;
	String directionsAtOneBlock;
	ArrayList<String> dir;
	String textToSpeak;
	
	LocationManager locationManager ;
	String provider;
	
	float[] destination;
	float[] destXY;
	public float[] getDestXY() {
		return destXY;
	}

	public void setDestXY(float[] destXY) {
		this.destXY = destXY;
	}

	float[] assoc, assoc2, conn, conn2;
	
	//Text switcher part
	private TextSwitcher mSwitcher;
    Button btnNext, btnPrev;    
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
	    
	    setContentView(R.layout.navigation);

	    // Getting LocationManager object
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
        // Creating an empty criteria object
        Criteria criteria = new Criteria();
        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);  
        if(provider!=null && !provider.equals("")){
        	
        	// Get the location from the given provider 
            Location location = locationManager.getLastKnownLocation(provider);
                        
            locationManager.requestLocationUpdates(provider, 1000, 1, this);
                                   
            if(location!=null)
            	onLocationChanged(location);
            else
            	Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();
            
        }
        else{
        	Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }
	    
	    Database = new DB(Navigation_Activity.this);
	    tts = new TextToSpeech(this, this);
	    rerouteButton = (Button)findViewById(R.id.rerouteButton);
	    //textToSpeechButton = (Button)findViewById(R.id.textToSpeechButton);	    	
		
		Intent intent = getIntent();  
		Bundle extras = intent.getExtras(); 
		if(extras !=null)
		{
			 float foo = extras.getFloat("foo");
			 System.out.println("Foo="+foo);
			 String bar = extras.getString("bar");
			 System.out.println("Bar="+bar);
		}			
			assoc=extras.getFloatArray("assoc");
			assoc2=extras.getFloatArray("assoc2");
			conn=extras.getFloatArray("conn");
			conn2=extras.getFloatArray("conn2");
			setDestXY(extras.getFloatArray("destXY"));
			//System.out.println("======>NavActivity...destXY " + destXY[0]+ "  "+ destXY[1]);
			destination = conn2;  // Keeping the co-ordinates of the destination safe
			int id = intent.getExtras().getInt("Id");
			
			//ImageView imageView2 = (ImageView)findViewById(R.id.lot);
			//System.out.println("==>BLAH imageview2getwidth = " + imageView2.getWidth());
		
		GetDirectionsList gdl = new GetDirectionsList();
		GetDirectionsStepByStep gdsbs = new GetDirectionsStepByStep(assoc, conn, conn2, assoc2);
		
		ArrayList<String> allDirections = new ArrayList<String>();		
		
		System.out.println(assoc[0]+" "+conn[0]+" "+conn2[0]+" "+assoc2[0]);
		allDirections = gdl.getDirectionsList(assoc, conn, conn2, assoc2);		
		
		final TextView scan_result_textbox = (TextView)findViewById(R.id.direction_results);
		scan_result_textbox.setSingleLine(false);
		scan_result_textbox.setFadingEdgeLength(50);
		scan_result_textbox.setTypeface(null, Typeface.BOLD);
		scan_result_textbox.setGravity(Gravity.CENTER_HORIZONTAL);
		
		temp_scan_result_holder ="\n______________________________________\nDIRECTIONS LIST:\n______________________________________\n";
		dir = new ArrayList<String>();
		
		for (String ad: allDirections) {
			
			//if (currentpositon is at SOME arbitary cell)
			//then only display these directions two at a time
			System.out.println(ad);	
			dir.add(ad);
			
			temp_scan_result_holder += ad + "\n";
		}
		dir.add("You have arrived at the destination.");
		
		temp_scan_result_holder += "\n DESTINATION\n";
		
		System.out.println("Fetching directions for one block (1,2):");
		directionsAtOneBlock = "";
		
		//PUT THE X,Y OF THE BLOCK HERE (INT VALUES)
		directionsAtOneBlock = gdsbs.fetchDirectionsForBlock(1,2); 				
		

		scan_result_textbox.setText(temp_scan_result_holder);
		
		rerouteButton.setOnClickListener(new View.OnClickListener() 
		{
		@Override
		
		public void onClick(View v)
			{		
				Intent intent = new Intent(Navigation_Activity.this, MainActivity.class);
				Bundle b=new Bundle();				
				b.putFloatArray("destination", destination);
				b.putFloatArray("destXYfromNav", destXY);
				System.out.println("===>getDestXY:"+destXY[0]);
				intent.putExtras(b);
				
	            startActivity(intent);      
	            finish();					
			}
		});

		/*
		textToSpeechButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				if (tts!=null) {
				    
					if (textToSpeak!=null) {
						if (!tts.isSpeaking()) {
							tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
						}
					}
				}
			}

			public void onInit(int code) {
				if (code==TextToSpeech.SUCCESS) {
					tts.setLanguage(Locale.getDefault());
				} else {
					tts = null;
					//Toast.makeText(this, "Failed to initialize TTS engine.", Toast.LENGTH_SHORT).show();
				}
			}
			
			protected void onDestroy() {
				if (tts!=null) {
					tts.stop();
		            tts.shutdown();
				}				
			}
		});*/		
		
		//Text Switcher Part
        //get The references 
		
		
		
		
        btnNext=(Button)findViewById(R.id.buttonNext);
        //btnPrev=(Button)findViewById(R.id.buttonPrev);
        
        mSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        
        // Set the ViewFactory of the TextSwitcher that will create TextView object when asked
        mSwitcher.setFactory(new ViewFactory() {
            
            public View makeView() {
                // TODO Auto-generated method stub
                // create new textView and set the properties like clolr, size etc
                TextView myText = new TextView(Navigation_Activity.this);
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(36);
                myText.setTextColor(Color.BLACK); // #9ECFFF = BACKGROUND
                return myText;
            }
        });

        // Declare the in and out animations and initialize them  
        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
        
        // set the animation type of textSwitcher
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
    
        mSwitcher.setText("Hit Next to Begin");
        
        // ClickListener for NEXT button
        // When clicked on Button TextSwitcher will switch between texts 
        // The current Text will go OUT and next text will come in with specified animation
        btnNext.setOnClickListener(new View.OnClickListener() {
            
    	// Array of String to Show In TextSwitcher 
        
        String[] textToShow = dir.toArray(new String[dir.size()]);
        int messageCount=textToShow.length;
        // to keep current Index of text
        int currentIndex=-1; 	
        	
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
                // If index reaches maximum reset it
                if(currentIndex<(messageCount-1))
                {    
                	currentIndex++;
                	mSwitcher.setText(textToShow[currentIndex]);
                	
                	if (currentIndex==messageCount-1)
                		btnNext.setEnabled(false);
                	//mSwitcher.setText("You have reached your destination");
                }
                else {
                	btnNext.setEnabled(false);
                }
                
                textToSpeak = textToShow[currentIndex];                
                
            	if (tts!=null) {				   
					if (textToSpeak!=null) {
						if (!tts.isSpeaking()) {
							tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
						}
					}
				}                
            }
        });
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
	public void onLocationChanged(Location location) {
		// Getting reference to TextView tv_longitude
		TextView tvLongitude = (TextView)findViewById(R.id.tv_longitude);
		
		// Getting reference to TextView tv_latitude
		TextView tvLatitude = (TextView)findViewById(R.id.tv_latitude);
		
		// Setting Current Longitude
		//tvLongitude.setText("Longitude:" + location.getLongitude());
		
		// Setting Current Latitude
		//tvLatitude.setText("Latitude:" + location.getLatitude() );
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
	
	
	public void onInit(int code) {
		if (code==TextToSpeech.SUCCESS) {
			tts.setLanguage(Locale.getDefault());
		} else {
			tts = null;
			Toast.makeText(this, "Failed to initialize TTS engine.", Toast.LENGTH_SHORT).show();
		}
		
		if (code==TextToSpeech.SUCCESS) {
			tts.setLanguage(Locale.getDefault());
		} else {
			tts = null;
			//Toast.makeText(this, "Failed to initialize TTS engine.", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onDestroy() {
		if (tts!=null) {
			tts.stop();
            tts.shutdown();
		}
		
		if (tts!=null) {
			tts.stop();
            tts.shutdown();
		}	
		
		super.onDestroy();
	}

	
}
