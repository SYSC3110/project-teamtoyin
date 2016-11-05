package UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Network.Network;
import Network.Node;

public class UserInterfaceGraphic extends JPanel {
	
	//private Algorithm algorithm;
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

	
			int xPos=50;
			int yPos=50;
		for (int i=0;i<list.size();i++){
			
			NodeGraphic ng = new NodeGraphic(list.get(i).getName(),xPos,yPos);
			nodeGraphiclist.add(ng);

			xPos+=150;
		    yPos+=33;
		}
		
		for (int i=0;i<nodeGraphiclist.size();i++){
			
		
			nodeGraphiclist.get(i).paintNode(g);


		}
		
		
	}
	
	
	
	//make a hashset of each node and the value be the x-axis and y-axis ??
	//use the hasNeighbor method to check then draw line 
	public void setNodeCoordinates(){
		
	}

	public static void main(String[] args) {
		Network n = new Network();
		
		Node n1 = new Node("T");
		Node n2 = new Node("O");
		Node n3 = new Node("Y");
		Node n4 = new Node("I");
		Node n5 = new Node("N");
		
		n.add(n1);
		n.add(n2);
		n.add(n3);
		n.add(n4);
		n.add(n5);
		
		
		

		//n.link(n1, n2);
		//n.link(n2, n3);
		//n.link(n3, n4);
		//n.link(n5, n2);
		UserInterfaceGraphic graph = new UserInterfaceGraphic(n);
		
		JFrame f = new JFrame("nodes");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(graph);
		f.setSize(900,900);
		f.setVisible(true);

	}

}
