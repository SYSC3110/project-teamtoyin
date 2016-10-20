package Network;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Richard Hanton
 * Osama Buhamad: Modified some logic to simplify and readability
 * The network holds all nodes and nodes neighbors
 *
 */
public class Network {
	
	private HashMap<String, HashSet<String>> nodes;	//Stores all of the network nodes with their neighbors

	/**
	 * Constructor to initialize our network of nodes
	 */
	public Network() {
		
		//Initialize new hashmap for nodes and neighbors
		nodes = new HashMap<String, HashSet<String>>();
	}

	/**
	 * Adds a node to our network
	 */
	public boolean add(String n) {
		
		//Validate n has a value
		if (n == null || n == "") { 
			System.out.println("Enter a valid node");
			return false; } 
		
		//Force node name to uppercase
		n = n.toUpperCase();
		
		//If the node is present in the network return
		if (nodes.containsKey(n)) {
			System.out.println("Node name already exists");
			return false;
		}
		
		//Add the node to the network, with a new array for its neighbors
		nodes.put(n, new HashSet<String>());
		
		//Successfully added
		return true;
	}

	
	/**
	 * Removes a node from the network, unlinking it from its neighbors
	 */
	public boolean remove(String n) {
		
		//Validate n has a value
		if (n == null || n == "") { 
			System.out.println("Node you entered is invalid");
			return false; } 
		
		//Force node name to uppercase
		n = n.toUpperCase();
		
		//If the node isn't present in the network return		
		if (!nodes.containsKey(n)) { 
			System.out.println("Node does not exist");
			return false; 
		}
		
		//For each of the nodes neighbors
		for (String neighbor : nodes.get(n)) {
			
			//Unlink the node from the neighbor
			this.unlink(n, neighbor);
			
		}
		//remove the node from the HashMap
		nodes.remove(n);
		//Successfully removed
		return true;
	}
	
	/**
	 * Returns whether or not node is present in network
	 */
	public boolean contains(String n) {
		
		//Validate n has a value
		if (n == null || n == "") { return false; } 
		
		//Force node name to uppercase
		n = n.toUpperCase();
		
		//Node not present in hashmap
		if (!nodes.containsKey(n)) { 
			return false; 
		} else { 
			return true;}
	}	
	
	/**
	 * Returns the neighbors of a node
	 */
	public HashSet<String> getNeighbors(String n) {
		
		//Validate n has a value
		if (n == null || n == "") { return null; } 
		
		//Force node name to uppercase
		n = n.toUpperCase();
		
		//If the node isn't present in the network return				
		if (!nodes.containsKey(n)) { return null; }
		
		return nodes.get(n);
	}
	
	/**
	 * Links two nodes in the network together
	 */
	public boolean link(String n1, String n2) {
		
		//Validate n1 has a value
		if (n1 == null || n1 == "") { return false; } 
		
		//Validate n2 has a value
		if (n2 == null || n2 == "") { return false; } 
		
		//Force n1 name to uppercase
		n1 = n1.toUpperCase();
		
		//Force n2 name to uppercase
		n2 = n2.toUpperCase();			

		//verify n1 and n2 are in the network
		if(!(nodes.containsKey(n1) && nodes.containsKey(n2))){
			return false;
		}
		
		//Add node 2 as neighbor to node 1 and vice-versa
		if (nodes.get(n1).add(n2) && nodes.get(n2).add(n1)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Unlinks two nodes in the network from each other
	 */
	public boolean unlink(String n1, String n2) {
		
		//Validate n1 has a value
		if (n1 == null || n1 == "") { return false; } 
		
		//Validate n has a value
		if (n2 == null || n2 == "") { return false; } 
		
		//Force n1 name to uppercase
		n1 = n1.toUpperCase();
		
		//Force n2 name to uppercase
		n2 = n2.toUpperCase();				
		
		//verify n1 and n2 are in the network
		if(!(nodes.containsKey(n1) && nodes.containsKey(n2))){
			return false;
		}
		
		//Remove node 2 as neighbor from node 1 and vice versa
		if (nodes.get(n1).remove(n2) && nodes.get(n2).remove(n1)) {
			return true;
		} else {
			return false;
		}
	}
	
}
