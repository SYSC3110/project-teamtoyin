package UserInterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;


/**
 * 
 * @author Lina El Sadek
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

	public UserInterface(UserInterfaceController UIC)
	{
		super("Team Toyin's Build-A-Network");
		this.UIC = UIC;
		setLayout(new GridLayout(3, 1));
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
		
		bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
				
		add(top);
		add(middle);
		add(bottom);
	}
	
	private void createJMenu()
	{

		//Initialize Elements
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		
		reset = new JMenuItem("Reset Topology");
		reset.setActionCommand("Reset");
		
		restore = new JMenuItem("Upload Topology");
		restore.setActionCommand("fromXML");
		
		undo = new JMenuItem("Undo");
		undo.setActionCommand("Undo");
		
		save = new JMenuItem("Save XML");
		save.setActionCommand("toXML");
		
		help = new JMenuItem("User Manual");
		help.setActionCommand("Help");
		
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
		nodeNum.setActionCommand("GetNumberOfNodes");
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
		randomAlgo.setActionCommand("RandomAlgorithm");
		randomAlgo.addActionListener(UIC);
		
		floodingAlgo = new JRadioButton("Flooding Algorithm");
		floodingAlgo.setActionCommand("FloodingAlgorithm");
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
		rateNum.setActionCommand("GetRateNumber");
		rateNum.addActionListener(UIC);
		
		PnlRate.add(lblRate);
		PnlRate.add(rateNum);
		
		top.add(PnlRate);
	}
	
	private void createNodeNameFields()
	{
		JPanel nodeTextFields = new JPanel();
		nodeTextFields.setLayout(new GridLayout(NumberOfNodes, 1));
		pnlNode = new JPanel();
		pnlNode.setLayout(new GridLayout(2, 1));
		
		lblNodeName = new JLabel("Enter Node Names");

		pnlNode.add(lblNodeName);
		
		for(int i=0; i<NumberOfNodes; i++)
		{
			nodeTextFields.add(new JTextField());
		}
		
		middle.removeAll();
		pnlNode.add(nodeTextFields);
		middle.add(pnlNode);
		middle.revalidate();
		
	}
	
	private void createNodeEdgeFields()
	{
		JPanel nodeEdgeFields = new JPanel();
		nodeEdgeFields.setLayout(new GridLayout(NumberOfNodes, 2, 2, 4));
		pnlEdge = new JPanel();
		pnlEdge.setLayout(new GridLayout(2, 1));
		
			
		lblNodeEdge = new JLabel("Enter Edge Between Nodes");
		
		pnlEdge.add(lblNodeEdge);
		
		for(int i=0; i<NumberOfNodes; i++)
		{
			nodeEdgeFields.add(new JTextField());
			nodeEdgeFields.add(new JTextField());
		}
		
		pnlEdge.add(nodeEdgeFields);
		middle.add(pnlEdge);
		middle.revalidate();
		
	}
	
	
	public static void main(String[] args)
	{
		UserInterfaceController controller = new UserInterfaceController();
	}

	public void update(Observable o, Object arg) {
		String command = "";
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
		
	}

}
