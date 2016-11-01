package JUniTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Network.Node;

/**
 * 
 * @author Lina El Sadek
 * @date 11/1/2016
 *
 */
public class NodeTest {

	private Node n;
	
	@Before
	public void setUp() throws Exception {
		n = new Node("A");
	}

	@Test
	public void testNode() {
		assertNotNull(n);
		
		//Make sure it converts the letter to uppercase
		Node n1 = new Node("a");
		assertEquals("A", n1.getName());
	}
	
	@Test (expected = NullPointerException.class)
	public void testNodeNullPointerExcep()
	{
		Node n1 = new Node(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNodeIllegalArgmtExcep()
	{
		Node n1 = new Node("");
	}
	
	@Test
	public void testGetName()
	{
		assertEquals("A", n.getName());
	}
	
	@Test
	public void testAddNeighbor()
	{
		//If a null is passed, it should return false
		assertFalse(n.addNeighbor(null));
		
		//add a neighbor
		Node n1 = new Node("B");
		assertTrue(n.addNeighbor(n1));
		
		//add a neighbor again, and make sure it did not get added
		assertTrue(n.addNeighbor(n1));
		assertEquals(1, n.getNeighbors().size());
	}
	
	@Test
	public void testRemoveNeighbor()
	{
		//If a null is passed, it should return false
		assertFalse(n.removeNeighbor(null));
		
		//Attempt to remove a node that is not a neighbor
		Node n1 = new Node("B");
		assertFalse(n.removeNeighbor(n1));
		
		//Attempt to remove a neighbor
		n.addNeighbor(n1);
		assertEquals(1, n.getNeighbors().size());
		assertTrue(n.removeNeighbor(n1));
		assertEquals(0, n.getNeighbors().size());
	}
	
	@Test
	public void testRemoveNeighbors()
	{
		
		Node n1 = new Node("B");
		Node n2 = new Node("C");
		
		n.addNeighbor(n1);
		n.addNeighbor(n2);
		
		assertEquals(2, n.getNeighbors().size());
		n.removeNeighbors();
		assertEquals(0, n.getNeighbors().size());
	}
	
	@Test
	public void testHasNeighbor()
	{
		//A null input would result in a false
		assertFalse(n.hasNeighbor(null));
		
		//A node that is not a neighbor should return false
		Node n1 = new Node("B");
		assertFalse(n.hasNeighbor(n1));
				
		n.addNeighbor(n1);
		assertTrue(n.hasNeighbor(n1));
	}
	
	@Test
	public void testGetNeighbours()
	{
		fail("Not implemented");
	}
	
	@Test
	public void testEquals()
	{
		fail("Not implemented");
	}

}
