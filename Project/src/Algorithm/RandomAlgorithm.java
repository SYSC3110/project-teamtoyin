package Algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import Network.*;

/**
 * 
 * 
 * Modified By: Lina El Sadek The random algorithm selects the next node to
 * traverse to randomly.
 *
 */
public class RandomAlgorithm implements Algorithm {
	private Network network; // Network of nodes the algorithm is running on
	private Random random; // Random instance for selecting next node
	private Node start_n; // Start node in the network
	private Node end_n; // End node in the network
	private int packet_count; // Number of packets transmitted during message sending
	private int rate; // Rate that messages should be injected into the network

	/**
	 * Constructor to assign network to the algorithm along with the start and
	 * end nodes.
	 */
	public RandomAlgorithm(Network n) {

		//If network is null throw exception
		if (n == null)
			throw new NullPointerException();
		else
			this.network = n;

		// Initialize the random class
		this.random = new Random();

		// Initialize rate, packet_count, start_n, end_n
		this.packet_count = 0;
		this.start_n = null;
		this.end_n = null;

	}

	/**
	 * Traverses the network of nodes beginning at the start node and ending at
	 * the end node specified in the constructor.
	 */

	public boolean run(Message m, int rate) {
		int count = 0;	//Counter for step while loop
		
		//Set rate
		this.rate = rate;
		
		//Inject message into network
		network.injectMessage(m);
		
		//Set network to closed for now, rate is not yet implemented
		network.setOpen(false);
		
		//While the network is good to go
		while (step()) {
			
			//Inject new messages here based on rate??? 
			//Will do this when babak emails me back
			
			//Step again until no more stepping required
			System.out.println("Stepping again...");
			
		}
		
		//Algorithm successfully ran if we reach here
		return true;
		
	}

	/**
	 * Performs a simulation step, moving messages to the next node and
	 * injecting new messages as required. Returns false when there is no
	 * further step to take.
	 */
	public boolean step() {

		int index = 0; 	// Index in arraylist of messages
		Node new_n; 	// New node to move message to

		//If no more messages travelling in network and the network is not receiving new messages
		if (!network.messagesMoving() && !network.isOpen()) {
			System.out.println("No messages moving and network closed for new messages.");
			return false;
		}

		// For each message in the network
		for (Message m : network.getMessages()) {

			// If the message is already at its destination, skip
			if (m.getNode() == m.getDestination()) {
				System.out.println(m.getContents() + " is at the destination node " + m.getNode().getName());
				continue;
			}

			// Get node to move message to
			new_n = this.next(m.getNode());
			
			System.out.println("Message " + m.getContents() + " moving from " + m.getNode().getName() + " to " + new_n.getName());
			
			//Set messages new node
			network.getMessages().get(index).setNode(new_n);
			
			//Increment packets sent
			this.countPacket();
			
			//Increment index counter for messages arraylist
			index ++;
			
		}

		//Step successfully completed
		return true;
		
	}

	/**
	 * Selects the next node to travel to and returns it.
	 */
	public Node next(Node n) {

		// If the node isn't present in the network return
		if (!network.contains(n)) {
			System.out.println("Does not contain node " + n.getName());
			return null;
		}

		// Get the nodes neighbors
		HashSet<Node> neighbors;
		neighbors = n.getNeighbors();

		// Get amount of neighbors
		int neighbor_count = neighbors.size();

		// Select random neighbor index
		int random_neighbor = random.nextInt(neighbor_count);

		// Fetch the random neighbor and return it
		int i = 0;
		for (Node rn : neighbors) {
			if (i == random_neighbor)
				return rn;
			i = i + 1;
		}

		// We should not get to this point
		System.out.println("Something went wrong choosing the random node");
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

	/**
	 * Testing
	 */
	public static void main(String[] args) {

		Network n = new Network();
		Node n1 = new Node("A");
		Node n2 = new Node("B");
		Node n3 = new Node("C");
		Node n4 = new Node("D");

		n.add(n1);
		n.add(n2);
		n.add(n3);
		n.add(n4);

		n.link(n1, n2);
		n.link(n2, n3);
		n.link(n3, n4);

		RandomAlgorithm algo = new RandomAlgorithm(n);

		Message m = new Message("MSG1", n1, n4);
		boolean value = algo.run(m, 3);
		System.out.println("Packets sent during transmission: " + algo.getPacketCount());
		System.out.println("true or false " + value);
	}

}
