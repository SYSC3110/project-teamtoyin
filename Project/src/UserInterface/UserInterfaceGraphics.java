package UserInterface;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Algorithm.Algorithm;
import Network.Network;

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
		n.add("A");
		n.add("B");
		n.add("C");
		n.add("D");
		
		n.link("A", "B");
		n.link("B", "C");
		n.link("C", "D");
		n.link("B", "D");
		
		UserInterfaceGraphics graph = new UserInterfaceGraphics(4);
		

		
		JFrame f = new JFrame("nodes");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(graph);
		f.setSize(800,800);
		f.setVisible(true);
		
		
		
		

	}

}
