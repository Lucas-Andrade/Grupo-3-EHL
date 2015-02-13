package design.panels.mainwindowpanels;


import java.awt.Color;
import java.awt.FlowLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.JPanel;
import design.borders.TextRoundBorder;
import entities.SimpleAirship;


/**
 * 
 * Class who's instances represents panel that contains
 * {@link JScrollPanelForElements#produceAJScrollPaneWithAllElements} and
 * {@link JWorldMapWithAirships#createAJPanelWithWorldMapAndAirships}. This class extends
 * {@link JPanel}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class JBodyPanelForMainWindow extends JPanel {
    
    
    // INSTANCE FIELD
    
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
     * {@code ROUNDBORDERTHICKNESS} int value that represents border thickness for round Border.
     */
    private static final int ROUNDBORDERTHICKNESS = 6;
    /**
     * {@code ROUNDBORDERRAD} int value that represents radius value for round Border.
     */
    private static final int ROUNDBORDERRAD = 12;
    /**
     * {@code ROUNDBORDERPOINTERSIZE} int value that represents pointer size value for round Border.
     */
    private static final int ROUNDBORDERPOINTERSIZE = 0;
    
    private Method update;
    private Method update2;
    
    
    // ////////////////////
    // // Constructors ////
    // ////////////////////
    
    



    /**
     * Public constructor that creates a new {@link JBodyPanelForMainWindow} adding the two panels
     * that is part of it.
     */
    public JBodyPanelForMainWindow() {
    
        setLayout( new FlowLayout() );
        setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        setBorder( new TextRoundBorder( Color.WHITE, ROUNDBORDERTHICKNESS, ROUNDBORDERRAD,
                                             ROUNDBORDERPOINTERSIZE ) );
        
        
    }
    
    /**
     * @param update the update to set
     */
    public void setUpdate( Method update ) {
    
        this.update = update;
    }

    public void setUpdate2( Method update2 ) {
        
        this.update2 = update2;
    }
    
    public void updateBodyPanel() {
        
        try {
            update.invoke( this );
        }
        catch( IllegalAccessException | IllegalArgumentException | InvocationTargetException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void updateBodyPanel(Iterable<SimpleAirship> airships) {
        
        try {
            update2.invoke( this, airships );
        }
        catch( IllegalAccessException | IllegalArgumentException | InvocationTargetException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
