package com.example.NAVIGATION;

import java.util.ArrayList;

public class GetDirectionsStepByStep {
	
	ArrayList<DirectionsSingleStep> allDirectionsList;
	String directionListAtOneBlock;

	
	public GetDirectionsStepByStep(float[] assoc, float[] conn, float[] conn2, float[] assoc2) {
		allDirectionsList = new ArrayList<DirectionsSingleStep>();
		directionListAtOneBlock = "";
		
		float xDiff=0; 
		float yDiff=0; 
		
		xDiff = assoc[5]-conn[5];
		yDiff = assoc[6]-conn[6];
				
		directionListAtOneBlock = intermediateDirections(xDiff,yDiff);
		allDirectionsList.add(new DirectionsSingleStep(assoc[0],assoc[1],assoc[2],assoc[3],assoc[4],assoc[5],assoc[6],directionListAtOneBlock));

		xDiff = conn[5]-conn2[5];
		yDiff = conn[6]-conn2[6];
				
		directionListAtOneBlock = intermediateDirections(xDiff,yDiff);
		allDirectionsList.add(new DirectionsSingleStep(conn[0],conn[1],conn[2],conn[3],conn[4],conn[5],conn[6],directionListAtOneBlock));

		xDiff = conn2[5]-assoc2[5];
		yDiff = conn2[6]-assoc2[5];
				
		directionListAtOneBlock = intermediateDirections(xDiff,yDiff);
		allDirectionsList.add(new DirectionsSingleStep(conn2[0],conn2[1],conn2[2],conn2[3],conn2[4],conn2[5],conn2[6],directionListAtOneBlock));
	}
	
	public String fetchDirectionsForBlock(int x, int y) {
		
		int xInDirections = 0;
		int yInDirections = 0;
		
		for (DirectionsSingleStep a: allDirectionsList) {
			xInDirections = (int)((a.navigationBlockNumber/16)+1);
			yInDirections =  (int)((a.navigationBlockNumber%15));						
			
			System.out.println("\n X in direc = " + xInDirections);
			System.out.println("\n Y in direc = " + yInDirections);
			
			if(x==xInDirections && y==yInDirections) {
				return a.faceDist;
			}
		}		
		return "Nothing found here";
	}

	
	
	public String intermediateDirections(float a, float b) {
		
		String stepsAtOneBlock = "";
		int groundDistance = 0;
		
		if (a == 0 && b == 0) {
			//do nothing as it is the same point
		}
		else if (a > 0 && b == 0) {
			//We're going left 
			stepsAtOneBlock = "Face West.\n";
			groundDistance = calcGroundDistance ((int)a); 
			stepsAtOneBlock += "Continue driving west for " + groundDistance + " meters";
					
		}
		else if (a < 0 && b == 0) {
			//We're going right on the same horizontal row
			stepsAtOneBlock = "Face East.\n";
			groundDistance = calcGroundDistance ((int)a); 
			stepsAtOneBlock += "Continue driving East for " + groundDistance + " meters";
		}							
		else if (a == 0 && b > 0) {
			//We're going up
			stepsAtOneBlock = "Face North.\n";			
			groundDistance = calcGroundDistance ((int)b); 
			stepsAtOneBlock += "Continue driving North for " + groundDistance + " meters";
		}
		else if (a == 0 && b < 0) {
			//We're going down on the same vertical row			
			stepsAtOneBlock = "Face South.\n";	
			groundDistance = calcGroundDistance ((int)b); 
			stepsAtOneBlock += "Continue driving South for " + groundDistance + " meters";
		}
				
		return stepsAtOneBlock;
	}

	public int calcGroundDistance(int diff) {
		// TODO Auto-generated method stub
		int grndDist = Math.abs(diff)*2;
		grndDist = grndDist/39;
		return grndDist;
	}

	
	
	
	
	
}
