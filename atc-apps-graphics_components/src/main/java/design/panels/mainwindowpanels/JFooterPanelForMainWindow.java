package design.panels.mainwindowpanels;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * Class who's instances represents panel that contains {@link JButton} group.
 * This class extends {@link JPanel}.
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */


@SuppressWarnings( "serial" )
public class JFooterPanelForMainWindow extends JPanel {
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
     * {@code nearestAirships}  variable that represents a {@link JButton} for an action. 
     */
    private JButton nearestAirships;
    /**
     * {@code transgressingAirships}  variable that represents a {@link JButton} for an action. 
     */
    private JButton transgressingAirships;
    /**
     * {@code airshipsWithLessPassengerThan}  variable that represents a {@link JButton} for an action. 
     */
    private JButton airshipsWithLessPassengerThan;
    /**
     * {@code patchAirship}  variable that represents a {@link JButton} for an action. 
     */
    private JButton patchAirship;
    /**
     * {@code postAirship}  variable that represents a {@link JButton} for an action. 
     */
    private JButton postAirship;
    /**
     * {@code deleteAirship}  variable that represents a {@link JButton} for an action. 
     */
    private JButton deleteAirship;
    /**
     * {@code showAllAirships}  variable that represents a {@link JButton} for an action. 
     */
    private JButton showAllAirships;
    
    /**
     * Public constructor responsible to create a {@link JPanel} with {@link JButton}.
     */
    
    public JFooterPanelForMainWindow() {
        
        this.setLayout( new FlowLayout() );
        this.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        
        showAllAirships = new JButton( "Show All Airships" );
        nearestAirships = new JButton( "Airships Closest to Coordinates " );
        transgressingAirships = new JButton( "Get Transgressing Airships" );
        airshipsWithLessPassengerThan = new JButton( "Get Airships with less Passengers" );
        patchAirship = new JButton( "Change Airship" );
        postAirship = new JButton( "Post Airship" );
        deleteAirship = new JButton( "Delete Airship" );
        
        this.add( showAllAirships );
        this.add( nearestAirships );
        this.add( transgressingAirships );
        this.add( airshipsWithLessPassengerThan );
        this.add( patchAirship );
        this.add( postAirship );
        this.add( deleteAirship );
    }
    
    /////////////////   
    // Get Methods //
   /////////////////  

    /**
     * @return the {@code nearestAirships}.
     */        
    public JButton getNearestAirships() {
        return nearestAirships;
    }
    
    /**
     * @return the {@code transgressingAirships}.
     */
    public JButton getTransgressingAirships() {
        return transgressingAirships;
    }
    
    /**
     * @return the {@code airshipsWithLessPassengerThan}.
     */
    public JButton getAirshipsWithLessPassengerThan() {
        return airshipsWithLessPassengerThan;
    }
    
    /**
     * @return the {@code patchAirship}.
     */    
    public JButton getPatchAirship() {
        return patchAirship;
    }
    
    /**
     * @return the {@code postAirship}.
     */ 
    public JButton getPostAirship() {
        return postAirship;
    }
    
    /**
     * @return the {@code deleteAirship}.
     */ 
    public JButton getDeleteAirship() {
        return deleteAirship;
    }
    
    /**
     * @return the {@code showAllAirships}.
     */ 
    public JButton getShowAllAirships() {
        return showAllAirships;
    }

}
