package main.java.gui.JPanels;

import javax.swing.JButton;
import javax.swing.JPanel;


public class JOkCancelPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private final String OK = "Ok";
	private final String CANCEL = "Cancel";
	
	private String leftButton;
	private String rightButton;
	private JButton okButton ;
	private JButton cancelButton;
	
	public JOkCancelPanel() {

		this.leftButton=OK;
		this.rightButton=CANCEL;
		initial();
		
		
	}
	public JOkCancelPanel(String leftButton, String rightButton) {

	if (leftButton == null || rightButton == null)
		throw new IllegalArgumentException();
	
	this.leftButton=leftButton;
	this.rightButton=rightButton;
	initial();
	
	
	}
	
	private void initial(){
		
		 this.setOpaque(false);
		 
		 okButton = new JButton(leftButton);
		 okButton.setActionCommand(leftButton);
		 this.add(okButton);
		 
		 cancelButton = new JButton(rightButton);
		 cancelButton.setActionCommand(rightButton);
		 this.add(cancelButton);
	
	}

	public JButton getleftButton(){
		
		return okButton;
		
	}
	
	public JButton getRightButton(){
		
		return cancelButton;
		
	}
	
}
