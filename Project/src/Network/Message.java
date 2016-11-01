/**
 * 
 */
package Network;

/**
 * @author Lina El Sadek
 *  
 * 
 */
// Modified October 18th 2016 <Scott Hanton> - Changed to account for hops a message goes through

public class Message {
	
	private String contents;	//Message contents
	private Node destination;	//Message destination node
	private Node source;		//Message source node
	private int hops;			//Hops a message has taken in the network
	
	/**
	 * The constructor is meant to create a message to be sent to a node
	 * @param contents
	 * @param destination
	 * @param source
	 */
	public Message (String contents, Node source, Node destination) {
		
		//Set message contents
		this.contents = contents;
		
		//Set message source node
		this.source = source;
		
		//Set message destination node
		this.destination = destination;
		
	}
	
	/**
	 * Returns the source node for a message
	 * @return
	 */
	public Node getSource() {
		
		//Return source node
		return this.source;
	}
	
	/**
	 * Sets the source node for the message 
	 * @param n
	 */
	public void setSource(Node n) {
		
		//If source string not empty or null
		if(n != null) {
			
			//Set message source node
			this.source = n;
			
		}
	}
	
	/**
	 * Returns the destination node for a message
	 */
	public Node getDestination() {
		
		//Return message destination
		return this.destination;
	}
	
	/**
	 * Returns the message contents
	 */
	public String getContents() {
		
		//Return message contents
		return this.contents;
	}
	
	/**
	 * Increments the number of hops a message has taken
	 * within the network
	 */
	public void countHop() {
		
		//Increment hops taken;
		this.hops ++;
		
	}
	
	/**
	 * Returns the number of hops a message has taken
	 * within the network.
	 */
	public int getHopCount() {
		
		//Return number of hops taken
		return this.hops;
		
	}
}
