package swingworkers.airships;


import swingworkers.AirshipsGetterSW;
import swingworkers.SwingWorkerFactory;
import utils.ClientGETRequest;
import entities.SimpleAirship;
import exceptions.MissingRequiredParameterException;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker;
import functionalcomponents.functionalmainwindow.FunctionalFooterPanel;


/**
 * Class whose instances have the purpose of add functionality to a
 * {@link GetAllTransgressingAirshipsWindowSwingWorker}.
 *
 * Extends {@link AirshipsGetterSW}.
 *
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetTransgressingAirshipsSW extends FunctionalFooterPanel.GetTrangressingAirshipsSW {
    
    
    // CONSTRUCTOR
    public GetTransgressingAirshipsSW() {
    
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
    
        ClientGETRequest request = new ClientGETRequest( "airships/reports" ) {
            
            @Override
            public void createParameters() throws MissingRequiredParameterException {
            
            }
        };
        
        
        return request.getSimpleAirshipListFromJson();
    }
    
    // INNER CLASS
    /**
     * Inner class responsible for produce a new instance of {@link GetTransgressingAirshipsSW}
     * 
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    
    public static class Factory
            implements
            SwingWorkerFactory< FunctionalFooterPanel.GetTrangressingAirshipsSW, Iterable< SimpleAirship > > {
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link GetTransgressingAirshipsSW}
         * 
         * @return Returns a {@link GetTransgressingAirshipsSW}
         */
        @Override
        public FunctionalFooterPanel.GetTrangressingAirshipsSW newInstance() {
        
            return new GetTransgressingAirshipsSW();
            
        }
        
        
        
    }
    
    
}
