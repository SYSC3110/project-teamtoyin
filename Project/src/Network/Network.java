package Network;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Richard Hanton
 * Osama Buhamad: Modified some logic to simplify and readability
 * The network holds all nodes and nodes neighbors
 *	Modified By: Lina El Sadek on 10/25/2016
 */

public class Network {
	
	private HashSet<Node> nodes;			//Stores all of the network nodes
	private ArrayList<Message> messages;	//List of messages present in the network
	private boolean open;					//When true the network will stay open for accepting new messages
	
	/**
	 * Constructor to initialize our network of nodes
	 */
	public Network() {
		
		//Initialize new hashmap for nodes and neighbors
		nodes = new HashSet<Node>();
		
		//Initialize new arraylist for messages in network
		messages = new ArrayList<Message>();
		
		//Set the network to open for accepting messages 
		this.open = true;
		
	}

	/**
	 * Adds a node to our network
	 */
	public boolean add(Node n) {
		
		//Validate n is not null
		if (n == null) { 
			System.out.println("Enter a valid node");
			return false; 
		} 
		
		//If the node is present in the network return
		if (nodes.contains(n)) {
			System.out.println("Node name already exists");
			return false;
		}
		
		//Add the node to the network, with a new array for its neighbors
		nodes.add(n);
		
		//Successfully added
		return true;
	}

	
	/**
	 * Removes a node from the network, unlinking it from its neighbors
	 */
	public boolean remove(Node n) {
		
		//Validate n has a value
		if (n == null) { 
			System.out.println("Node you entered is invalid");
			return false; 
		} 
		
		//If the node isn't present in the network return		
		if (!nodes.contains(n)) { 
			System.out.println("Node does not exist");
			return false; 
		}
		
		//Remove the nodes neighbor links 
		n.removeNeighbors();
		
		//remove the node from the HashMap
		nodes.remove(n);
		
		//Successfully removed
		return true;
		
	}
	
	/**
	 * Returns whether or not node is present in network
	 */
	public boolean contains(Node n) {
		
		//Validate n has a value
		if (n == null) { 
			return false; 
		} 
		
		//Node not present in hashmap
		if (!nodes.contains(n)) {
			return false; 
		} else {
			return true;
		}
	}	
	
	
	/**
	 * Links two nodes in the network together
	 */
	public boolean link(Node n1, Node n2) {

		//Validate n1 has a value
		if (n1 == null) { return false; } 
		
		//Validate n2 has a value
		if (n2 == null) { return false; } 		

		//Verify nodes arn't the same
		if (n1 == n2) { return false; } 
		
		//verify n1 and n2 are in the network
		if(!(nodes.contains(n1) && nodes.contains(n2))){
			return false;
		}
		
		//Add node 2 as neighbor to node 1 and vice-versa
		if (n1.addNeighbor(n2) && n2.addNeighbor(n1)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Unlinks two nodes in the network from each other
	 */
	public boolean unlink(Node n1, Node n2) {
		
		//Validate n1 has a value
		if (n1 == null) { return false; } 
		
		//Validate n has a value
		if (n2 == null) { return false; } 			
		
		//Verify nodes arn't the same
		if (n1 == n2) { return false; } 
		
		//verify n1 and n2 are in the network
		if(!(nodes.contains(n1) && nodes.contains(n2))){
			return false;
		}
		
		//Remove node 2 as neighbor from node 1 and vice versa
		if (n1.removeNeighbor(n2) && n2.removeNeighbor(n1)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Injects a message into the network
	 */
	public boolean injectMessage(Message m) {
		
		//Check that message being added has source and destination
		if (m.getSource() == null || m.getDestination() == null) { return false; }
		
		//Add message to our list of messages in the network
		messages.add(m);
		
		//Successfully added
		return true;
		
	}
	
	/**
	 * Removes a message from the network
	 */
	public void removeMessage(Message m) {
		
		//If the message is in the network
		if (this.messages.contains(m)) {
			
			//Remove the message
			this.messages.remove(m);
			
		}
		
	}
	/**
	 * Determines whether the network has messages which 
	 * need to keep moving. 
	 */
	public boolean messagesMoving() {
		
		//Loop each message
		for (Message m : messages) {
			
			//If the message is not at the destination
			if (m.getNode() != m.getDestination())
				
				//Messages are still moving, return true
				return true;
		}
		
		//All messages are at the destination
		return false;
		
	}
	
	/**
	 * Returns the messages in the network 
	 */
	public ArrayList<Message> getMessages() {
		
		//Return messages currently in network
		return this.messages;
		
	}
	
	/**
	 * Sets all the messages in the network
	 */
	public void setMessages(ArrayList<Message> messages) {
		
		//Check messages is not null
		if (messages == null) { System.out.println("could not set messages, null"); return; } 
		
		//Set messages in network
		this.messages = messages;
		
		//return
		return;
		
	}
	
	/**
	 * Sets the network open or closed, if the network
	 * is closed and no messages are moving, then 
	 * the simulation is done.
	 */
	public void setOpen(boolean open) {
		
		//Set network open status
		this.open = open;
		
	}
	
	/**
	 * Returns whether or not the network is forced 
	 * open. Meaning that there are no messages moving
	 * but it is still accepting new messages.
	 */
	public boolean isOpen() {
		
		//If network is open
		if (this.open) {
			
			return true;
			
		} else {
			
			return false;
			
		}
	}
	
	/**
	 * Returns the number of nodes in the network
	 * @return
	 */
	public int getNetworkNodesNumber() {
		
		//Return nodes size
		return nodes.size();
		
	}
	
	/**
	 * Returns the list of nodes in the network
	 */
	public HashSet<Node> getNodes(){
		
		//Return nodes hashset
		return this.nodes;
		
	}
	
	/**
	 * Returns a node based on a string
	 */
	public Node getNode(String nodeName) {
			
		for(Node n : nodes)
		{
			if(n.getName().equals(nodeName.toUpperCase()))
				return n;
		}
		
		return null;
	}	
	
	
	
}
