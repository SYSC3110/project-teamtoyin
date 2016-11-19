package JUniTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Algorithm.FloodingAlgorithm;
import Network.Message;
import Network.Network;
import Network.Node;

/**
 * 
 * @author Lina El Sadek
 * @date 11/19/2016
 *
 */
public class FloodingAlgorithmTest {

	private Network network;
	private FloodingAlgorithm fa;
	private Message msg;
	private Node src, dest, n2, n3;
	
	@Before
	public void setUp() throws Exception {
		network = new Network();
		fa = new FloodingAlgorithm(network);
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
	}
	
	@Test(expected = NullPointerException.class)
	public void testFloodingAlgorithm()
	{
		//Ensure it throws a null pointer exception
		Network n = null;
		FloodingAlgorithm fa2 = new FloodingAlgorithm(n);
		
		//Ensure the object has been initialized correctly
		assertNotNull(fa);		
	}

	@Test
	public void testRun() {

		//Pass negative rates
		assertTrue(fa.run(msg, -1));
		
		//pass rate = 0
		assertTrue(fa.run(msg, 0));
		
		//pass rate = 2
		assertTrue(fa.run(msg, 1));
	}
	
	@Test
	public void testPacketCount()
	{
		//It should be n-1 packets, if rate is 0
		fa.run(msg, 0);
		assertEquals(fa.getPacketCount(), 3);
	}

}
