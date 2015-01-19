package main.java.gui.JPanels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

		GridBagLayout CommonPanelBox = new GridBagLayout();
		CommonPanelBox.columnWidths = new int[] { 170,170 };
		CommonPanelBox.rowHeights = new int[] { 80, 0, 0, 15 };
		CommonPanelBox.columnWeights = new double[] { 1.0 };
		CommonPanelBox.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		
		this.setLayout(CommonPanelBox);
		this.setBackground(new Color(0,0,0,0));
		
		JLablePlusJTextField latitude = new JLablePlusJTextField("Latitude", 8,Color.WHITE);
		GridBagConstraints LatitudeBox = new GridBagConstraints();
		
		LatitudeBox.insets = new Insets(0, 0, 0, 0);
		LatitudeBox.gridx = 0;
		LatitudeBox.gridy = 0;
		
		
		this.add(latitude, LatitudeBox);

		
		JLablePlusJTextField minAltitude = new JLablePlusJTextField("Min. Altitude", 8,Color.WHITE);		
		GridBagConstraints minAltitudeBox = new GridBagConstraints();
		
		minAltitudeBox.insets = new Insets(0, 0, 0, 0);
		minAltitudeBox.gridx = 0;
		minAltitudeBox.gridy = 1;
		
		this.add(minAltitude, minAltitudeBox);

		
		JLablePlusJTextField longitude = new JLablePlusJTextField("Longitude",8,Color.WHITE);
		GridBagConstraints LongitudeBox = new GridBagConstraints();
		LongitudeBox.insets = new Insets(0, 0, 0, 0);
		LongitudeBox.gridx = 1;
		LongitudeBox.gridy = 0;
		
		this.add(longitude, LongitudeBox);
		
		JLablePlusJTextField maxAltitude = new JLablePlusJTextField("Max. Altitude", 8,Color.WHITE);
		GridBagConstraints maxAltitudeBox = new GridBagConstraints();
		maxAltitudeBox.insets = new Insets(0, 0, 0, 0);
		maxAltitudeBox.gridx = 1;
		maxAltitudeBox.gridy = 1;
		
		this.add(maxAltitude, maxAltitudeBox);
			
		JLablePlusJTextField altitude = new JLablePlusJTextField("Altitude", 8,Color.WHITE);		
		GridBagConstraints altitudeBox = new GridBagConstraints();
		altitudeBox.insets = new Insets(0, 0, 0, 0);
		altitudeBox.gridx = 0;
		altitudeBox.gridy = 2;
		
		this.add(altitude, altitudeBox);
		
		
		Border whiteline = BorderFactory.createLineBorder(Color.WHITE);
		TitledBorder title = BorderFactory.createTitledBorder(whiteline, "Geographical Coordiantes");
		title.setTitleColor(Color.WHITE);			
		title.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(title);
			
	}
	


	
	
}
	
	


