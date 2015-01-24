package main.java.gui.fromDG_to_P.panelsandutils.airshipspanles;

import java.awt.Color;

import ByGD.panelsandutils.LabelPlusTextFieldPanel;

@SuppressWarnings ("serial")
public class GeographicalCoordinatesPanel extends PanelWithBorder {
	
	// Fields
	
	private LabelPlusTextFieldPanel latitude;
	private LabelPlusTextFieldPanel longitude;
	private LabelPlusTextFieldPanel altitude;
	
	// Constructor
	
	public GeographicalCoordinatesPanel(String borderTitle) {
	
		super(borderTitle, Color.WHITE);
	}
	
	// Implementation of the method inherited from the DefaultPanel class
	
	@Override
	protected void createAndAddSpecificComponents() {
	
		latitude = new LabelPlusTextFieldPanel("Latitude", Color.WHITE, null, 8, false);
		longitude = new LabelPlusTextFieldPanel("Longitude", Color.WHITE, null, 8, false);
		altitude = new LabelPlusTextFieldPanel("Altitude", Color.WHITE, null, 8, false);
		
		this.add(latitude);
		this.add(longitude);
		this.add(altitude);
	}
	
	// Public Get Methods
	
	public LabelPlusTextFieldPanel getLatitude() {
	
		return latitude;
	}
	
	public LabelPlusTextFieldPanel getLongitude() {
	
		return longitude;
	}
	
	public LabelPlusTextFieldPanel getAltitude() {
	
		return altitude;
	}
	
}