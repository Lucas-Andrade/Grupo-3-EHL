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
	private JTabbedPane typeAirshipTabbedPane;
	private JPanel civilPanel;
	private JPanel militaryPanel;
	
	public PostAirshipsWindow() {
	
		super(860, 310);
		initialize();
		this.setVisible(true);
		
	}
	
	private void initialize() {
	
		typeAirshipTabbedPane = new JTabbedPane();
		typeAirshipTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		civilPanel = new JPanel(new FlowLayout());
		civilPanel.setBackground(new Color(65, 72, 78));
		
		militaryPanel = new JPanel(new FlowLayout());
		militaryPanel.setBackground(new Color(65, 72, 78));
		
		typeAirshipTabbedPane.addTab(CIVILAIRSHIP, null, civilPanel, null);
		typeAirshipTabbedPane.addTab(MILITARYAIRSHIP, null, militaryPanel, null);
		
		this.add(typeAirshipTabbedPane, 0);
		
		civilPanel.add(new JPanelImage("src/main/resources/images/civil.png",
			"src/main/resources/images/add.png"));
		militaryPanel.add(new JPanelImage("src/main/resources/images/military.png",
			"src/main/resources/images/add.png"));
		
		civilAirshipCommonPainel = new JCommonPostAirshipPanel();
		civilPanel.add(civilAirshipCommonPainel);
		
		militaryAirshipCommonPainel = new JCommonPostAirshipPanel();
		militaryPanel.add(militaryAirshipCommonPainel);
		
		specificCivilPanel = new JPanelWithSpecificCivilAirhipParameters();
		civilPanel.add(specificCivilPanel);
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
	
	public JTabbedPane getTypeAirshipTabbedPane() {
	
		return typeAirshipTabbedPane;
	}
	
	public JPanel getCivilPanel() {
	
		return civilPanel;
	}
	
	public JPanel getMilitaryPanel() {
	
		return militaryPanel;
	}
}