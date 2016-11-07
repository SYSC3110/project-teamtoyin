package Network;

/**
 * 
 * @author Toyin Odujebe
 *
 */
public enum NetworkCommands {
	InvalidNodeName, NodeExists, NodeEmpty, NodeDoesNotExist, SetEditableNextNode, NodeNumAvailable, CreateMessageFields;
	
	public String getCommand(){
		return this.toString();
	}
}
