package Algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import Network.Message;
import Network.Network;
import Network.Node; 

/*
 * Author Toyin Odujebe
 */

public class FloodingAlgorithm implements Algorithm {
	private Network network;	//Network of nodes the algorithm is running on
	private Node start_n;		//represents the start node of the message
	private Node end_n;		//End node in the network
	private Node curr_Node;
	private int neighboursSize;	//represents the number of neighbours a node has
	private ArrayList<Node> nextNodes;	//an array list of all the next ndoes from a current node
	int count, count2, packetCounter;
	private HashSet<Node> neighbors;
	private HashSet<Node> neighbors1;
	
	public FloodingAlgorithm(Network n){
		this.network = n;
	}
	
	public boolean run(Message m, int run) {
		// TODO Auto-generated method stub
		//Get the messages start node
		this.start_n = m.getSource();
		this.end_n = m.getDestination();
		Node currentNode = start_n;
		count = 0;
		//Get the nodes neighbors
		//HashSet<String> neighbors;
		//HashSet<String> neighbors1;
		neighbors = start_n.getNeighbors();
		this.user(neighbors, currentNode);
		System.out.println("Toyins network");
		while(this.curr_Node != this.end_n){
			forwardPacket(neighbors);
		}
		
		return false;
	}

	private void forwardPacket(HashSet<Node> neighbors) {
		HashSet<Node> neighbors1;
		for (Node n: neighbors){
			int value = 0;
			this.curr_Node = n;
			do{
				value++;
				neighbors1 = n.getNeighbors();
				this.user(neighbors1, n);
			}while(value != neighbors1.size());
		}

	}

	public Node next(Node n) {
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
	
	public void user(HashSet<Node> neighbors2, Node currentNode){
		//nextNodes = new ArrayList<String>();
		this.curr_Node = currentNode;
		int counter = 0;
		neighbors2 = currentNode.getNeighbors();
		for (Node n: neighbors2){
			counter++;
			Node newNode = n;
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
		Node n1 = new Node("A");
		Node n2 = new Node("B");
		Node n3 = new Node("C");
		Node n4 = new Node("D");
		Node n5 = new Node("E");
		
		n.add(n1);
		n.add(n2);
		n.add(n3);
		n.add(n4);
		n.add(n5);
		
		n.link(n1, n2);
		n.link(n1, n2);
		n.link(n1, n3);
		n.link(n2, n3);
		n.link(n2, n4);
		n.link(n2, n5);
		n.link(n4, n5);
		
		FloodingAlgorithm algo = new FloodingAlgorithm(n);
		
		Message m = new Message("Message contents", n1, n5);
		algo.run(m, 5);
		System.out.println("Packets sent during transmission: " + algo.getPacketCount());
		
	}


}
