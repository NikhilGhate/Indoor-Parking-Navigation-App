package com.example.NAVIGATION;

import java.util.ArrayList;

public class GetDirectionsList {
	ArrayList<String> finalDirectionsList;
	ArrayList<String> intermediateDirections;
	
	public ArrayList<String> getDirectionsList(float[] assoc,float[] conn, float[] conn2, float[] assoc2) {
		finalDirectionsList = new ArrayList<String>();
		
		float xDiff = assoc[5]-conn[5];
		float yDiff = assoc[6]-conn[6];
		
		finalDirectionsList.addAll(intermediateDirections(xDiff,yDiff));		
		
		xDiff = conn[5] - conn2[5];
		yDiff = conn[6] - conn2[6];
		
		intermediateDirections(xDiff,yDiff);
		finalDirectionsList.addAll(intermediateDirections(xDiff,yDiff));
		
		xDiff = conn2[5] - assoc2[5];
		yDiff = conn2[6] - assoc2[6];
		
		intermediateDirections(xDiff,yDiff);
		finalDirectionsList.addAll(intermediateDirections(xDiff,yDiff));
		
		return finalDirectionsList;
	}
	
	public ArrayList<String> intermediateDirections(float a, float b) {
		intermediateDirections = new ArrayList<String>();
		int groundDistance = 0;
		
		if (a == 0 && b == 0) {
			//do nothing as it is the same point
		}
		else if (a > 0 && b == 0) {
			//We're going left 
			intermediateDirections.add("Face West");
			groundDistance = calcGroundDistance ((int)a); 
			intermediateDirections.add("Drive West for " + groundDistance + " meters");					
		}
		else if (a < 0 && b == 0) {
			//We're going right on the same horizontal row
			intermediateDirections.add("Face East");
			groundDistance = calcGroundDistance ((int)a); 
			intermediateDirections.add("Drive East for " + groundDistance + " meters");	
		}							
		else if (a == 0 && b > 0) {
			//We're going up
			intermediateDirections.add("Face North");
			groundDistance = calcGroundDistance ((int)b); 
			intermediateDirections.add("Drive North for " + groundDistance + " meters");			
		}
		else if (a == 0 && b < 0) {
			//We're going down on the same vertical row			
			intermediateDirections.add("Face South");
			groundDistance = calcGroundDistance ((int)b); 
			intermediateDirections.add("Drive South for " + groundDistance + " meters");	
		}
				
		return intermediateDirections;
	}

	public int calcGroundDistance(int diff) {
		// TODO Auto-generated method stub
		int grndDist = Math.abs(diff)*2;
		grndDist = grndDist/39;
		return grndDist;
	}

}
