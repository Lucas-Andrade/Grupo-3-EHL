package main.java.gui.designWindows.windows;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import main.java.gui.designWindows.JPanels.JCommonPostAirshipPanel;
import main.java.gui.designWindows.JPanels.JDialogWithBackground;
import main.java.gui.designWindows.JPanels.JOkCancelPanel;
import main.java.gui.designWindows.JPanels.JPanelWithAishipsImages;
import main.java.gui.designWindows.JPanels.JPanelWithSpecificCivilAirhipParameters;
import main.java.gui.designWindows.JPanels.JPanelWithSpecificMilitaryAirhipParameters;

public class PostAirshipsWindow extends JDialogWithBackground{

	
	private static final long serialVersionUID = 1L;
	
	private static final String MILITARYAIRSHIP = "Military Airship";
	private static final String CIVILAIRSHIP = "Civil Airship";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

			new PostAirshipsWindow();

			}
		});
	}

	public PostAirshipsWindow() {
		super(new Color(65, 72, 78), 400, 600);
		initialize();
	}
	
	
	private void initialize() {


		JTabbedPane TypeAirshipTabbedPane = new JTabbedPane();
		TypeAirshipTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		JPanel civilpanel = new JPanel();
		civilpanel.setBackground(new Color(65, 72, 78));
		civilpanel.setSize(370, 550);
		
		JPanel militaryPanel = new JPanel();
		militaryPanel.setBackground(new Color(65, 72, 78));
		militaryPanel.setSize(370, 550);
		
		TypeAirshipTabbedPane.addTab(CIVILAIRSHIP, null, civilpanel, null);
		TypeAirshipTabbedPane.addTab(MILITARYAIRSHIP, null, militaryPanel, null);

		this.getContentPane().add(TypeAirshipTabbedPane);

		JPanelWithAishipsImages cena = new JPanelWithAishipsImages(
				"src/main/resources/images/civil.png");
		civilpanel.add(cena);
		
		
	
		militaryPanel.add(new JPanelWithAishipsImages(
				"src/main/resources/images/military.png"));

		
		JCommonPostAirshipPanel cena2 = new JCommonPostAirshipPanel();
		civilpanel.add(cena2);
		
		cena2.getJAltitudePanel().getJTextField().getText();
		
		militaryPanel.add(new JCommonPostAirshipPanel());

		civilpanel.add(new JPanelWithSpecificCivilAirhipParameters());
		militaryPanel.add(new JPanelWithSpecificMilitaryAirhipParameters());

		civilpanel.add(new JOkCancelPanel());
		militaryPanel.add(new JOkCancelPanel());
		this.setVisible(true);
	}

}