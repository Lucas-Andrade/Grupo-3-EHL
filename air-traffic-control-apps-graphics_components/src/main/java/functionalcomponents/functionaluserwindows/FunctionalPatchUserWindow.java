package functionalcomponents.functionaluserwindows;


import User;

import java.awt.event.ActionListener;

import javax.swing.SwingWorker;

import functionalcomponents.FunctionalWindow;
import functionalcomponents.FunctionalWindowSwingWorker;
import main.java.Database;
import main.java.domain.commands.CompletionStatus;
import main.java.domain.commands.patchcommands.PatchUserPasswordCommand;
import main.java.gui.design.windows.popupwindows.SuccessWindow;
import main.java.gui.design.windows.userwindows.PatchUserWindow;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances have the purpose of add functionality to a {@link PatchUserWindow}. Giving
 * functionality to a window means adding an {@link ActionListener} to all its buttons.
 *
 * Extends {@link FunctionalWindow} of {@link String}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalPatchUserWindow extends FunctionalWindow< CompletionStatus > {
    
    /**
     * {@code functionalWindow} - The {@code PatchUserWindow} we want to add functionality to.
     */
    private PatchUserWindow functionalWindow;
    
    /**
     * {@code usersDatabase} - The users database.
     */
    private Database< User > usersDatabase;
    
    /**
     * Public constructor that will add functionality to a given non functional
     * {@link PatchUserWindow} and will display it.
     * 
     * @param nonFunctionalWindow
     *            - The {@code PatchUserWindow} we want to add functionality to.
     * @param usersDatabase
     *            - The users database.
     */
    public FunctionalPatchUserWindow( PatchUserWindow nonFunctionalWindow,
                                      Database< User > usersDatabase ) {
    
        super( nonFunctionalWindow );
        
        this.functionalWindow = nonFunctionalWindow;
        this.usersDatabase = usersDatabase;
    }
    
    /**
     * Method that will return a {@link FunctionalWindowSwingWorker} with an {@code Override}
     * implementation of its {@link SwingWorker#doInBackground() doInBackground()} and
     * {@link FunctionalWindowSwingWorker#functionalDone(Object) functionalDone(Object)} methods to
     * add the correct functionality to a {@link PatchUserWindow}.
     * 
     * @return Returns a {@link FunctionalWindowSwingWorker} with an {@code Override} of its
     *         methods.
     */
    @Override
    protected FunctionalWindowSwingWorker< CompletionStatus > getSwingWorker() {
    
        return new FunctionalWindowSwingWorker< CompletionStatus >(
                                                                    functionalWindow.getErrorJTextArea() ) {
            
            /**
             * String representation of the parameters to use in the commands and that are obtained
             * from the window's text fields.
             */
            private String username = functionalWindow.getUserPanel().getJTextField().getText();
            private String oldPassword = functionalWindow.getOldPasswordPanel().getJTextField()
                                                         .getText();
            private String newPassword = functionalWindow.getNewPasswordPanel().getJTextField()
                                                         .getText();
            private String newPasswordToConfirm =
                    functionalWindow.getNewPasswordConfirmationPanel().getJTextField().getText();
            
            /**
             * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method
             * with the purpose of verifying if the inserted {@code newPassord} and
             * {@code newPasswordToConfirm} match and executing a {@link PatchUserPasswordCommand},
             * obtaining its result.
             * 
             * @return Returns a String.
             * 
             * @throws InvalidArgumentException
             *             If the result of the passwords don't match or no password was written in
             *             the text appropriate text field.
             */
            @Override
            protected CompletionStatus doInBackground() throws Exception {
            
                if( newPassword.equals( "" ) )
                    throw new InvalidArgumentException( "Please Insert a New Password" );
                
                if( !newPasswordToConfirm.equals( newPassword ) ) {
                    throw new InvalidArgumentException( "The Passwords Don't Match" );
                }
                
                return new PatchUserPasswordCommand( usersDatabase, username, oldPassword,
                                                     newPassword ).call();
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
             * 
             * @throws InvalidArgumentException
             *             If the result of the {@code doInBackground} method is not positive.
             */
            @Override
            public void functionalDone( CompletionStatus resultOfDoInBackGround ) throws Exception {
            
                if( resultOfDoInBackGround.operationCompletedSuccessfully() ) {
                    
                    new SuccessWindow( resultOfDoInBackGround.getMessage() );
                    functionalWindow.dispose();
                }
                else {
                    functionalWindow.getErrorJTextArea()
                                    .setText( resultOfDoInBackGround.getMessage() );
                }
            }
        };
    }
}
