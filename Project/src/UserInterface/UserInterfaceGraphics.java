package UserInterface;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Algorithm.Algorithm;
import Network.Network;
import Network.Node;

public class UserInterfaceGraphics extends JPanel {
	
	//private Algorithm algorithm;
	private Network network;
	private int x1,x2,y1,y2;
	private int nodesNum;
	public UserInterfaceGraphics(int nodesNum){

		this.nodesNum=nodesNum;
		x1=10;
		x2=10;
		y1=50;
		y2=50;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		this.setBackground(Color.WHITE);
		for (int i=0;i<nodesNum;i++){
			g.setColor(Color.BLUE);
			g.drawRect(x1, x2, y1, y1);
			
			setCoordinates(70);
		}
		
	}
	
	public void setCoordinates(int x){
		//x1+=x;
		x2+=x;
		
	}

	public static void main(String[] args) {
		Network n = new Network();
		
		Node n1 = new Node("A");
		Node n2 = new Node("B");
		Node n3 = new Node("C");
		Node n4 = new Node("D");
		
		n.add(n1);
		n.add(n2);
		n.add(n3);
		n.add(n4);
		
		n.link(n1, n2);
		n.link(n2, n3);
		n.link(n3, n4);
		
		UserInterfaceGraphics graph = new UserInterfaceGraphics(4);
		

		
		JFrame f = new JFrame("nodes");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(graph);
		f.setSize(800,800);
		f.setVisible(true);
		
		
		
		

	}

}
