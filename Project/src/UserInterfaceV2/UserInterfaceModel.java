package UserInterfaceV2;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Observable;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Algorithm.*;
import Network.*;

/**
 * Author: Richard Hanton
 * Modified: Osama Buhamad, Lina El Sadek
 *
 */
public class UserInterfaceModel extends Observable {

	private Network network;
	private Algorithm algorithm;
	private Message message;
	
	/** 
	 * Constructor for our user interface model
	 */
	public UserInterfaceModel() {
		
		//Initialize the network
		this.network = new Network();
		
	}
	
	/**
	 * Add node to our network
	 */
	public void addNode(String str) {
		
		//Create node based on entered string
		Node n = new Node(str);
		
		//Create event
		UserInterfaceEvent e = new UserInterfaceEvent(this);
		
		//Set type of event
		e.setType("Node Update");
		
		//Start of Try/Catch
		try {
			
		
			//If node added to network
			if (network.add(n)) {
							
				//Set network in event
				e.setNetwork(this.network);
				
				//Set success
				e.setSuccess(true);
				
			} else {
				
				//Set message to return
				e.setMessage("Could not add the node.");
				
				//Set success
				e.setSuccess(false);
				
			}
		
		//Caught Exception
		} catch(Exception ex) {
			
			//Save exception into return message
			e.setMessage(ex.toString());
			
		}
		
		//Set observer as changed 
		setChanged();		
		
		//Notify view that the model updated
		notifyObservers(e);	
		
	}
	
	/**
	 * Add node to our network
	 */
	public void removeNode(Node n) {
		
		//Create event
		UserInterfaceEvent e = new UserInterfaceEvent(this);
		
		//Set type of event
		e.setType("Node Update");
		
		//Start of Try/Catch
		try {

			//If node added to network
			if (network.remove(n)) {
				
				//Set network in event
				e.setNetwork(this.network);
				
				//Set success
				e.setSuccess(true);
				
			} else {
				
				e.setMessage("Could not remove node");
				
			}
		
		//Caught Exception	
		} catch(Exception ex) {
			
			//Save exception into return message
			e.setMessage(ex.toString());
			
		}
		
		//Set observer as changed 
		setChanged();		
		
		//Notify view that the model updated
		notifyObservers(e);	
		
	}
	
	/**
	 * Link 2 nodes in our network
	 */
	public void linkNodes(Node n1, Node n2) {
		
		//Create event
		UserInterfaceEvent e = new UserInterfaceEvent(this);
		
		//Set type of event
		e.setType("Node Link Update");
		
		//Start of Try/Catch
		try {
			
		
			//If node added to network
			if (network.link(n1, n2)) {
							
				//Set network in event
				e.setNetwork(this.network);
				
				//Set success
				e.setSuccess(true);
				
			} else {
				
				//Set message
				e.setMessage("Could not link " + n1.toString() + " and " + n2.toString());
				
				//Set success
				e.setSuccess(false);
				
			}
		
		//Caught Exception
		} catch(Exception ex) {
			
			//Save exception into return message
			e.setMessage(ex.toString());
			
		}
		
		//Set observer as changed 
		setChanged();		
		
		//Notify view that the model updated
		notifyObservers(e);			
	}

	/**
	 * Unlink 2 nodes in our network
	 */
	public void unlinkNodes(Node n1, Node n2) {
		
		//Create event
		UserInterfaceEvent e = new UserInterfaceEvent(this);
		
		//Set type of event
		e.setType("Node Link Update");
		
		//Start of Try/Catch
		try {
			
		
			//If node added to network
			if (network.unlink(n1, n2)) {
							
				//Set network in event
				e.setNetwork(this.network);
				
				//Set success
				e.setSuccess(true);
				
			} else {
				
				//Set message
				e.setMessage("Could not unlink " + n1.toString() + " and " + n2.toString());
				
				//Set success
				e.setSuccess(false);
				
			}
		
		//Caught Exception
		} catch(Exception ex) {
			
			//Save exception into return message
			e.setMessage(ex.toString());
			
		}
		
		//Set observer as changed 
		setChanged();		
		
		//Notify view that the model updated
		notifyObservers(e);			
	}

	/**
	 * Set the message we are going to send
	 */
	public boolean setMessage(String contents, Node source, Node destination) {
		
		//Start of Try/Catch
		try {
			
			//Try to create the message
			message = new Message(contents, source, destination);
			
			//Return successful
			return true;
			
		//Message unable to be sent
		} catch(Exception ex) {
			
			//Create event
			UserInterfaceEvent e = new UserInterfaceEvent(this);
			
			//Set type of event
			e.setType("Set Message");
			
			//Set message to return to view
			e.setMessage("Unable to create this message.");
			
			//Set observer as changed 
			setChanged();		
			
			//Notify view that the model updated
			notifyObservers(e);		
			
			//Return unsuccessful
			return false;
			
		}
	}
	
	/**
	 * Run a simulation given the algorithm name and number 
	 * of message injections
	 */
	public void runSimulation(String algo, int rate) {
		
		//Check message not null
		if (this.message == null) { return; } 
				
		//Create event
		UserInterfaceEvent e = new UserInterfaceEvent(this);
		
		//Set event type
		e.setType("Simulation");
		 
		//Check if class exists 
		try {
			
			//Check class exists
		    Class<?> class_temp = Class.forName("Algorithm."+algo);
		    
		    //Get algorithm constructor
		    Constructor<?> con = class_temp.getConstructor(Network.class);
		    
		    //Initialize the algorithm
		    algorithm = (Algorithm) con.newInstance(network);
		    
		    //Run the algorithm
		    if (algorithm.run(message, rate)) {
		    	
		    	//Algorithm successfully executed
		    	e.setSuccess(true);
		    	
		    	//Set a success message and pass the information to be displayed 
		    	e.setMessage(algorithm.getInfoStr()+": Algorithm ran successfully");
		    	
		    } else {
		    	
		    	//Algorithm did not execute successfully
		    	e.setMessage("Something went wrong running the algorithm...");
		    	
		    }
		    
		    	        
		//Caught exception
		} catch (Exception ex) {
					    
		    e.setMessage("Algorithm not found.");
		    
		}
		
		//Set observer as changed 
		setChanged();		
		
		//Notify view that the model updated
		notifyObservers(e);				
		
	}

