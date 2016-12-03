package Algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Network.Message;
import Network.Network;
import Network.Node;
/*
 * Author: Toyin Odujebe
 * This algorithm uses a recursive depth first algorithm to determine how to traverse a network given an initial node
 */
public class DepthFirstAlgorithm extends Algorithm {
	private ArrayList<Node> visited;	//List that contains every node that has been visited
	private ArrayList<Node> traverseList; //List that contains how the network was traversed from the initial node
	private boolean flag=false;		//Flag that checks it is the first time the Dfs method is being called
	private Node source;
	
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

	}
	
	/*
	 * Recursive method for depth first algorithm
	 */
	public void dfs(HashSet<Node> neighbors, Node node){
		
		//Add present node to the list of nodes that are been traversed
		this.getTraverseList().add(node);
		visited.add(node);
		//Get each node in the neighbors list
		for (Node n : neighbors) {
			//if the node is not null and it is not contained in the visited list then proceed
			if(n!=null && !visited.contains(n)){
				//call the dfs method on that node for recursion 
				dfs(n.getNeighbors(),n);;
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
		Node next_node = null;	//Node to be returned
		Node n;
		//Check if its the first time this method is being called and sets the flag to true so it does not populate the table over and over again
		if(flag==false){
			source= m.getNode();
			this.dfs(source.getNeighbors(), source);
			flag=true;
		}
		n = m.getNode();	//Set n to the present node
			
		// If the node isn't present in the network or in the traverse list then just return
		if (!network.contains(n) || !this.getTraverseList().contains(n)) {
			getInfo().add("Does not contain node " + n.getName()+"\n");
			System.out.println("Does not contain node " + n.getName());
			return null;
		}

		for(int i = 0; i< this.getTraverseList().size(); i++){	//Iterate through the traverse list
			if(this.traverseList.get(i) == n){	//When the present node is equal to the node in the traverse list
				next_node= this.traverseList.get(i+1);	//set the next node to the next node in the traverse list after the present node
			}
		}
		
		return next_node;	//Return the next node
	}
	
}
