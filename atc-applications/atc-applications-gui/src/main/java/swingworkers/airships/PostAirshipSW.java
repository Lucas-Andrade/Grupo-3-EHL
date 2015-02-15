package swingworkers.airships;


import javax.swing.JTabbedPane;
import swingworkers.SwingWorkerFactory;
import utils.CompletionStatus;
import utils.StringUtils;
import commands.getcommands.GetElementFromADatabaseByIdCommand;
import commands.postcommands.PostCivilAirshipCommand;
import commands.postcommands.PostMilitaryAirshipCommand;
import databases.Database;
import design.windows.airshipwindows.PostAirshipsWindow;
import elements.Airship;
import elements.User;
import exceptions.InternalErrorException;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow.SwingWorker;
import functionalcomponents.functionalmainwindow.FunctionalMainWindow;


/**
 * Class whose instances have the purpose of add functionality to a {@link PostAirshipsWindow}.
 *
 * Extends {@link FunctionalPostAirshipWindow.SwingWorker}.
 *
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostAirshipSW extends FunctionalPostAirshipWindow.SwingWorker {
    
    
    // INSTANCE FIELD
    private Database< Airship > airshipsDatabase;
    private Database< User > usersDatabase;
    
    
    // CONSTRUCTOR
    public PostAirshipSW( PostAirshipsWindow window, Database< Airship > airshipsDatabase,
                          Database< User > usersDatabase ) {
    
        super( window );
        this.airshipsDatabase = airshipsDatabase;
        this.usersDatabase = usersDatabase;
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
    
        double lat = StringUtils.parameterToDouble( latitudeLabel, latitude );
        double lon = StringUtils.parameterToDouble( longitudeLabel, longitude );
        double alt = StringUtils.parameterToDouble( altitudeLabel, altitude );
        double maxAlt = StringUtils.parameterToDouble( maxAltitudeLabel, maxAltitude );
        double minAlt = StringUtils.parameterToDouble( minAltitudeLabel, minAltitude );
        
        User loggedInUser;
        try {
            loggedInUser =
                    new GetElementFromADatabaseByIdCommand<>(
                                                              usersDatabase,
                                                              FunctionalMainWindow.getLoggedUser()
                                                                                  .getIdentification() ).call()
                                                                                                        .get();
        }
        catch( Exception e ) {
            throw new InternalErrorException( "ERROR WITH THE LOGGED-IN USER IN PostAirshipSW!", e );
        }
        
        if( typeAirshipTabbedPane == 0 )
            return new PostCivilAirshipCommand(
                                                lat,
                                                lon,
                                                alt,
                                                maxAlt,
                                                minAlt,
                                                StringUtils.parameterToInteger( specificComponentLabel,
                                                                                specificComponent ),
                                                airshipsDatabase, loggedInUser ).call();
        
        return new PostMilitaryAirshipCommand(
                                               lat,
                                               lon,
                                               alt,
                                               maxAlt,
                                               minAlt,
                                               StringUtils.parameterToBoolean( specificComponentLabel,
                                                                               specificComponent ),
                                               airshipsDatabase, loggedInUser ).call();
        
    }
    
    // INNER CLASS
    /**
     * Inner class responsible for produce a new instance of {@link PostAirshipSW}
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public static class Factory implements
            SwingWorkerFactory< FunctionalPostAirshipWindow.SwingWorker, CompletionStatus > {
        
        
        // INSTANCE FIELD
        private PostAirshipsWindow window;
        private Database< Airship > airshipDatabase;
        private Database< User > usersDatabase;
        
        
        // CONSTRUCTOR
        public Factory( PostAirshipsWindow window, Database< Airship > airshipDatabase,
                        Database< User > usersDatabase ) {
        
            this.window = window;
            this.airshipDatabase = airshipDatabase;
            this.usersDatabase = usersDatabase;
        }
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link PostAirshipSW}
         * 
         * @return Returns a {@link PostAirshipSW}
         */
        @Override
        public SwingWorker newInstance() {
        
            return new PostAirshipSW( window, airshipDatabase, usersDatabase );
        }
        
    }
    
}
