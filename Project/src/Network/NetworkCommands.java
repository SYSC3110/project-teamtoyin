package Network;

public enum NetworkCommands {
	InvalidNodeName, NodeExists, NodeEmpty, NodeDoesNotExist, SetEditableNextNode, NodeNumAvailable;
	
	public String getCommand(){
		return this.toString();
	}
}
