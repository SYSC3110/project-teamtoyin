package Network;
import java.util.HashSet;
import java.util.Iterator;


/**
 * 
 * @author Richard Hanton
 * @date 11/1/2016
 * 
 * Modified by: Lina El Sadek
 *
 */
public class Node {
	private String name;				//Nodes name
	private HashSet<Node> neighbors;	//All of this nodes neighbors

	/**
	 * Constructor to initialize a node
	 */
	public Node(String name) throws NullPointerException, IllegalArgumentException{
		
		//Set nodes name
		if (name == null)
			throw new NullPointerException();
		else if (name.equals(""))
			throw new IllegalArgumentException();
		else
			this.name = name.toUpperCase();
		
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
		
		Iterator<Node> iter = neighbors.iterator();
		
		//Verify this node has neighbors
		if (this.neighbors.isEmpty()) { return; }
		
		//Remove each neighbor
		while(iter.hasNext())
		{
			//get the current node
			Node n = iter.next();
			
			//remove this object as a neighbor from that object
			n.removeNeighbor(this);
			
			//remove neighbor from this object
			iter.remove();
			
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
		return (o instanceof Node) && (((Node) o).getName()).equals(this.name);
	}
	
	/**
	 * Override hashcode method
	 */
	public int hashCode() {
		return this.name.hashCode();
	}
	
	/**
	 * Converting node to string
	 */
	public String toString() {
		return this.name;
	}
	
}
