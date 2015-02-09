package functionalcomponents.functionaluserwindows;


import Airship;
import User;
import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import functionalcomponents.FunctionalWindow;
import functionalcomponents.FunctionalWindowSwingWorker;
import functionalcomponents.functionalmainwindow.FunctionalMainWindow;
import main.java.Database;
import main.java.domain.commands.AuthenticateUserCommand;
import main.java.gui.design.windows.MainWindow;
import main.java.gui.design.windows.userwindows.LogInWindow;


/**
 * Class whose instances have the purpose of add functionality to a {@link LogInWindow}. Giving
 * functionality to a window means adding an {@link ActionListener} to all its buttons.
 *
 * Extends {@link FunctionalWindow} of {@link User}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalLoginWindow extends FunctionalWindow< User > {
    
    /**
     * {@code functionalWindow} - The {@code LoginWindow} we want to add functionality to.
     */
    private LogInWindow functionalWindow;
    
    /**
     * The {@code User} and {@code Airship}'s databases
     */
    private Database< User > usersDatabase;
    private Database< Airship > airshipsDatabase;
    
    /**
     * Public constructor that will add functionality to a given non functional {@link LoginWindow}
     * and will display it.
     * 
     * @param nonFunctionalWindow
     *            - The {@code LoginWindow} we want to add functionality to.
     * @param usersDatabase
     * @param airshipsDatabase
     */
    public FunctionalLoginWindow( LogInWindow nonFunctionalWindow, Database< User > usersDatabase,
                                  Database< Airship > airshipsDatabase ) {
        
        super( nonFunctionalWindow );
        
        this.functionalWindow = nonFunctionalWindow;
        
        this.usersDatabase = usersDatabase;
        this.airshipsDatabase = airshipsDatabase;
    }
    
    /**
     * Method that will return a {@link FunctionalWindowSwingWorker} with an {@code Override}
     * implementation of its {@link SwingWorker#doInBackground() doInBackground()} and
     * {@link FunctionalWindowSwingWorker#functionalDone(Object) functionalDone(Object)} methods to
     * add the correct functionality to a {@link LogInWindow}.
     * 
     * @return Returns a {@link FunctionalWindowSwingWorker} with an {@code Override} of its
     *         methods.
     */
    @Override
    protected FunctionalWindowSwingWorker< User > getSwingWorker() {
        
        return new FunctionalWindowSwingWorker< User >( functionalWindow.getErrorJTextArea() ) {
            
            /**
             * String representation of the parameters to use in the commands and that are obtained
             * from the window's text fields.
             */
            String username = functionalWindow.getUserPanel().getJTextField().getText();
            String password = functionalWindow.getPasswordPanel().getJTextField().getText();
            
            /**
             * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method
             * with the purpose of executing a {@link AuthenticateUserCommand} and obtaining its
             * result.
             * 
             * @return Returns a {@link User}
             */
            @Override
            protected User doInBackground() throws Exception {
                
                return new AuthenticateUserCommand( username, password, usersDatabase ).call()
                                                                                       .get();
            }
            
            /**
             * Implementation of the {@link FunctionalWindowSwingWorker#functionalDone()
             * functionalDone()}. This method will receive the result of the
             * {@link SwingWorker#doInBackground() doInBackground()} method and open a new
             * {@link FunctionalMainWindow}, closing this window.
             * 
             * @param resultOfDoInBackGround
             *            - The result of the {@link SwingWorker#doInBackground() doInBackground()}
             *            method.
             */
            @Override
            protected void functionalDone( User resultOfDoInBackGround ) throws Exception {
                
                new FunctionalMainWindow(
                                          new MainWindow( airshipsDatabase,
                                                          airshipsDatabase.getAllElements().get() ),
                                          usersDatabase, airshipsDatabase, resultOfDoInBackGround );
                
                functionalWindow.dispose();
            }
        };
    }
    
    
    public class SwingWorker extends FunctionalWindowSwingWorker< User > {
        
        
    }
    
    
}
