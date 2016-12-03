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

import Graphics.MessageGraphic;
import Graphics.NodeGraphic;
import Network.Message;
import Network.Network;
import Network.Node;

/**
 * This class is to show the graphics of a topology of a given network 
 * @author Osama Buhamad
 * Modified By: Lina El Sadek
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
	private JTable table;
	private ArrayList<Node> messagePathList;
	private ArrayList<NodeGraphic> messageGraphicList;
	private static HashMap<String, List<Integer>> historyMap;
	List<Integer> orders = new ArrayList<Integer>();
	private MessageGraphic mg;
	private int counter=0;

	
	public UserInterfaceGraphic(Network network, Message m, JFrame f) {

		super(new BorderLayout());
		this.network = network;
		list = new ArrayList<Node>(network.getNodes());
		nodeGraphiclist = new ArrayList<NodeGraphic>();
		this.count = 0;
		setNodeCoordinatesCounter = 0;

		mg = new MessageGraphic(getName(), 0, 0);
		messagePathList = new ArrayList<Node>();
		messageGraphicList = new ArrayList<NodeGraphic>();
		historyMap = new HashMap<String, List<Integer>>();

		this.messagePath(m);
		setHistoryhMap();
		 //printhistoryMap();

		table = new JTable(this.toTableModel(historyMap));
		table.setSize(900, 150);
		table.setGridColor(Color.RED);
		table.setEnabled(false);
		this.add(table, BorderLayout.SOUTH);
		f.add(this, BorderLayout.CENTER);
	}

	/**
	 * save an image of the created topology
	 */
	public void topologyToImg() {
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		this.paint(g);
		try {
			ImageIO.write(image, "png", new File("topology.png"));
		} catch (IOException ex) {
			Logger.getLogger(Customizer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);

		// calling this method to set the coordinates of the nodes before
		// painting them
		if (setNodeCoordinatesCounter == 0)
			setNodeCoordinates();

		for (int i = 0; i < nodeGraphiclist.size(); i++) {
			nodeGraphiclist.get(i).paintNode(g);
		}

		// painting the links between the drawn nodes
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i; j < list.size(); j++) {
				if (list.get(i).hasNeighbor(list.get(j))) {
					int x = nodeGraphiclist.get(j).getxPosition();
					int y = nodeGraphiclist.get(j).getyPosition();
					nodeGraphiclist.get(i).paintLink(g, x, y);
				}
			}
		}

	
		/**
		 * TODO 
		 * HERE 
		 * need to update these value when stepping forward or backward
		 * 
		 * 
		 */
		
		//if (setNodeCoordinatesCounter != 0){
			int x = messageGraphicList.get(messagePathList.size()-1).getxPosition();
			int y = messageGraphicList.get(messagePathList.size()-1).getyPosition();
			mg=new MessageGraphic(messageGraphicList.get(messagePathList.size()-1).getName(), x, y);
			mg.paintMessage(g, x, y);		
			
		//}
		
		
	}

	/*
	 * This method sets the coordinates of each node in the network
	 */
	public void setNodeCoordinates() {

		for (int i = 0; i < list.size(); i++) {

			ng = new NodeGraphic(list.get(i).getName(), xPos, yPos);
			this.nodeGraphiclist.add(ng);

			// placing the nodes bases on the count value
			if (count % 2 == 0) {
				xPos += 100;
				yPos += 0;
			} else {
				yPos += 100;
				xPos += 0;
			}
			count++;
		}
		setNodeCoordinatesCounter++;
	}


	/**
	 * this method sets the locations of the nodes based on the message history
	 * 
	 * @param m
	 */
	public void messagePath(Message m) {
		messagePathList = m.getHistory();
		setNodeCoordinates();

		for (int i = 0; i < nodeGraphiclist.size(); i++) {
			if (nodeGraphiclist.get(i).getName().equals(m.getDestination().getName())) {
				destNodeGraphic = new NodeGraphic(nodeGraphiclist.get(i).getName(),
						nodeGraphiclist.get(i).getxPosition(), nodeGraphiclist.get(i).getyPosition());
				break;
			}
		}
		for (int i = 0; i < messagePathList.size(); i++) {
			for (int j = 0; j < nodeGraphiclist.size(); j++) {
				if (messagePathList.get(i).getName().equals(nodeGraphiclist.get(j).getName())) {
					// System.out.print(""+nodeGraphiclist.get(j).getName()+"
					// x:"+nodeGraphiclist.get(j).getxPosition()+"
					// y:"+nodeGraphiclist.get(j).getyPosition()+" ");
					messageGraphicList.add(nodeGraphiclist.get(j));
				}
			}
		}

		// System.out.print(""+destNodeGraphic.getName()+"
		// x:"+destNodeGraphic.getxPosition()+"
		// y:"+destNodeGraphic.getyPosition()+" ");
		messageGraphicList.add(destNodeGraphic);

	}

	public ArrayList<NodeGraphic> getMessagepath() {
		return messageGraphicList;
	}

	public void setHistoryhMap() {
		for (int x = 0; x < nodeGraphiclist.size(); x++) {
			for (int i = 0; i < getMessagepath().size(); i++) {
				if (getMessagepath().get(i).getName().equals(nodeGraphiclist.get(x).getName())) {
					orders.add(i);
					historyMap.put(getMessagepath().get(i).getName(), orders);
				}
			}
			orders = new ArrayList<Integer>();
		}

	}

	/**
	 * prints the path of a message of each node
	 */
	public void printhistoryMap() {
		for (Entry<String, List<Integer>> entry : historyMap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

	/**
	 * return the message history path in a table from
	 * 
	 * @param map
	 * @return
	 */
	public static TableModel toTableModel(Map map) {
		DefaultTableModel model = new DefaultTableModel(new Object[] { "Node", "Path" }, 0);
		for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			model.addRow(new Object[] { entry.getKey(), entry.getValue() });
		}
		return model;
	}
	
	/**
	 * method to call print message location--- not called for now maybe not going to be needed  
	 */
	private void callPrintMessage(){
		int x = nodeGraphiclist.get(messagePathList.size()-1).getxPosition();
		int y = nodeGraphiclist.get(messagePathList.size()-1).getyPosition();
		mg=new MessageGraphic(nodeGraphiclist.get(messagePathList.size()-1).getName(), x, y);
		//mg.paintMessage(g, x, y);
	}
	
	

}