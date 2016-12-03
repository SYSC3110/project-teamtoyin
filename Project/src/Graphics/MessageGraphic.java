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
		this.setHeight(10);
		this.setWidth(10);
		this.setxTextOffset(10);
		this.setyTextOffset(33);

	}
	
	public void paintMessage(Graphics g, int xLocation, int yLocation) {
		
		g.setColor(Color.RED);
		g.fillOval(xLocation+getxTextOffset(),yLocation+getyTextOffset(), getWidth(), getHeight());		
		repaint();
	
		/**
		 * TODO
		 * When updated with stepping forward and backward, need to paint the old position with white(delete it) and 
		 * go to the next position to paint it  
		 */
		
	} //end of paintMessage
	

}
