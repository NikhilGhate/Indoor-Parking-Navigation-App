package com.example.NAVIGATION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CellsOnRoute {
	public List<int[]> listOfCellXandY;
	
	public List<Integer> listOfCellNumbers;
	
	
	public CellsOnRoute() {
		listOfCellXandY = new ArrayList<int[]>();	
		listOfCellNumbers = new ArrayList<Integer>();
	}
	
	public List<Integer> getListOfCellNumbersOnRoute (float[] assoc, float[] conn, float[] conn2, float[] assoc2) {
		
		if (assoc[0]>conn[0]) {
			for(int i=(int)assoc[0]; i>= (int)conn[0]; i--) {
				listOfCellNumbers.add(i);
			}			
		}
		
		if (assoc[0]<conn[0]) {
			for(int i=(int)assoc[0]; i<= (int)conn[0]; i++) {
				listOfCellNumbers.add(i);
			}
		}
		
		if (conn2[0]>assoc2[0]) {
			for (int i=(int)conn2[0]; i>=assoc2[0];i--) {
				listOfCellNumbers.add(i);
			}
		}
		
		
		float[] connectors = {2f,46f,17f,47f,32f,48f};
		int id1 = 0, id2 = 0;
		for (int i=0; i <6; i++) {
			if (connectors[i]==conn[0]) {
				id1 = i;
			}
			if (connectors[i]==conn2[0]) {
				id2 = i;
			}			
		}
		
		if (id1>id2) {
			for (int i=id1;i>=id2;i--)
				listOfCellNumbers.add((int) connectors[i]);			
		}
		else if (id1<id2) {
			for (int i=id1;i<=id2;i++)
				listOfCellNumbers.add((int) connectors[i]);
		}
		else if (id1==id2) {
				listOfCellNumbers.add((int) connectors[id1]);
		}
		
		
		if(conn2[0]<assoc2[0]) {
			for (int i=(int)conn2[0]; i<=assoc2[0];i++) {
				listOfCellNumbers.add(i);
			}
		}
		
		
		
		return listOfCellNumbers;
	}
	
	public List<int[]> getCellArraysOnRoute() {
		BlockSlotAssociation bsa = new BlockSlotAssociation();
		for (Integer row : listOfCellNumbers) {
	        //System.out.println("Row = " + Arrays.toString(row));
			int[] xy = {bsa.getXBlock((int)row),bsa.getYBlock(row)};
			listOfCellXandY.add(xy);
	    }
		return listOfCellXandY;	
	}
}
