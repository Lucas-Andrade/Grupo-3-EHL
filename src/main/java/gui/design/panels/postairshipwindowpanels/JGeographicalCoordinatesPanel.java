package main.java.gui.design.panels.postairshipwindowpanels;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import main.java.domain.model.airships.Airship;
import main.java.gui.design.borders.TextRoundBorder;
import main.java.gui.design.panels.JLablePlusJTextField;
    /**
     * 
     * Class who's instances represents panel that contains three {@link JLablePlusJTextField},
     * one for a latitude {@link Airship} parameter the other for longitude {@link Airship} parameter
     * and the third for altitude {@link Airship} parameter. 
     * Also contains a {@link TextRoundBorder}.
     * This class extends {@link JPanel}, and has the follow configuration:
     * 
     *  <pre>      
     *       _____Geographical Coordinates______________________________
     *      |                                                           |
     *      |      Latitude            Longitude            Altitude    | 
     *      |     __________          __________          __________    |
     *      |    |__________|        |__________|        |__________|   |
     *      |___________________________________________________________|
     *  </pre>
     * 
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */

@SuppressWarnings( "serial" )
public class JGeographicalCoordinatesPanel extends JPanel {
   
    /**
     * {@code TRANSLUCENTCOMPONENT} int variable that represents panel Color (translucent Color).
     */
    private static final int TRANSLUCENTCOMPONENT = 0; 
    /**
     * {@code JTEXTFIELDSIZE} int variable that represents {@link JTextField} size.
     */
    private static final int JTEXTFIELDSIZE = 8;  

    /**
     * {@code latitude} variable that represents  {@link JLablePlusJTextField}.
     */
    private JLablePlusJTextField latitude;
    /**
     * {@code longitude} variable that represents  {@link JLablePlusJTextField}.
     */
    private JLablePlusJTextField longitude;
    /**
     * {@code altitude} variable that represents  {@link JLablePlusJTextField}.
     */
    private JLablePlusJTextField altitude;
    
     //////////////////////
    //// Constructors ////
   //////////////////////
  
   /**
    * Public constructor that creates a new {@link JGeographicalCoordinatesPanel} adding
    *  the three {@link JLablePlusJTextField} and {@link TextRoundBorder}.
    */
    public JGeographicalCoordinatesPanel() {
        
        
        this.setLayout( new FlowLayout() );
        this.setBackground( new Color( TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT ) );
        
        latitude = new JLablePlusJTextField( "Latitude", JTEXTFIELDSIZE, Color.WHITE );
        this.add( latitude );
        
        longitude = new JLablePlusJTextField( "Longitude", JTEXTFIELDSIZE, Color.WHITE );
        this.add( longitude );
        
        altitude = new JLablePlusJTextField( "Altitude", JTEXTFIELDSIZE, Color.WHITE );
        this.add( altitude );
        
        
        TitledBorder titlegeoCoordinates =
                BorderFactory.createTitledBorder( BorderFactory.createLineBorder( Color.WHITE ),
                                                  "Geographical Coordinates" );
        
        titlegeoCoordinates.setTitleColor( Color.WHITE );
        titlegeoCoordinates.setTitleJustification( TitledBorder.LEFT );
        
        this.setBorder( titlegeoCoordinates );
        
    }
    
    /////////////////
    // Get Methods //
   /////////////////
   
   /**
    * @return the {@code latitude}.
    */    
    public JLablePlusJTextField getLatitude() {
        return latitude;
    }
    
    /**
     * @return the {@code longitude}.
     */  
    public JLablePlusJTextField getLongitude() {
        return longitude;
    }
    
    /**
     * @return the {@code altitude}.
     */  
    public JLablePlusJTextField getAltitude() {
        return altitude;
    }
    
}
