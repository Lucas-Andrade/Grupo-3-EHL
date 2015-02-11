package design.panels;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import design.borders.TextRoundBorder;
import entities.Entity;


/**
 * Class who's instances represents a ScroolPanel with {@link Entity} information. This panel
 * extends {@link JPanel}.
 * 
 * @param <E>
 *            The type of the Entities existing.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

@SuppressWarnings( "serial" )
public abstract class ScrollPanelForEntities< E extends Entity > extends JPanel {
    
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
    private static final int ROUNDBORDERTHICKNESS = 5;
    
    /**
     * {@code ROUNDBORDERRAD} int value that represents radius value for round Border.
     */
    private static final int ROUNDBORDERRAD = 4;
    
    /**
     * {@code ROUNDBORDERPOINTERSIZE} int value that represents pointer size value for round Border.
     */
    private static final int ROUNDBORDERPOINTERSIZE = 0;
    /**
     * {@code LISTPANELWIDTH} int value that represents {@code listPanel} width.
     */
    private static final int LISTPANELWIDTH = 200;
    /**
     * {@code LISTPANELHEIGHT} int value that represents {@code listPanel} height.
     */
    private static final int LISTPANELHEIGHT = 350;
    /**
     * {@code SCROLLPANELWIDTH} int value that represents {@code scrollPane} width.
     */
    private static final int SCROLLPANELWIDTH = 200;
    /**
     * {@code SCROLLPANELHEIGHT} int value that represents {@code scrollPane} height.
     */
    private static final int SCROLLPANELHEIGHT = 200;
    /**
     * {@code TEXTAREAPANELWIDTH} int value that represents {@code textArea} width.
     */
    private static final int TEXTAREAPANELWIDTH = 200;
    /**
     * {@code TEXTAREAPANELHEIGHT} int value that represents {@code textArea} height.
     */
    private static final int TEXTAREAPANELHEIGHT = 200;
    /**
     * {@code COLUMNSNUMBERFORGRIDLAYOUT} int value that represents GridLayout columns number.
     */
    private static final int COLUMNSNUMBERFORGRIDLAYOUT = 1;
    
    
    // //////////////////////////
    // /// Components Fields ////
    // //////////////////////////
    
    /**
     * {@code scrollPane} {@link JScrollPane} variable that represents panel that contains a list of
     * entities.
     */
    private JScrollPane scrollPane;
    
    /**
     * {@code textArea} {@link JTextArea} variable that represents panel that contains the
     * information about an entity.
     */
    private JTextArea textArea;
    
    
    // ////////////////////
    // // Constructors ////
    // ////////////////////
    
    /**
     * Public constructor that creates a new {@link ScrollPanelForEntities}, specifying some panel
     * characteristics.
     */
    
    public ScrollPanelForEntities() {
    
        this.setLayout( new FlowLayout() );
        this.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        
        scrollPane = new JScrollPane();
        textArea = new JTextArea();
        textArea.setEditable( false );
        textArea.setLineWrap( true );
        textArea.setOpaque( false );
    }
    
    // //////////////////
    // Public Methods //
    // //////////////////
    
    /**
     * Public method responsible to add into panel, a {@link JButton} for each {@link Entity}.
     * 
     * @param dataBase
     *            - {@link Database} variable with all the entities.
     * @param iterable
     *            - {@link Iterable} variable with only the entities that satisfy the request.
     * 
     * @return a panel with a {@link JScrollPane} that contains a {@link JButton} for each
     *         {@link Entity}.
     */
    public JPanel produceAJScrollPaneWithAllEntities( Iterable< E > iterable ) {
    
        JPanel listPanel = new JPanel();
        int counter = 0;
        
        
        for( E entity : iterable ) {
            
            JButton button = newButton( entity.getIdentification(), textArea );
            
            button.add( new JLabel( entity.getIdentification() ) );
            button.setOpaque( true );
            button.setContentAreaFilled( false );
            button.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
            
            listPanel.add( button );
            ++counter;
        }
        
        
        
        listPanel.setLayout( new GridLayout( counter, COLUMNSNUMBERFORGRIDLAYOUT ) );
        listPanel.setPreferredSize( new Dimension( LISTPANELWIDTH, LISTPANELHEIGHT ) );
        
        
        scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
        scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollPane.setPreferredSize( new Dimension( SCROLLPANELWIDTH, SCROLLPANELHEIGHT ) );
        scrollPane.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        scrollPane.setBorder( new TextRoundBorder( Color.WHITE, ROUNDBORDERTHICKNESS,
                                                   ROUNDBORDERRAD, ROUNDBORDERPOINTERSIZE ) );
        this.add( scrollPane );
        
        textArea.setBorder( new TextRoundBorder( Color.WHITE, ROUNDBORDERTHICKNESS, ROUNDBORDERRAD,
                                                 ROUNDBORDERPOINTERSIZE ) );
        textArea.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        textArea.setPreferredSize( new Dimension( TEXTAREAPANELWIDTH, TEXTAREAPANELHEIGHT ) );
        this.add( textArea );
        
        
        scrollPane.setViewportView( listPanel );
        
        return this;
    }
    
    protected abstract JButton newButton( String identification, JTextArea textArea );
    
//     /////////////////   
//    // Get Methods //
//   /////////////////  
//
//    /**
//     * @return the element information.
//     */    
//    protected String getString( E element ) {
//        return element.toString();
//    }
//    
}