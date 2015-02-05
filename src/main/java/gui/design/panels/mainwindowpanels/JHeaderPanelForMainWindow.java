package main.java.gui.design.panels.mainwindowpanels;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import main.java.gui.design.panels.JPanelImage;


@SuppressWarnings( "serial" )
public class JHeaderPanelForMainWindow extends JPanel {
        
     //////////////////////////////////////////////////// 
    // Graphical Fields used only for design purposes //
   ////////////////////////////////////////////////////
   
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
     * {@code PANELWIDTH} int value that represents panel width. 
     */
    private static final int PANELWIDTH = 600;
    /**
     * {@code PANELWIDTH} int value that represents panel height. 
     */
    private static final int PANELHEIGHT = 100;
    /**
     * {@code userPanel} {@link JPanel} variable that represent all user operations.
     */
    private JUserPanelForHeaderPanel userPanel;
    
    //////////////////////
    //// Constructors ////
   //////////////////////
   
   /**
    * Public constructor that creates a new {@link JHeaderPanelForMainWindow} adding
    *  some components.
    */
    public JHeaderPanelForMainWindow() {
        
        this.setLayout( new FlowLayout() );
        this.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        
        JPanelImage logopanel = new JPanelImage( "/images/radar.png", "/images/logo.png" );
        this.add( logopanel );
        
        JPanel oneColorPanel = new JPanel();
        oneColorPanel.setPreferredSize( new Dimension( PANELWIDTH, PANELHEIGHT ) );
        oneColorPanel.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        this.add( oneColorPanel );
        
        userPanel = new JUserPanelForHeaderPanel();
        this.add( userPanel );
        
    }
    
     /////////////////   
    // Get Methods //
   /////////////////  

    /**
     * @return the {@code userPanel}.
     */    
    public JUserPanelForHeaderPanel getUserPanel() {
        
        return userPanel;
    }
}
