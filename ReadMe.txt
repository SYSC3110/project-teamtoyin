Authors:
Richard Hanton
Lina Elsadek
Osama Buhamad
Toyin Odujebe

=====================================================================================
			Milestone 4
=====================================================================================
Changes to Milestone 3:
New additions and Design Decisions:
- We updated the simulation where by now instead of you just running the simulation and it does all the stepping at once, now you can step from one node to the next based on the path configured by the algorithm in use. 
- When the message is traversing the topology it is represented by a red ball beside the current node. When the message gets to its destination node, it is represented by a green ball also beside the current node.

New features:
- Import: Now you can import an xml file into the simulation. 
- Export Topology: used to export the topology as a png file.
- Save: you can now save the topology as an xml document.

Algorithm.java:
This class was modified to account for the above change of stepping from one node to the next. 
New methods:
- stepper_intialize(Message m, int rate): this method initializes stepping which moves the specified message from the source to its destination using the algorithms method for selecting the next node, allows stepping through the process.
- stepper(): Performs a single step of moving the message from the source to the destination, allowing the user to see the message moving on the topography.
- removeLastMessage(): this method removes the last message when step back is performed and removes the information about the message from the UI.
- updateInfo(): this method updates the list of visited nodes when moving messages and also checks if step back was performed or not
- topologyReseter(): void method that resets everything when end simulation is clicked in the UI. So no information is saved from the previous steps. 

MessageGraphic.java:
- paintLastMessage(Graphics g, int x, int y): this method prints a green circle on the destination node when the message is delivered to it. 

UserInterfaceModel.java
- save(String filename): This method saves the current network as an XML document. The document is structured with a network node that contains all the nodes in the network and all of the link between these nodes.
- load(String filename): This method loads a network XML document and populates the program with the list of nodes, and the links between these nodes as retrieves from the XML file.

Contributions:
Lina El Sadek: 
•	Update

Toyin Odujebe: 
•	Updated the ReadMe and User Manual
•	Fixed major bugs in Algorthm.java class when stepping back, stepping forward and ending simulation
•	Updated UML


Richard Hanton: 
•	Implemented the xml export and import features to save the network or import a previously saved network
•	Added the ability to step through a simulation to view the messages path as it travels from source to destination
•	Added "Step Backwards" to undo a step when stepping through the simulation.

Osama Buhamad:
•	


Known Issues:
Please refer to our github repo for resolved and current issues.

=====================================================================================
			Milestone 3
=====================================================================================
Changes to Milestone 2:
New additions and Design Decisions:
The whole UserInterface was updated for better, more navigatable User Interface. 
-When closing the topology UI, the entire program closes, the issue was fixed by setting the window to DISPOSE_ON_CLOSE
-The information about the run was displayed on the console, now it is displayed on the GUI since it is a GUI driven application.
-Milestone 2's UI only allowed same number of edges as the number of nodes. Now we allow for as many edges as possible (n*n n = number of nodes)

Algorithm.java:
-The Algorithm class was changed to abstract because all algorithms have the same run method and the only difference will be the step and next methods.
-run(Message m, int rate): this method takes in a message object and gets the message source and destination. The next node is selected in this method by invoking the next() method. The current node is then updated to the next node. The packet transmitted gets incremented each time a packet is received at a node and also the number of hops a message goes through gets incremented. The run method also take in an integer that represents the rate messages will be injected into the network.

New Algorithms: 
All algorithms are concrete Algorithm classes and it implements the abstract methods defined in the abstract Algorithm.java class. Their constructors takes in a network object.

FloodingAlgorithm.java
In this algorithm, a node sends a message to all its neighbours except for the node that it got the message from. 
step(): this method performs a simulation step, moving messages to the next node and injecting new messages as required. It returns false if there are no further steps to be taken. This message does this by invoking the method next(Message m). In this method the hop count and packet counts are incremented to account for the metrics we are keeping track of. 
next(Message m): this method takes in a Message object that represents a Message and determines the next node that a packet should traverse from the current node. It does this by:
•	Checks if node actually exists in the Network
•	Gets neighbours of the present node which are in the HashSet 
•	For Flooding algorithm, if the neighbour is not already in the history of nodes the message has visited then return it as the next node to traverse to.

ShortestPathAlgorithm.java:
This algorithm prepopulates a routing table using the breadth first search algorithm. We create a routing entry for each node that contains a destination node and the next node to go to get to that destination node.
loadRoutingTable(): loads the routing table with which neighbor node to travel to depending on the destination node. We use the breadth-first search algorithm in this method. 
step(): this method performs a simulation step, moving messages to the next node and injecting new messages as required. It returns false if there are no further steps to be taken. This message does this by invoking the method next(Message m). In this method the hop count and packet counts are incremented to account for the metrics we are keeping track of. 
next(Message m): this method takes in a Message object that represents a Message and determines the next node that a packet should traverse from the current node. It does this by:
•	Checks if node actually exists in the Network
•	Get routing table for that specific node and determine the next node to traverse to depending on the already populated routing table 
•	Return the next node 

DepthFirstAlgorithm.java (Our own routing algorithm):
This algorithm uses a recursive depth first algorithm to determine how to traverse a network given the first node in the network. We chose the first node in the network so that the path taken is static and in real life scenario less costly for routers to have to perform the algorithm each time they get a network. No need for extremely smart routers when using this algorithm except for the first node which will be the centralized node. 
dfs(HashSet<Node> neighbours, Node node): this method takes in a Node object and also takes in a HashSet<Node> object which represents the neighbours of the specific node. The node is added to the traverse list and also to the visited list and then we get each node in its neighbours list and recursively call the dfs method.
getTraverseList(): returns the traverse list that contains how the messages will be traversed in the network based on current node position.
step(): this method performs a simulation step, moving messages to the next node and injecting new messages as required. It returns false if there are no further steps to be taken. This message does this by invoking the method next(Message m). In this method the hop count and packet counts are incremented to account for the metrics we are keeping track of. 
next(Message m): this method takes in a Message object that represents a Message and determines the next node that a packet should traverse from the current node. It does this by:
•	Checks if node actually exists in the Network and if it is included in the traverse list
•	Gets the current position of the message in the traverse list and sets the next node to the next node in the traverse list 
•	Return the next node 

Contributions:
Lina El Sadek: 
•	Added unit tests for all remaining algorithm classes, and User Interface classes.
•	Redrew the UML Diagram using Draw.io.
•	Refactored the Algorithm Classes to reduce code duplication.

Toyin Odujebe: 
•	Implemented DepthFirstAlgorithm.java, DepthFirstAlgorithmTest.java
•	Wrote the readMe file
•	Refactored classes that contain unnecessary methods and code.

Richard Hanton: 
•	Updated the User Manual for the new GUI.
•	Implemented a new GUI.

Osama Buhamad:
•	Improved UserIntefaceGraphic class by showing a table of the path that a message took along with the topology.  
•	Modified the GUI to show the information messages instead of the console. 
•	refactored and cleaned up the code in some classes.  
•	Added the ability to save the topology to an image to use it for next milestone.


Known Issues:
Please refer to our github repo for resolved and current issues.
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
•	Modified RandomAlgorithm.java
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
•	Implemented the Node.java class, Algorithm.java, Network.java, RandomAlgorithm.java, ShortestPathAlgorithm.java
•	Worked on refactoring the GUI
•	Modified FloodingAlgorithm.java. Fixed the rate issues in the algorithms
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

