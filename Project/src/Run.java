import java.util.Scanner;

import Algorithm.*;
import Network.*;


public class Run {

	public static void main(String[] args)
	{
		Scanner reader = new Scanner(System.in);
		int nodeNum = 0;
		int choice = 0;
		String nodeName = "";
		String sourceNode = "";
		String destinationNode = "";
		String msgStr = "";
		Boolean flag = false;
		int edgeNum = 0;
		int counter = 0;
		String edgeInput = "";
		String doneCommand = "done";
		String[] edgeInputArray = new String[2];
		Network network = new Network();
		Message msg;
		Algorithm algorithm;
		System.out.println("Welcome to Team Toyin's Network Algorithm Program");
		System.out.println("First, we will build your network...");
		System.out.println("Please enter the number of nodes you want in your network");
		nodeNum = reader.nextInt();
		edgeNum = nodeNum * nodeNum;
		System.out.println("You have entered " + nodeNum + " as the number of nodes in your network");
		for(int i=0; i<nodeNum; i++)
		{
			System.out.println("Please enter the name of node number " + (i+1)+ " ");
			nodeName = reader.next();
			flag = network.add(nodeName.toUpperCase());
			while(!flag)
			{
				System.out.println("You have entered a wrong input. Please make sure it's either letters or numbers.");
				System.out.println("Please enter the name of node number " + (i+1)+ " ");
				nodeName = reader.next();
				flag = network.add(nodeName.toUpperCase());
			}
			reader.nextLine();
		}
		System.out.println("Now that we have created the nodes, please enter the edges you want between the nodes");
		System.out.println("A sample input would be: Node1 Node2");
		edgeInput = reader.nextLine();
		while(counter<edgeNum)
		{	
			if(doneCommand.equals(edgeInput.toLowerCase()))
			{
				break;
			}
			edgeInputArray = edgeInput.split(" ");
			while(edgeInputArray.length <2)
			{
				System.out.println("Oops, you have either entered less arguments than required, or did not enter it correctly");
				System.out.println("Please Try again");
				edgeInput = reader.nextLine();
				edgeInputArray = edgeInput.split(" ");
			}
			network.link(edgeInputArray[0].toUpperCase(), edgeInputArray[1].toUpperCase());
			counter++;
			System.out.println("If you're done, please type done, or continue adding edges");
			edgeInput = reader.nextLine();
		}
		
		System.out.println("Now enter the your message parameters");
		System.out.print("\tMessage:");
		msgStr = reader.nextLine();
		System.out.print("\n\tSourceNode:");
		sourceNode = reader.next();
		while(!network.contains(sourceNode.toUpperCase()))
		{
			System.out.println("You have entered an invalid node. Make sure the node you enter is in the network");
			sourceNode = reader.next();
		}
		System.out.print("\n\tDestinationNode:");
		destinationNode = reader.next();
		while(!network.contains(destinationNode.toUpperCase()))
		{
			System.out.println("You have entered an invalid node. Make sure the node you enter is in the network");
			destinationNode = reader.next();
		}
		msg = new Message(msgStr, sourceNode.toUpperCase(), destinationNode.toUpperCase());
		algorithm = new RandomAlgorithm(network);
		System.out.println("Now enter the algorithm you want to be used. Your Options are:");
		System.out.println("1) Random Algorithm");
		//System.out.println("2) Flooding Algorithm");
		//System.out.println("3) Shortest Path Algorithm");
		//System.out.println("4) To be Decided");
		choice = reader.nextInt();
		while(choice != 1)
		{
			System.out.println("You have entered an invalid option. Your Options are:");
			System.out.println("1) Random Algorithm");
			choice = reader.nextInt();
		}
		
		flag = false;
		switch(choice)
		{
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
		
		if(flag){
			algorithm.run(msg);
			System.out.println("Number of Packets generated: "+algorithm.getPacketCount());
			System.out.println("Number of hops taken: " + msg.getHopCount());
		}
		
		
		reader.close();
	}
}
