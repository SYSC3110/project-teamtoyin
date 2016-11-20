package UserInterfaceV2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Network.Node;

public class UserInterfaceView extends JFrame implements Observer {

	private UserInterfaceModel uim;					//User interface model
	private JTextField addNodeText;					//TextField for adding new node
	private JTextField messageContents;				//Message contents
	private JComboBox<Node> sourceNode;				//Combo box for listing nodes
	private JComboBox<Node> destinationNode;		//Combo box for listing nodes
	private JTextField messageInjectionRate;		//Message injection rate for the network
	DefaultListModel<Node> nodeListModel;			//Actual list storing the network nodes
	private JList<Node> nodeList;					//Jlist of node list for network nodes
	DefaultListModel<String> linkedNodeListModel;	//Actual list storing the network nodes linked
	private JList<Node> linkedNodeList;				//Jlist of linked node list for network nodes
	private JComboBox<Node> nodeListCombo1;			//Combo box for listing nodes
	private JComboBox<Node> nodeListCombo2;			//Combo box for listing nodes
	private JComboBox<String> algorithmListCombo;	//List of agorithms
	private ArrayList<String> algorithms = new ArrayList<String>() {{ add("RandomAlgorithm"); add("FloodingAlgorithm"); add("ShortestPathAlgorithm"); }};
	
	/**
	 * the console messages components 
	 * TODO : 
	 */
	private JPanel frameOutputManager;
	private JTextArea outputDescriptionTextArea;
	private JScrollPane outputScroll;
	
	
	/**
	 * Constructor for UserInterfaceView
	 */
	public UserInterfaceView() {
		
		//Initialize UserInterfaceModel
		this.uim = new UserInterfaceModel();
		
		//Add this as observer to model
		this.uim.addObserver(this);
		
		//Set default close action
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set default layout for our main window
		this.setLayout(new BorderLayout());
		
		//Add node manage section
		this.createNodeManager();
		
		//Add the output messages section
		this.createOutputManager();
		
		//Load algorithms into dropdown
		this.loadAlgorithms();
		
		//Size window
		this.pack();
		
		//Set window visible
		this.setVisible(true);
		
	}
	
