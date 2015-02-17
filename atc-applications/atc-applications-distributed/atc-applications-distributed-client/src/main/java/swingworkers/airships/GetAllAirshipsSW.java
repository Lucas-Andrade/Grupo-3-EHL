package swingworkers.airships;


import swingworkers.SwingWorkerFactory;
import utils.ClientGETRequest;
import entities.SimpleAirship;
import exceptions.MissingRequiredParameterException;
import functionalcomponents.functionalmainwindow.BodyPanelFunctionalizer;


/**
 * TODO -documentation
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllAirshipsSW extends BodyPanelFunctionalizer.SwingWorker {
    
    
    
    @Override
    protected Iterable< SimpleAirship > doInBackground() throws Exception {
    
        ClientGETRequest request = new ClientGETRequest( "airships/" ) {
            
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
    
    public static class Factory implements
            SwingWorkerFactory< BodyPanelFunctionalizer.SwingWorker, Iterable< SimpleAirship > > {
        
        // INSTANCE FIELDS
       
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link GetTransgressingAirshipsSW}
         * 
         * @return Returns a {@link GetTransgressingAirshipsSW}
         */
        @Override
        public GetAllAirshipsSW newInstance() {
        
            return new GetAllAirshipsSW();
            
        }
        
    }
    
}
