package main.java.gui.ByGD;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings ("serial")
public class ButtonsPanel extends JPanel {
	
	// Fields
	
	private JButton leftButton;
	private JButton rightButton;
	
	// Constructors
	
	public ButtonsPanel() {
	
		this("Ok", "Cancel");
	}
	
	public ButtonsPanel(String leftButtonText, String rightButtonText) {
	
		if (leftButtonText == null || rightButtonText == null)
			throw new IllegalArgumentException();
		
		this.setOpaque(false);
		
		leftButton = new JButton(leftButtonText);
		this.add(leftButton);
		
		rightButton = new JButton(rightButtonText);
		this.add(rightButton);
	}
	
	// Public Get Methods
	
	public JButton getLeftButton() {
	
		return leftButton;
	}
	
	public JButton getRightButton() {
	
		return rightButton;
	}
}