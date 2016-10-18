package Algorithm;
import Network.Message; 

/**
 * 
 * Interface for each routing algorithm
 *
 */
public interface Algorithm {
	
	/**
	 * Run method which moves the specified message from the source
	 * to its destination using the algorithms method for selecting 
	 * the next node.
	 * @param m
	 */
	public void run(Message m);
	
	/**
	 * Returns the next node to travel to based on the current node.
	 * @param n
	 */
	public String next(String n);
	
	/**
	 * Counts a packet transfer in the network when moving a message
	 * from its source to its destination.
	 */
	public void countPacket();
	
	/**
	 * Returns the total number of packets transmitted at a given 
	 * time for a message going from its source to destination.
	 */
	public int getPacketCount();
	
}
