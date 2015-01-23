package main.java.gui.JPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.gui.Borders.TextRoundBorder;

@SuppressWarnings("serial")
public class JUserForMainWindowPanel extends JPanel {

	JButton addUser;
	JButton removeUser;
	JButton infoAllUsers;
	JButton logout;
	JButton changePassword;
	public JUserForMainWindowPanel(){
		
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(65, 72, 78));
		
		JLogoPanel logopanel = new JLogoPanel(); 
		this.add(logopanel,FlowLayout.LEFT);
		
		JPanel oneColorPanel = new JPanel();
		oneColorPanel.setPreferredSize(new Dimension(800,100));
		oneColorPanel.setBackground(new Color(65, 72, 78));
		this.add(oneColorPanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		this.add(panel);
		panel.setBackground(new Color(65, 72, 78));
		
		JPanelImage userImage = new JPanelImage("src/main/resources/images/users/chuck_norris.jpg");
		userImage.setBorder( new TextRoundBorder(Color.WHITE,6,12,0));
		panel.add(userImage);	
	
		JPanel myOptions = new JPanel(new GridLayout(3,0));
		myOptions.setBackground(new Color(65, 72, 78));		
		panel.add(myOptions);

		JLabel username = new JLabel("CHUCK NORRIS"); 
		username.setForeground(Color.WHITE);		
		myOptions.add(username);		
		changePassword = new JButton("Change Password");	
		myOptions.add(changePassword);		
		logout = new JButton("Logout");
		myOptions.add(logout);
		
			
		JPanel userOptions = new JPanel();
		userOptions.setLayout(new GridLayout(3,0));
		panel.add(userOptions);
		
		addUser = new JButton(new ImageIcon("src/main/resources/images/removeUser.png"));
		userOptions.add(addUser);
		removeUser = new JButton(new ImageIcon("src/main/resources/images/infoUser.png"));
		userOptions.add(removeUser);
		infoAllUsers = new JButton(new ImageIcon("src/main/resources/images/addUser.png"));
		userOptions.add(infoAllUsers);
	
	}

	
	
	
	public JButton getJButtonAddUser() {
		return addUser;
	}

	public void setJButtonAddUser(JButton addUser) {
		this.addUser = addUser;
	}

	public JButton getJButtonRemoveUser() {
		return removeUser;
	}

	public void setJButtonRemoveUser(JButton removeUser) {
		this.removeUser = removeUser;
	}

	public JButton getJButtonInfoAllUsers() {
		return infoAllUsers;
	}

	public void setJButtonInfoAllUsers(JButton infoAllUsers) {
		this.infoAllUsers = infoAllUsers;
	}

	public JButton getJButtonLogout() {
		return logout;
	}

	public void setJButtonLogout(JButton logout) {
		this.logout = logout;
	}

	public JButton getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(JButton changePassword) {
		this.changePassword = changePassword;
	}
	
	
	
	
}

