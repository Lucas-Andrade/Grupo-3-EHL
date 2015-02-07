package parsingtools.commandfactories.getfactories.getallfactories;


import java.util.Map;
import java.util.concurrent.Callable;

import parsingtools.commandfactories.CommandFactory;
import utils.Optional;

import commands.getcommands.GetAllElementsInADatabaseCommand;

import databases.Database;
import elements.Element;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;


/**
 * Abstract class whose subclasses instances are {@link CommandFactory factories} that produce
 * commands of type {@link GetAllElementsInADatabaseCommand}.
 * 
 * Extends {@link CommandFactory} of {@link Optional} {@link Iterable Iterables<E>}.
 * 
 * @param <E>
 *            - The type of elements that a database can contain.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes.
 */
public abstract class GetAllElementsInADatabaseCommandsFactory< E extends Element > extends
        CommandFactory< Optional< Iterable< E >>> {
    
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
    public GetAllElementsInADatabaseCommandsFactory( Database< E > database )
        throws InvalidArgumentException {
    
        
        if( database == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null database!" );
        
        this.database = database;
    }
    
    /**
     * Returns a command of type {@link GetAllElementsInADatabaseCommand}.
     * 
     * @param parametersMap
     *            The container of the parameters required to create the command.
     * 
     * @return A command of type {@link GetAllElementsInADatabaseCommand}.
     */
    @Override
    protected Callable< Optional< Iterable< E >>>
            internalNewCommand( Map< String, String > parametersMap ) {
    
        try {
            return new GetAllElementsInADatabaseCommand< E >( database );
        }
        catch( InvalidArgumentException e ) {
            throw new InternalErrorException(
                                              "UNEXPECTED EXCEPTION IN GetAllElementsInADatabaseCommandsFactory!" );
            // never happens cause database is not null
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
