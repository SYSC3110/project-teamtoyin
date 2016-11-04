package UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Algorithm.Algorithm;
import Network.Network;
import Network.Node;

public class UserInterfaceGraphics extends JPanel {
	
	//private Algorithm algorithm;
	private Network network;
	private int xCoordinate,yCoordinate,boxDimension1,boxDimension2;
	private int nodesNum;
	private List<Node> list;
	private int count=0;
	public UserInterfaceGraphics(Network network){

		this.network=network;
		this.nodesNum=network.getNetworkNodesNumber();

	     list = new ArrayList<Node>(network.getNodes());

		xCoordinate=100;
		yCoordinate=250;
		boxDimension1=30;
		boxDimension2=30;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		this.setBackground(Color.WHITE);
		g.setColor(Color.BLUE);
		// Creating a List of HashSet elements

		for (Node n : list){
			
			g.drawRect(xCoordinate, yCoordinate, boxDimension1, boxDimension2);
			g.drawString(n.getName(), xCoordinate+10, yCoordinate+20 );
			setCoordinates(60);	
		}
		
		
		
		/*
		for (int i=0;i<list.size();i++){
			
			
			g.setColor(Color.BLUE);
			//g.drawLine(xCoordinate, yCoordinate,boxDimension1,boxDimension1);
			
			//setCoordinates(40);
		}
		
		*/
	}
	
	public void setCoordinates(int x){
	
			xCoordinate+=x*count;
			
	 if(count>=2){
			yCoordinate+=x;
			count=0;
		}
	 count++;
		
		
		
	}
	
	//make a hashset of each node and the value be the x-axis and y-axis ??
	//use the hasNeighbor method to check then draw line 
	public void setNodeCoordinates(){
		
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
		
		
		

		//n.link(n1, n2);
		//n.link(n2, n3);
		//n.link(n3, n4);
		//n.link(n5, n2);
		UserInterfaceGraphics graph = new UserInterfaceGraphics(n);
		
		JFrame f = new JFrame("nodes");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(graph);
		f.setSize(900,900);
		f.setVisible(true);

	}

}
