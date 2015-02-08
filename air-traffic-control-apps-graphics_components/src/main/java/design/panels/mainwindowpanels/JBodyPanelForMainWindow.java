package design.panels.mainwindowpanels;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import design.borders.TextRoundBorder;

    /**
     * 
     * Class who's instances represents panel that contains {@link JScrollPanelForElements#produceAJScrollPaneWithAllElements}
     *  and {@link  JWorldMapWithAirships#createAJPanelWithWorldMapAndAirships}.
     * This class extends {@link JPanel}.
     * 
     *
     *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */

@SuppressWarnings( "serial" )
public class JBodyPanelForMainWindow extends JPanel {
    
    
    // INSTANCE FIELD
    
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
    private static final int ROUNDBORDERPOINTERSIZE =0 ;
    
       ////////////////////////////
      ///// Components Fields ////
     ////////////////////////////
    /**
     * {@code airshipsScrollPane} {@link JPanel} variable that represents
     *  a {@link JScrollPanelForElements#produceAJScrollPaneWithAllElements} panel. 
     */    
    private JPanel airshipsScrollPane;
    /**
     * {@code airshipsScrollPane} {@link JPanel} variable that represents
     *  a {@link  JWorldMapWithAirships#createAJPanelWithWorldMapAndAirships} panel. 
     */
    private JPanel worldMapWithAirships;
    
    
      //////////////////////
     //// Constructors ////
    //////////////////////
    
    /**
     * Public constructor that creates a new {@link JBodyPanelForMainWindow} adding 
     * the two panels that is part of it. 
     */
    
    public JBodyPanelForMainWindow( Database< Airship > airshipsDatabase,
                                    Iterable< Airship > airshipsFound ) {
        
        this.setLayout( new FlowLayout() );
        
        airshipsScrollPane =
                new JWorldMapWithAirships().produceAJScrollPaneWithAllElements( airshipsDatabase,
                                                                                airshipsFound );
        
        worldMapWithAirships =
                new JWorldMapWithAirships().createAJPanelWithWorldMapAndAirships( airshipsFound );
        
        this.add( worldMapWithAirships );
        this.add( airshipsScrollPane );
        
        this.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        this.setBorder( new TextRoundBorder( Color.WHITE, ROUNDBORDERTHICKNESS, ROUNDBORDERRAD, ROUNDBORDERPOINTERSIZE ) );
    }
    
    
    /**
     *  Public method that is responsible for update {@link JBodyPanelForMainWindow} panel.
     * 
     * @param airshipsDatabase - {@link Database} variable with all {@link Airship}.
     * @param airshipsFound - {@link Iterable} variable with only the elements that satisfy the request.
     */
    
    public void updateBodyPanel( Database< Airship > airshipsDatabase,
                                 Iterable< Airship > airshipsFound ) {
        
        this.remove( worldMapWithAirships );
        this.remove( airshipsScrollPane );
        
        this.worldMapWithAirships =
                new JWorldMapWithAirships().createAJPanelWithWorldMapAndAirships( airshipsFound );
        this.airshipsScrollPane =
                new JWorldMapWithAirships().produceAJScrollPaneWithAllElements( airshipsDatabase,
                                                                                airshipsFound );
        
        this.add( worldMapWithAirships );
        this.add( airshipsScrollPane );
        
        this.revalidate();
        this.repaint();
    }
    
      /////////////////
     // Get Methods //
    /////////////////
    
    /**
     * @return the element {@code airshipsScrollPane}.
     */  
    public JPanel getAirshipsScrollPane() {
        
        return airshipsScrollPane;
    }
    
    /**
     * @return the element {@code worldMapWithAirships}.
     */  
    public JPanel getWorldMapWithAirships() {
        
        return worldMapWithAirships;
        
    }
}
