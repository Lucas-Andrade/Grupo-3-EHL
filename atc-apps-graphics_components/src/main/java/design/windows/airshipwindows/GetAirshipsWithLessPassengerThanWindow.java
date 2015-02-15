package design.windows.airshipwindows;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import design.GridBagUtils;
import design.panels.JLablePlusJTextField;
import design.windows.WindowBase;

    
    /**
     *   Class who's instances represents panel that extends {@link WindowBase},  
     *   so it inherits a {@link JPanelImage}, {@link JTwoButtonsPanel} and {@link JTextArea} with error information.
     *   This instance also add a {@link JLablePlusJTextField} and has this configuration. 
     *   
     *  <pre>
     *     ____________________________________
     *    |                                    |
     *    |               Image                |
     *    |____________________________________|
     *    |                                    |
     *    |          JLabelPlusJTextFied       |
     *    |____________________________________|
     *    |                                    |
     *    |             Two buttons            |
     *    |____________________________________|
     *    |                                    |
     *    |             error text area        |
     *    |____________________________________|
     *    
     * </pre>
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */

@SuppressWarnings( "serial" )
public class GetAirshipsWithLessPassengerThanWindow extends WindowBase {
    
    
     //////////////////////////////////////////////////// 
    // Graphical Fields used only for design purposes //
   ////////////////////////////////////////////////////

    /**
     * {@code WINDOWWIDTH} int value that represents {@link GetAirshipsWithLessPassengerThanWindow} width. 
     */
    private static final int WINDOWWIDTH = 550;
    /**
     * {@code WINDOWHEIGHT} int value that represents {@link GetAirshipsWithLessPassengerThanWindow} height. 
     */
    private static final int WINDOWHEIGHT = 550;
    /**
     * {@code TOPINSETS} int value that represents the size of top borders used in
     * {@code GridBagConstraints}.
     */
    private static final int TOPINSETS = 30;
    /**
     * {@code LEFTINSETS} int value that represents the size of left borders used in
     * {@code GridBagConstraints}.
     */
    private static final int LEFTINSETS = 0;
    /**
     * {@code RIGHTINSETS} int value that represents the size of right borders used in
     * {@code GridBagConstraints}.
     */
    private static final int RIGHTINSETS = 0;
    /**
     * {@code BOTTOMINSETS} int value that represents the size of bottom borders used in
     * {@code GridBagConstraints}.
     */
    private static final int BOTTOMINSETS = 30;
    /**
     * {@code GRIDXFORGRIDBAGLAYOUT} int value that represents the column where is insert the components
     * into {@code GridBagConstraints}.
     */
    private static final int GRIDXFORGRIDBAGLAYOUT = 0;
    /**
     * {@code GRIDYFORGRIDBAGLAYOUT} int value that represents the row where is insert the components
     * into {@code GridBagConstraints}.
     */
    private static final int GRIDYFORGRIDBAGLAYOUT = 1;
    /**
     * {@code JTEXTFIELDSIZE} int value that represents the {@link JTextField} size.
     */
    private static final int JTEXTFIELDSIZE = 10;
    
     ////////////////////////////
    ///// Components Fields ////
   ////////////////////////////
    
    /**
     * {@code constraints} variable that represents the configuration used in the panel layout.
     */    
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    
    /**
     * {@code numberOfPassengers} variable that represents {@link JLablePlusJTextField}.
     */ 
    private JLablePlusJTextField numberOfPassengers;    
    
     //////////////////////
    //// Constructors ////
   //////////////////////
    
    /**
     * Public constructor that creates a new {@link MainWindow} adding {@link JLablePlusJTextField}.
     */    
    
    public GetAirshipsWithLessPassengerThanWindow() {
        super( WINDOWWIDTH, WINDOWHEIGHT, "/images/passengers.png" );
              
        numberOfPassengers =
                new JLablePlusJTextField( "Maximum Number of Passengers", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( numberOfPassengers,
                  GridBagUtils.updateGridBagConstraints( constraints, GRIDXFORGRIDBAGLAYOUT, GRIDYFORGRIDBAGLAYOUT, 
                                                         new Insets( TOPINSETS, LEFTINSETS, BOTTOMINSETS, RIGHTINSETS) ) );
        
    }
 
     /////////////////   
    // Get Methods //
   /////////////////  

    /**    
     * @return the {@code numberOfPassengers}
     */
    
    public JLablePlusJTextField getNumberOfPassengers() {
        return numberOfPassengers;
    }
       
}
