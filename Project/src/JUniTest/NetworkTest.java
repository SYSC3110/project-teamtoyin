package JUniTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Network.Network;

/**
 * @author Lina El Sadek
 * @date 10/16/2016
 *
 */
public class NetworkTest {
	private Network network;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		network = new Network();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Network.Network#Network()}.
	 */
	@Test
	public void testNetwork() {
		//Check the network Object is no longer null
		assertNotNull(network);
		
	}

	/**
	 * Test method for {@link Network.Network#add(java.lang.String)}.
	 */
	@Test
	public void testAdd() {
		
		//Add a new Node to the network, with different formats
		assertTrue(network.add("A"));
		assertTrue(network.add("0"));
		
		//Add an Existing Node to the Network
		assertFalse(network.add("A"));
		assertFalse(network.add("a"));
		
		//Attempt to add an empty string to the network
		assertFalse(network.add(""));
		
		//Attempt to add a null to the network
		assertFalse(network.add(null));
		
		
	}

	/**
	 * Test method for {@link Network.Network#remove(java.lang.String)}.
	 */
	@Test
	public void testRemove() {
		//Attempt to remove a node from an empty hashmap
		assertFalse(network.remove("A"));
		
		//Remove a node from the hashmap, with same case
		network.add("A");
		assertTrue(network.remove("A"));
		
		//Remove a node from the hashmap, with different case
		network.add("B");
		assertTrue(network.remove("b"));
		
		//Attempt to remove null value
		assertFalse(network.remove(null));
		
		//Attempt to remove empty string
		assertFalse(network.remove(""));

	}

	/**
	 * Test method for {@link Network.Network#contains(java.lang.String)}.
	 */
	@Test
	public void testContains() {

		//Attempt to look for empty strings
		assertFalse(network.contains(""));
		
		//Attempt to look for empty strings
		assertFalse(network.contains(null));
		
		//Attempt to look for not existing node
		assertFalse(network.contains("A"));
		
		network.add("A");
		//Attempt to look for existing node with matching case
		assertTrue(network.contains("A"));
		//Attempt to look for existing node with mismatching case
		assertTrue(network.contains("a"));
	}

	/**
	 * Test method for {@link Network.Network#getNeighbors(java.lang.String)}.
	 */
	@Test
	public void testGetNeighbors() {

		//Attempt to get neighbors from inexisting nodes
		assertNull(network.getNeighbors(null));
		assertNull(network.getNeighbors(""));
		assertNull(network.getNeighbors("A"));
		
		network.add("A");
		assertNotNull(network.getNeighbors("A"));
		assertNotNull(network.getNeighbors("a"));
		
		network.add("B");
		network.add("C");
		network.link("A", "B");
		network.link("B", "C");
		network.link("C", "A");
		assertNotNull(network.getNeighbors("B"));
		assertNotNull(network.getNeighbors("C"));
	}

	/**
	 * Test method for {@link Network.Network#link(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLink() {
		//Attempt to link invalid inputs
		assertFalse(network.link(null, null));
		assertFalse(network.link("", null));
		
		//Attempt to link inexistent nodes
		assertFalse(network.link("A", "B"));
		
		//Link existent nodes
		network.add("A");
		network.add("B");
		assertTrue(network.link("A", "B"));
	}

	/**
	 * Test method for {@link Network.Network#unlink(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUnlink() {
		//Attempt to link invalid inputs
		assertFalse(network.unlink(null, null));
		assertFalse(network.unlink("", null));
		
		//Attempt to link inexistent nodes
		assertFalse(network.unlink("A", "B"));
		
		//Link existent nodes
		network.add("A");
		network.add("B");
		network.link("A", "B");
		assertTrue(network.unlink("A", "B"));
	}

}
