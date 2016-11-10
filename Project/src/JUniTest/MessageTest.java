package JUniTest;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Network.Message;
import Network.Network;
import Network.Node;
/*
 * Author Toyin Odujebe
 * Modified Osama Buhamad, Lina El Sadek
 */
public class MessageTest {

	private Message message1, message2, message3;
	@Before
	public void setUp() throws Exception {
		Node src = new Node("A");
		Node dst = new Node("E");
		message1 = new Message("Hey", src, dst);
		message2 = null;
		
	}

	/**
	 * Test method for Message()
	 */
	@Test(expected = NullPointerException.class)
	public void testMessage(){
		assertNotNull(message1);
		assertNull(message2);
		
		//Confirm null input throws an error
		message3= new Message (null, null, null); 
	}
	
	/**
	 * Test method for getSource()
	 */
	@Test
	public void testgetSource(){
		assertEquals("A", message1.getSource().getName());
		assertNotEquals("@", message1.getSource().getName());
	} 
	
	/**
	 * Test method for setSource()
	 */
	@Test
	public void testsetSource(){
		Node src2 = new Node("Toyin");
		message1.setSource(src2);
		assertEquals("TOYIN", message1.getSource().getName());
		
		//Ensure setter converts inputs to upper case
		src2 = new Node("a");
		message1.setSource(src2);
		assertEquals("A", message1.getSource().getName());
	}
	
	
	/**
	 * Test method for setDestination() and getDestination
	 */
	@Test 
	public void testsetDestination(){
		//A valid case
		Node dst = new Node("A");
		message1.setDestination(dst);
		assertEquals("A", message1.getDestination().getName());
		
		//Lower Letter conversion to upper letter
		dst = new Node("a");
		message1.setDestination(dst);
		assertEquals("A", message1.getDestination().getName());
		
		//Ensure value did not change
		message1.setDestination(null);
		assertEquals("A", message1.getDestination().getName());
		
	}
	
	/**
	 * Test method for getContents()
	 */
	@Test
	public void testgetContents(){
		assertEquals("Hey", message1.getContents());
		assertNotEquals("HEYY", message1.getContents());	
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
