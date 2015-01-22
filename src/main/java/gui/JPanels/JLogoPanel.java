package main.java.gui.JPanels;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JLogoPanel extends JPanel {

	
	public JLogoPanel(){
		

		this.setLayout(new FlowLayout());
		this.setBackground(new Color(65, 72, 78));
			
		JPanelImage radarIcon = new JPanelImage("src/main/resources/images/radar.png");	
		this.add(radarIcon);
				
		JPanelImage titleLogo = new JPanelImage("src/main/resources/images/logo.png");
		this.add(titleLogo);
					
	}
	
}
