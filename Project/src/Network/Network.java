package Network;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 
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
		
		//If the node isn't present in the network return
		if (nodes.containsKey(n)) {
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
		
		//If the node isn't present in the network return		
		if (!nodes.containsKey(n)) { return false; }
		
		//For each of the nodes neighbors
		for (String neighbor : nodes.get(n)) {
			
			//Unlink the node from the neighbor
			this.unlink(n, neighbor);
			
		}
		
		//Successfully removed
		return true;
	}
	
	/**
	 * Returns whether or not node is present in network
	 */
	public boolean contains(String n) {
		
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
		
		//If the node isn't present in the network return				
		if (!nodes.containsKey(n)) { return null; }
		
		return nodes.get(n);
	}
	
	/**
	 * Links two nodes in the network together
	 */
	public boolean link(String n1, String n2) {
		
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
		
		//Remove node 2 as neighbor from node 1 and vice versa
		if (nodes.get(n1).remove(n2) && nodes.get(n2).remove(n1)) {
			return true;
		} else {
			return false;
		}
	}
}