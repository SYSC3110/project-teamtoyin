package JUniTest;
import UserInterface.NodeGraphic;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Network.Node;


public class NodeGraphicTest {
	private NodeGraphic nodegraph;
	private NodeGraphic nodegraph1 = null;
	
	@Before
	public void setUp() throws Exception {
		nodegraph = new NodeGraphic("A", 10, 10);
	}
	
	@Test
	public void testNodeGraphic(){
		assertNotNull(nodegraph);
		assertNull(nodegraph1);
	}
	
	@Test
	public void testgetxPosition(){
		assertNotNull(nodegraph.getxPosition());
		assertEquals(10, nodegraph.getxPosition());
	}
	
	@Test
	public void testsetxPosition(){
		nodegraph.setxPosition(20);
		assertNotNull(nodegraph.getxPosition());
		assertNotEquals(10, nodegraph.getxPosition());
		assertEquals(20, nodegraph.getxPosition());
	}
	
	
	@Test
	public void testgetyPosition(){
		assertNotNull(nodegraph.getyPosition());
		assertEquals(10, nodegraph.getyPosition());
	}
	
	@Test
	public void testsetyPosition(){
		nodegraph.setyPosition(20);
		assertNotNull(nodegraph.getyPosition());
		assertNotEquals(10, nodegraph.getyPosition());
		assertEquals(20, nodegraph.getyPosition());
	}
	
	
	@Test 
	public void testgetWidth(){
		assertNotNull(nodegraph.getWidth());
		assertEquals(30, nodegraph.getWidth());
	}
	
	@Test
	public void testsetWidth(){
		nodegraph.setWidth(50);
		assertNotNull(nodegraph.getWidth());
		assertNotEquals(30, nodegraph.getWidth());
		assertEquals(50, nodegraph.getWidth());
	}
	
	@Test 
	public void testgetHeight(){
		assertNotNull(nodegraph.getHeight());
		assertEquals(30, nodegraph.getHeight());
	}
	
	@Test
	public void testsetHeight(){
		nodegraph.setHeight(50);
		assertNotNull(nodegraph.getHeight());
		assertNotEquals(30, nodegraph.getHeight());
		assertEquals(50, nodegraph.getHeight());
	}
	

	
}
