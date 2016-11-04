package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Algorithm.*;
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
	
	public UserInterfaceController(){
		network = new Network();
		UI = new UserInterface(this);
		network.addObserver(UI);
	}
	public void actionPerformed(ActionEvent e) {
		String command = "";
		
		if(e.getSource() instanceof JTextField)
		{
			command = e.getActionCommand();
			if(command.equals(UICCommands.GetNumberOfNodes.getCommand()))
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
			else if(command.equals(UICCommands.GetRateNumber.getCommand()))
			{
				String rateNumString = ((JTextField)e.getSource()).getText();
				try{
					int rateNumInt = Integer.parseInt(rateNumString);
					System.out.println(rateNumInt);
					if(rateNumInt<0)
					{
						((JTextField)e.getSource()).setText("Must be positive integer or zero");
					}
				}catch(NumberFormatException err){
					((JTextField)e.getSource()).setText("Must be positive integer or zero");
				}
			}
			else if(command.equals(UICCommands.NodeNameInserted.getCommand()))
			{
				String nodeName = ((JTextField)e.getSource()).getText();
				Node n = new Node(nodeName);
				network.add(n);
			}
			else if(command.equals(UICCommands.FirstEdgeInserted.getCommand()))
			{
				String nodeName = ((JTextField)e.getSource()).getText();
				Node n = new Node(nodeName);
				network.contains(n);
			}
			else if(command.contentEquals(UICCommands.SecondEdgeInserted.getCommand()))
			{
				String nodeName = ((JTextField)e.getSource()).getText();
				Node n = new Node(nodeName);
				network.contains(n);
			}
		}
		else if (e.getSource() instanceof JRadioButton)
		{
			command = e.getActionCommand();
			if(command.equals(UICCommands.RandomAlgorithm.getCommand()))
			{
				algorithm = new RandomAlgorithm(network);
			}
			else if(command.equals(UICCommands.FloodingAlgorithm.getCommand()))
			{
				algorithm = new FloodingAlgorithm(network);
			}
		}
		
	}
	
	public void connectEdges(Node n1, Node n2)
	{
		network.link(n1, n2);
	}
}