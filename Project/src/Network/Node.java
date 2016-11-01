package Network;
import java.util.HashSet;

public class Node {
	private String name;				//Nodes name
	private HashSet<Node> neighbors;	//All of this nodes neighbors

	/**
	 * Constructor to initialize a node
	 */
	public Node(String name) {
		
		//Set nodes name
		this.name = name;
		
		//Initialize HashSet
		this.neighbors = new HashSet<Node>();
		
	}
	
	/**
	 * Returns the nodes name 
	 */
	public String getName() {
		
		//Return this nodes name
		return this.name;
		
	}
	
	/**
	 * Adds a neighbor to the node
	 */
	public boolean addNeighbor(Node n) {
		
		//Validate the neighbor node is not null
		if (n == null) { return false; } 
		
		//Check if already a neighbor
		if (this.hasNeighbor(n)) { return true; } 
		
		//Add node as a neighbor to this node
		this.neighbors.add(n);
		
		//If we added this node as a neighbor to the input node
		if (n.addNeighbor(this)) {
			
			//Successfully created node link
			return true;
		
		//Error creating node link			
		} else {
			
			//Remove input node as a neighbor
			this.neighbors.remove(n);
			
			//Error creating node link
			return false;
			
		}
	}
	
	/** 
	 * Removes a neighbor node
	 */
	public boolean removeNeighbor(Node n) {
		
		//Validate the neighbor node is not null
		if (n == null) { return false; }
		
		//Verify node is present in list of neighbors
		if (!this.neighbors.contains(n)) { return false; }
		
		//Remove node from neighbors
		if (this.neighbors.remove(n)) {
			
			//Remove this node from removed nodes neighbors
			n.removeNeighbor(n);
		
			//Neighbor successfully removed
			return true;
			
		//Could not remove node for some reason
		} else {
			
			//Error occured when removing neighbor
			return false;
			
		}

	}
	
	/**
	 * Removes all of the nodes neighbors
	 */
	public void removeNeighbors() {
		
		//Verify this node has neighbors
		if (this.neighbors.isEmpty()) { return; }
		
		//Remove each neighbor
		for (Node neighbor : this.neighbors) {
			
			//Unlink the node from the neighbor
			this.removeNeighbor(neighbor);
			
			//Unlink this node from the neighbor
			neighbor.removeNeighbor(this);
			
		}
		
	}
	
	/**
	 * Returns whether or not a node is a neighbor
	 */
	public boolean hasNeighbor(Node n) {
		
		//Validate the neighbor node is not null
		if (n == null) { return false; }
		
		//Check if node present in hashset of neighbors
		if (neighbors.contains(n)) {
			
			//Node is present
			return true;
			
		} else {
			
			//Node not present
			return false;
			
		}
			
	}
	
	/**
	 * Returns this nodes neighbors
	 */
	public HashSet<Node> getNeighbors() {
		return this.neighbors;
	}
	
	/**
	 * Override equals method
	 */
	public boolean equals(Object o) {
		return (o instanceof Node) && (((Node) o).getName()).equals(this.getName());
	}
	
	/**
	 * Override hashcode method
	 */
	public int hashCode() {
		return name.hashCode();
	}
	
}
