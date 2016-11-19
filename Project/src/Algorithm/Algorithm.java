package Algorithm;


import Network.Message; 
import Network.Node; 

/**
 * 
 * Interface for each routing algorithm
 * Author Richard Hanton
 */
public abstract class Algorithm {
	
	/**
	 * Run method which moves the specified message from the source
	 * to its destination using the algorithms method for selecting 
	 * the next node.
	 * @param m
	 * @param rate
	 */
	public abstract boolean run(Message m, int rate);
	
	/**
	 * Performs a simulation step on the messages within the network
	 */
	protected abstract boolean step();
	
	/**
	 * Returns the next node to travel to basd on the message
	 */
	protected abstract Node next(Message m);	
	
	/**
	 * Counts a packet transfer in the network when moving a message
	 * from its source to its destination.
	 */
	public abstract void countPacket();
	
	/**
	 * Returns the total number of packets transmitted at a given 
	 * time for a message going from its source to destination.
	 */
	public abstract int getPacketCount();
	
}
