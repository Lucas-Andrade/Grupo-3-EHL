package swingworkers;


import utils.StringUtils;
import app.EntitiesConversor;
import commands.AuthenticateUserCommand;
import databases.Database;
import design.windows.userwindows.LogInWindow;
import elements.User;
import entities.SimpleUser;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow.SwingWorker;


public class LoginWindowSwingWorker extends FunctionalLoginWindow.SwingWorker {
    
    
    
    // INSTANCE FIELD
    private Database< User > usersDatabase;
    
    
    
    // CONSTRUCTOR
    public LoginWindowSwingWorker( LogInWindow window, Database< User > usersDatabase ) {
    
        super( window );
        this.usersDatabase = usersDatabase;
    }
    
    
    
    // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalLoginWindow.SwingWorker
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method with the
     * purpose of executing a {@link AuthenticateUserCommand} and obtaining its result.
     * 
     * @return Returns a {@link User}
     */
    @Override
    protected SimpleUser doInBackground() throws Exception {
    
        User loggedUser =
                new AuthenticateUserCommand( StringUtils.parameterToString( usernameLabel, username ),
                                             StringUtils.parameterToString( passwordLabel, password ),
                                             usersDatabase ).call().get();
        return new EntitiesConversor().toSimpleUser( loggedUser );
    }
    
    
    
    // INNER CLASS
    public static class Factory implements
            SwingWorkerFactory< FunctionalLoginWindow.SwingWorker, SimpleUser > {
        
        
        
        // INSTANCE FIELDS
        private LogInWindow window;
        private Database< User > usersDatabase;
        
        
        
        // CONSTRUCTOR
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
