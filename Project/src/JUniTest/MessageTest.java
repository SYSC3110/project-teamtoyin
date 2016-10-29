package JUniTest;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Network.Message;
import Network.Network;
/*
 * Author Toyin Odujebe
 * Modified Osama Buhamad
 */
public class MessageTest {

	private Message message1, message2,message3;
	@Before
	public void setUp() throws Exception {
		message1 = new Message("Hey", "A", "E");
		message2 = null;
		message3= new Message (null, null, null); 
	}


	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test method for Message()
	 */
	@Test
	public void testMessage(){
		assertNotNull(message1);
		assertNull(message2);
		assertNotNull(message3);
	}
	
	/**
	 * Test method for getSource()
	 */
	@Test
	public void testgetSource(){
		assertEquals("A", message1.getSource());
		assertNotEquals("@", message1.getSource());
		assertEquals(null, message3.getSource());
	} 
	
	/**
	 * Test method for setSource()
	 */
	@Test 
	public void testsetSource(){
		message1.setSource("Toyin");
		assertEquals("Toyin", message1.getSource());
	}
	
	/**
	 * Test method for getDestination()
	 */
	@Test
	public void testgetDestination(){
		assertEquals("E", message1.getDestination());
		assertEquals(null, message3.getDestination());
	}
	
	/**
	 * Test method for getContents()
	 */
	@Test
	public void testgetContents(){
		assertEquals("Hey", message1.getContents());
		assertNotEquals("HEYY", message1.getContents());
		assertEquals(null, message3.getContents());
		
	}
	
	
	
	
	
	

}
