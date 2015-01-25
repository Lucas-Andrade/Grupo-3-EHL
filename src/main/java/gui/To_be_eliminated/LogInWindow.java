//package main.java.gui.To_be_eliminated;
//
//import java.awt.Color;
//
//import main.java.gui.designWindows.JPanels.JPanelImage;
//import main.java.gui.designWindows.windows.WindowBase;
//
//public class LogInWindow extends WindowBase {
//	
//	// Fields
//	
//	private JLablePlusJTextField userPanel;
//	private JLablePlusJTextField passwordPanel;
//	
//	// Constructor
//	
//	public LogInWindow(int width, int height) {
//	
//		super(width, height, "src/main/resources/images/radar.png");
//		
//		initialize();
//	}
//	
//	// Private Auxiliar Method
//	
//	private void initialize() {
//	
//		
//		userPanel = new JLablePlusJTextField("Username", Color.WHITE, null, 20);
//		
//		passwordPanel = new JLablePlusJTextField("Password", Color.WHITE, "src/ByD/locker.png", 20);
//		
//		this.addComponent(userPanel, 1);
//		this.addComponent(passwordPanel, 2);
//	}
//	
//	// Public Get Method
//	
//	public JLablePlusJTextField getUserPanel() {
//	
//		return userPanel;
//	}
//	
//	public JLablePlusJTextField getPasswordPanel() {
//	
//		return passwordPanel;
//	}
//}