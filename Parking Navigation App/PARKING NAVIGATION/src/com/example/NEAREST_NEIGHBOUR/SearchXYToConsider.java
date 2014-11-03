package com.example.NEAREST_NEIGHBOUR;

import java.util.ArrayList;

public class SearchXYToConsider {
	public String x_y_to_consider(int x, int y) {		
		
			ArrayList<SearchArea> mySearchAreas = new ArrayList<SearchArea>();
			
			mySearchAreas.add(new SearchArea(1,1,	" AND ((x=1 AND Y=2) OR (x=4 AND Y=2) OR (x=1 AND Y=3))"));
			mySearchAreas.add(new SearchArea(1,2,	" AND ((x=1 AND Y=1) OR (x=4 AND Y=2) OR (x=1 AND Y=3))"));
			mySearchAreas.add(new SearchArea(1,3,	" AND ((x=1 AND Y=2) OR (x=1 AND Y=4))"));
			mySearchAreas.add(new SearchArea(1,4,	" AND ((x=1 AND Y=3) OR (x=1 AND Y=5))"));
			mySearchAreas.add(new SearchArea(1,5,	" AND ((x=1 AND Y=4) OR (x=1 AND Y=6))"));
			mySearchAreas.add(new SearchArea(1,6,	" AND ((x=1 AND Y=5) OR (x=1 AND Y=7))"));
			mySearchAreas.add(new SearchArea(1,7,	" AND ((x=1 AND Y=6) OR (x=1 AND Y=8))"));
			mySearchAreas.add(new SearchArea(1,8,	" AND ((x=1 AND Y=7) OR (x=1 AND Y=9))"));
			mySearchAreas.add(new SearchArea(1,9,	" AND ((x=1 AND Y=8) OR (x=1 AND Y=10))"));
			mySearchAreas.add(new SearchArea(1,10,	" AND ((x=1 AND Y=9) OR (x=1 AND Y=11))"));
			mySearchAreas.add(new SearchArea(1,11,	" AND ((x=1 AND Y=10 )OR (x=1 AND Y=12))"));
			mySearchAreas.add(new SearchArea(1,12,	" AND ((x=1 AND Y=11) OR (x=1 AND Y=13))"));
			mySearchAreas.add(new SearchArea(1,13,	" AND ((x=1 AND Y=12) OR (x=1 AND Y=14))"));
			mySearchAreas.add(new SearchArea(1,14,	" AND ((x=1 AND Y=13) OR (x=1 AND Y=15))"));
			mySearchAreas.add(new SearchArea(1,15,	" AND ((x=1 AND Y=13) OR (x=1 AND Y=14))"));

			mySearchAreas.add(new SearchArea(2,1,	" AND ((x=2 AND Y=2) OR (x=2 AND Y=3) OR (x=4 AND Y=2) OR (x=5 AND y=2))"));
			mySearchAreas.add(new SearchArea(2,2,	" AND ((x=2 AND Y=1) OR (x=2 AND Y=3) OR (x=4 AND Y=2) OR (x=5 AND y=2))"));
			mySearchAreas.add(new SearchArea(2,3,	" AND ((x=2 AND Y=2) OR (x=2 AND Y=4))"));
			mySearchAreas.add(new SearchArea(2,4,	" AND ((x=2 AND Y=3) OR (x=2 AND Y=5))"));
			mySearchAreas.add(new SearchArea(2,5,	" AND ((x=2 AND Y=4) OR (x=2 AND Y=6))"));
			mySearchAreas.add(new SearchArea(2,6,	" AND ((x=2 AND Y=5) OR (x=2 AND Y=7))"));
			mySearchAreas.add(new SearchArea(2,7,	" AND ((x=2 AND Y=6) OR (x=2 AND Y=8))"));
			mySearchAreas.add(new SearchArea(2,8,	" AND ((x=2 AND Y=7) OR (x=2 AND Y=9))"));
			mySearchAreas.add(new SearchArea(2,9,	" AND ((x=2 AND Y=8) OR (x=2 AND Y=10))"));
			mySearchAreas.add(new SearchArea(2,10,	" AND ((x=2 AND Y=9) OR (x=2 AND Y=11))"));
			mySearchAreas.add(new SearchArea(2,11,	" AND ((x=2 AND Y=10) OR (x=2 AND Y=12))"));
			mySearchAreas.add(new SearchArea(2,12,	" AND ((x=2 AND Y=11) OR (x=2 AND Y=13))"));
			mySearchAreas.add(new SearchArea(2,13,	" AND ((x=2 AND Y=12) OR (x=2 AND Y=14))"));
			mySearchAreas.add(new SearchArea(2,14,	" AND ((x=2 AND Y=13) OR (x=2 AND Y=15))"));
			mySearchAreas.add(new SearchArea(2,15,	" AND ((x=2 AND Y=13) OR (x=2 AND Y=14))"));
							
			mySearchAreas.add(new SearchArea(3,1,	" AND ((x=3 AND Y=2) OR (x=5 AND Y=2) OR (x=6 AND y=2))"));
			mySearchAreas.add(new SearchArea(3,2,	" AND ((x=3 AND Y=1) OR (x=5 AND Y=2) OR (x=6 AND y=2) OR (x=3 AND y=3))"));
			mySearchAreas.add(new SearchArea(3,3,	" AND ((x=3 AND Y=2) OR (x=6 AND Y=2))"));
			mySearchAreas.add(new SearchArea(3,4,	" AND ((x=3 AND Y=3) OR (x=3 AND Y=4))"));
			mySearchAreas.add(new SearchArea(3,5,	" AND ((x=3 AND Y=4) OR (x=3 AND Y=5))"));
			mySearchAreas.add(new SearchArea(3,6,	" AND ((x=3 AND Y=5) OR (x=3 AND Y=6))"));
			mySearchAreas.add(new SearchArea(3,7,	" AND ((x=3 AND Y=6) OR (x=3 AND Y=7))"));
			mySearchAreas.add(new SearchArea(3,8,	" AND ((x=3 AND Y=7) OR (x=3 AND Y=8))"));
			mySearchAreas.add(new SearchArea(3,9,	" AND ((x=3 AND Y=8) OR (x=3 AND Y=9))"));
			mySearchAreas.add(new SearchArea(3,10,	" AND ((x=3 AND Y=9) OR (x=3 AND Y=10))"));
			mySearchAreas.add(new SearchArea(3,11,	" AND ((x=3 AND Y=10) OR (x=3 AND Y=11))"));
			mySearchAreas.add(new SearchArea(3,12,	" AND ((x=3 AND Y=11) OR (x=3 AND Y=12))"));
			mySearchAreas.add(new SearchArea(3,13,	" AND ((x=3 AND Y=12) OR (x=3 AND Y=13))"));
			mySearchAreas.add(new SearchArea(3,14,	" AND ((x=3 AND Y=13) OR (x=3 AND Y=15))"));
			mySearchAreas.add(new SearchArea(3,15,	" AND ((x=3 AND Y=13) OR (x=3 AND Y=14))"));


			for (SearchArea sa : mySearchAreas)
			{
				if (sa.getX()==x && sa.getY()==y) {
					return sa.getText();
				}			
			}
			return "Default";
	}

}
