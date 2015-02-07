package main.java.gui.design.panels.mainwindowpanels;


import Airship;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.java.gui.design.panels.JPanelImage;
import main.java.gui.design.panels.JScrollPanelForElements;
import main.java.utils.exceptions.InternalErrorException;

    /**
     * 
     * Class who's instances represents a panel that contains a {@link JPanelImage} with a world map 
     * and {@link JLabel} images.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
@SuppressWarnings( "serial" )
public class JWorldMapWithAirships extends JScrollPanelForElements< Airship > {
    
    
    // //////////////////////////////////////////////////
    // Graphical Fields used only for design purposes //
    // //////////////////////////////////////////////////
    
    /**
     * {@code IMAGESCALEFACTOR} double value that represents scale for a correct position of the
     * {@code labelAirship}.
     */
    private static final double IMAGESCALEFACTOR = 1.86;
    /**
     * {@code ORIGINPOSITIONLONGITUDE} double value that represents adjustment to be done for a
     * correct insertation of {@code labelAirship}.
     */
    private static final double ORIGINPOSITIONLONGITUDE = 25;
    /**
     * {@code ORIGINPOSITIONLATITUDE} double value that represents adjustment to be done for a
     * correct insertation of {@code labelAirship}.
     */
    private static final double ORIGINPOSITIONLATITUDE = 167;
    /**
     * {@code JLABELWIDTH} int value that represents {@code labelAirship} width.
     */
    private static final int JLABELWIDTH = 50;
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
    
    // //////////////////
    // Public Methods //
    // //////////////////
    
    /**
     * 
     * Public method responsible to add a {@link JLabel} image into {@link JPanelImage} with world
     * map, in the correct place, giving latitude and longitude {@link Airship}.
     * 
     * @param airshipsFound
     *            - {@link Iterable} variable with only the elements that satisfy the request.
     * 
     * @return a Panel that contains a world map and {@link JLabel} image for each {@link Airship}.
     */
    
    public JPanel createAJPanelWithWorldMapAndAirships( Iterable< Airship > airshipsFound ) {
        
        JPanelImage.CreateImage worldMap = new JPanelImage.CreateImage( "/images/planisphere.png" );
        worldMap.setLayout( null );
        
        this.add( worldMap );
        
        try {
            for( Airship airship : airshipsFound ) {
                
                Double latitude =
                        ORIGINPOSITIONLATITUDE - IMAGESCALEFACTOR
                                * (airship.getCoordinates().getLatitude().getValue());
                Double longitude =
                        IMAGESCALEFACTOR * (airship.getCoordinates().getLongitude().getValue())
                                - ORIGINPOSITIONLONGITUDE;
                
                JLabel labelAirship;
                
                labelAirship =
                        new JLabel(
                                    new ImageIcon(
                                                   ImageIO.read( getClass().getResourceAsStream( "/images/militaryAirship.png" ) ) ) );
                
                labelAirship.setBounds( longitude.intValue(), latitude.intValue(), JLABELWIDTH,
                                        JLABELHEIGHT );
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
}
