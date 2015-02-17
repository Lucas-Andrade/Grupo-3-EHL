package design.windows.airshipwindows;


import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import design.GridBagUtils;
import design.panels.postairshipwindowpanels.JAirCorridorPanel;
import design.panels.postairshipwindowpanels.JGeographicalCoordinatesPanel;
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
public class PatchAirshipsWindow extends WindowBase {
    
     //////////////////////////////////////////////////// 
    // Graphical Fields used only for design purposes //
   ////////////////////////////////////////////////////

    /**
     * {@code WINDOWWIDTH} int value that represents {@link PatchAirshipsWindow} width. 
     */    
    private static final int WINDOWWIDTH = 400;
    /**
     * {@code WINDOWHEIGHT} int value that represents {@link PatchAirshipsWindow} height. 
     */
    private static final int WINDOWHEIGHT = 400;
    /**
     * {@code GRIDYFORGEOPANEL} int value that represents the row where is insert {@code geoPanel}
     * into {@code GridBagConstraints}.
     */
    private static final int GRIDYFORGEOPANEL = 1;
    /**
     * {@code GRIDYFORCORRIDORPANEL} int value that represents the row where is insert {@code corridorPanel}
     * into {@code GridBagConstraints}.
     */
    private static final int GRIDYFORCORRIDORPANEL = 2;
    
     ////////////////////////////
    ///// Components Fields ////
   ////////////////////////////
    
    /**
     * {@code constraints} variable that represents the configuration used in the panel layout.
     */        
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    /**
     * {@code geoPanel} variable that represents {@link JGeographicalCoordinatesPanel}.
     */
    private JGeographicalCoordinatesPanel geoPanel;
    /**
     * {@code corridorPanel} variable that represents {@link JAirCorridorPanel}.
     */
    private JAirCorridorPanel corridorPanel;
      
     //////////////////////
    //// Constructors ////
   //////////////////////
    
    /**
     * Public constructor that creates a new {@link PatchAirshipsWindow} adding {@link JGeographicalCoordinatesPanel} and 
     *  {@link JAirCorridorPanel}.
     */     
    
    public PatchAirshipsWindow() {
        
        super( WINDOWWIDTH, WINDOWHEIGHT, "/images/edit.png", "/images/civil.png" );
                
        geoPanel = new JGeographicalCoordinatesPanel();
        this.getContentPane()
            .add( geoPanel, GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORGEOPANEL ) );
        corridorPanel = new JAirCorridorPanel();
        this.getContentPane().add( corridorPanel,
                                   GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORCORRIDORPANEL ) );
        this.setVisible( true );
        
    }
    
    
    /////////////////   
   // Get Methods //
  /////////////////  

    /**    
     * @return the {@code geoPanel}
     */
    public JGeographicalCoordinatesPanel getGeoPanel() {
        return geoPanel;
    }
    
}
