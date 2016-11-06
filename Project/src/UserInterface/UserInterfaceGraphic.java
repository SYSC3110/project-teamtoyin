package UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Network.Network;
import Network.Node;

/**
 * 
 * @author Osama Buhamad
 *
 * @date 11/04/2016
 *
 */

public class UserInterfaceGraphic extends JPanel {
	
	
	private Network network;
	private List<Node> list;
	private List<NodeGraphic> nodeGraphiclist;
	int count;
	private int setNodeCoordinatesCounter;

	public UserInterfaceGraphic(Network network){
		this.network=network;
	     list = new ArrayList<Node>(network.getNodes());
	     nodeGraphiclist = new ArrayList<NodeGraphic>();
	     this.count=0;
	     setNodeCoordinatesCounter = 0;
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
						if(isLinked(list.get(i),list.get(j))){
							//System.out.println("node: "+list.get(i).getName()+" is linked with: "+list.get(j).getName());
							int x =nodeGraphiclist.get(j).getxPosition();
							int y =nodeGraphiclist.get(j).getyPosition();
							nodeGraphiclist.get(i).paintLink(g,x,y);
					
						}
					}		
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

			//these values are random for now 
//			Random rand = new Random();
//			int  n = rand.nextInt(90) + 40;
//			xPos+=50;
//			yPos+=50;
			if(count%2==0){
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

	public boolean isLinked(Node n1, Node n2){
		
			if(n1.hasNeighbor(n2)){
				return true;
			}
		return false;
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
		UserInterfaceGraphic graph = new UserInterfaceGraphic(n);
		
		JFrame f = new JFrame("nodes");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(graph);
		f.setSize(900,900);
		f.setVisible(true);

	}

}
