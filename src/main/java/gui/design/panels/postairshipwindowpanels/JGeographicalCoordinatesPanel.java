package main.java.gui.design.panels.postairshipwindowpanels;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import main.java.gui.design.panels.JLablePlusJTextField;


@SuppressWarnings( "serial" )
public class JGeographicalCoordinatesPanel extends JPanel {
    
    private static final int TRANSLUCENTCOMPONENT = 0;  
    private static final int JTEXTFIELDSIZE = 8;  

    
    private JLablePlusJTextField latitude;
    private JLablePlusJTextField longitude;
    private JLablePlusJTextField altitude;
    
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
    
    
    
    public JLablePlusJTextField getLatitude() {
        return latitude;
    }
    
    public JLablePlusJTextField getLongitude() {
        return longitude;
    }
    
    public JLablePlusJTextField getAltitude() {
        return altitude;
    }
    
}
