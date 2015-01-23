package main.java.gui.fromDG_to_P;

import java.awt.Color;

import main.java.gui.JPanels.JPanelImage;

public class LogInWindow extends WindowBase {
	
	// Fields
	
	private JLablePlusJTextField userPanel;
	private JLablePlusJTextField passwordPanel;
	
	// Constructor
	
	public LogInWindow(int width, int height) {
	
		super(width, height);
		
		initialize();
	}
	
	// Private Auxiliar Method
	
	private void initialize() {
	
		JPanelImage radarIcon = new JPanelImage("src/ByD/radar.png");
		
		userPanel = new JLablePlusJTextField("Username", Color.WHITE, null, 20);
		
		passwordPanel = new JLablePlusJTextField("Password", Color.WHITE, "src/ByD/locker.png", 20);
		
		this.addComponent(radarIcon, 0);
		this.addComponent(userPanel, 1);
		this.addComponent(passwordPanel, 2);
	}
	
	// Public Get Method
	
	public JLablePlusJTextField getUserPanel() {
	
		return userPanel;
	}
	
	public JLablePlusJTextField getPasswordPanel() {
	
		return passwordPanel;
	}
}