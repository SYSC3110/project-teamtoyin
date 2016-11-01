package UserInterface;

public enum UICCommands {
	GetNumberOfNodes, GetRateNumber, NodeNameInserted, FirstEdgeInserted, SecondEdgeInserted, RandomAlgorithm, FloodingAlgorithm;
	
	public String getCommand(){
		return this.toString();
	}
}
