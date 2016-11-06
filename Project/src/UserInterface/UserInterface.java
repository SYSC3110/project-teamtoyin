package UserInterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import Network.Node;
import UserInterface.UICommands;
import Network.NetworkCommands;

/**
 * 
 * @author Lina El Sadek
 * Modified Toyin Odujebe
 * @date 10/23/2016
 *
 */
public class UserInterface extends JFrame implements Observer{
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem reset;
	private JMenuItem restore;
	private JMenuItem undo;
	private JMenuItem save;
	private JMenuItem help;
	
	private JPanel top;
	private JPanel middle;
	private JPanel middle2;
	private JPanel bottom;
	private JPanel algorithmPanel;
	
	private JLabel algorithmlbl;
	private JRadioButton randomAlgo;
	private JRadioButton floodingAlgo;
	private ButtonGroup algorithmGroup;
	
	private JLabel lblRate;
	private JPanel PnlRate;
	
	private JPanel PnlNodeNum;
	private JLabel lblNode;
	
	private JTextField nodeNum;
	private JTextField rateNum;
	
	private JPanel pnlNode;
	private JLabel lblNodeName;
	
	private JPanel pnlEdge;
	private JLabel lblNodeEdge;
	
	private int NumberOfNodes;
	
	private UserInterfaceController UIC;
	
	private JOptionPane errorDialog;
	private ArrayList<JTextField> allEdgetextFields;
	private JTextField messageContent, startNode, endNode;
	private boolean createMessageFlag = true;

