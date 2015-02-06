package main.java.gui.design.panels;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;
import main.java.utils.exceptions.InternalErrorException;
    
    /**
     * Class who's instances represents panel who contains several images side to side
     *  This panel extends {@link JPanel}. The panel has this configuration: 
     *  <pre>  
     *   ___________________________________
     *  |         |         |     |         |
     *  | Image 1 | Image 2 | ... | Image n | 
     *  |_________|_________|_____|_________|
     *   </pre>
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */

@SuppressWarnings( "serial" )
public class JPanelImage extends JPanel {
   
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
     * {@code XCOORDINATEFORDRAWIMAGE} int value that represents x position for drawing image.
     */    
    private static final int XCOORDINATEFORDRAWIMAGE = 0;
    /**
     * {@code YCOORDINATEFORDRAWIMAGE} int value that represents y position for drawing image.
     */    
    private static final int YCOORDINATEFORDRAWIMAGE = 0;

     //////////////////////
    //// Constructors ////
   //////////////////////
       
    /**
     * Public constructor responsible to add a panel with a image into {@link JPanelImage}.
     * 
     * @param path - Strings variables with the images localizations.
     */
    
    public JPanelImage( String... path ) {
        
        this.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        for( String imagePath : path ) {
            
            this.add( new CreateImage( imagePath ) );
            
        }
        
    }
    
    /**
     * Internal static class who's instances represents panel with one image.
     * This panel extends {@link JPanel}. 
     *
     *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */

    public static class CreateImage extends JPanel {
        
        // INSTANCE FIELD
        
        /**
         * {@code image} {@link BufferedImage} variable that describes an Image with an accessible buffer of image data.
         */
        private BufferedImage image;
        
        
        /**
         * Public method responsible to create an image that will be adding  into panel
         * 
         * @param path - String variable with the image localization.
         */
        
        public CreateImage( String path ) {
            
            try {
                image = ImageIO.read( getClass().getResourceAsStream( path ) );
            }
            catch( IOException e ) {
                
                throw new InternalErrorException( "Image Not Found : " + path, e );
            }
            
            setOpaque( false );
            setPreferredSize( new Dimension( image.getWidth( this ), image.getHeight( this ) ) );
        }
        
          ///////////////
         // Overrides //
        ///////////////
     
        /**
         * Override of the {@link JComponent#paintComponent} method from {@link JComponent}.
         */
        @Override
        public void paintComponent( Graphics g ) {
            super.paintComponent( g );
            g.drawImage( image, XCOORDINATEFORDRAWIMAGE, YCOORDINATEFORDRAWIMAGE, this );
            
        }
        
    }
    
}
