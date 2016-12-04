package Graphics;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class is used to paint the network topology nodes and their links 
 * @author Osama Buhamad
 *
 * @date 11/04/2016
 *
 */
public class NodeGraphic extends Graphic {
	/*
	 * the constructor takes a node name and the x and y coordinates 
	 */
	public NodeGraphic(String nodeName, int xPosition, int yPosition){
		super(nodeName, xPosition, yPosition);
		this.setName(nodeName);
		this.setHeight(30);
		this.setWidth(30);
		this.setxTextOffset(17);
		this.setyTextOffset(10);
	}

	/**
	 * this method draws a node based on its coordinates 
	 * @param g
	 */
	public void paintNode(Graphics g){
		g.setColor(Color.BLUE);
		
		g.drawRect(this.getxPosition(),this.getyPosition(), this.getWidth(), this.getHeight());
	
		
		g.drawString(this.getNodeName(), this.getxPosition()+this.getWidth()-this.getxTextOffset(), this.getyPosition()+this.getHeight()-this.getyTextOffset());
		
	}
	
	public void paintLink(Graphics g, int xDestentation, int yDestenation){
		g.setColor(Color.BLACK);
		g.drawLine(this.getxPosition()+getWidth(),this.getyPosition()+getWidth() ,xDestentation ,yDestenation);
		
	}
	
	
	

}