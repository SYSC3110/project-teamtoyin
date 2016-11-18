package UserInterfaceV2;

import java.lang.reflect.Constructor;
import java.util.Observable;

import Algorithm.*;
import Network.*;
import UserInterface.UserInterfaceGraphic;

public class UserInterfaceModel extends Observable {

	private Network network;
	private Algorithm algorithm;
	private Message message;
	
	/** 
	 * Constructor for our user interface model
	 */
	public UserInterfaceModel() {
		
		//Initialize the network
		this.network = new Network();
		
	}
	
	/**
	 * Add node to our network
	 */
	public void addNode(String str) {
		
		//Create node based on entered string
		Node n = new Node(str);
		
		//Create event
		UserInterfaceEvent e = new UserInterfaceEvent(this);
		
		//Set type of event
		e.setType("Node Update");
		
		//Start of Try/Catch
		try {
			
		
			//If node added to network
			if (network.add(n)) {
							
				//Set network in event
				e.setNetwork(this.network);
				
				//Set success
				e.setSuccess(true);
				
			} else {
				
				//Set message to return
				e.setMessage("Could not add the node.");
				
				//Set success
				e.setSuccess(false);
				
			}
		
		//Caught Exception
		} catch(Exception ex) {
			
			//Save exception into return message
			e.setMessage(ex.toString());
			
		}
		
		//Set observer as changed 
		setChanged();		
		
		//Notify view that the model updated
		notifyObservers(e);	
		
	}
	
	/**
	 * Add node to our network
	 */
	public void removeNode(Node n) {
		
		//Create event
		UserInterfaceEvent e = new UserInterfaceEvent(this);
		
		//Set type of event
		e.setType("Node Update");
		
		//Start of Try/Catch
		try {

			//If node added to network
			if (network.remove(n)) {
				
				//Set network in event
				e.setNetwork(this.network);
				
				//Set success
				e.setSuccess(true);
				
			} else {
				
				e.setMessage("Could not remove node");
				
			}
		
		//Caught Exception	
		} catch(Exception ex) {
			
			//Save exception into return message
			e.setMessage(ex.toString());
			
		}
		
		//Set observer as changed 
		setChanged();		
		
		//Notify view that the model updated
		notifyObservers(e);	
		
	}
	
	/**
	 * Link 2 nodes in our network
	 */
	public void linkNodes(Node n1, Node n2) {
		
		//Create event
		UserInterfaceEvent e = new UserInterfaceEvent(this);
		
		//Set type of event
		e.setType("Node Link Update");
		
		//Start of Try/Catch
		try {
			
		
			//If node added to network
			if (network.link(n1, n2)) {
							
				//Set network in event
				e.setNetwork(this.network);
				
				//Set success
				e.setSuccess(true);
				
			} else {
				
				//Set message
				e.setMessage("Could not link " + n1.toString() + " and " + n2.toString());
				
				//Set success
				e.setSuccess(false);
				
			}
		
		//Caught Exception
		} catch(Exception ex) {
			
			//Save exception into return message
			e.setMessage(ex.toString());
			
		}
		
		//Set observer as changed 
		setChanged();		
		
		//Notify view that the model updated
		notifyObservers(e);			
	}

	/**
	 * Unlink 2 nodes in our network
	 */
	public void unlinkNodes(Node n1, Node n2) {
		
		//Create event
		UserInterfaceEvent e = new UserInterfaceEvent(this);
		
		//Set type of event
		e.setType("Node Link Update");
		
		//Start of Try/Catch
		try {
			
		
			//If node added to network
			if (network.unlink(n1, n2)) {
							
				//Set network in event
				e.setNetwork(this.network);
				
				//Set success
				e.setSuccess(true);
				
			} else {
				
				//Set message
				e.setMessage("Could not unlink " + n1.toString() + " and " + n2.toString());
				
				//Set success
				e.setSuccess(false);
				
			}
		
		//Caught Exception
		} catch(Exception ex) {
			
			//Save exception into return message
			e.setMessage(ex.toString());
			
		}
		
		//Set observer as changed 
		setChanged();		
		
		//Notify view that the model updated
		notifyObservers(e);			
	}

	/**
	 * Set the message we are going to send
	 */
	public boolean setMessage(String contents, Node source, Node destination) {
		
		//Start of Try/Catch
		try {
			
			//Try to create the message
			message = new Message(contents, source, destination);
			
			//Return successful
			return true;
			
		//Message unable to be sent
		} catch(Exception ex) {
			
			//Create event
			UserInterfaceEvent e = new UserInterfaceEvent(this);
			
			//Set type of event
			e.setType("Set Message");
			
			//Set message to return to view
			e.setMessage("Unable to create this message.");
			
			//Set observer as changed 
			setChanged();		
			
			//Notify view that the model updated
			notifyObservers(e);		
			
			//Return unsuccessful
			return false;
			
		}
	}
	
	/**
	 * Run a simulation given the algorithm name and number 
	 * of message injections
	 */
	public void runSimulation(String algo, int messageInjections) {
		
		//Check message not null
		if (this.message == null) { return; } 
				
		//Create event
		UserInterfaceEvent e = new UserInterfaceEvent(this);
		
		//Set event type
		e.setType("Simulation");
		 
		//Check if class exists 
		try {
			
			//Check class exists
		    Class<?> class_temp = Class.forName("Algorithm."+algo);
		    
		    //Get algorithm constructor
		    Constructor<?> con = class_temp.getConstructor(Network.class);
		    
		    //Initialize the algorithm
		    algorithm = (Algorithm) con.newInstance(network);
		    
		   
		    
		    //Run the algorithm
		    if (algorithm.run(message, messageInjections)) {
		    	
		    	//Algorithm successfully executed
		    	e.setSuccess(true);
		    	
		    	//Set a success message
		    	e.setMessage("Algorithm ran successfully");
		    	
		    	//Show network topology
			    new UserInterfaceGraphic(network,message);
		    	
		    } else {
		    	
		    	//Algorithm did not execute successfully
		    	e.setMessage("Something went wrong running the algorithm...");
		    	
		    }
		    	        
		//Caught exception
		} catch (Exception ex) {
					    
		    e.setMessage("Algorithm not found.");
		    
		}
		
		//Set observer as changed 
		setChanged();		
		
		//Notify view that the model updated
		notifyObservers(e);				
		
	}
}

