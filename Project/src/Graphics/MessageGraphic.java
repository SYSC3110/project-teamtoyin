package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


/**
 * 
 * @author Osama Buhamad
 *
 * @date 11/06/2016
 *
 */
public class MessageGraphic extends Graphic implements ActionListener{

	/*
	 * the variables for the animation part
	 */
	int velocity =1;
	Timer timer = new Timer(10,this);
	int xball=9,yball;
	int xcounter=0,ycounter=0;
	
	public MessageGraphic(String nodeName, int xPosition, int yPosition) {
		super(nodeName, xPosition, yPosition);
		this.setHeight(15);
		this.setWidth(15);
		timer.start();
	}
	
	public void paintMessage(Graphics g, int xLocation, int yLocation){
		g.setColor(Color.RED);
		//g.fillOval(this.getxPosition(),this.getyPosition(),getWidth() ,getHeight());
		g.fillOval(xLocation+xball,yLocation+yball, 15, 15);
		repaint();
	
		
		/**
		 * TODO
		 * call reset the coordinates (make the method)  
		 */
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(xcounter<=100){
		xball+=velocity;
		//yball+=velocity;
		repaint();
		xcounter++;
		//ycounter++;
		}else{
			try {
				xcounter=0;
				Thread.sleep(400);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
		}
		

	}
	


	

}
