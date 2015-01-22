package main.java.gui.JPanels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class JCommonPostAirshipPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JCommonPostAirshipPanel() {
		initial();
	}

	private void initial()  {


		this.setBackground(new Color(0,0,0,0));
		this.setLayout(new GridLayout(2,1));
	
		JPanel geoCoordinates = new JPanel();
		geoCoordinates.setLayout(new FlowLayout());
		geoCoordinates.setBackground(new Color(0,0,0,0));

		
		JLablePlusJTextField latitude = new JLablePlusJTextField("Latitude", 8,Color.WHITE);		
		geoCoordinates.add(latitude);
		
		JLablePlusJTextField longitude = new JLablePlusJTextField("Longitude",8,Color.WHITE);
		geoCoordinates.add(longitude);
	
		JLablePlusJTextField altitude = new JLablePlusJTextField("Altitude", 8,Color.WHITE);
		geoCoordinates.add(altitude);
				

		JPanel  altitudeParameters= new JPanel();
		altitudeParameters.setLayout(new FlowLayout());
		altitudeParameters.setBackground(new Color(0,0,0,0));	
		
		JLablePlusJTextField maxAltitude = new JLablePlusJTextField("Max. Altitude", 8,Color.WHITE);
		altitudeParameters.add(maxAltitude);
		
		JLablePlusJTextField minAltitude = new JLablePlusJTextField("Min. Altitude", 8,Color.WHITE);
		altitudeParameters.add(minAltitude);
					
		Border whiteline = BorderFactory.createLineBorder(Color.WHITE);
		
		TitledBorder titlegeoCoordinates = BorderFactory.createTitledBorder(whiteline, "Geographical Coordinates");
		titlegeoCoordinates.setTitleColor(Color.WHITE);			
		titlegeoCoordinates.setTitleJustification(TitledBorder.LEFT);
		geoCoordinates.setBorder(titlegeoCoordinates);
				
		
		TitledBorder titlealtitudeParameters = BorderFactory.createTitledBorder(whiteline, "Air Corridor");
		titlealtitudeParameters.setTitleColor(Color.WHITE);			
		titlealtitudeParameters.setTitleJustification(TitledBorder.LEFT);
		altitudeParameters.setBorder(titlealtitudeParameters);
		
		this.add(geoCoordinates);		
		this.add(altitudeParameters);

	}
	


	
	
}
	
	


