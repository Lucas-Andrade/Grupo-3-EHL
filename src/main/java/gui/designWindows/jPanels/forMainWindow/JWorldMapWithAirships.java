package main.java.gui.designWindows.jPanels.forMainWindow;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.domain.model.airships.Airship;
import main.java.gui.designWindows.jPanels.JScrollPanelForElements;
import main.java.gui.designWindows.jPanels.forAll.JPanelImage;




@SuppressWarnings("serial")
public class JWorldMapWithAirships extends JScrollPanelForElements<Airship>{

	private final double IMAGESCALEFACTOR=1.86;
	private final double ORIGINPOSITIONLONGITUDE=25;
	private final double ORIGINPOSITIONYLATITUDE=167;
	
	
	public JPanel createAJPanelWithWorldMapAndAirships(Iterable<Airship> database) {
		

		JPanelImage.createImage worldMap = new JPanelImage.createImage("src/main/resources/images/planisphere.png");
		worldMap.setLayout(null);
	    this.add(worldMap);
			
			
			try {
				for(Airship airship:database){
					
					
					 Double latitude =  ORIGINPOSITIONYLATITUDE-IMAGESCALEFACTOR*(airship.getCoordinates().getLatitude().getValue());
					 Double longitude = IMAGESCALEFACTOR*(airship.getCoordinates().getLongitude().getValue())-ORIGINPOSITIONLONGITUDE;
					
					JLabel labelairship ;
					labelairship =new JLabel(new ImageIcon("src/main/resources/images/militaryAirship.png"));   
					labelairship.setBounds(longitude.intValue(), latitude.intValue(),50,50); 
				    worldMap.add(labelairship);
								
							
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			this.setPreferredSize(new Dimension(720,390));
			this.setBackground(new Color(65, 72, 78)); 
			
			
			return this;
			
		}

	
	
	
	
	
	
}
