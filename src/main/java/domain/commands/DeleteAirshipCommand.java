package main.java.domain.commands;


import java.util.concurrent.Callable;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.DatabaseException;


/**
 * Class whose instances are {@link Callable}s that delete an {@link Airship} from the
 * {@link Database} given its identification.
 * 
 * Implements the Interface {@link Callable} of {@link String}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class DeleteAirshipCommand implements Callable< String > {
    
    /**
     * The database that contains the airship we want to delete.
     */
    private final Database< Airship > airshipDatabase;
    
    /**
     * The airship identification.
     */
    private final String identification;
    
    /**
     * Creates a new instance of this command that delete a {@link Airship} by its
     * {@code identification}.
     * 
     * @param airshipDatabase
     *            - The database whose elements will be deleted.
     * @param identification
     *            - Airship identification
     * @throws InvalidArgumentException
     *             If the {@code database} or {@code identification} are null.
     */
    public DeleteAirshipCommand( Database< Airship > airshipDatabase, String identification )
        throws InvalidArgumentException {
        if( airshipDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        if( identification == null )
            throw new InvalidArgumentException(
                                                "Cannot instantiate command with null identification." );
        
        this.airshipDatabase = airshipDatabase;
        this.identification = identification;
    }
    
    /**
     * Delete the {@code Airship} with the given {@code identification} from the
     * {@code airshipDatabase}. If the {@code Airship} is deleted is returned the message <i>Airship
     * successfully removed<i>, otherwise is returned <i>Airship doesn't exist in the database<i>.
     * 
     * @throws DatabaseException
     *             - If performing a forbidden operation in a database.
     */
    @Override
    public String call() throws DatabaseException {
        try {
            if( airshipDatabase.removeByIdentification( identification ) )
                return "Airship successfully removed";
        }
        catch( InvalidArgumentException e ) {// never happens because identification is non null
        }
        
        return "Airship doesn't exist in the database";
    }
}
