package UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;



import Algorithm.RandomAlgorithm;
import Graphics.MessageGraphic;
import Graphics.NodeGraphic;
import Network.Message;
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
	private NodeGraphic destNodeGraphic;
	private NodeGraphic ng;
	int xPos=200;
	int yPos=150;
	private int count;
	private int setNodeCoordinatesCounter;
	private JFrame f;
	
	private Message m;
	private ArrayList<Node> messagePathList;
	private ArrayList<NodeGraphic> messageGraphicList;


	private MessageGraphic mg;

	public UserInterfaceGraphic(Network network){
		f = new JFrame("nodes");
		f.add(this);
		
		this.network=network;
	    list = new ArrayList<Node>(network.getNodes());
	    nodeGraphiclist = new ArrayList<NodeGraphic>();
	    this.count=0;
	    setNodeCoordinatesCounter = 0;
	    
	    mg= new MessageGraphic(getName(), 0, 0);
	    messagePathList= new ArrayList<Node>();
	    messageGraphicList = new ArrayList<NodeGraphic>();
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
				
			
					//g.setColor(Color.RED);					
				for (int i=0;i<messageGraphicList.size();i++){
					mg.paintMessage(g, messageGraphicList.get(i).getxPosition(),messageGraphicList.get(i).getyPosition(),i);
					//mg.paintMessage(g, this.getMessagepath());
					repaint();
				}				
		}
	

					
		
	/*
	 * This method sets the coordinates of each node in the network
	 */
	public void setNodeCoordinates(){	

		for (int i=0;i<list.size();i++){

			 ng = new NodeGraphic(list.get(i).getName(),xPos,yPos);
			this.nodeGraphiclist.add(ng);

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
	
	

	public JFrame getFrame()
	{
		return f;
	}
	
	public void messagePath(Message m){
		messagePathList = m.getHistory(); 
		setNodeCoordinates();
		
		for(int i=0;i<nodeGraphiclist.size();i++){
			if(nodeGraphiclist.get(i).getName().equals(m.getDestination().getName())){
				destNodeGraphic = new NodeGraphic(nodeGraphiclist.get(i).getName(), nodeGraphiclist.get(i).getxPosition(), nodeGraphiclist.get(i).getyPosition());
				break;
			}
			}
		for(int i=0;i<messagePathList.size();i++){
			for(int j=0;j<nodeGraphiclist.size();j++){
				
				if(messagePathList.get(i).getName().equals(nodeGraphiclist.get(j).getName())){
					//System.out.print(""+nodeGraphiclist.get(j).getName()+" x:"+nodeGraphiclist.get(j).getxPosition()+" y:"+nodeGraphiclist.get(j).getyPosition()+" ");
					messageGraphicList.add(nodeGraphiclist.get(j));
				}
				
				
			}		
		}
		 
		//System.out.print(""+destNodeGraphic.getName()+" x:"+destNodeGraphic.getxPosition()+" y:"+destNodeGraphic.getyPosition()+" ");
		messageGraphicList.add(destNodeGraphic);
		
		
	}
	
	
	public ArrayList<NodeGraphic> getMessagepath(){
		return messageGraphicList;
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
		//n.link(n1,n6);
		UserInterfaceGraphic graph = new UserInterfaceGraphic(n);
		
		RandomAlgorithm algo = new RandomAlgorithm(n);

		Message m = new Message("MSG1", n1, n6);
		boolean value = algo.run(m, 0);

		graph.messagePath(m);

	}

}