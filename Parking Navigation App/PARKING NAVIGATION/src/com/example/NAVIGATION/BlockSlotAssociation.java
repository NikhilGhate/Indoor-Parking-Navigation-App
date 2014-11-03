package com.example.NAVIGATION;

import java.util.Scanner;

public class BlockSlotAssociation {
	
	public int BlockSlotAssociation_disp(int i) {		
		
			 if (i>=1&&i<=30) {
				 if(i%15!=0) 
					 return (i%15);
				 else 
					 return ((i%15)+15);
			 }
			 else if (i>=31&&i<=60) {
				 if(i%15!=0) 
					 return ((i%15)+15);
				 else 
					 return ((i%15)+30);
			 }
			 else if (i>=61&&i<=90) {
				 if(i%15!=0) 
					 return ((i%15)+30);
				 else 
					 return ((i%15)+45);
			 }
			return 0;	
	}	
	
	public int getXSlot (int n) {
		if (n>=1&&n<=15)
			return 1;
		else if (n>=16&&n<=30)
			return 2;
		else if (n>=31&&n<=45)
			return 3;
		else if (n>=46&&n<=60)
			return 4;
		else if (n>=61&&n<=75)
			return 5;
		else if (n>=76&&n<=90)
			return 6;
		
		return 9999;		
	}
	
	public int getYSlot (int n) {
		//System.out.println("\n\n n= "+ n+ " " + n%16 +"\n\n");
		if (n%15!=0)
			return n%15;
		else
			return 15;
		
		
	}
	
	public int getXBlock (int n) {
		if (n>=1&&n<=15)
			return 1;
		else if (n>=16&&n<=30)
			return 2;
		else if (n>=31&&n<=45)
			return 3;		
		else if (n==46)
			return 4;
		else if (n==47)
			return 5;
		else if (n==48)
			return 6;
		
		return 9999;		
	}
	
	public int getYBlock (int n) {
		//System.out.println("\n\n n= "+ n+ " " + n%16 +"\n\n");
		if (n%15!=0)
			return n%15;
		else
			return 15;
		
		
	}
	
	public static void main(String args[]) {
		while (true) {
			System.out.println("\nPut in the slot:");
			BlockSlotAssociation bsa = new BlockSlotAssociation();
			Scanner in = new Scanner(System.in);
			int a = in.nextInt();
			System.out.println("Associated for " + a + ": " + bsa.BlockSlotAssociation_disp(a));
			System.out.println("X:"+bsa.getXSlot(a));
			System.out.println("Y:"+bsa.getYSlot(a));
		}
	}
}
