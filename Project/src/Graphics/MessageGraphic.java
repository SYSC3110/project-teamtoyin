package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
/**
 * This class is used to print a red ball beside the current node in the network topology.. and a green ball 
 * beside the destination node at the end
 * @author Osama Buhamad
 *
 * @date 11/06/2016
 *
 */
public class MessageGraphic extends Graphic {

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
	
		
	} //end of paintMessage

	/**
	 * the method prints a green circle on the destination node when the message is delivered to it 
	 */
	public void paintLastMessage(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.fillOval(x+getxTextOffset(),y+getyTextOffset(), 20, 22);
		repaint();
		
	}
	

}
