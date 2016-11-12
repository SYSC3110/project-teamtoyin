package UserInterface;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Algorithm.*;
import Network.Message;
import Network.Network;
import Network.Node;

/**
 * 
 * @author Lina El Sadek
 * @date 10/25/2016
 *
 */
public class UserInterfaceController implements ActionListener{

	private Algorithm algorithm;
	private Network network;
	private UserInterface UI;
	private Message msg;
	private String msgContent, sourceNode, destNode;
	private Node source, dest;
	private UserInterfaceGraphic topology;
	private int rateNumInt;
	
	/**
	 * Constructor for UserInterfaceController class
	 */
	public UserInterfaceController(){
		network = new Network();
		UI = new UserInterface(this);
		network.addObserver(UI);
		rateNumInt = 0;
	}
	
	/**
	 * Listens to events from UserInterface.java
	 */
	public void actionPerformed(ActionEvent e) {
		String command = "";
		
		if(e.getSource() instanceof JTextField)
		{
			command = e.getActionCommand();
			if(command.equals(UICommands.GetNumberOfNodes.getCommand()))
			{
				String numOfNodesString = ((JTextField)e.getSource()).getText();
				try{
					int numOfNodesInt = Integer.parseInt(numOfNodesString);
					System.out.println(numOfNodesInt);
					network.notifyNodeNumIsAvailable(numOfNodesInt);
					if(numOfNodesInt<=0)
					{
						((JTextField)e.getSource()).setText("Must be positive integer");
					}
				}catch(NumberFormatException err){
					((JTextField)e.getSource()).setText("Must be positive integer");
				}
			}
			else if(command.equals(UICommands.GetRateNumber.getCommand()))
			{
				String rateNumString = ((JTextField)e.getSource()).getText();
				try{
					rateNumInt = Integer.parseInt(rateNumString);
					System.out.println(rateNumInt);
					if(rateNumInt<0)
					{
						((JTextField)e.getSource()).setText("Must be positive integer or zero");
					}
				}catch(NumberFormatException err){
					((JTextField)e.getSource()).setText("Must be positive integer or zero");
				}
			}
			else if(command.equals(UICommands.NodeNameInserted.getCommand()))
			{
				String nodeName = ((JTextField)e.getSource()).getText();
				if(nodeName.equals(""))
					((JTextField)e.getSource()).setText("Must enter a name");
				else{
					Node n = new Node(nodeName);
					network.add(n);
				}
			}
			else if(command.equals(UICommands.FirstEdgeInserted.getCommand()))
			{
				String nodeName = ((JTextField)e.getSource()).getText();
				if(network.checkNodeName(nodeName)){
					Node n = network.getNode(nodeName);
					network.contains(n);
				}
				
			}
			else if(command.contentEquals(UICommands.SecondEdgeInserted.getCommand()))
			{
				String nodeName = ((JTextField)e.getSource()).getText();
				if(network.checkNodeName(nodeName)){
					Node n = network.getNode(nodeName);
					network.contains(n);
				}
			}
			else if(command.contentEquals(UICommands.MessageContent.getCommand()))
			{
				msgContent = ((JTextField)e.getSource()).getText();
				if(msgContent.equals(""))
					((JTextField)e.getSource()).setText("Please enter a message");
				if(isMessageReady()){
					msg = new Message(msgContent, source, dest);
					UI.enableRun();
				}
			}
			else if(command.contentEquals(UICommands.StartNodeEntered.getCommand()))
			{
				sourceNode = ((JTextField)e.getSource()).getText();
				if(network.checkNodeName(sourceNode)){
					source = network.getNode(sourceNode);
					if(isMessageReady()){
						msg = new Message(msgContent, source, dest);
						UI.enableRun();
					}
				}
				
			}
			else if(command.contentEquals(UICommands.EndNodeEntered.getCommand()))
			{
				destNode = ((JTextField)e.getSource()).getText();
				if(network.checkNodeName(destNode)){
				dest = network.getNode(destNode);
				if(isMessageReady()){
					msg = new Message(msgContent, source, dest);
					UI.enableRun();
					}
				}
			}
		}
		else if (e.getSource() instanceof JRadioButton)
		{
			command = e.getActionCommand();
			if(command.equals(UICommands.RandomAlgorithm.getCommand()))
			{
				algorithm = new RandomAlgorithm(network);
			}
			else if(command.equals(UICommands.FloodingAlgorithm.getCommand()))
			{
				algorithm = new FloodingAlgorithm(network);
			}
		}
		else if (e.getSource() instanceof JButton)
		{
			command = e.getActionCommand();
			if(command.equals(UICommands.ShowTopology.getCommand()))
			{
				if(topology == null)
					topology = new UserInterfaceGraphic(network);
				else{

					topology.getFrame().dispose();
					topology = new UserInterfaceGraphic(network);
				}
			}
			else if(command.equals(UICommands.RunAlgorithm.getCommand()))
			{
				if(rateNumInt==0)
					algorithm.run(msg, 0);
				else
					algorithm.run(msg, rateNumInt);
			}
			
		}
	}
	
	/**
	 * Adapter for userInterface.java and Network.java 
	 * @param n1
	 * @param n2
	 */
	public void connectEdges(String n1, String n2)
	{
		Node n1_node, n2_node;
		n1_node = network.getNode(n1);
		n2_node = network.getNode(n2);
		network.link(n1_node, n2_node);
	}
	
	/**
	 * Checks all fields of a message ready before creating a message object
	 * @return
	 */
	private boolean isMessageReady()
	{
		if(!msgContent.equals("") && source != null && dest != null)
			return true;
		else
			return false;
	}
	
	/**
	 * Creates the graphical representation of the network topology
	 */
	private void createNetworkTopology()
	{
		topology = new UserInterfaceGraphic(network);
		
	}
}