package swingworkers.airships;


import swingworkers.SwingWorkerFactory;
import utils.ClientRequest;
import com.google.gson.Gson;
import design.windows.airshipwindows.GetGeographicalCoordinatesParametersWindow;
import entities.SimpleAirship;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker;


/**
 * Class whose instances have the purpose of add functionality to a
 * {@link GetGeographicalCoordinatesParametersSwingWorker}.
 *
 * Extends {@link FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker}.
 *
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

public class GetGeographicalCoordinatesParametersSwingWorker extends
        FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker {
    
    
    // CONSTRUCTOR
    public GetGeographicalCoordinatesParametersSwingWorker( GetGeographicalCoordinatesParametersWindow window ) {
    
        super( window );
        
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM 
    //FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground()} method with the purpose of
     * create a request with the info needed to be executed.  
     * 
     * @return Returns a {@link Iteable} of {@link SimpleAirship}s.
     */
    
    @Override
    protected Iterable< SimpleAirship > doInBackground() throws Exception {
    
        
        ClientRequest request = new ClientRequest( "GET", "/airships/find" ) {
            
            @Override
            public void createParameters() {            
                
                newParameter( latitudeLabel, latitude );
                newParameter( longitudeLabel, longitude );
                newParameter( airshipsNumberLabel, airshipsNumber );
                
            }
            
        };
        
        if( request.createConnection() )
            
            new Gson().fromJson( request.getResponse(), Iterable.class );
        
        throw new Exception( request.getResponse() );
    }
    
    
    // INNER CLASS
    /**
     * Inner class responsible for produce a new instance of
     * {@link GetGeographicalCoordinatesParametersSwingWorker}
     * 
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public static class Factory
            implements
            SwingWorkerFactory< FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker, Iterable< SimpleAirship > > {
        
        // INSTANCE FIELDS
        
        private GetGeographicalCoordinatesParametersWindow window;
        
        
        // CONSTRUCTOR
        public Factory( GetGeographicalCoordinatesParametersWindow window ) {
        
            this.window = window;
            
            
        }
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link GetGeographicalCoordinatesParametersSwingWorker}
         * 
         * @return Returns a {@link GetGeographicalCoordinatesParametersSwingWorker}
         */
        
        @Override
        public SwingWorker newInstance() {
        
            return new GetGeographicalCoordinatesParametersSwingWorker( window );
        }
        
    }
    
    
    
}
