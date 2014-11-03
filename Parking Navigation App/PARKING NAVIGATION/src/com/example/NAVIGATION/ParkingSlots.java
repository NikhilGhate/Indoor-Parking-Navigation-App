package com.example.NAVIGATION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ParkingSlots {

	private float parkingSlots[][];
    public float[][] getParkingSlots() {
		return parkingSlots;
	}

	public void setParkingSlots(float[][] parkingSlots) {
		this.parkingSlots = parkingSlots;
	}

	private List<float[]> rowList = new ArrayList<float[]>();

    	
	public ParkingSlots(float xIncrement, float yIncrement) {
		setParkingSlots(null);
		
		float x=0, y=0;
		for (int i=0; i < 90; i++) {
			x = xIncrement + x;
			y = yIncrement + y;
			if (i%3==0) {
				rowList.add(new float[] { (i+1), x+1, x +72, y+1, y + 110, x+ 36 , y+ 55});
				x = x+72;
			}
			else {
					rowList.add(new float[] { (i+1), x+1, x +60, y+1, y + 110, x+ 30 , y+ 55});
					x = x+60;
			}
			if (i==14 || i==44 || i == 74 ) {
				y = y + 110;
				y = y + 160;
				x = xIncrement;
			}
			else if (i == 29 || i ==59) {
				y = y + 110;
				x = xIncrement;
			}
		}	
	}

	public List<float[]> getRowList() {
		return rowList;
	}

	public void setRowList(List<float[]> rowList) {
		this.rowList = rowList;
	}
	
	public float[] getSlot(float xinpt, float yinpt) {

		Scanner in = new Scanner(System.in);
	    float[] returnRow = new float[] {9999};

	    for (float[] row : rowList) {
	    	
	    	//System.out.print("\nSlot Row = " + Arrays.toString(row)); 
	        
		    	    if (xinpt >= row[1] && xinpt <= row[2] && yinpt >= row[3] && yinpt <= row [4]) {
		    	    	/*
		    	    	System.out.println("\nx1 = " + row[1]);
		    	    	System.out.print("x2 = " + row[2]);
		    	    	System.out.println("\ny1 = " + row[3]);
		    	    	System.out.print("y2 = " + row[4]);
		    	    	*/
		    	    	returnRow = row;
		    	    	break;
		    	    }
		    	    else {
		    	    	//System.out.print("  Slot: !! NOT IN RANGE !!");
		    	    }
	    }
		return returnRow;
	    
	    
	}
	
	
}
