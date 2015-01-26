package main.java.gui.designWindows.jPanels.forAll;

import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class JTwoButtonsPanel extends JPanel {

	
//	private final String OK = "Ok";
//	private final String CANCEL = "Cancel";
	
	private JButton leftButton;
	private JButton rightButton;

	
	public JTwoButtonsPanel() {

		this("OK","Cancel");
		
		
	}
	public JTwoButtonsPanel(String leftButtonText,String rightButtonText) {

	if (leftButtonText == null || rightButtonText == null)
		throw new IllegalArgumentException();
	
	this.leftButton= new JButton(leftButtonText);
	 this.add(leftButton);
	 
	this.rightButton= new JButton(rightButtonText);
	this.add(rightButton);
	
	}
	
	public JButton getLeftButton() {
		return leftButton;
	}
	public JButton getRightButton() {
		return rightButton;
	}
	
	public void setLeftButton(JButton leftButton) {
		this.leftButton = leftButton;
	}
	public void setRightButton(JButton rightButton) {
		this.rightButton = rightButton;
	}
	
	

	
	
}
