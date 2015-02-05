package main.java.gui.design.panels.postairshipwindowpanels;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import main.java.gui.design.panels.JLablePlusJTextField;


@SuppressWarnings( "serial" )
public class JAirCorridorPanel extends JPanel {
    
    private static final int TRANSLUCENTCOMPONENT = 0;
    private static final int JTEXTFIELDSIZE = 8;
    
    
    private JLablePlusJTextField maxAltitude;
    private JLablePlusJTextField minAltitude;
    
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
    
    public JLablePlusJTextField getMaxAltitude() {
        return maxAltitude;
    }
    
    public JLablePlusJTextField getMinAltitude() {
        return minAltitude;
    }
}
