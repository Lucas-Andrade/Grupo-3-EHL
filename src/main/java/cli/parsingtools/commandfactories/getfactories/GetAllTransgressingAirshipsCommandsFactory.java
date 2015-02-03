package main.java.cli.parsingtools.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.parsingtools.commandfactories.CommandFactory;
import main.java.cli.parsingtools.commandfactories.ParsingCommand;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllElementsInADatabaseCommandsFactory;
import main.java.domain.commands.getcommands.GetAllTransgressingAirshipsCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.utils.Optional;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.WrongLoginPasswordException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * Class whose instances are {@link ParsingCommand factories} that produce commands of type
 * {@link GetAllTransgressingAirshipsCommand}. Commands are {@link Callable} instances.
 * 
 * Extends {@link GetAllElementsInADatabaseCommandsFactory} of {@link Optional} {@link Iterable
 * Iterables} of {@link Airship}.
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
     * @return A command of type {@link GetAllTransgressingAirshipsCommand}.
     */
    @Override
    protected Callable< Optional< Iterable< Airship >>>
            internalNewCommand( Map< String, String > parametersMap )
                throws InvalidParameterValueException, WrongLoginPasswordException,
                NoSuchElementInDatabaseException, InternalErrorException,
                MissingRequiredParameterException, InvalidArgumentException {
        
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
    
    @Override
    public String getCommandsDescription() {
        return "Gets all airships that are transgressing their air corridors.";
    }
}
