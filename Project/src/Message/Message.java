/**
 * 
 */
package Message;

/**
 * @author Lina El Sadek
 * @date 10/16/2016
 *
 */
public class Message {
	private String msg;
	private String destination;
	private String sourceOfMessage;
	
	/**
	 * The constructor is meant to create a message to be sent to a node
	 * @param msg
	 * @param destination
	 * @param sourceOfMessage
	 */
	public Message (String msg, String destination, String sourceOfMessage)
	{
		this.msg = msg;
		this.destination = destination;
		this.sourceOfMessage = sourceOfMessage;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSourceOfMessage()
	{
		return sourceOfMessage;
	}
	
	/**
	 * 
	 * @param str
	 */
	public void setSourceOfMessage(String str)
	{
		if(str != null && str !="")
		{
			sourceOfMessage = str;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDestination()
	{
		return destination;
	}
}
