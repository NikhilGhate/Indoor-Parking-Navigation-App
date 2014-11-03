package com.example.NAVIGATION;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("X,Y from touch:");
		float x1 = in.nextInt();
		float y1 = in.nextInt();
		Navigate n = new Navigate(x1, y1, 0, 0);
		n.navigate();
		System.out.println("-------------------------");
		float[] assoc = n.getResultantAssociatedBlockRow();
		float[] conn = n.getResultantConnectorBlockRow();
		System.out.println("Spine:" + conn[0] + " " + conn[1] + " " + conn[2]);
		System.out.println("-------------------------");
		
		System.out.println("X,Y from touch:");
		float x2 = in.nextInt();
		float y2 = in.nextInt();
		Navigate n2 = new Navigate(x2, y2, 0, 0);
		n2.navigate();
		System.out.println("-------------------------");
		float[] assoc2 = n2.getResultantAssociatedBlockRow();
		float[] conn2 = n2.getResultantConnectorBlockRow();
		System.out.println("Spine:" + conn2[0] +  " " + conn2[1] + " " + conn2[2]);
		System.out.println("-------------------------");
		
		System.out.println("=========Summary==========");		

		if (!(conn2[3]==conn[3] && conn2[4]==conn[4])) {
			System.out.println("Line 1: b:" + assoc[0] + " " + assoc[5] + " " + assoc[6] + " " + " b:" + conn[0] + " " + conn[5] + " " + conn[6]);
			System.out.println("Line 2: b:" + conn2[0] + " " + conn2[5] + " " + conn2[6] + " b:" + conn[0] + " " + conn[5] + " " + conn[6]);
			System.out.println("Line 3: b:" + assoc2[0] + " " + assoc2[5] + " " + assoc2[6] + " b:" + conn2[0] + " " + conn2[5] + " " + conn2[6]);
			
		}
		
		else {
			System.out.println("Line 1: b:" + assoc[0] + " " + assoc[5] + " " + assoc[6] + " b:" + assoc2[0] + " " + assoc2[5] + " " + assoc2[6]);
		}
		
		System.out.println("=========Summary==========");
		in.close();
		
		
		
	}
	
}
