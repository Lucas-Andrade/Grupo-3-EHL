package swingworkers;


import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import utils.ClientRequest;
import com.google.gson.Gson;
import entities.SimpleAirship;
import exceptions.InvalidArgumentException;
import functionalcomponents.infobuttons.EntitiesInfoButton;
import functionalcomponents.infobuttons.SimpleAirshipInfoButton;
import gson_entities.AirshipFromJson;


/**
 * TODO Instances of this class are {@link SwingWorker}s associated to
 * {@link SimpleAirshipInfoButton}, that will GET, in the {@link SwingWorker#doInBackground()
 * doInBackground()} method, the associated {@link SimpleAirship} info by its {@code identification}
 * .
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class SimpleAirshipInfoSW extends EntitiesInfoButton.EntitiesInfoSwingWorker< SimpleAirship > {
    
    private String identification;
    
    /**
     * Create a {@link SwingWorker} associated to a {@code SimpleAirship} {@code identification}.
     * 
     * 
     * @param identification
     *            - The {@link SimpleAirship} identification.
     * @param textArea
     *            - The {@link JTextArea} where to display the result.
     * @param database
     *            - The {@link Airship} {@link Database}.
     */
    public SimpleAirshipInfoSW( String identification, JTextArea textArea ) {
    
        super( textArea );
        
        this.identification = identification;
    }
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method with the
     * purpose of execute the {@link GetElementFromADatabaseByIdCommand}, obtaining its result (
     * {@code Airship}), and convert it to {@code SimpleAirship}.
     * 
     * @return Returns an {@link SimpleAirship} from the {@code database} that matches the given
     *         {@code identification}.
     * 
     * @throws InvalidArgumentException
     *             If either database or identification are null.
     */
    @Override
    protected SimpleAirship doInBackground() throws Exception {
    
        ClientRequest request = new ClientRequest( "GET", "users/" + identification ) {
            
            @Override
            public void createParameters() {}
        };
        
        
        if( request.createConnection() )
            return new Gson().fromJson( request.getResponse(), AirshipFromJson.class ).convert();
        throw new Exception( request.getResponse() );
    }
    
    
    /**
     * Instances of the class are {@link SwingWorker} {@code factories}, that creates instances of
     * {@link GUISimpleAirshipInfoSW} that will be run in {@link SimpleAirshipInfoButton}.
     *
     * @param <SwingWorker>
     *            The type of the {@link SwingWorker} returned in the method {@link #newInstance()}.
     * @param <SimpleAirship>
     *            The type of the results returned by the methods
     *            {@link SwingWorker#doInBackground()} and {@link SwingWorker#get()}.
     */
    public class SwFactory implements
            SwingWorkerForButtonFactory< SwingWorker< SimpleAirship, Void >, SimpleAirship > {
        
        
        /**
         * TODO Creates a {@link SwingWorker} {@code factories}, that creates instances of
         * {@link GUISimpleAirshipInfoSW} using the {@link SwFactory#newInstance} that will be run
         * in {@link SimpleAirshipInfoButton}.
         * 
         * @param database
         *            - The {@code User} {@link Database}.
         */
        public SwFactory() {
        
        }
        
        /**
         * Produces a new instance of {@link GUISimpleAirshipInfoSW}.
         * 
         * @see swingworkers.SwingWorkerForButtonFactory#newInstance(String, JTextArea)
         */
        @Override
        public SwingWorker< SimpleAirship, Void > newInstance( String identification,
                                                               JTextArea textArea ) {
        
            return new SimpleAirshipInfoSW( identification, textArea );
        }
    }
}
