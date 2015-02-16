package swingworkers.users;


import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import swingworkers.SwingWorkerForButtonFactory;
import utils.EntitiesConversor;
import commands.getcommands.GetElementFromADatabaseByIdCommand;
import databases.Database;
import elements.User;
import entities.SimpleUser;
import exceptions.InvalidArgumentException;
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
public class GetUserByIdSW extends EntitiesInfoButton.EntitiesInfoSwingWorker< SimpleUser > {
    
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
    public GetUserByIdSW( String identification, JTextArea textArea, Database< User > database ) {
    
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
    
        User user =
                new GetElementFromADatabaseByIdCommand< User >( database, identification ).call()
                                                                                          .get();
        return new EntitiesConversor().toSimpleUser( user );
    }
    
    
    /**
     * Instances of the class are {@link SwingWorker} {@code factories}, that creates instances of
     * {@link GetUserByIdSW} that will be run in {@link SimpleUserInfoButton}.
     *
     * @param <SwingWorker>
     *            The type of the {@link SwingWorker} returned in the method {@link #newInstance()}.
     * @param <SimpleUser>
     *            The type of the results returned by the methods
     *            {@link SwingWorker#doInBackground()} and {@link SwingWorker#get()}.
     */
    public static class Factory implements
            SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > {
        
        private Database< User > database;
        
        /**
         * Creates a {@link SwingWorker} {@code factories}, that creates instances of
         * {@link GetUserByIdSW} using the {@link Factory#newInstance} that will be run in
         * {@link SimpleUserInfoButton}.
         * 
         * @param database
         *            - The {@code User} {@link Database}.
         */
        public Factory( Database< User > database ) {
        
            this.database = database;
        }
        
        /**
         * Produces a new instance of {@link GetUserByIdSW}.
         * 
         * @see swingworkers.SwingWorkerForButtonFactory#newInstance(String, JTextArea)
         */
        @Override
        public SwingWorker< SimpleUser, Void > newInstance( String identification,
                                                            JTextArea textArea ) {
        
            return new GetUserByIdSW( identification, textArea, database );
        }
    }
}
