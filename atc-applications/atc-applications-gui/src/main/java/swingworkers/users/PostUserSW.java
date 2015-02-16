package swingworkers.users;


import swingworkers.SwingWorkerFactory;
import utils.CompletionStatus;
import utils.StringUtils;
import commands.getcommands.GetElementFromADatabaseByIdCommand;
import commands.postcommands.PostUserCommand;
import databases.Database;
import design.windows.userwindows.PostUserWindow;
import elements.User;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import functionalcomponents.functionaluserwindows.FunctionalPostUserWindow;


/**
 * Concrete implementation of {@link FunctionalPostUserWindow.SwingWorker}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostUserSW extends FunctionalPostUserWindow.SwingWorker {
    
    
    private Database< User > usersDatabase;
    
    public PostUserSW( PostUserWindow window, Database< User > usersDatabase ) {
    
        super( window );
        this.usersDatabase = usersDatabase;
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalPostUserWindow.SwingWorker
    /**
     * Creates and stores a new user with the username {@link #username}, the password
     * {@link #password}, the email {@link #email} and, if given, the full name {@link #fullName}.
     * It is expected to return a {@link CompletionStatus} representing the success/failure of the
     * operation or an exception if the operation couldn't perform.
     * 
     * @return Returns a {@link CompletionStatus} representing the success/failure of the operation.
     * 
     * @throws Exception
     *             If (1) the {@link #password} and the {@link #confirmPassword} don't match or (2)
     *             the operation couldn't perform.
     */
    @Override
    protected CompletionStatus doInBackground() throws Exception {
    
        if( !password.equals( confirmPassword ) )
            throw new InvalidArgumentException( "Passwords don't match!" );
        
        User loggedInUser;
        try {
            loggedInUser =
                    new GetElementFromADatabaseByIdCommand< User >(
                                                                    usersDatabase,
                                                                    simpleLoggedUser.getIdentification() ).call()
                                                                                                          .get();
        }
        catch( Exception e ) {
            throw new InternalErrorException(
                                              "UNEXPECTED ERROR WITH LOGGED-IN USER IN PostUserSW!",
                                              e );
        }
        
        
        return new PostUserCommand( StringUtils.parameterToString( usernameLabel, username ),
                                    StringUtils.parameterToString( passwordLabel, password ),
                                    StringUtils.parameterToString( emailLabel, email ), fullName,
                                    usersDatabase, loggedInUser ).call();
        // the fullname is optional, it might be null or empty
        
    }
    
    // INNER CLASS
    /**
     * Class whose instances are responsible for producing {@link PostUserSW}s.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public static class Factory implements
            SwingWorkerFactory< FunctionalPostUserWindow.SwingWorker, CompletionStatus > {
        
        
        private PostUserWindow window;
        private Database< User > usersDatabase;
        
        
        public Factory( PostUserWindow window, Database< User > usersDatabase ) {
        
            this.window = window;
            this.usersDatabase = usersDatabase;
        }
        
        /**
         * @see swingworkers.SwingWorkerFactory#newInstance()
         */
        @Override
        public PostUserSW newInstance() {
        
            return new PostUserSW( window, usersDatabase );
        }
        
    }
    
}
