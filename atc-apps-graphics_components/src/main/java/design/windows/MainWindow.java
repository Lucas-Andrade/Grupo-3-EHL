package design.windows;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import design.GridBagUtils;
import design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import design.panels.mainwindowpanels.JFooterPanelForMainWindow;
import design.panels.mainwindowpanels.JHeaderPanelForMainWindow;
import exceptions.InternalErrorException;


/**
 * Class whose instance represents a panel that contains other specific {@link JPanel},
 * {@link JHeaderPanelForMainWindow} , {@link JBodyPanelForMainWindow},
 * {@link JFooterPanelForMainWindow} and {@link JTextArea}. This class extends {@link JFrame} and
 * has this configuration:
 * 
 * <pre>
 *      ________________________________________________________
 *     |                                                        |   
 *     |          {@link JHeaderPanelForMainWindow}             |  
 *     |_______________________________________________________ |  
 *     |                                                        |  
 *     |                                                        |  
 *     |          {@link JBodyPanelForMainWindow}               |  
 *     |                                                        |  
 *     |_______________________________________________________ |  
 *     |                                                        |  
 *     |                                                        |  
 *     |          {@link JFooterPanelForMainWindow}             |  
 *     |________________________________________________________|      
 *     |                                                        |  
 *     |                {@link JTextArea}                       | 
 *     |________________________________________________________|
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class MainWindow extends JFrame {
    
    
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
     * {@code GRIDYFORBODYPANEL} int value that represents the row where is insert {@code bodyPanel}
     * .
     */
    private static final int GRIDYFORBODYPANEL = 1;
    /**
     * {@code GRIDYFORFOOTERPANEL} int value that represents the row where is insert
     * {@code footerPanel}.
     */
    private static final int GRIDYFORFOOTERPANEL = 2;
    /**
     * {@code GRIDYFORCONTENTPANE} int value that represents the row where is insert
     * {@code errorJTextArea}.
     */
    private static final int GRIDYFORCONTENTPANE = 3;
    
    // //////////////////////////
    // /// Components Fields ////
    // //////////////////////////
    
    /**
     * {@code constraints} variable that represents the configuration used in the panel layout.
     */
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    /**
     * {@code headerPanel} variable that represents {@link JHeaderPanelForMainWindow} that is part
     * of this panel.
     */
    private JHeaderPanelForMainWindow headerPanel;
    /**
     * {@code bodyPanel} variable that represents {@link JBodyPanelForMainWindow} that is part of
     * this panel.
     */
    private JBodyPanelForMainWindow bodyPanel;
    /**
     * {@code footerPanel} variable that represents {@link JFooterPanelForMainWindow} that is part
     * of this panel.
     */
    private JFooterPanelForMainWindow footerPanel;
    /**
     * {@code errorJTextArea} variable that represents {@link JTextArea} that is part of this panel.
     */
    private JTextArea errorJTextArea;
    
    
    
    // PRIVATE CONSTRUCTOR AND getInstance
    
    /**
     * The only instance of {@link MainWindow}.
     */
    private static volatile MainWindow theMainWindow;
    
    /**
     * A lock for the {@link #theMainWindow}.
     */
    private static Object windowLock = new Object();
    
    /**
     * Creates a new {@link MainWindow} adding the {@link JHeaderPanelForMainWindow},
     * {@link JBodyPanelForMainWindow}, {@link JFooterPanelForMainWindow} and {@link JTextArea}.
     */    
    private MainWindow() {

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        try {
            this.setIconImage(  ImageIO.read( getClass().getResourceAsStream( "/images/radar.png" ) ));
        }
        catch( IOException e ) {
            throw new InternalErrorException( "Image Not Found : /images/radar.png", e );
        }
        
        this.setTitle( "Air Traffic Control" );
        
        JPanel contentPane = new JPanel();
        
        contentPane.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        getContentPane().add( contentPane );
        
        contentPane.setLayout( new GridBagLayout() );
        
        headerPanel = new JHeaderPanelForMainWindow();
        
        contentPane.add( headerPanel, constraints );
        
        
        bodyPanel = new JBodyPanelForMainWindow();
        contentPane.add( bodyPanel,
                         GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORBODYPANEL ) );
        
        footerPanel = new JFooterPanelForMainWindow();
        contentPane.add( footerPanel,
                         GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORFOOTERPANEL ) );
        
        errorJTextArea = new JTextArea( " " );
        errorJTextArea.setForeground( Color.RED );
        errorJTextArea.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        errorJTextArea.setEditable( false );
        
        contentPane.add( errorJTextArea,
                         GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORCONTENTPANE ) );
        
        pack();
        setLocationRelativeTo( null );
        setResizable( false );
    }
    
    /**
     * Returns the {@link MainWindow}.
     * 
     * @return The {@link MainWindow}.
     */
    public static MainWindow getInstance() {
    
        
        if( theMainWindow == null )
            synchronized (windowLock) {
                
                if( theMainWindow == null ) {
                    theMainWindow = new MainWindow();
                }
            }
        
        return theMainWindow;
    }
    
    
        
    // ///////////////
    // Set Methods //
    // ///////////////
    
    
    /**
     * 
     * @param {@code headerPanel}
     */
    public void setHeaderPanel( JHeaderPanelForMainWindow headerPanel ) {
    
        this.headerPanel = headerPanel;
    }
    
    /**
     * @param the
     *            {@code footerPanel}.
     */
    public void setFooterPanel( JFooterPanelForMainWindow footerPanel ) {
    
        this.footerPanel = footerPanel;
    }
    
    /**
     * @param the
     *            {@code bodyPanel}.
     */
    public void setBodyPanel( JBodyPanelForMainWindow bodyPanel ) {
    
        this.bodyPanel = bodyPanel;
    }
    
    
    // ///////////////
    // Get Methods //
    // ///////////////
    
    /**
     * 
     * @return the {@code headerPanel}
     */
    public JHeaderPanelForMainWindow getHeaderPanel() {
    
        return headerPanel;
    }
    
    /**
     * 
     * @return the {@code bodyPanel}
     */
    public JBodyPanelForMainWindow getBodyPanel() {
    
        return bodyPanel;
    }
    
    /**
     * 
     * @return the {@code footerPanel}
     */
    public JFooterPanelForMainWindow getFooterPanel() {
    
        return footerPanel;
    }
    
    /**
     * @return the {@code errorJTextArea}
     */
    public JTextArea getErrorJTextArea() {
    
        return errorJTextArea;
    }

}
