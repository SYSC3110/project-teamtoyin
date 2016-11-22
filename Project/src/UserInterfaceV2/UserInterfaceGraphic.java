package UserInterfaceV2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.beans.Customizer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;



import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Algorithm.Algorithm;
import Algorithm.RandomAlgorithm;
import Graphics.MessageGraphic;
import Graphics.NodeGraphic;
import Network.Message;
import Network.Network;
import Network.Node;

/**
 * This class is to show the graphics of a topology of a given network 
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
	int yPos=10;
	private int count;
	private int setNodeCoordinatesCounter;
	private JFrame f;
	private JTable table;
	private Message m;
	private ArrayList<Node> messagePathList;
	private ArrayList<NodeGraphic> messageGraphicList;
	private static HashMap<String, List<Integer>> historyMap;
	List<Integer> orders = new ArrayList<Integer>();

	private MessageGraphic mg;
	
			

	public UserInterfaceGraphic(Network network,Message m){
		
		f = new JFrame("Network Topology");
		f.setLayout(new BorderLayout());

		f.getContentPane().add(this, BorderLayout.SOUTH);

		this.network=network;
		list = new ArrayList<Node>(network.getNodes());
		nodeGraphiclist = new ArrayList<NodeGraphic>();
		this.count=0;
		setNodeCoordinatesCounter = 0;

		mg= new MessageGraphic(getName(), 0, 0);
		messagePathList= new ArrayList<Node>();
		messageGraphicList = new ArrayList<NodeGraphic>();
		historyMap = new HashMap<String,List<Integer>>();

		this.messagePath(m);
		setHistoryhMap();
		//printhistoryMap();

		table = new JTable(this.toTableModel(historyMap));
		table.setSize(900,150);
		table.setGridColor(Color.RED);
		table.setEnabled(false);
		//this.add(table);
		f.add(this);
		f.getContentPane().add(table, BorderLayout.NORTH);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(900,900);
		f.setVisible(true);
		topologyToImg();
	}
	
	
	/**
	 * save an image of the created topology
	 */
	public void topologyToImg(){
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		this.paint(g);
		 try {
		        ImageIO.write(image, "png", new File("topology.png"));
		    } catch (IOException ex) {
		        Logger.getLogger(Customizer.class.getName()).log(Level.SEVERE, null, ex);
		   }	
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
				
				//painting the links between the drawn nodes . 
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
				

				g.setColor(Color.BLACK);
				g.drawString("Path taken: ", 0, 10);
				
				
			
		}
	

			
		
	/*
	 * This method sets the coordinates of each node in the network
	 */
	public void setNodeCoordinates(){	

		for (int i=0;i<list.size();i++){

			 ng = new NodeGraphic(list.get(i).getName(),xPos,yPos);
			this.nodeGraphiclist.add(ng);

			//placing the nodes bases on the count value 
			if(count%2==0){
				xPos+=100;
				yPos+=0;
			}else {
				yPos+=100;
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
	
	/**
	 * this method sets the locations of the nodes based on the message history 
	 * @param m
	 */
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
	
	public void setHistoryhMap(){
		for(int x=0; x<nodeGraphiclist.size();x++){
			for(int i=0; i<getMessagepath().size();i++){
				if(getMessagepath().get(i).getName().equals(nodeGraphiclist.get(x).getName()))
				{
				orders.add(i);
				historyMap.put(getMessagepath().get(i).getName(),orders );
				}
			}
			orders = new ArrayList<Integer>();
		}	
		
	}
	
	/**
	 * prints the path of a message of each node 
	 */
	public void printhistoryMap(){
		for (Entry<String, List<Integer>> entry : historyMap.entrySet()) {
		    System.out.println(entry.getKey()+" : "+entry.getValue());
		}
	}

	/**
	 * return the message history path in a table from 
	 * @param map
	 * @return
	 */
	public static TableModel toTableModel(Map map) {
	     DefaultTableModel model = new DefaultTableModel (
	   new Object[] { "Node", "Path" }, 0
	  );
	  for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
	   Map.Entry entry = (Map.Entry)it.next();
	   model.addRow(new Object[] { entry.getKey(), entry.getValue() });
	  }
	  return model;
	 }
	
	
	public static void main(String[] args) {
		Network n = new Network();
		
		Node n1 = new Node("A");
		Node n2 = new Node("B");
		Node n3 = new Node("C");
		Node n4 = new Node("D");
		Node n5 = new Node("E");
		Node n6 = new Node("F");
		Node n7 = new Node("G");
		Node n8 = new Node("H");
		Node n9 = new Node("I");
		Node n10 = new Node("J");
		Node n11 = new Node("K");
		
		
		n.add(n1);
		n.add(n2);
		n.add(n3);
		n.add(n4);
		n.add(n5);
		n.add(n6);
		n.add(n6);
		n.add(n7);
		n.add(n8);
		n.add(n9);
		n.add(n10);
		n.add(n11);
		
		n.link(n1, n2);
		n.link(n2, n3);
		n.link(n3, n4);
		n.link(n5, n2);
		n.link(n6, n4);
		n.link(n5, n6);
		//n.link(n1,n6);
		n.link(n7, n8);
		n.link(n2, n9);
		n.link(n9, n10);
		n.link(n11, n10);
		n.link(n5, n8);
		Message m = new Message("MSG1", n1, n6);
		Algorithm algo = new RandomAlgorithm(n);
		boolean value = algo.run(m, 0);
		UserInterfaceGraphic graph = new UserInterfaceGraphic(n,m);
		
		

		
		
	
	
	}

}