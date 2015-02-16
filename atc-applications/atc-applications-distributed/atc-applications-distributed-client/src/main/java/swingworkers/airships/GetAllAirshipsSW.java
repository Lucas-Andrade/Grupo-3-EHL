package swingworkers.airships;


import java.util.ArrayList;
import java.util.Collection;
import swingworkers.SwingWorkerFactory;
import utils.ClientRequest;
import utils.GetClientRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.SimpleAirship;
import exceptions.MissingRequiredParameterException;
import functionalcomponents.functionalmainwindow.BodyPanelFunctionalizer;
import gson_entities.AirshipFromJson;


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
    
        ClientRequest request = new GetClientRequest( "airships/" ) {
            
            @Override
            public void createParameters() throws MissingRequiredParameterException {
            
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
     * Inner class responsible for produce a new instance of {@link GetTransgressingAirshipsSW}
     * 
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    
    public static class Factory implements
            SwingWorkerFactory< BodyPanelFunctionalizer.SwingWorker, Iterable< SimpleAirship > > {
        
        // INSTANCE FIELDS
        
        // CONSTRUCTOR
        
        public Factory() {
        
            
        }
        
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
