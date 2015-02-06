package main.java.gui.design.panels.postairshipwindowpanels;


import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

    /**
     * 
     * Class who's instances represents panel who contains one {@link JLabel} and {@link JTextField}.
     * This panel extends {@link JPanel}. The panel has this configuration :
     * 
     * <pre>
     *        ________________________________________
     *       |               ____________________    |
     *       |      JLabel  |_____JTextField_____|   |            
     *       |_______________________________________| 
     * </pre>
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */

@SuppressWarnings( "serial" )
public class JPanelWithSpecificCivilAirhipParameters extends JPanel {
    
    // INSTANCE FIELD
    
      ////////////////////////////////////////////////////
     // Graphical Fields used only for design purposes //
    ////////////////////////////////////////////////////
    
    /**
     * {@code COLUMNSNUMBER} int value that represents {@link JTextField} size.
     */
    private static final int COLUMNSNUMBER = 10;
    /**
     * {@code TOPBORDER} int value that represents width of the top, in pixels.
     */
    private static final int TOPBORDER = 10;
    /**
     * {@code LEFTBORDER} int value that represents width of the left, in pixels.
     */
    private static final int LEFTBORDER = 0;
    /**
     * {@code BOTTOMBORDER} int value that represents width of the bottom, in pixels.
     */
    private static final int BOTTOMBORDER = 20;
    /**
     * {@code RIGHTBORDER} int value that represents width of the right, in pixels.
     */
    private static final int RIGHTBORDER = 100;
        
      ////////////////////////////
     ///// Components Fields ////
    ////////////////////////////
   
    /**
     * {@code numberPassengersTextField} variable that represents {@link JTextField}.
     */
    private JTextField numberPassengersTextField;
    /**
     * {@code numberPassengerLabel} variable that represents {@link JLabel}.
     */
    private JLabel numberPassengerLabel;
    
    /**
     * Public constructor that creates a new {@link JPanelWithSpecificCivilAirhipParameters} adding
     *  the one {@link JLable} and {@link JTextField} side to side.
     */
    public JPanelWithSpecificCivilAirhipParameters() {
     
        this.setOpaque( false );
           
        numberPassengerLabel = new JLabel( "Passengers :" );
          numberPassengerLabel.setForeground( Color.WHITE );
        this.add( numberPassengerLabel );
        
        
        numberPassengersTextField = new JTextField();
        numberPassengersTextField.setColumns( COLUMNSNUMBER );
        this.add( numberPassengersTextField );
        
        
        this.setBorder( BorderFactory.createEmptyBorder( TOPBORDER, LEFTBORDER, BOTTOMBORDER, RIGHTBORDER ) );
        
    }
    
     /////////////////
    // Get Methods //
   /////////////////
   
   /**
    * @return the {@code numberPassengersTextField}.
    */
    public JTextField getNumberPassengerJTextField() {
        
        return numberPassengersTextField;
    }
    
    /**
     * @return the {@code numberPassengerLabel}.
     */
    public JLabel getNumberPassengerJLabel() {
        
        return numberPassengerLabel;
    }
    
}
