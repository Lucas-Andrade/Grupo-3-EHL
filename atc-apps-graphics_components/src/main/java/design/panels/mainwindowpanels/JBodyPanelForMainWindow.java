package design.panels.mainwindowpanels;


import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
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
    
    
    
    
    /**
     * {@code airshipsScrollPane} {@link JPanel} variable that represents a
     * {@link JScrollPanelForElements#produceAJScrollPaneWithAllElements} panel.
     */
    private JPanel airshipsScrollPane;
    /**
     * {@code airshipsScrollPane} {@link JPanel} variable that represents a
     * {@link JWorldMapWithAirships#createAJPanelWithWorldMapAndAirships} panel.
     */
    private JPanel worldMapWithAirships;

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
        
        createWorldMapAndScrollPanel(new ArrayList< SimpleAirship >());
    }
    
    
    /**
     * Update the {@link JBodyPanelForMainWindow} panel with the {@code airshipsFound}.
     * 
     * @param airshipsFound
     *            - List of {@link SimpleAirship}s which the {@code WorldMap} and the
     *            {@code ScrollPane} will be created.
     * 
     */
    public void updateBodyPanel( Iterable< SimpleAirship > airshipsFound ) {
    
        remove();
        createWorldMapAndScrollPanel( airshipsFound );
        paint();
    }

    
    /**
     * Create the {@code worldMapWithAirships} and the {@code airshipsScrollPane}, with a new list
     * of {@link SimpleAirship}.
     * 
     * @see JWorldMapWithAirships#createAJPanelWithWorldMapAndAirships(Iterable)
     * @see JWorldMapWithAirships#produceAJScrollPaneWithAllEntities(Iterable)
     * 
     * @param airshipsFound
     *            - List of {@link SimpleAirship}s which the {@code WorldMap} and the
     *            {@code ScrollPane} will be created.
     */
    private void createWorldMapAndScrollPanel( Iterable< SimpleAirship > airshipsFound ) {
    
        worldMapWithAirships =
                new JWorldMapWithAirships().createAJPanelWithWorldMapAndAirships( airshipsFound );
        airshipsScrollPane =
                new JWorldMapWithAirships().produceAJScrollPaneWithAllEntities( airshipsFound );
        
        add( worldMapWithAirships );
        add( airshipsScrollPane );
    }
    
    
    /**
     * Remove the {@code worldMapWithAirships} and the {@code airshipsScrollPane}.
     */
    private void remove() {
    
        remove( worldMapWithAirships );
        remove( airshipsScrollPane );
    }
    
    /**
     * Paint the {@code worldMapWithAirships} and the {@code airshipsScrollPane}.
     */
    private void paint() {
    
        revalidate();
        repaint();
    }
}


