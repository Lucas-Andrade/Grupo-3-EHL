package main.java.gui.design.windows.airshipwindows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import main.java.gui.design.GridBagUtils;
import main.java.gui.design.panels.JLablePlusJTextField;
import main.java.gui.design.windows.WindowBase;

@SuppressWarnings("serial")
public class GetAirshipsWithLessPassengerThenWindow extends WindowBase{

	
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
	private JLablePlusJTextField numberOfPassengers;
	
	
	public GetAirshipsWithLessPassengerThenWindow() {
		super(350, 350,"src/main/resources/images/passengers.png");
		
		initial();
	}

	private void initial() {
		
		 numberOfPassengers = new JLablePlusJTextField("Maximum Number of Passengers", 10, Color.WHITE);
		this.getContentPane().add(numberOfPassengers, GridBagUtils.updateGridBagConstraints(constraints, 0,1,new Insets(30, 0, 30, 0)));
		 this.setVisible(true);
		
	}

	public JLablePlusJTextField getLatitude() {
		return numberOfPassengers;
	}



}
