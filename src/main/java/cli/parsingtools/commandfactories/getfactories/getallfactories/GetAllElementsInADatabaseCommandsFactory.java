package main.java.cli.parsingtools.commandfactories.getfactories.getallfactories;


import java.util.concurrent.Callable;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.domain.commands.getcommands.GetAllElementsInADatabaseCommand;
import main.java.domain.model.Database;
import main.java.domain.model.Element;
import main.java.utils.Optional;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Abstract class whose subclasses instances are {@link StringsToCommandsFactory factories} that
 * produce commands of type {@link GetAllElementsInADatabaseCommand}.
 * 
 * Extends {@link StringsToCommandsFactory} of {@link Optional} {@link Iterable Iterables<E>}.
 * 
 * @param <E>
 *            - The type of elements that a database can contain.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes.
 */
public abstract class GetAllElementsInADatabaseCommandsFactory< E extends Element > extends
        StringsToCommandsFactory< Optional< Iterable< E >>> {
    
    // INSTANCE FIELDS
    
    /**
     * {@code database} - The database where to get the elements from.
     */
    private final Database< E > database;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link GetAllElementsInADatabaseCommandFactory factory} that produces commands
     * of type {@link GetAllElementsInADatabaseCommand}.
     * 
     * @param commandsDescription
     *            - A short description of the commands produced by this factory.
     * 
     * @param database
     *            The database where to get the elements from.
     * @throws InvalidArgumentException
     *             If either {@code commandsDescription} or {@code database} are {@code null}.
     */
    public GetAllElementsInADatabaseCommandsFactory( String commandsDescription,
                                                     Database< E > database )
        throws InvalidArgumentException {
        
        super( commandsDescription );
        
        if( database == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null database!" );
        
        this.database = database;
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
    
    /**
     * Returns a command of type {@link GetAllElementsInADatabaseCommand}.
     * 
     * @return A command of type {@link GetAllElementsInADatabaseCommand}.
     */
    @Override
    protected Callable< Optional< Iterable< E >>> internalNewInstance() {
        
        try {
            return new GetAllElementsInADatabaseCommand< E >( database );
            
        }
        catch( InvalidArgumentException e ) { // never happens because database is not null
            return null;
        }
    }
    
    /**
     * Returns an array of strings with name of the parameters needed to produce the command - in
     * this case it will return {@code null} because factories of this type need no parameters to
     * create their commands.
     * 
     * @return {@code null}.
     */
    @Override
    protected String[] getRequiredParametersNames() {
        
        return null;
    }
}