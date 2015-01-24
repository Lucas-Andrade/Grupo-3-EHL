package main.java.gui.fromDG_to_P.panelsandutils.airshipspanles;

import java.awt.Color;

import ByGD.panelsandutils.LabelPlusTextFieldPanel;

@SuppressWarnings ("serial")
public class AirCorridorPanel extends PanelWithBorder {
	
	// Fields
	
	private LabelPlusTextFieldPanel minAltitude;
	private LabelPlusTextFieldPanel maxAltitude;
	
	// Constructor
	
	public AirCorridorPanel(String borderTitle) {
	
		super(borderTitle, Color.WHITE);
	}
	
	// Implementation of the method inherited from the DefaultPanel class
	
	@Override
	protected void createAndAddSpecificComponents() {
	
		minAltitude = new LabelPlusTextFieldPanel("Maximum Altitude", Color.WHITE, null, 8, false);
		maxAltitude = new LabelPlusTextFieldPanel("Minimum Altitude", Color.WHITE, null, 8, false);
		
		this.add(minAltitude);
		this.add(maxAltitude);
	}
	
	// Public Get Methods
	
	public LabelPlusTextFieldPanel getMinAltitude() {
	
		return minAltitude;
	}
	
	public LabelPlusTextFieldPanel getMaxAltitude() {
	
		return maxAltitude;
	}
}