package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * 
 * @author Lina El Sadek
 * @date 10/25/2016
 *
 */
public class UserInterfaceController implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		String command = "";
		
		if(e.getSource() instanceof JTextField)
		{
			command = e.getActionCommand();
			if(command.equals("GetNumberOfNodes"))
			{
				String numOfNodesString = ((JTextField)e.getSource()).getText();
				try{
					int numOfNodesInt = Integer.parseInt(numOfNodesString);
					System.out.println(numOfNodesInt);
					if(numOfNodesInt<=0)
					{
						((JTextField)e.getSource()).setText("Must be positive integer");
					}
				}catch(NumberFormatException err){
					((JTextField)e.getSource()).setText("Must be positive integer");
				}
			}
			else if(command.equals("GetRateNumber"))
			{
				String rateNumString = ((JTextField)e.getSource()).getText();
				try{
					int rateNumInt = Integer.parseInt(rateNumString);
					System.out.println(rateNumInt);
					if(rateNumInt<0)
					{
						((JTextField)e.getSource()).setText("Must be positive integer or zero");
					}
				}catch(NumberFormatException err){
					((JTextField)e.getSource()).setText("Must be positive integer or zero");
				}
			}
		}
		
	}
}