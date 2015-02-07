package functionalcomponents.functionalmainwindow;


import Airship;
import User;
import java.awt.event.ActionListener;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow;
import main.java.Database;
import main.java.gui.design.panels.mainwindowpanels.JFooterPanelForMainWindow;
import main.java.gui.design.panels.mainwindowpanels.JHeaderPanelForMainWindow;
import main.java.gui.design.windows.MainWindow;
import main.java.gui.design.windows.userwindows.LogInWindow;


/**
 * Class whose instances have the purpose of adding functionality to a {@link MainWindow}. Giving
 * functionality to a window means adding an {@link ActionListener} to all its buttons.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalMainWindow {
    
    // Fields
    
    /**
     * {@code functionalMainWindow} - The {@code MainWindow} we want to add functionality to.
     */
    private MainWindow functionalMainWindow;
    
    /**
     * {@code usersDatabase} - The users database.
     */
    private Database< User > usersDatabase;
    
    /**
     * {@code airshipsDatabase} - The airships database.
     */
    private Database< Airship > airshipsDatabase;
    
    /**
     * {@code user} - The user who is currently logged in.
     */
    private User user;
    
    /**
     * Public constructor that will add functionality to a given non functional {@link MainWindow}.
     * 
     * @param nonFunctionalMainWindow
     *            - The {@code MainWindow} we want to add functionality to.
     * @param usersDatabase
     *            - The users database.
     * @param airshipsDatabase
     *            - The airships database.
     * @param user
     *            - The user who is currently logged in.
     */
    public FunctionalMainWindow( MainWindow nonFunctionalMainWindow,
                                 Database< User > usersDatabase,
                                 Database< Airship > airshipsDatabase, User user ) {
        
        this.functionalMainWindow = nonFunctionalMainWindow;
        
        this.usersDatabase = usersDatabase;
        this.airshipsDatabase = airshipsDatabase;
        this.user = user;
        
        functionalHeaderPanel();
        functionalFooterPanel();
        functionalLogOutButton();
        functionalTurnOffButton();
    }
    
    // Private Methods
    
    /**
     * Method that will replace the given non functional window's header panel with a new functional
     * header panel using the method {@link MainWindow#setHeaderPanel(JHeaderPanelForMainWindow)
     * setHeaderPanel(JHeaderPanelForMainWindow)}.
     */
    private void functionalHeaderPanel() {
        
        functionalMainWindow.setHeaderPanel( (new FunctionalHeaderPanel(
                                                                         functionalMainWindow.getHeaderPanel(),
                                                                         usersDatabase, user )).getHeaderPanel() );
    }
    
    /**
     * Method that will replace the given non functional window's footer panel with a new functional
     * footer panel using the method {@link MainWindow#setFooterPanel(JFooterPanelForMainWindow)
     * setFooterPanel(JFooterPanelForMainWindow)}.
     */
    private void functionalFooterPanel() {
        
        functionalMainWindow.setFooterPanel( (new FunctionalFooterPanel(
                                                                         functionalMainWindow.getFooterPanel(),
                                                                         functionalMainWindow.getBodyPanel(),
                                                                         airshipsDatabase,
                                                                         user,
                                                                         functionalMainWindow.getErrorJTextArea() )).getFooterPanel() );
    }
    
    /**
     * Method that will add functionality to the {@link JHeaderPanelForMainWindow#logoutButton
     * logoutButton} button.
     * 
     * The given action will dispose of the current MainWindow and create a new
     * {@link FunctionalLoginWindow} with the objective of allowing another user to logIn.
     */
    private void functionalLogOutButton() {
        
        functionalMainWindow.getHeaderPanel()
                            .getUserPanel()
                            .getLogoutButton()
                            .addActionListener( action -> {
                                                    
                                                    new FunctionalLoginWindow( new LogInWindow(),
                                                                               usersDatabase,
                                                                               airshipsDatabase );
                                                    functionalMainWindow.dispose();
                                                } );
    }
    
    /**
     * Method that will add functionality to the {@link JHeaderPanelForMainWindow#turnOffButton
     * turnOffButton} button.
     * 
     * The given action will dispose of the current MainWindow, closing the program.
     */
    private void functionalTurnOffButton() {
        
        functionalMainWindow.getHeaderPanel().getUserPanel().getTurnOffButton()
                            .addActionListener( action -> functionalMainWindow.dispose() );
    }
    
    // Public Get Methods
    
    /**
     * @return the {@code functionalMainWindow}.
     */
    public MainWindow getFunctionalMainWindow() {
        
        return functionalMainWindow;
    }
}
