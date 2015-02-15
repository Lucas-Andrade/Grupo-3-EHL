package functionalcomponents.functionalmainwindow;



import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import swingworkers.SwingWorkerFactory;
import app.Utils;
import design.panels.mainwindowpanels.JHeaderPanelForMainWindow;
import design.windows.popupwindows.UnderConstrutionWindow;
import design.windows.userwindows.GetUsersWindow;
import entities.SimpleUser;
import exceptions.InternalErrorException;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.functionaluserwindows.FunctionalPatchUserWindow;
import functionalcomponents.functionaluserwindows.FunctionalPostUserWindow;


/**
 * Class whose instances add functionality to {@link JHeaderPanelForMainWindow}s. Giving
 * functionality to this type of panels means adding an {@link ActionListener} to most of its
 * buttons, but <b>windows who incorporate these panels still need to add functionality to the
 * log-out button and the turn-off button</b>.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalHeaderPanel {
    
    
    // STATIC MEMBERS
    
    /**
     * The {@link SwingWorkerFactory} that produces {@link SwingWorker}s for the button that gets
     * all the users registered in the app.
     */
    private static SwingWorkerFactory< GetAllUsersSW, Iterable< SimpleUser > > getAllUsers_SwingWorkerFactory;
    
    /**
     * A lock for the {@link #getAllUsers_SwingWorkerFactory}.
     */
    private static Object getAllUsers_SwFactoryLock = new Object();
    
    /**
     * Sets the {@link SwingWorkerFactory} that produces {@link GetAllUsersSW}s for the button that
     * gets all the users registered in the app.
     * 
     * @param swFactory
     *            The {@link SwingWorkerFactory} that produces {@link GetAllUsersSW}s for the button
     *            that gets all the users registered in the app.
     * @return {@code true} if {@code swFactory} was set as the factory that produces swingworkers
     *         for the button; <br/>
     *         {@code false} if there was a factory already set or {@code swFactory} is {@code null}
     *         .
     */
    public static
            boolean
            setAllUsersFactory( SwingWorkerFactory< GetAllUsersSW, Iterable< SimpleUser > > swFactory ) {
    
        return Utils.setSWFactory( FunctionalHeaderPanel.class, "getAllUsers_SwingWorkerFactory",
                                   swFactory, getAllUsers_SwFactoryLock );
    }
    
    
    
    // INNER CLASS
    /**
     * Class whose instances are {@link SwingWorker}s able to add functionality to a button that
     * gets all the users registered in the app.
     * <p>
     * The unimplemented method {@link #doInBackground()} is supposed to return an {@link Iterable}
     * with the representations as {@link SimpleUser}s of all the users registered in the app; it is
     * not supposed to throw exceptions.
     * </p>
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public abstract static class GetAllUsersSW extends SwingWorker< Iterable< SimpleUser >, Void > {
        
        /**
         * Opens a new {@link GetUsersWindow} that presents all the users returned by the method
         * {@link #doInBackground()}.
         */
        @Override
        protected void done() {
        
            try {
                new GetUsersWindow( get() );
            }
            catch( InterruptedException | ExecutionException e ) {
                throw new InternalErrorException(
                                                  "UNEXPECTED ERROR IN FunctionalHeaderPanel.GetAllUsersSW",
                                                  e );
            }
        }
        
    }
    
    
    
    // INSTANCE FIELD
    /**
     * The {@link JHeaderPanelForMainWindow} receiving functionality.
     */
    private JHeaderPanelForMainWindow headerPanel;
    
    
    
    // CONSTRUCTOR & PRIVATE METHODS
    
    /**
     * Adds functionality to the non-functional {@link JHeaderPanelForMainWindow}
     * {@code headerPanel}.
     * 
     * @param headerPanel
     *            The {@link JHeaderPanelForMainWindow} to which we will had functionality.
     */
    public FunctionalHeaderPanel( JHeaderPanelForMainWindow headerPanel ) {
    
        this.headerPanel = headerPanel;
        addFunctionality();
    }
    
    /**
     * Adds functionality to the add-user button, the change-password button, the get-all-users
     * button and the remove-user button.
     */
    private void addFunctionality() {
    
        addChangePasswordButtonAction();
        
        addAddUserButtonAction();
        addRemoveUserButtonAction();
        addInfoAllUsersButtonAction();
    }
    
    /**
     * Adds functionality to the {@link JHeaderPanelForMainWindow#changePasswordButton}; this button
     * opens a new {@link FunctionalPatchUserWindow}.
     */
    private void addChangePasswordButtonAction() {
    
        headerPanel.getUserPanel().getChangePasswordButton()
                   .addActionListener( action -> new FunctionalPatchUserWindow() );
    }
    
    /**
     * Adds functionality to the {@link JHeaderPanelForMainWindow#addUserButton}; this button opens
     * a new {@link FunctionalPostUserWindow}.
     */
    private void addAddUserButtonAction() {
    
        headerPanel.getUserPanel().getAddUserButton()
                   .addActionListener( action -> new FunctionalPostUserWindow() );
    }
    
    /**
     * This functionality is not yet supported; this button opens a new
     * {@link UnderConstrutionWindow}.
     */
    private void addRemoveUserButtonAction() {
    
        headerPanel.getUserPanel().getRemoveUserButton()
                   .addActionListener( action -> new UnderConstrutionWindow() );
    }
    
    /**
     * Adds functionality to the {@link JHeaderPanelForMainWindow#infoAllUsersButton}; this button
     * opens a new {@link GetUsersWindow}.
     */
    private void addInfoAllUsersButtonAction() {
    
        headerPanel.getUserPanel().getInfoAllUsersButton()
                   .addActionListener( action -> runNew_GetAllUsers_SwingWorker() );
    }
    
    /**
     * Runs a new {@link SwingWorker} created by {@link #getAllUsers_SwingWorkerFactory}. If the
     * {@link #getAllUsers_SwingWorkerFactory} is {@code null}, it is opened a new
     * {@link UnderConstrutionWindow}.
     */
    private void runNew_GetAllUsers_SwingWorker() {
    
        try {
            Utils.runNewSwingWorker( getAllUsers_SwingWorkerFactory,
                                     FunctionalHeaderPanel.class.getSimpleName() );
        }
        catch( SwingWorkerFactoryMissingException e ) {
            new UnderConstrutionWindow();
        }
    }
    
    
    
    // PUBLIC METHOD
    /**
     * Returns the base {@link JHeaderPanelForMainWindow} that this {@link FunctionalHeaderPanel} is
     * giving functionality to.
     * 
     * @return The base {@link JHeaderPanelForMainWindow} that this {@link FunctionalHeaderPanel} is
     *         giving functionality to.
     */
    public JHeaderPanelForMainWindow getHeaderPanel() {
    
        return headerPanel;
    }
    
    
}
