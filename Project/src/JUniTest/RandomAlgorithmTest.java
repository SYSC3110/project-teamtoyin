package JUniTest;

import static org.junit.Assert.*;
/*
 * Author Toyin Odujebe
 */
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Algorithm.RandomAlgorithm;
import Network.Message;
import Network.Network;

/**
 * 
 * @author User
 * Modified By: Lina El Sadek
 */
public class RandomAlgorithmTest {
	private Network n1;
	private Message message1;
	private Random randomValue;
	private RandomAlgorithm ra;

	@Before
	public void setUp() throws Exception {
		n1 = new Network();
		message1 = new Message("Hey", "A", "B");	
		randomValue = new Random();
		ra = new RandomAlgorithm(n1);
	}


	@After
	public void tearDown() throws Exception {
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
	public void testnext(){
		n1.add("A");
		n1.add("B");
		n1.link("A", "B");
		assertNotNull(ra.next("A"));
		assertEquals("A", ra.next("B"));
		
	}
	
	@Test
	public void testrun(){
		Network n = new Network();
		n.add("A");
		n.add("B");
		n.add("C");
		n.add("D");
		
		n.link("A", "B");
		n.link("B", "C");
		n.link("C", "D");
		n.link("B", "D");
		
		RandomAlgorithm algo = new RandomAlgorithm(n);
		
		Message m = new Message("Message contents", "A", "D");
		assertTrue(algo.run(m, 0));

	}
}
