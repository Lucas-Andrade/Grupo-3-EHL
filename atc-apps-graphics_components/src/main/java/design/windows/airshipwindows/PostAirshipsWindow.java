package design.windows.airshipwindows;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import design.panels.JPanelImage;
import design.panels.JTwoButtonsPanel;
import design.panels.postairshipwindowpanels.JCommonPostAirshipPanel;
import design.panels.postairshipwindowpanels.JPanelWithSpecificCivilAirhipParameters;
import design.panels.postairshipwindowpanels.JPanelWithSpecificMilitaryAirhipParameters;
import design.windows.WindowBase;



/**
 *   Class who's instances represents panel that extends {@link WindowBase},  
 *   so it inherits a {@link JTwoButtonsPanel} and {@link JTextArea} with error information.
 *   This instance also add {@link JTabbedPane} with two options, and has this configuration: 
 *   
 *  <pre>
 *     ____________________________________
 *    |____________________________________|
 *    |           |         |              |
 *    |  Option1  | Option2 |              |
 *    |___________|_________|______________|
 *    |                                    |
 *    |                                    |
 *    |           JTabbedPane              |
 *    |                                    |
 *    |____________________________________|
 *   
 *    
 * </pre>
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

@SuppressWarnings( "serial" )
public class PostAirshipsWindow extends WindowBase {
    
    
    //////////////////////////////////////////////////// 
    // Graphical Fields used only for design purposes //
   ////////////////////////////////////////////////////

    /**
     * {@code WINDOWWIDTH} int value that represents {@link PostAirshipsWindow} width. 
     */    
    private static final int WINDOWWIDTH = 860;
    /**
     * {@code WINDOWHEIGHT} int value that represents {@link PostAirshipsWindow} height. 
     */    
    private static final int WINDOWHEIGHT = 380;
    /**
     * {@code REDCOMPONENT} int value that represents Red Component for panel color. 
     */
    private static final int REDCOMPONENT = 65;
    /**
     * {@code GREENCOMPONENT} int value that represents Green Component for panel color. 
     */
    private static final int GREENCOMPONENT = 72;
    /**
     * {@code BLUECOMPONENT} int value that represents Blue Component for panel color. 
     */
    private static final int BLUECOMPONENT = 78;
    /**
     * {@code POSITIONAIRSHIPTABBEDPANE} int value that represents the position of {@code typeAirshipTabbedPane}
     * in the panel.
     */
    private static final int POSITIONAIRSHIPTABBEDPANE = 0;
     
     ////////////////////////////
    ///// Components Fields ////
   ////////////////////////////
    
    /**
     * {@code MILITARYAIRSHIP} variable that represents {@link String} with one of 
     * {@code typeAirshipTabbedPane} options.
     */
    private static final String MILITARYAIRSHIP = "Military Airship";
    /**
     * {@code CIVILAIRSHIP} variable that represents {@link String} with one of 
     * {@code typeAirshipTabbedPane} options.
     */
    private static final String CIVILAIRSHIP = "Civil Airship";
  
    /**
     * {@code militaryAirshipCommonPainel} variable that represents {@link JCommonPostAirshipPanel}. 
     */
    private JCommonPostAirshipPanel militaryAirshipCommonPainel;
    /**
     * {@code civilAirshipCommonPainel} variable that represents {@link JCommonPostAirshipPanel}. 
     */
    private JCommonPostAirshipPanel civilAirshipCommonPainel;
    /**
     * {@code specificMilitaryPanel} variable that represents {@link JPanelWithSpecificMilitaryAirhipParameters}. 
     */
    private JPanelWithSpecificMilitaryAirhipParameters specificMilitaryPanel;
    /**
     * {@code specificCivilPanel} variable that represents {@link JPanelWithSpecificCivilAirhipParameters}. 
     */
    private JPanelWithSpecificCivilAirhipParameters specificCivilPanel;
    /**
     * {@code civilOkCancelPanel} variable that represents {@link JTwoButtonsPanel}. 
     */
    private JTwoButtonsPanel civilOkCancelPanel;
    /**
     * {@code militaryOkCancelPanel} variable that represents {@link JTwoButtonsPanel}. 
     */
    private JTwoButtonsPanel militaryOkCancelPanel;
    /**
     * {@code typeAirshipTabbedPane} variable that represents {@link JTabbedPane}. 
     */
    private JTabbedPane typeAirshipTabbedPane;
    /**
     * {@code civilPanel} variable that represents {@link JPanel}. 
     */
    private JPanel civilPanel;
    /**
     * {@code militaryPanel} variable that represents {@link JPanel}. 
     */
    private JPanel militaryPanel;
    
    
    /**
     * Public constructor that creates a new {@link PostAirshipsWindow} adding {@link JTabbedPane} with two options
     * one for {@link CivilAirship} and the other for {@link MilitaryAirship}. Each one with one specific panel, 
     * the other are the same.
     * 
     */     
    public PostAirshipsWindow() {
        
        super( WINDOWWIDTH, WINDOWHEIGHT );
      
        this.setVisible( true );
       
        typeAirshipTabbedPane = new JTabbedPane();
        typeAirshipTabbedPane.setTabLayoutPolicy( JTabbedPane.SCROLL_TAB_LAYOUT );
        
        civilPanel = new JPanel( new FlowLayout() );
        civilPanel.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        
        militaryPanel = new JPanel( new FlowLayout() );
        militaryPanel.setBackground(  new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        
        typeAirshipTabbedPane.addTab( CIVILAIRSHIP, null, civilPanel, null );
        typeAirshipTabbedPane.addTab( MILITARYAIRSHIP, null, militaryPanel, null );
        
        this.add( typeAirshipTabbedPane, POSITIONAIRSHIPTABBEDPANE );
        
        civilPanel.add( new JPanelImage( "/images/civil.png", "/images/add.png" ) );
        militaryPanel.add( new JPanelImage( "/images/military.png", "/images/add.png" ) );
        
        civilAirshipCommonPainel = new JCommonPostAirshipPanel();
        civilPanel.add( civilAirshipCommonPainel );
        militaryAirshipCommonPainel = new JCommonPostAirshipPanel();
        militaryPanel.add( militaryAirshipCommonPainel );
        
        specificCivilPanel = new JPanelWithSpecificCivilAirhipParameters();
        civilPanel.add( specificCivilPanel );
        specificMilitaryPanel = new JPanelWithSpecificMilitaryAirhipParameters();
        militaryPanel.add( specificMilitaryPanel );
    }
    
     /////////////////   
    // Get Methods //
   /////////////////  

    /**
     * 
     * @return the {@code militaryAirshipCommonPainel}
     */
    public JCommonPostAirshipPanel getMilitaryAirshipCommonPainel() {
        
        return militaryAirshipCommonPainel;
    }
    
    /**
     * 
     * @return the {@code civilAirshipCommonPainel}
     */
    public JCommonPostAirshipPanel getCivilAirshipCommonPainel() {
        
        return civilAirshipCommonPainel;
    }
    
    /**
     * 
     * @return the {@code specificMilitaryPanel}
     */
    public JPanelWithSpecificMilitaryAirhipParameters getSpecificMilitaryPanel() {
        
        return specificMilitaryPanel;
    }
    
    /**
     * 
     * @return the {@code specificCivilPanel}
     */
    public JPanelWithSpecificCivilAirhipParameters getSpecificCivilPanel() {
        
        return specificCivilPanel;
    }
    
    /**
     * 
     * @return the {@code civilOkCancelPanel}
     */
    public JTwoButtonsPanel getCivilOkCancelPanel() {
        
        return civilOkCancelPanel;
    }
    
    /**
     * 
     * @return the {@code militaryOkCancelPanel}
     */
    public JTwoButtonsPanel getMilitaryOkCancelPanel() {
        
        return militaryOkCancelPanel;
    }
    
    /**
     * 
     * @return the {@code typeAirshipTabbedPane}
     */
    public JTabbedPane getTypeAirshipTabbedPane() {
        
        return typeAirshipTabbedPane;
    }
    
    /**
     * 
     * @return the {@code civilPanel}
     */
    public JPanel getCivilPanel() {
        
        return civilPanel;
    }
    
    /**
     * 
     * @return the {@code militaryPanel}
     */
    public JPanel getMilitaryPanel() {
        
        return militaryPanel;
    }
}
