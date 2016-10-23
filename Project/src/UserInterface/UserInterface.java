package UserInterface;

import javax.swing.*;


/**
 * 
 * @author Lina El Sadek
 * @date 10/23/2016
 *
 */
public class UserInterface extends JFrame {
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem reset;
	private JMenuItem restore;
	private JMenuItem undo;
	private JMenuItem save;
	private JMenuItem help;

	public UserInterface()
	{
		super("Team Toyin's Build-A-Network");
		createJMenu();
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void createJMenu()
	{

		//Initialize Elements
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		
		reset = new JMenuItem("Reset Topology");
		reset.setActionCommand("Reset");
		
		restore = new JMenuItem("Upload Topology");
		restore.setActionCommand("fromXML");
		
		undo = new JMenuItem("Undo");
		undo.setActionCommand("Undo");
		
		save = new JMenuItem("Save XML");
		save.setActionCommand("toXML");
		
		help = new JMenuItem("User Manual");
		help.setActionCommand("Help");
		
		//Add Menu Items to Menu
		menu.add(reset);
		menu.add(restore);
		menu.add(undo);
		menu.add(save);
		menu.add(help);
		
		//Add Menu to Menu Bar
		menuBar.add(menu);
		
		//add Menu Bar to Frame
		setJMenuBar(menuBar);

		
		
	}
	
	
	public static void main(String[] args)
	{
		UserInterface UI = new UserInterface();

	}

}
