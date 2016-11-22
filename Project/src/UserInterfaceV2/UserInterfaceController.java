package UserInterfaceV2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

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
			if (nodeText != "" && !nodeText.isEmpty()) {
				
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
			Node n2 = uiv.getSelectedLinkNodeCombo2();
			
			//If items selected
			if (n1 != null && n2 != null) {
				
				//Link the nodes if possible
				uim.unlinkNodes(n1, n2);
				
			}
			
		//If start simulation is clicked
		} else if (command == "Start Simulation") {
			
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

		//If importing a previously saved network
		} else if (command == "Save") {
			
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
			
		//If saving the current network
		} else if (command == "Import") {
			
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
		

	}

}
