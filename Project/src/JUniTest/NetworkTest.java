package JUniTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Network.Network;
import Network.Node;

/**
 * @author Lina El Sadek
 *
 */
public class NetworkTest {
	private Network network;
	

	@Before
	public void setUp() throws Exception {
		network = new Network();
	}


	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for Network()
	 */
	@Test
	public void testNetwork() {
		//Check the network Object is no longer null
		assertNotNull(network);
		
	}

	/**
	 * Test method for add()
	 */
	@Test
	public void testAdd() {
		
		Node n1 = new Node("A");
		Node n2 = new Node("0");
		Node n3 = new Node(",");
		Node n4 = new Node("a");
		
		//Add a new Node to the network, with different formats
		assertTrue(network.add(n1));
		assertTrue(network.add(n2));
		assertTrue(network.add(n3));
		
		//Add an Existing Node to the Network
		assertFalse(network.add(n1));
		assertFalse(network.add(n4));
		
		//Attempt to add an empty string to the network
		//Not valid test anymore.
		//assertFalse(network.add(""));
		
		//Attempt to add a null to the network
		assertFalse(network.add(null));
	}

	/**
	 * Test method for remove()
	 */
	@Test
	public void testRemove() {
		
		Node n1 = new Node("A");

		//Attempt to remove a node from an empty hashmap
		assertFalse(network.remove(n1));
		
		//Remove a node from the hashmap, with same case
		network.add(n1);
		assertTrue(network.remove(n1));
		
		//Remove a node from the hashmap, with different case
		//not a valid test anymore
		//network.add(n2);
		//assertTrue(network.remove(n2));
		
		//Attempt to remove null value
		assertFalse(network.remove(null));
		
		//Attempt to remove empty string
		//not a valid test anymore
		//assertFalse(network.remove(""));
		
		//Attempt to remove a space string
		//not a valid test anymore
		//assertFalse(network.remove(" "));

	}

	/**
	 * Test method for Contains()
	 */
	@Test
	public void testContains() {
		
		Node n1 = new Node("A");

		//Not a valid case anymore
		//Attempt to look for empty strings
		//assertFalse(network.contains(""));
		
		//Attempt to look for empty strings
		assertFalse(network.contains(null));
		
		//Attempt to look for not existing node
		assertFalse(network.contains(n1));
		
		//Not a valid test case
		//network.add("$");
		//Attempt to look for a symbol
		//assertTrue(network.contains("$"));
		
		network.add(n1);
		
		//Attempt to look for existing node with matching case
		assertTrue(network.contains(n1));
		
		//Not a valid test case.
		//Attempt to look for existing node with mismatching case
		//assertTrue(network.contains("a"));
	}

	/**
	 * Test method for Link()
	 */
	@Test
	public void testLink() {
		
		Node n1 = new Node("A");
		Node n2 = new Node("B");
		
		//Attempt to link invalid inputs
		assertFalse(network.link(null, null));
		//assertFalse(network.link("", null)); //not valid test case
		
		//Attempt to link inexistent nodes
		assertFalse(network.link(n1, n2));
		
		//Link existent nodes
		network.add(n1);
		network.add(n2);
		assertTrue(network.link(n1, n2));
	}

	/**
	 * Test method for unlink()
	 */
	@Test
	public void testUnlink() {
		
		Node n1 = new Node("A");
		Node n2 = new Node("B");
		
		//Attempt to link invalid inputs
		assertFalse(network.unlink(null, null));
		//assertFalse(network.unlink("", null)); //not valid testcase
		
		//Attempt to link nonexistent nodes
		assertFalse(network.unlink(n1, n2));
		
		//Link existent nodes
		network.add(n1);
		network.add(n2);
		
		//Attempt to unlink nodes are not linked
		assertFalse(network.unlink(n1, n2));
		
		network.link(n1, n2);
		assertTrue(network.unlink(n1, n2));
	}

}
