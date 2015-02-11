package GSwingWorkers;


import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import GSwingWorkers.GUISimpleAirshipInfoSW.SwFactory;
import app.EntitiesConversor;
import commands.getcommands.GetElementFromADatabaseByIdCommand;
import databases.Database;
import elements.User;
import entities.SimpleUser;
import exceptions.InvalidArgumentException;
import functionalcomponents.SwingWorkerForButtonFactory;
import functionalcomponents.infobuttons.EntitiesInfoButton;
import functionalcomponents.infobuttons.SimpleUserInfoButton;


/**
 * Instances of this class are {@link SwingWorker}s associated to {@link SimpleUserInfoButton}, that
 * will GET, in the {@link SwingWorker#doInBackground() doInBackground()} method, the associated
 * {@link SimpleUser} info by its {@code identification}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GUISimpleUserInfoSW extends EntitiesInfoButton.EntitiesInfoSwingWorker< SimpleUser > {
    
    private final Database< User > database;
    private final String identification;
    
    
    /**
     * Create a {@link SwingWorker} associated to a {@code SimpleUser} {@code identification}.
     * 
     * 
     * @param identification
     *            - The {@link SimpleUser} identification.
     * @param textArea
     *            - The {@link JTextArea} where to display the result.
     * @param database
     *            - The {@link User} {@link Database}.
     */
    public GUISimpleUserInfoSW( String identification, JTextArea textArea, Database< User > database ) {
    
        super( textArea );
        
        this.identification = identification;
        this.database = database;
    }
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method with the
     * purpose of execute the {@link GetElementFromADatabaseByIdCommand}, obtaining its result (
     * {@code User}), and convert it to {@code SimpleUser}.
     * 
     * @return Returns an {@link SimpleUser} from the {@code database} that matches the given
     *         {@code identification}.
     * 
     * @throws InvalidArgumentException
     *             If either database or identification are null.
     */
    @Override
    protected SimpleUser doInBackground() throws Exception {
    
        return new EntitiesConversor().toSimpleUser( new GetElementFromADatabaseByIdCommand< User >(
                                                                                                     database,
                                                                                                     identification ).call()
                                                                                                                     .get() );
    }
    
    
    /**
     * Instances of the class are {@link SwingWorker} {@code factories}, that creates instances of
     * {@link GUISimpleUserInfoSW} that will be run in {@link SimpleUserInfoButton}.
     *
     * @param <SwingWorker>
     *            The type of the {@link SwingWorker} returned in the method {@link #newInstance()}.
     * @param <SimpleUser>
     *            The type of the results returned by the methods
     *            {@link SwingWorker#doInBackground()} and {@link SwingWorker#get()}.
     */
    public class factory implements
            SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > {
        
        private Database< User > database;
        
        /**
         * Creates a {@link SwingWorker} {@code factories}, that creates instances of
         * {@link GUISimpleUserInfoSW} using the {@link SwFactory#newInstance} that will be run in
         * {@link SimpleUserInfoButton}.
         * 
         * @param database
         *            - The {@code User} {@link Database}.
         */
        public factory( Database< User > database ) {
        
            this.database = database;
        }
        
        /**
         * Produces a new instance of {@link GUISimpleUserInfoSW}.
         * 
         * @see functionalcomponents.SwingWorkerForButtonFactory#newInstance(String, JTextArea)
         */
        @Override
        public SwingWorker< SimpleUser, Void > newInstance( String identification,
                                                            JTextArea textArea ) {
        
            return new GUISimpleUserInfoSW( identification, textArea, database );
        }
    }
}
