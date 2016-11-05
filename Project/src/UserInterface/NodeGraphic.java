package UserInterface;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Osama Buhamad
 *
 * @date 11/04/2016
 *
 */
public class NodeGraphic {
	
	//these variables are used to define the x-axis and y-axis position of a node 
	private int xPosition;
	private int yPosition;
	
	//these two variables are used to set the dimensions of the box that represents the node 
	private int width=30;
	private int height=30;
	
	//these variables are used to properly position the node name in the box 
	private int xTextOffset=17;
	private int yTextOffset=10;
	
	
	private String nodeName;
	
	/*
	 * the constructor takes a node name and the x and y coordinates 
	 */
	public NodeGraphic(String nodeName, int xPosition, int yPosition){
		this.nodeName=nodeName;
		this.xPosition=xPosition;
		this.yPosition=yPosition;
	}

	
	/**
	 * return the x-axis Position
	 * @return
	 */
	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	
	/**
	 * return the y-axis Position
	 * @return
	 */
	public int getyPosition() {
		return yPosition;
	}

	/**
	 * Setting the y-axis Position
	 * @param yPosition
	 */
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	/**
	 * return the width of the node box 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * return the height of the node box
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void paintNode(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(xPosition, yPosition, width, height);
		g.drawString(nodeName, xPosition+width-xTextOffset, yPosition+height-yTextOffset );
	}
	

}
