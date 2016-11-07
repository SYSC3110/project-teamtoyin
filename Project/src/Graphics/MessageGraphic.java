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
	private ArrayList<NodeGraphic> nodeGraphicList;
	private int num;
	private int num2;
	
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
		//g.fillOval(this.getxPosition(),this.getyPosition(),getWidth() ,getHeight());
		g.drawOval(num+getWidth()+getxTextOffset(),num2+getWidth(), getWidth(), getHeight());
		//g.drawString(order,num+getxTextOffset(),num2, 15, 15);
		g.drawString(String.valueOf(order), num+getWidth()+getxTextOffset()+3, num2+getWidth()+getyTextOffset()+3);
		repaint();
	}
	
//	public void paintMessage(Graphics g, ArrayList<NodeGraphic> messagepath) {
//		nodeGraphicList=messagepath;
//		g.setColor(Color.RED);
//		//for(int i=0;i<nodeGraphicList.size();i++){
//
//			g.fillOval(nodeGraphicList.get(0).getxPosition()+xball,nodeGraphicList.get(0).getyPosition()+yball,getWidth() ,getHeight());
//			//repaint();
//
//		//}
//		//g.fillOval(xLocation+xball,yLocation+yball, 15, 15);	
//	}
	
	public void actionPerformed(ActionEvent e){
		if(xcounter==0){
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();		
			}
		}else if(xcounter==1){
			num+=num;
			num+=num2;
			repaint();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();		
			}
			
		}else{
//			num=num-xball;
//			num=num2-yball;
//			System.out.println("xball: "+xball);
//			System.out.println("yball: "+yball);
//			repaint();
//			
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();		
//			}
			xcounter=0;
		}
		xcounter++;
		/*
		xcounter++;
		ycounter++;
		
		if(xcounter<=num && ycounter<=num2){
				xball=num;
				yball=num2;
				xcounter++;
				ycounter++;
				repaint();
				
				}else{
					try {
						xcounter=0;
						ycounter=0;
						num+=num;
						num2+=100;
						yball=num2*2;
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();		
					}
				}
				*/
	}

	
	


	

}
