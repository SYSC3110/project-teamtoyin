package UserInterface;

public enum UICommands {
	Reset, fromXML, Undo, toXML, Help, RandomAlgorithm, FloodingAlgorithm, GetNumberOfNodes, GetRateNumber, NodeNameInserted, FirstEdgeInserted, SecondEdgeInserted, MessageContent,
	StartNodeEntered, EndNodeEntered, ShowTopology, RunAlgorithm;


	public String getCommand(){
		return this.toString();
	}
}
