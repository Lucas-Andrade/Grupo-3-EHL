package main.java.gui.design.panels.postairshipwindowpanels;


import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings( "serial" )
public class JPanelWithSpecificCivilAirhipParameters extends JPanel {
    
    private static final int COLUMNSNUMBER = 10;
    private static final int TOPBORDER = 10;
    private static final int LEFTBORDER = 0;
    private static final int BOTTOMBORDER = 20;
    private static final int RIGHTBORDER = 100;
        
    private JTextField numberPassengersTextField;
    private JLabel numberPassengerLabel;
    
    public JPanelWithSpecificCivilAirhipParameters() {
        initial();
    }
    
    
    private void initial() {
        
        
        this.setOpaque( false );
        
        numberPassengerLabel = new JLabel( "Passengers :" );
        numberPassengerLabel.setForeground( Color.WHITE );
        this.add( numberPassengerLabel );
        
        
        numberPassengersTextField = new JTextField();
        numberPassengersTextField.setColumns( COLUMNSNUMBER );
        this.add( numberPassengersTextField );
        
        this.setBorder( BorderFactory.createEmptyBorder( TOPBORDER, LEFTBORDER, BOTTOMBORDER, RIGHTBORDER ) );
        
        
        
    }
    
    public JTextField getNumberPassengerJTextField() {
        
        return numberPassengersTextField;
    }
    
    public JLabel getNumberPassengerJLabel() {
        
        return numberPassengerLabel;
    }
    
}
