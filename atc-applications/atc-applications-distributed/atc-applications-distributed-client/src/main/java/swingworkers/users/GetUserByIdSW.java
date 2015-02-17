package swingworkers.users;


import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import org.eclipse.jetty.server.Authentication.User;
import swingworkers.SwingWorkerForButtonFactory;
import utils.ClientRequest;
import utils.ClientGETRequest;
import com.google.gson.Gson;
import entities.SimpleUser;
import exceptions.InvalidArgumentException;
import functionalcomponents.infobuttons.EntitiesInfoButton;
import functionalcomponents.infobuttons.SimpleUserInfoButton;
import gson_entities.UserFromJson;


/**
 * Instances of this class are {@link SwingWorker}s associated to {@link SimpleUserInfoButton}, that
 * will GET, in the {@link SwingWorker#doInBackground() doInBackground()} method, the associated
 * {@link SimpleUser} info by its {@code identification}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetUserByIdSW extends EntitiesInfoButton.EntitiesInfoSwingWorker< SimpleUser > {
    
    private final String identification;
    
    
    /**
     * Create a {@link SwingWorker} associated to a {@code SimpleUser} {@code identification}.
     * 
     * 
     * @param identification
     *            - The {@link SimpleUser} identification.
     * @param textArea
     *            - The {@link JTextArea} where to display the result.
     * @param database
     *            - The {@link User} {@link Database}.
     */
    public GetUserByIdSW( String identification, JTextArea textArea ) {
    
        super( textArea );
        
        this.identification = identification;
    }
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method with the
     * purpose of execute the {@link GetElementFromADatabaseByIdCommand}, obtaining its result (
     * {@code User}), and convert it to {@code SimpleUser}.
     * 
     * @return Returns an {@link SimpleUser} from the {@code database} that matches the given
     *         {@code identification}.
     * 
     * @throws InvalidArgumentException
     *             If either database or identification are null.
     */
    @Override
    protected SimpleUser doInBackground() throws Exception {
    
        ClientRequest request = new ClientGETRequest( "users/" + identification ) {
            
            @Override
            public void createParameters() {} };
        
        return new Gson().fromJson( request.getResponse(), UserFromJson.class ).convert();
    }
    
    
    /**
     * Instances of the class are {@link SwingWorker} {@code factories}, that creates instances of
     * {@link GetUserByIdSW} that will be run in {@link SimpleUserInfoButton}.
     *
     * @param <SwingWorker>
     *            The type of the {@link SwingWorker} returned in the method {@link #newInstance()}.
     * @param <SimpleUser>
     *            The type of the results returned by the methods
     *            {@link SwingWorker#doInBackground()} and {@link SwingWorker#get()}.
     */
    public static class Factory implements
            SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > {
        
        
        /**
         * Creates a {@link SwingWorker} {@code factories}, that creates instances of
         * {@link GetUserByIdSW} using the {@link Factory#newInstance} that will be run in
         * {@link SimpleUserInfoButton}.
         * 
         * @param database
         *            - The {@code User} {@link Database}.
         */
        public Factory(  ) {
        
        }
        
        /**
         * Produces a new instance of {@link GetUserByIdSW}.
         * 
         * @see swingworkers.SwingWorkerForButtonFactory#newInstance(String, JTextArea)
         */
        @Override
        public SwingWorker< SimpleUser, Void > newInstance( String identification,
                                                            JTextArea textArea ) {
        
            return new GetUserByIdSW( identification, textArea );
        }
    }
}
