package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
/**
 * 
 * @author Osama Buhamad
 *
 * @date 11/06/2016
 *
 */
public class MessageGraphic extends Graphic {

	/*
	 * the variables for the animation part
	 */
	private HashSet<Point> points;
	private int currentOffset;
	/**
	 * the instructor of the MessageGraphic 
	 * @param nodeName
	 * @param xPosition
	 * @param yPosition
	 */
	public MessageGraphic(String nodeName, int xPosition, int yPosition) {
		super(nodeName, xPosition, yPosition);
		this.setHeight(25);
		this.setWidth(25);
		this.setxTextOffset(20);
		this.setyTextOffset(15);
		
		points = new HashSet<Point>();
		currentOffset=getWidth();
	}
	
	public void paintMessage(Graphics g, int xLocation, int yLocation,int order) {
		
		g.setColor(Color.RED);
		Point p = new Point(xLocation,yLocation);
		points.add(p);
		if(!points.contains(p)){
			g.drawOval(xLocation+getWidth()+getxTextOffset()*order,yLocation+getWidth()*order, getWidth(), getHeight());
			g.drawString(String.valueOf(order), xLocation+getWidth()+getxTextOffset()+3*order, yLocation+getWidth()+getyTextOffset()+3*order);
			repaint();
		}else{
			//System.out.println("here");
			g .drawOval(xLocation+getWidth()+getxTextOffset()*order,yLocation+getWidth()*order, getWidth(), getHeight());
			g.drawString(String.valueOf(order), xLocation+getWidth()+(currentOffset*order)-3, yLocation+getWidth()+(currentOffset*order)-5);
			repaint();
		}
				
	} //end of paintMessage
	

}
