package main.java.gui.designWindows.windows.airshipWindows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import main.java.gui.designWindows.GridBagUtils;
import main.java.gui.designWindows.jPanels.forAll.JLablePlusJTextField;
import main.java.gui.designWindows.windows.WindowBase;

@SuppressWarnings("serial")
public class GetGeographicalCoordinatesParametersWindow extends WindowBase{

	
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
	private JLablePlusJTextField latitude;
	private JLablePlusJTextField longitude;
	
	public GetGeographicalCoordinatesParametersWindow() {
		super(350, 350,"src/main/resources/images/map-icon.png");
		
		initial();
	}

	private void initial() {
		
		 latitude = new JLablePlusJTextField("Latitude", 10, Color.WHITE);
		 this.getContentPane().add(latitude, GridBagUtils.updateGridBagConstraints(constraints, 1));
		 
		 longitude = new JLablePlusJTextField("Longitude", 10, Color.WHITE);
		 this.getContentPane().add(longitude, GridBagUtils.updateGridBagConstraints(constraints, 0,2,new Insets(0, 0, 20, 0)));
		this.setVisible(true);
		
	}

	public JLablePlusJTextField getLatitude() {
		return latitude;
	}

	public JLablePlusJTextField getLongitude() {
		return longitude;
	}


}
