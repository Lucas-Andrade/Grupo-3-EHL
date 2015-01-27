package main.java.gui.design.panels.mainwindowpanels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.gui.design.borders.TextRoundBorder;
import main.java.gui.design.panels.JPanelImage;

@SuppressWarnings("serial")
public class JUserPanelForHeaderPanel extends JPanel{

	private JButton changePasswordButton;
	private JButton logoutButton;
	private JButton addUserButton;
	private JButton removeUserButton;
	private JButton infoAllUsersButton;
	private JButton turnOffButton;

	/**
	 * @return the turnOffButton
	 */
	public JButton getTurnOffButton()
	{
		return turnOffButton;
	}

	public JUserPanelForHeaderPanel(){
		

		this.setLayout(new FlowLayout());
		this.setBackground(new Color(65, 72, 78));
		
		JPanelImage userImage = new JPanelImage("src/main/resources/images/users/chuck_norris.jpg");
		userImage.setBorder( new TextRoundBorder(Color.WHITE,5,4,0));
		this.add(userImage);	

		JPanel myOptions = new JPanel(new GridLayout(4,0));
		myOptions.setBackground(new Color(65, 72, 78));		
		this.add(myOptions);

		JLabel username = new JLabel("CHUCK NORRIS"); 
		username.setBackground(new Color(0,0,0));
		username.setForeground(Color.WHITE);		
		myOptions.add(username);
		
		changePasswordButton = new JButton("Change Password");	
		myOptions.add(changePasswordButton);		
		
		logoutButton = new JButton("Logout");
		myOptions.add(logoutButton);
		
		turnOffButton = new JButton("Turn Off");
		myOptions.add(turnOffButton);
			
		JPanel userOptions = new JPanel();
		userOptions.setLayout(new GridLayout(3,0));
		this.add(userOptions);
		
		addUserButton = new JButton(new ImageIcon("src/main/resources/images/addUser.png"));
		userOptions.add(addUserButton);
		removeUserButton = new JButton(new ImageIcon("src/main/resources/images/removeUser.png"));
		userOptions.add(removeUserButton);
		infoAllUsersButton = new JButton(new ImageIcon("src/main/resources/images/infoUser.png"));
		userOptions.add(infoAllUsersButton);
		
		
	}

	/**
	 * @return the changePasswordButton
	 */
	public JButton getChangePasswordButton()
	{
		return changePasswordButton;
	}

	/**
	 * @return the logoutButton
	 */
	public JButton getLogoutButton()
	{
		return logoutButton;
	}

	/**
	 * @return the addUserButton
	 */
	public JButton getAddUserButton()
	{
		return addUserButton;
	}

	/**
	 * @return the removeUserButton
	 */
	public JButton getRemoveUserButton()
	{
		return removeUserButton;
	}

	/**
	 * @return the infoAllUsersButton
	 */
	public JButton getInfoAllUsersButton()
	{
		return infoAllUsersButton;
	}
}
