package UserInterface;

public enum UICommands {
	Reset, fromXML, Undo, toXML, Help, RandomAlgorithm, FloodingAlgorithm, GetNumberOfNodes, GetRateNumber, NodeNameInserted, FirstEdgeInserted, SecondEdgeInserted;


	public String getCommand(){
		return this.toString();
	}
}
