package main.java.gui.To_be_eliminated.windows;

import java.awt.Color;

import main.java.gui.To_be_eliminated.panelsandutils.LabelPlusTextFieldPanel;

@SuppressWarnings ("serial")
public class PatchUserWindow extends WindowBase {
	
	// Fields
	
	private LabelPlusTextFieldPanel userPanel;
	private LabelPlusTextFieldPanel oldPasswordPanel;
	private LabelPlusTextFieldPanel newPasswordPanel;
	private LabelPlusTextFieldPanel newPasswordConfirmationPanel;
	
	// Constructor
	
	public PatchUserWindow(int width, int height) {
	
		super(width, height, "src/main/resources/images/user.png");
	}
	
	// Implementation of the method inherited from the WindowBase class
	
	@Override
	protected void createAndAddSpecificComponents() {
	
		userPanel = new LabelPlusTextFieldPanel("Username", Color.WHITE, null, 20, false);
		
		oldPasswordPanel = new LabelPlusTextFieldPanel("Old Password", Color.WHITE,
			"src/main/resources/images/locker.png", 20, true);
		
		newPasswordPanel = new LabelPlusTextFieldPanel("New Password", Color.WHITE, null, 20, true);
		
		newPasswordConfirmationPanel = new LabelPlusTextFieldPanel("Confirm New Password",
			Color.WHITE, null, 20, true);
		
		this.addComponent(userPanel, 1);
		this.addComponent(oldPasswordPanel, 2);
		this.addComponent(newPasswordPanel, 3);
		this.addComponent(newPasswordConfirmationPanel, 4);
	}
	
	// Public Get Methods
	
	public LabelPlusTextFieldPanel getUserPanel() {
	
		return userPanel;
	}
	
	public LabelPlusTextFieldPanel getOldPasswordPanel() {
	
		return oldPasswordPanel;
	}
	
	public LabelPlusTextFieldPanel getNewPasswordPanel() {
	
		return newPasswordPanel;
	}
	
	public LabelPlusTextFieldPanel getNewPasswordConfirmationPanel() {
	
		return newPasswordConfirmationPanel;
	}
}