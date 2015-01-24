package main.java.gui.fromDG_to_P.panelsandutils.airshipspanles;


@SuppressWarnings ("serial")
public class MilitaryAirshipPanel extends AirshipsPanel {
	
	// Fields
	
	private SpecificMilitaryAirshipFieldPanel specificFieldPanel;
	
	// Constructor
	
	public MilitaryAirshipPanel() {
	
		super("src/ByGD/add.png", "src/ByGD/military.png");
	}
	
	// Implementation of the method inherited from the AishipsPanel class
	
	@Override
	protected void createAndAddSpecificComponents() {
		
		specificFieldPanel = new SpecificMilitaryAirshipFieldPanel();
		
		this.add(specificFieldPanel);
	}
	
	// Public Get Methods
	
	public SpecificMilitaryAirshipFieldPanel getSpecificFieldPanel() {
		
		return specificFieldPanel;
	}
}