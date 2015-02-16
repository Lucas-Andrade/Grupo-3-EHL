package swingworkers.airships;


import java.util.ArrayList;
import java.util.Collection;
import commands.getcommands.GetAirshipsWithLessPassengersThanCommand;
import swingworkers.SwingWorkerFactory;
import utils.EntitiesConversor;
import utils.StringUtils;
import databases.Database;
import design.windows.airshipwindows.GetAirshipsWithLessPassengerThanWindow;
import elements.Airship;
import entities.SimpleAirship;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsWithLessPassengerThanWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker;



/**
 * Class whose instances have the purpose of add functionality to a
 * {@link GetAirshipsWithLessPassengersThanSW}.
 *
 * Extends {@link FunctionalGetAirshipsWithLessPassengersThanWindow}.
 *
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

public class GetAirshipsWithLessPassengersThanSW extends
        FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker {
    
    private Database< Airship > airshipsDatabase;
    private Collection< SimpleAirship > simpleAirshipsWithLessPassengers = new ArrayList<>();
    
    public GetAirshipsWithLessPassengersThanSW( GetAirshipsWithLessPassengerThanWindow window,
                                                      Database< Airship > airshipsDatabase ) {
    
        super( window );
        this.airshipsDatabase = airshipsDatabase;
        
    }
    
    
    // IMPLEMENTATION OF METHODS INHERITED FROM
    // FunctionalGetAirshipsWithLessPassengersThanCommand.SwingWorker
    /**
     * Implementation of the {@link SwingWorker#doInBackground()} method with the purpose of
     * executing a {@link GetAirshipsWithLessPassengersThanCommand} obtaining its result and convert
     * it to {@linkplain SimpleAirship}.
     * 
     * @return Returns a {@link Iterable} of {@link SimpleAirship}s.
     */
    @Override
    protected Iterable< SimpleAirship > doInBackground() throws Exception {
    
        Iterable< Airship > airshipsNearest =
                new GetAirshipsWithLessPassengersThanCommand(
                                                              airshipsDatabase,
                                                              StringUtils.parameterToInteger( passengersNumberLabel,
                                                                                              passengersNumber ) ).call()
                                                                                                                  .get();
        
        for( Airship airship : airshipsNearest )
            
            simpleAirshipsWithLessPassengers.add( new EntitiesConversor().toSimpleAirship( airship ) );
        
        return simpleAirshipsWithLessPassengers;
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
        private Database< Airship > airshipDatabase;
        private GetAirshipsWithLessPassengerThanWindow window;
        
        
        // CONSTRUCTOR
        public Factory( GetAirshipsWithLessPassengerThanWindow window,
                        Database< Airship > airshipDatabase ) {
        
            this.window = window;
            this.airshipDatabase = airshipDatabase;
            
        }
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link GetAirshipsWithLessPassengersThanSW}
         * 
         * @return Returns a {@link GetAirshipsWithLessPassengersThanSW}
         */
        
        @Override
        public SwingWorker newInstance() {
        
            return new GetAirshipsWithLessPassengersThanSW( window, airshipDatabase );
        }
        
    }
    
}
