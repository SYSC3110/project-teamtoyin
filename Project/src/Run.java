import java.util.Scanner;

import Algorithm.*;
import Network.*;

/**
 * @author Lina El Sadek
 * @Modified Toyin Odujebe
 * 
 * 
 */
public class Run {

	private static Network network;
	private static Algorithm algorithm;
	private static Message msg;
	
	public static void main(String[] args) {
		
		//Variable definition and initialization
		Scanner reader = new Scanner(System.in);
		String nodeName = "";
		String sourceNode = "";
		String destinationNode = "";
		String msgStr = "";
		String edgeInput = "";
		String doneCommand = "done";
		Node n;
		Node node_source = new Node("");
		Node node_dest = new Node("");
		
		String[] edgeInputArray = new String[2];
		Boolean flag = false;
		int edgeNum = 0;
		int counter = 0;
		int nodeNum = 0;
		int rateNum = 0;
		int choice = 0;
		network = new Network();
		
		//Prints welcome message
		printWelcome();
		
		//Prompts the user to enter the number of nodes in the network
		//making sure it's a positive integer
		System.out.println("Please enter the number of nodes you want in your network");
		
		do {
			
		    while (!reader.hasNextInt()) {
		        System.out.println("That's not a number!");
		        reader.next();
		    }
		    nodeNum = reader.nextInt();
		    if(nodeNum <=0)
		    {
		    	System.out.println("That's not a positive number!");
		    }
		    
		} while (nodeNum <= 0);
		
		//There can only be n*n edges in a network assuming all nodes are connected to all edges
		edgeNum = nodeNum * nodeNum;
		System.out.println("You have entered " + nodeNum + " as the number of nodes in your network");
		
		//Prompts the user to enter names for the nodes 
		for(int i=0; i<nodeNum; i++) {
			
			System.out.println("Please enter the name of node number " + (i+1)+ " ");
			nodeName = reader.next();
			n = new Node(nodeName.toUpperCase());			
			flag = network.add(n);
			
			while(!flag) {
				
				System.out.println("You have entered a wrong input. Please make sure it's either letters or numbers.");
				System.out.println("Please enter the name of node number " + (i+1)+ " ");
				nodeName = reader.next();
				n = new Node(nodeName.toUpperCase());
				flag = network.add(n);
				
			}
			
			reader.nextLine();
		}
		
		System.out.println("Now that we have created the nodes, please enter the edges you want between the nodes");
		System.out.println("A sample input would be: Node1 Node2");
		edgeInput = reader.nextLine();
		
		//Prompts the user to enter the edges between the nodes
		while(counter<edgeNum) {
			
			if(doneCommand.equals(edgeInput.toLowerCase())) {
				break;
			}
			
			while(!validateInputString(edgeInput)) {
				edgeInput = reader.nextLine();
			}
			
			edgeInputArray = edgeInput.split(" ");
			Node n1 = new Node(edgeInputArray[0].toUpperCase());
			Node n2 = new Node(edgeInputArray[1].toUpperCase());
			
			network.link(n1, n2);
			counter++;
			System.out.println("If you're done, please type done, or continue adding edges");
			edgeInput = reader.nextLine();
			
		}
		
		//Prompts the user to create a message, with source node, and destination node
		System.out.println("Now enter the your message parameters");
		System.out.print("\tMessage:");
		msgStr = reader.nextLine();
		System.out.print("\n\tSourceNode:");
		sourceNode = reader.next();

		while(!network.contains(node_source)) {
			System.out.println("You have entered an invalid node. Make sure the node you enter is in the network");
			sourceNode = reader.next();
			node_source = new Node(sourceNode);
		}
		
		System.out.print("\n\tDestinationNode:");
		destinationNode = reader.next();
		
		while(!network.contains(node_dest)) {
			System.out.println("You have entered an invalid node. Make sure the node you enter is in the network");
			destinationNode = reader.next();
			node_dest = new Node(destinationNode);			
		}
		
		
		msg = new Message(msgStr, node_source, node_dest);
		algorithm = new RandomAlgorithm(network);
		
		//Prints the Algorithm menu for the user
		printAlgorithmMenu();
		do {
			
		    while (!reader.hasNextInt()) {
		        System.out.println("That's not a number!");
		        reader.next();
		    }
		    
		    choice = reader.nextInt();
		    
		    if(choice <=0) {
		    	System.out.println("That's not a positive number!");
		    } else if(choice !=1) {
		    	System.out.println("You have entered an invalid option.");
		    	printAlgorithmMenu();
		    }
		    
		} while (choice != 1);
		
		//Promt the user if he wants to add rate or not
		System.out.println("Do you want to add a rate value enter yes or no?");
		String rateFlag = reader.next();
		
		while(!(rateFlag.toUpperCase().equals("YES") || rateFlag.toUpperCase().equals("NO"))) {
			System.out.println("Please enter yes or no. Thanks");
			rateFlag = reader.next();
		}
		
		if(rateFlag.toUpperCase().equals("YES")) {
			
			//Prompt the user to enter number of times for rate 
			System.out.println("Please enter the value for rate: ");
			
			do {
				
			    while (!reader.hasNextInt()) {
			        System.out.println("That's not a number!");
			        reader.next();
			    }
			    
			    rateNum = reader.nextInt();
			    
			    if(rateNum <0) {
			    	System.out.println("That's not a positive number!");
			    }
			    
			    if(rateNum <2 ) {
			    	rateNum = 2;
			    }
			} while (rateNum <= 0);
		}
		
		//Initialize the algorithm
		flag = initializeAlgorithm(choice);
		
		//Runs the algorithm on the network
		if(flag){
			algorithm.run(msg, rateNum);
			System.out.println("Number of Packets generated: "+algorithm.getPacketCount());
			System.out.println("Number of hops taken: " + msg.getHopCount());
		}
		
		reader.close();
	}
	
	private static void printAlgorithmMenu() {
		System.out.println("Enter the algorithm you want to be used. Your Options are:");
		System.out.println("1) Random Algorithm");
		//System.out.println("2) Flooding Algorithm");
		//System.out.println("3) Shortest Path Algorithm");
		//System.out.println("4) To be Decided");
	}
	
	private static void printWelcome() {
		System.out.println("Welcome to Team Toyin's Network Algorithm Program");
		System.out.println("First, we will build your network...");
	}
	
	private static boolean initializeAlgorithm(int choice) {
		boolean flag = false;
		
		switch(choice) {
			case 1:
				algorithm = new RandomAlgorithm(network);
				flag = true;
				break;
			case 2: 
				//algorithm = new FloodingAlgorithm(network);
				break;
			case 3:
				//algorithm = new ShortPathAlgorithm(network);
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid choice, no such algorithm");
		}
		
		return flag;
	}
	
	private static boolean validateInputString(String str) {
		String[] strArray = new String[2];
		
		strArray = str.split(" ");
		
		Node n1 = new Node(strArray[0]);
		Node n2 = new Node(strArray[1]);
		
		if(strArray.length<2) {
			
			System.out.println("Oops, you have either entered less arguments than required, or did not enter it correctly");
			System.out.println("Please Try again");
			return false;
			
		} else if(!network.contains(n1)) {
			
			System.out.println("Your left most argument is not in the network");
			System.out.println("Please Try again");
			return false;
			
		} else if(!network.contains(n2)) {
			
			System.out.println("Your right most argument is not in the network");
			System.out.println("Please Try again");
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
}
