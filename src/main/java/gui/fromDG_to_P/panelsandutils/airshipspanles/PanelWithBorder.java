package main.java.gui.fromDG_to_P.panelsandutils.airshipspanles;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings ("serial")
public abstract class PanelWithBorder extends JPanel {
	
	// Fields
	
	private TitledBorder border;
	
	// Constructor
	
	public PanelWithBorder(String borderTitle, Color borderColor) {
	
		this.setLayout(new GridLayout(1,3));
		this.setBackground(new Color(0, 0, 0, 0));
		
		createAndAddSpecificComponents();
		
		border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borderColor),
			borderTitle);
		
		border.setTitleColor(Color.WHITE);
		border.setTitleJustification(TitledBorder.LEFT);
		
		this.setBorder(border);
	}
	
	// Protected Abstract Method To Be Implemented
	
	protected abstract void createAndAddSpecificComponents();
	
	// Public Get Methods
	
	public TitledBorder getBorder() {
	
		return border;
	}
}