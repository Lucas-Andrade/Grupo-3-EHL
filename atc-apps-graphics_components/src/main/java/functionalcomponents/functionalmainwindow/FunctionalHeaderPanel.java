package functionalcomponents.functionalmainwindow;



import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import org.eclipse.jetty.server.Authentication.User;
import swingworkers.SwingWorkerFactory;
import app.Utils;
import design.panels.mainwindowpanels.JHeaderPanelForMainWindow;
import design.windows.MainWindow;
import design.windows.popupwindows.UnderConstrutionWindow;
import design.windows.userwindows.GetUsersWindow;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.functionaluserwindows.FunctionalPatchUserWindow;
import functionalcomponents.functionaluserwindows.FunctionalPostUserWindow;


/**
 * Class whose instances have the purpose of adding functionality to a
 * {@link JHeaderPanelForMainWindow}. Giving functionality to a panel means adding an
 * {@link ActionListener} to all its buttons (i.e. {@link User Users} commands: POST, GET, PATCH,
 * DELETE
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalHeaderPanel {
    
    
    // Factories
    private static SwingWorkerFactory< ?, ? > addUserFactory;
    private static SwingWorkerFactory< ?, ? > changePasswordFactory;
    private static SwingWorkerFactory< ?, ? > infoAllUsersFactory;
    private static SwingWorkerFactory< ?, ? > removeUserFactory;
    
    
    // Factories Locks
    private static Object addUserFactoryLock = new Object();
    private static Object changePasswordFactoryLock = new Object();
    private static Object infoAllUsersFactoryLock = new Object();
    private static Object removeUserFactoryLock = new Object();
    
    // Set factories
    /**
     * @param addUserFactory
     *            the addUserFactory to set
     */
    public static boolean setAddUserFactory( SwingWorkerFactory< ?, ? > addUserFactory ) {
    
        return Utils.setSWFactory( FunctionalHeaderPanel.class, "addUserFactory",
                                   addUserFactory, addUserFactoryLock );
    }
    
    /**
     * @param changePasswordFactory
     *            the changePasswordFactory to set
     */
    public static boolean setChangePasswordFactory( SwingWorkerFactory< ?, ? > changePasswordFactory ) {
    
        
        return Utils.setSWFactory( FunctionalHeaderPanel.class, "changePasswordFactory",
                                   changePasswordFactory, changePasswordFactoryLock );
    }
    
    /**
     * @param infoAllUsersFactory
     *            the infoAllUsersFactory to set
     */
    public boolean setInfoAllUsersFactory( SwingWorkerFactory< ?, ? > infoAllUsersFactory ) {
    
        return Utils.setSWFactory( FunctionalHeaderPanel.class, "infoAllUsersFactory",
                                   infoAllUsersFactory, infoAllUsersFactoryLock );
    }
    
    /**
     * @param removeUserFactory
     *            the removeUserFactory to set
     */
    public boolean setRemoveUserFactory( SwingWorkerFactory< ?, ? > removeUserFactory ) {
    
        return Utils.setSWFactory( FunctionalHeaderPanel.class, "removeUserFactory",
                                   removeUserFactory, removeUserFactoryLock );
    }

    
    
    // Field
    /**
     * {@code headerPanel} - The {@link MainWindow} header panel to which we will had functionality.
     */
    private JHeaderPanelForMainWindow headerPanel;
    
    
    
    // Constructor
    /**
     * Public constructor that will add functionality to a given non functional
     * {@link JHeaderPanelForMainWindow}.
     * 
     * @param headerPanel
     *            - The {@link MainWindow} header panel to which we will had functionality.
     * @param usersDatabase
     *            - The users database.
     * @param user
     *            - The user who is currently logged in.
     */
    public FunctionalHeaderPanel( JHeaderPanelForMainWindow headerPanel ) {
    
        this.headerPanel = headerPanel;
        
        addFunctionality();
    }

    // Private Methods
    /**
     * All the methods that will give functionality to the {@code footerPanel}.
     */
    private void addFunctionality() {
    
        addAddUserButtonAction();
        addChangePasswordButtonAction();
        addInfoAllUsersButtonAction();
        addRemoveUserButtonAction();
    }

    //Add Action Listeners
    
    //POST
    /**
     * Method that will add functionality to the {@link JHeaderPanelForMainWindow#addUserButton
     * addUserButton} button.
     * 
     * The given action will be to create a new {@link FunctionalPostUserWindow} with the objective
     * of posting new {@link User} in the given database.
     */
    private void addAddUserButtonAction() {
    
        headerPanel.getUserPanel().getAddUserButton()
                   .addActionListener( action -> runSingWorker( addUserFactory ) );
    }
    
    //PATCH
    /**
     * Method that will add functionality to the
     * {@link JHeaderPanelForMainWindow#changePasswordButton changePasswordButton} button.
     * 
     * The given action will be to create a new {@link FunctionalPatchUserWindow} with the objective
     * of patching a {@link User} existing in the given database.
     */
    private void addChangePasswordButtonAction() {
    
        headerPanel.getUserPanel().getChangePasswordButton()
                   .addActionListener( action -> runSingWorker( changePasswordFactory ) );
    }
    
    //DELET
    /**
     * DELETE USER - Not Implemented!
     */
    private void addRemoveUserButtonAction() {
    
        headerPanel.getUserPanel().getRemoveUserButton()
                   .addActionListener( action -> runSingWorker( removeUserFactory ) );
    }
    
    //GET
    /**
     * Method that will add functionality to the
     * {@link JHeaderPanelForMainWindow#infoAllUsersButton infoAllUsersButton} button.
     * 
     * The given action will be to create a new {@link GetUsersWindow} that will show a list of all
     * the {@link User users} existing in the given database.
     */
    private void addInfoAllUsersButtonAction() {
    
        headerPanel.getUserPanel().getInfoAllUsersButton()
                   .addActionListener( action -> runSingWorker( infoAllUsersFactory ) );
    }
    
    //New SwingWorkers
    /**
     * Create a new {@link SwingWorker} associated with the given {@code factory} and run it. If the
     * {@code factory} is null, then its open the {@link UnderConstrutionWindow}.
     * 
     * @param factory
     *            - The {@link SwingWorker} factory.
     */
    private void runSingWorker( SwingWorkerFactory< ?, ? > factory ) {
    
        try {
            Utils.runNewSwingWorker(factory, "FunctionalHeaderPanel");
        }
        catch( SwingWorkerFactoryMissingException e ) {
            new UnderConstrutionWindow();
        }
    }
    
    // Public Method
    /**
     * @return the {@code headerPanel}.
     */
    public JHeaderPanelForMainWindow getHeaderPanel() {
    
        return headerPanel;
    }
    

}
