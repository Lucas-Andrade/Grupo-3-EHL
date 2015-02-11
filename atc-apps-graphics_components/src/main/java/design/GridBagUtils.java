package design;


import java.awt.GridBagConstraints;
import java.awt.Insets;


public class GridBagUtils {
    
    
    private static final int TOPINSETS = 0;
    private static final int LEFTINSETS = 0;
    private static final int RIGHTINSETS = 0;
    private static final int BOTTOMINSETS = 0;
    
    /**
     * Unused private constructor
     */
    
    private GridBagUtils() {}
    
    public static GridBagConstraints createGridBagConstraints() {
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets( TOPINSETS, LEFTINSETS, RIGHTINSETS, BOTTOMINSETS );
        constraints.anchor = GridBagConstraints.CENTER;
        
        return constraints;
    }
    
    public static GridBagConstraints updateGridBagConstraints( GridBagConstraints constraints,
                                                               int gridy ) {
        
        constraints.gridy = gridy;
        
        return constraints;
    }
    
    public static GridBagConstraints updateGridBagConstraints( GridBagConstraints constraints,
                                                               int gridx, int gridy, Insets insect ) {
        
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.insets = insect;
        constraints.anchor = GridBagConstraints.CENTER;
        
        return constraints;
    }
}
