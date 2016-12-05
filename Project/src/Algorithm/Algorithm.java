package Algorithm;


import java.util.ArrayList;
import java.util.Stack;

import Network.Message;
import Network.Network;
import Network.Node;
import UserInterfaceV2.UserInterfaceView; 

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
	private ArrayList<String> backinfo = new ArrayList<String>();
	
	private String infoStr="";
	private String stpinfoStr="";
	int count = 0;			//Counter for step while loop
	int injected = 0;		//Counter for new message injections
	int rate = 0;			//Rate at which new messages are injected into the network
	Message m_original;		//Original message passed into the network
	private boolean flag = true;
	
	/**
	 * Run method which moves the specified message from the source
	 * to its destination using the algorithms method for selecting 
	 * the next node.
	 * @param m
	 * @param rate
	 */
	public boolean run(Message m, int rate) {
		
		Message new_m;		//New message to inject into the network
		
		//Specify rate globally
		this.rate = rate;
		
		//Specify the message globally
		this.m_original = m;
		
		//Inject message into network
		network.injectMessage(this.m_original);
		
		//If the rate is 0, the network is closed for new messages
		if (this.rate <= 0) {
			
			//Set network to closed
			network.setOpen(false);
			
		}
		
		//While the network is good to go
		while (step()) {
			
			//If we should inject a new message 
			if ( ( this.rate != 0 ) && ( (this.count % this.rate) == 0 ) && ( this.injected < this.max_injections ) ) {
	
				//Create a new message
				new_m = new Message(this.m_original.getContents() + " - " + this.count, this.m_original.getSource(), this.m_original.getDestination());
				
				//Inject message into network
				network.injectMessage(new_m);
				
				//Increment injected counter
				this.injected ++;
				
			} else if (this.rate > 0 && injected >= this.max_injections) {
				
				//Network not open for new messages
				network.setOpen(false);
				
			}
			
			//Step again until no more stepping required
			getInfo().add("Stepping again...\n");
			System.out.println("Stepping again...");
			
			//Increment counter
			this.count++;
			
		}
		
		//Algorithm successfully ran if we reach here
		return true;
		
	}
	
	/**
	 * Initializes stepping which moves the specified message from the source
	 * to its destination using the algorithms method for selecting 
	 * the next node, allows stepping through the process.
	 * @param m
	 * @param rate
	 */
	public void stepper_initialize(Message m, int rate) {
				
		//Specify rate globlaly
		this.rate = rate;		
		
		//Specify the message globally
		this.m_original = m;
				
		//Inject message into network
		network.injectMessage(this.m_original);
		
		//If the rate is 0, the network is closed for new messages
		if (this.rate <= 0) {
			
			//Set network to closed
			network.setOpen(false);
			
		}
		
	}
	
	/**
	 * Performs a single step of moving the message from the source
	 * to the destination, allowing the user to see the message moving
	 * on the topography.
	 */
	public boolean stepper() {
		
		Message new_m;		//New message to inject into the network

		//If we moved the message to the next node
		if (step()) {
			
			//If we should inject a new message 
			if ( ( this.rate != 0 ) && ( (this.count % this.rate) == 0 ) && ( this.injected < this.max_injections ) ) {
	
				//Create a new message
				new_m = new Message(this.m_original.getContents() + " - " + this.count, this.m_original.getSource(), this.m_original.getDestination());
				
				//Inject message into network
				network.injectMessage(new_m);
				
				//Increment injected counter
				this.injected ++;
				
			} else if (this.rate > 0 && injected >= this.max_injections) {
				
				//Network not open for new messages
				network.setOpen(false);
				
			}
			
			//Step again until no more stepping required
			getInfo().add("Stepping again...\n");
			System.out.println("Stepping again...");
			
			//Increment counter
			this.count++;
			
			//Success moved message to next node(s)
			return true;
			
		//Nothing else to do, we're done
		} else {
			
			//Process done, return false
			return false;
			
		}
	}
	
	
	/**
	 * Performs a simulation step on the messages within the network
	 */
	protected abstract boolean step();
	
	/**
	 * Returns the next node to travel to based on the message
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
	
	/**
	 * Remove the last message when step back is performed 
	 */
	public String removeLastMessage(){
				stpinfoStr="";

				if(backinfo.size()>2){
					backinfo.remove(backinfo.size()-2);
					backinfo.remove(backinfo.size()-1);
					for(String s: backinfo){
						stpinfoStr+=s;
					}
				}
				return stpinfoStr;
	}
	/*
	 * Updates the list when stepping based on if you have stepped back previously or not
	 */
	public String updateInfo(){
		stpinfoStr="";
		if(flag){
			for(int i=0;i<=info.size()-2;i++){
				backinfo.add(info.get(i));
				stpinfoStr+=backinfo.get(i);
			}
			flag = false;
		}
		else{
			
			backinfo.add(info.get(info.size()-3));
			backinfo.add(info.get(info.size()-2));
			for(String s: backinfo){
				stpinfoStr+=s;
			}
		}
		return stpinfoStr;
	}
	
	/*
	 * Reset everything when End simulation is clicked
	 */
	public void topologyReseter(){
		this.info.clear();
		this.backinfo.clear();
		this.stpinfoStr = "";
		this.infoStr = "";
		this.flag = true;
	}
	
	
}
