package JUniTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Algorithm.LastAlgorithm;
import Algorithm.ShortestPathAlgorithm;
import Network.Message;
import Network.Network;
import Network.Node;

public class LastAlgorithmTest {

	private Network network;
	private LastAlgorithm sp;
	private Message msg;
	private Node src, dest, n2, n3;
	
	@Before
	public void setUp() throws Exception {
		network = new Network();
		src = new Node("A");
		dest = new Node("D");
		n2 = new Node("B");
		n3 = new Node("C");
		network.add(src);
		network.add(n2);
		network.add(n3);
		network.add(dest);
		network.link(src, n2);
		network.link(n2, n3);
		network.link(n3, dest);
		msg = new Message("Hello", src, dest);
		sp = new LastAlgorithm(network);	
		sp.dfs(src.getNeighbors(), src);
	}

	@Test(expected = NullPointerException.class)
	public void testShortestPathAlgorithm()
	{
		//Ensure it throws a null pointer exception
		Network n = null;
		LastAlgorithm sp2 = new LastAlgorithm(n);
		
		//Ensure the object has been initialized correctly
		assertNotNull(sp2);		
	}

	@Test
	public void testgetTraverseList(){
		assertNotNull(sp.getTraverseList());
	}
	@Test
	public void testRun() {
		
		//Pass negative rates
		assertTrue(sp.run(msg, -1));
		
		//pass rate = 0
		assertTrue(sp.run(msg, 0));
		
		//pass rate = 2
		assertTrue(sp.run(msg, 1));
	}
	
	@Test
	public void testPacketCount()
	{
		//It should be n-1 packets, if rate is 0
		sp.run(msg, 0);
		assertEquals(sp.getPacketCount(), 3);
	}

}
