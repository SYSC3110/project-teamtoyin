package Algorithm;
import java.util.HashSet;
import java.util.Random;
import Network.Network;

/**
 * 
 * The random algorithm selects the next node to traverse
 * to randomly.
 *
 */
public class RandomAlgorithm implements Algorithm {
	private Network network;	//Network of nodes the algorithm is running on
	private Random random;		//Random instance for selecting next node
	private String start_n;		//Start node in the network
	private String end_n;		//End node in the network
	
	/**
	 * Constructor to assign network to the algorithm along with
	 * the start and end nodes.
	 */
	public RandomAlgorithm(Network n, String start, String end) {
		
		//Assign network we will run algorithm on
		this.network = n;
		
		//Network node we begin at
		this.start_n = start;
		
		//Network node we traverse to
		this.end_n = end;
		
		//Initialize the random class
		this.random = new Random();
		
	}
	
	/**
	 * Traverses the network of nodes beginning at the start node
	 * and ending at the end node specified in the constructor.
	 */
	public void run() {
		
		//Set the current node as our start node
		String current_n = this.start_n;
		
		//Loop until we reach the end node
		do {
			
			//Select the next node to traverse to
			String new_n = this.next(current_n);
			
			//Display debug
			System.out.println("Going from " + current_n + " to " + new_n);
			
			//Assign chosen node to current node
			current_n = new_n;
			
		} while (current_n != end_n);
		
		//Done
		
	}
	
	/**
	 * Selects the next node to travel to and returns it.
	 */
	public String next(String n) {
		
		//If the node isn't present in the network return				
		if (!network.contains(n)) { System.out.println("Does not contain " + n); return null; }
		
		//Get the nodes neighbors
		HashSet<String> neighbors;
		neighbors = network.getNeighbors(n);
		
		//Get amount of neighbors
		int neighbor_count = neighbors.size();
		
		//Select random neighbor index
		int random_neighbor = random.nextInt(neighbor_count);
		
		//Fetch the random neighbor and return it
		int i = 0;
		for(String rn : neighbors) {
		    if (i == random_neighbor)
		        return rn;
		    i = i + 1;
		}
		
		//We should not get to this point
		System.out.println("Something went wrong choosing the random node");
		return null;
	}
	
	/**
	 * Testing
	 */
	public static void main(String[] args) {
		
		Network n = new Network();
		n.add("A");
		n.add("B");
		n.add("C");
		n.add("D");
		
		n.link("A", "B");
		n.link("B", "C");
		n.link("C", "D");
		n.link("B", "D");
		
		RandomAlgorithm algo = new RandomAlgorithm(n, "A", "D");
		algo.run();
		
	}

}
