Authors:
Richard Hanton
Lina Elsadek
Osama Buhamad
Toyin Odujebe

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

