package com.example.NAVIGATION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NavigationBlocks {

	
    private List<float[]> navigationBlocks = new ArrayList<float[]>();
    
    
    public int getConnectorBlock(int origBlock) {
    	if (origBlock >=1 && origBlock <= 15)
    		return 2;
    	else if (origBlock >=16 && origBlock <= 30)
    		return 17;
    	else if (origBlock >=31 && origBlock <= 45)
    		return 32;    			
    	else if (origBlock == 46 || origBlock == 47 || origBlock == 48)
    		return origBlock;
    	
    	
    	
    	return 9999;
    }	
    
	public NavigationBlocks(float xIncrement, float yIncrement) {
		
		float x=0, y=110;
		for (int i=0; i < 45; i++) {
			x = xIncrement + x;
			y = yIncrement + y;
			if (i%3==0) {
				navigationBlocks.add(new float[] { (i+1), x+1, x +72, y+1, y + 160, x+ 36 , y+ 80});
				x = x+72;
			}
			else {
				navigationBlocks.add(new float[] { (i+1), x+1, x +60, y+1, y + 160, x+ 30 , y+ 80});
				x = x+60;
			}
			
			if (i==14 || i==29) {
				y = y +220;
				y = y + 160;
				x = xIncrement;
			}
		}	
		
		//There are 3 more blocks
		navigationBlocks.add(new float[]{46,73f,132f, 270f, 490f, 98f, 380f});
		navigationBlocks.add(new float[]{47,73f,132f, 650f, 870f, 98f, 760f});
		navigationBlocks.add(new float[]{48,73f,132f, 1030f, 1140f, 98f, 1085f});
	}

	public List<float[]> getnavigationBlocks() {
		return navigationBlocks;
	}

	public void setnavigationBlocks(List<float[]> navigationBlocks) {
		this.navigationBlocks = navigationBlocks;
	}
	
	public float[] getBlock(float xinpt, float yinpt) {

		Scanner in = new Scanner(System.in);
	    float[] returnRow = new float[] {9999};

	    for (float[] row : navigationBlocks) {  	
	        
		    	    if (xinpt >= row[1] && xinpt <= row[2] && yinpt >= row[3] && yinpt <= row [4]) {
		    	    	returnRow = row;
		    	    	break;
		    	    }
		    	    else {		    	    	
		    	    }
	    }
		return returnRow;	    
	}
	
	public float[] getBlockRowByBlockNumber(int blockNumber) {
	
		Scanner in = new Scanner(System.in);
	    float[] returnRow = new float[] {9999};

	    for (float[] row : navigationBlocks) {  	
	        
		    	    if (blockNumber == row[0]) {
		    	    	returnRow = row;
		    	    	break;
		    	    }
		    	    else {
		    	    	//System.out.print("  Block: !! NOT IN RANGE !!");
		    	    }
	    }
		return returnRow;	    
	}
	
	public int getBlockNumberByXAndY (int[] cur_x_y) {
		
		if(cur_x_y[0]==0||cur_x_y[1]==0)
			return 9999;
		
			if(cur_x_y[0]==1||cur_x_y[0]==2||cur_x_y[0]==3)
				return ((((cur_x_y[0])-1)*15) + cur_x_y[1]);		
			else if (cur_x_y[0]==4&&cur_x_y[1]==2)
				return 46;
			else if (cur_x_y[0]==5&&cur_x_y[1]==2)
				return 47;
			else if (cur_x_y[0]==6&&cur_x_y[1]==2)
				return 48;

		return 9999;
	}
	
	public float[] getBlockRowByXAndY (int[] current_x_y) {
		
		
		int blockNumber = getBlockNumberByXAndY(current_x_y);
		
		for (float[] row : navigationBlocks) {  	
	        
    	    if (blockNumber == (int)row[0]) {
    	    	return row;    	    	
    	    }
    	    else {
    	    	//System.out.print("  Block: !! NOT IN RANGE !!");
    	    }
		}
		float a[] ={9999,9999,9999,9999,9999,9999,9999}; 
		return 	  a;		
	}
	
	
	
	
	
}
 