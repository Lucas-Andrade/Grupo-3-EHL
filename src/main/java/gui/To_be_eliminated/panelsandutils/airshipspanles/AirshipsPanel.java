package main.java.gui.To_be_eliminated.panelsandutils.airshipspanles;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings ("serial")
public abstract class AirshipsPanel extends JPanel {
	
	// Fields
	
	private GeographicalCoordinatesPanel coordinatesPanel;
	private AirCorridorPanel airCorridorPanel;
	private AirshipsImages imagesPanel;
	
	// Constructor
	
	public AirshipsPanel(String firstImagePath, String secondImagePath) {
	
		this.setBackground(new Color(0, 0, 0, 0));
		this.setLayout(new GridLayout(4, 1));
		
		imagesPanel = new AirshipsImages(firstImagePath, secondImagePath);
		
		coordinatesPanel = new GeographicalCoordinatesPanel("Geographical Coordinates");
		airCorridorPanel = new AirCorridorPanel("Air Corridor");
		
		this.add(imagesPanel);
		this.add(coordinatesPanel);
		this.add(airCorridorPanel);
		
		createAndAddSpecificComponents();
	}
	
	// Protected Abstract Method To Be Implemented
	
	protected abstract void createAndAddSpecificComponents();
	
	// Public Get Methods
	
	public GeographicalCoordinatesPanel getCoordinatesPanel() {
	
		return coordinatesPanel;
	}
	
	public AirCorridorPanel getAirCorridorPanel() {
	
		return airCorridorPanel;
	}
}