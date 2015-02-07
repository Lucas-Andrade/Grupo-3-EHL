package main.java.gui.design.panels.postairshipwindowpanels;


import AirCorridor;
import Airship;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import main.java.gui.design.GridBagUtils;
    
    /**
     * 
     * Class who's instances represents panel that contains all commons panels needed to create 
     * an {@link Airship}. This two panels are {@link JGeographicalCoordinatesPanel} and {@link JAirCorridorPanel}.
     * This class extends {@link JPanel}, and has the follow configuration:
     * 
     * <pre>      
     *  _______________________________________________________________________ 
     * |                                                                       |
     * |      _____Geographical Coordinates______________________________      |
     * |     |                                                           |     |
     * |     |      Latitude            Longitude            Altitude    |     |
     * |     |     __________          __________          __________    |     |
     * |     |    |__________|        |__________|        |__________|   |     |
     * |     |___________________________________________________________|     |
     * |                                                                       |
     * |                                                                       |
     * |                  _____Air Corridor_________________________           |
     * |                 |                                          |          |
     * |                 |    Max. Altitude        Min. Altitude    |          |
     * |                 |     __________          __________       |          |
     * |                 |    |__________|        |__________|      |          |
     * |                 |__________________________________________|          |
     * |_______________________________________________________________________|
     *  </pre>
     * 
     * 
     * 
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */

@SuppressWarnings( "serial" )
public class JCommonPostAirshipPanel extends JPanel {
 
    
    /**
     * {@code TRANSLUCENTCOMPONENT} int variable that represents panel Color (translucent Color).
     */
    private static final int TRANSLUCENTCOMPONENT = 0;  
    /**
     * {@code TOPINSETS} int value that represents the size of top borders used in
     * {@code GridBagConstraints}.
     */
    private static final int TOPINSETS = 0;
    /**
     * {@code LEFTINSETS} int value that represents the size of left borders used in
     * {@code GridBagConstraints}.
     */
    private static final int LEFTINSETS = 0;
    /**
     * {@code BOTTOMINSETS} int value that represents the size of bottom borders used in
     * {@code GridBagConstraints}.
     */
    private static final int BOTTOMINSETS = 0;
    /**
     * {@code RIGHTINSETS} int value that represents the size of right borders used in
     * {@code GridBagConstraints}.
     */
    private static final int RIGHTINSETS = 0;
    /**
     * {@code GRIDXPOSITION} int value that represents the column where is insert the components
     * into {@code GridBagConstraints}.
     */
    private static final int GRIDXPOSITION = 0;
    /**
     * {@code GRIDYPOSITION} int value that represents the rows where is insert the components
     * into {@code GridBagConstraints}.
     */
    private static final int GRIDYPOSITION = 0;
    /**
     * {@code NUMBEROFROWSFORMYOPTIONSPANEL} int value that represents the number of rows of {@link GridLayout}.
     */
    private static final int NUMBEROFROWSFORGRIDLAYOUT = 2;
    /**
     * {@code NUMBEROFROWSFORMYOPTIONSPANEL} int value that represents the number of columns of {@link GridLayout}.
     */
    private static final int NUMBEROFCOLUMNSFORGRIDlAYOUT = 1;
    
      ////////////////////////////
     ///// Components Fields ////
    ////////////////////////////
    
    /**
     * {@code geoCoodinates} variable that represents @Link {@link JGeographicalCoordinatesPanel} that composes this panel.
     */
    private JGeographicalCoordinatesPanel geoCoodinates;
    /**
     * {@code geoCoodinates} variable that represents @Link {@link AirCorridor} that is part of this panel.
     */
    private JAirCorridorPanel airCorridor;
    
    /**
     * {@code constraints} variable that represents the configuration used in the panel layout.
     */
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    
    
    /**
     * Public constructor that creates a new {@link JCommonPostAirshipPanel} adding one
     *  {@link JGeographicalCoordinatesPanel} and one {@link AirCorridor}.
     */
    public JCommonPostAirshipPanel() {
          
        this.setBackground( new Color( TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT ) );
        this.setLayout( new GridLayout( NUMBEROFROWSFORGRIDLAYOUT, NUMBEROFCOLUMNSFORGRIDlAYOUT ) );
        
        geoCoodinates = new JGeographicalCoordinatesPanel();
        this.add( geoCoodinates, GridBagUtils.updateGridBagConstraints( constraints, GRIDXPOSITION, GRIDYPOSITION,
                                                                        new Insets( TOPINSETS, LEFTINSETS, BOTTOMINSETS, RIGHTINSETS ) ) );
        
        airCorridor = new JAirCorridorPanel();
        this.add( airCorridor, GridBagUtils.updateGridBagConstraints( constraints, GRIDXPOSITION, GRIDYPOSITION,
                                                                         new Insets( TOPINSETS, LEFTINSETS, BOTTOMINSETS, RIGHTINSETS ) ) );
        
    }
    
    /////////////////   
    // Get Methods //
   /////////////////  

    /**
     * @return the {@code geoCoodinates}.
     */    
    public JGeographicalCoordinatesPanel getGeoCoodinates() {
        return geoCoodinates;
    }
    
    /**
     * @return the {@code airCorridor}.
     */    
    public JAirCorridorPanel getAirCorridor() {
        return airCorridor;
    }
    
    
}
