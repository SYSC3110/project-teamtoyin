package Algorithm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import Network.*;

/**
 * @author Richard Hanton
 * 
 * Modified By: Lina El Sadek 
 * Modified By Osama Buahamd
 * 
 * The random algorithm selects the next node to
 * traverse to randomly.
 */
public class RandomAlgorithm extends Algorithm {
	private Random random; 				// Random instance for selecting next node
	
	/**
	 * Constructor to assign network to the algorithm.
	 */
	public RandomAlgorithm(Network n) throws NullPointerException{

		//If network is null throw exception
		if (n == null)
			throw new NullPointerException();
		else
			this.network = n;

		// Initialize the random class
		this.random = new Random();

		// Initialize packet_count
		this.packet_count = 0;	
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
		
		//Get messages current node
		Node n = m.getNode();
		
		// If the node isn't present in the network return
		if (!network.contains(n)) {
			getInfo().add("Does not contain node " + n.getName()+"\n");
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
		getInfo().add("Something went wrong choosing the random node\n");
		System.out.println("Something went wrong choosing the random node");
		return null;

	}

}
