package main.java.gui.designWindows.JPanels.ForMainWindow;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.gui.designWindows.borders.TextRoundBorder;

@SuppressWarnings("serial")
public class JBodyPanelForMainWindow extends JPanel{

	
	private JPanel airshipsScrollPane;

	public JBodyPanelForMainWindow(Database<Airship> airshipdatabase) {
		
		this.setLayout(new FlowLayout());
		
		airshipsScrollPane = new JWorldMapWithAirships().produceAJScrollPaneWithAllElements(airshipdatabase);
		this.add(new JWorldMapWithAirships().createAJPanelWithWorldMapAndAirships(airshipdatabase));
		this.add(airshipsScrollPane);
		this.setBackground(new Color(65,72,78));
		this.setBorder(new TextRoundBorder(Color.WHITE,6,12,0) );	
		
	}

	public JPanel getAirshipsScrollPane() {
		return airshipsScrollPane;
	}
	
}
