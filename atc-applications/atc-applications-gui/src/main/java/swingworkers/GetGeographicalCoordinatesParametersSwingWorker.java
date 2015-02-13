package swingworkers;


import java.util.ArrayList;
import java.util.Collection;
import utils.StringUtils;
import app.EntitiesConversor;
import commands.getcommands.GetAirshipsCloserToCommand;
import databases.Database;
import design.windows.airshipwindows.GetGeographicalCoordinatesParametersWindow;
import elements.Airship;
import entities.SimpleAirship;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker;

    /**
     *   Class whose instances have the purpose of add functionality to a
     * {@link GetGeographicalCoordinatesParametersWindow}. 
     *
     * Extends {@link FunctionalGetGeographicalCoordinatesParametersWindow}.
     *
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */


public class GetGeographicalCoordinatesParametersSwingWorker extends
        FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker {
    
    // INSTANCE FIELD
    private Database< Airship > airshipsDatabase;
    private Collection< SimpleAirship > simpleAirshipsNearest = new ArrayList<>();
    
    // CONSTRUCTOR
    public GetGeographicalCoordinatesParametersSwingWorker( GetGeographicalCoordinatesParametersWindow window,
                                                            Database< Airship > airshipDatabase ) {
    
        super( window );
        this.airshipsDatabase = airshipDatabase;
        
    }
    
 // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground()} method with the
     * purpose of executing a {@link GetAirshipsCloserToCommand} obtaining its result 
     * and convert it to {@linkplain SimpleAirship}.
     * 
     * @return Returns a {@link Iteable} of {@link SimpleAirship}s.
     */
    
    @Override
    protected Iterable< SimpleAirship > doInBackground() throws Exception {
    
        
        Iterable< Airship > airshipsNearest =
                new GetAirshipsCloserToCommand(
                                                airshipsDatabase,
                                                StringUtils.parameterToInteger( airshipsNumberLabel,
                                                                                airshipsNumber ),
                                                StringUtils.parameterToDouble( latitudeLabel, latitude ),
                                                StringUtils.parameterToDouble( longitudeLabel,
                                                                               longitude ) ).call()
                                                                                            .get();
        
        
        for( Airship airship : airshipsNearest )
            
            simpleAirshipsNearest.add( new EntitiesConversor().toSimpleAirship( airship ) );
        
        return simpleAirshipsNearest;
    }
    
    
    // INNER CLASS
    /**
     * Inner class responsible for produce a new 
     * instance of {@link GetGeographicalCoordinatesParametersSwingWorker}
     * 
     *
     *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public static class Factory
            implements
            SwingWorkerFactory< FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker, Iterable< SimpleAirship > > {
    
        // INSTANCE FIELDS
        private Database< Airship > airshipDatabase;
        private GetGeographicalCoordinatesParametersWindow window;
     
        
        // CONSTRUCTOR
        public Factory( GetGeographicalCoordinatesParametersWindow window,
                        Database< Airship > airshipDatabase ) {
        
            this.window = window;
            this.airshipDatabase = airshipDatabase;
            
        }
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the
         * purpose of create a {@link GetGeographicalCoordinatesParametersSwingWorker} 
         * 
         * @return Returns a {@link GetGeographicalCoordinatesParametersSwingWorker}
         */
        
        @Override
        public SwingWorker newInstance() {
                    
            return new GetGeographicalCoordinatesParametersSwingWorker( window, airshipDatabase );
        }
            
    }
}
