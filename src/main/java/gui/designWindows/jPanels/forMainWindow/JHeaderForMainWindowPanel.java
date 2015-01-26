package main.java.gui.designWindows.jPanels.forMainWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.jPanels.forAll.JPanelImage;
import main.java.gui.functionalWindows.functionalUserWindows.FunctionalJUserPanelForHeaderPanel;




@SuppressWarnings("serial")
public class JHeaderForMainWindowPanel extends JPanel {

	private JUserPanelForHeaderPanel userPanel;
	
	public JHeaderForMainWindowPanel(Database<User> userdatabase, User user){
		
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(65, 72, 78));
		
		
		JPanelImage logopanel = new JPanelImage("src/main/resources/images/radar.png","src/main/resources/images/logo.png"); 
		this.add(logopanel);
	
		JPanel oneColorPanel = new JPanel();
		oneColorPanel.setPreferredSize(new Dimension(600,100));
		oneColorPanel.setBackground(new Color(65, 72, 78));
		this.add(oneColorPanel);
		
		
		userPanel = new FunctionalJUserPanelForHeaderPanel(new JUserPanelForHeaderPanel(), userdatabase, user).getHeaderPanel();
		this.add(userPanel);
	
	}

	public JUserPanelForHeaderPanel getUserPanel() {
		return userPanel;
	}
}

