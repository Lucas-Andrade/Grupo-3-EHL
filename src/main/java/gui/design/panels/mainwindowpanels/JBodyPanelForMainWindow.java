package main.java.gui.design.panels.mainwindowpanels;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.gui.design.borders.TextRoundBorder;

@SuppressWarnings ("serial")
public class JBodyPanelForMainWindow extends JPanel {
	
	// Fields
	
	private JPanel airshipsScrollPane;
	private JPanel worldMapWithAirships;
	
	// Constructor
	
	public JBodyPanelForMainWindow(Database<Airship> airshipsDatabase,
		Iterable<Airship> airshipsFound) {
	
		this.setLayout(new FlowLayout());

		airshipsScrollPane = new JWorldMapWithAirships().produceAJScrollPaneWithAllElements(
			airshipsDatabase, airshipsFound);
		
		worldMapWithAirships = new JWorldMapWithAirships()
			.createAJPanelWithWorldMapAndAirships(airshipsFound);
		
		this.add(worldMapWithAirships);
		this.add(airshipsScrollPane);
		
		this.setBackground(new Color(65, 72, 78));
		this.setBorder(new TextRoundBorder(Color.WHITE, 6, 12, 0));
	}
	

	// Public Set Methods
	
	public void updateBodyPanel(Database<Airship> airshipsDatabase,
		Iterable<Airship> airshipsFound) {
	
		this.remove( worldMapWithAirships );
		this.remove( airshipsScrollPane );
		
		this.worldMapWithAirships = new JWorldMapWithAirships()
		.createAJPanelWithWorldMapAndAirships(airshipsFound);
		this.airshipsScrollPane = new JWorldMapWithAirships().produceAJScrollPaneWithAllElements(
				airshipsDatabase, airshipsFound);
		
		this.add(worldMapWithAirships);
		this.add(airshipsScrollPane);
		
		this.revalidate();
		this.repaint();
	}
	
	
	// Public Get Methods
	
	public JPanel getAirshipsScrollPane() {
	
		return airshipsScrollPane;
	}
	
	public JPanel getWorldMapWithAirships() {
	
		return worldMapWithAirships;

	}
}
