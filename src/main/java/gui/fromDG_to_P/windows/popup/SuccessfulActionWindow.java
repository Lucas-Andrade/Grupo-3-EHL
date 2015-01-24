package main.java.gui.fromDG_to_P.windows.popup;

import java.awt.Color;

import main.java.gui.JPanels.JPanelImage;

@SuppressWarnings ("serial")
public class SuccessfulActionWindow extends PopUpWindow {
	
	// Constructor
	
	public SuccessfulActionWindow(String message, Color color) {
	
		super(message, color);
	}
	
	// Implementation of the method inherited from the PopUpWindow class
	
	@Override
	protected void createAndAddSpecificComponents() {
	
		this.getContentPane().add(new JPanelImage("src/ByGD/successIcon.png"),
			this.getConstraints());
		
		this.setTitle("Successful Action");
	}
}