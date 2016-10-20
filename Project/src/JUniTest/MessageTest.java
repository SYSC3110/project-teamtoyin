package JUniTest;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Network.Message;
import Network.Network;

public class MessageTest {

	private Message message1, message2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		message1 = new Message("Hey", "A", "E");
		message2 = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testMessage(){
		assertNotNull(message1);
		assertNull(message2);
	}
	
	@Test
	public void testgetSource(){
		assertEquals("A", message1.getSource());
	} 
	
	

}
