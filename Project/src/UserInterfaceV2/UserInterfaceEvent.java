package UserInterfaceV2;

import Network.*;

import java.util.EventObject;

public class UserInterfaceEvent extends EventObject {
	
	private Network network;
	private boolean success = false;
	private String type;
	private String message;
	
	public UserInterfaceEvent(Object arg0) {
		super(arg0);
		
	}
	
	/**
	 * Specify network in event sent back
	 */
	public void setNetwork(Network network) {
		
		//Set network
		this.network = network;
		
	}
	
	/**
	 * Return the network
	 */
	public Network getNetwork() {
		
		//Return network
		return this.network;
		
	}
	
	/**
	 * Set whether action was successful or not
	 */
	public void setSuccess(boolean success) {
		
		//Set success value
		this.success = success;
		
	}

	/**
	 * Returns whether request was successful or not
	 */
	public boolean getSuccess() {
		
		//Return success status
		return this.success;
		
	}
	
	/**
	 * Set the type of event returning
	 */
	public void setType(String t) {
		
		//Set type
		this.type = t;
		
	}
	
	/**
	 * Get the type of event returning
	 */
	public String getType() {
		
		//Return type
		return this.type;
		
	}
	
	/**
	 * Set the message of event returning
	 */
	public void setMessage(String m) {
		
		//Set message
		this.message = m;
		
	}
	
	/**
	 * Get the message of event returning
	 */
	public String getMessage() {
		
		//Return message
		return this.message;
		
	}	
}
