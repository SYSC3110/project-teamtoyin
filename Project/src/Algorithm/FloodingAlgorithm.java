package Algorithm;

import java.util.ArrayList;
import java.util.HashSet;
/*
 * Author Toyin Odujebe
 */

import Network.Message;
import Network.Network;

public class FloodingAlgorithm implements Algorithm {
	private Network network;	//Network of nodes the algorithm is running on
	private String start_n;		//represents the start node of the message
	private String end_n;		//End node in the network
	private String curr_Node;
	private int neighboursSize;	//represents the number of neighbours a node has
	private ArrayList<String> nextNodes;	//an array list of all the next ndoes from a current node
	int count, count2, packetCounter;
	private HashSet<String> neighbors;
	private HashSet<String> neighbors1;
	
	public FloodingAlgorithm(Network n){
		this.network = n;
	}
	
	public boolean run(Message m, int run) {
		// TODO Auto-generated method stub
		//Get the messages start node
		this.start_n = m.getSource();
		this.end_n = m.getDestination();
		String currentNode = start_n;
		count = 0;
		//Get the nodes neighbors
		//HashSet<String> neighbors;
		//HashSet<String> neighbors1;
		neighbors = network.getNeighbors(start_n);
		this.user(neighbors, currentNode);
		System.out.println("Toyins network");
		while(this.curr_Node != this.end_n){
			forwardPacket(neighbors);
		}
		
		return false;
	}

	private void forwardPacket(HashSet<String> neighbors) {
		HashSet<String> neighbors1;
		for (String str: neighbors){
			int value = 0;
			this.curr_Node = str;
			do{
				value++;
				neighbors1 = network.getNeighbors(str);
				this.user(neighbors1, str);
			}while(value != neighbors1.size());
		}

	}

	public String next(String n) {
		return null;
	}

	public void countPacket() {
		// TODO Auto-generated method stub
		count++;
		packetCounter++;
		
	}

	public int getPacketCount() {
		// TODO Auto-generated method stub
		return this.packetCounter;
	}
	public void user(HashSet<String> neighbors2, String currentNode){
		//nextNodes = new ArrayList<String>();
		this.curr_Node = currentNode;
		int counter = 0;
		neighbors2 = network.getNeighbors(currentNode);
		for (String str: neighbors2){
			counter++;
			String newNode = str;
			System.out.println("From "+ currentNode + " to " + newNode);
			//System.out.println(node);
			//currentNode = newNode;
			countPacket();
			if(this.count == neighbors2.size()){
				System.out.println("Count value: " + count);
				System.out.println("neighbours size: "+ neighbors2.size());
				this.count = 0;
				System.out.println("Hey");
				//currentNode = newNode;
			}
		}
		System.out.println("Count value: " + count);
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
