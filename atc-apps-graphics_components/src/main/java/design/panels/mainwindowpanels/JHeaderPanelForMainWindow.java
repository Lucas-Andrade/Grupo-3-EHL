package design.panels.mainwindowpanels;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import design.panels.JPanelImage;



/**
 * 
 * Class who's instances represents panel that contains a {@link JPanelImage} and
 * {@link JUserPanelForHeaderPanel}. This class extends {@link JPanel}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

@SuppressWarnings( "serial" )
public class JHeaderPanelForMainWindow extends JPanel {
    
    // //////////////////////////////////////////////////
    // Graphical Fields used only for design purposes //
    // //////////////////////////////////////////////////
    
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
    /**
     * {@code userPanel} {@link JPanel} variable that represent an animation operation.
     */
    private SpecialPanel middlePanel;
    
    
    // ////////////////////
    // // Constructors ////
    // ////////////////////
    
    /**
     * Public constructor that creates a new {@link JHeaderPanelForMainWindow} adding some
     * components.
     */
    public JHeaderPanelForMainWindow() {
    
        this.setLayout( new FlowLayout() );
        this.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        
        JPanelImage logopanel = new JPanelImage( "/images/radar.png", "/images/logo.png" );
        this.add( logopanel );
                
        middlePanel = new SpecialPanel();                
        middlePanel.setPreferredSize( new Dimension( PANELWIDTH, PANELHEIGHT ) );
        middlePanel.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        
        Timer timer = new Timer();
        timer.schedule( new TimerTask() {
            
            @Override
            public void run() {
            
                middlePanel.doStuff();
            }
        }, 0, middlePanel.myLong );
                
        this.add( middlePanel );
        
        
        
        
        
        userPanel = new JUserPanelForHeaderPanel();
        this.add( userPanel );
        
    }
    
    
    // ///////////////
    // Get Methods //
    // ///////////////
    
    /**
     * @return the {@code userPanel}.
     */
    public JUserPanelForHeaderPanel getUserPanel() {
    
        return userPanel;
    }
    /**
     * @return the {@code userPanel}.
     */
    
    public SpecialPanel getSpecialPanel() {
    
        return middlePanel;
    }
}
