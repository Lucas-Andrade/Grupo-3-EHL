package main.java.gui.design.windows.airshipwindows;

import java.awt.GridBagConstraints;

import main.java.gui.design.GridBagUtils;
import main.java.gui.design.panels.postairshipwindowpanels.JAirCorridorPanel;
import main.java.gui.design.panels.postairshipwindowpanels.JGeographicalCoordinatesPanel;
import main.java.gui.design.windows.WindowBase;

@SuppressWarnings("serial")
public class PatchAirshipsWindow extends WindowBase{

	private JGeographicalCoordinatesPanel geoPanel;
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
	private JAirCorridorPanel corridorPanel;
	
	public PatchAirshipsWindow() {
		super(400, 400,"src/main/resources/images/edit.png","src/main/resources/images/civil.png");
		
		initial();
	}

	private void initial() {
		
		 geoPanel = new JGeographicalCoordinatesPanel();
		 this.getContentPane().add(geoPanel, GridBagUtils.updateGridBagConstraints(constraints, 1));
		 corridorPanel = new JAirCorridorPanel();
		 this.getContentPane().add(corridorPanel, GridBagUtils.updateGridBagConstraints(constraints, 2));
		this.setVisible(true);
		
	}

	public JGeographicalCoordinatesPanel getGeoPanel() {
		return geoPanel;
	}	
	
}
