package main.java.gui.designWindows.jPanels.forMainWindow;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JFooterPanelForMainWindow extends JPanel{

	private JButton getNearestAirships;
	private JButton getTransgressingAirships;
	private JButton getAirshipsWithLessPassengerThan;
	private JButton patchAirship;
	private JButton postAirship;
	private JButton deleteAirship;
	private JButton showAllAirships;

	
	public JFooterPanelForMainWindow(){
		
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(65,72,78));
		
		showAllAirships = new JButton("Show All Airships");
		getNearestAirships = new JButton("Airships Closest to Coordinates ");
		getTransgressingAirships = new JButton("Get Transgressing Airships");
		getAirshipsWithLessPassengerThan = new JButton("Get Airships with less Passengers");
		patchAirship = new JButton("Change Airship");
		postAirship = new JButton("Post Airship");
		deleteAirship = new JButton("Delete Airship");
		
		this.add(showAllAirships);
		this.add(getNearestAirships);
		this.add(getTransgressingAirships);
		this.add(getAirshipsWithLessPassengerThan);
		this.add(patchAirship);
		this.add(postAirship);
		this.add(deleteAirship);
	}

	public JButton getNearestAirships() {
		return getNearestAirships;
	}

	public JButton getTransgressingAirships() {
		return getTransgressingAirships;
	}

	public JButton getAirshipsWithLessPassengerThan() {
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

	public JButton getShowAllAirships() {
		return showAllAirships;
	}
}