	public UserInterface(UserInterfaceController UIC)
	{
		super("Team Toyin's Build-A-Network");
		this.UIC = UIC;
		setLayout(new GridLayout(4, 1));
		CreatePanels();
		createNodeNum();
		ChooseAlgorithm();
		createRate();
		createJMenu();
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void CreatePanels()
	{
		top = new JPanel();
		top.setLayout(new GridLayout(1, 3));
		
		middle = new JPanel();
		middle.setLayout(new GridLayout(1, 2));
		
		middle2 = new JPanel();
		middle2.setLayout(new GridLayout(1, 3));
		
		bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
				
		add(top);
		add(middle);
		add(middle2);
		add(bottom);
	}
	
	private void createJMenu()
	{

		//Initialize Elements
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		
		reset = new JMenuItem("Reset Topology");
		reset.setActionCommand(UICommands.Reset.getCommand());
		
		restore = new JMenuItem("Upload Topology");
		restore.setActionCommand(UICommands.fromXML.getCommand());
		
		undo = new JMenuItem("Undo");
		undo.setActionCommand(UICommands.Undo.getCommand());
		
		save = new JMenuItem("Save XML");
		save.setActionCommand(UICommands.toXML.getCommand());
		
		help = new JMenuItem("User Manual");
		help.setActionCommand(UICommands.Help.getCommand());
		
		//Add Menu Items to Menu
		menu.add(reset);
		menu.add(restore);
		menu.add(undo);
		menu.add(save);
		menu.add(help);
		
		//Add Menu to Menu Bar
		menuBar.add(menu);
		
		//add Menu Bar to Frame
		setJMenuBar(menuBar);	
	}
	
	private void createNodeNum()
	{
		PnlNodeNum = new JPanel();
		PnlNodeNum.setLayout(new GridLayout(2, 1));
		
		lblNode = new JLabel("Number of Nodes");
		
		nodeNum = new JTextField();
		nodeNum.setEditable(true);
		nodeNum.setText("Number of Nodes");
		nodeNum.setActionCommand(UICommands.GetNumberOfNodes.getCommand());
		nodeNum.addActionListener(UIC);

		
		PnlNodeNum.add(lblNode);
		PnlNodeNum.add(nodeNum);
		top.add(PnlNodeNum);
	}
	
	private void ChooseAlgorithm()
	{
		algorithmPanel = new JPanel();
		algorithmPanel.setLayout(new GridLayout(3, 1));
		
		algorithmlbl = new JLabel ("Choose an Algorithm");
		algorithmPanel.add(algorithmlbl);
		
		algorithmGroup = new ButtonGroup();
		randomAlgo = new JRadioButton("Random Algorithm");
		randomAlgo.setActionCommand(UICommands.RandomAlgorithm.getCommand());
		randomAlgo.addActionListener(UIC);
		
		floodingAlgo = new JRadioButton("Flooding Algorithm");
		floodingAlgo.setActionCommand(UICommands.FloodingAlgorithm.getCommand());
		floodingAlgo.addActionListener(UIC);
		
		algorithmGroup.add(randomAlgo);
		algorithmGroup.add(floodingAlgo);
		
		algorithmPanel.add(randomAlgo);
		algorithmPanel.add(floodingAlgo);
		
		
		top.add(algorithmPanel);
		
	}
	
	private void createRate()
	{
		PnlRate = new JPanel();
		PnlRate.setLayout(new GridLayout(2, 1));
		
		lblRate = new JLabel("Rate");
		
		rateNum = new JTextField();
		rateNum.setEditable(true);
		rateNum.setActionCommand(UICommands.GetRateNumber.getCommand());
		rateNum.addActionListener(UIC);
		
		PnlRate.add(lblRate);
		PnlRate.add(rateNum);
		
		top.add(PnlRate);
	}
	
	private void createNodeNameFields()
	{
		JPanel nodeTextFields = new JPanel();
		JTextField nodeNameTextField;
		nodeTextFields.setLayout(new GridLayout(NumberOfNodes, 1));
		pnlNode = new JPanel();
		pnlNode.setLayout(new GridLayout(2, 1));
		
		lblNodeName = new JLabel("Enter Node Names");

		pnlNode.add(lblNodeName);
		
		for(int i=0; i<NumberOfNodes; i++)
		{
			nodeNameTextField = new JTextField();
			nodeNameTextField.setActionCommand(UICommands.NodeNameInserted.getCommand());
			nodeNameTextField.addActionListener(UIC);
			nodeTextFields.add(nodeNameTextField);
		}
		
		middle.removeAll();
		pnlNode.add(nodeTextFields);
		middle.add(pnlNode);
		middle.revalidate();
		
	}
	
	private void createNodeEdgeFields()
	{
		JPanel nodeEdgeFields = new JPanel();
		JTextField edgeTextField ;
		nodeEdgeFields.setLayout(new GridLayout(NumberOfNodes, 2, 2, 4));
		pnlEdge = new JPanel();
		pnlEdge.setLayout(new GridLayout(2, 1));
		allEdgetextFields = new ArrayList<JTextField>();
		
			
		lblNodeEdge = new JLabel("Enter Edge Between Nodes");
		
		pnlEdge.add(lblNodeEdge);
		
		for(int i=0; i<NumberOfNodes; i++)
		{
			
			edgeTextField = new JTextField();
			edgeTextField.setActionCommand(UICommands.FirstEdgeInserted.getCommand());
			edgeTextField.addActionListener(UIC);
			if(i == 0)
				edgeTextField.setEditable(true);
			else
				edgeTextField.setEditable(false);
			
			nodeEdgeFields.add(edgeTextField);
			allEdgetextFields.add(edgeTextField);
			
			edgeTextField = new JTextField();
			edgeTextField.setActionCommand(UICommands.SecondEdgeInserted.getCommand());
			edgeTextField.addActionListener(UIC);
			edgeTextField.setEditable(false);
			allEdgetextFields.add(edgeTextField);
			nodeEdgeFields.add(edgeTextField);
			
			
		}
		
		pnlEdge.add(nodeEdgeFields);
		middle.add(pnlEdge);
		middle.revalidate();		
	}
	
	private void createMessageFields()
	{
		messageContent = new JTextField("Message Content");
		messageContent.setEditable(true);
		messageContent.setActionCommand(UICommands.MessageContent.getCommand());
		messageContent.addActionListener(UIC);
		
		startNode = new JTextField("Start Node");
		startNode.setEditable(true);
		startNode.setActionCommand(UICommands.StartNodeEntered.getCommand());
		startNode.addActionListener(UIC);;
		
		endNode = new JTextField("End Node");
		endNode.setEditable(true);
		endNode.setActionCommand(UICommands.EndNodeEntered.getCommand());
		endNode.addActionListener(UIC);
		
		middle2.add(messageContent);
		middle2.add(startNode);
		middle2.add(endNode);
		
		middle2.revalidate();
	}

	
	private void createJOptionPane(String header, String errorMsg, String dialogType)
	{
		errorDialog = new JOptionPane();
		if(dialogType.equals("Warning"))
		{
			JOptionPane.showMessageDialog(this,errorMsg,
			    header, JOptionPane.WARNING_MESSAGE);
		}
		else if(dialogType.equals("Error"))
		{
			JOptionPane.showMessageDialog(this,errorMsg,
				    header, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void update(Observable o, Object arg) {
		String command = "";
		String n1;
		String n2;
		
		if(arg instanceof String)
		{
			command = (String)arg;
			if(command.contains("NodeNumAvailable"))
			{
				String[] getNodeNum = new String[2];
				getNodeNum = command.split(":");
				try
				{
					NumberOfNodes = Integer.parseInt(getNodeNum[1]);
					createNodeNameFields();
					createNodeEdgeFields();
				}
				catch(NumberFormatException err)
				{
					System.out.println("Oops, cannot convert to integer");
				}
				catch(NullPointerException err)
				{
					System.out.println("Number of Nodes was not provided");
				}	
			}
		}
		else if(arg instanceof NetworkCommands)
		{
			command = ((NetworkCommands)arg).getCommand();
			if(command.equals(NetworkCommands.InvalidNodeName.getCommand()))
			{
				createJOptionPane("Error", "You have entered an invalid name to the node.", "Error");
			}
			else if(command.equals(NetworkCommands.NodeExists.getCommand()))
			{
				createJOptionPane("Warning!", "The node name you entered already exists.", "Warning");
			}
			else if(command.equals(NetworkCommands.NodeEmpty.getCommand()))
			{
				createJOptionPane("Warning!", "You have left one of the edges empty.", "Warning");
			}
			else if(command.equals(NetworkCommands.NodeDoesNotExist.getCommand()))
			{
				createJOptionPane("Error", "The node you entered does not exist.", "Error");
			}
			else if(command.equals(NetworkCommands.SetEditableNextNode.getCommand()))
			{
				
				for(int i=0; i<allEdgetextFields.size(); i++)
				{
					if(i%2==0 && i>=2)//if it's an odd and greater than 2, get the last two nodes
					{
						if(allEdgetextFields.get(i-2).isEditable() && allEdgetextFields.get(i-1).isEditable()) {
							n1 = allEdgetextFields.get(i-2).getText();
							n2 = allEdgetextFields.get(i-1).getText();
							
							UIC.connectEdges(n1, n2);
						}
					}
					
					if(!allEdgetextFields.get(i).isEditable())
					{
						allEdgetextFields.get(i).setEditable(true);						
						break;
					}
					
					//Special case when it's the last one
					if(i == allEdgetextFields.size()-1)
					{
						n1 = allEdgetextFields.get(i-1).getText();
						n2 = allEdgetextFields.get(i).getText();
						
						UIC.connectEdges(n1, n2);
					}
				}
				if(createMessageFlag)
				{
					createMessageFields();
					createMessageFlag = false;
				}
			}
			else if(command.equals(NetworkCommands.CreateMessageFields.getCommand()))
			{
				createMessageFields();
			}
		}	
	}
	
	public static void main(String[] args)
	{
		UserInterfaceController controller = new UserInterfaceController();
	}
}