	/**
	 * Creates the node manager section on the right of the GUI
	 */
	public void createNodeManager() {
		
		//Panel for right side (adding nodes, deleting nodes)
		JPanel frameNodeManager = new JPanel(new GridBagLayout());
		
		//Set size of this panel
		frameNodeManager.setPreferredSize(new Dimension(240, 860));
		
		//Constraints for grid bag layout
		GridBagConstraints c = new GridBagConstraints();
       
		/** 
		 * Add algorithm label
		 */
		//Label for algorithm
		JLabel selectAlgorithm = new JLabel("Select Algorithm");
		selectAlgorithm.setHorizontalAlignment(JLabel.CENTER);
		
		//Label position
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0;		
		c.insets = new Insets(13, 5, 3, 5);
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		//Add label
		frameNodeManager.add(selectAlgorithm, c);		
		
		
		/**
		 * Add combo box for algorithm list
		 */
		
		//Create algorithm list combo box
		algorithmListCombo = new JComboBox<String>();

		//Combo box position
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.insets = new Insets(3, 5, 3, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		//Add combo box
		frameNodeManager.add(algorithmListCombo, c);
		
		/**
		 * Add Separator
		 */
		//Create seperator for sections 
		JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
		
		//Separator position
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0;
		c.insets = new Insets(25, 15, 25, 15);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		//Add separator for this section and next 
		frameNodeManager.add(separator1, c);
		
		
		/** 
		 * Add Node Label 
		 */
		//Label for add node
		JLabel addNode = new JLabel("Add Node");
		
		//Label position
		c.gridx = 0;
		c.gridy = 3;
		c.weighty = 0;
		c.insets = new Insets(3, 5, 3, 5);
		c.gridwidth = 1;
		
		//Add label
		frameNodeManager.add(addNode, c);
		
		/** 
		 * Add Node TextField
		 */
		//Input area for node name
		addNodeText = new JTextField("", 10);
				
		//TextField position
		c.gridx = 1;
		c.gridy = 3;
	    c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		//Add textfield frame to window
		frameNodeManager.add(addNodeText, c);	
		
		/**
		 * Add and Delete Buttons
		 */
		//Add button for adding node
		JButton addButton = new JButton("Add");
		
		//Button position		
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.weighty = 0;
		
		//Add action listener to add button
		addButton.setActionCommand("Add Node");
		addButton.addActionListener(new UserInterfaceController(this, this.uim));
		
		//Add button to window
		frameNodeManager.add(addButton, c);	
		
		//Add button for adding node
		JButton removeButton = new JButton("Remove");
		
		//Button position		
		c.gridx = 1;
		c.gridy = 4;
		
		//Add action listener to remove button
		removeButton.setActionCommand("Remove Node");
		removeButton.addActionListener(new UserInterfaceController(this, this.uim));
		
		//Add button to window
		frameNodeManager.add(removeButton, c);	
		
		
		/** 
		 * Node list
		 */
		//List of nodes model
		nodeListModel = new DefaultListModel<Node>();
		
		//List of nodes list
		nodeList = new JList(nodeListModel);
		
		//Number of nodes to show in list at once
		nodeList.setVisibleRowCount(8);
		
		//List position
		c.gridx = 0;
		c.gridy = 5;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		//List scrolling
		JScrollPane nodeListPane = new JScrollPane(nodeList);
		
		//Add list to our node manager panel
		frameNodeManager.add(nodeListPane, c);	
		
		
		/**
		 * Add Separator
		 */
		//Create seperator for sections 
		JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
		
		//Separator position
		c.gridx = 0;
		c.gridy = 6;
		c.weighty = 0;
		c.insets = new Insets(25, 15, 25, 15);
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		//Add seperator for this section and next 
		frameNodeManager.add(separator2, c);
		
		
		/** 
		 * Add Link Node Label 
		 */
		//Label for add node
		JLabel linkNodes = new JLabel("Link Nodes");
		linkNodes.setHorizontalAlignment(JLabel.CENTER);
		
		//Label position
		c.gridx = 0;
		c.gridy = 7;
		c.weighty = 0;
		c.weightx = 1;
		c.insets = new Insets(3, 5, 3, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		//Add label
		frameNodeManager.add(linkNodes, c);
		
		
		/**
		 * Add combo box for node list
		 */
		
		//Create node list combo box
		nodeListCombo1 = new JComboBox<Node>();

		//Combo box position
		c.gridx = 0;
		c.gridy = 8;
		c.weightx = 0;
		c.insets = new Insets(3, 5, 3, 5);
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		
		//Add combo box
		frameNodeManager.add(nodeListCombo1, c);
		
		
		/**
		 * Add combo box for node list
		 */		
		//Create node list combo box
		nodeListCombo2 = new JComboBox<Node>();

		//Combo box position
		c.gridx = 1;
		c.gridy = 8;
		c.weightx = 0;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		
		//Add combo box
		frameNodeManager.add(nodeListCombo2, c);
		
		/**
		 * Link and Unlink Buttons
		 */
		//Add button for adding node
		JButton linkButton = new JButton("Link");
		
		//Button position		
		c.gridx = 0;
		c.gridy = 9;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.weighty = 0;
		
		//Add action listener to add button
		linkButton.setActionCommand("Link Nodes");
		linkButton.addActionListener(new UserInterfaceController(this, this.uim));
		
		//Add button to window
		frameNodeManager.add(linkButton, c);	
		
		/**
		 * Link and Unlink Buttons
		 */
		//Add button for adding node
		JButton unlinkButton = new JButton("Unlink");
		
		//Button position		
		c.gridx = 1;
		c.gridy = 9;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.weighty = 0;
		
		//Add action listener to add button
		unlinkButton.setActionCommand("Unlink Nodes");
		unlinkButton.addActionListener(new UserInterfaceController(this, this.uim));
		
		//Add button to window
		frameNodeManager.add(unlinkButton, c);
		
		/** 
		 * Linked list
		 */
		//List of node links model
		linkedNodeListModel = new DefaultListModel<String>();
		
		//List of node links list
		linkedNodeList = new JList(linkedNodeListModel);
		
		//Number of node links to show in list at once
		linkedNodeList.setVisibleRowCount(8);
		
		//List position
		c.gridx = 0;
		c.gridy = 10;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = GridBagConstraints.REMAINDER;

		//List scrolling
		JScrollPane linkedNodeListPane = new JScrollPane(linkedNodeList);
		
		//Add list to our node manager panel
		frameNodeManager.add(linkedNodeListPane, c);	
		
		
		/**
		 * Add Separator
		 */
		//Create separator for sections 
		JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
		
		//Separator position
		c.gridx = 0;
		c.gridy = 11;
		c.weighty = 0;
		c.insets = new Insets(25, 15, 25, 15);
	    c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		//Add seperator for this section and next 
		frameNodeManager.add(separator3, c);
		
		
		
		/** 
		 * Add Message Contents Label 
		 */
		//Label for add node
		JLabel messageContentsLabel = new JLabel("Message Contents");
		
		//Label position
		c.gridx = 0;
		c.gridy = 12;
		c.weighty = 0;
		c.insets = new Insets(3, 5, 3, 5);
		c.gridwidth = 1;
		
		//Add label
		frameNodeManager.add(messageContentsLabel, c);
		
		
		/** 
		 * Add Message Contents TextField
		 */
		//Input area for node name
		messageContents = new JTextField("", 10);
				
		//TextField position
		c.gridx = 1;
		c.gridy = 12;
	    c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		//Add textfield frame to window
		frameNodeManager.add(messageContents, c);	
		
		
		/** 
		 * Add Source Node Label
		 */
		//Label for add node
		JLabel sourceNodeLabel = new JLabel("Source Node");
		
		//Label position
		c.gridx = 0;
		c.gridy = 13;
		c.weighty = 0;
		c.insets = new Insets(3, 5, 3, 5);
		c.gridwidth = 1;
		
		//Add label
		frameNodeManager.add(sourceNodeLabel, c);
		
		
		/**
		 * Add combo box for message source node
		 */
		
		//Create node list combo box
		sourceNode = new JComboBox<Node>();

		//Combo box position
		c.gridx = 1;
		c.gridy = 13;
		c.weightx = 0;
		c.insets = new Insets(3, 5, 3, 5);
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		
		//Add combo box
		frameNodeManager.add(sourceNode, c);
		
		
		/** 
		 * Add Injections Label 
		 */
		//Label for add node
		JLabel destinationNodeLabel = new JLabel("Dest Node");
		
		//Label position
		c.gridx = 0;
		c.gridy = 14;
		c.weighty = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3, 5, 3, 5);
		c.gridwidth = 1;
		
		//Add label
		frameNodeManager.add(destinationNodeLabel, c);
		
		
		/**
		 * Add combo box for message destination node
		 */		
		//Create node list combo box
		destinationNode = new JComboBox<Node>();

		//Combo box position
		c.gridx = 1;
		c.gridy = 14;
		c.weightx = 0;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		
		//Add combo box
		frameNodeManager.add(destinationNode, c);
		
		
		/** 
		 * Add Injections Label 
		 */
		//Label for add node
		JLabel addInjection = new JLabel("Injection Rate");
		
		//Label position
		c.gridx = 0;
		c.gridy = 15;
		c.weighty = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3, 5, 3, 5);
		c.gridwidth = 1;
		
		//Add label
		frameNodeManager.add(addInjection, c);
		
		/** 
		 * Add Message Injection TextField
		 */
		//Input area for node name
		messageInjectionRate = new JTextField("", 10);
				
		//TextField position
		c.gridx = 1;
		c.gridy = 15;
	    c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		//Add textfield frame to window
		frameNodeManager.add(messageInjectionRate, c);	
		
		
		/**
		 * Add separator
		 */
		//Create separator for sections 
		JSeparator separator4 = new JSeparator(SwingConstants.HORIZONTAL);
		
		c.gridx = 0;
		c.gridy = 16;
		c.weighty = 0;
		c.insets = new Insets(25, 15, 25, 15);
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		//Add seperator for this section and next 
		frameNodeManager.add(separator4, c);
		
		
		/**
		 * Start Simulation Button
		 */
		//Add button for adding node
		JButton startSimulation = new JButton("Start Simulation");
		
		//Button position		
		c.gridx = 0;
		c.gridy = 17;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(18, 5, 3, 5);

		
		//Add action listener to add button
		startSimulation.setActionCommand("Start Simulation");
		startSimulation.addActionListener(new UserInterfaceController(this, this.uim));
		
		//Add button to window
		frameNodeManager.add(startSimulation, c);
		
		/**
		 * Add frame to window
		 */
		//Add node manager frame to window
		this.add(frameNodeManager, BorderLayout.EAST);
		
	}
	
