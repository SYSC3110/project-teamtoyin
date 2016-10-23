package UserInterface;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * 
 * @author Lina El Sadek
 * @date 10/23/2016
 *
 */
public class UserInterface extends JFrame {
	
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
	
	private JPanel PnlNode;
	private JLabel lblNode;
	
	private JTextField nodeNum;
	private JTextField rateNum;

	public UserInterface()
	{
		super("Team Toyin's Build-A-Network");
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
		PnlNode = new JPanel();
		PnlNode.setLayout(new GridLayout(2, 1));
		
		lblNode = new JLabel("Number of Nodes");
		
		nodeNum = new JTextField();
		nodeNum.setEditable(true);
		nodeNum.setText("Number of Nodes");

		
		PnlNode.add(lblNode);
		PnlNode.add(nodeNum);
		top.add(PnlNode);
	}
	
	private void ChooseAlgorithm()
	{
		algorithmPanel = new JPanel();
		algorithmPanel.setLayout(new GridLayout(3, 1));
		
		algorithmlbl = new JLabel ("Choose an Algorithm");
		algorithmPanel.add(algorithmlbl);
		
		algorithmGroup = new ButtonGroup();
		randomAlgo = new JRadioButton("Random Algorithm");
		floodingAlgo = new JRadioButton("Flooding Algorithm");
		
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
		
		PnlRate.add(lblRate);
		PnlRate.add(rateNum);
		
		top.add(PnlRate);
	}
	
	public static void main(String[] args)
	{
		UserInterface UI = new UserInterface();
	}

}
