package UserInterface;
/**
 * 
 * @author Toyin Odujebe
 * Modified Lina El Sadek
 *
 */
public enum UICCommands {
	GetNumberOfNodes, GetRateNumber, NodeNameInserted, FirstEdgeInserted, SecondEdgeInserted, RandomAlgorithm, FloodingAlgorithm;
	
	public String getCommand(){
		return this.toString();
	}
}
