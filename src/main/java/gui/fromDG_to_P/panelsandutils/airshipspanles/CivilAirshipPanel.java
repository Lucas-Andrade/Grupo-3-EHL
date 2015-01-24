package main.java.gui.fromDG_to_P.panelsandutils.airshipspanles;

@SuppressWarnings ("serial")
public class CivilAirshipPanel extends AirshipsPanel {
	
	// Fields
	
	private SpecificCivilAirshipFieldPanel specificFieldPanel;
	
	// Constructor
	
	public CivilAirshipPanel() {
	
		super("src/ByGD/add.png", "src/ByGD/civil.png");
	}
	
	// Implementation of the method inherited from the AishipsPanel class
	
	@Override
	protected void createAndAddSpecificComponents() {
	
		specificFieldPanel = new SpecificCivilAirshipFieldPanel();
		
		this.add(specificFieldPanel);
	}
	
	// Public Get Methods
	
	public SpecificCivilAirshipFieldPanel getSpecificFieldPanel() {
	
		return specificFieldPanel;
	}
}