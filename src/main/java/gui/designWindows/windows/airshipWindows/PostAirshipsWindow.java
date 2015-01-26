package main.java.gui.designWindows.windows.airshipWindows;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import main.java.gui.designWindows.jPanels.forAll.JPanelImage;
import main.java.gui.designWindows.jPanels.forAll.JTwoButtonsPanel;
import main.java.gui.designWindows.jPanels.forPostAirshipWindow.JCommonPostAirshipPanel;
import main.java.gui.designWindows.jPanels.forPostAirshipWindow.JPanelWithSpecificCivilAirhipParameters;
import main.java.gui.designWindows.jPanels.forPostAirshipWindow.JPanelWithSpecificMilitaryAirhipParameters;
import main.java.gui.designWindows.windows.WindowBase;

@SuppressWarnings("serial")
public class PostAirshipsWindow extends WindowBase{

	
	
	private static final String MILITARYAIRSHIP = "Military Airship";
	private static final String CIVILAIRSHIP = "Civil Airship";
	
	private JCommonPostAirshipPanel militaryAirshipCommonPainel;
	private JCommonPostAirshipPanel civilAirshipCommonPainel;
	private JPanelWithSpecificMilitaryAirhipParameters specificMilitaryPanel;
	private JPanelWithSpecificCivilAirhipParameters specificCivilPanel;
	private JTwoButtonsPanel civilOkCancelPanel;
	private JTwoButtonsPanel militaryOkCancelPanel;

	public PostAirshipsWindow() {
		super(860,310);
		initialize();
		this.setVisible(true);

		
	}
	
	
	private void  initialize() {

		JTabbedPane TypeAirshipTabbedPane = new JTabbedPane();
		TypeAirshipTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		JPanel civilpanel = new JPanel(new FlowLayout());
		civilpanel.setBackground(new Color(65, 72, 78));
	
		JPanel militaryPanel = new JPanel(new FlowLayout());
		militaryPanel.setBackground(new Color(65, 72, 78));
	
		
		TypeAirshipTabbedPane.addTab(CIVILAIRSHIP, null, civilpanel, null);
		TypeAirshipTabbedPane.addTab(MILITARYAIRSHIP, null, militaryPanel, null);

		this.add(TypeAirshipTabbedPane,0);

	
		civilpanel.add(new JPanelImage("src/main/resources/images/civil.png","src/main/resources/images/add.png"));
		militaryPanel.add(new JPanelImage("src/main/resources/images/military.png","src/main/resources/images/add.png"));

		
		 civilAirshipCommonPainel = new JCommonPostAirshipPanel();
		civilpanel.add(civilAirshipCommonPainel);
	
		 militaryAirshipCommonPainel = new JCommonPostAirshipPanel();
		militaryPanel.add(militaryAirshipCommonPainel);

		specificCivilPanel = new JPanelWithSpecificCivilAirhipParameters();
		civilpanel.add(specificCivilPanel);
		specificMilitaryPanel = new JPanelWithSpecificMilitaryAirhipParameters();
		militaryPanel.add(specificMilitaryPanel);

		
	
	}


	public JCommonPostAirshipPanel getMilitaryAirshipCommonPainel() {
		return militaryAirshipCommonPainel;
	}


	public JCommonPostAirshipPanel getCivilAirshipCommonPainel() {
		return civilAirshipCommonPainel;
	}


	public JPanelWithSpecificMilitaryAirhipParameters getSpecificMilitaryPanel() {
		return specificMilitaryPanel;
	}


	public JPanelWithSpecificCivilAirhipParameters getSpecificCivilPanel() {
		return specificCivilPanel;
	}


	public JTwoButtonsPanel getCivilOkCancelPanel() {
		return civilOkCancelPanel;
	}


	public JTwoButtonsPanel getMilitaryOkCancelPanel() {
		return militaryOkCancelPanel;
	}

}