	/**
	 * Saves the network to the specified path
	 */
	public void save(String path) {
		ArrayList<Node> linked = new ArrayList<Node>();
		
		//Create event
		UserInterfaceEvent e = new UserInterfaceEvent(this);
		
		//Set type of event
		e.setType("Save Network");
		
		//Try/Catch
		try {
			
			//New document builder factory
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			
			//New document builder
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			//Root element of our document
			Document doc = docBuilder.newDocument();
			
			//Network element of document
			Element rootElement = doc.createElement("network");
			
			//Add network to doc
			doc.appendChild(rootElement);
			
			//Nodes element of document
			Element nodesElement = doc.createElement("nodes");
			
			//Add network to doc
			rootElement.appendChild(nodesElement);
			
			//Append each node
			for (Node n : this.network.getNodes()) {
				
				//Create node element
				Element nodeElement = doc.createElement("node");
				
				//Add node name to element
				nodeElement.appendChild(doc.createTextNode(n.getName()));
				
				//Add to nodes section
				nodesElement.appendChild(nodeElement);
				
			}

			//Nodes element of document
			Element linksElement = doc.createElement("links");
			
			//Add network to doc
			rootElement.appendChild(linksElement);
			
			//Append each node
			for (Node n : this.network.getNodes()) {
				
				//For each link on this node
				for (Node neighbor : n.getNeighbors()) {
				
					//If we have already linked from other node
					if (linked.contains(neighbor)) 
						continue;
					
					//Create node element
					Element linkElement = doc.createElement("link");
					
					//Add from to link element
					linkElement.setAttribute("from", n.getName());
					
					//Add to to link element
					linkElement.setAttribute("to", neighbor.getName());
					
					//Add link element to links list
					linksElement.appendChild(linkElement);					
				
				}
				
				//Add this node to linked
				linked.add(n);
				
			}
			
			//New transformer factory
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			//New transformer
			Transformer transformer = transformerFactory.newTransformer();
			
			//Transformer settings
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			//Get document source
			DOMSource source = new DOMSource(doc);
			
			//Stream to save file
			StreamResult result = new StreamResult(new File(path));

			//Save to file
			transformer.transform(source, result);
			
			//Success
			e.setSuccess(true);
			
			//Success message
			e.setMessage("Network saved.");
			
		//Caught exception
		} catch (Exception ex) {
			
			//Assign error message to string
			e.setMessage(ex.toString());
			
		}

		//Set observer as changed 
		setChanged();		
		
		//Notify view that the model updated
		notifyObservers(e);			
		
	}
	
	/**
	 * Import a saved network
	 */
	public void load(String path) {
		
		//Clear current network
		this.network = new Network();
		
		//Create event
		UserInterfaceEvent e = new UserInterfaceEvent(this);
		
		//Set type of event
		e.setType("Import Network");
		
		//Try/Catch
		try {
			
			//Load file from path
			File fXmlFile = new File(path);
			
			//New document builder factory
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			
			//New document builder
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			//Parse document
			Document doc = dBuilder.parse(fXmlFile);
			
			//Normalize document
			doc.getDocumentElement().normalize();
			
			//Get all nodes
			org.w3c.dom.NodeList nodeList = doc.getElementsByTagName("node");
				
			//For each node
			for (int temp = 0; temp < nodeList.getLength(); temp++) {

				//Get individual node
				org.w3c.dom.Node nNode = nodeList.item(temp);
				
				//Check node name not blank
				if (nNode.getTextContent() != "") {
					
					//Create new node
					Node n = new Node(nNode.getTextContent());
					
					//Add node to this network
					this.network.add(n);
				
				}
				
			}
			
			//Get all links
			org.w3c.dom.NodeList linkList = doc.getElementsByTagName("link");
				
			//For each node
			for (int temp = 0; temp < linkList.getLength(); temp++) {

				//Get individual node
				org.w3c.dom.Node lNode = linkList.item(temp);
									
				//Get to and from node names
				String fromNode = lNode.getAttributes().getNamedItem("from").getNodeValue();
				String toNode = lNode.getAttributes().getNamedItem("to").getNodeValue();
				
				//If both nodes exist in the network, link them
				if (this.network.getNode(fromNode) != null &&
						this.network.getNode(toNode) != null) {
								
					//Create new node
					this.network.link(this.network.getNode(fromNode), this.network.getNode(toNode));
				
				}
		
			}
			
			//Set network in event
			e.setNetwork(this.network);
				
			//Success
			e.setSuccess(true);
			
			//Success message
			e.setMessage("Network imported.");
			
		//Caught exception
		} catch (Exception ex) {
			
			//Assign error message to string
			e.setMessage(ex.getMessage());

		}
		
		//Set observer as changed 
		setChanged();		
		
		//Notify view that the model updated
		notifyObservers(e);	
				
	}
	
	public Network getNetwork()
	{
		return network;
	}
	
	public Message getMessage()
	{
		return message;
	}
}

