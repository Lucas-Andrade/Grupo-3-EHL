package design.panels.mainwindowpanels;


import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import design.panels.JPanelImage;
import design.panels.ScrollPanelForEntities;
import entities.SimpleAirship;
import exceptions.InternalErrorException;
import functionalcomponents.infobuttons.SimpleAirshipInfoButton;


/**
 * Class who's instances represents a panel that contains a {@link JPanelImage} with a world map and
 * {@link JLabel} images.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class JWorldMapWithAirships extends ScrollPanelForEntities< SimpleAirship > {
    
    
    // //////////////////////////////////////////////////
    // Graphical Fields used only for design purposes //
    // //////////////////////////////////////////////////
    
    /**
     * {@code IMAGESCALEFACTOR} double value that represents scale for a correct position of the
     * {@code labelAirship}.
     */
    private static final double IMAGESCALEFACTOR = 1.86;
    /**
     * {@code ORIGINPOSITIONLATITUDE} double value that represents adjustment to be done for a
     * correct insertation of {@code labelAirship}.
     */
    private static final double ORIGINPOSITIONLATITUDE = 167;
    /**
     * {@code ORIGINPOSITIONLATITUDEFORLOCATIONICON} double value that represents adjustment to be done for a
     * correct insertation of {@code locationIconLabel}.
     */
    private static final double ORIGINPOSITIONLATITUDEFORLOCATIONICON = 155;
    /**
     * {@code JLABELWIDTH} int value that represents {@code labelAirship} width.
     */
    private static final int JLABELWIDTH = 50;
    /**
     * {@code JLABELWIDTHFORLOCATIONMAP} int value that represents {@code locationIconLabel} width.
     */
    private static final int JLABELWIDTHFORLOCATIONMAP = 150;    
    /**
     * {@code SPACEFORLABEL} int value that represents space between label image and text.
     */
    private static final int SPACEFORLABEL = 35;   
    /**
     * {@code SPACEFORLOCATIONICONLABEL} int value that represents space between label image and text.
     */
    private static final int SPACEFORLOCATIONICONLABEL = 50;
    /*
     * {@code ORIGINPOSITIONLONGITUDE} double value that represents adjustment to be done for a
     * correct insertation of {@code labelAirship}.
     */
    private static final double ORIGINPOSITIONLONGITUDE = JLABELWIDTH/2- SPACEFORLABEL;
    /*
     * {@code ORIGINPOSITIONLONGITUDEFORLOCATIONICON} double value that represents adjustment to be done for a
     * correct insertation of {@code locationIconLabel}.
     */
    private static final double ORIGINPOSITIONLONGITUDEFORLOCATIONICON = JLABELWIDTHFORLOCATIONMAP/2- SPACEFORLOCATIONICONLABEL;
    /**
     * {@code JLABELHEIGHT} int value that represents {@code labelAirship} height.
     */
    private static final int JLABELHEIGHT = 50;
    /**
     * {@code PANELWIDTH} int value that represents panel width.
     */
    private static final int PANELWIDTH = 720;
    /**
     * {@code PANELWIDTH} int value that represents panel height.
     */
    private static final int PANELHEIGT = 390;
    /**
     * {@code REDCOMPONENT} int value that represents Red Component for panel color.
     */
    private static final int REDCOMPONENT = 65;
    /**
     * {@code REDCOMPONENT} int value that represents Green Component for panel color.
     */
    private static final int GREENCOMPONENT = 72;
    /**
     * {@code REDCOMPONENT} int value that represents Blue Component for panel color.
     */
    private static final int BLUECOMPONENT = 78;
    /**
     * {@code worldMap} Jpanel  that represents world map.
     */
    JPanelImage.CreateImage worldMap;
    // //////////////////
    // Public Methods //
    // //////////////////
    
    /**
     * Public method responsible to add a {@link JLabel} image into {@link JPanelImage} with world
     * map, in the correct place, giving latitude and longitude {@link SimpleAirship}.
     * 
     * @param airshipsFound
     *            - list of {@link SimpleAirship}s which the {@code WorldMap} will be created.
     * 
     * 
     * @return a Panel that contains a world map and {@link JLabel} image for each {@link Airship}.
     */
    public JPanel createAJPanelWithWorldMapAndAirships( Iterable< SimpleAirship > airshipsFound ) {
    
       
        
        worldMap = new JPanelImage.CreateImage( "/images/planisphere.png" );
        worldMap.setLayout( null );
        
        this.add( worldMap );
        
        try {
            for( SimpleAirship airship : airshipsFound ) {
                
                Double latitude = ORIGINPOSITIONLATITUDE - IMAGESCALEFACTOR * (airship.latitude);
                Double longitude = IMAGESCALEFACTOR * (airship.longitude) - ORIGINPOSITIONLONGITUDE;
                
                JLabel labelAirship;
                
                labelAirship =
                        new JLabel(
                                    new ImageIcon(
                                                   ImageIO.read( getClass().getResourceAsStream( "/images/militaryAirship.png" ) ) ) );
                
                labelAirship.setBounds( longitude.intValue(), latitude.intValue(), JLABELWIDTH,
                                        JLABELHEIGHT );
                labelAirship.setText( airship.getIdentification() );
                worldMap.add( labelAirship );
                
            }
        }
        catch( IOException e ) {
            
            throw new InternalErrorException( "Image Not Found : /images/militaryAirship.png", e );
        }
        
        this.setPreferredSize( new Dimension( PANELWIDTH, PANELHEIGT ) );
        this.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        
        return this;
    }
    
    /**
     * Create a new {@link SimpleAirshipInfoButton}.
     * 
     * @see ScrollPanelForEntities#newButton(String, JTextArea)
     */
    @Override
    protected JButton newButton( String identification, JTextArea textArea ) {
    
        return new SimpleAirshipInfoButton( identification, textArea );
    }
    
    
    
    
    public JPanel createAJPanelWithWorldMapAndAirshipsAndLocationIcon( Iterable< SimpleAirship > airshipsFound, String latitude, String longitude){
        
       
        createAJPanelWithWorldMapAndAirships( airshipsFound );
        
        Double latitudeLocationIcon = ORIGINPOSITIONLATITUDEFORLOCATIONICON - IMAGESCALEFACTOR * (Double.parseDouble( latitude));            
        Double longitudeLocationIcon = IMAGESCALEFACTOR * (Double.parseDouble(longitude)) - ORIGINPOSITIONLONGITUDEFORLOCATIONICON;
         JLabel locationIconLabel;
        try {
            locationIconLabel =
                    new JLabel(
                                new ImageIcon(
                                               ImageIO.read( getClass().getResourceAsStream( "/images/location-icon.png" ) ) ) );
            
         
            
          
        locationIconLabel.setBounds(  longitudeLocationIcon.intValue(),latitudeLocationIcon.intValue(), 
                                                                          JLABELWIDTHFORLOCATIONMAP,JLABELHEIGHT );
        locationIconLabel.setText( "("+latitude+";"+longitude+")");
        worldMap.add( locationIconLabel );
        }
        catch( IOException e ) {
            throw new InternalErrorException( "Image Not Found : /images/location-icon.png", e );
        }
        return this;
    }
       
}
