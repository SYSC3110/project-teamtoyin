package UserInterfaceV2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import Network.*;

/**
 * Author: Richard Hanton
 *
 */
public class UserInterfaceController implements ActionListener {

	private UserInterfaceModel uim;	//User interface model 
	private UserInterfaceView uiv;	//User interface view 
	
	public UserInterfaceController(UserInterfaceView uiv, UserInterfaceModel uim) {
		
		//Assign user interface model to controller
		this.uim = uim;
		
		//Assign user interface view to controller
		this.uiv = uiv;
		
	}
	
	public void actionPerformed(ActionEvent e) {
	
		//Get action command from view
		String command = e.getActionCommand();
		
		//If add node clicked
		if (command == "Add Node") {
			
			//Call add node method
			this.addNodeCommand();
			
		//If remove node clicked
		} else if (command == "Remove Node") {
			
			//Call remove node method
			removeNodeCommand();
			
		//If link nodes is clicked
		} else if (command == "Link Nodes") {
		
			//Call link nodes method
			linkNodesCommand();
			
		//If unlink nodes clicked
		} else if (command == "Unlink Nodes") {
			
			//Call the unlink nodes method
			unlinkNodesCommand();
			
		//If start simulation is clicked
		} else if (command == "Start Simulation") {
			
			//Call the start simulation method
			startSimulationCommand();
		
		//If initialize stepping is clicked
		} else if (command == "Initialize Stepping") {
			
			//Call the initialize stepping command
			initializeSteppingCommand();
		
		//If step forwards is clicked
		} else if (command == "Step Forwards") {
		
			//Call step forwards command
			stepForwardsCommand();
			
		//If step backwards is clicked
		}  else if (command == "Step Backwards") {
		
			//Call step forwards command
			stepBackwardsCommand();
			
		//If end simulation clicked
		} else if (command == "End Simulation") {
			
			//Call end simulation command
			endSimulationCommand();
			
		//If importing a previously saved network
		} else if (command == "Save") {
			
			//Call the save method
			saveCommand();
			
		//If saving the current network
		} else if (command == "Import") {
			
			//Call the import method
			importCommand();
		
		//If saving the network image
		} else if (command == "Export") {
			
			//Call the export image command
			exportImageCommand();
				
		}

	}
	
	/**
	 * Method for adding a node to the network
	 */
	private void addNodeCommand() {
		
		//Get node text from view
		String nodeText = uiv.getAddNodeTextField();
		
		//Check text entered
		if (nodeText != "" && !nodeText.isEmpty()) {
			
			//Add node to our network through model
			uim.addNode(nodeText);
		
		}
		
	}

	/**
	 * Method for removing a node from the 
	 * network.
	 */
	private void removeNodeCommand() {
		
		//Get selected node in our list
		Node n = uiv.getSelectedNodeListItem();
		
		//If an item was selected
		if (n != null) {
			
			//Remove from our network through model
			uim.removeNode(n);
			
		}
		
	}


	/**
	 * Method for linking two nodes within
	 * the network.
	 */
	private void linkNodesCommand() {
		
		//Get combo box selected nodes
		Node n1 = uiv.getSelectedLinkNodeCombo1();
		Node n2 = uiv.getSelectedLinkNodeCombo2();
		
		//If items selected
		if (n1 != null && n2 != null) {
			
			//Link the nodes if possible
			uim.linkNodes(n1, n2);
			
		}
		
	}

	/**
	 * Method for unlinking two nodes
	 * within the network.
	 */
	private void unlinkNodesCommand() {
		
		//Get combo box selected nodes
		Node n1 = uiv.getSelectedLinkNodeCombo1();
		Node n2 = uiv.getSelectedLinkNodeCombo2();
		
		//If items selected
		if (n1 != null && n2 != null) {
			
			//Link the nodes if possible
			uim.unlinkNodes(n1, n2);
			
		}
		
	}

