package design.windows.airshipwindows;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import design.GridBagUtils;
import design.panels.JLablePlusJTextField;
import design.windows.WindowBase;

    /**
     *   Class who's instances represents panel that extends {@link WindowBase},  
     *   so it inherits a {@link JPanelImage}, {@link JTwoButtonsPanel} and {@link JTextArea} with error information.
     *   This instance also add three {@link JLablePlusJTextField} and has this configuration: 
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
     *    |          JLabelPlusJTextFied       |
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
public class GetGeographicalCoordinatesParametersWindow extends WindowBase {
    
     //////////////////////////////////////////////////// 
    // Graphical Fields used only for design purposes //
   ////////////////////////////////////////////////////

    /**
     * {@code WINDOWWIDTH} int value that represents {@link GetGeographicalCoordinatesParametersWindow} width. 
     */
    private static final int WINDOWWIDTH = 370;
    /**
     * {@code WINDOWHEIGHT} int value that represents {@link GetGeographicalCoordinatesParametersWindow} height. 
     */
    private static final int WINDOWHEIGHT = 550;
    /**
     * {@code JTEXTFIELDSIZE} int value that represents the {@link JTextField} size.
     */
    private static final int JTEXTFIELDSIZE = 10;
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
     * {@code RIGHTINSETS} int value that represents the size of right borders used in
     * {@code GridBagConstraints}.
     */
    private static final int RIGHTINSETS = 20;
    /**
     * {@code BOTTOMINSETS} int value that represents the size of bottom borders used in
     * {@code GridBagConstraints}.
     */
    private static final int BOTTOMINSETS = 0;
    /**
     * {@code GRIDXFORGRIDBAGLAYOUT} int value that represents the column where is insert the components
     * into {@code GridBagConstraints}.
     */
    private static final int GRIDXFORGRIDBAGLAYOUT = 0;
    /**
     * {@code GRIDYFORLATITUDE} int value that represents the row where is insert the {@code latitude} 
     * {@link JLablePlusJTextField} into {@code GridBagConstraints}.
     */
    private static final int GRIDYFORLATITUDE = 1;
    /**
     * {@code GRIDYFORLONGITUDE} int value that represents the row where is insert the {@code longitude} 
     * {@link JLablePlusJTextField} into {@code GridBagConstraints}.
     */
    private static final int GRIDYFORLONGITUDE = 2;
    /**
     * {@code GRIDYFORAIRSHIPSNUMBER} int value that represents the row where is insert the {@code altitude} 
     * {@link JLablePlusJTextField} into {@code GridBagConstraints}.
     */
    private static final int GRIDYFORAIRSHIPSNUMBER = 3;
    
    ////////////////////////////
    ///// Components Fields ////
   ////////////////////////////
    
    /**
     * {@code constraints} variable that represents the configuration used in the panel layout.
     */    
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    /**
     * {@code latitude} variable that represents {@link JLablePlusJTextField} that is part of this {@link JFrame}.
     */
    private JLablePlusJTextField latitude;
    /**
     * {@code longitude} variable that represents {@link JLablePlusJTextField} that is part of this {@link JFrame}.
     */
    private JLablePlusJTextField longitude;
    /**
     * {@code airshipsNumber} variable that represents {@link JLablePlusJTextField} that is part of this {@link JFrame}.
     */
    private JLablePlusJTextField airshipsNumber;
    
        
    /**
     * Public constructor that creates a new {@link GetGeographicalCoordinatesParametersWindow} adding
     * three {@link JLablePlusJTextField}.      * 
     */
    
    public GetGeographicalCoordinatesParametersWindow() {
        super( WINDOWWIDTH, WINDOWHEIGHT, "/images/map-icon.png" );
               
        latitude = new JLablePlusJTextField( "Latitude", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( latitude, GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORLATITUDE ) );
        
        longitude = new JLablePlusJTextField( "Longitude", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( longitude,
                  GridBagUtils.updateGridBagConstraints( constraints, GRIDXFORGRIDBAGLAYOUT, GRIDYFORLONGITUDE,
                                                         new Insets( TOPINSETS, LEFTINSETS, RIGHTINSETS, BOTTOMINSETS ) ) );
        
        airshipsNumber = new JLablePlusJTextField( "Number of Airships", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( airshipsNumber,
                  GridBagUtils.updateGridBagConstraints( constraints, GRIDXFORGRIDBAGLAYOUT, GRIDYFORAIRSHIPSNUMBER,
                                                         new Insets( TOPINSETS, LEFTINSETS, RIGHTINSETS, BOTTOMINSETS ) ) );
        
        
        this.setVisible( true );
    }
    
     /////////////////   
    // Get Methods //
   /////////////////  

    /**
     * 
     * @return the {@code latitude}
     */
    public JLablePlusJTextField getLatitude() {
        return latitude;
    }
    /**
     * 
     * @return the {@code longitude}
     */
    public JLablePlusJTextField getLongitude() {
        return longitude;
    }
    /**
     * 
     * @return the {@code airshipsNumber}
     */
    public JLablePlusJTextField getAirshipsNumber() {
        return airshipsNumber;
    }
}
