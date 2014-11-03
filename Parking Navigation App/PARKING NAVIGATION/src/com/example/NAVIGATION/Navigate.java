package com.example.NAVIGATION;

import java.util.Scanner;


public class Navigate {
	
	private float x;
	private float y;
	private float XIncr;
	private float YIncr;
	private float[] resultantBlockEntry;
	private float[] resultantSlotEntry;
	private float[] resultantAssociatedBlockRow;
	private float[] resultantConnectorBlockRow;
	
	private int connectorBlock;
	
	public Navigate(float x, float y, float Xincr, float Yincr) {
		setX(x);
		setY(y);
		setXIncr(Xincr);
		setYIncr(Yincr);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float[] getResultantBlockEntry() {
		return resultantBlockEntry;
	}

	public void setResultantBlockEntry(float[] resultantBlockEntry) {
		this.resultantBlockEntry = resultantBlockEntry;
	}

	public float[] getResultantSlotEntry() {
		return resultantSlotEntry;
	}

	public void setResultantSlotEntry(float[] resultantSlotEntry) {
		this.resultantSlotEntry = resultantSlotEntry;
	}

	public float[] getResultantAssociatedBlockRow() {
		return resultantAssociatedBlockRow;
	}

	public void setResultantAssociatedBlockRow(float[] resultantAssociatedBlockRow) {
		this.resultantAssociatedBlockRow = resultantAssociatedBlockRow;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	public int getConnectorBlock() {
		return connectorBlock;
	}

	public void setConnectorBlock(int connectorBlock) {
		this.connectorBlock = connectorBlock;
	}

	public float[] getResultantConnectorBlockRow() {
		return resultantConnectorBlockRow;
	}

	public void setResultantConnectorBlockRow(float[] resultantConnectorBlockRow) {
		this.resultantConnectorBlockRow = resultantConnectorBlockRow;
	}

	@SuppressWarnings("resource")
	public void inputXY() {
		System.out.println("\nSelect x,y: ");
		Scanner in = new Scanner(System.in);
		x= in.nextFloat();
		setX(x);
		y= in.nextFloat();
		setY(y);
	}
	

	public void navigate() {
		NavigationBlocks nb = new NavigationBlocks(0,0);
		ParkingSlots ps = new ParkingSlots(0,0);
		BlockSlotAssociation bsa = new BlockSlotAssociation();
		//Navigate nav = new Navigate(getX(),getY());
		 
		//while (true) {
		//inputXY();
		
		setResultantBlockEntry(nb.getBlock(getX(), getY()));
		setResultantSlotEntry(ps.getSlot(getX(), getY()));
		
		
		float[] resultantBlockEntry = getResultantBlockEntry();
		float[] resultantSlotEntry = getResultantSlotEntry(); 
		float[] resultantAssociatedBlockRow_ = null;
		float[] resultantConnectorBlockRow_ = null;
		
		int associatedBlock=0;
		int connectorBlock=0;
		
		System.out.println("\nAnswer");
		
			if (resultantBlockEntry[0]!=9999) {
				System.out.println("\nBLOCK!\n");
				System.out.println("Block:" + (int)resultantBlockEntry[0]);
				System.out.println("Block (" + (int)((resultantBlockEntry[0]/16)+1) + ", " 
											 + (int)((resultantBlockEntry[0]%15)) + ")"); 
				System.out.println("Centre: " + (int)resultantBlockEntry[5] + ", " 
											  + (int)resultantBlockEntry[6]);
				
				// We set the value of the clicked block as the 'associated' block for class ...
				setResultantAssociatedBlockRow(resultantBlockEntry); 
				associatedBlock = ((int)resultantBlockEntry[0]); // ... and locally
				resultantAssociatedBlockRow_ = getResultantAssociatedBlockRow(); // ... and locally again
				System.out.println("1resultantConnectorBlockRow_"+getResultantAssociatedBlockRow());
				
				connectorBlock = nb.getConnectorBlock((int)resultantBlockEntry[0]);
				resultantConnectorBlockRow_ = nb.getBlockRowByBlockNumber(connectorBlock);
				setResultantConnectorBlockRow(resultantConnectorBlockRow_);
				System.out.println("Connector Block Number: " + connectorBlock);	
				System.out.println("Connector Block ROW: " + getResultantConnectorBlockRow());	
						
			}
			else if (resultantSlotEntry[0]!=9999) {
				System.out.println("\nSLOT!\n");
				System.out.println("Slot:" + (int)resultantSlotEntry[0]);

				System.out.println("Slot :" + bsa.getXSlot((int)resultantSlotEntry[0]) + ", " 
											+ bsa.getYSlot((int)resultantSlotEntry[0]));
				
				associatedBlock = bsa.BlockSlotAssociation_disp((int)resultantSlotEntry[0]);
				setResultantAssociatedBlockRow(nb.getBlockRowByBlockNumber(associatedBlock));
				resultantAssociatedBlockRow_ = getResultantAssociatedBlockRow();
				
				System.out.println("\nAssociated for Slot:" + (int)resultantSlotEntry[0]
						+ " is Block: " + associatedBlock);
							
				System.out.println("Block :" + bsa.getXBlock(associatedBlock) + ", " 
						+ bsa.getYBlock(associatedBlock));
				
				System.out.println("Centre: " + (int)resultantAssociatedBlockRow_[1] + ", " 
						  + (int)resultantAssociatedBlockRow_[2]);
				
				//Connector Block
				connectorBlock = nb.getConnectorBlock(associatedBlock);
				setConnectorBlock (connectorBlock);
				System.out.println("Connector Block: " + getConnectorBlock());
				
				//Connector Block Row				
				resultantConnectorBlockRow_ = nb.getBlockRowByBlockNumber(connectorBlock);				
				setResultantConnectorBlockRow(resultantConnectorBlockRow_);
				System.out.println("Connector Block Row:" + resultantConnectorBlockRow_[0]);
			}
			else if (resultantBlockEntry[0]==9999&&resultantSlotEntry[0]==9999) {
				System.out.println("Both are 9999");	
			}
		//}
	}

	public float getXIncr() {
		return XIncr;
	}

	public void setXIncr(float xIncr) {
		XIncr = xIncr;
	}

	public float getYIncr() {
		return YIncr;
	}

	public void setYIncr(float yIncr) {
		YIncr = yIncr;
	}


	
}
