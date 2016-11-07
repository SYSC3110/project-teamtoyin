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
	int xball=9,yball;
	int xcounter=0,ycounter=0;
	private ArrayList<NodeGraphic> nodeGraphicList;
	private int num;
	private int num2;
	
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
	}
	
	public void paintMessage(Graphics g, ArrayList<NodeGraphic> messagepath) {
		nodeGraphicList=messagepath;
		g.setColor(Color.RED);
		//for(int i=0;i<nodeGraphicList.size();i++){

			g.fillOval(nodeGraphicList.get(0).getxPosition()+xball,nodeGraphicList.get(0).getyPosition()+yball,getWidth() ,getHeight());
			//repaint();
			num=nodeGraphicList.get(1).getxPosition();
			num2=nodeGraphicList.get(1).getyPosition();
		//}
		//g.fillOval(xLocation+xball,yLocation+yball, 15, 15);	
	}
	
	public void actionPerformed(ActionEvent e){
		if(xball<=num && yball<=num2){
				xball+=velocity;
				yball+=velocity;
				repaint();
				xcounter++;
				ycounter++;
				}else{
					try {
						xcounter=0;
						ycounter=0;
						xball=0;
						yball=0;
						Thread.sleep(400);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();		
					}
				}
	}

	
	


	

}
