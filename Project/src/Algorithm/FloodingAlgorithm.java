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
 * Modified Osama Buhamad
 */

public class FloodingAlgorithm extends Algorithm {
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
	 * Performs a simulation step, moving messages to the next node and
	 * injecting new messages as required. Returns false when there is no
	 * further step to take.
	 */
	@Override
	protected boolean step() {
		
		Node new_n; 				//New node to move message to
		Message new_m;				//New message created to flood out
		ArrayList<Message> messages;//New list of messages for the network
		
		//Initialize arraylist of messages
		messages = new ArrayList<Message>();
		
		//If no more messages travelling in network and the network is not receiving new messages
		if (!network.messagesMoving() && !network.isOpen()) {
			
			getInfo().add(" No messages moving and network closed for new messages.\n");
			System.out.println("No messages moving and network closed for new messages.");
			
			return false;
		}

		//Iterator for messages in network
		Iterator<Message> i = network.getMessages().iterator();
		
		//For each message in network
		while (i.hasNext()) {
			
			getInfo().add("Message under considering...\n");
			System.out.println("Message under considering...");
			
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
			
			//Send to all neighbors that havn't received it yet
			while (new_n != null) {
								
				//Create a copy of the message
				new_m = new Message(m);
				
				//Move the message to the next node
				new_m.setNode(new_n, true);
				
				//Count a hop for this new message
				new_m.countHop();
				
				//Add to list of updated network messages
				messages.add(new_m);
				
				//Add new node to current messages history so we dont send another message there
				m.addHistory(new_n);
				
				//Increment packets sent
				this.countPacket();
				
				//Debug
				getInfo().add("Copy of message going from " + m.getNode().getName() + " to " + new_m.getNode().getName()+"\n");
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

		// Fetch the random neighbor and return it
		for (Node neighbor_n : neighbors) {
			
			//If we havn't visited this node
			if (!m.getHistory().contains(neighbor_n)) {
				return neighbor_n;
			} else {
				getInfo().add("Message history contains " + neighbor_n.getName()+"\n");
				System.out.println("Message history contains " + neighbor_n.getName());
			}
		}

		// We should not get to this point
		getInfo().add("No new neighbor nodes to send this message to \n");
		System.out.println("No new neighbor nodes to send this message to");
		
		return null;
		
	}
}
