package swingworkers;


import javax.swing.JTabbedPane;
import utils.CompletionStatus;
import utils.StringUtils;
import commands.postcommands.PostCivilAirshipCommand;
import commands.postcommands.PostMilitaryAirshipCommand;
import databases.Database;
import design.windows.airshipwindows.PostAirshipsWindow;
import elements.Airship;
import elements.User;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow.SwingWorker;


/**
 * Class whose instances have the purpose of add functionality to a {@link PostAirshipsWindow}.
 *
 * Extends {@link FunctionalPostAirshipWindow.SwingWorker}.
 *
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostAirshipSwingWorker extends FunctionalPostAirshipWindow.SwingWorker {
    
    
    // INSTANCE FIELD
    private Database< Airship > airshipsDatabase;
    private User user;
    
    
    // CONSTRUCTOR
    public PostAirshipSwingWorker( PostAirshipsWindow window, Database< Airship > airshipsDatabase,
                                   User user ) {
    
        super( window );
        
        this.airshipsDatabase = airshipsDatabase;
        this.user = user;
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalPostAirshipWindow.SwingWorker
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground()} method with the purpose of
     * executing a {@link PostCivilAirshipCommand} or {@link PostMilitaryAirshipCommand}, depending
     * of {@link JTabbedPane} activated. After this proceed to a creation of a specific
     * {@link Airship}.
     * 
     * @return Returns a {@link CompletionStatus} with all the operation information.
     */
    @Override
    protected CompletionStatus doInBackground() throws Exception {
    
        
        return (typeTypeAirshipTabbedPane == 0)
                                               ? new PostCivilAirshipCommand(
                                                                              StringUtils.parameterToDouble( latitudeLabel,
                                                                                                             latitude ),
                                                                              StringUtils.parameterToDouble( longitudeLabel,
                                                                                                             longitude ),
                                                                              StringUtils.parameterToDouble( altitudeLabel,
                                                                                                             altitude ),
                                                                              StringUtils.parameterToDouble( minAltitudeLabel,
                                                                                                             minAltitude ),
                                                                              StringUtils.parameterToDouble( maxAltitudeLabel,
                                                                                                             maxAltitude ),
                                                                              StringUtils.parameterToInteger( specificComponentLabel,
                                                                                                              specificComponent ),
                                                                              airshipsDatabase,
                                                                              user ).call()
                                               
                                               : new PostMilitaryAirshipCommand(
                                                                                 StringUtils.parameterToDouble( latitudeLabel,
                                                                                                                latitude ),
                                                                                 StringUtils.parameterToDouble( longitudeLabel,
                                                                                                                longitude ),
                                                                                 StringUtils.parameterToDouble( altitudeLabel,
                                                                                                                altitude ),
                                                                                 StringUtils.parameterToDouble( maxAltitudeLabel,
                                                                                                                maxAltitude ),
                                                                                 StringUtils.parameterToDouble( minAltitudeLabel,
                                                                                                                minAltitude ),
                                                                                 StringUtils.parameterToBoolean( specificComponentLabel,
                                                                                                                 specificComponent ),
                                                                                 airshipsDatabase,
                                                                                 user ).call();
        
    }
    
    
    
    // INNER CLASS
    /**
     * Inner class responsible for produce a new instance of {@link PostAirshipSwingWorker}
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    
    public static class Factory implements
            SwingWorkerFactory< FunctionalPostAirshipWindow.SwingWorker, CompletionStatus > {
        
        
        // INSTANCE FIELD
        private Database< Airship > airshipDatabase;
        private PostAirshipsWindow window;
        private User user;
        
        
        // CONSTRUCTOR
        public Factory( Database< Airship > airshipDatabase, PostAirshipsWindow window, User user ) {
        
            this.airshipDatabase = airshipDatabase;
            this.window = window;
            this.user = user;
            
        }
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link PostAirshipSwingWorker}
         * 
         * @return Returns a {@link PostAirshipSwingWorker}
         */
        
        @Override
        public SwingWorker newInstance() {
        
            return new PostAirshipSwingWorker( window, airshipDatabase, user );
        }
        
    }
    
}