	/**
	 * Method for running an entire simulation.
	 */
	private void startSimulationCommand() {
		
		//Get injection rate from view
		int messageInjectionRate = uiv.getMessageInjectionRate();
		
		//Get selected algorithm
		String algorithm = uiv.getSelectedAlgorithm();
		
		//Get the message contents
		String messageContents = uiv.getMessageContents();
		
		//Get the message source node
		Node sourceNode = uiv.getMessageSourceNode();
		
		//Get the message destination node
		Node destinationNode = uiv.getMessageDestinationNode();
		
		//If message was successfully set
		if (uim.setMessage(messageContents, sourceNode, destinationNode)) {
			
			System.out.println("set msg");
			
			//Run the simulation in the model
			uim.runSimulation(algorithm, messageInjectionRate);			
			
		}
		
	}
	
	/**
	 * Method to initialize stepping through the
	 * network simulation.
	 */
	private void initializeSteppingCommand() {
		
		//Get injection rate from view
		int messageInjectionRate = uiv.getMessageInjectionRate();
		
		//Get selected algorithm
		String algorithm = uiv.getSelectedAlgorithm();
		
		//Get the message contents
		String messageContents = uiv.getMessageContents();
		
		//Get the message source node
		Node sourceNode = uiv.getMessageSourceNode();
		
		//Get the message destination node
		Node destinationNode = uiv.getMessageDestinationNode();
		
		//If message was successfully set
		if (uim.setMessage(messageContents, sourceNode, destinationNode)) {
						
			System.out.println("get to initializing,,,");
			
			//Run the simulation in the model
			uim.initializeStepping(algorithm, messageInjectionRate);			
			
		}
		
	}
	
	/**
	 * Step forwards in the stepping simulation
	 */
	public void stepForwardsCommand() {
		
		//Call step forwards in model
		uim.stepForwards();
		
	}

	/**
	 * Step backwards in the stepping simulation
	 */
	public void stepBackwardsCommand() {
		
		//Call step forwards in model
		uim.stepBackwards();
		
	}
	
	/** 
	 * Ends a stepping simulation 
	 */
	public void endSimulationCommand() {
		
		//Call end stepping simulation in model
		uim.endSimulation();
		
	}
	/**
	 * Method for saving the network topography
	 */
	private void saveCommand() {
		
		//Filename from save dialog
		JTextField filename = new JTextField();
		
		//Path to directory from save dialog
		JTextField dir = new JTextField();
		
		//File choose for saving file
		JFileChooser c = new JFileChooser();
		
		//Save dialog
		int rVal = c.showSaveDialog(uiv);
		
		//Get the file name they will save as 
		if (rVal == JFileChooser.APPROVE_OPTION) {
			
			//Set filename text
			filename.setText(c.getSelectedFile().getName());
			
			//Set directory text
			dir.setText(c.getCurrentDirectory().toString());
			
			//Tell the model to save to the path they chose
			uim.save(dir.getText() + "\\" +  filename.getText());
			
		}
		
	}
	
	/**
	 * Method for importing a previously
	 * saved netwotk topography.
	 */
	private void importCommand() {
		
		//Filename from import dialog
		JTextField filename = new JTextField();
		
		//Path to directory from save dialog
		JTextField dir = new JTextField();
		
		//File choose to select file to import			
		JFileChooser c = new JFileChooser();
		
		//Open dialog
		int rVal = c.showOpenDialog(uiv);
		
		//File selected
		if (rVal == JFileChooser.APPROVE_OPTION) {
			
			//Set filename text
			filename.setText(c.getSelectedFile().getName());
			
			//Set directory text
			dir.setText(c.getCurrentDirectory().toString());
			
			//Tell the model to load from the path they chose
			uim.load(dir.getText() + "\\" +  filename.getText());
			
		}
		
	}
	
	/**
	 * Method for exporting the network image
	 */
	private void exportImageCommand() {
		
		UserInterfaceGraphic g = uiv.getTopology();
		
		if (g != null) {
			g.topologyToImg();
		}
		
	}
}
