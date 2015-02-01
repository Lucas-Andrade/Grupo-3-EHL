package main.java.gui.design.panels.mainwindowpanels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.domain.model.airships.Airship;
import main.java.gui.design.panels.JPanelImage;
import main.java.gui.design.panels.JScrollPanelForElements;

@SuppressWarnings ("serial")
public class JWorldMapWithAirships extends JScrollPanelForElements<Airship> {
	
	// Final Fields
	
	private final double IMAGESCALEFACTOR = 1.86;
	private final double ORIGINPOSITIONLONGITUDE = 25;
	private final double ORIGINPOSITIONYLATITUDE = 167;
	
	// Public Methods
	
	public JPanel createAJPanelWithWorldMapAndAirships(Iterable<Airship> airshipsFound) {
	
		JPanelImage.createImage worldMap = new JPanelImage.createImage(
			"/images/planisphere.png");
		worldMap.setLayout(null);

		this.add(worldMap);
		
		try {
			for (Airship airship : airshipsFound) {
				
				Double latitude = ORIGINPOSITIONYLATITUDE - IMAGESCALEFACTOR
					* (airship.getCoordinates().getLatitude().getValue());
				Double longitude = IMAGESCALEFACTOR
					* (airship.getCoordinates().getLongitude().getValue())
					- ORIGINPOSITIONLONGITUDE;
				
				JLabel labelairship;
				labelairship = new JLabel(new ImageIcon(
					"src/main/resources/images/militaryAirship.png"));
				labelairship.setBounds(longitude.intValue(), latitude.intValue(), 50, 50);
				worldMap.add(labelairship);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setPreferredSize(new Dimension(720, 390));
		this.setBackground(new Color(65, 72, 78));
		
		return this;
	}
}
