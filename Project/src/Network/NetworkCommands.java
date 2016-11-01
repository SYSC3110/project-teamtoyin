package Network;

public enum NetworkCommands {
	InvalidNodeName, NodeExists, NodeEmpty, NodeDoesNotExist, SetEditableNextNode;
	
	public String getCommand(){
		return this.toString();
	}
}
