package UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

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
	public UserInterfaceGraphic(Network network){

		this.network=network;
		

	     list = new ArrayList<Node>(network.getNodes());
	     nodeGraphiclist = new ArrayList<NodeGraphic>();
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		this.setBackground(Color.WHITE);

		//calling this method to set the coordinates of the nodes before painting them 
		setNodeCoordinates();
		
		for (int i=0;i<nodeGraphiclist.size();i++){
			nodeGraphiclist.get(i).paintNode(g);
		}	
		
		for(int i=0;i<list.size();i++){
			if(isLinked(list.get(i),list.get(i+1))){
				int x =nodeGraphiclist.get(i+1).getxPosition();
				int y =nodeGraphiclist.get(i+1).getyPosition();
				nodeGraphiclist.get(i).paintLink(g,x,y);
			}
			
		}

	}
	
	
	/*
	 * This method sets the coordinates of each node in the network
	 */
	public void setNodeCoordinates(){

		int xPos=50;
		int yPos=50;
		for (int i=0;i<list.size();i++){

			NodeGraphic ng = new NodeGraphic(list.get(i).getName(),xPos,yPos);
			nodeGraphiclist.add(ng);

			//these values are random for now 
			xPos+=150;
			yPos+=33;
		}
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
		
		n.add(n1);
		n.add(n2);
		n.add(n3);
		n.add(n4);
		n.add(n5);
		
		
		

		n.link(n1, n2);
		n.link(n2, n3);
		n.link(n3, n4);
		n.link(n5, n2);
		UserInterfaceGraphic graph = new UserInterfaceGraphic(n);
		
		JFrame f = new JFrame("nodes");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(graph);
		f.setSize(900,900);
		f.setVisible(true);

	}

}
