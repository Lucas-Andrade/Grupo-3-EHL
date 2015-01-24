package main.java.gui.To_be_eliminated.panelsandutils.airshipspanles;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings ("serial")
public class SpecificCivilAirshipFieldPanel extends JPanel {
	
	// Fields
	
	private JLabel passengersNumberLabel;
	private JTextField passengersNumberTextField;
	
	// Constructor
	
	public SpecificCivilAirshipFieldPanel() {
	
		this.setOpaque(false);
		
		passengersNumberLabel = new JLabel("Number of Passengers:");
		passengersNumberLabel.setForeground(Color.WHITE);
		
		passengersNumberTextField = new JTextField();
		passengersNumberTextField.setColumns(10);
		
		this.add(passengersNumberLabel);
		this.add(passengersNumberTextField);
	}
	
	// Public Get Methods
	
	public JLabel getPassengersNumberLabel() {
	
		return passengersNumberLabel;
	}
	
	public JTextField getPassengersNumberTextField() {
	
		return passengersNumberTextField;
	}
}