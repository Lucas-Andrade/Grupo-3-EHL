package main.java.gui.functionalcomponents.functionaluserwindows;


import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import main.java.domain.commands.postcommands.PostUserCommand;
import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.gui.design.windows.popupwindows.SuccessWindow;
import main.java.gui.design.windows.userwindows.PatchUserWindow;
import main.java.gui.design.windows.userwindows.PostUserWindow;
import main.java.gui.functionalcomponents.FunctionalWindow;
import main.java.gui.functionalcomponents.FunctionalWindowSwingWorker;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances have the purpose of add functionality to a {@link PostUserWindow}. Giving
 * functionality to a window means adding an {@link ActionListener} to all its buttons.
 *
 * Extends {@link FunctionalWindow} of {@link String}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalPostUserWindow extends FunctionalWindow< String > {
    
    /**
     * {@code functionalWindow} - The {@code PatchUserWindow} we want to add functionality to.
     */
    private PostUserWindow functionalWindow;
    
    /**
     * {@code usersDatabase} - The users database.
     */
    private Database< User > usersDatabase;
    
    /**
     * {@code userWhoIsPosting} - The {@code User} who is posting the new user.
     */
    private User userWhoIsPosting;
    
    /**
     * Public constructor that will add functionality to a given non functional
     * {@link PatchUserWindow} and will display it.
     * 
     * @param nonFunctionalWindow
     *            - The {@code PostUserWindow} we want to add functionality to.
     * @param usersDatabase
     *            - The users database.
     * @param userWhoIsPosting
     *            - The {@code User} who is posting the new user.
     */
    public FunctionalPostUserWindow( PostUserWindow nonFunctionalWindow,
                                     Database< User > usersDatabase, User userWhoIsPosting ) {
        
        super( nonFunctionalWindow );
        
        this.functionalWindow = nonFunctionalWindow;
        this.usersDatabase = usersDatabase;
        this.userWhoIsPosting = userWhoIsPosting;
    }
    
    /**
     * Method that will return a {@link FunctionalWindowSwingWorker} with an {@code Override}
     * implementation of its {@link SwingWorker#doInBackground() doInBackground()} and
     * {@link FunctionalWindowSwingWorker#functionalDone(Object) functionalDone(Object)} methods to
     * add the correct functionality to a {@link PostUserWindow}.
     * 
     * @return Returns a {@link FunctionalWindowSwingWorker} with an {@code Override} of its
     *         methods.
     */
    @Override
    protected FunctionalWindowSwingWorker< String > getSwingWorker() {
        
        return new FunctionalWindowSwingWorker< String >( functionalWindow.getErrorJTextArea() ) {
            
            /**
             * String representation of the parameters to use in the commands and that are obtained
             * from the window's text fields.
             */
            private String username = functionalWindow.getUsername().getJTextField().getText();
            private String password = functionalWindow.getPassword().getJTextField().getText();
            private String confirmPassword = functionalWindow.getConfirmPassword().getJTextField()
                                                             .getText();
            private String email = functionalWindow.getEmail().getJTextField().getText();
            private String fullName = functionalWindow.getFullname().getJTextField().getText();
            
            /**
             * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method
             * with the purpose of verifying if the inserted {@code password} and
             * {@code confirmPassword} match and executing a {@link PostUserCommand}, obtaining its
             * result.
             * 
             * @return Returns a String with information regarding the result of the command which
             *         will be the same as the return of it's call method.
             * 
             * @throws InvalidArgumentException
             *             If the {@code password} and {@code confirmPassword} don't match or if the
             *             value given for to the PostUserCommand are invalid.
             */
            @Override
            protected String doInBackground() throws Exception {
                
                if( !password.equals( confirmPassword ) )
                    throw new InvalidArgumentException( "The Passwords Don't Match" );
                
                return new PostUserCommand( username, password, email, fullName, usersDatabase,
                                            userWhoIsPosting ).call();
            }
            
            /**
             * Implementation of the {@link FunctionalWindowSwingWorker#functionalDone()
             * functionalDone()}. This method will receive the result of the
             * {@link SwingWorker#doInBackground() doInBackground()} method and, if this result
             * positive, open new {@link SuccessWindow}, closing this one.
             * 
             * @param resultOfDoInBackGround
             *            - The result of the {@link SwingWorker#doInBackground() doInBackground()}
             *            method.
             */
            @Override
            public void functionalDone( String resultOfDoInBackGround ) throws Exception {
                
                new SuccessWindow( resultOfDoInBackGround );
                functionalWindow.dispose();
            }
        };
    }
}