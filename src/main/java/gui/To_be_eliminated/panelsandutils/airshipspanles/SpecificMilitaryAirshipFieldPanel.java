package main.java.gui.To_be_eliminated.panelsandutils.airshipspanles;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings ("serial")
public class SpecificMilitaryAirshipFieldPanel extends JPanel {
	
	// Fields
	private JLabel militaryArmorLabel;
	private JRadioButton YesButton;
	private JRadioButton NoButton;
	private ButtonGroup groupButtons;
	
	// Constructor
	
	public SpecificMilitaryAirshipFieldPanel() {
	
		this.setOpaque(false);
		
		militaryArmorLabel = new JLabel("Has Armor?");
		militaryArmorLabel.setForeground(Color.WHITE);
		
		YesButton = new JRadioButton("Yes");
		YesButton.setForeground(Color.WHITE);
		YesButton.setSelected(true);
		
		NoButton = new JRadioButton("No");
		NoButton.setForeground(Color.WHITE);
		
		groupButtons = new ButtonGroup();
		groupButtons.add(YesButton);
		groupButtons.add(NoButton);
		
		this.add(militaryArmorLabel);
		this.add(YesButton);
		this.add(NoButton);
	}
	
	// Public Get Methods
	
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