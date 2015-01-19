package main.java.gui.JPanels;


import java.awt.Color;

import javax.swing.JPanel;

public class JPanelWithAishipsImages extends JPanel {


	private static final long serialVersionUID = 1L;
	private String path;

	
	public JPanelWithAishipsImages(String path) {
		
		this.path=path;
		initial();
	}


	private void initial() {
			
	 	this.add(new JPanelImage("src/main/resources/images/add.png"));
		this.add(new JPanelImage(path));
		this.setBackground(new Color(0,0,0,0));		
	}

}
