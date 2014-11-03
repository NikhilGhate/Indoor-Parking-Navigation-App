package com.example.NEAREST_NEIGHBOUR;

public class MainClass {
	public static void main_test (String args[]) {
		SearchXYToConsider sc = new SearchXYToConsider();
		
		for (int i=1;i<=3;i++)
			for (int j=1;j<=15;j++)
		//System.out.println());
			{
		String answer = sc.x_y_to_consider(i, j);
			}
	}

}
