package main.java.gui.designWindows;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridBagUtils {
	
//	public static GridBagLayout createMainGridBagLayout() {
//	
//		GridBagLayout gridBagLayout = new GridBagLayout();
//		
//		gridBagLayout.columnWidths = new int[] {0, 0};
//		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
//		gridBagLayout.columnWeights = new double[] {0.0, 0.0};
//		gridBagLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//		
//		return gridBagLayout;
//	}
	
	public static GridBagConstraints createGridBagConstraints() {
	
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.anchor = GridBagConstraints.CENTER;
		
		return constraints;
	}
	
	public static GridBagConstraints updateGridBagConstraints(GridBagConstraints constraints,
		int gridy) {
	
		constraints.gridy = gridy;
		
		return constraints;
	}
	
	public static GridBagConstraints updateGridBagConstraints(GridBagConstraints constraints,
		int gridx, int gridy, Insets insect) {
	
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.insets = insect;
		constraints.anchor = GridBagConstraints.CENTER;
		
		return constraints;
	}
}