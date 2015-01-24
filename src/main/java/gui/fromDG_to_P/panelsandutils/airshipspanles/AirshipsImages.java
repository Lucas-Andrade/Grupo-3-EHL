package main.java.gui.fromDG_to_P.panelsandutils.airshipspanles;

import java.awt.Color;

import javax.swing.JPanel;

import main.java.gui.JPanels.JPanelImage;

@SuppressWarnings ("serial")
public class AirshipsImages extends JPanel {
	
	// Constructor
	
	public AirshipsImages(String firstImagePath, String secondImagePath) {
	
		this.add(new JPanelImage(firstImagePath));
		this.add(new JPanelImage(secondImagePath));
		
		this.setBackground(new Color(0, 0, 0, 0));
	}
}