package swingworkers.airships;


import java.util.ArrayList;
import java.util.Collection;
import swingworkers.SwingWorkerFactory;
import app.EntitiesConversor;
import commands.getcommands.GetAllTransgressingAirshipsCommand;
import databases.Database;
import elements.Airship;
import entities.SimpleAirship;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker;
import functionalcomponents.functionalmainwindow.FunctionalFooterPanel;


/**
 * Class whose instances have the purpose of add functionality to a
 * {@link GetTransgressingAirshipsWindowSW}.
 *
 * Extends {@link FunctionalGetGeographicalCoordinatesParametersWindow}.
 *
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetTransgressingAirshipsWindowSW extends
        FunctionalFooterPanel.GetTrangressingAirshipsSW {
    
    // INSTANCE FIELD
    private Database< Airship > airshipDatabase;
    
    
    // CONSTRUCTOR
    
    public GetTransgressingAirshipsWindowSW( Database< Airship > airshipDatabase ) {
    
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
        
        Collection< SimpleAirship > simpleAirshipCollection = new ArrayList<>();
        for( Airship airship : airshipIterable )
            simpleAirshipCollection.add( new EntitiesConversor().toSimpleAirship( airship ) );
        
        return simpleAirshipCollection;
        
    }
    
    
    // INNER CLASS
    /**
     * Inner class responsible for produce a new instance of
     * {@link GetTransgressingAirshipsWindowSW}
     * 
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    
    public static class Factory
            implements
            SwingWorkerFactory< FunctionalFooterPanel.GetTrangressingAirshipsSW, Iterable< SimpleAirship > > {
        
        // INSTANCE FIELDS
        private Database< Airship > airshipDatabase;
        
        // CONSTRUCTOR
        
        public Factory( Database< Airship > airshipDatabase ) {
        
            this.airshipDatabase = airshipDatabase;
            
        }
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link GetTransgressingAirshipsWindowSW}
         * 
         * @return Returns a {@link GetTransgressingAirshipsWindowSW}
         */
        @Override
        public FunctionalFooterPanel.GetTrangressingAirshipsSW newInstance() {
        
            return new GetTransgressingAirshipsWindowSW( airshipDatabase );
        }
        
    }
    
}
