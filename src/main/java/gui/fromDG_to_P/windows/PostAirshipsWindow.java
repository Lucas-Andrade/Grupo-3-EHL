package main.java.gui.fromDG_to_P.windows;

import javax.swing.JTabbedPane;

import ByGD.panelsandutils.airships.CivilAirshipPanel;
import ByGD.panelsandutils.airships.MilitaryAirshipPanel;

@SuppressWarnings ("serial")
public class PostAirshipsWindow extends WindowBase {
	
	// Fields
	
	private JTabbedPane typeOfAirshipTabPane;
	private CivilAirshipPanel civilAirshipPanel;
	private MilitaryAirshipPanel militaryAirshipPanel;
	
	// Constructor
	
	public PostAirshipsWindow(int width, int height) {
	
		super(width, height, null);
	}
	
	// Implementation of the method inherited from the WindowBase class
	
	@Override
	protected void createAndAddSpecificComponents() {
	
		typeOfAirshipTabPane = new JTabbedPane();
		typeOfAirshipTabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		civilAirshipPanel = new CivilAirshipPanel();
		militaryAirshipPanel = new MilitaryAirshipPanel();
		
		typeOfAirshipTabPane.addTab("Civil Airship", null, civilAirshipPanel, null);
		typeOfAirshipTabPane.addTab("Military Airship", null, militaryAirshipPanel, null);
		
		this.getContentPane().add(typeOfAirshipTabPane);
	}
	
	// Public Get Methods
	
	public JTabbedPane getTypeOfAirshipTabPane() {
	
		return typeOfAirshipTabPane;
	}
	
	public CivilAirshipPanel getCivilAirshipPanel() {
	
		return civilAirshipPanel;
	}
	
	public MilitaryAirshipPanel getMilitaryAirshipPanel() {
	
		return militaryAirshipPanel;
	}
}