package design.windows.popupwindows;


import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import design.GridBagUtils;
import exceptions.InternalErrorException;



/**
 * Abstract class, whose instances are {@link JDialog} with a {@code Ok} button, that dispose this
 * window.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public abstract class PopupWindow extends JDialog {
    
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
     * {@code HGAPFORFLOWLAYOUT} int value that represents the horizontal gap between components for 
     * {@link FlowLayout}.
     */
    private static final int HGAPFORFLOWLAYOUT = 50;
    /**
     * {@code HGAPFORFLOWLAYOUT} int value that represents the vertical gap between components for 
     * {@link FlowLayout}.
     */
    private static final int VGAPFORFLOWLAYOUT = 50;
    /**
     * {@code GRIDYFORLABEL} int value that represents the column where is insert the label
     * into {@code GridBagConstraints}.
     */
    private static final int GRIDYFORLABEL = 0;
    /**
     * {@code GRIDYFORJBUTTON} int value that represents the column where is insert the button
     * into {@code GridBagConstraints}.
     */
    private static final int GRIDYFORJBUTTON = 1;
    /**
     * {@code POPUPWINDOWWITH} int value that represents the popupWindow width.
     */
    private static final int POPUPWINDOWWITH = 400;
    /**
     * {@code POPUPWINDOWHEIGHT} int value that represents the popupWindow height.
     */
    private static final int POPUPWINDOWHEIGHT = 10;
    
    /**
     * {@code constraints} variable that represents the configuration used in the panel layout.
     */   
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    
    /**
     * Creates a Pop-up window with a {@code image}, a {@code message}, and a {@code ok-button}.
     * 
     * @param message
     * @param image
     */
    public PopupWindow( String message, String image ) {
        setLayout( new GridBagLayout() );
        add( getLabel( message, image ),
             GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORLABEL ) );
        
        setDefaultSettings();
    }
    
    /**
     * Creates a Pop-up window with a {@code component} and a {@code ok-button}.
     * 
     * @param component
     */
    public PopupWindow( Component component ) {
        setLayout( new GridBagLayout() );
        add( component, GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORLABEL ) );
        
        setDefaultSettings();
    }
    
    /**
     * Set all shared Settings: Title, Color, Icon, size, ... Also is added the {@code ok-button}
     */
    private void setDefaultSettings() {
        setTitle( "Air Traffic Controll" );
        getContentPane().setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        setSize( POPUPWINDOWWITH, POPUPWINDOWHEIGHT );
        setIconImage( Toolkit.getDefaultToolkit().getImage( "/images/radar.png" ) );
        
        add( createButton(), GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORJBUTTON ) );
        
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setModalityType( ModalityType.TOOLKIT_MODAL );
        
        pack();
        setLocationRelativeTo( null );
        setResizable( false );
        setVisible( true );
    }
    
    
    
    /**
     * @param message
     * @param image
     * @return the Label with a {@code image} and a {@code message}
     */
    private JPanel getLabel( String message, String image ) {
        JPanel mainPanel = new JPanel();
        JLabel imageLabel;
        try {
            imageLabel =
                    new JLabel(
                                new ImageIcon(
                                               ImageIO.read( getClass().getResourceAsStream( image ) ) ) );
            
            JLabel messageLabel = new JLabel( message, JLabel.CENTER );
            messageLabel.setForeground( Color.WHITE );
            
            
            mainPanel.setLayout( new FlowLayout( FlowLayout.LEADING, HGAPFORFLOWLAYOUT,
                                                 VGAPFORFLOWLAYOUT ) );
            mainPanel.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
            mainPanel.add( imageLabel );
            mainPanel.add( messageLabel );
        }
        catch( IOException e ) {
            throw new InternalErrorException( "Image Not Found : " + image, e );
        }
        return mainPanel;
    }
    
    
    /**
     * @return the buttonPanel, that contains the button
     */
    private JPanel createButton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        JButton okButton = new JButton( "Ok" );
        
        okButton.addActionListener( buttonActivation -> dispose() );
        
        buttonPanel.add( okButton );
        getRootPane().setDefaultButton( okButton );
        return buttonPanel;
    }
}
