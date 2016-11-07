package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	int xball=150,yball=250;
	int xcounter=0,ycounter=0;

	private int num;
	private int num2;
	private int moveText=0;
	public MessageGraphic(String nodeName, int xPosition, int yPosition) {
		super(nodeName, xPosition, yPosition);
		this.setHeight(25);
		this.setWidth(25);
		this.setxTextOffset(20);
		this.setyTextOffset(15);
		timer.start();
		num=0;
		num2=0;
	}
	
	public void paintMessage(Graphics g, int xLocation, int yLocation,int order){
		g.setColor(Color.RED);
		num=xLocation;
		num2=yLocation;
		
		g.drawOval(num+getWidth()+getxTextOffset(),num2+getWidth(), getWidth(), getHeight());
		g.drawString(String.valueOf(order), num+getWidth()+getxTextOffset()+3, num2+getWidth()+getyTextOffset()+3);
		repaint();
		
	}
	

	
	public void actionPerformed(ActionEvent e){
		
		if(xcounter<=20){
			moveText+=10;
		}		
			try {
				
				repaint();
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();		
			}

	}

}
