package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Algorithm.*;
import Network.Network;

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
			if(command.equals("GetNumberOfNodes"))
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
			else if(command.equals("GetRateNumber"))
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
			else if(command.equals("NodeNameInserted"))
			{
				String nodeName = ((JTextField)e.getSource()).getText();
				network.add(nodeName);
			}
			else if(command.equals("FirstEdgeInserted"))
			{
				String nodeName = ((JTextField)e.getSource()).getText();
				network.contains(nodeName);
			}
			else if(command.contentEquals("SecondEdgeInserted"))
			{
				String nodeName = ((JTextField)e.getSource()).getText();
				network.contains(nodeName);
			}
		}
		else if (e.getSource() instanceof JRadioButton)
		{
			command = e.getActionCommand();
			if(command.equals("RandomAlgorithm"))
			{
				algorithm = new RandomAlgorithm(network);
			}
			else if(command.equals("FloodingAlgorithm"))
			{
				algorithm = new FloodingAlgorithm(network);
			}
		}
		
	}
	
	public void connectEdges(String n1, String n2)
	{
		network.link(n1, n2);
	}
}