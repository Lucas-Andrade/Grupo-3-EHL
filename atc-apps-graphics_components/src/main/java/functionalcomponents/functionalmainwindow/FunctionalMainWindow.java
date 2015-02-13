package functionalcomponents.functionalmainwindow;



import java.util.Collection;
import javax.swing.SwingWorker;
import swingworkers.SwingWorkerFactory;
import design.panels.mainwindowpanels.JFooterPanelForMainWindow;
import design.panels.mainwindowpanels.JHeaderPanelForMainWindow;
import design.windows.MainWindow;
import entities.SimpleAirship;
import entities.SimpleLoggedUser;
import exceptions.InvalidArgumentException;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow;



/**
 * Class whose instances have the purpose of adding functionality to a {@link MainWindow}. Giving
 * functionality to a {@link MainWindow} means replacing its panels with functional panels.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalMainWindow {
    
    
    
    // STATIC FIELDS
    
    /**
     * The {@code MainWindow} we want to add functionality to.
     */
    public static final MainWindow windowBase = new MainWindow();
    
    /**
     * The user who is currently logged in.
     */
    private static volatile SimpleLoggedUser user;
    
    /**
     * The factory responsible for producing {@link SwingWorker}s that return the set of airships to
     * appear in this window's body panel.
     */
    private static SwingWorkerFactory< SwingWorker< Collection< SimpleAirship >, Void >, Collection< SimpleAirship > > swFactory;
    
    /**
     * Locks for the {@link #user} and the {@link #swFactory}.
     */
    private static Object userLock = new Object();
    private static Object factoryLock = new Object();
    
    // STATIC METHODS
    
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
    private static boolean setLoggedUser( SimpleLoggedUser loggedUser ) {
    
        if( loggedUser != null )
            synchronized (userLock) {
                if( user == null ) {
                    user = loggedUser;
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
            return user;
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
                if( user == null
                    || !newLoggedUser.getIdentification().equals( user.getIdentification() ) ) {
                    return false;
                }
                user = newLoggedUser;
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
    public static boolean removeLoggedUser() {
    
        synchronized (userLock) {
            if( user == null ) {
                return false;
            }
            user = null;
            return true;
        }
    }
    
    /**
     * Sets the {@link SwingWorkerFactory} that produces {@link SwingWorker}s that return the set of
     * airships to appear in this window's body panel.
     * 
     * @param factory
     *            The {@link SwingWorkerFactory} that produces {@link SwingWorker}s that return the
     *            set of airships to appear in this window's body panel.
     * @return {@code true} if {@code factory} was set as the factory that produces
     *         {@link SwingWorker}s that return the set of airships to appear in this window's body
     *         panel; <br/>
     *         {@code false} if there was a factory already set or {@code factory} is {@code null}.
     */
    public static
            boolean
            setSwingWorkerFactory( SwingWorkerFactory< SwingWorker< Collection< SimpleAirship >, Void >, Collection< SimpleAirship > > factory ) {
    
        if( factory != null )
            synchronized (factoryLock) {
                if( swFactory == null ) {
                    swFactory = factory;
                    return true;
                }
            }
        return false;
    }
    
    
    private FunctionalBodyPanel functionalBodyPanel;
    
    // CONSTRUCTOR
    /**
     * Adds functionality to the panels of a {@link MainWindow} and displays it.
     * 
     * @throws InvalidArgumentException
     *             If {@code loggedUser} is {@code null} and there was no previously set logged-in
     *             user.
     */
    public FunctionalMainWindow( SimpleLoggedUser loggedUser ) throws InvalidArgumentException {
    
        synchronized (windowBase) {
            
            if( !setLoggedUser( loggedUser ) && loggedUser == null )
                throw new InvalidArgumentException( "Cannot create FunctionalMainWindow with null"
                                                    + " logged-in user." );
            
            functionalHeaderPanel();
            functionalFooterPanel();
            functionalBodyPanel = new FunctionalBodyPanel( windowBase.getBodyPanel() );
            
            windowBase.setVisible( true );
        }
    }
    
    
    
    // PRIVATE METHODS
    
    // used in the constructor
    /**
     * Replaces the non-functional window's header panel with a new functional header panel using
     * the method {@link MainWindow#setHeaderPanel(JHeaderPanelForMainWindow)
     * setHeaderPanel(JHeaderPanelForMainWindow)}.
     */
    private void functionalHeaderPanel() {
    
        windowBase.setHeaderPanel( (new FunctionalHeaderPanel( windowBase.getHeaderPanel(), user )).getHeaderPanel() );
        functionalLogOutButton();
        functionalTurnOffButton();
    }
    
    // used in the constructor
    /**
     * Method that will replace the given non functional window's footer panel with a new functional
     * footer panel using the method {@link MainWindow#setFooterPanel(JFooterPanelForMainWindow)
     * setFooterPanel(JFooterPanelForMainWindow)}.
     */
    private void functionalFooterPanel() {
    
        windowBase.setFooterPanel( (new FunctionalFooterPanel( windowBase.getFooterPanel(),
                                                               windowBase.getBodyPanel() )).getFooterPanel() );
    }
    
    // used in the constructor
    /**
     * Method that will add functionality to the {@link JHeaderPanelForMainWindow#logoutButton
     * logoutButton} button.
     * 
     * The given action will dispose of the current MainWindow and create a new
     * {@link FunctionalLoginWindow} with the objective of allowing another user to logIn.
     */
    private void functionalLogOutButton() {
    
        windowBase.getHeaderPanel().getUserPanel().getLogoutButton().addActionListener( action -> {
            
            new FunctionalLoginWindow();
            windowBase.dispose();
        } );
    }
    
    // used in the constructor
    /**
     * Method that will add functionality to the {@link JHeaderPanelForMainWindow#turnOffButton
     * turnOffButton} button.
     * 
     * The given action will dispose of the current MainWindow, closing the program.
     */
    private void functionalTurnOffButton() {
    
        windowBase.getHeaderPanel().getUserPanel().getTurnOffButton()
                  .addActionListener( action -> windowBase.dispose() );
    }
    
    
    
    // PUBLIC GET METHODS
    
    /**
     * Returns the main window.
     * 
     * @return The main window.
     */
    public MainWindow getFunctionalMainWindow() {
    
        return windowBase;
    }
    
    public FunctionalBodyPanel getFunctionalBodyPanel() {
        
        return functionalBodyPanel;
    }
}
