package com.example.NAVIGATION;

public class DirectionsSingleStep {
	String faceDist;
	float navigationBlockNumber;
	float x1;
	float x2;
	float y1;
	float y2;
	float cx;
	float cy;
	
	public DirectionsSingleStep(float blockNum, float x1, float x2, float y1, float y2, float cx, float cy, String faceDist) {
		this.faceDist = faceDist;
		this.navigationBlockNumber = blockNum;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.cx=cx;
		this.cy=cy;		
	}
}
