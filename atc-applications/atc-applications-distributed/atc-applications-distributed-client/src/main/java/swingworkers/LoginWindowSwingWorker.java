package swingworkers;


import org.eclipse.jetty.server.Authentication.User;
import com.google.gson.Gson;
import design.windows.userwindows.LogInWindow;
import entities.SimpleUser;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow.SwingWorker;
import gson_entities.UserFromJson;


public class LoginWindowSwingWorker extends FunctionalLoginWindow.SwingWorker {
    
    // CONSTRUCTOR
    public LoginWindowSwingWorker( LogInWindow window ) {
    
        super( window );
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
    
        ClientRequest request = new ClientRequest( "GET", "users/authenticate" ) {
            
            @Override
            public void createParameters() {
            
                addAuthenticateParameters( username, password );
            }
        };

        if( request.createConnection() )
            return new Gson().fromJson( request.getResponse(), UserFromJson.class ).convert();
        throw new Exception( request.getResponse() );
    }
    
    
    // INNER CLASS
    public static class Factory implements
            SwingWorkerFactory< FunctionalLoginWindow.SwingWorker, SimpleUser > {
        
        
        
        // INSTANCE FIELDS
        private LogInWindow window;
        
        
        
        // CONSTRUCTOR
        public Factory( LogInWindow window ) {
        
            this.window = window;
        }
        
        
        @Override
        public LoginWindowSwingWorker newInstance() {
        
            return new LoginWindowSwingWorker( window );
        }
    }
}
