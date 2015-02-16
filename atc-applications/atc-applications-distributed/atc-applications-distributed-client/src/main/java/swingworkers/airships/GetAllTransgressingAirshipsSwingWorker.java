package swingworkers.airships;


import javax.swing.JTextArea;
import swingworkers.AirshipsGetterSW;
import swingworkers.SwingWorkerFactory;
import utils.ClientRequest;
import com.google.gson.Gson;
import entities.SimpleAirship;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker;


/**
 * Class whose instances have the purpose of add functionality to a
 * {@link GetAllTransgressingAirshipsWindowSwingWorker}.
 *
 * Extends {@link AirshipsGetterSW}.
 *
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllTransgressingAirshipsSwingWorker extends AirshipsGetterSW {
    
    
    // INSTANCE FIELD
    private static JTextArea errorTextArea;
    
    // CONSTRUCTOR
    public GetAllTransgressingAirshipsSwingWorker() {
    
        super( errorTextArea );
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM
    // FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method with the
     * purpose purpose of create a request with the info needed to be executed.  
     * 
     * @return Returns a {@link Iterable} of {@link SimpleAirship}s.
     */
    @Override
    protected Iterable< SimpleAirship > doInBackground() throws Exception {
    
        ClientRequest request = new ClientRequest( "GET", "/airships/reports" ) {
            
            @Override
            public void createParameters() {
            
            }
            
        };
        
        if( request.createConnection() )
            
            new Gson().fromJson( request.getResponse(), Iterable.class );
        
        throw new Exception( request.getResponse() );
    }
    
    // INNER CLASS
    /**
     * Inner class responsible for produce a new instance of
     * {@link GetAllTransgressingAirshipsSwingWorker}
     * 
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    
    public static class Factory implements
            SwingWorkerFactory< AirshipsGetterSW, Iterable< SimpleAirship > > {
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link GetAllTransgressingAirshipsSwingWorker}
         * 
         * @return Returns a {@link GetAllTransgressingAirshipsSwingWorker}
         */
        @Override
        public AirshipsGetterSW newInstance() {
        
            return new GetAllTransgressingAirshipsSwingWorker();
            
        }
        
        
        
    }
    
    
}
