package GSwingWorkers;


import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import app.EntitiesConversor;
import commands.getcommands.GetElementFromADatabaseByIdCommand;
import databases.Database;
import elements.User;
import entities.SimpleUser;
import exceptions.InvalidArgumentException;
import functionalcomponents.SwingWorkerForButtonFactory;
import functionalcomponents.infobuttons.EntitiesInfoButton;


/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GUISimpleUserInfoSW extends
        EntitiesInfoButton.EntitiesInfoSwingWorker< SimpleUser > {
    
    private final Database< User > database;
    private final String identification;
    
    
    /**
     * 
     * @param identification
     *            - The entity identification.
     * @param textArea
     *            - The {@link JTextArea} where to display the result.
     * @param database
     *            -The {@code User} {@link Database}, where it will
     */
    public GUISimpleUserInfoSW( String identification, JTextArea textArea, Database< User > database ) {
    
        super( textArea );
        
        this.identification = identification;
        this.database = database;
    }
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method with the
     * purpose the purpose of executing a {@link GetElementFromADatabaseByIdCommand} and obtaining
     * its result.
     * 
     * @return Returns an element from the given database that matches the given identification.
     * 
     * @throws InvalidArgumentException
     *             If any of the given parameters are invalid.
     */
    @Override
    protected SimpleUser doInBackground() throws Exception {
    
        return EntitiesConversor.toSimpleUser( new GetElementFromADatabaseByIdCommand< User >(
                                                                                               database,
                                                                                               identification ).call()
                                                                                                               .get() );
    }
    
    
    /**
     * 
     * 
     *
     */
    public class factory implements
            SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > {
        
        private Database< User > database;
        
        public factory( Database< User > database ) {
        
            this.database = database;
        }
        
        /**
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
