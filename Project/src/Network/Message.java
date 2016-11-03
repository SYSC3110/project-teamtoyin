package Network;

import java.util.ArrayList;

/**
 * @author Lina El Sadek
 *  
 * 
 */
// Modified October 18th 2016 <Scott Hanton> - Changed to account for hops a message goes through

public class Message {
	
	private String contents;			//Message contents
	private Node destination;			//Message destination node
	private Node source;				//Message source node
	private Node position;				//Node the message is currently at
	private int hops;					//Hops a message has taken in the network
	private ArrayList<Node> history;	//Stores the messages node traveling history 
	
	/**
	 * The constructor is meant to create a message to be sent to a node
	 * @param contents
	 * @param destination
	 * @param source
	 */
	public Message (String contents, Node source, Node destination) {
		
		//Set message contents
		if(contents == null)
			this.contents = "";
		else
			this.contents = contents;
		
		//Set message source node
		if(source == null) {
			throw new NullPointerException();
		} else {
			this.source = source;
			this.position = source;
		}
		
		//Set message destination node
		if(destination == null)
			throw new NullPointerException();
		else
			this.destination = destination;
		
		//Initialize number of hops to 0
		hops = 0;
		
		//Initialize history ArrayList
		history = new ArrayList<Node>();
		
	}
	
	/**
	 * A constructor to create a new message which is a copy of an 
	 * existing message
	 */
	public Message (Message m) {
		
		//Check m is null
		if (m == null) {
			throw new NullPointerException();
		}
		
		//Duplicate message contents
		this.contents = m.getContents();
		
		//Duplicate message destination
		this.destination = m.getDestination();
		
		//Duplicate message source
		this.source = m.getSource();
		
		//Duplicate message position
		this.position = m.getNode();
		
		//Duplicate message hops
		this.hops = m.getHopCount();
		
		//Duplicate message history
		this.history = new ArrayList<Node>(m.getHistory());
		
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
	 * Returns the node the message is currently on
	 * @return
	 */
	public Node getNode() {
		
		//Return node the message is at
		return this.position;
	}
	
	/**
	 * Sets the node the message is currently on
	 * @param n
	 */
	public void setNode(Node n) {
		
		//If node is not null
		if(n != null) {
			
			//Save current node in history
			this.history.add(this.position);
			
			System.out.println("Added " + this.position.getName() + " to my history because moving forward");
			
			//Set messages position in the network
			this.position = n;
			
		}
	}
	
	/**
	 * Adds to a nodes history
	 */
	public void addHistory(Node n) {
		
		//Check if n is null
		if (n == null) { return; }
		
		//Add to history
		this.history.add(n);
		
		return;
		
	}
	
	/**
	 * Sets the destination node for the message
	 * @param str
	 */
	public void setDestination(Node n)
	{
		//If destination string not empty or null
		if(n != null ) {
			
			//Set message destination node
			this.destination = n;
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
	
	/**
	 * Returns the nodes history
	 */
	public ArrayList<Node> getHistory() {
		
		//Return history ArrayList
		return this.history;
		
	}
}
