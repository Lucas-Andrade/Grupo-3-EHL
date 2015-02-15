package functionalcomponents.functionalmainwindow;



import design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import design.windows.MainWindow;
import entities.SimpleLoggedUser;
import exceptions.LoggedInUserMissingException;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow;



/**
 * Class whose instances have the purpose of adding functionality to a {@link MainWindow}. Giving
 * functionality to a {@link MainWindow} means replacing its panels with functional panels.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalMainWindow {
    
    /* Implementation notes:
     * - Getting the locks must follow the following order: never synchronize with the userLock
     *   before synchronizing with the windowLock and never synchronize with the windowBase
     *   before synchronizing with the windowLock 
     */
    
    
    
    // STATIC FINAL FIELDS
    
    /**
     * The {@code MainWindow} we want to add functionality to.
     */
    private static final MainWindow windowBase = MainWindow.getInstance();
    
    /**
     * The functionalizer of this {@link FunctionalMainWindow} uses to create and update its
     * {@link JBodyPanelForMainWindow}.
     */
    public static final BodyPanelFunctionalizer bodyPanelFunctionalizer =
            new BodyPanelFunctionalizer( windowBase.getErrorJTextArea() );
    
    
    
    // STATIC MEMBERS ABOUT THE loggedInUser
    
    /**
     * The user who is currently logged in.
     */
    private static volatile SimpleLoggedUser loggedInUser;
    
    /**
     * A lock for the {@link #loggedInUser}.
     */
    private static Object userLock = new Object();
    
    
    /**
     * Sets the {@link SimpleLoggedUser} that is logged in in this session of the app.
     * 
     * @param loggedUser
     *            The {@link SimpleLoggedUser} that is logged in in this session of the app.
     * @return {@code true} if {@code loggedUser} was set as the {@link SimpleLoggedUser} that is
     *         logged in in this session of the app; <br/>
     *         {@code false} if there is already a user logged in or {@code loggedUser} is
     *         {@code null}.
     */
    public static boolean setLoggedUser( SimpleLoggedUser loggedUser ) {
    
        if( loggedUser != null )
            synchronized (userLock) {
                
                if( loggedInUser == null ) {
                    loggedInUser = loggedUser;
                    windowBase.getHeaderPanel().getUserPanel()
                              .setUsernameLabel( loggedUser.getIdentification() );
                    return true;
                }
            }
        return false;
    }
    
    /**
     * Gets the {@link SimpleLoggedUser} that is logged in in this session of the app.
     * 
     * @return the {@link SimpleLoggedUser} that is logged in in this session of the app; <br/>
     *         {@code null} if there is none.
     */
    public static SimpleLoggedUser getLoggedUser() {
    
        synchronized (userLock) {
            return loggedInUser;
        }
    }
    
    /**
     * Updates the {@link SimpleLoggedUser} that is logged in in this session of the app. The
     * already logged in user and the new logged in user must share the same identification (known
     * via method {@link SimpleLoggedUser#getIdentification()}.
     * 
     * @param newLoggedUser
     *            The new {@link SimpleLoggedUser} that is to replace the already logged in user.
     * @return {@code true} if {@code newLoggedUser} was set as the new user logged in in this
     *         session of the app; <br/>
     *         {@code false} if (1) {@code newLoggedUser} is {@code null} or (2) there was no user
     *         previously logged in or (3) the previously logged in user and {@code newLoggedUser}
     *         have different identifications - in neither case, {@code newLoggedUser} is set as the
     *         new logged in user.
     */
    public static boolean updateLoggedUser( SimpleLoggedUser newLoggedUser ) {
    
        if( newLoggedUser != null )
            synchronized (userLock) {
                
                if( loggedInUser == null
                    || !newLoggedUser.getIdentification().equals( loggedInUser.getIdentification() ) ) {
                    return false;
                }
                loggedInUser = newLoggedUser;
                windowBase.getHeaderPanel().getUserPanel()
                          .setUsernameLabel( newLoggedUser.getIdentification() );
                return true;
            }
        return false;
    }
    
    /**
     * Removes the {@link SimpleLoggedUser} that is logged in in this session of the app.
     * 
     * @return {@code true} if the {@link SimpleLoggedUser} that was logged in was removed; <br/>
     *         {@code false} if no user was logged in.
     */
    private static boolean removeLoggedUser() {
    
        synchronized (userLock) {
            if( loggedInUser == null ) {
                return false;
            }
            loggedInUser = null;
            return true;
        }
    }
    
    
    
    // PRIVATE CONSTRUCTOR AND getInstance
    
    /**
     * The only instance of {@link FunctionalMainWindow}.
     */
    private static volatile FunctionalMainWindow theFunctionalMainWindow;
    
    /**
     * A lock for the {@link #theFunctionalMainWindow}.
     */
    private static Object windowLock = new Object();
    
    /**
     * Adds functionality to the panels of the {@link #windowBasew} and displays it.
     */
    private FunctionalMainWindow() {
    
        synchronized (windowBase) {
            
            putFunctionalHeaderPanel();
            putFunctionalFooterPanel();
            
            windowBase.setVisible( true );
        }
    }
    
    /**
     * Returns the {@link FunctionalMainWindow}.
     * 
     * @return The {@link FunctionalMainWindow}.
     * @throws LoggedInUserMissingException
     *             If no user was previously set as logged-in (using
     *             {@link #setLoggedUser(SimpleLoggedUser)}).
     */
    public static FunctionalMainWindow getInstance() throws LoggedInUserMissingException {
    
        if( loggedInUser == null )
            throw new LoggedInUserMissingException();
        
        if( theFunctionalMainWindow == null )
            synchronized (windowLock) {
                
                if( theFunctionalMainWindow == null ) {
                    theFunctionalMainWindow = new FunctionalMainWindow();
                }
            }
        windowBase.setVisible( true );
        return theFunctionalMainWindow;
    }
    
    
    
    // PRIVATE METHODS
    
    // used in the constructor
    /**
     * Replaces the non-functional header panel of this window with a new functional header panel.
     */
    private void putFunctionalHeaderPanel() {
    
        windowBase.setHeaderPanel( (new FunctionalHeaderPanel( windowBase.getHeaderPanel() )).getHeaderPanel() );
        functionalizeTheLogOutButton();
        functionalizeTheTurnOffButton();
    }
    
    // used in the constructor
    /**
     * Replace the non-functional footer panel of this window with a new functional footer panel.
     */
    private void putFunctionalFooterPanel() {
    
        windowBase.setFooterPanel( (new FunctionalFooterPanel( windowBase.getFooterPanel(),
                                                               bodyPanelFunctionalizer )).getFooterPanel() );
    }
    
    // used in the putFunctionalHeaderPanel
    /**
     * Adds functionality to the log-out button so that it disposes the
     * {@link #theFunctionalMainWindow} and shows a new {@link FunctionalLoginWindow}.
     */
    private void functionalizeTheLogOutButton() {
    
        windowBase.getHeaderPanel().getUserPanel().getLogoutButton().addActionListener( action -> {
            windowBase.dispose();
            removeLoggedUser();
            new FunctionalLoginWindow();
        } );
    }
    
    // used in the putFunctionalHeaderPanel
    /**
     * Adds functionality to the turn-off button.
     * 
     * The given action will dispose of the current MainWindow, closing the program.
     */
    private void functionalizeTheTurnOffButton() {
    
        windowBase.getHeaderPanel().getUserPanel().getTurnOffButton()
                  .addActionListener( action -> windowBase.dispose() );
        // TODO check if this actually closes all windows
    }
    
}
