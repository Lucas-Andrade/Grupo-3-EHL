package swingworkers;


import utils.CompletionStatus;
import utils.StringUtils;
import commands.patchcommands.PatchUserPasswordCommand;
import databases.Database;
import design.windows.userwindows.PatchUserWindow;
import elements.User;
import exceptions.InvalidArgumentException;
import functionalcomponents.functionaluserwindows.FunctionalPatchUserWindow;


/**
 * Concrete implementation of {@link FunctionalPatchUserWindow.SwingWorker}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchUserSwingWorker extends FunctionalPatchUserWindow.SwingWorker {
    
    
    private Database< User > usersDatabase;
    
    public PatchUserSwingWorker( PatchUserWindow window, Database< User > usersDatabase ) {
    
        super( window );
        this.usersDatabase = usersDatabase;
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalPostUserWindow.SwingWorker
    /**
     * Replaces the password of the user with the username {@link #username}, {@code oldPassword},
     * with {@code newPassword}. It is expected to return a {@link CompletionStatus} representing
     * the success/failure of the operation or an exception if the operation couldn't perform.
     * 
     * @return Returns a {@link CompletionStatus} representing the success/failure of the operation.
     * 
     * @throws Exception
     *             If (1) the {@link #password} and the {@link #confirmPassword} don't match or (2)
     *             the operation couldn't perform.
     */
    @Override
    protected CompletionStatus doInBackground() throws Exception {
    
        if( !newPassword.equals( newConfirmationPassword ) )
            throw new InvalidArgumentException( "New passwords don't match!" );
        
        return new PatchUserPasswordCommand(
                                             usersDatabase,
                                             StringUtils.parameterToString( usernameLabel, username ),
                                             StringUtils.parameterToString( oldPasswordLabel,
                                                                            oldPassword ),
                                             StringUtils.parameterToString( newPasswordLabel,
                                                                            newPassword ) ).call();
    }
    
    
    
    // INNER CLASS
    /**
     * Class whose instances are responsible for producing {@link PatchUserSwingWorker}s.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public static class Factory implements
            SwingWorkerFactory< FunctionalPatchUserWindow.SwingWorker, CompletionStatus > {
        
        
        private PatchUserWindow window;
        private Database< User > usersDatabase;
        
        
        public Factory( PatchUserWindow window, Database< User > usersDatabase ) {
        
            this.window = window;
            this.usersDatabase = usersDatabase;
        }
        
        
        @Override
        public PatchUserSwingWorker newInstance() {
        
            return new PatchUserSwingWorker( window, usersDatabase );
        }
        
    }
    
}
