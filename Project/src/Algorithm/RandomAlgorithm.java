package Algorithm;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import Network.*;

/**
 * 
 * The random algorithm selects the next node to traverse
 * to randomly.
 *
 */
public class RandomAlgorithm implements Algorithm {
	private Network network;	//Network of nodes the algorithm is running on
	private Random random;		//Random instance for selecting next node
	private Node start_n;		//Start node in the network
	private Node end_n;		//End node in the network
	private int packet_count; 	//Number of packets transmitted during message sending
	private int rate;			//this is the rate at which new packets are created and transmitted
	
	/**
	 * Constructor to assign network to the algorithm along with
	 * the start and end nodes.
	 */
	public RandomAlgorithm(Network n) {
		
		//Assign network we will run algorithm on
		this.network = n;
		
		//Initialize the random class
		this.random = new Random();
		
	}
	
	/**
	 * Traverses the network of nodes beginning at the start node
	 * and ending at the end node specified in the constructor.
	 */
	
	public boolean run(Message m, int rate) {

		//Get the messages start node
		this.start_n = m.getSource();
		
		//Get the messages end node
		this.end_n = m.getDestination();
		
		//Set the current node as our start node
		Node current_n = this.start_n;

		//Loop until we reach the end node
		do {
			
			//Select the next node to traverse to
			Node new_n = this.next(current_n);
			
			//Display debug
			System.out.println("Going from " + current_n + " to " + new_n);
			
			//Assign chosen node to current node
			current_n = new_n;
			
			//A packet was transferred
			this.countPacket();
			
			//to increment the hop count messages have gone through
			m.countHop();
			
		} while (!current_n.equals(end_n));
		
		//Done
		return true;
	}
	
	/**
	 * Selects the next node to travel to and returns it.
	 */
	public Node next(Node n) {
		
		//If the node isn't present in the network return				
		if (!network.contains(n)) { System.out.println("Does not contain node " + n.getName()); return null; }
		
		//Get the nodes neighbors
		HashSet<Node> neighbors;
		neighbors = n.getNeighbors();
		
		//Get amount of neighbors
		int neighbor_count = neighbors.size();
		
		//Select random neighbor index
		int random_neighbor = random.nextInt(neighbor_count);
		
		//Fetch the random neighbor and return it
		int i = 0;
		for(Node rn: neighbors) {
		    if (i == random_neighbor)
		        return rn;
		    i = i + 1;
		}
		
		//We should not get to this point
		System.out.println("Something went wrong choosing the random node");
		return null;
	}
	
	/**
	 * Increment the counter for number of packets transmitted
	 * during message sending.
	 */
	public void countPacket() {
		
		//Increment packets counter
		this.packet_count ++;
		
	}
	
	/**
	 * Returns number of packets transmitted during message 
	 * sending.
	 */
	public int getPacketCount() {
		
		//Increment packets counter
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
		
		Message m = new Message("Message contents", n1, n2);
		boolean value = algo.run(m, 3);
		System.out.println("Packets sent during transmission: " + algo.getPacketCount());
		System.out.println("true or false " + value);
	}

	

}
