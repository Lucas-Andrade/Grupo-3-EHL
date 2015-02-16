package swingworkers;


import org.eclipse.jetty.server.Authentication.User;
import utils.ClientRequest;
import utils.GetClientRequest;
import utils.StringUtils;
import com.google.gson.Gson;
import design.windows.userwindows.LogInWindow;
import entities.SimpleUser;
import exceptions.MissingRequiredParameterException;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow.SwingWorker;
import gson_entities.UserFromJson;


public class LoginWindowSW extends FunctionalLoginWindow.SwingWorker {
    
    // CONSTRUCTOR
    public LoginWindowSW( LogInWindow window ) {
    
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
    
        ClientRequest request = new GetClientRequest( "users/authenticate" ) {
            
            @Override
            public void createParameters() throws MissingRequiredParameterException {
            
                addAuthenticateParameters( StringUtils.parameterToString( usernameLabel, username ),
                                           StringUtils.parameterToString( passwordLabel, password ) );
            }
        };
        
        return new Gson().fromJson( request.getResponse(), UserFromJson.class ).convert();
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
        public LoginWindowSW newInstance() {
        
            return new LoginWindowSW( window );
        }
    }
}
