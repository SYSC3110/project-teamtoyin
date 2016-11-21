package Algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Network.Message;
import Network.Network;
import Network.Node;
/*
 * Author: Toyin Odujebe
 */
public class DepthFirstAlgorithm extends Algorithm {
	private ArrayList<Node> visited;
	private ArrayList<Node> traverseList;
	
	public DepthFirstAlgorithm(Network network) throws NullPointerException{
		//If network is null throw exception
		if (network == null)
			throw new NullPointerException();
		else
			this.network = network;
		
		// Initialize packet_count
		this.packet_count = 0;
		
		this.traverseList = new ArrayList<Node>();
		this.visited = new ArrayList<Node>();
		
		//Node node = network.getNodes().iterator().next();
		//this.dfs(node.getNeighbors(), node);
		//this.dfs(neighbors, node);
	}
	
	public void dfs(HashSet<Node> neighbors, Node node){
		//Node dest = node2;
		System.out.print(node.getName() + "\t");
		
		//Add present node to the list of nodes that are been traversed
		this.getTraverseList().add(node);
		visited.add(node);
		//Get each node in the neighbors list
		for (Node n : neighbors) {
			//System.out.println(n.data);
			//if the node is not null and it is not contained in the visited list then proceed
			if(n!=null && !visited.contains(n)){
				//call the dfs method on that node for recursion 
				dfs(n.getNeighbors(),n);
				//add the node to the visited list
				//visited.add(n);
			}
		}
	}
	
	//Returns the traverse list to be used in the next method
	public ArrayList<Node> getTraverseList() {
		return this.traverseList;
	}
	
	
	@Override
	protected boolean step() {
		int index = 0; 	// Index in arraylist of messages
		Node new_n; 	// New node to move message to

		//If no more messages travelling in network and the network is not receiving new messages
		if (!network.messagesMoving() && !network.isOpen()) {
			getInfo().add("No messages moving and network closed for new messages.\n");
			System.out.println("No messages moving and network closed for new messages.");
			return false;
		}

		//Iterator for messages in network
		Iterator<Message> i = network.getMessages().iterator();
		
		//For each message in network
		while (i.hasNext()) {
			
			//Get message
			Message m = i.next();
			
			// If the message is already at its destination, skip
			if (m.getNode() == m.getDestination()) {
				
				//Debug
				getInfo().add(m.getContents() + " is at the destination node " + m.getNode().getName()+"\n");
				System.out.println(m.getContents() + " is at the destination node " + m.getNode().getName());
				
				//Node is at destination so remove it
				i.remove();
								
				//Continue to next message
				continue;
				
			} else {
				
				//Debug
				getInfo().add(m.getContents() + " is at node " + m.getNode().getName() + " and is going to node " + m.getDestination().getName()+"\n");
				System.out.println(m.getContents() + " is at node " + m.getNode().getName() + " and is going to node " + m.getDestination().getName());
			
			}

			// Get node to move message to
			new_n = this.next(m);
			
			//Update the messages position in the network
			network.getMessages().get(index).setNode(new_n);
			
			//Increment packets sent
			this.countPacket();
			
			//Count message hops
			network.getMessages().get(index).countHop();
			
			//Increment index counter for messages arraylist
			index ++;
			
		}
		

		//Step successfully completed
		return true;
	}

	@Override
	protected Node next(Message m) {
		// TODO Auto-generated method stub
		Node next_node = null;
		Node n;
		
		n = m.getNode();
			
		// If the node isn't present in the network return
		if (!network.contains(n) || !this.getTraverseList().contains(n)) {
			getInfo().add("Does not contain node " + n.getName()+"\n");
			System.out.println("Does not contain node " + n.getName());
			return null;
		}
		
		for(int i = 0; i< this.getTraverseList().size(); i++){
			if(this.traverseList.get(i) == n){
				next_node= this.traverseList.get(i+1);
			}
		}
		
		return next_node;
	}
	
	public static void main(String[] args) {
		
		Network n = new Network();
		Node n1 = new Node("1");
		Node n2 = new Node("2");
		Node n3 = new Node("3");		
		Node n4 = new Node("4");
		Node n5 = new Node("5");

		n.add(n1);
		n.add(n2);
		n.add(n3);
		n.add(n4);
		n.add(n5);

		n.link(n1, n2);
		n.link(n2, n3);
		n.link(n2, n4);	
		n.link(n3, n4);		
		n.link(n4, n5);

		DepthFirstAlgorithm algo = new DepthFirstAlgorithm(n);
		
		Message m = new Message("MSG1", n1, n5);
		algo.dfs(n1.getNeighbors(), n1);
		//algo.run(m,0);
		//print out the traverse list
		//for(int i = 0; i< algo.getTraverseList().size(); i++){
		//	System.out.println(algo.getTraverseList().get(i));
		//}
		
		boolean value = algo.run(m, 0);
		System.out.println("Packets sent during transmission: " + algo.getPacketCount());
		System.out.println("true or false " + value);
		
	}

}
