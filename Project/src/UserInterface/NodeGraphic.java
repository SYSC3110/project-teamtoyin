package UserInterface;

import java.awt.Color;
import java.awt.Graphics;

public class NodeGraphic {
	private int xPosition=50;
	private int yPosition=50;
	private int width=30;
	private int height=30;
	
	private String nodeName;
	
	public NodeGraphic(String nodeName, int xPosition, int yPosition){
		this.nodeName=nodeName;
		this.xPosition=xPosition;
		this.yPosition=yPosition;
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	
	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void paintNode(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(xPosition, yPosition, width, height);
		g.drawString(nodeName, xPosition+width-17, yPosition+height-10 );
	}
	

}
