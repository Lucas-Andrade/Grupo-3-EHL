package main.java.gui.designWindows.JPanels.ForMainWindow;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.gui.designWindows.JPanels.ForAll.JPanelImage;
import main.java.gui.designWindows.borders.TextRoundBorder;

@SuppressWarnings("serial")
public class JUserPanelForHeaderPanel extends JPanel{

	private JButton changePassword;
	private JButton logout;
	private JButton addUser;
	private JButton removeUser;
	private JButton infoAllUsers;

	public JUserPanelForHeaderPanel(){
		

		this.setLayout(new FlowLayout());
		this.setBackground(new Color(65, 72, 78));
		
		JPanelImage userImage = new JPanelImage("src/main/resources/images/users/chuck_norris.jpg");
		userImage.setBorder( new TextRoundBorder(Color.WHITE,5,4,0));
		this.add(userImage);	

		JPanel myOptions = new JPanel(new GridLayout(3,0));
		myOptions.setBackground(new Color(65, 72, 78));		
		this.add(myOptions);

		JLabel username = new JLabel("CHUCK NORRIS"); 
		username.setBackground(new Color(0,0,0));
		username.setForeground(Color.WHITE);		
		myOptions.add(username);
		
		changePassword = new JButton("Change Password");	
		myOptions.add(changePassword);		
		
		logout = new JButton("Logout");
		myOptions.add(logout);
		
			
		JPanel userOptions = new JPanel();
		userOptions.setLayout(new GridLayout(3,0));
		this.add(userOptions);
		
		addUser = new JButton(new ImageIcon("src/main/resources/images/removeUser.png"));
		userOptions.add(addUser);
		removeUser = new JButton(new ImageIcon("src/main/resources/images/infoUser.png"));
		userOptions.add(removeUser);
		infoAllUsers = new JButton(new ImageIcon("src/main/resources/images/addUser.png"));
		userOptions.add(infoAllUsers);
		
		
	}

	public JButton getChangePassword() {
		return changePassword;
	}

	public JButton getLogout() {
		return logout;
	}

	public JButton getAddUser() {
		return addUser;
	}

	public JButton getRemoveUser() {
		return removeUser;
	}

	public JButton getInfoAllUsers() {
		return infoAllUsers;
	}
	
	
	
}
