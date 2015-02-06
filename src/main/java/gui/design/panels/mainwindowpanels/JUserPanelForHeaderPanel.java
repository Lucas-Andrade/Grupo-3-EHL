package main.java.gui.design.panels.mainwindowpanels;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.java.gui.design.borders.TextRoundBorder;
import main.java.gui.design.panels.JPanelImage;
import main.java.utils.exceptions.InternalErrorException;

/**
 * 
 * Class who's instances represents panel that contains {@link JButton} group and a {@link JPanelImage}.
 * This class extends {@link JPanel}.
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

@SuppressWarnings( "serial" )
public class JUserPanelForHeaderPanel extends JPanel {
    
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
    private static final int ROUNDBORDERTHICKNESS = 5;
    /**
     * {@code ROUNDBORDERRAD} int value that represents radius value for round Border. 
     */    
    private static final int ROUNDBORDERRAD = 4;
    /**
     * {@code ROUNDBORDERPOINTERSIZE} int value that represents pointer size value for round Border. 
     */  
    private static final int ROUNDBORDERPOINTERSIZE =0 ;
    /**
     * {@code TRANSLUCENTCOMPONENT} int variable that represents panel Color (translucent Color). 
     */
    private static final int TRANSLUCENTCOMPONENT = 0; 
    
    /**
     * {@code NUMBEROFROWSFORMYOPTIONSPANEL} int variable that represents the number of rows of {@code myOptions} panel. 
     */
    private static final int NUMBEROFROWSFORMYOPTIONSPANEL = 0;

    /**
     * {@code NUMBEROFCOLUMNSFORMYOPTIONSPANEL} int variable that represents the number of columns of {@code myOptions} panel. 
     */
    private static final int NUMBEROFCOLUMNSFORMYOPTIONSPANEL = 4;
    
    /**
     * {@code NUMBEROFROWSFORUSEROPTIONSPANEL} int variable that represents the number of rows of {@code userOptions} panel. 
     */
    private static final int NUMBEROFROWSFORUSEROPTIONSPANEL = 0;
    /**
     * {@code NUMBEROFCOLUMNSFORUSEROPTIONSPANEL} int variable that represents the number of columns of {@code userOptions} panel. 
     */
    private static final int NUMBEROFCOLUMNSFORUSEROPTIONSPANEL = 3;
  
    /**
     * {@code changePasswordButton} variable that represents a {@link JButton} for an action. 
     */
    private JButton changePasswordButton;
    /**
     * {@code logoutButton} variable that represents a {@link JButton} for an action. 
     */
    private JButton logoutButton;
    /**
     * {@code addUserButton} variable that represents a {@link JButton} for an action. 
     */
    private JButton addUserButton;
    /**
     * {@code removeUserButton} variable that represents a {@link JButton} for an action. 
     */
    private JButton removeUserButton;
    /**
     * {@code infoAllUsersButton} variable that represents a {@link JButton} for an action. 
     */
    private JButton infoAllUsersButton;
    /**
     * {@code turnOffButton} variable that represents a {@link JButton} for an action. 
     */
    private JButton turnOffButton;
    
    
    /**
     * Public constructor that creates a {@link JPanel}, that contains {@link JComponent}'s.
     * 
     */   
    public JUserPanelForHeaderPanel() {
        
        
        this.setLayout( new FlowLayout() );
        this.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        
        JPanelImage userImage = new JPanelImage( "/images/users/chuck_norris.jpg" );
        userImage.setBorder( new TextRoundBorder( Color.WHITE,ROUNDBORDERTHICKNESS, ROUNDBORDERRAD, ROUNDBORDERPOINTERSIZE ) );
        this.add( userImage );
        
        JPanel myOptions = new JPanel( new GridLayout( NUMBEROFCOLUMNSFORMYOPTIONSPANEL, NUMBEROFROWSFORMYOPTIONSPANEL ) );
        myOptions.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        this.add( myOptions );
        
        JLabel username = new JLabel( "CHUCK NORRIS" );
        username.setBackground( new Color( TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT ) );
        username.setForeground( Color.WHITE );
        myOptions.add( username );
        
        changePasswordButton = new JButton( "Change Password" );
        myOptions.add( changePasswordButton );
        
        logoutButton = new JButton( "Logout" );
        myOptions.add( logoutButton );
        
        turnOffButton = new JButton( "Turn Off" );
        myOptions.add( turnOffButton );
        
        JPanel userOptions = new JPanel();
        userOptions.setLayout( new GridLayout( NUMBEROFCOLUMNSFORUSEROPTIONSPANEL, NUMBEROFROWSFORUSEROPTIONSPANEL ) );
        this.add( userOptions );
        
        String path = null;
        try {
            path = "/images/addUser.png";
            addUserButton =
                    new JButton(
                                 new ImageIcon(
                                                ImageIO.read( getClass().getResourceAsStream( path ) ) ) );
            userOptions.add( addUserButton );
            path = "/images/removeUser.png";
            removeUserButton =
                    new JButton(
                                 new ImageIcon(
                                                ImageIO.read( getClass().getResourceAsStream( path ) ) ) );
            userOptions.add( removeUserButton );
            path = "/images/infoUser.png";
            infoAllUsersButton =
                    new JButton(
                                 new ImageIcon(
                                                ImageIO.read( getClass().getResourceAsStream( path ) ) ) );
            userOptions.add( infoAllUsersButton );
        }
        catch( IOException e ) {
            throw new InternalErrorException( "Image Not Found: " + path, e );
        }
        
    }
    
    /**
     * @return the turnOffButton
     */
    public JButton getTurnOffButton() {
        return turnOffButton;
    }
    
    /**
     * @return the changePasswordButton
     */
    public JButton getChangePasswordButton() {
        return changePasswordButton;
    }
    
    /**
     * @return the logoutButton
     */
    public JButton getLogoutButton() {
        return logoutButton;
    }
    
    /**
     * @return the addUserButton
     */
    public JButton getAddUserButton() {
        return addUserButton;
    }
    
    /**
     * @return the removeUserButton
     */
    public JButton getRemoveUserButton() {
        return removeUserButton;
    }
    
    /**
     * @return the infoAllUsersButton
     */
    public JButton getInfoAllUsersButton() {
        return infoAllUsersButton;
    }
}
