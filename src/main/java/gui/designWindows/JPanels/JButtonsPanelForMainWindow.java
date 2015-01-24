package main.java.gui.designWindows.JPanels;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JButtonsPanelForMainWindow extends JPanel{

	JButton getNearestAirships;
	JButton getTransgressinips;
	JButton getAirshipsWithLessPassengerThan;
	JButton patchAirship;
	JButton postAirship;
	JButton deleteAirship;

	
	public JButtonsPanelForMainWindow(){
		
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(65,72,78));
		
		getNearestAirships = new JButton("Get Nearest Airship of Geographical Coordinate");
		getTransgressinips = new JButton("Get Transgressing Airships");
		getAirshipsWithLessPassengerThan = new JButton("Get Airships with less Passengers");
		patchAirship = new JButton("Change Airship");
		postAirship = new JButton("Post Airship");
		deleteAirship = new JButton("Delete Airship");
		
		this.add(getNearestAirships);
		this.add(getTransgressinips);
		this.add(getAirshipsWithLessPassengerThan);
		this.add(patchAirship);
		this.add(postAirship);
		this.add(deleteAirship);
	}

	public JButton getGetNearestAirships() {
		return getNearestAirships;
	}

	public JButton getGetTransgressinips() {
		return getTransgressinips;
	}

	public JButton getGetAirshipsWithLessPassengerThan() {
		return getAirshipsWithLessPassengerThan;
	}

	public JButton getPatchAirship() {
		return patchAirship;
	}

	public JButton getPostAirship() {
		return postAirship;
	}

	public JButton getDeleteAirship() {
		return deleteAirship;
	}
	
	
}
