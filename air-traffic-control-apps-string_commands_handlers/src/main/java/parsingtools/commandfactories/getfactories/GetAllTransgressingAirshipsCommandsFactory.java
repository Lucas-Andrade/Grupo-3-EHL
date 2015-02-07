package parsingtools.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;

import parsingtools.commandfactories.CommandFactory;
import utils.Optional;

import commands.getcommands.GetAllTransgressingAirshipsCommand;

import databases.Database;
import elements.Airship;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;


/**
 * Class whose instances are {@link CommandFactory factories} that produce commands of type
 * {@link GetAllTransgressingAirshipsCommand}. Commands are {@link Callable} instances.
 * 
 * Extends {@link CommandFactory} of {@link Optional} {@link Iterable Iterables} of {@link Airship}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllTransgressingAirshipsCommandsFactory extends
        CommandFactory< Optional< Iterable< Airship >>> {
    
    // INSTANCE FIELDS
    
    /**
     * {@code airshipsDatabase} - The database where to search the elements from.
     */
    private final Database< Airship > airshipsDatabase;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link GetAllElementsInADatabaseCommandFactory factory} that produces commands
     * of type {@link GetAllTransgressingAirshipsCommand}.
     * 
     * @param airshipsDatabase
     *            - The airshipsDatabase where to get the elements from.
     * 
     * @throws InvalidArgumentException
     *             If the {@code airshipsDatabase} is null.
     */
    public GetAllTransgressingAirshipsCommandsFactory( Database< Airship > airshipsDatabase )
        throws InvalidArgumentException {
    
        if( airshipsDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null database!" );
        
        this.airshipsDatabase = airshipsDatabase;
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
    
    /**
     * Returns a command of type {@link GetAllTransgressingAirshipsCommand}.
     * 
     * @param parametersMap
     *            The container of the parameters required to create the command.
     * 
     * @return A command of type {@link GetAllTransgressingAirshipsCommand}.
     */
    @Override
    protected Callable< Optional< Iterable< Airship >>>
            internalNewCommand( Map< String, String > parametersMap ) {
    
        try {
            return new GetAllTransgressingAirshipsCommand( airshipsDatabase );
        }
        catch( InvalidArgumentException e ) {
            throw new InternalErrorException(
                                              "UNEXPECTED EXCEPTION IN GetAllTransgressingAirshipsCommandsFactory!",
                                              e );
        }
        // never happens because database is not null
    }
    
    /**
     * Returns an array of strings with the name of the parameters needed to produce the command -
     * in this case it will return {@code null} because factories of this type need no parameters to
     * create their commands.
     * 
     * @return {@code null}.
     */
    @Override
    protected String[] getRequiredParametersNames() {
    
        return null;
    }
    
    /**
     * Returns a short description of the command produced by this factory.
     * 
     * @return a short description of the command produced by this factory.
     */
    @Override
    public String getCommandsDescription() {
    
        return "Gets all airships that are transgressing their air corridors.";
    }
}
