package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import Network.Message;
import Network.Network;
import Network.Node;

/**
 * @author Richard Hanton
 *
 * Pre-populates a routing table using the breadth first search algorithm.
 *
 */
public class ShortestPathAlgorithm extends Algorithm {
	private HashMap<Node, HashMap<Node, Node>> routing_table; //Shortest path routing table (Current node, destination node, next node)

	/**
	 * Constructor to assign network to the algorithm.
	 */
	public ShortestPathAlgorithm(Network network) throws NullPointerException{
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
	
	/**
	 * Loads the routing table with which neighbor node to travel to
	 * depending on the destination node. Uses the Breadth-first search
	 * algorithm.
	 */
	private void loadRoutingTable() {
		Queue<Node> q; 										//Queue of nodes to visit 
		Node curr_n;										//Node we are currently at
		Node prev_n;										//Node we visited a node from
		HashMap<Node, Node> visited;						//Hashmap of nodes visited and the node we visited from
		HashMap<Node, Node> routing_entry;					//Entry in our routing table (destination node, next node)
		
		//Populate routing table for each node in network
		for (Node start_n : this.network.getNodes()) {
		
			//Initialize hashmap
			visited = new HashMap<Node, Node>();
			
			//Initialize queue
			q = new LinkedList<Node>();
			
			//Get an element from network to start at
			q.add( start_n );
			
			//Add this node to visited
			visited.put(start_n, null);
			
			//While more nodes to visit
			while (!q.isEmpty()) {
				
				//Get node from queue
				curr_n = q.poll();
							
				//For each of the nodes neighbors
				for (Node n : curr_n.getNeighbors()) {
					
					//If we have not yet visited this now
					if (!visited.containsKey(n)) {
						
						//Mark visited
						visited.put(n, curr_n);
						
						//Add to queue
						q.add(n);
						
					}
					
				}
			}
			
			//Loop each node visited
			for (Node n : visited.keySet()) {
				
				//If it is our start node continue
				if (n == start_n) { continue; } 
				
				//Starting point to move backwards towards start node
				prev_n = n;
								
				//Loop until we reach a neighbor of our start node
				while (!start_n.getNeighbors().contains(prev_n)) {
					
					//Go to node we visited this node from
					prev_n = visited.get(prev_n);				
					
				}
				
				//Routing entry for start node
				routing_entry = routing_table.get(start_n);
				
				//Add values to routing entry
				routing_entry.put(n, prev_n);
				
				//Add entry to routing entry 
				routing_table.put(start_n, routing_entry);
				
			}
		
		}
		
	}
	
	/**
	 * Performs a simulation step, moving messages to the next node and
	 * injecting new messages as required. Returns false when there is no
	 * further step to take.
	 */
	@Override
	protected boolean step() {
		
		int index = 0; 	// Index in arraylist of messages
		Node new_n; 	// New node to move message to

		//If no more messages travelling in network and the network is not receiving new messages
		if (!network.messagesMoving() && !network.isOpen()) {
			getInfo().add("No messages moving and network closed for new messages.\n");
			System.out.println("No messages moving and network closed for new messages.");
			return false;
		}

		//Iterator for messages in network
		Iterator<Message> i = network.getMessages().iterator();
		
		//For each message in network
		while (i.hasNext()) {
			
			//Get message
			Message m = i.next();

			// If the message is already at its destination, skip
			if (m.getNode() == m.getDestination()) {
				
				//Debug
				getInfo().add(m.getContents() + " is at the destination node " + m.getNode().getName()+"\n");
				System.out.println(m.getContents() + " is at the destination node " + m.getNode().getName());
				
				//Node is at destination so remove it
				i.remove();
								
				//Continue to next message
				continue;
				
			} else {
				
				//Debug
				getInfo().add(m.getContents() + " is at node " + m.getNode().getName() + " and is going to node " + m.getDestination().getName()+"\n");
				System.out.println(m.getContents() + " is at node " + m.getNode().getName() + " and is going to node " + m.getDestination().getName());
			
			}

			// Get node to move message to
			new_n = this.next(m);
			
			//Update the messages position in the network
			network.getMessages().get(index).setNode(new_n);
			
			//Increment packets sent
			this.countPacket();
			
			//Count message hops
			network.getMessages().get(index).countHop();
			
			//Increment index counter for messages arraylist
			index ++;
			
		}
		

		//Step successfully completed
		return true;
		
	}
	
	/**
	 * Selects the next node to travel to and returns it.
	 */
	@Override
	protected Node next(Message m) {
		HashMap<Node, Node> hm;	//Mashmap of routing table for given node 
		Node next_node;			//Next node to go to
		Node n;					//Current node that the message is at
		
		//Get messages current node
		n = m.getNode();
		
		// If the node isn't present in the network return
		if (!network.contains(n)) {
			getInfo().add("Does not contain node " + n.getName()+"\n");
			System.out.println("Does not contain node " + n.getName());
			return null;
		}

		//Get routing table for current node
		hm = routing_table.get(n);
		
		//Get next node based on destination
		next_node = hm.get(m.getDestination());
		
		//Return the next node
		return next_node;
		
	}
	
	public static void main(String[] args) {
		
		Network n = new Network();
		Node n1 = new Node("1");
		Node n2 = new Node("2");
		Node n3 = new Node("3");		
		Node n4 = new Node("4");
		Node n5 = new Node("5");

		n.add(n1);
		n.add(n2);
		n.add(n3);
		n.add(n4);
		n.add(n5);

		n.link(n1, n2);
		n.link(n2, n3);
		n.link(n2, n4);	
		n.link(n3, n4);		
		n.link(n4, n5);

		ShortestPathAlgorithm algo = new ShortestPathAlgorithm(n);
		Message m = new Message("MSG1", n1, n5);
		boolean value = algo.run(m, 0);
		System.out.println("Packets sent during transmission: " + algo.getPacketCount());
		System.out.println("true or false " + value);
		
	}
	

}
