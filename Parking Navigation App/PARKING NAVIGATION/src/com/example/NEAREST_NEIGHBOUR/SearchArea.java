package com.example.NEAREST_NEIGHBOUR;

import java.util.ArrayList;

public class SearchArea {
	private int x;
	private int y;
	private String text;
	
	public SearchArea() {		
	}
	
	public SearchArea(int xVal, int yVal, String textVal) {
		setX(xVal);
		setY(yVal);
		setText(textVal);		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}	
	
	

}

	
		


