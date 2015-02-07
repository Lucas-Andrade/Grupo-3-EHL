package functionalcomponents.functionalmainwindow;


import User;

import java.awt.event.ActionListener;

import functionalcomponents.functionaluserwindows.FunctionalPatchUserWindow;
import functionalcomponents.functionaluserwindows.FunctionalPostUserWindow;
import main.java.Database;
import main.java.domain.commands.getcommands.GetAllElementsInADatabaseCommand;
import main.java.gui.design.panels.mainwindowpanels.JHeaderPanelForMainWindow;
import main.java.gui.design.windows.MainWindow;
import main.java.gui.design.windows.popupwindows.UnderConstrutionWindow;
import main.java.gui.design.windows.userwindows.GetUsersWindow;
import main.java.gui.design.windows.userwindows.PatchUserWindow;
import main.java.gui.design.windows.userwindows.PostUserWindow;
import main.java.utils.exceptions.InternalErrorException;


/**
 * Class whose instances have the purpose of adding functionality to a
 * {@link JHeaderPanelForMainWindow}. Giving functionality to a panel means adding an
 * {@link ActionListener} to all its buttons (i.e. {@link User Users} commands: POST, GET, PATCH,
 * DELETE
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalHeaderPanel {
    
    // Field
    
    /**
     * {@code headerPanel} - The {@link MainWindow} header panel to which we will had functionality.
     */
    private JHeaderPanelForMainWindow headerPanel;
    
    /**
     * {@code usersDatabase} - The users database.
     */
    private Database< User > usersDatabase;
    
    /**
     * {@code user} - The user who is currently logged in.
     */
    private User user;
    
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
    public FunctionalHeaderPanel( JHeaderPanelForMainWindow headerPanel,
                                  Database< User > usersDatabase, User user ) {
    
        this.headerPanel = headerPanel;
        this.usersDatabase = usersDatabase;
        this.user = user;
        
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
    
        headerPanel.getUserPanel()
                   .getAddUserButton()
                   .addActionListener( action -> new FunctionalPostUserWindow(
                                                                               new PostUserWindow(),
                                                                               usersDatabase, user ) );
    }
    
    /**
     * Method that will add functionality to the
     * {@link JHeaderPanelForMainWindow#changePasswordButton changePasswordButton} button.
     * 
     * The given action will be to create a new {@link FunctionalPatchUserWindow} with the objective
     * of patching a {@link User} existing in the given database.
     */
    private void addChangePasswordButtonAction() {
    
        headerPanel.getUserPanel()
                   .getChangePasswordButton()
                   .addActionListener( action -> new FunctionalPatchUserWindow(
                                                                                new PatchUserWindow(),
                                                                                usersDatabase ) );
    }
    
    /**
     * DELETE USER - Not Implemented!
     */
    private void addRemoveUserButtonAction() {
    
        headerPanel.getUserPanel().getRemoveUserButton()
                   .addActionListener( action -> new UnderConstrutionWindow() );
    }
    
    /**
     * Method that will add functionality to the
     * {@link JHeaderPanelForMainWindow#infoAllUsersButton infoAllUsersButton} button.
     * 
     * The given action will be to create a new {@link GetUsersWindow} that will show a list of all
     * the {@link User users} existing in the given database.
     */
    private void addInfoAllUsersButtonAction() {
    
        headerPanel.getUserPanel()
                   .getInfoAllUsersButton()
                   .addActionListener( action -> {
                                           try {
                                               new GetUsersWindow(
                                                                   usersDatabase,
                                                                   new GetAllElementsInADatabaseCommand< User >(
                                                                                                                 usersDatabase ).call()
                                                                                                                                .get() );
                                           }
                                           catch( Exception e ) {
                                               throw new InternalErrorException( e );
                                           }
                                       } );
    }
    
    // Public Get Method
    
    /**
     * @return the {@code headerPanel}.
     */
    public JHeaderPanelForMainWindow getHeaderPanel() {
    
        return headerPanel;
    }
}
