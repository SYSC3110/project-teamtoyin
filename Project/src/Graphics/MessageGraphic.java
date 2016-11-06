package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class MessageGraphic extends Graphic implements ActionListener{

	/*
	 * the variables for the animation part
	 */
	int velocity =1;
	Timer timer = new Timer(5,this);
	int xball=9,yball;
	int xcounter=0,ycounter=0;
	
	public MessageGraphic(String nodeName, int xPosition, int yPosition) {
		super(nodeName, xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent e){
		if(xcounter<=150 && ycounter<=150){
		xball+=velocity;
		yball+=velocity;
		//repaint();
		xcounter++;
		//ycounter++;
		}
	}
	
	public void paintMessage(Graphics g, int xDestentation, int yDestenation){
		g.setColor(Color.BLACK);
		g.drawLine(this.getxPosition()+getWidth(),this.getyPosition()+getWidth() ,xDestentation ,yDestenation);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

}
