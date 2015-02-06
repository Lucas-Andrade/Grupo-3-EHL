package main.java.cli.parsingtools.commandfactories.getfactories.getallfactories;


import java.util.concurrent.Callable;
import main.java.cli.parsingtools.commandfactories.CommandFactory;
import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * A {@link CommandFactory factory} that creates commands to get all the users in an users database.
 * Commands are {@link Callable} instances.
 * 
 * Extends {@link GetAllElementsInADatabaseCommandsFactory} of {@link User Users}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllUsersInADatabaseCommandsFactory extends
        GetAllElementsInADatabaseCommandsFactory< User > {
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link GetAllUsersFromADatabaseCommandFactory factory} that produces commands
     * to get all the users in {@code usersDatabase}.
     * 
     * @param usersDatabase
     *            The database where to get the users from.
     * @throws InvalidArgumentException
     *             If the {@code usersDatabase} is {@code null}.
     */
    public GetAllUsersInADatabaseCommandsFactory( Database< User > usersDatabase )
        throws InvalidArgumentException {
    
        super( usersDatabase );
    }
    
    /**
     * Returns a short description of the command produced by this factory.
     * 
     * @return a short description of the command produced by this factory.
     */
    @Override
    public String getCommandsDescription() {
    
        return "Gets the list of all users.";
    }
}
