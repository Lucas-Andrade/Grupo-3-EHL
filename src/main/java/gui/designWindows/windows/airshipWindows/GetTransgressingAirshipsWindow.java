package main.java.gui.designWindows.windows.airshipWindows;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.gui.designWindows.jPanels.forMainWindow.JWorldMapWithAirships;

@SuppressWarnings("serial")
public class GetTransgressingAirshipsWindow extends JFrame{

	private JPanel airshipsScrollPane;

	public GetTransgressingAirshipsWindow(Database<Airship> airshipdatabase) {
		
		
		airshipsScrollPane = new JWorldMapWithAirships().createAJPanelWithWorldMapAndAirships(airshipdatabase); 		
		this.getContentPane().add(airshipsScrollPane);
		this.setSize(720,400);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/radar.png"));
		this.setTitle("Air Traffic Control");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	
}