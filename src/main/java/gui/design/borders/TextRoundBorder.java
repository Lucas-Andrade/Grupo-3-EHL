package main.java.gui.design.borders;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;


@SuppressWarnings( "serial" )
public class TextRoundBorder extends AbstractBorder {
    
    
    private static final int XCOORDINATEOFRECTANGULE = 0;
    private static final int YCOORDINATEOFRECTANGULE = 0;
    private static final int DIVIDEBYTWO = 2;


    private Color color;
    private int thickness;
    private int radii;
    private int pointerSize;
    private Insets insets;
    private BasicStroke stroke;
    private int strokePad;
    RenderingHints hints;
    
 
    public TextRoundBorder( Color color, int thickness, int radii, int pointerSize ) {
        
        this.thickness = thickness;
        this.radii = radii;
        this.pointerSize = pointerSize;
        this.color = color;
        
        stroke = new BasicStroke( thickness );
        strokePad = thickness / DIVIDEBYTWO;
        
        hints = new RenderingHints( RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON );
        
        int pad = radii + strokePad;
        int bottomPad = pad + pointerSize + strokePad;
        insets = new Insets( pad, pad, bottomPad, pad );
    }
    
     
    @Override
    public Insets getBorderInsets( Component c ) {
        return insets;
    }
    
    @Override
    public Insets getBorderInsets( Component c, Insets insets ) {
        return getBorderInsets( c );
    }
    
    @Override
    public void paintBorder( Component c, Graphics g, int x, int y, int width, int height ) {
        
        Graphics2D g2 = (Graphics2D)g;
        
        int bottomLineY = height - thickness - pointerSize;
        
        RoundRectangle2D.Double bubble =
                new RoundRectangle2D.Double( strokePad, strokePad, width - thickness,
                                             bottomLineY, radii, radii );
        
        Polygon pointer = new Polygon();
        
        Area area = new Area( bubble );
        area.add( new Area( pointer ) );
        
        g2.setRenderingHints( hints );
        
        // Paint the BG color of the parent, everywhere outside the clip
        // of the text bubble.
        Component parent = c.getParent();
        if( parent != null ) {
            Color bg = parent.getBackground();
            Rectangle rect = new Rectangle( XCOORDINATEOFRECTANGULE, YCOORDINATEOFRECTANGULE, width, height );
            Area borderRegion = new Area( rect );
            borderRegion.subtract( area );
            g2.setClip( borderRegion );
            g2.setColor( bg );
            g2.fillRect( XCOORDINATEOFRECTANGULE, YCOORDINATEOFRECTANGULE, width, height );
            g2.setClip( null );
        }
        
        g2.setColor( color );
        g2.setStroke( stroke );
        g2.draw( area );
    }
}
