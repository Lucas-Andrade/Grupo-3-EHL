package main.java.gui.designWindows.JPanels;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class JPanelWithSpecificMilitaryAirhipParameters extends JPanel {

	

	private final static String YES = "Yes";
	private final static String NO = "No";
	private JRadioButton YesButton;
	private JRadioButton NoButton;
	private ButtonGroup groupButtons;
	
	
	private static final long serialVersionUID = 1L;


	public JPanelWithSpecificMilitaryAirhipParameters() {
			initial();
	} 


	private void initial() {
		 
		this.setOpaque(false);
	 	
		 JLabel militaryArmorLabel = new JLabel("Has Armor?");
		 militaryArmorLabel.setForeground(Color.WHITE);
		 this.add(militaryArmorLabel);
				
		 YesButton = new JRadioButton(YES);
		 YesButton.setForeground(Color.WHITE);
		 YesButton.setMnemonic(KeyEvent.VK_B);
		 YesButton.setActionCommand(YES);
		 YesButton.setSelected(true);
		 YesButton.setOpaque(false);
		 this.add(YesButton);
		
		 
		 NoButton = new JRadioButton(NO);
		 NoButton.setForeground(Color.WHITE);
		 NoButton.setMnemonic(KeyEvent.VK_B);
		 NoButton.setActionCommand(NO);
		 NoButton.setOpaque(false);
		 this.add(NoButton);
		 	 
		 groupButtons = new ButtonGroup();
		 groupButtons.add(YesButton);
		 groupButtons.add(NoButton);
		 
		 this.setBorder(BorderFactory.createEmptyBorder(10, 0, 16, 100));
		
	}


	public JRadioButton getYesButton() {
		return YesButton;
	}

	public JRadioButton getNoButton() {
		return NoButton;
	}


	public ButtonGroup getGroupButtons() {
		return groupButtons;
	}


	
	
	

}
