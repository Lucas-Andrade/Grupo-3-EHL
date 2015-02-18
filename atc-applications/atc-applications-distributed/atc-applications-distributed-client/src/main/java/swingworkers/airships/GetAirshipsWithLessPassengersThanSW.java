package swingworkers.airships;



import swingworkers.SwingWorkerFactory;
import utils.ClientGETRequest;
import utils.StringCommandsDictionary;
import utils.StringUtils;
import design.windows.airshipwindows.GetAirshipsWithLessPassengerThanWindow;
import entities.SimpleAirship;
import exceptions.MissingRequiredParameterException;
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

public class GetAirshipsWithLessPassengersThanSW extends
        FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker {
    
    // CONSTRUCTOR
    public GetAirshipsWithLessPassengersThanSW( GetAirshipsWithLessPassengerThanWindow window ) {
    
        super( window );
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM
    // FunctionalGetAirshipsWithLessPassengersThanCommand.SwingWorker
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground()} method with the purpose of create
     * a request with the info needed to be executed.
     * 
     * 
     * @return Returns a {@link Iterable} of {@link SimpleAirship}s.
     */
    @Override
    protected Iterable< SimpleAirship > doInBackground() throws Exception {
    
        ClientGETRequest request =
                new ClientGETRequest( "airships/nbPassengers/" + passengersNumber + "/bellow" ) {
                    
                    @Override
                    public void createParameters() throws MissingRequiredParameterException {
                    
                        addNewParameter( StringCommandsDictionary.NUMBEROFPASSENGERS,
                                         StringUtils.parameterToString( passengersNumberLabel,
                                                                        passengersNumber ) );
                        
                    }
                };
        
        return request.getSimpleAirshipListFromJson();
    }
    
    
    
    // INNER CLASS
    /**
     * Inner class responsible for produce a new instance of
     * {@link GetAirshipsWithLessPassengersThanSW}
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
         * create a {@link GetAirshipsWithLessPassengersThanSW}
         * 
         * @return Returns a {@link GetAirshipsWithLessPassengersThanSW}
         */
        
        @Override
        public SwingWorker newInstance() {
        
            return new GetAirshipsWithLessPassengersThanSW( window );
        }
    }
    
}
