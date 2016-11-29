package Algorithm;


import java.util.ArrayList;

import Network.Message;
import Network.Network;
import Network.Node; 

/**
 * 
 * Interface for each routing algorithm
 * Author Richard Hanton
 * Modified by:Lina El Sadek
 * Modified: Osama Buhamad
 */
public abstract class Algorithm {
	
	protected int packet_count;
	protected Network network;
	private int max_injections = 20;
	private ArrayList<String> info = new ArrayList<String>();
	private String infoStr="";
	
	/**
	 * Run method which moves the specified message from the source
	 * to its destination using the algorithms method for selecting 
	 * the next node.
	 * @param m
	 * @param rate
	 */
	public boolean run(Message m, int rate) {
		
		int count = 0;		//Counter for step while loop
		int injected = 0;	//Counter for new message injections
		Message new_m;		//New message to inject into the network
		
		//Inject message into network
		network.injectMessage(m);
		
		//If the rate is 0, the network is closed for new messages
		if (rate <= 0) {
			
			//Set network to closed
			network.setOpen(false);
			
		}
		
		//While the network is good to go
		while (step()) {
			
			//If we should inject a new message 
			if ( ( rate != 0 ) && ( (count % rate) == 0 ) && ( injected < this.max_injections ) ) {
	
				//Create a new message
				new_m = new Message(m.getContents() + " - " + count, m.getSource(), m.getDestination());
				
				//Inject message into network
				network.injectMessage(new_m);
				
				//Increment injected counter
				injected ++;
				
			} else if (rate > 0 && injected >= this.max_injections) {
				
				//Network not open for new messages
				network.setOpen(false);
				
			}
			
			//Step again until no more stepping required
			getInfo().add("Stepping again...\n");
			System.out.println("Stepping again...");
			
			//Increment counter
			count++;
			
		}
		
		//Algorithm successfully ran if we reach here
		return true;
		
	}
	
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
	protected void countPacket() {
	
		// Increment packets counter
		this.packet_count++;
	
	}
	
	/**
	 * Returns the total number of packets transmitted at a given 
	 * time for a message going from its source to destination.
	 */
	public int getPacketCount() {
	
		// Increment packets counter
		return this.packet_count;
	
	}	
	
	/**
	 * return a string of the information about the algorithm 
	 * will be called in the model, so the information can be shown in the GUI
	 * @return
	 */
	public ArrayList<String> getInfo(){
		return info;
	}
	
	/**
	 * get a string of the list of info 
	 * @return
	 */
	public String getInfoStr(){
		for(String s: info){
			infoStr=infoStr+s+" ";
		}
		return infoStr;
	}
	
	
}
