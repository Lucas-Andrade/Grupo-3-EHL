package swingworkers;


import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JTextArea;
import app.EntitiesConversor;
import commands.getcommands.GetAllTransgressingAirshipsCommand;
import databases.Database;
import elements.Airship;
import entities.SimpleAirship;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker;


/**
 * Class whose instances have the purpose of add functionality to a
 * {@link GetAllTransgressingAirshipsWindowSwingWorker}.
 *
 * Extends {@link FunctionalGetGeographicalCoordinatesParametersWindow}.
 *
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllTransgressingAirshipsWindowSwingWorker extends AirshipsGetterSW {
    
    // INSTANCE FIELD
    private static JTextArea errorJtextArea;
    private Database< Airship > airshipDatabase;
    private Collection< SimpleAirship > simpleAirshipCollection = new ArrayList<>();
    
    
    // CONSTRUCTOR

    public GetAllTransgressingAirshipsWindowSwingWorker( Database< Airship > airshipDatabase ) {
    
        super( errorJtextArea );
        this.airshipDatabase = airshipDatabase;
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM
    // FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method with the
     * purpose of executing a execute a {@link GetAllTransgressingAirshipsCommand}, obtaining its
     * result and convert {@link Airship} to {@link SimpleAirship}.
     * 
     * @return Returns a {@link Iterable} of {@link SimpleAirship}s.
     */
    @Override
    protected Iterable< SimpleAirship > doInBackground() throws Exception {
    
        
        
        Iterable< Airship > airshipIterable =
                new GetAllTransgressingAirshipsCommand( airshipDatabase ).call().get();
        
        for( Airship airship : airshipIterable ) {
            
            simpleAirshipCollection.add( new EntitiesConversor().toSimpleAirship( airship ) );
            
        }
        
        return simpleAirshipCollection;
        
    }
    
    
    // INNER CLASS
    /**
     * Inner class responsible for produce a new instance of
     * {@link GetAllTransgressingAirshipsWindowSwingWorker}
     * 
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    
    public static class Factory implements
            SwingWorkerFactory< AirshipsGetterSW, Iterable< SimpleAirship > > {
        
        // INSTANCE FIELDS
        private Database< Airship > airshipDatabase;
        
        // CONSTRUCTOR
        
        public Factory( Database< Airship > airshipDatabase ) {
        
            this.airshipDatabase = airshipDatabase;
            
        }
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link GetAllTransgressingAirshipsWindowSwingWorker}
         * 
         * @return Returns a {@link GetAllTransgressingAirshipsWindowSwingWorker}
         */
        @Override
        public AirshipsGetterSW newInstance() {
        
            return new GetAllTransgressingAirshipsWindowSwingWorker( airshipDatabase );
            
        }
        
    }
    
}
