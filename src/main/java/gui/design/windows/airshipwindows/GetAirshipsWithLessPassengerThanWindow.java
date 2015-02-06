package main.java.gui.design.windows.airshipwindows;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import main.java.gui.design.GridBagUtils;
import main.java.gui.design.panels.JLablePlusJTextField;
import main.java.gui.design.windows.WindowBase;


@SuppressWarnings( "serial" )
public class GetAirshipsWithLessPassengerThanWindow extends WindowBase {
    
    private static final int WINDOWWIDTH = 550;
    private static final int WINDOWHEIGHT = 550;
    private static final int TOPINSETS = 30;
    private static final int LEFTINSETS = 0;
    private static final int RIGHTINSETS = 0;
    private static final int BOTTOMINSETS = 30;
    private static final int GRIDXFORGRIDBAGLAYOUT = 0;
    private static final int GRIDYFORGRIDBAGLAYOUT = 1;
    private static final int JTEXTFIELDSIZE = 10;
    

    
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    private JLablePlusJTextField numberOfPassengers;
    
    
    public GetAirshipsWithLessPassengerThanWindow() {
        super( WINDOWWIDTH, WINDOWHEIGHT, "/images/passengers.png" );
        
        initial();
    }
    
    private void initial() {
        
        numberOfPassengers =
                new JLablePlusJTextField( "Maximum Number of Passengers", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( numberOfPassengers,
                  GridBagUtils.updateGridBagConstraints( constraints, GRIDXFORGRIDBAGLAYOUT, GRIDYFORGRIDBAGLAYOUT, 
                                                         new Insets( TOPINSETS, LEFTINSETS, BOTTOMINSETS, RIGHTINSETS) ) );
        this.setVisible( true );
        
    }
    
    public JLablePlusJTextField getNumberOfPassengers() {
        return numberOfPassengers;
    }
    
    
    
}
