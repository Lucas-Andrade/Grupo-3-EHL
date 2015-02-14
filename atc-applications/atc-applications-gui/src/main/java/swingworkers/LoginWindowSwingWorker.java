package swingworkers;


import utils.StringUtils;
import app.EntitiesConversor;
import commands.AuthenticateUserCommand;
import databases.Database;
import design.windows.userwindows.LogInWindow;
import elements.User;
import entities.SimpleUser;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow;


/**
 * Concrete implementation of {@link FunctionalLoginWindow.SwingWorker}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class LoginWindowSwingWorker extends FunctionalLoginWindow.SwingWorker {
    
        
    private Database< User > usersDatabase;
    
    public LoginWindowSwingWorker( LogInWindow window, Database< User > usersDatabase ) {
    
        super( window );
        this.usersDatabase = usersDatabase;
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalLoginWindow.SwingWorker
    /**
     * Authenticates the user with username {@link #username} and password {@link #password} and
     * returns its representation as a {@link SimpleUser} if the password is correct or throws an
     * exception otherwise.
     * 
     * @return The representation as a {@link SimpleUser} of the authenticated user.
     * @throws Exception
     *             If the password is incorrect.
     */
    @Override
    protected SimpleUser doInBackground() throws Exception {
    
        User loggedUser =
                new AuthenticateUserCommand(
                                             StringUtils.parameterToString( usernameLabel, username ),
                                             StringUtils.parameterToString( passwordLabel, password ),
                                             usersDatabase ).call().get();
        return new EntitiesConversor().toSimpleUser( loggedUser );
    }
    
    
    
    // INNER CLASS
    public static class Factory implements
            SwingWorkerFactory< FunctionalLoginWindow.SwingWorker, SimpleUser > {
        
        
        private LogInWindow window;
        private Database< User > usersDatabase;
        
        
        public Factory( LogInWindow window, Database< User > usersDatabase ) {
        
            this.window = window;
            this.usersDatabase = usersDatabase;
        }
        
        
        @Override
        public LoginWindowSwingWorker newInstance() {
        
            return new LoginWindowSwingWorker( window, usersDatabase );
        }
        
    }
    
}
