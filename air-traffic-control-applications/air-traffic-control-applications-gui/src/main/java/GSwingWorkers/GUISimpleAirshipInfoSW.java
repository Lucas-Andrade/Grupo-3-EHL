package GSwingWorkers;


import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import app.EntitiesConversor;
import commands.getcommands.GetElementFromADatabaseByIdCommand;
import databases.Database;
import elements.Airship;
import entities.SimpleAirship;
import exceptions.InvalidArgumentException;
import functionalcomponents.SwingWorkerForButtonFactory;
import functionalcomponents.infobuttons.EntitiesInfoButton;


/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GUISimpleAirshipInfoSW extends
        EntitiesInfoButton.EntitiesInfoSwingWorker< SimpleAirship > {
    
    private Database< Airship > database;
    private String identification;
    
    
    
    /**
     * 
     * @param identification
     *            - The entity identification.
     * @param textArea
     *            - The {@link JTextArea} where to display the result.
     * @param database
     *            -The {@code User} {@link Database}, where it will
     */
    public GUISimpleAirshipInfoSW( String identification, JTextArea textArea,
                                    Database< Airship > database ) {
    
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
    protected SimpleAirship doInBackground() throws Exception {
    
        return EntitiesConversor.toSimpleAirship( new GetElementFromADatabaseByIdCommand< Airship >(
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
            SwingWorkerForButtonFactory< SwingWorker< SimpleAirship, Void >, SimpleAirship > {
        
        private Database< Airship > database;

        public factory( Database< Airship > database ) {
        
            this.database = database;
        }
        
        /**
         * 
         * @see functionalcomponents.SwingWorkerForButtonFactory#newInstance(String, JTextArea)
         */
        @Override
        public SwingWorker< SimpleAirship, Void > newInstance( String identification,
                                                            JTextArea textArea ) {
        
            return new GUISimpleAirshipInfoSW( identification, textArea, database );
        }
    }
    
    
}