	/**
	 * Creates the output messages section on the left of the GUI
	 * TODO 
	 */
	
	public void createOutputManager(){
		
		frameOutputManager= new JPanel(new BorderLayout());
		//Set size of this panel
		frameOutputManager.setPreferredSize(new Dimension(900, 200));
		
		 outputDescriptionTextArea = new JTextArea("\t\t\t\t====Topology Information====\t\t");
		 outputDescriptionTextArea.setEditable(false);

		 outputScroll = new JScrollPane(outputDescriptionTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		 frameOutputManager.add(outputScroll);
		 this.add(frameOutputManager, BorderLayout.WEST);
	}
	
	/**
	 * Loads the algorithms into dropdown
	 */
	public void loadAlgorithms() {
		
		//For each algorithm
		for (String s : algorithms) {
			
			//Add item to combo
			algorithmListCombo.addItem(s);
			
		}
	}
	
	/**
	 * Returns the selected combo box items for linking nodes 
	 */
	public String getSelectedAlgorithm() {
		
		//Get algorithm list selected item
		return this.algorithmListCombo.getSelectedItem().toString();
		
	}
	
	/**
	 * Returns the value currently in add node text field.
	 */
	public String getAddNodeTextField() {
		
		//Return the value entered in the add node text field.
		return this.addNodeText.getText();
	}
	
	/**
	 * Returns the value currently selected in our list of nodes.
	 */
	public Node getSelectedNodeListItem() {
		
		//Return the selected item in our node list
		return (Node) this.nodeList.getSelectedValue();

	}
	
	/**
	 * Returns the selected combo box items for linking nodes 
	 */
	public Node getSelectedLinkNodeCombo1() {
		
		//Get combo 1 selected item
		return (Node) this.nodeListCombo1.getSelectedItem();
		
	}

	/**
	 * Returns the selected combo box items for linking nodes 
	 */
	public Node getSelectedLinkNodeCombo2() {
		
		//Get combo 2 selected item
		return (Node) this.nodeListCombo2.getSelectedItem();
		
	}
	
	/**
	 * Returns the number of injections to do during simulation
	 */
	public int getMessageInjectionRate() {
		
		//Start of Try/Catch
		try {
			
			//Return value
			return Integer.parseInt(this.messageInjectionRate.getText());
			
		} catch (Exception ex) {
			
			//Return 0 if anything fails
			return 0;
			
		}
		
	}
	
	
	/**
	 * Return the message contents from the view
	 */
	public String getMessageContents() {
		
		//Return message contents
		return this.messageContents.getText();
	}
	
	/**
	 * Return the message source node from the view
	 */
	public Node getMessageSourceNode() {
		
		//Return message contents
		return (Node) this.sourceNode.getSelectedItem();
		
	}
	
	/**
	 * Return the message destination node from the view
	 */
	public Node getMessageDestinationNode() {
		
		//Return message contents
		return (Node) this.destinationNode.getSelectedItem();
		
	}
	
	/**
	 * When the model has updated
	 */
	public void update(Observable o, Object arg) {
		
		//Cast incoming object to user interface event
		UserInterfaceEvent e = (UserInterfaceEvent) arg;
		
		//Check if event type is new nodes added or removed
		if (e.getType() == "Node Update") {
			
			//Update the node manager section
			updateNodeManager(e);
		
		}
		
		//Check if event type is new links created, if nodes removes then links removed too 
		if (e.getType() == "Node Link Update" || e.getType() == "Node Update") {
			
			//Update the link nodes manager section
			updateLinkManager(e);
			
		}
		
		//Check if simulation message is incoming
		if (e.getType() == "Simulation") {
			
			//If simulation ran successfully
			if (e.getSuccess()) {
				
				//create array to split the passed string 
				String[] str = e.getMessage().split(":");
				String[] str1 = str[0].split("$");
				for(String s: str1){
					outputDescriptionTextArea.setText(s);
				}
				
				JOptionPane.showMessageDialog(null, str[1]);
				//Show success message
				//JOptionPane.showMessageDialog(null, e.getMessage());

			//Simulation failed for some reason
			} else {
				
				//If a message is present
				if (e.getMessage() != "") {
					
					//Show message
					JOptionPane.showMessageDialog(null, e.getMessage());
					
				}
			}
		}
		
	}
	
	/**
	 * Update the section to manage nodes
	 */
	public void updateNodeManager(UserInterfaceEvent e) {
		
		//Check if  success
		if (e.getSuccess()) {
		
			//Clear existing network node lists
			this.nodeListModel.clear();
			this.nodeListCombo1.removeAllItems();
			this.nodeListCombo2.removeAllItems();
			this.sourceNode.removeAllItems();
			this.destinationNode.removeAllItems();
			
			//Loop each node in network to add to our lists
			for (Node n : e.getNetwork().getNodes()) {
				
				//Add to list
				this.nodeListModel.addElement(n);
				
				//Add node to node list combobox1
				this.nodeListCombo1.addItem(n);
				
				//Add node to node list combobox2
				this.nodeListCombo2.addItem(n);
				
				//Add node to node list source nodes
				this.sourceNode.addItem(n);
				
				//Add node to node list destination nodes
				this.destinationNode.addItem(n);
				
			}
	
		//Not successful
		} else {
			
			//Check if error message set
			if (e.getMessage() != "") {
				
				//Show message
				JOptionPane.showMessageDialog(null, e.getMessage());
				
			}
		}
		
	}
	
	/**
	 * Update the section to link nodes together
	 */
	public void updateLinkManager(UserInterfaceEvent e) {
		ArrayList<Node> displayed = new ArrayList<Node>(); //List of main nodes we have displayed link for 
		
		//Check if success
		if (e.getSuccess()) {
				
			this.linkedNodeListModel.clear();
			
			//Loop each node in network to add to our lists
			for (Node n1 : e.getNetwork().getNodes()) {
				
				//Add node to list of nodes displayed
				displayed.add(n1);
				
				//For each of this nodes neighbors
				for (Node n2 : n1.getNeighbors()) {
					
					//Check if node link already displayed
					if (!displayed.contains(n2)) {
						
						//Display the link
						this.linkedNodeListModel.addElement(n1.toString() + " - " + n2.toString() );
					
					}
						
				}
				
			}
		
		//Not successful
		} else {
			
			//Check if error message set
			if (e.getMessage() != "") {
				
				//Show message
				JOptionPane.showMessageDialog(null, e.getMessage());
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserInterfaceView view = new UserInterfaceView();
		
	}


}
