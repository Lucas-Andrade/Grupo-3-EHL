package main.java.gui.design.panels.postairshipwindowpanels;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import main.java.gui.design.GridBagUtils;


@SuppressWarnings( "serial" )
public class JCommonPostAirshipPanel extends JPanel {
    
    private static final int TRANSLUCENTCOMPONENT = 0;  
    private static final int NUMBEROFROWSFORMYOPTIONSPANEL = 2;
    private static final int NUMBEROFCOLUMNSFORMYOPTIONSPANEL = 1;
    private static final int TOPINSETS = 0;
    private static final int LEFTINSETS = 0;
    private static final int BOTTOMINSETS = 0;
    private static final int RIGHTINSETS = 0;
    private static final int GRIDXPOSITION = 0;
    private static final int GRIDYPOSITION = 0;
    
    
    private JGeographicalCoordinatesPanel geoCoodinates;
    private JAirCorridorPanel airCorridor;
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    
    public JCommonPostAirshipPanel() {
        initial();
    }
    
    private void initial() {
        
        this.setBackground( new Color( TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT ) );
        this.setLayout( new GridLayout( NUMBEROFROWSFORMYOPTIONSPANEL, NUMBEROFCOLUMNSFORMYOPTIONSPANEL ) );
        
        geoCoodinates = new JGeographicalCoordinatesPanel();
        this.add( geoCoodinates, GridBagUtils.updateGridBagConstraints( constraints, GRIDXPOSITION, GRIDYPOSITION,
                                                                        new Insets( TOPINSETS, LEFTINSETS, BOTTOMINSETS, RIGHTINSETS ) ) );
        
        airCorridor = new JAirCorridorPanel();
        this.add( airCorridor, GridBagUtils.updateGridBagConstraints( constraints, GRIDXPOSITION, GRIDYPOSITION,
                                                                        new Insets( TOPINSETS, LEFTINSETS, BOTTOMINSETS, RIGHTINSETS ) ) );
        
        
    }
    
    public JGeographicalCoordinatesPanel getGeoCoodinates() {
        return geoCoodinates;
    }
    
    public JAirCorridorPanel getAirCorridor() {
        return airCorridor;
    }
    
    
}
