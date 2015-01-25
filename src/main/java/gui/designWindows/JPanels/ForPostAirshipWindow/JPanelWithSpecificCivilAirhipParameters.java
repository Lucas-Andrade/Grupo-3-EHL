package main.java.gui.designWindows.JPanels.ForPostAirshipWindow;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPanelWithSpecificCivilAirhipParameters extends JPanel {


	private static final long serialVersionUID = 1L;
	private JTextField numberPassengersTextField;
	private JLabel numberPassengerLabel;
	 
	public JPanelWithSpecificCivilAirhipParameters() {
		Initial();
	}


	private void Initial() {

		
		this.setOpaque(false);	
		
		 JLabel numberPassengerLabel = new JLabel("Passengers :");
		 numberPassengerLabel.setForeground(Color.WHITE);
		 this.add(numberPassengerLabel);
		
		
		 numberPassengersTextField = new JTextField();
		 numberPassengersTextField.setColumns(10);
		 this.add(numberPassengersTextField);	
		 
		 this.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 100));
		 
		
		 
	}

	public JTextField getNumberPassengerJTextField(){
		
		return numberPassengersTextField;		
	}
	
	public JLabel getNumberPassengerJLabel(){
		
		return numberPassengerLabel;		
	}
	
}
