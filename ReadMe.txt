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

