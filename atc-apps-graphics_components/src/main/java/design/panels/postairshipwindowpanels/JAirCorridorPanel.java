package design.panels.postairshipwindowpanels;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import design.panels.JLablePlusJTextField;



  /**
   * 
   * Class who's instances represents panel that contains two {@link JLablePlusJTextField},
   * one for a minimum {@link Airship} altitude and the other for maximum {@link Airship} altitude. 
   * Also contains a {@link TextRoundBorder}.
   * This class extends {@link JPanel}, and has the follow configuration:
   * 
   *  <pre>      
   *       _____Air Corridor_________________________
   *      |                                          |
   *      |    Max. Altitude        Min. Altitude    | 
   *      |     __________          __________       |
   *      |    |__________|        |__________|      |
   *      |__________________________________________|
   *  </pre>
   * 
   * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
   */

@SuppressWarnings( "serial" )
public class JAirCorridorPanel extends JPanel {
    
    /**
     * {@code TRANSLUCENTCOMPONENT} int variable that represents panel Color (translucent Color).
     */
    private static final int TRANSLUCENTCOMPONENT = 0;
    /**
     * {@code JTEXTFIELDSIZE} int variable that represents {@link JTextField} size.
     */
    private static final int JTEXTFIELDSIZE = 8;
  
    /**
     * {@code maxAltitude} variable that represents  {@link JLablePlusJTextField}.
     */
    private JLablePlusJTextField maxAltitude;
    /**
     * {@code minAltitude} variable that represents  {@link JLablePlusJTextField}.
     */
    private JLablePlusJTextField minAltitude;
    
     //////////////////////
    //// Constructors ////
   //////////////////////
  
   /**
    * Public constructor that creates a new {@link JAirCorridorPanel} adding
    *  the two {@link JLablePlusJTextField} and {@link TextRoundBorder}.
    */
    public JAirCorridorPanel() {
        
        
        this.setLayout( new FlowLayout() );
        this.setBackground( new Color( TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT ) );
        
        maxAltitude = new JLablePlusJTextField( "Max. Altitude", JTEXTFIELDSIZE, Color.WHITE );
        this.add( maxAltitude );
        
        minAltitude = new JLablePlusJTextField( "Min. Altitude", JTEXTFIELDSIZE, Color.WHITE );
        this.add( minAltitude );
                
        TitledBorder titlealtitudeParameters =
                BorderFactory.createTitledBorder( BorderFactory.createLineBorder( Color.WHITE ),
                                                  "Air Corridor" );
        titlealtitudeParameters.setTitleColor( Color.WHITE );
        titlealtitudeParameters.setTitleJustification( TitledBorder.LEFT );
        this.setBorder( titlealtitudeParameters );
                
    }
    
      /////////////////
     // Get Methods //
    /////////////////
    
    /**
     * @return the {@code maxAltitude}.
     */
    public JLablePlusJTextField getMaxAltitude() {
        return maxAltitude;
    }
    
    /**
     * @return the {@code minAltitude}.
     */    
    public JLablePlusJTextField getMinAltitude() {
        return minAltitude;
    }
}
