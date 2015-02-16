package swingworkers.airships;

import java.util.ArrayList;
import java.util.Collection;
import swingworkers.SwingWorkerFactory;
import app.EntitiesConversor;
import commands.getcommands.GetAllElementsInADatabaseCommand;
import databases.Database;
import elements.Airship;
import entities.SimpleAirship;
import functionalcomponents.functionalmainwindow.BodyPanelFunctionalizer;

/**TODO -documentation
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllAirshipsSW extends BodyPanelFunctionalizer.SwingWorker {

    private Database< Airship > airshipDatabase;

    
    public GetAllAirshipsSW(Database< Airship > airshipDatabase ) {
        
        this.airshipDatabase = airshipDatabase;
    }

    @Override
    protected Iterable< SimpleAirship > doInBackground() throws Exception {
    
        Collection< SimpleAirship > simpleAirshipCollection = new ArrayList<>();

        
        Iterable< Airship > airshipIterable =
                new GetAllElementsInADatabaseCommand<Airship>( airshipDatabase ).call().get();
        
        for( Airship airship : airshipIterable ) {
            
            simpleAirshipCollection.add( new EntitiesConversor().toSimpleAirship( airship ) );
            
        }
        
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
    
    public static class Factory implements
            SwingWorkerFactory< BodyPanelFunctionalizer.SwingWorker, Iterable< SimpleAirship > > {
        
        // INSTANCE FIELDS
        private Database< Airship > airshipDatabase;
        
        // CONSTRUCTOR
        
        public Factory( Database< Airship > airshipDatabase  ) {

            this.airshipDatabase = airshipDatabase;
            
        }
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link GetTransgressingAirshipsWindowSW}
         * 
         * @return Returns a {@link GetTransgressingAirshipsWindowSW}
         */
        @Override
        public GetAllAirshipsSW newInstance() {
        
            return new GetAllAirshipsSW( airshipDatabase );
            
        }
        
    }
    
}
