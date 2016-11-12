package UserInterface;

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
		String command = e.getActionCommand();
		
		if(e.getSource() instanceof JTextField)
			jTextFieldActions(e, command);
		else if (e.getSource() instanceof JRadioButton)
			jRadioButtonActions(command);
		else if (e.getSource() instanceof JButton)
			jButtonActions(command);
	}

	/**
	 * Method responsible for JButton Action Events
	 * @param command
	 */
	private void jButtonActions(String command) {
		if(command.equals(UICommands.ShowTopology.getCommand()))
		{
			if(topology == null)
				createNetworkTopology();
			else{
				topology.getFrame().dispose();
				createNetworkTopology();
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

	/**
	 * Method responsible for JRadioButton Action Events
	 * @param command
	 */
	private void jRadioButtonActions(String command) {
		if(command.equals(UICommands.RandomAlgorithm.getCommand()))
			algorithm = new RandomAlgorithm(network);
		else if(command.equals(UICommands.FloodingAlgorithm.getCommand()))
			algorithm = new FloodingAlgorithm(network);
	}

	/**
	 * Method responsible for JTextField Action Events
	 * @param e
	 * @param command
	 */
	private void jTextFieldActions(ActionEvent e, String command) {
		JTextField texFieldtObj = (JTextField)e.getSource();
		if(command.equals(UICommands.GetNumberOfNodes.getCommand()))
		{
			String numOfNodesString = texFieldtObj.getText();
			try{
				int numOfNodesInt = Integer.parseInt(numOfNodesString);
				System.out.println(numOfNodesInt);
				network.notifyNodeNumIsAvailable(numOfNodesInt);
				if(numOfNodesInt<=0)
					texFieldtObj.setText("Must be positive integer");
			}catch(NumberFormatException err){
				texFieldtObj.setText("Must be positive integer");
			}
		}
		else if(command.equals(UICommands.GetRateNumber.getCommand()))
		{
			String rateNumString = texFieldtObj.getText();
			try{
				rateNumInt = Integer.parseInt(rateNumString);
				System.out.println(rateNumInt);
				if(rateNumInt<0)
					texFieldtObj.setText("Must be positive integer or zero");
			}catch(NumberFormatException err){
				texFieldtObj.setText("Must be positive integer or zero");
			}
		}
		else if(command.equals(UICommands.NodeNameInserted.getCommand()))
		{
			String nodeName = texFieldtObj.getText();
			if(nodeName.equals(""))
				texFieldtObj.setText("Must enter a name");
			else{
				Node n = new Node(nodeName);
				network.add(n);
			}
		}
		else if(command.equals(UICommands.FirstEdgeInserted.getCommand()))
		{
			String nodeName = texFieldtObj.getText();
			if(network.checkNodeName(nodeName)){
				Node n = network.getNode(nodeName);
				network.contains(n);
			}
			
		}
		else if(command.contentEquals(UICommands.SecondEdgeInserted.getCommand()))
		{
			String nodeName = texFieldtObj.getText();
			if(network.checkNodeName(nodeName)){
				Node n = network.getNode(nodeName);
				network.contains(n);
			}
		}
		else if(command.contentEquals(UICommands.MessageContent.getCommand()))
		{
			msgContent = texFieldtObj.getText();
			if(msgContent.equals(""))
				texFieldtObj.setText("Please enter a message");
			else if(isMessageReady()){
				msg = new Message(msgContent, source, dest);
				UI.enableRun();
			}
		}
		else if(command.contentEquals(UICommands.StartNodeEntered.getCommand()))
		{
			sourceNode = texFieldtObj.getText();
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
			destNode = texFieldtObj.getText();
			if(network.checkNodeName(destNode)){
				dest = network.getNode(destNode);
				if(isMessageReady()){
					msg = new Message(msgContent, source, dest);
					UI.enableRun();
				}
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
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		UserInterfaceController controller = new UserInterfaceController();
	}
}