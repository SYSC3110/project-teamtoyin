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
	private String destination;	//Message destination node
	private String source;		//Message source node
	private int hops;			//Hops a message has taken in the network
	
	/**
	 * The constructor is meant to create a message to be sent to a node
	 * @param contents
	 * @param destination
	 * @param source
	 */
	public Message (String contents, String source, String destination) {
		
		//Set message contents
		if(contents == null)
			this.contents = "";
		else
			this.contents = contents;
		
		//Set message source node
		if(source == null)
			this.source = "";
		else
			this.source = source;
		
		//Set message destination node
		if(destination == null)
			this.destination = "";
		else
			this.destination = destination;
		
		//Initialize number of hops to 0
		hops = 0;
		
	}
	
	/**
	 * Returns the source node for a message
	 * @return
	 */
	public String getSource() {
		
		//Return source node
		return this.source;
	}
	
	/**
	 * Sets the source node for the message 
	 * @param n
	 */
	public void setSource(String n) {
		
		//If source string not empty or null
		if(n != null && n !="") {
			
			//Set message source node
			this.source = n.toUpperCase();
			
		}
	}
	
	/**
	 * Sets the destination node for the message
	 * @param str
	 */
	public void setDestination(String str)
	{
		//If destination string not empty or null
		if(str != null && str !="") {
			
			//Set message destination node
			this.destination = str.toUpperCase();
		}
	}
	
	/**
	 * Returns the destination node for a message
	 */
	public String getDestination() {
		
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
	
	/**
	 * Sets the contents of the message
	 * @param str
	 */
	public void setContent(String str)
	{
		if(str != null && str !="") {
			contents = str;
		}
		
	}
}
