package UserInterface;

public enum UICommands {
	Reset, fromXML, Undo, toXML, Help, RandomAlgorithm, FloodingAlgorithm, GetNumberOfNodes, GetRateNumber, NodeNameInserted, FirstEdgeInserted, SecondEdgeInserted, MessageContent,
	StartNodeEntered, EndNodeEntered;


	public String getCommand(){
		return this.toString();
	}
}
