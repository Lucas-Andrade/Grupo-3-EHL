package main.java.gui.fromDG_to_P.windows;

import java.awt.Color;

import main.java.gui.fromDG_to_P.panelsandutils.LabelPlusTextFieldPanel;

@SuppressWarnings ("serial")
public class LogInWindow extends WindowBase {
	
	// Fields
	
	private LabelPlusTextFieldPanel userPanel;
	private LabelPlusTextFieldPanel passwordPanel;
	
	// Constructor
	
	public LogInWindow(int width, int height) {
	
		super(width, height, "src/main/resources/images/radar.png");
	}
	
	// Implementation of the method inherited from the WindowBase class
	
	@Override
	protected void createAndAddSpecificComponents() {
	
		userPanel = new LabelPlusTextFieldPanel("Username", Color.WHITE, null, 20, false);
		
		passwordPanel = new LabelPlusTextFieldPanel("Password", Color.WHITE,
			"src/main/resources/images/locker.png", 20, true);
		
		this.addComponent(userPanel, 1);
		this.addComponent(passwordPanel, 2);
	}
	
	// Public Get Method
	
	public LabelPlusTextFieldPanel getUserPanel() {
	
		return userPanel;
	}
	
	public LabelPlusTextFieldPanel getPasswordPanel() {
	
		return passwordPanel;
	}
}