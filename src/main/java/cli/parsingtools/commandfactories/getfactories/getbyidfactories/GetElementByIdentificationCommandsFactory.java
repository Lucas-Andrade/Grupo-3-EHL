package main.java.cli.parsingtools.commandfactories.getfactories.getbyidfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.parsingtools.commandfactories.CommandFactory;
import main.java.cli.parsingtools.commandfactories.ParsingCommand;
import main.java.domain.commands.getcommands.GetElementFromADatabaseByIdCommand;
import main.java.domain.model.Database;
import main.java.domain.model.Element;
import main.java.utils.Optional;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.MissingRequiredParameterException;


/**
 * Abstract class whose subclasses' instances are {@link CommandFactory factories} that produce
 * commands of type {@link GetElementFromADatabaseByIdCommand}. To ensure that the factory is
 * immutable, first is produced a {@link ParsingCommand} that will parse all necessary
 * {@code required parameters}. Commands are {@link Callable} instances.
 * 
 * Extends {@link CommandFactory} of {@link Optional Optional<E>}
 * 
 * @param <E>
 *            - The type of elements that a database can contain.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class GetElementByIdentificationCommandsFactory< E extends Element > extends
        CommandFactory< Optional< E >> {
    
    // INSTANCE FIELDS
    
    /**
     * {@code requiredParametersNames} - The array of strings with the names of the parameters
     * needed to produce the command.
     */
    private final String[] requiredParametersNames;
    
    /**
     * {@code database} - The database where to get the elements from.
     */
    private final Database< E > database;
    
    
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link GetElementByIdentificationCommandFactory} that produces commands of type
     * {@link GetElementFromADatabaseByIdCommand}.
     * 
     * @param identificationParameterName
     *            - The name of the parameter (whose value is the element's identification) to look
     *            for in the key-set of the {@link Map} of parameters received in the method
     *            {@link #newCommand(Map)} .
     * @param database
     *            - The database where to get the elements from.
     * 
     * @throws InvalidArgumentException
     *             If either {@code identificationParameterName} or {@code database} are
     *             {@code null}.
     */
    public GetElementByIdentificationCommandsFactory( String identificationParameterName,
                                                      Database< E > database )
        throws InvalidArgumentException {
    
        if( identificationParameterName == null )
            throw new InvalidArgumentException(
                                                "Cannot instantiate factory with null parameter name!" );
        
        if( database == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null database!" );
        
        this.requiredParametersNames = new String[]{ identificationParameterName };
        this.database = database;
    }
    
    
    /**
     * Returns a command of type {@link GetElementFromADatabaseByIdCommand} after produced a
     * {@link ParsingCommand} that will parse necessary {@code required parameters}.
     * 
     * @param parametersMap
     *            The container of the parameters required to create the command.
     * 
     * @return A command of type {@link GetElementFromADatabaseByIdCommand}.
     * @throws MissingRequiredParameterException
     *             If any of the required parameters is missing.
     */
    @Override
    protected Callable< Optional< E >> internalNewCommand( Map< String, String > parametersMap )
        throws MissingRequiredParameterException {
    
        return new GetEBIC_ParsingCommand( parametersMap ).newCommand();
    }
    
    /**
     * Returns an array of strings with name of the parameter needed to produce the command- in this
     * case the name of the parameter that contains the desired element's identification.
     * 
     * @return An array of strings with the name of the required parameters.
     */
    @Override
    protected String[] getRequiredParametersNames() {
    
        return requiredParametersNames;
    }
    
    // INNER CLASS
    /**
     * Class that extends {@link ParsingCommand}, whose instances will parse the
     * {@code required parameters} and will create a {@link GetElementFromADatabaseByIdCommand}
     */
    private class GetEBIC_ParsingCommand extends ParsingCommand< Optional< E >> {
        
        /**
         * {@code identification} - The identification of the element to get from {@link #database}.
         */
        private String identification;
        
        /**
         * Create the {@code ParsingCommand}
         * 
         * @param parametersMap
         *            The container of the parameters required to create the command.
         * 
         * @throws MissingRequiredParameterException
         *             If any of the required parameters is missing.
         */
        public GetEBIC_ParsingCommand( Map< String, String > parametersMap )
            throws MissingRequiredParameterException {
        
            super( parametersMap );
            identification = getParameterAsString( requiredParametersNames[0] );
        }
        
        /**
         * Returns a command of type {@link GetElementFromADatabaseByIdCommand}.
         * 
         * @return A command of type {@link GetElementFromADatabaseByIdCommand}.
         */
        @Override
        public Callable< Optional< E >> newCommand() {
        
            try {
                return new GetElementFromADatabaseByIdCommand< E >( database, identification );
            }
            catch( InvalidArgumentException e ) {
                throw new InternalErrorException(
                                                  "UNEXPECTED EXCEPTION IN GetElementByIdentificationCommandsFactory!",
                                                  e );
                // never happens cause database is not null
            }
        }
    }
}
