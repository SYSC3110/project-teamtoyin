package Algorithm;

import java.util.HashMap;
import java.util.Queue;

import Network.Message;
import Network.Network;
import Network.Node;

public class DepthFirst implements Algorithm {
	private Network network;			//Network of nodes the algorithm is running on
	private int packet_count;					// Number of packets transmitted during message sending
	private int max_injections = 20; 	//Maximum number of nodes to inject in the network	
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
	
	/**
	 * Traverses the network of nodes beginning at the start node and ending at
	 * the end node specified in the constructor.
	 */	
	public boolean run(Message m, int rate) {
		// TODO Auto-generated method stub
		int count = 0;		//Counter for step while loop
		int injected = 0;	//Counter for new message injections
		Message new_m;		//New message to inject into the network
		
		//Inject message into network
		network.injectMessage(m);
		
		//If the rate is 0, the network is closed for new messages
		if (rate <= 0) {
			
			//Set network to closed
			network.setOpen(false);
			
		}
		
		//While the network is good to go
		while (step()) {
			
			//If we should inject a new message 
			if ( ( rate != 0 ) && ( (count % rate) == 0 ) && ( injected < this.max_injections ) ) {

				//Create a new message
				new_m = new Message(m.getContents() + " - " + count, m.getSource(), m.getDestination());
				
				//Inject message into network
				network.injectMessage(new_m);
				
				//Increment injected counter
				injected ++;
				
			} else if (rate > 0 && injected >= this.max_injections) {
				
				//Network not open for new messages
				network.setOpen(false);
				
			}
			
			//Step again until no more stepping required
			System.out.println("Stepping again...");
			
			//Increment counter
			count++;
			
		}
		
		//Algorithm successfully ran if we reach here
		return true;
	}

	public boolean step() {
		// TODO Auto-generated method stub
		return false;
	}

	public Node next(Message m) {
		// TODO Auto-generated method stub
		return null;
	}

	public void countPacket() {
		// TODO Auto-generated method stub
		this.packet_count++;
		
	}

	public int getPacketCount() {
		// TODO Auto-generated method stub
		return this.packet_count;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
