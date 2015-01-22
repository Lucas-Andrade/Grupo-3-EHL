package main.java.gui.ByGD;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridBagUtils {
	
	public static GridBagConstraints createGridBagConstraints() {
	
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 10, 0);
		constraints.anchor = GridBagConstraints.CENTER;
		
		return constraints;
	}
	
	public static GridBagConstraints updateGridYBagConstraints(GridBagConstraints constraints, int y) {
	
		constraints.gridy = y;
		
		return constraints;
	}
}