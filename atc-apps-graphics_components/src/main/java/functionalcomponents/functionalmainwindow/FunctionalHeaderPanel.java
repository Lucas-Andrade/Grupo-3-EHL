package functionalcomponents.functionalmainwindow;



import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import org.eclipse.jetty.server.Authentication.User;
import swingworkers.SwingWorkerFactory;
import design.panels.mainwindowpanels.JHeaderPanelForMainWindow;
import design.windows.MainWindow;
import design.windows.popupwindows.UnderConstrutionWindow;
import design.windows.userwindows.GetUsersWindow;
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
    private SwingWorkerFactory< ?, ? > addUserFactory;
    private SwingWorkerFactory< ?, ? > changePasswordFactory;
    private SwingWorkerFactory< ?, ? > infoAllUsersFactory;
    private SwingWorkerFactory< ?, ? > removeUserFactory;
    
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
        
        addAddUserButtonAction();
        addChangePasswordButtonAction();
        addInfoAllUsersButtonAction();
        addRemoveUserButtonAction();
    }
    
    // Private Method
    
    /**
     * Method that will add functionality to the {@link JHeaderPanelForMainWindow#addUserButton
     * addUserButton} button.
     * 
     * The given action will be to create a new {@link FunctionalPostUserWindow} with the objective
     * of posting new {@link User} in the given database.
     */
    private void addAddUserButtonAction() {
    
        headerPanel.getUserPanel().getAddUserButton()
                   .addActionListener( action -> action( addUserFactory ) );
    }
    
    /**
     * Method that will add functionality to the
     * {@link JHeaderPanelForMainWindow#changePasswordButton changePasswordButton} button.
     * 
     * The given action will be to create a new {@link FunctionalPatchUserWindow} with the objective
     * of patching a {@link User} existing in the given database.
     */
    private void addChangePasswordButtonAction() {
    
        headerPanel.getUserPanel().getChangePasswordButton()
                   .addActionListener( action -> action( changePasswordFactory ) );
    }
    
    /**
     * DELETE USER - Not Implemented!
     */
    private void addRemoveUserButtonAction() {
    
        headerPanel.getUserPanel().getRemoveUserButton()
                   .addActionListener( action -> action( removeUserFactory ) );
    }
    
    /**
     * Method that will add functionality to the
     * {@link JHeaderPanelForMainWindow#infoAllUsersButton infoAllUsersButton} button.
     * 
     * The given action will be to create a new {@link GetUsersWindow} that will show a list of all
     * the {@link User users} existing in the given database.
     */
    private void addInfoAllUsersButtonAction() {
    
        headerPanel.getUserPanel().getInfoAllUsersButton()
                   .addActionListener( action -> action( infoAllUsersFactory ) );
    }
    
    /**
     * Create a new {@link SwingWorker} associated with the given {@code factory} and run it. If the
     * {@code factory} is null, then its open the {@link UnderConstrutionWindow}.
     * 
     * @param factory
     *            - The {@link SwingWorker} factory.
     */
    private void action( SwingWorkerFactory< ?, ? > factory ) {
    
        try {
            factory.newInstance().run();
        }
        catch( NullPointerException e ) {
            new UnderConstrutionWindow();
        }
    }
    
    // Public Get Method
    
    /**
     * @return the {@code headerPanel}.
     */
    public JHeaderPanelForMainWindow getHeaderPanel() {
    
        return headerPanel;
    }
    
    /**
     * @param addUserFactory
     *            the addUserFactory to set
     */
    public void setAddUserFactory( SwingWorkerFactory< ?, ? > addUserFactory ) {
    
        this.addUserFactory = addUserFactory;
    }
    
    /**
     * @param changePasswordFactory
     *            the changePasswordFactory to set
     */
    public void setChangePasswordFactory( SwingWorkerFactory< ?, ? > changePasswordFactory ) {
    
        this.changePasswordFactory = changePasswordFactory;
    }
    
    /**
     * @param infoAllUsersFactory
     *            the infoAllUsersFactory to set
     */
    public void setInfoAllUsersFactory( SwingWorkerFactory< ?, ? > infoAllUsersFactory ) {
    
        this.infoAllUsersFactory = infoAllUsersFactory;
    }
    
    /**
     * @param removeUserFactory
     *            the removeUserFactory to set
     */
    public void setRemoveUserFactory( SwingWorkerFactory< ?, ? > removeUserFactory ) {
    
        this.removeUserFactory = removeUserFactory;
    }
}
