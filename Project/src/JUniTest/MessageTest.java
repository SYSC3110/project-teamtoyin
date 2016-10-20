package JUniTest;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Network.Message;
import Network.Network;

public class MessageTest {

	private Message message1, message2,message3;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		message1 = new Message("Hey", "A", "E");
		message2 = null;
		message3= new Message (null, null, null); 
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
		assertNotNull(message3);
	}
	
	@Test
	public void testgetSource(){
		assertEquals("A", message1.getSource());
		assertNotEquals("@", message1.getSource());
		assertEquals(null, message3.getSource());
	} 
	
	@Test
	public void testgetDestination(){
		assertEquals("E", message1.getDestination());
		assertEquals(null, message3.getDestination());
	}
	
	@Test
	public void testgetContenets(){
		assertEquals("Hey", message1.getContents());
		assertEquals(null, message3.getContents());
		
	}
	
	
	
	
	
	

}
