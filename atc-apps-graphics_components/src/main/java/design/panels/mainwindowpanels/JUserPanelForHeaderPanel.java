package design.panels.mainwindowpanels;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import design.borders.TextRoundBorder;
import design.panels.JPanelImage;
import exceptions.InternalErrorException;


/**
 * 
 * Class who's instances represents panel that contains {@link JButton} group and a
 * {@link JPanelImage}. This class extends {@link JPanel}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class JUserPanelForHeaderPanel extends JPanel {
    
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
     * {@code TRANSLUCENTCOMPONENT} int variable that represents panel Color (translucent Color).
     */
    private static final int TRANSLUCENTCOMPONENT = 0;
    /**
     * {@code NUMBEROFROWSFORMYOPTIONSPANEL} int variable that represents the number of rows of
     * {@code myOptions} panel.
     */
    private static final int NUMBEROFROWSFORMYOPTIONSPANEL = 0;
    /**
     * {@code NUMBEROFCOLUMNSFORMYOPTIONSPANEL} int variable that represents the number of columns
     * of {@code myOptions} panel.
     */
    private static final int NUMBEROFCOLUMNSFORMYOPTIONSPANEL = 4;
    /**
     * {@code NUMBEROFROWSFORUSEROPTIONSPANEL} int variable that represents the number of rows of
     * {@code userOptions} panel.
     */
    private static final int NUMBEROFROWSFORUSEROPTIONSPANEL = 0;
    /**
     * {@code NUMBEROFCOLUMNSFORUSEROPTIONSPANEL} int variable that represents the number of columns
     * of {@code userOptions} panel.
     */
    private static final int NUMBEROFCOLUMNSFORUSEROPTIONSPANEL = 3;
    
    
    
    // INSTANCE FIELDS
    
    /**
     * The label with the name of the logged-in user.
     */
    private static JLabel username;
    
    /**
     * {@code changePasswordButton} variable that represents a {@link JButton} for an action.
     */
    private JButton changePasswordButton;
    /**
     * {@code logoutButton} variable that represents a {@link JButton} for an action.
     */
    private JButton logoutButton;
    /**
     * {@code turnOffButton} variable that represents a {@link JButton} for an action.
     */
    private JButton turnOffButton;
    
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
    
    
    
    // CONSTRUCTOR & PRIVATE METHODS
    
    /**
     * Creates a new {@link JUserPanelForHeaderPanel}.
     */
    public JUserPanelForHeaderPanel() {
    
        
        this.setLayout( new FlowLayout() );
        this.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        
        
        configureUserImage();
        
        JPanel myOptions =
                new JPanel( new GridLayout( NUMBEROFCOLUMNSFORMYOPTIONSPANEL,
                                            NUMBEROFROWSFORMYOPTIONSPANEL ) );
        myOptions.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        this.add( myOptions );
        configureUsernameLabel( myOptions );
        createButtonsBellowTheUsernameLabel( myOptions );
        
        
        JPanel userOptions = new JPanel();
        userOptions.setLayout( new GridLayout( NUMBEROFCOLUMNSFORUSEROPTIONSPANEL,
                                               NUMBEROFROWSFORUSEROPTIONSPANEL ) );
        this.add( userOptions );
        createButtonsWithIcons( userOptions );
        
    }
    
    
    /**
     * Creates and configures the panel that contains the user's image.
     */
    private void configureUserImage() {
    
        JPanelImage userImage = new JPanelImage( "/images/users/chuck_norris.jpg" );
        userImage.setBorder( new TextRoundBorder( Color.WHITE, ROUNDBORDERTHICKNESS,
                                                  ROUNDBORDERRAD, ROUNDBORDERPOINTERSIZE ) );
        this.add( userImage );
    }
    
    /**
     * Creates and configures the label with the currently logged-in user's username.
     * 
     * @param myOptions
     *            The panel to contain the label.
     */
    private void configureUsernameLabel( JPanel myOptions ) {
    
        username = new JLabel( "CHUCK NORRIS" );
        username.setBackground( new Color( TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT,
                                           TRANSLUCENTCOMPONENT ) );
        username.setForeground( Color.WHITE );
        myOptions.add( username );
    }
    
    /**
     * Creates the buttons to change the logged-in user's password, to log out of this session and
     * to turn off the app.
     * 
     * @param myOptions
     *            The panel to contain the buttons.
     */
    private void createButtonsBellowTheUsernameLabel( JPanel myOptions ) {
    
        changePasswordButton = new JButton( "Change Password" );
        myOptions.add( changePasswordButton );
        
        logoutButton = new JButton( "Logout" );
        myOptions.add( logoutButton );
        
        turnOffButton = new JButton( "Turn Off" );
        myOptions.add( turnOffButton );
    }
    
    /**
     * Creates the buttons to add a new user, to remove an existing user and to consult all the
     * user's.
     * 
     * @param userOptions
     *            The panel to contain the buttons.
     */
    private void createButtonsWithIcons( JPanel userOptions ) {
    
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
    
    
    
    // PUBLIC METHODS - SETTER & GETTERS
    
    /**
     * Sets the username to appear in the logged-in user's username label.
     * 
     * @param newTextName
     *            The username of the currently looged-in user.
     */
    public void setUsernameLabel( String newTextName ) {
    
        username.setText( newTextName );
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
     * @return the turnOffButton
     */
    public JButton getTurnOffButton() {
    
        return turnOffButton;
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
