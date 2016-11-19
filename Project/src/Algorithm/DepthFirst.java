package Algorithm;

import java.util.HashMap;
import java.util.Queue;

import Network.Message;
import Network.Network;
import Network.Node;

public class DepthFirst extends Algorithm {
	private HashMap<Node, HashMap<Node, Node>> routing_table; //Depth first routing table (Current node, destination node, next node)
	
	/**
	 * Constructor to assign network to the algorithm.
	 */
	public DepthFirst(Network network) throws NullPointerException{
		HashMap<Node, Node> routing_entry;	//Routing table entry 
		
		//If network is null throw exception
		if (network == null)
			throw new NullPointerException();
		else
			this.network = network;

		// Initialize packet_count
		this.packet_count = 0;
		
		//Initialize routing table
		routing_table = new HashMap<Node, HashMap<Node, Node>>();
		
		//For each node in network initialize their routing entry
		for (Node n: network.getNodes()) {
			
			//Initialize new hashmap
			routing_entry = new HashMap<Node, Node>();
			
			//Add hashmap to routing table
			routing_table.put(n, routing_entry);
			
		}
		
		//Load the routing table
		this.loadRoutingTable();
		
	}
	private void loadRoutingTable() {
		// TODO Auto-generated method stub
		Queue<Node> q; 										//Queue of nodes to visit 
		Node curr_n;										//Node we are currently at
		Node prev_n;										//Node we visited a node from
		HashMap<Node, Node> visited;						//Hashmap of nodes visited and the node we visited from
		HashMap<Node, Node> routing_entry;					//Entry in our routing table (destination node, next node)
		
	}
	
	@Override
	protected boolean step() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Node next(Message m) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
