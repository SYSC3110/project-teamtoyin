package UserInterfaceV2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Network.*;

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
			
			//Get node text from view
			String nodeText = uiv.getAddNodeTextField();
			
			//Check text entered
			if (nodeText != "") {
				
				//Add node to our network through model
				uim.addNode(nodeText);
			
			}
			
		//If remove node clicked
		} else if (command == "Remove Node") {
			
			//Get selected node in our list
			Node n = uiv.getSelectedNodeListItem();
			
			//If an item was selected
			if (n != null) {
				
				//Remove from our network through model
				uim.removeNode(n);
				
			}
		//If link nodes is clicked
		} else if (command == "Link Nodes") {
		
			//Get combo box selected nodes
			Node n1 = uiv.getSelectedLinkNodeCombo1();
			Node n2 = uiv.getSelectedLinkNodeCombo2();
			
			//If items selected
			if (n1 != null && n2 != null) {
				
				//Link the nodes if possible
				uim.linkNodes(n1, n2);
				
			}
			
		//If unlink nodes clicked
		} else if (command == "Unlink Nodes") {
			
			//Get combo box selected nodes
			Node n1 = uiv.getSelectedLinkNodeCombo1();
			Node n2 = uiv.getSelectedLinkNodeCombo1();
			
			//If items selected
			if (n1 != null && n2 != null) {
				
				//Link the nodes if possible
				uim.linkNodes(n1, n2);
				
			}
			
		//If start simulation is clicked
		} else if (command == "Start Simulation") {
			
			//Get number of injections from view
			int messageInjections = uiv.getMessageInjections();
			
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
				uim.runSimulation(algorithm, messageInjections);			
				
			}

		}

	}

}
