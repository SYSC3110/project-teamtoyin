package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import Network.Message;
import Network.Network;
import Network.Node;

/**
 * @author Scott Hanton
 * 
 */
public class ShortestPath implements Algorithm {
	private Network network;			// Network of nodes the algorithm is running on
	private int packet_count; 		 	// Number of packets transmitted during message sending
	private int max_injections = 20; 	//Maximum number of nodes to inject in the network
	private HashMap<Node, HashMap<Node, Node>> routing_table; //Shortest path routing table (Current node, destination node, next node)
	
	/**
	 * Constructor to assign network to the algorithm.
	 */
	public ShortestPath(Network n) {

		//If network is null throw exception
		if (n == null)
			throw new NullPointerException();
		else
			this.network = n;

		// Initialize packet_count
		this.packet_count = 0;
		
		//Load the routing table
		this.loadRoutingTable();
		
	}
	
	/**
	 * Loads the routing table with which neighbor node to travel to
	 * depending on the destination node. Uses the Breadth-first search
	 * algorithm.
	 */
	public void loadRoutingTable() {
		Queue<Node> q = new LinkedList<Node>();				//Queue of nodes to visit 
		ArrayList<Node> visited = new ArrayList<Node>();	//ArrayList of nodes visited
		Node prev_n; 										//Previous node visited 
		Node curr_n;										//Node we are currently at
		
		//Get an element  from network to start at
		q.add( (this.network.getNodes().toArray(new Node[1]) [0]) );
		
		//While more nodes to visit
		while (!q.isEmpty()) {
						
		}
	}
	
	/**
	 * Traverses the network of nodes beginning at the start node and ending at
	 * the end node specified in the constructor.
	 */	
	public boolean run(Message m, int rate) {

		
		// TODO Auto-generated method stub
		return false;
		
	}

	/**
	 * Performs a simulation step, moving messages to the next node and
	 * injecting new messages as required. Returns false when there is no
	 * further step to take.
	 */
	public boolean step() {
		
		// TODO Auto-generated method stub
		return false;
		
	}
	
	/**
	 * Selects the next node to travel to and returns it.
	 */
	public Node next(Message m) {
		
		// TODO Auto-generated method stub
		return null;
		
	}

	/**
	 * Increment the counter for number of packets transmitted during message
	 * sending.
	 */
	public void countPacket() {

		// Increment packets counter
		this.packet_count++;

	}

	/**
	 * Returns number of packets transmitted during message sending.
	 */
	public int getPacketCount() {

		// Increment packets counter
		return this.packet_count;

	}

}
