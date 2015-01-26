package main.java.gui.designWindows.windows.airshipWindows;

import java.awt.GridBagConstraints;

import main.java.gui.designWindows.GridBagUtils;
import main.java.gui.designWindows.jPanels.forPostAirshipWindow.JAirCorridorPanel;
import main.java.gui.designWindows.jPanels.forPostAirshipWindow.JGeographicalCoordinatesPanel;
import main.java.gui.designWindows.windows.WindowBase;

@SuppressWarnings("serial")
public class PatchGeographicalCoordinatesWindow extends WindowBase{

	private JGeographicalCoordinatesPanel geoPanel;
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
	private JAirCorridorPanel corridorPanel;
	
	public PatchGeographicalCoordinatesWindow() {
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
