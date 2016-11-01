package JUniTest;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Network.Message;
import Network.Network;
/*
 * Author Toyin Odujebe
 * Modified Osama Buhamad, Lina El Sadek
 */
public class MessageTest {

	private Message message1, message2,message3;
	@Before
	public void setUp() throws Exception {
		message1 = new Message("Hey", "A", "E");
		message2 = null;
		message3= new Message (null, null, null); 
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
		assertEquals("", message3.getSource());
	} 
	
	/**
	 * Test method for setSource()
	 */
	@Test 
	public void testsetSource(){
		message1.setSource("Toyin");
		assertEquals("TOYIN", message1.getSource());
		
		//Ensure setter converts inputs to upper case
		message1.setSource("a");
		assertEquals("A", message1.getSource());
		
		//Ensure value did not change
		message1.setSource(null);
		assertEquals("A", message1.getSource());
		message1.setSource("");
		assertEquals("A", message1.getSource());
	}
	
	/**
	 * Test method for setDestination()
	 */
	@Test 
	public void testsetDestination(){
		//A valid case
		message1.setDestination("A");
		assertEquals("A", message1.getDestination());
		
		//Lower Letter conversion to upper letter
		message1.setDestination("a");
		assertEquals("A", message1.getDestination());
		
		//Ensure value did not change
		message1.setDestination(null);
		assertEquals("A", message1.getDestination());
		message1.setDestination("");
		assertEquals("A", message1.getDestination());
	}
	
	/**
	 * Test method for getDestination()
	 */
	@Test
	public void testgetDestination(){
		assertEquals("E", message1.getDestination());
		assertEquals("", message3.getDestination());
	}
	
	/**
	 * Test method for getContents()
	 */
	@Test
	public void testgetContents(){
		assertEquals("Hey", message1.getContents());
		assertNotEquals("HEYY", message1.getContents());
		assertEquals("", message3.getContents());
		
	}
	
	/**
	 * Test method for setContents()
	 */
	@Test
	public void testSetContents()
	{
		//Basic Case
		message1.setContent("Hello");
		assertEquals("Hello", message1.getContents());
		
		//Invalid case
		message1.setContent(null);
		assertEquals("Hello", message1.getContents());
		message1.setContent("");
		assertEquals("Hello", message1.getContents());
	}
	
	
	
	
	
	

}
