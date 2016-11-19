package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;

import Network.Message;
import Network.Network;
import Network.Node;

public class DepthFirst extends Algorithm {
	private ArrayList<Node> visited;
	/**
	 * Constructor to assign network to the algorithm.
	 */
	public DepthFirst(Network network) throws NullPointerException{
		
		//If network is null throw exception
		if (network == null)
			throw new NullPointerException();
		else
			this.network = network;

		// Initialize packet_count
		this.packet_count = 0;

	}
	

	@Override
	protected boolean step() {
		// TODO Auto-generated method stub

		int index = 0; 	// Index in arraylist of messages
		Node new_n; 	// New node to move message to

		//If no more messages travelling in network and the network is not receiving new messages
		if (!network.messagesMoving() && !network.isOpen()) {
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

	@Override
	protected Node next(Message m) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
