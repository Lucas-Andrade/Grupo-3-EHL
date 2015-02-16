package swingworkers.airships;



import java.util.ArrayList;
import java.util.Collection;
import swingworkers.SwingWorkerFactory;
import utils.ClientRequest;
import utils.GetClientRequest;
import utils.StringCommandsDictionary;
import utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import design.windows.airshipwindows.GetAirshipsWithLessPassengerThanWindow;
import entities.SimpleAirship;
import exceptions.MissingRequiredParameterException;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsWithLessPassengerThanWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker;
import gson_entities.AirshipFromJson;


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
    
        ClientRequest request =
                new GetClientRequest( "airships/nbPassengers/" + passengersNumber + "/bellow" ) {
                    
                    @Override
                    public void createParameters() throws MissingRequiredParameterException {
                    
                        addNewParameter( StringCommandsDictionary.NUMBEROFPASSENGERS,
                                         StringUtils.parameterToString( passengersNumberLabel,
                                                                        passengersNumber ) );
                        
                    }
                };
        
        
        
        Iterable< AirshipFromJson > airshipsFromJson =
                new Gson().fromJson( request.getResponse(),
                                     new TypeToken< ArrayList< AirshipFromJson >>() {}.getType() );
        
        
        
        Collection< SimpleAirship > simpleAirships = new ArrayList<>();
        
        for( AirshipFromJson airship : airshipsFromJson )
            simpleAirships.add( airship.convert() );
        
        return simpleAirships;
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
