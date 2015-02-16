package swingworkers.users;


import java.util.ArrayList;
import java.util.Collection;
import swingworkers.SwingWorkerFactory;
import utils.ClientRequest;
import utils.GetClientRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.SimpleUser;
import functionalcomponents.functionalmainwindow.FunctionalHeaderPanel;
import gson_entities.UserFromJson;


/**
 * Concrete implementation of {@link FunctionalHeaderPanel.GetAllUsersSW}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllUsersSW extends FunctionalHeaderPanel.GetAllUsersSW {
    
    
    // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalLoginWindow.SwingWorker
    /**
     * Return an {@link Iterable} of {@link SimpleUser}s representing all the users registered in
     * the app.
     */
    @Override
    protected Iterable< SimpleUser > doInBackground() throws Exception {
    
        ClientRequest request = new GetClientRequest( "users/" ) {
            
            @Override
            public void createParameters() {
            
            }
        };
        
        
        
        Iterable< UserFromJson > jsonUsers =
                new Gson().fromJson( request.getResponse(),
                                     new TypeToken< ArrayList< UserFromJson >>() {}.getType() );
        
        
        Collection< SimpleUser > simpleUsers = new ArrayList<>();
        
        for( UserFromJson user : jsonUsers )
            simpleUsers.add( user.convert() );
        
        return simpleUsers;
    }
    
    
    
    // INNER CLASS
    public static class Factory implements
            SwingWorkerFactory< FunctionalHeaderPanel.GetAllUsersSW, Iterable< SimpleUser > > {
        
        @Override
        public GetAllUsersSW newInstance() {
        
            return new GetAllUsersSW();
        }
        
    }
    
}
