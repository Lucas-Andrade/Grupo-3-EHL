package main.java.gui.design.panels.postairshipwindowpanels;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import main.java.gui.design.panels.JLablePlusJTextField;

@SuppressWarnings("serial")
public class JAirCorridorPanel extends JPanel {

	private JLablePlusJTextField maxAltitude;
	private JLablePlusJTextField minAltitude;
	
	public JAirCorridorPanel(){
		
		
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(0, 0, 0, 0));

		maxAltitude = new JLablePlusJTextField("Max. Altitude", 8, Color.WHITE);
		this.add(maxAltitude);

		minAltitude = new JLablePlusJTextField("Min. Altitude", 8, Color.WHITE);
		this.add(minAltitude);


		TitledBorder titlealtitudeParameters = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE),"Air Corridor");
		titlealtitudeParameters.setTitleColor(Color.WHITE);
		titlealtitudeParameters.setTitleJustification(TitledBorder.LEFT);
		this.setBorder(titlealtitudeParameters);

		
		
	}

	public JLablePlusJTextField getMaxAltitude() {
		return maxAltitude;
	}

	public JLablePlusJTextField getMinAltitude() {
		return minAltitude;
	}
}
