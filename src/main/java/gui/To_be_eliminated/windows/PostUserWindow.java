package main.java.gui.To_be_eliminated.windows;

import java.awt.Color;

import main.java.gui.To_be_eliminated.panelsandutils.LabelPlusTextFieldPanel;
import main.java.gui.designWindows.JPanels.JPanelImage;

@SuppressWarnings ("serial")
public class PostUserWindow extends WindowBase {
	
	// Fields
	
	private LabelPlusTextFieldPanel userPanel;
	private LabelPlusTextFieldPanel passwordPanel;
	private LabelPlusTextFieldPanel emailPanel;
	private LabelPlusTextFieldPanel fullNamePanel;
	
	// Constructor
	
	public PostUserWindow(int width, int height) {
	
		super(width, height, "src/main/resources/images/user.png");
	}
	
	// Implementation of the method inherited from the WindowBase class
	
	@Override
	protected void createAndAddSpecificComponents() {
	
		JPanelImage postUserImage = new JPanelImage("src/main/resources/images/user.png");
		
		userPanel = new LabelPlusTextFieldPanel("Username", Color.WHITE, null, 20, false);
		
		passwordPanel = new LabelPlusTextFieldPanel("Password", Color.WHITE, "src/main/resources/images/locker.png",
			20, true);
		
		emailPanel = new LabelPlusTextFieldPanel("Email", Color.WHITE, null, 20, false);
		
		fullNamePanel = new LabelPlusTextFieldPanel("Full Name", Color.WHITE, null, 20, false);
		
		this.addComponent(postUserImage, 0);
		this.addComponent(userPanel, 1);
		this.addComponent(passwordPanel, 2);
		this.addComponent(emailPanel, 3);
		this.addComponent(fullNamePanel, 4);
	}
	
	// Public Get Methods
	
	public LabelPlusTextFieldPanel getUserPanel() {
	
		return userPanel;
	}
	
	public LabelPlusTextFieldPanel getPasswordPanel() {
	
		return passwordPanel;
	}
	
	public LabelPlusTextFieldPanel getEmailPanel() {
	
		return emailPanel;
	}
	
	public LabelPlusTextFieldPanel getFullNamePanel() {
	
		return fullNamePanel;
	}
}