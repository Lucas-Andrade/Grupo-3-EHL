package main.java.gui.designWindows.jPanels.forPostFirshipWindow;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import main.java.gui.designWindows.jPanels.forAll.JLablePlusJTextField;

@SuppressWarnings("serial")
public class JGeographicalCoordinatesPanel extends JPanel {

	private JLablePlusJTextField latitude;
	private JLablePlusJTextField longitude;
	private JLablePlusJTextField altitude;
	
	public 	JGeographicalCoordinatesPanel(){
		
		
	this.setLayout(new FlowLayout());
	this.setBackground(new Color(0, 0, 0, 0));

	latitude = new JLablePlusJTextField("Latitude", 8, Color.WHITE);
	this.add(latitude);

	longitude = new JLablePlusJTextField("Longitude", 8, Color.WHITE);
	this.add(longitude);

	altitude = new JLablePlusJTextField("Altitude", 8, Color.WHITE);
	this.add(altitude);


	TitledBorder titlegeoCoordinates = BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.WHITE), "Geographical Coordinates");
	
	titlegeoCoordinates.setTitleColor(Color.WHITE);
	titlegeoCoordinates.setTitleJustification(TitledBorder.LEFT);
	
	this.setBorder(titlegeoCoordinates);
	
	}

	
	
	public JLablePlusJTextField getLatitude() {
		return latitude;
	}

	public JLablePlusJTextField getLongitude() {
		return longitude;
	}

	public JLablePlusJTextField getAltitude() {
		return altitude;
	}	
	
}
