package swingworkers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.eclipse.jetty.server.Authentication.User;
import com.google.gson.Gson;
import design.windows.userwindows.LogInWindow;
import entities.SimpleUser;
import functionalcomponents.SwingWorkerFactory;
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
    
        String url = "http://localhost:9999/"; //TODO
        HttpURLConnection connection = ( HttpURLConnection )new URL( url ).openConnection();

        // 200 -> ok
        connection.getResponseCode();

        // message from the server
        BufferedReader in = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
        String inputLine;
        String html = new String();

        //TODO
        while( ( inputLine = in.readLine() ) != null )
        {
            html += inputLine ;
        }
        in.close();
        
        return  new Gson().fromJson( html, UserFromJson.class ).convert();
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
