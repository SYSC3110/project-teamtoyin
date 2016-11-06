package UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Graphics.MessageGraphic;
import Graphics.NodeGraphic;
import Network.Network;
import Network.Node;

/**
 * 
 * @author Osama Buhamad
 *
 * @date 11/04/2016
 *
 */

public class UserInterfaceGraphic extends JPanel  {
	
	
	private Network network;
	private List<Node> list;
	private List<NodeGraphic> nodeGraphiclist;
	private int count;
	private int setNodeCoordinatesCounter;
	private MessageGraphic mg;

	public UserInterfaceGraphic(Network network){
		JFrame f = new JFrame("nodes");
		f.add(this);
		
		this.network=network;
	    list = new ArrayList<Node>(network.getNodes());
	    nodeGraphiclist = new ArrayList<NodeGraphic>();
	    this.count=0;
	    setNodeCoordinatesCounter = 0;
	    
	    mg= new MessageGraphic(getName(), 0, 0);
	     
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(900,900);
		f.setVisible(true);
	}
	
	public void paintComponent(Graphics g){
			super.paintComponent(g);
			this.setBackground(Color.WHITE);

			//calling this method to set the coordinates of the nodes before painting them 
				if(setNodeCoordinatesCounter == 0)
					setNodeCoordinates();

				for (int i=0;i<nodeGraphiclist.size();i++){
					nodeGraphiclist.get(i).paintNode(g);
				}	
				
				for(int i=0;i<list.size()-1;i++){
					for(int j=i;j<list.size();j++)
					{
						if(list.get(i).hasNeighbor(list.get(j))){
							int x =nodeGraphiclist.get(j).getxPosition();
							int y =nodeGraphiclist.get(j).getyPosition();
							nodeGraphiclist.get(i).paintLink(g,x,y);	
						}
					}		
				}
				
				//trying to draw a moving circle from one node to another
				//for(int i=0;i<list.size()-1;i++){
					g.setColor(Color.RED);					
					//g.fillOval(nodeGraphiclist.get(0).getxPosition()+xball,nodeGraphiclist.get(0).getxPosition()+yball, 15, 15);
				//}
					
					/**
					 * TODO
					 * ISSUE: a ball is drawn at every node and we want only one ball that is moving 
					 */
				for (int i=0;i<nodeGraphiclist.size();i++){
					mg.paintMessage(g, nodeGraphiclist.get(i).getxPosition(),nodeGraphiclist.get(i).getyPosition());
					repaint();
					
				}
				
		}
	

					
		
	/*
	 * This method sets the coordinates of each node in the network
	 */
	public void setNodeCoordinates(){	
		int xPos=200;
		int yPos=150;
		for (int i=0;i<list.size();i++){

			NodeGraphic ng = new NodeGraphic(list.get(i).getName(),xPos,yPos);
			nodeGraphiclist.add(ng);


			//placing the nodes bases on the count value 
			if(count%3==0){
				xPos+=150;
				yPos+=0;
			}else {
				yPos+=150;
				xPos+=0;
			}
				count++;
		}
			setNodeCoordinatesCounter++;
	}
	
	
	

	
	
	public static void main(String[] args) {
		Network n = new Network();
		
		Node n1 = new Node("A");
		Node n2 = new Node("B");
		Node n3 = new Node("C");
		Node n4 = new Node("D");
		Node n5 = new Node("E");
		Node n6 = new Node("F");
		
		n.add(n1);
		n.add(n2);
		n.add(n3);
		n.add(n4);
		n.add(n5);
		n.add(n6);
		
		n.link(n1, n2);
		n.link(n2, n3);
		n.link(n3, n4);
		n.link(n5, n2);
		n.link(n6, n4);
		n.link(n5, n6);
		n.link(n1,n6);
		UserInterfaceGraphic graph = new UserInterfaceGraphic(n);
	}

}