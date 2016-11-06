package Network;

public enum NetworkCommands {
	InvalidNodeName, NodeExists, NodeEmpty, NodeDoesNotExist, SetEditableNextNode, NodeNumAvailable, CreateMessageFields;
	
	public String getCommand(){
		return this.toString();
	}
}
