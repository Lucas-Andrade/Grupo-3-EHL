package swingworkers.airships;



import swingworkers.SwingWorkerFactory;
import utils.ClientRequest;
import com.google.gson.Gson;
import design.windows.airshipwindows.GetAirshipsWithLessPassengerThanWindow;
import entities.SimpleAirship;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsWithLessPassengerThanWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker;


/**
 * Class whose instances have the purpose of add functionality to a
 * {@link GetAirshipsWithLessPassengersThanWindowSwingWorker}.
 *
 * Extends {@link FunctionalGetAirshipsWithLessPassengersThanWindow.SwingWorker}.
 *
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

public class GetAirshipsWithLessPassengersThanSwingWorker extends
        FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker {
    
    // CONSTRUCTOR
    public GetAirshipsWithLessPassengersThanSwingWorker( GetAirshipsWithLessPassengerThanWindow window ) {
    
        super( window );
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalGetAirshipsWithLessPassengersThanCommand.SwingWorker
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground()} method with the
     * purpose of create a request with the info needed to be executed.  
     * 
     * 
     * @return Returns a {@link Iterable} of {@link SimpleAirship}s.
     */
    @Override
    protected Iterable< SimpleAirship > doInBackground() throws Exception {
    
        ClientRequest request = new ClientRequest( "GET", "/airships/nbPassengers/"+passengersNumber+"/bellow" ) {
            
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
     * {@link GetAirshipsWithLessPassengersThanSwingWorker}
     * 
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public static class Factory
            implements
            SwingWorkerFactory< FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker, Iterable< SimpleAirship > > {
        
        // INSTANCE FIELDS
        
        private GetAirshipsWithLessPassengerThanWindow window;
        
        
        // CONSTRUCTOR
        public Factory( GetAirshipsWithLessPassengerThanWindow window ) {
        
            this.window = window;
            
            
        }
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link GetAirshipsWithLessPassengersThanSwingWorker}
         * 
         * @return Returns a {@link GetAirshipsWithLessPassengersThanSwingWorker}
         */
        
        @Override
        public SwingWorker newInstance() {
        
            return new GetAirshipsWithLessPassengersThanSwingWorker( window );
        }
    }
    
}
