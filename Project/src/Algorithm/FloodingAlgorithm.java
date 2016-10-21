package Algorithm;

import java.util.ArrayList;
import java.util.HashSet;

import Network.Message;
import Network.Network;

public class FloodingAlgorithm implements Algorithm {
	private Network network;	//Network of nodes the algorithm is running on
	private String start_n;		//represents the start node of the message
	private String end_n;		//End node in the network
	private int neighboursSize;	//represents the number of neighbours a node has

	public FloodingAlgorithm(Network n){
		this.network = n;
	}
	
	public void run(Message m, int run) {
		// TODO Auto-generated method stub
		//Get the messages start node
		this.start_n = m.getSource();
		this.end_n = m.getDestination();
		String currentNode = start_n;
		int count = 0;
		//Get the nodes neighbors
		HashSet<String> neighbors;
		neighbors = network.getNeighbors(start_n);
		/*for (String str: neighbors){
			do{
				this.user(neighbors, currentNode, count, start_n);
			}while
		}*/
		this.user(neighbors, currentNode, count, start_n);
	}

	public String next(String n) {
		return null;
	}

	public void countPacket() {
		// TODO Auto-generated method stub
		
	}

	public int getPacketCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void user(HashSet<String> neighbors2, String currentNode, int count, String node){
		neighbors2 = network.getNeighbors(node);
		for (String str: neighbors2){
			String newNode = str;
			System.out.println("From "+ currentNode + " to " + newNode);
			//currentNode = newNode;
			count++;
			if(count == neighbors2.size()){
				currentNode = newNode;
			}
		}
	}
	
	public static void main(String[] args) {
		
		Network n = new Network();
		n.add("A");
		n.add("B");
		n.add("C");
		n.add("D");
		n.add("E");
		
		n.link("A", "B");
		n.link("A", "C");
		n.link("A", "D");
		n.link("C", "B");
		n.link("B", "D");
		n.link("B", "E");
		n.link("D", "E");
		
		FloodingAlgorithm algo = new FloodingAlgorithm(n);
		
		Message m = new Message("Message contents", "A", "D");
		algo.run(m, 5);
		System.out.println("Packets sent during transmission: " + algo.getPacketCount());
		
	}


}
