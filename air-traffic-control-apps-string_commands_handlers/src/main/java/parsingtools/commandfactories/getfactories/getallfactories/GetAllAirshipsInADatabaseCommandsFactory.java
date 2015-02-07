package parsingtools.commandfactories.getfactories.getallfactories;


import java.util.concurrent.Callable;
import parsingtools.commandfactories.CommandFactory;
import databases.Database;
import elements.Airship;
import exceptions.InvalidArgumentException;


/**
 * A {@link CommandFactory factory} that creates commands to get all the airships in an airships
 * database. Commands are {@link Callable} instances.
 * 
 * Extends {@link GetAllElementsInADatabaseCommandsFactory} of {@link Airship Airships}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllAirshipsInADatabaseCommandsFactory extends
        GetAllElementsInADatabaseCommandsFactory< Airship > {
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link GetAllAirshipsFromADatabaseCommandFactory factory} that produces
     * commands to get all the airships in {@code airshipDatabase}.
     * 
     * @param airshipDatabase
     *            - The database where to get the airships from.
     * 
     * @throws InvalidArgumentException
     *             If the {@code airshipDatabase} is {@code null}.
     */
    public GetAllAirshipsInADatabaseCommandsFactory( Database< Airship > airshipDatabase )
        throws InvalidArgumentException {
        
        super( airshipDatabase );
    }
    
    /**
     * Returns a short description of the command produced by this factory.
     * 
     * @return a short description of the command produced by this factory.
     */
    @Override
    public String getCommandsDescription() {
        
        return "Gets the list of all airships.";
    }
}
