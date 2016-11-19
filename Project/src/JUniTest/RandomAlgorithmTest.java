package JUniTest;

import static org.junit.Assert.*;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Algorithm.RandomAlgorithm;
import Network.Message;
import Network.Network;
import Network.Node;

/**
 * 
 * @author Toyin Odujebe
 * Modified By: Lina El Sadek
 */
public class RandomAlgorithmTest {
	private Network n1;
	private Message message1;
	private Random randomValue;
	private RandomAlgorithm ra;
	private Node src;
	private Node dest;

	@Before
	public void setUp() throws Exception {
		n1 = new Network();
		src = new Node("A");
		dest = new Node("B");
		message1 = new Message("Hey", src, dest);	
		randomValue = new Random();
		ra = new RandomAlgorithm(n1);
	}

	
	@Test(expected = NullPointerException.class)
	public void testRandomAlgorithm(){
		assertNotNull(n1);
		assertNotNull(message1);
		assertNotNull(randomValue);
		assertNotNull(ra);
		
		//Try adding an undefined network
		Network nullNetwork = null;
		ra = new RandomAlgorithm(nullNetwork);
		
		
	}
	
	@Test
	public void testrun(){
		Network n = new Network();
		Node node1 = new Node("A");
		Node node2 = new Node("B");
		Node node3 = new Node("C");
		Node node4 = new Node("D");
		
		n.add(node1);
		n.add(node2);
		n.add(node3);
		n.add(node4);
		
		n.link(node1, node2);
		n.link(node2, node3);
		n.link(node3, node4);
		n.link(node2, node4);
		
		RandomAlgorithm algo = new RandomAlgorithm(n);
		
		Message m = new Message("Message contents", node1, node4);
		assertTrue(algo.run(m, 0));

	}
}
