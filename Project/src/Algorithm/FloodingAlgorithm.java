package Algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Network.Message;
import Network.Network;
import Network.Node; 

/*
 * Author Toyin Odujebe
 * Modified Richard Hanton
 */

public class FloodingAlgorithm extends Algorithm {
	private Network network;			//Network of nodes the algorithm is running on
	private int packet_count;					// Number of packets transmitted during message sending
	private int max_injections = 20; 	//Maximum number of nodes to inject in the network	
	
	/**
	 * Constructor to assign network to the algorithm.
	 */
	public FloodingAlgorithm(Network n) throws NullPointerException{
		
		//If network is null throw exception
		if (n == null)
			throw new NullPointerException();
		else
			this.network = n;
		
		// Initialize packet_count
		this.packet_count = 0;
		
	}
	
	/**
	 * Traverses the network of nodes beginning at the start node and ending at
	 * the end node specified in the constructor.
	 */	
	public boolean run(Message m, int rate) {
		
		int count = 0;		//Counter for step while loop
		int injected = 0;	//Counter for new message injections		
		Message new_m; //New message to inject into the network
		
		//Inject message into the network
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
			System.out.println("");
			System.out.println(" ------ Stepping again...   ------ ");
			System.out.println("");
			
			//Increment counter
			count++;			
			
		}
		
		//Algorithm successfully ran if we reach here	
		return true;
		
	}
	
	/**
	 * Performs a simulation step, moving messages to the next node and
	 * injecting new messages as required. Returns false when there is no
	 * further step to take.
	 */
	protected boolean step() {
		Node new_n; 				//New node to move message to
		Message new_m;				//New message created to flood out
		ArrayList<Message> messages;//New list of messages for the network
		
		//Initialize arraylist of messages
		messages = new ArrayList<Message>();
		
		//If no more messages travelling in network and the network is not receiving new messages
		if (!network.messagesMoving() && !network.isOpen()) {
			System.out.println("No messages moving and network closed for new messages.");
			return false;
		}

		//Iterator for messages in network
		Iterator<Message> i = network.getMessages().iterator();
		
		//For each message in network
		while (i.hasNext()) {
			
			System.out.println("Message under considering...");
			
			//Get message
			Message m = i.next();
			
			// If the message is already at its destination, skip
			if (m.getNode() == m.getDestination()) {
				
				//Debug
				System.out.println(m.getContents() + " is at the destination node " + m.getNode().getName());
				
				//Node is at destination so remove it
				i.remove();
								
				//Continue to next message
				continue;
				
			} else {
				
				//Debug
				System.out.println(m.getContents() + " is at node " + m.getNode().getName() + " and is going to node " + m.getDestination().getName());
			
			}

			// Get node to move message to
			new_n = this.next(m);
			
			//Send to all neighbors that havn't received it yet
			while (new_n != null) {
								
				//Create a copy of the message
				new_m = new Message(m);
				
				//Move the message to the next node
				new_m.setNode(new_n);
				
				//Count a hop for this new message
				new_m.countHop();
				
				//Add to list of updated network messages
				messages.add(new_m);
				
				//Add new node to current messages history so we dont send another message there
				m.addHistory(new_n);
				
				//Increment packets sent
				this.countPacket();
				
				//Debug
				System.out.println("Copy of message going from " + m.getNode().getName() + " to " + new_m.getNode().getName());
				
				// Get next node to move message to
				new_n = this.next(m);

			}				
			
			//Remove original message, it has been passed to all eligible neighbors
			i.remove();
			
		}
		
		//Update messages in network
		network.setMessages(messages);

		//Step successfully completed
		return true;
		
	}
	
	/**
	 * Selects the next node to travel to and returns it.
	 */
	protected Node next(Message m) {
		
		//Get messages current node
		Node n = m.getNode();
		
		// If the node isn't present in the network return
		if (!network.contains(n)) {
			System.out.println("Does not contain node " + n.getName());
			return null;
		}

		// Get the nodes neighbors
		HashSet<Node> neighbors;
		neighbors = n.getNeighbors();

		// Fetch the random neighbor and return it
		for (Node neighbor_n : neighbors) {
			if (!m.getHistory().contains(neighbor_n))
				return neighbor_n;
			else
				System.out.println("Message history contains " + neighbor_n.getName());
		}

		// We should not get to this point
		System.out.println("No new neighbor nodes to send this message to");
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

	
	public static void main(String[] args) {
		
		Network n = new Network();
		Node n1 = new Node("A");
		Node n2 = new Node("B");
		Node n3 = new Node("C");		
		Node n4 = new Node("D");
		Node n5 = new Node("E");

		n.add(n1);
		n.add(n2);
		n.add(n3);
		n.add(n4);
		n.add(n5);

		n.link(n1, n2);
		n.link(n2, n3);
		n.link(n2, n4);	
		n.link(n3, n5);		
		n.link(n4, n5);

		FloodingAlgorithm algo = new FloodingAlgorithm(n);

		Message m = new Message("MSG1", n1, n5);
		boolean value = algo.run(m, 0);
		System.out.println("Packets sent during transmission: " + algo.getPacketCount());
		System.out.println("true or false " + value);
		
	}


}
