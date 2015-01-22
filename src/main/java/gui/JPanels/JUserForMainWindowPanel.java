package main.java.gui.JPanels;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.gui.Borders.TextRoundBorder;

@SuppressWarnings("serial")
public class JUserForMainWindowPanel extends JPanel {

	
	
	public JUserForMainWindowPanel(){
		
				
		JPanelImage userImage = new JPanelImage("src/main/resources/images/users/chuck_norris.jpg");
		userImage.setBorder( new TextRoundBorder(Color.WHITE,6,12,0));
		this.setBackground(new Color(65, 72, 78));
			
		this.add(userImage);
		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new GridLayout(3,0));
		panel.setBackground(new Color(65, 72, 78));
			
		JLabel username = new JLabel("CHUCK NORRIS"); 
		username.setForeground(Color.WHITE);		
		panel.add(username);
		
		JButton changePassword = new JButton("Change Password");	
		panel.add(changePassword);

		JButton logout = new JButton("Logout");
		panel.add(logout);
		
		JPanel userOptions = new JPanel();
		userOptions.setLayout(new GridLayout(3,0));
		this.add(userOptions);
		
		JButton addUser = new JButton(new ImageIcon("src/main/resources/images/removeUser.png"));
		userOptions.add(addUser);
		JButton removeUser = new JButton(new ImageIcon("src/main/resources/images/infoUser.png"));
		userOptions.add(removeUser);
		JButton infoAllUsers = new JButton(new ImageIcon("src/main/resources/images/addUser.png"));
		userOptions.add(infoAllUsers);
	}
	
	
}

