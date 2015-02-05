package main.java.gui.design.windows.airshipwindows;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import main.java.gui.design.GridBagUtils;
import main.java.gui.design.panels.JLablePlusJTextField;
import main.java.gui.design.windows.WindowBase;


@SuppressWarnings( "serial" )
public class GetGeographicalCoordinatesParametersWindow extends WindowBase {
    
    private static final int WINDOWWIDTH = 370;
    private static final int WINDOWHEIGHT = 550;
    private static final int JTEXTFIELDSIZE = 10;
    private static final int TOPINSETS = 0;
    private static final int LEFTINSETS = 0;
    private static final int RIGHTINSETS = 20;
    private static final int BOTTOMINSETS = 0;
    private static final int GRIDXFORGRIDBAGLAYOUT = 0;
    private static final int GRIDYFORLATITUDE = 1;
    private static final int GRIDYFORLONGITUDE = 2;
    private static final int GRIDYFORALTITUDE = 3;
    
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    private JLablePlusJTextField latitude;
    private JLablePlusJTextField longitude;
    private JLablePlusJTextField airshipsNumber;
    
    public GetGeographicalCoordinatesParametersWindow() {
        super( WINDOWWIDTH, WINDOWHEIGHT, "/images/map-icon.png" );
        
        initial();
    }
    
    private void initial() {
        
        latitude = new JLablePlusJTextField( "Latitude", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( latitude, GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORLATITUDE ) );
        
        longitude = new JLablePlusJTextField( "Longitude", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( longitude,
                  GridBagUtils.updateGridBagConstraints( constraints, GRIDXFORGRIDBAGLAYOUT, GRIDYFORLONGITUDE,
                                                         new Insets( TOPINSETS, LEFTINSETS, RIGHTINSETS, BOTTOMINSETS ) ) );
        
        airshipsNumber = new JLablePlusJTextField( "Airships Number", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( airshipsNumber,
                  GridBagUtils.updateGridBagConstraints( constraints, GRIDXFORGRIDBAGLAYOUT, GRIDYFORALTITUDE,
                                                         new Insets( TOPINSETS, LEFTINSETS, RIGHTINSETS, BOTTOMINSETS ) ) );
        
        
        this.setVisible( true );
    }
    
    public JLablePlusJTextField getLatitude() {
        return latitude;
    }
    
    public JLablePlusJTextField getLongitude() {
        return longitude;
    }
    
    public JLablePlusJTextField getAirshipsNumber() {
        return airshipsNumber;
    }
}
