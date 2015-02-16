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
import design.windows.airshipwindows.GetGeographicalCoordinatesParametersWindow;
import entities.SimpleAirship;
import exceptions.MissingRequiredParameterException;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsCloserToWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker;
import gson_entities.AirshipFromJson;


/**
 * Class whose instances have the purpose of add functionality to a {@link GetAirshipsCloserToSW}.
 *
 * Extends {@link FunctionalGetAirshipsCloserToWindow.SwingWorker}.
 *
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

public class GetAirshipsCloserToSW extends FunctionalGetAirshipsCloserToWindow.SwingWorker {
    
    
    
    // CONSTRUCTOR
    public GetAirshipsCloserToSW( GetGeographicalCoordinatesParametersWindow window ) {
    
        super( window );
        
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM
    // FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground()} method with the purpose of create
     * a request with the info needed to be executed.
     * 
     * @return Returns a {@link Iteable} of {@link SimpleAirship}s.
     */
    
    @Override
    protected Iterable< SimpleAirship > doInBackground() throws Exception {
    
        
        ClientRequest request = new GetClientRequest( "airships/find" ) {
            
            @Override
            public void createParameters() throws MissingRequiredParameterException {
            
                addNewParameter( StringCommandsDictionary.LATITUDE,
                                 StringUtils.parameterToString( latitudeLabel, latitude ) );
                addNewParameter( StringCommandsDictionary.LONGITUDE,
                                 StringUtils.parameterToString( longitudeLabel, longitude ) );
                addNewParameter( StringCommandsDictionary.NUMBEROFAIRSHIPSTOFIND,
                                 StringUtils.parameterToString( airshipsNumberLabel, airshipsNumber ) );
                
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
     * Inner class responsible for produce a new instance of {@link GetAirshipsCloserToSW}
     * 
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public static class Factory
            implements
            SwingWorkerFactory< FunctionalGetAirshipsCloserToWindow.SwingWorker, Iterable< SimpleAirship >> {
        
        // INSTANCE FIELDS
        
        private GetGeographicalCoordinatesParametersWindow window;
        
        
        // CONSTRUCTOR
        public Factory( GetGeographicalCoordinatesParametersWindow window ) {
        
            this.window = window;
            
            
        }
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link GetAirshipsCloserToSW}
         * 
         * @return Returns a {@link GetAirshipsCloserToSW}
         */
        @Override
        public GetAirshipsCloserToSW newInstance() {
        
            return new GetAirshipsCloserToSW( window );
        }
        
    }
    
    
    
}
