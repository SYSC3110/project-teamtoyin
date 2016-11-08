Authors:
Richard Hanton
Lina Elsadek
Osama Buhamad
Toyin Odujebe



=====================================================================================
			Milestone 2
=====================================================================================
Changes made to Milestone 1:
New additions and Design Decisions:
Node.java:
	This class encapsulates everything that has to do with a node. This was done to have high cohesion and so that each node can keep track of its own neighbors instead of the network to do that job. For every instantiation of a new Node object, there will be a name to represent that specific node. The Node class has a Hashset that contains a specific node object’s neighbors. If nodes are linked, then they are neighbors and they are added to eachother’s Hashset. The method to add a neighbor to a node is addNeighbour(Node n). To remove the connection between two nodes, we call the method removeNeighbour(Node n) that removes a node from another node’s Hashset. 

Message.java:
	The addition to this class is an array list that takes in node objects called history. Every time the set node method (setNode(Node n)) is called, it signifies that the message is going from one node to the next. When this occurs, we want to remember the previous node the message was at so we store it in the history array list and then set the new message position to the node that was passed into the setNode method. 

Network.java:
	For high cohesion and low coupling, the attributes of a node were moved to Node.java. Nodes can still be added or removed from a network by calling the methods add(Node n) or remove(Node n) respectively and these nodes are added or removed from the Hashset that takes in node objects. Another addition is a list of messages in the network. This list keeps track of all the messages that have been injected into the network while the network is still active. When the injectMessage(Message m) method is invoked, the new message is added to the list of messages for the current instance of network. Everything else is still the same from Milestone 1. 
 
RandomAlgorithm.java:
The random algorithm class is almost the same as the previous except it was modified to have a maximum number of messages that can be injected into the network. This was done so the simulation does not run forever. 

Implemented Model View Controller model in the following form:
UserInterface is the view that takes care of the general layout of the various components.
UserInterfaceController is the controller. It implements ActionListener and takes care of the actions performed in the view. Where appropriate, it called the relevant methods in the Model class.
Network is the model. It implements methods to add/remove nodes, and link/unlink node.
Implemented Observer Observable model as follows:
UserInterface is the Observer. It listens to the observer and behaves based on a set of commands given to it from the Observable.
Network is the Observable.  Applicable methods, notify observers of the state change in that class
Created Enums which are set as action commands for the various components in the UserInterface class. Additional enums are created for communication between the Observer and Observable.


Contributions:
Lina El Sadek: 
•	Continued work on Run.java to make it more user friendly and fixed bugs.
•	Implemented RandomAlgorithm
•	Test: Wrote AllTest.java, NetworkTest.java, NodeTest.java and RandomAlgorithmTest.java
•	Implemented UserInterfaceController.java and UserInterface.java
•	Implemented Message.java class
•	Modified Node.java
Toyin Odujebe: 
•	Worked on Run.java to make it more user friendly and fixed some bugs. 
•	Implemented FloodingAlgorithm.java
•	Test: MessageTest.java, NodeGraphicTest.java, RandomAlgorithmTest.java
•	Worked on making commands Enums: NetworkCommands.java, UICommands.java and UICCommands.java
•	Modified the UserInterfaceController.java and UserInterface.java classes to use the command enums.
Richard Hanton: 
•	Implemented the Node.java class, Algorithm.java, Network.java
•	Modified FloodingAlgorithm.java and RandomAlgorithm.java. Fixed the rate issues in the algorithms
•	Completed the RandomAlgorithm.java
•	Modified Message.java added hops a message goes through
•	
Osama Buhamad:
•	Implemented Graphic.java, MessageGraphic.java, NodeGraphic.java, 
•	Worked on rate for RandomAlgorithm.java
•	Implemented test cases for MessageTest.java, NodeTest.java and RandomAlgorithm.java
•	Implemented userInterfaceGraphic.java


Known Issues:
Please refer to our github repo for resolved and current issues.

=====================================================================================
			Milestone 1
=====================================================================================
Current Product:
Presently we have completed Milestone 1 and already began working on milestone 2 by doing unit tests for the Message and Network classes. Presently all tests are passing and as the project continues, we will be adding other test cases.
Explanation of classes:
Network.java: 
Contains a variable called nodes that’s a HashMap and in this HashMap, the keys are of type string to represent the nodes and the value of each key is of type HashSet to represent the neighbours. The HashSet value itself contains objects of type Strings. The HashMap is used to store a node and its neighbours. A HashMap was used because each node is unique (i.e. Key) and a HashSet was used for the value of the Key because a node can have a set of neighbours. 
add(String n): takes in a string object as input which represents a node and ensures it’s of the right format and then adds this new node to the HashMap as a key.
link(String n1, String n2): takes in two string objects that represent nodes and after sufficient checks for format and inclusion in the HashMap, adds each node to each other’s HashSet as a neighbour.
unlink(String n1, String n2) takes in two string objects that represent nodes and after sufficient checks for format and inclusion in the HashMap, remove the nodes from each other’s HashSet.
remove(String n): takes in a string object that represents a node and for every single node that is connected to it, calls the unlink() method to remove the relationship between them and then remove the node itself from the HashMap.
contains(String n): returns true if the input String object is in the HashMap as a key so it represents a unique node.
getNeighbours(String n): takes in a string object which represents a node and returns the HashSet associated with that node which are the node’s neighbours. 

Message.java
This class encapsulates everything about a message. In the constructor, it takes the content of the message you are trying to pass on, also the source of the message and the desired destination. This class contains a method called countHop() and this method increments a counter for every time this message goes through a hop which is an intermediary node. 
Algorithm.java 
This class is the Algorithm interface and is following the strategy pattern since we are going to have different routing algorithms. The methods in this class are not implemented and will be implemented in the concrete algorithms that will implement this Algorithm.java class

RandomAlgorithm.java
This class is a concrete Algorithm class and it implements the methods defined in the interface Algorithm.java class. This constructor takes in a network object and the run method takes in a message object that is going to be transported from one node to the next.
next(String n): this method takes in a string object that represents a node and determines the next node that a packet should traverse from the current node. It does this by:
•	Checks if node actually exists in the HashMap
•	Gets neighbours of the present node which are in the HashSet 
•	For random algorithm, we get the size of the neighbours and then get a random number within the range of the size of the neighbours
•	Now all we do is fetch the random neighbour and return it
run(Message m): this method takes in a message object and gets the message source and destination. The next node is selected in this method by invoking the next() method. The current node is then updated to the next node. The packet transmitted gets incremented each time a packet is received at a node and also the number of hops a message goes through gets incremented. 
  
Run.java
This class contains the main method to run the program. Takes in user inputs when prompted on the console with the aid of Scanner and also print statements to tell the user when to enter these inputs.

