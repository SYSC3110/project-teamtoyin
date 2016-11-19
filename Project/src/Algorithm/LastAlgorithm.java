package Algorithm;

import java.util.ArrayList;
import java.util.HashSet;

import Network.Message;
import Network.Network;
import Network.Node;

public class LastAlgorithm extends Algorithm {
	private ArrayList<Node> visited;
	
	public LastAlgorithm(Network network) throws NullPointerException{
		//If network is null throw exception
		if (network == null)
			throw new NullPointerException();
		else
			this.network = network;
		
		// Initialize packet_count
		this.packet_count = 0;
	}
	
	public  void dfs(HashSet<Node> neighbors, Node node, Node node2){
		Node dest = node2;
		System.out.print(node.getName() + "\t");
		
		for (Node n : neighbors) {
			//System.out.println(n.data);
			if(n == dest) break;
			while(n != dest){
				if(n!=null && !visited.contains(n)){
					dfs(n.getNeighbors(),n, dest);
					visited.add(n);
				}
			}
		}
	}
	@Override
	protected boolean step() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Node next(Message m) {
		// TODO Auto-generated method stub
		return null;
	}

}
