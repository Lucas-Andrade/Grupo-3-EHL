package design.windows.userwindows;


import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import design.panels.ScrollPanelForUsers;
import design.windows.popupwindows.PopupWindow;
import entities.SimpleUser;



/**
 * Class who's instances represents panel that extends {@link PopupWindow}, so it inherits a
 * {@link JPanel} with a {@link JButton}. This instance also add a
 * {@link JScrollPanelForUsers#produceAJScrollPaneWithAllElements} and has this configuration:
 * 
 * <pre>
 *     ____________________________________
 *    |                                    |
 *    |                                    |
 *    |                                    |
 *    | produceAJScrollPaneWithAllElements |
 *    |____________________________________|
 *    |                                    |
 *    |              JButton               |
 *    |____________________________________|
 * 
 * 
 * </pre>
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class GetUsersWindow extends PopupWindow {
    
    // //////////////////////////////////////////////////
    // Graphical Fields used only for design purposes //
    // //////////////////////////////////////////////////
    
    /**
     * {@code WINDOWWIDTH} int value that represents {@link GetAirshipsWithLessPassengerThanWindow}
     * width.
     */
    private static final int WINDOWWIDTH = 500;
    /**
     * {@code TOPINSETS} int value that represents the size of top borders used in
     * {@code GridBagConstraints}.
     */
    private static final int WINDOWHEIGHT = 300;
    
    
    // ////////////////////
    // // Constructors ////
    // ////////////////////
    
    /**
     * Public constructor that creates a new {@link GetUsersWindow} 
     * adding {@link ScrollPanelForUsers#produceAJScrollPaneWithAllElements}.
     */    
    public GetUsersWindow( Iterable< SimpleUser > users ) {
        
        super( new ScrollPanelForUsers().produceAJScrollPaneWithAllEntities( users ) );

        setPreferredSize( new Dimension( WINDOWWIDTH, WINDOWHEIGHT ) );
    }
}

