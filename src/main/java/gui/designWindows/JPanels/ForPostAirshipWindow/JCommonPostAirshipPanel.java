package main.java.gui.designWindows.JPanels.ForPostAirshipWindow;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import main.java.gui.designWindows.GridBagUtils;

@SuppressWarnings("serial")
public class JCommonPostAirshipPanel extends JPanel {



	private JGeographicalCoordinatesPanel geoCoodinates;
	private JAirCorridorPanel airCorridor;
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
	
	public JCommonPostAirshipPanel() {
		initial();
	}

	private void initial() {

		this.setBackground(new Color(0, 0, 0, 0));
		this.setLayout(new GridLayout(2, 1));

		 geoCoodinates = new JGeographicalCoordinatesPanel();
		this.add(geoCoodinates, GridBagUtils.updateGridBagConstraints(constraints,0, 0, new Insets(0, 0, 0, 0)));
		
		 airCorridor = new JAirCorridorPanel();
		this.add(airCorridor, GridBagUtils.updateGridBagConstraints(constraints,0, 0, new Insets(0, 0, 0, 0)));
		

	}

	public JGeographicalCoordinatesPanel getGeoCoodinates() {
		return geoCoodinates;
	}

	public JAirCorridorPanel getAirCorridor() {
		return airCorridor;
	}

	
}
